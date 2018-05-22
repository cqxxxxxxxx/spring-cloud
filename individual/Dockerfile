FROM maven:3.3.3-jdk-8 AS build

LABEL name="12345"
VOLUME /root/.m2
WORKDIR /usr/src/app
COPY pom.xml ./
RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.0.2:go-offline
COPY . .
RUN mvn -B -e -o -T 1C verify
## RUN mvn -f pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:8
COPY --from=build /usr/src/app/target/*.jar /root/app.jar
ENTRYPOINT ["java","-jar","/root/app.jar"]
