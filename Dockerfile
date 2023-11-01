FROM openjdk:18
EXPOSE 8080
ADD target/1source-spring-boot-oauth2-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]