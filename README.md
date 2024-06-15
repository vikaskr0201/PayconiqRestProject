# Payconiq Rest - API Automation

## Author : Vikas Kumar 
## Project Overview 
## Link to Detail Test Documentation
[Link to Payconiq API Automation Doc](https://docs.google.com/document/d/1bcD6wp04Oa1qDpg8n-DygQKrlZZNbq8zqM3m0H113UM/edit)


The project is a maven based the major dependencies are updated in pom.xml as:

Rest Assured

JUnit

Maven Surefire and Failsafe Plugin

Serenity (used for test report only)

## To Run

Go to the project root directory.

Precondition : We expect Java8 (Oracle JDK) and Maven is installed on the system.

[Link to Oracle JDK](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

[Link to Maven](https://maven.apache.org/install.html)

```
Maven home: /usr/local/Cellar/maven/3.6.3/libexec
Java version: 1.8.0_301, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk1.8.0_301.jdk/Contents/Home/jre

```
If not please SET JAVA_HOME. For Mac users as :

```
 export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_301.jdk/Contents/Home/jre
```

Run using maven command line as :

1.Run only smoke test tagging done in the test class PayconiqTest
```
mvn clean verify -Dtags="type:smoke" serenity:aggregate 
```
2.Run complete test means regression using tags or if no tags are mentioned it will run regression only 
```
 mvn clean verify -Dtags="type:regression" serenity:aggregate
```
```
 mvn clean verify serenity:aggregate 
```

## Results

The result of the test run can be viewed under target directory in the project root folder only.

Open index.html in chrome browser to view the report

```
target/site/serenity/index.html
```

