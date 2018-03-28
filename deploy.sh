#!/usr/bin/env bash

read -p '输入module(eureka)   ' module
read -p '输入profile(default)   ' profile
read -p 'host端口映射到80  ' port
read -p '输入域名' domainName
tempDir="$PWD"
if [ -z $module ]; then
    module='eureka'
fi
if [ -z $profile ]; then
    profile='default'
fi
if [ -z $port ]; then
    port='80'
fi
if [ -z $domainName ]; then
    domainName='cqxxxxxxxx.com'
fi
cd "$PWD/$module"
echo "$PWD"
git pull
docker run -v ~/.m2:/root/.m2 -v "$PWD":/tmp/build --rm -w /tmp/build maven:3.3.3-jdk-8 mvn clean package -Dmaven.test.skip=true
cd "$tempDir"
echo "$module,$profile,$prot"
docker build --build-arg MODULE="$module" --build-arg PROFILE="$profile" -t $module .
docker run -d -p $port:80 -v ~/logs:/logs --name $module -e TZ=Asia/Shanghai -e VIRTUAL_HOST=$domainName $module
