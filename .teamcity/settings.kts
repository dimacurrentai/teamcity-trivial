import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import java.io.File

version = "2025.11"

project {
    buildType(Magic)
}

object Magic : BuildType({
    name = "Magic"

    vcs {
        root(DslContext.settingsRoot)
    }

    fun shellSingleQuoteLiteral(text: String): String {
        // POSIX-safe single-quote escape: '  ->  '"'"'
        return "'" + text.replace("'", "'\"'\"'") + "'"
    }

    // Read during DSL "setup" (generation) time and embed into the step body as inert data
    val embeddedFileTxt = shellSingleQuoteLiteral(File("file.txt").readText(Charsets.UTF_8))

    val magicSteps = listOf(
        Triple(
            "echo_hello",
            "echo \"hello2\"",
            """
            set -eu
            echo "hello2"
            echo "----- embedded file.txt (read during DSL setup) -----"
            printf '%s' $embeddedFileTxt
            printf '\n'
            echo "----------------------------------------------------"
            """.trimIndent()
        ),
        Triple("sleep_3", "sleep 3", "sleep 3"),
        Triple("echo_world", "echo \"world\"", "echo \"world\""),
    )

    steps {
        magicSteps.forEach { (stepId, stepName, stepScriptContent) ->
            println("Building step: id=$stepId name=$stepName")
            script {
                name = stepName
                id = stepId
                scriptContent = stepScriptContent
            }
        }
    }
})
