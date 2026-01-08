import jetbrains.buildServer.configs.kotlin.v2021_2.*

object SimpleBuild : BuildType({
    name = "Simple Pipeline"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "echo hello"
            scriptContent = "echo \"hello\""
        }
        script {
            name = "sleep 3"
            scriptContent = "sleep 3"
        }
        script {
            name = "echo world"
            scriptContent = "echo \"world\""
        }
    }
})
