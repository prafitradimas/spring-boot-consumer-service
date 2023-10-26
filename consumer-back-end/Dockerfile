FROM maven:3.9.5-sapmachine-21

WORKDIR /consumer-back-end
COPY . .
RUN mvn clean install -Dmaven.test.skip

CMD mvn spring-boot:run
