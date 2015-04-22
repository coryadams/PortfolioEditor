PortfolioEditor Application
--------------------------------------------------------

This is a Spring Boot application that uses JPA and Spring Data + Repository and MVC via Thymeleaf. UI Components
are provided by the Bootstrap project with Javascript used to provide some UI functionality.

The JPA SOR uses mySQL.

The application provides a UI to manage several types of assets including; Equities, Options, Bonds and Futures.
Additionally Portfolios can be created which allow adding and removing of the aforementioned assets.  A Task
will run on a daily basis after market close which will snapshot each Portfolios asset state into a table called
daily_asset_allocation.

Daily asset allocations can be viewed for a Portfolio using a date range.

--------------------------------------------------------

Setup:

Prerequisites:
 1) Java SDK 1.7 or greater
 2) Maven 3.2.4 or greater
 3) MySQL 5.5.x
 4) Tomcat 7.0.x

The application currently builds as a WAR via 'mvn package' but could be modified easily to use
Spring Boot's capability of providing a single executable jar with embedded Tomcat.

DB Setup:

 1) Run the db-setup.sql file to create the DB, credentials and tables.
  mysql> source '/<root_path>/PortfolioEditor/src/test/resources/db-setup.sql
 2) Run the test-data-setup.sql file to create populate the db.
  mysql> source '/<root_path>/PortfolioEditor/src/test/resources/test-data-setup-setup.sql

Tomcat Setup:
 1) install tomcat
 2) deploy the target/<artifact>.war to $TOMCAT_HOME/webapps

Running the application:

 Access the application at http://<host>:8080/PortfolioEditor


