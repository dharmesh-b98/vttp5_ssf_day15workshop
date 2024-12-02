#the base image with JDK used to build and run your java application
FROM  maven:3.9.9-eclipse-temurin-23

#labelling the dockerfile
LABEL MAINTAINER="dharmesh"
LABEL description="This is VTTP5 SSF day 15 Workshop demo"
LABEL name="vttp5-ssf-day15workshop"

ARG APP_DIR=/APP

#directory where your source code will reside
#directory where you copy your project to (in the step)
WORKDIR ${APP_DIR}

#copy the required files and or directories into the image
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY .mvn .mvn

#sdfds
RUN mvn package -Dmaven.test.skip=true

ENV SERVER_PORT=3000

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-jar", "target/vttp5_ssf_day17l-0.0.1-SNAPSHOT.jar","--server.port=${SERVER_PORT}"]
#can also use mvn spring-boot:run as entrypoint