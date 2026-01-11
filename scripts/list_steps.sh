#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
(cd "$ROOT_DIR"; ls steps/*.sh | sort) > "${ROOT_DIR}/.steps_list.txt.tmp"
