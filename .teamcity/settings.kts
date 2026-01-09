import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.script

version = "2025.11"

project {
    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "hw3"
            id = "hw3"
            scriptContent = "echo hw4"
        }
        script {
            name = "hw4"
            id = "hw4"
            scriptContent = "echo hw4"
        }
        script {
            name = "hw5"
            id = "hw5"
            scriptContent = "echo hw5"
        }
    }

    features {
        perfmon {
        }
    }
})
