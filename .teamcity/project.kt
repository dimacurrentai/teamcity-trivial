import jetbrains.buildServer.configs.kotlin.*

object TrivialProject : Project({
    name = "TeamCity Trivial"

    buildType(SimpleBuild)
})
