FROM openjdk:21-jdk-oracle

# Set the working directory in the container
WORKDIR /app
# Copy the application JAR file into the container
COPY build/libs/products-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8085

# Specify the command to run on container startup
CMD ["java", "-jar", "app.jar"]
