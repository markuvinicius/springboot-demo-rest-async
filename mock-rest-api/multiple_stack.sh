#!/bin/bash

for f in {3000..3010}; do (json-server --port $f --delay 200 employees.js 2>&1 > logs/$f.log &); done