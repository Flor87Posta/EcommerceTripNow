FROM gradle:7.6.1-jdk11-alpine

COPY . .

EXPOSE 8585

RUN gradle build

ENTRYPOINT ["java", "-jar", "build/libs/Ecommerce-TripNow-Challenge-MindHub-0.0.1-SNAPSHOT.jar"]