FROM java:openjdk-openjdk-8-jdk

MAINTAINER cqxxxxxxxx@gmail.com

ARG MODULE
ARG PROFILE=default
ENV MODULE = ${MODULE}
ENV PROFILE = ${PROFILE}
ADD ${MODULE}/target/*.jar ${MODULE}.jar
ENV JAVA_OPTS = '-Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${PROFILE} '
ENTRYPOINT [ "sh", "-c", "java ${JAVA_OPTS} -jar /${MODULE}.jar" ]

