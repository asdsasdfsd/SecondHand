FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -Dmaven.test.skip=true


FROM openjdk:17-jdk-alpine

COPY --from=build /app/target/SecondHand-0.1.jar /app/SecondHand.jar

EXPOSE 7079

ENTRYPOINT ["java", "-jar", "/app/SecondHand.jar"]
