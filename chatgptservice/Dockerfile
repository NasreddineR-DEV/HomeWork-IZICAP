FROM openjdk:17-jdk-alpine
EXPOSE 8090
# Copy data.csv file
ADD target/chatgptservice-docker.jar chatgptservice-docker.jar
ENTRYPOINT ["java","-jar","/chatgptservice-docker.jar"]