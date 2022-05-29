FROM openjdk:11-jdk-slim
VOLUME /tmp
EXPOSE 8090
ADD presentation/target/presentation.jar worker-company.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/worker-company.jar"]
