#!/usr/bin/env bash

read -p '输入module   ' module
read -p '输入profile   ' profile
read -p '端口映射(8080:9000 )  ' port_map
tempDir="$PWD"
cd "/root/code/spring-cloud/$module"
echo "$PWD"
git pull
docker run -v ~/.m2:/root/.m2 -v "$tempDir":/tmp/build --rm -w /tmp/build maven:3.3.3-jdk-8 mvn clean package  -s docker/settings.xml -Dmaven.test.skip=true
docker build --build-arg MODULE="$module" --build-arg PROFILE="$profile" -t $module .
docker run -d -p $port_map -v ~/logs:/logs --name $module -e TZ=Asia/Shanghai $module