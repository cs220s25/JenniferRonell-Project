FROM amazonlinux

WORKDIR /app

RUN yum install -y maven-amazon-corretto21

COPY pom.xml .
COPY src src

RUN mvn clean package

CMD ["java", "-jar", "target/dbot-1.0-SNAPSHOT.jar"]
