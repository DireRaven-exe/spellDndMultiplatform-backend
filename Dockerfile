# using multistage docker build
# ref: https://docs.docker.com/develop/develop-images/multistage-build/
# temp container to build using gradle
FROM gradle:8.7-jdk21 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle.kts settings.gradle.kts $APP_HOME
  
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
COPY build/libs/fat.jar $APP_HOME
USER root
RUN chown -R gradle /home/gradle/src

EXPOSE 8080
ENTRYPOINT exec java -jar fat.jar