FROM openjdk:11-jdk-slim
ADD application/target/application.jar worker-company.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/worker-company.jar"]
