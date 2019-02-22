FROM openjdk:8-jdk-alpine

MAINTAINER Milfist <milfist@gmail.com>

COPY ./ /tmp/src/

EXPOSE 8080

COPY ./target/mongodb-fluentd-1.0.0-SNAPSHOT.jar ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "mongodb-fluentd-1.0.0-SNAPSHOT.jar"]
