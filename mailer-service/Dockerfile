FROM woahbase/alpine-openjdk8:latest
EXPOSE 9084
WORKDIR /mailer-service
COPY target/mailer-service-1.jar mailer-service-1.jar

#ENTRYPOINT exec java $JAVA_OPTS -jar mailer-service-1.jar
ENTRYPOINT ["java","-jar","mailer-service-1.jar"]