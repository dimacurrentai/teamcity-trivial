package project

import jetbrains.buildServer.configs.kotlin.v2025_11.*
import jetbrains.buildServer.configs.kotlin.v2025_11.Project

object Project : Project({
    name = "Root project"

    buildType(buildTypes.SimpleBuild)
})
