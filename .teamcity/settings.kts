import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import java.io.File

version = "2025.11"

val Magic = object : BuildType({
    id("Magic")
    name = "Magic"

    fun shellSingleQuoteLiteral(text: String): String {
        // POSIX-safe single-quote escape: '  ->  '"'"'
        return "'" + text.replace("'", "'\"'\"'") + "'"
    }

    fun makeStepId(stepIndex: Int, stepPath: String): String {
        val slug = stepPath
            .trim()
            .replace(Regex("""[^A-Za-z0-9]+"""), "_")
            .trim('_')
            .ifBlank { "step" }
            .take(60)

        val indexPrefix = (stepIndex + 1).toString().padStart(2, '0')
        return "step_${indexPrefix}_$slug"
    }

    fun BuildSteps.buildStepsFromStepsTxt(stepsTxtName: String) {
        // TeamCity evaluates DSL from the .teamcity/ directory, so this reads from that directory.
        val settingsDir = File(".").canonicalFile
        val stepsTxt = File(settingsDir, stepsTxtName)
        println("DSL settings dir: ${settingsDir.path}")
        println("Loading steps from: ${stepsTxt.path}")

        val stepPaths = stepsTxt
            .readLines(Charsets.UTF_8)
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .filterNot { it.startsWith("#") }

        stepPaths.forEachIndexed { stepIndex, stepPath ->
            val stepId = makeStepId(stepIndex, stepPath)
            val quotedStepPath = shellSingleQuoteLiteral(stepPath)
            println("Building step: id=$stepId name=$stepPath")
            script {
                id = stepId
                name = stepPath
                scriptContent = """
                set -eu
                echo "Running step: id=$stepId path=$stepPath"
                sh $quotedStepPath
                """.trimIndent()
            }
        }
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        buildStepsFromStepsTxt("steps.txt")
    }
}) {}

val ParametrizedMagic = object : BuildType({
    id("ParametrizedMagic")
    name = "ParametrizedMagic"

    fun shellSingleQuoteLiteral(text: String): String {
        // POSIX-safe single-quote escape: '  ->  '"'"'
        return "'" + text.replace("'", "'\"'\"'") + "'"
    }

    fun makeStepId(stepIndex: Int, stepPath: String): String {
        val slug = stepPath
            .trim()
            .replace(Regex("""[^A-Za-z0-9]+"""), "_")
            .trim('_')
            .ifBlank { "step" }
            .take(60)

        val indexPrefix = (stepIndex + 1).toString().padStart(2, '0')
        return "step_${indexPrefix}_$slug"
    }

    fun BuildSteps.buildStepsFromStepsTxt(stepsTxtName: String) {
        // TeamCity evaluates DSL from the .teamcity/ directory, so this reads from that directory.
        val settingsDir = File(".").canonicalFile
        val stepsTxt = File(settingsDir, stepsTxtName)
        println("DSL settings dir: ${settingsDir.path}")
        println("Loading steps from: ${stepsTxt.path}")

        val stepPaths = stepsTxt
            .readLines(Charsets.UTF_8)
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .filterNot { it.startsWith("#") }

        stepPaths.forEachIndexed { stepIndex, stepPath ->
            val stepId = makeStepId(stepIndex, stepPath)
            val quotedStepPath = shellSingleQuoteLiteral(stepPath)
            println("Building step: id=$stepId name=$stepPath")
            script {
                id = stepId
                name = stepPath
                scriptContent = """
                set -eu
                echo "Running step: id=$stepId path=$stepPath"
                sh $quotedStepPath
                """.trimIndent()
            }
        }
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    params {
        param("env.A", "1")
        param("env.B", "2")
    }

    steps {
        buildStepsFromStepsTxt("steps.txt")
    }
}) {}

project {
    buildType(Magic)
    buildType(ParametrizedMagic)
}
