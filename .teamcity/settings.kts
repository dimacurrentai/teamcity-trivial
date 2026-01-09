import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script

version = "2025.11"

private data class ScriptStepDef(
    val id: String,
    val name: String,
    val scriptContent: String,
)

private val MAGIC_STEPS: List<ScriptStepDef> = listOf(
    ScriptStepDef(
        id = "echo_hello",
        name = "echo \"hello\"",
        scriptContent = "echo \"hello\"",
    ),
    ScriptStepDef(
        id = "sleep_3",
        name = "sleep 3",
        scriptContent = "sleep 3",
    ),
    ScriptStepDef(
        id = "echo_world",
        name = "echo \"world\"",
        scriptContent = "echo \"world\"",
    ),
)

project {
    buildType(Magic)
}

object Magic : BuildType({
    name = "Magic"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        MAGIC_STEPS.forEach { step ->
            script {
                name = step.name
                id = step.id
                scriptContent = step.scriptContent
            }
        }
    }
})
