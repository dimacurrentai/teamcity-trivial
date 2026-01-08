import jetbrains.buildServer.configs.kotlin.v2025_11.*
import jetbrains.buildServer.configs.kotlin.v2025_11.Project

object TrivialProject : Project({
    name = "TeamCity Trivial"

    buildType(SimpleBuild)
})
