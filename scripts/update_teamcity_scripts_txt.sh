#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT_DIR"

mkdir -p steps .teamcity

# Collect files directly under steps/ (not recursive), sorted alphabetically.
shopt -s nullglob
files=(steps/*.sh)
shopt -u nullglob

if ((${#files[@]} == 0)); then
  : > .teamcity/scripts.txt
  exit 0
fi

printf '%s\n' "${files[@]}" | LC_ALL=C sort > .teamcity/scripts.txt

