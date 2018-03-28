FROM java:openjdk-8-jdk

MAINTAINER cqxxxxxxxx@gmail.com

EXPOSE 80
ARG MODULE
ARG PROFILE=default
ENV MODULE=${MODULE}
ENV PROFILE=${PROFILE}
RUN mkdir -p /app
WORKDIR /app
COPY ${MODULE}/target/*.jar app.jar
ENV JAVA_OPTS='-Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${PROFILE}'
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
