FROM openjdk:17-alpine

# expose the tomcat port to let communication with another containers
EXPOSE 8080

WORKDIR /usr/app

ARG JAR_NAME
COPY ./target/$JAR_NAME ./product-service.jar

CMD ["java", "-jar", "./product-service.jar"]