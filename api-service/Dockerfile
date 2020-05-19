FROM woahbase/alpine-openjdk8:latest
EXPOSE 9080

WORKDIR /api/
COPY target/api-service-1.jar api-service-1.jar

ENTRYPOINT ["java","-jar","api-service-1.jar"]

