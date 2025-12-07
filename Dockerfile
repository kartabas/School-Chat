# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

#CMD ["mvn", "clean", "package", "-DskipTests"]

# Copy the jar file into the container
COPY target/*.jar app.jar

#COPY src/main/resources/ resources/
# Expose the port the app runs on (adjust if different)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java","-jar","app.jar"]
