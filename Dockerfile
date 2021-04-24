FROM maven:3.6.3-openjdk-14 as builder
COPY ./src ./src
COPY ./pom.xml ./pom.xml
RUN mvn clean package

FROM openjdk:14-jdk-alpine
COPY --from=builder /target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]