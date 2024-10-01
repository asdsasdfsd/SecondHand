FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/SecondHand-0.1.jar /app/SecondHand.jar

EXPOSE 7079

ENTRYPOINT ["java", "-jar", "/app/SecondHand.jar"]
