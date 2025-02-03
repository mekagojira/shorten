# Use the official OpenJDK 21 image as the base image
FROM gradle:jdk21 AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and build.gradle files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copy the source code
COPY src src

# Grant execution permissions to the Gradle wrapper
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew build --no-daemon -x test

# Create a new image for running the application
FROM gradle:jdk21

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build image
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]