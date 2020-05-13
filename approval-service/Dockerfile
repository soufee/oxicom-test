FROM woahbase/alpine-openjdk8:latest
EXPOSE 9082

WORKDIR /approve/
COPY target/approval-service-1.jar approval-service-1.jar

ENTRYPOINT ["java","-jar","approval-service-1.jar"]

#ENTRYPOINT exec java $JAVA_OPTS -jar approval-service-1.jar -Dspring.cloud.consul.discovery.hostname=${spring.cloud.consul.host}