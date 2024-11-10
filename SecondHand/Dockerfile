FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/SecondHand-0.1.jar /app/SecondHand.jar

ENV DB_USERNAME=myusername
ENV DB_PASSWORD=mypassword
ENV MAIL_URL=http://localhost:8081
ENV RESOURCE_URL=http://localhost:8080

EXPOSE 7079

ENTRYPOINT ["java", "-jar", "/app/SecondHand.jar"]
