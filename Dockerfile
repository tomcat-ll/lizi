#FROM java:8
FROM openjdk:8-jdk-alpine
FROM apache/skywalking-java-agent:8.5.0-jdk8
ARG JAR_FILE
COPY ${JAR_FILE} /app.jar
#docker的端口，随机。或者默认在这个端口，可以是一个范围10000-10089
EXPOSE 10087
ENTRYPOINT ["java","-jar","-Dapp.id=svcbService","-javaagent:/opt/skywalking/agent/skywalking-agent.jar","-Dskywalking.agent.service_name=svcbService","-Dskywalking.collector.backend_service=skywalking-oap-server:11800","-jar","-Djava.security.egd=file:/dev/./urandom",
                   "/app.jar"]