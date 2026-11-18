FROM eclipse-temurin:24-jdk-noble

RUN apt-get update \
    && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/*
VOLUME /tmp
COPY target/*.jar  app.jar
ENTRYPOINT ["java","-jar","app.jar"]