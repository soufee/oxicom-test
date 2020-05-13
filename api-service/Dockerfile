FROM woahbase/alpine-openjdk8:latest
EXPOSE 9080

WORKDIR /api/
COPY target/api-service-1.jar api-service-1.jar

ENTRYPOINT ["java","-jar","api-service-1.jar"]

#ENTRYPOINT exec java $JAVA_OPTS -jar api-service-1.jar -Dspring.cloud.consul.discovery.hostname=${spring.cloud.consul.host}