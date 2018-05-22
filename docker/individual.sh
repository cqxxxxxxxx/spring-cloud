#!/usr/bin/env bash

tempDir="individual"
cd $PWD/../$tempDir
docker run -v ~/.m2:/root/.m2 -v "$PWD":/tmp/build --rm -w /tmp/build maven:3.3.3-jdk-8 mvn clean package  -s docker/settings.xml -Dmaven.test.skip=true
docker build --build-arg APP_ENV="dev" -t cqx534/$tempDir:0.0.1 $PWD
docker run -d -p 8080:9000 -v ~/logs:/root/logs --name $tempDir -e TZ=Asia/Shanghai cqx534/$tempDir:0.0.1