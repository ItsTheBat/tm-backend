FROM gradle:7.6.0-jdk17-alpine as build
WORKDIR /workspace/app
 
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
RUN ./gradlew dependencies
 
COPY src src
RUN ./gradlew build unpack -x test
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)
 
FROM gradle:7.6.0-jdk17-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","TaskManagerApplication"]