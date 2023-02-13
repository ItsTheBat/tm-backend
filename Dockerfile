FROM gradle:7.6.0-jdk17-alpine as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle build

FROM gradle:7.6.0-jdk17-alpine
COPY --from=gradleimage /home/gradle/source/build/libs/taskmanager-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
ENTRYPOINT ["java", "-jar", "taskmanager-0.0.1-SNAPSHOT.jar"]