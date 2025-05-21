#build
FROM maven:3.9.9-sapmachine-22 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package

#final
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/fqw-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]