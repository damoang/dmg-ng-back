FROM eclipse-temurin:21-jdk AS builder

COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean build -x test

FROM eclipse-temurin:21-jre

COPY --from=builder build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
