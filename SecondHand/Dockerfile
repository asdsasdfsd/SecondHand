FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/SecondHand-0.1.jar /app/SecondHand.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/app/SecondHand.jar"]