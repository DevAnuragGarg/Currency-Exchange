# initial dependency
FROM openjdk:17

# maintainer of the project
LABEL maintainer="Anurag Garg <developer.anuraggarg@gmail.com>"

# exposing the port for the service
EXPOSE 8291

#add the jar file, need to replace add with copy
ADD target/currency-exchange-0.0.10-SNAPSHOT.jar currency-exchange-0.0.10-SNAPSHOT.jar

# run this jar
ENTRYPOINT ["java", "-jar", "currency-exchange-0.0.10-SNAPSHOT.jar"]