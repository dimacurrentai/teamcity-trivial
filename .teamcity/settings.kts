import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
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

    steps {
        script {
            name = "magic"
            id = "magic"
            scriptContent = "echo magic"
        }
        script {
            name = "works"
            id = "works"
            scriptContent = "echo workd"
        }
    }
})
