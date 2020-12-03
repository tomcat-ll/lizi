#FROM java:8
FROM openjdk:8-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
#docker的端口，随机。或者默认在这个端口，可以是一个范围10000-10089
EXPOSE 10087
ENTRYPOINT ["java","-jar","/app.jar"]