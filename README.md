## TeamCity Trivial Template

This repository is a minimal TeamCity Kotlin DSL project that auto-populates a single build with three steps (generated from a list in `.teamcity/settings.kts`):

1. echo "hello"
2. sleep 3
3. echo "world"

How to use with TeamCity Versioned Settings

- In TeamCity, create a project and enable Versioned Settings.
- Choose Kotlin format and point it to this repository.
- TeamCity will import the `.teamcity/` directory and create the build configuration automatically.

Notes

- This repo currently uses `version = "2025.11"` in `.teamcity/settings.kts`.

### ParametrizedMagic (required parameters)

The `ParametrizedMagic` build configuration requires two TeamCity parameters:

- `env.A` (exposed to build steps as environment variable `A`)
- `env.B` (exposed to build steps as environment variable `B`)

When you trigger `ParametrizedMagic` manually, TeamCity will prompt you to enter values for **A** and **B**.
