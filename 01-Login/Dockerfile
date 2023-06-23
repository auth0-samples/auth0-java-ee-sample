# Build stage
FROM maven:3.6.1-jdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app

WORKDIR /home/app
RUN mvn clean package

# Deploy stage
FROM jboss/wildfly
COPY --from=build /home/app/target/auth0-javaee-security-0.0.1-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments

# wildfly container configured with port 8080, see exec.sh/exec.ps1 to map localhost:3000 to 8080
EXPOSE 8080
