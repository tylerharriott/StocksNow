# StocksNow
This project is a Java GUI interface in which users can sign up and track there current stock portfolio right from their desktop. The user can not only track the different stocks they own, but also the client can receive a summary of any stock.
## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.
### Prerequisites
What things you need to install the software
```
- Java 10 or later
- Windows or Mac OS
- SQL
- Maven
- Libraries
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
To run the tests
- Go to test --> java -->(*Choose a package*)
Once there, you can run each package and makesure nothing fails.

Each test tests the functionality of the java class. If one tests fails then that means there is an error in one ormore of the methods.

## Development
If everything runs smoothly and successful, the user can click the Run button to try it out.
If all goes well again, the user may wrap the package up in a jar file.

Since the softwareusing Maven, there is little work to be done to get a jar file. 

### Steps
1. Go to the "Terminal" in your IDE.
2. Enter these commands:
```java
mvn clean
mvn compile
mvn package
```
3. Check to see if there are no errors
4. Once confirmed of no errors,check the "target" directory in your project.
5. Locate Jar file

## Built With

* [Java](https://www.java.com/en/) - Programming Lang.
* [SQL](https://www.mysql.com/) - Programming Lang. Manage Data
* [CSS](https://learn.shayhowe.com/html-css/) - Programming Lang. Design/Presentation
* [Maven](https://maven.apache.org/) - Dependency Management

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* [sstrickx](https://github.com/sstrickx/yahoofinance-api) 
