# Java Tomcat Application with DB (PostgreSQL)

A simple web application to work with the database of players.

## Deploy and run

1. Install Apache Tomcat (program was tested on Apache Tomcat 9.0.33 and Apache Tomcat 10.0.0-M1)
2. Restore database from scheme.sql (Recommended use PgAdmin).
3. In pom.xml file you can change configuration of Tomcat User and change path and port.
4. Run `mvn tomcat7:run`.

