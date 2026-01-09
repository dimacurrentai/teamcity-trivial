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

    // Confirm Kotlin DSL is being evaluated from the .teamcity/ directory
    val settingsDir = File(".").canonicalFile
    println("DSL settings dir (expected .teamcity): ${settingsDir.path}")

    // NOTE: TeamCity's DSL sandbox blocks reading files outside .teamcity/ (e.g. ../file.txt).
    // Read during DSL "setup" (generation) time from inside .teamcity/ instead.
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
                scriptContent = """
                echo "Building step (runtime): id=$stepId name=$stepName"
                $stepScriptContent
                """.trimIndent()
            }
        }
    }
})
