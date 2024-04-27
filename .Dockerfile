FROM gradle:8.7-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/testemployee993/spellDndMultiplatform-backend
RUN gradle build --no-daemon
CMD ./home/testemployee993/spellDndMultiplatform-backend/gradlew build
FROM openjdk:17-jdk-slim

RUN mkdir /app

COPY --from=build /home/testemployee993/spellDndMultiplatform-backend/build/libs/ /app/

ENTRYPOINT ["java","-jar","/app/fat.jar"]
