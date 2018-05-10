FROM java:openjdk-8-jdk

MAINTAINER cqxxxxxxxx@gmail.com

ARG PROFILE=default
ENV PROFILE=${PROFILE}
COPY target/*.jar /root/app.jar
ENV JAVA_OPTS='-Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${PROFILE}'
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /root/app.jar