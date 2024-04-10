FROM openjdk:17

# Expose port
EXPOSE 8080

# Copy the JAR file
COPY target/sb_docker_app.jar /usr/app/

# Copy the JDBC driver for SQL Server
# COPY target/mssql-jdbc-12.6.1.jre8.jar /usr/app/

WORKDIR /usr/app/

ENTRYPOINT ["java", "-jar", "sb_docker_app.jar"]