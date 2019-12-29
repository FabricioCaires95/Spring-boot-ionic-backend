#FROM java:8-jdk-alpine
#
#COPY ./target/control-0.0.1-SNAPSHOT.jar /usr/app/
#
#WORKDIR /usr/app
#
#RUN sh -c 'touch control-0.0.1-SNAPSHOT.jar'
#
#ENTRYPOINT ["java","-jar","control-0.0.1-SNAPSHOT.jar"]

# Use the official maven/Java 8 image to create a build artifact.
# https://hub.docker.com/_/maven
FROM maven:3.5-jdk-8-alpine as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Use AdoptOpenJDK for base image.
# It's important to use OpenJDK 8u191 or above that has container support enabled.
# https://hub.docker.com/r/adoptopenjdk/openjdk8
# https://docs.docker.com/develop/develop-images/multistage-build/#use-multi-stage-builds
FROM adoptopenjdk/openjdk8:jdk8u202-b08-alpine-slim

# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/target/control-*.jar /control.jar
COPY src/main/resources/google/nilone-aic-c57226f16cff.json /nilone-aic-c57226f16cff.json

#setting environment variables


# Run the web service on container startup.
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=${PORT}","-jar","/control.jar"]
