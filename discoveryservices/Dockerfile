FROM openjdk:17-jdk-alpine
EXPOSE 8761
ADD target/discoveryservices-docker.jar discoveryservices-docker.jar
ENTRYPOINT ["java","-jar","/discoveryservices-docker.jar"]