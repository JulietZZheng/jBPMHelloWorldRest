This sample shows how to expose workflow as rest service and start a workflow hello from rest service http://localhost:port/hello-mgmt-rest/api/workflow/com.sample.hellomgmt.workflow.hello/yourname. The workflow hello contains a script task (with java).

## Environment

### Requirements:

__Java__

JDK 11. 

__Maven__

Use the latest version of Maven available that will work for your java environment. Note that when the source compiles, it will target Java 11 since it goes off the compiler settings in the projects' pom.xml files.

## Building

__Clone the Repository__

```
git clone https://github.com/JulietZZheng/jBPMHelloWorldRest.git
```

__Build

```
cd jBPMHelloWorldRest
mvn clean install
```

## Running

__Download/install latest wildfly like wildfly-21.0.2.Final


__Setup

*. Installing

	1. Install(unzip) latest wildfly wildfly-21.0.2.Final
	2. set JAVA_HOME JDK 1.11 or later
	3. Go to the folder WILDFLY_HOME/bin and execute the command to start WildFly in standalone mode:
		#> standalone.bat (Windows)
	4. Open a web browser and access the url http://localhost:9880 to see the WildFly welcome page.

*. Deploying the JDBC Driver

	(before start download and setup mysql driver, do as the url instructed - https://www.wildfly.org/news/2014/02/06/GlassFish-to-WildFly-migration/)
	1. connect to admin
		D:\wildfly-21.0.2.Final\bin>jboss-cli.bat --connect
	2. add jdbc-drive
		/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-class-name=com.mysql.jdbc.Driver)
		
*. Configuring

	0. set hibernate.dialect in standalone.conf.bat
		set "JAVA_OPTS=%JAVA_OPTS% -Dhibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect"
		set "JAVA_OPTS=%JAVA_OPTS% -DANTLR_USE_DIRECT_CLASS_LOADING=true"
	
	1. add datasources jdbc/__jbpm
		
		a. connect to admin
			D:\wildfly-21.0.2.Final\bin>jboss-cli.bat --connect
		b. add datasources jdbc/__jbpm
			/subsystem=datasources/data-source=<Your DS Name>:add(driver-name=mysql,user-name=<your db user name>,password=<your db pass>,connection-url=jdbc:mysql://localhost:3306/<your db name>,min-pool-size=5,max-pool-size=15,jndi-name=java:/jdbc/__jbpm,enabled=true,validate-on-match=true,valid-connection-checker-class-name=org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker,exception-sorter-class-name=org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter)
			

__Deploy

Copy ear file hello-mgmt-ear-1.0.0-SNAPSHOT.ear to wildfly deployment folder <D:\wildfly-21.0.2.Final\standalone\deployments> and start wildfly by run command standalone.bat if it's not started yet

__Verify Deployment__

Go to url like http://localhost:9880/hello-mgmt-rest/api/workflow/com.sample.hellomgmt.workflow.hello/yourname to see if you can launch the workflow com.sample.hellomgmt.workflow.hello;

