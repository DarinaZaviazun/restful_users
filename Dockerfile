FROM maven:3.6.3-jdk-11-slim

WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn clean install
