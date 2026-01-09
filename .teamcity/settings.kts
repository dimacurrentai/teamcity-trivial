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

    // Read during DSL "setup" (generation) time from one directory above .teamcity/
    val fileOneDirUp = File(settingsDir.parentFile, "file.txt")
    require(fileOneDirUp.exists()) { "Expected file one dir up from .teamcity: ${fileOneDirUp.path}" }
    val embeddedFileTxt = shellSingleQuoteLiteral(fileOneDirUp.readText(Charsets.UTF_8))

    val magicSteps = listOf(
        Triple(
            "echo_helloA",
            "echo \"helloB\"",
            """
            set -eu
            echo "helloC"
            echo "----- embedded file.txt (read during DSL setup) -----"
            printf '%s' $embeddedFileTxt
            printf '\n'
            echo "----------------------------------------------------"
            """.trimIndent()
        ),
        Triple("sleep_1", "sleep 1", "sleep 1"),
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
