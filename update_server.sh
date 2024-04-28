#!bin/sh

echo [-------------------- update server packages -----------------------]
sudo apt update
sudo apt dist-upgrade

echo [---------------- get changes from git and build jar ---------------]
git pull
./gradlew clean build

echo [-------------------------- build image ----------------------------]
docker build -t backend .

echo [----------------------- start new server --------------------------]
docker compose up -d --force-recreate

