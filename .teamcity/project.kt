import jetbrains.buildServer.configs.kotlin.v2021_2.*

object TrivialProject : Project({
    name = "TeamCity Trivial"

    buildType(SimpleBuild)
})
