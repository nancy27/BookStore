FROM openjdk
EXPOSE 8080
ADD build/libs/BookStore-0.0.1-SNAPSHOT.jar  BookStore.jar
ENTRYPOINT ["java","-jar","BookStore.jar"]