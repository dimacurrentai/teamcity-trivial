import jetbrains.buildServer.configs.kotlin.v2024_03.*
import jetbrains.buildServer.configs.kotlin.v2024_03.Project

object TrivialProject : Project({
    name = "TeamCity Trivial"

    buildType(SimpleBuild)
})
