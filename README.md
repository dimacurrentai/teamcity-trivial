## TeamCity Trivial Template

This repository is a minimal TeamCity Kotlin DSL project that auto-populates a single build with three steps:

1. echo "hello"
2. sleep 3
3. echo "world"

How to use with TeamCity Versioned Settings

- In TeamCity, create a project and enable Versioned Settings.
- Choose Kotlin format and point it to this repository.
- TeamCity will import the `.teamcity/` directory and create the build configuration automatically.

Notes

- This repo is pinned for **TeamCity 2024.03.x** (`jetbrains/teamcity-server:2024.03.3`), so `.teamcity/settings.kts` uses `version = "2024.03"` and the `v2024_03` Kotlin DSL API imports.
