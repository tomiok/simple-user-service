# Slim jdk image
FROM frolvlad/alpine-oraclejdk8:slim

VOLUME /tmp

# Copy and rename the jar
COPY target/*.jar app.jar

# Expose port 8100
EXPOSE 8100

# Touch the archive for timestamp
RUN sh -c 'touch /app.jar'

# Run java -jar command in the renamed jar
ENTRYPOINT ["java","-jar","/app.jar"]
