# StocksNow
This project is a Java GUI interface in which users can sign up and track there current stock portfolio right from their desktop. The user can not only track the different stocks they own, but also the client can receive a summary of any stock.
## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.
### Prerequisites
What things you need to install the software
```
Java 10 or later
Windows or Mac OS
SQL
Maven
Libraries
```

1. First, make sure you have the Java 10 or later JDK downloaded
2. Then, download SQL onto local machine if not already done so.
3. Import the libraries from the project and check to see if the pom.xml has the code below.
  ```java
  <dependency>
    <groupId>com.yahoofinance-api</groupId>
    <artifactId>YahooFinanceAPI</artifactId>
    <version>3.14.0</version>
</dependency>

<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-jdk14</artifactId>
    <version>1.7.25</version>
</dependency>
  ```
## Running the tests
