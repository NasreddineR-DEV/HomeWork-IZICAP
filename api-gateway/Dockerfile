FROM openjdk:17-jdk-alpine
EXPOSE 8080
ADD target/api-gateway-docker.jar api-gateway-docker.jar
ENTRYPOINT ["java","-jar","/api-gateway-docker.jar"]