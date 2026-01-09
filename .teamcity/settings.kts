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

    fun embedFileAsHereDoc(path: String, labelBase: String = "FILE_EOF"): String {
        val text = File(path).readText(Charsets.UTF_8).trimEnd()
        val lines = text.lineSequence().toSet()

        var label = labelBase
        var i = 1
        while (lines.contains(label)) {
            label = "${labelBase}_$i"
            i += 1
        }

        return buildString {
            appendLine("cat <<'$label'")
            if (text.isNotEmpty()) appendLine(text)
            appendLine(label)
        }
    }

    // Read during DSL "setup" (generation) time and embed into the step body
    val embeddedFileTxt = embedFileAsHereDoc("file.txt", labelBase = "FILE_TXT_EOF")

    val magicSteps = listOf(
        Triple(
            "echo_hello",
            "echo \"hello2\"",
            """
            set -euo pipefail
            echo "hello2"
            echo "----- embedded file.txt (read during DSL setup) -----"
            $embeddedFileTxt
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
