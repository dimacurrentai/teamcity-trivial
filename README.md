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
