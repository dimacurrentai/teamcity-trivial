import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

version = "2024.03"

project {

    vcsRoot(HttpsGithubComDimacurrentaiTeamcityTrivialRefsHeadsMain)

    buildType(Blah)
}

object Blah : BuildType({
    name = "Blah"

    vcs {
        root(HttpsGithubComDimacurrentaiTeamcityTrivialRefsHeadsMain)
    }

    steps {
        script {
            name = "hw2"
            id = "hw2"
            scriptContent = "echo hw2"
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})

object HttpsGithubComDimacurrentaiTeamcityTrivialRefsHeadsMain : GitVcsRoot({
    name = "https://github.com/dimacurrentai/teamcity-trivial#refs/heads/main"
    url = "https://github.com/dimacurrentai/teamcity-trivial"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
})
