import jetbrains.buildServer.configs.kotlin.v2024_03.*
import jetbrains.buildServer.configs.kotlin.v2024_03.BuildType

object SimpleBuild : BuildType({
    name = "Simple Pipeline"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "Say Hello"
            scriptContent = """echo \"hello\""".trimIndent()
        }
        script {
            name = "Sleep 3 Seconds"
            scriptContent = """sleep 3""".trimIndent()
        }
        script {
            name = "Say World"
            scriptContent = """echo \"world\""".trimIndent()
        }
    }

    triggers {
        vcs {
            branchFilter = "+:*"
        }
    }
})
