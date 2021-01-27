FROM openjdk:8-jdk-alpine
ADD target/customers-0.0.1-SNAPSHOT.jar customers-0.0.1-SNAPSHOT.jar
EXPOSE 3333
ENTRYPOINT java -jar customers-0.0.1-SNAPSHOT.jar customers 
