FROM woahbase/alpine-openjdk8:latest
#FROM maven:3.5-jdk-8
WORKDIR /app
EXPOSE 9081
COPY target/id-generator-1.jar id-generator-1.jar
ENTRYPOINT ["java","-jar","id-generator-1.jar"]

#CMD ["java","-jar","id-generator-1.jar"]
#ENTRYPOINT ["sh", "-c", "java -jar id-generator-1.jar"]
#ENTRYPOINT ["java","-jar","id-generator-1.jar"]
##ENTRYPOINT exec java $JAVA_OPTS -jar id-generator-1.jar -Dspring.cloud.consul.discovery.hostname=${spring.cloud.consul.host}

#FROM openjdk:8-jdk-alpine
#EXPOSE 9081
#VOLUME /tmp
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} id-generator-1.jar
#ENTRYPOINT ["sh", "-c", "java -jar /id-generator-1.jar"]