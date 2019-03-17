FROM java:8
EXPOSE 8080
ADD /target/restful-web-services-0.0.1-SNAPSHOT.jar restful-web-services-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "restful-web-services-0.0.1-SNAPSHOT.jar"]