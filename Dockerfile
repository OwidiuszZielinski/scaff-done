FROM maven:3.8-openjdk-17-slim AS build
COPY . /home/app
WORKDIR /home/app
RUN mvn -f /home/app/pom.xml clean package -Pproduction -DskipTests -X


FROM openjdk:17-alpine
COPY --from=build /home/app/target/scaff-done-0.0.2-SNAPSHOT.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]
