# --- Build stage ---
FROM eclipse-temurin:21-jdk AS build
WORKDIR /server

# Build
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src ./src

RUN chmod +x ./mvnw && ./mvnw clean package -DskipTests


# --- Run stage ---
FROM eclipse-temurin:21-jre AS run
WORKDIR /server

# Copy the earlier built jar file
COPY --from=build /server/target/*.jar ./blog-app.jar

# Port
EXPOSE 7075

ENTRYPOINT ["java", "-jar", "blog-app.jar"]
