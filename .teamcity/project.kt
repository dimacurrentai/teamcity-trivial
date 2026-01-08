import jetbrains.buildServer.configs.kotlin.v2025_11.*

object TrivialProject : Project({
    name = "TeamCity Trivial"

    buildType(SimpleBuild)
})
