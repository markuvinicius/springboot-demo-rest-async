#!/bin/bash

java \
-Xms256m \
-Xmx256m \
-XX:+UseG1GC \
-XX:MaxGCPauseMillis=500 \
-XX:InitiatingHeapOccupancyPercent=90 \
-XX:+UseStringDeduplication \
-XX:+AggressiveOpts \
-XX:+UseCompressedOops \
-jar target/springboot-demo-rest-async-0.0.1-SNAPSHOT.jar 