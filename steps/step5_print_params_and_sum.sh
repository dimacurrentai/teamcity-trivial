#!/usr/bin/env sh

# This step is intended to be run under the ParametrizedMagic build type, which sets env vars A and B.
# In `ParametrizedMagic`, A and B are required and must be configured in TeamCity (Parameters: env.A and env.B).

A="${A:-0}"
B="${B:-0}"

echo "A=${A}, B=${B}"
echo "C=A+B=$((A + B))"

