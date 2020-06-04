FROM maven:3.6.3-jdk-11-slim AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package

FROM openjdk:11-jre-alpine

WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/*.jar /app/app.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]