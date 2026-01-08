import jetbrains.buildServer.configs.kotlin.*

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

import jetbrains.buildServer.configs.kotlin.*

object SimpleBuild : BuildType({
    name = "Simple Pipeline"

    vcs {
        root(DslContext.settingsRoot)
    }

    requirements {
        matches("teamcity.agent.jvm.os.name", ".*(Linux|Mac).*")
    }

    steps {
        script {
            name = "Say Hello"
            scriptContent = "echo \"hello\""
        }
        script {
            name = "Sleep 3 Seconds"
            scriptContent = "sleep 3"
        }
        script {
            name = "Say World"
            scriptContent = "echo \"world\""
        }
    }

    triggers {
        vcs {
            branchFilter = "+:*"
        }
    }
})
