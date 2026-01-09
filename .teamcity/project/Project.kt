package project

import jetbrains.buildServer.configs.kotlin.v2024_03.*
import jetbrains.buildServer.configs.kotlin.v2024_03.Project

object Project : Project({
    name = "Root project"

    buildType(buildTypes.SimpleBuild)
})
