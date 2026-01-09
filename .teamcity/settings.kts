import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script

version = "2025.11"

project {
    buildType(Magic)
}

object Magic : BuildType({
    name = "Magic"

    vcs {
        root(DslContext.settingsRoot)
    }

    val magicSteps = listOf(
        Triple("echo_hello", "echo \"hello2\"", "echo \"hello2\""),
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
