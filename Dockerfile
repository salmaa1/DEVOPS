#FROM openjdk:8-jdk-alpine
FROM openjdk:17-jdk-alpine
EXPOSE 8082
ADD target/tp-foyer-5.0.0.war tp-foyer-5.0.0.war
ENTRYPOINT ["java","-jar","/tp-foyer-5.0.0.war"]