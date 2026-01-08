package buildTypes

import jetbrains.buildServer.configs.kotlin.v2025_11.*

object SimpleBuild : BuildType({
    name = "Simple Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "Hello"
            scriptContent = "echo hello"
        }
        script {
            name = "Sleep"
            scriptContent = "sleep 3"
        }
        script {
            name = "World"
            scriptContent = "echo world"
        }
    }
})
