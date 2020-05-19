FROM woahbase/alpine-openjdk8:latest
WORKDIR /app
EXPOSE 9081
COPY target/id-generator-1.jar id-generator-1.jar
ENTRYPOINT ["java","-jar","id-generator-1.jar"]
