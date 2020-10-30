# springboot-demo-rest-async
A spring boot rest api that consumes a bunch of services asynchronously. This project was built as a POC for CompletableFuture async calls and JVM Tunning

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install:

* Java OpenJDK 1.11: https://openjdk.java.net/install/
* Maven 3.3.6: https://maven.apache.org/download.cgi
* NodeJS V14.4.4 https://nodejs.org/en/download/
* npm 6.14.4 https://www.npmjs.com/get-npm

## Installing

A step by step series of examples that tell you how to get a development env running

### Build

```
mvn clean package
```

### Set-UP the Mock Rest APIs

To start-up the mock Rest APIs, just follow

```
cd mock-rest-api
npm install -s
npm instlal -g json-server
chmod a+x multiple_stack.sh
./multiple_stack.sh
```

This commands will install the project dependencies and start 11 nodeJs processes exposing a rest endpoint at http://localhost:{port}/employees
The {port} list will start from 3000 to 3010

### Running the application

To run the application locally, do the following:

```
cd springboot-demo-rest-async
chmod a+x start_java_application.sh
./start_java_application.sh
```

After running, you can start making GET requests to the application on the endpoint http://localhost:8080/async


## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [SpringBoot](https://spring.io/projects/spring-boot) - The Java Web Framework User
* [Maven](https://maven.apache.org/) - Dependency Management
* [JUnit 5](https://junit.org/junit5) - The Java Unit Test Framework

## Contributing

Feel free to submit a pul request

## Versioning

We use [SemVer](http://semver.org/) for versioning. 

## Authors

* **Marku Vinícius** - *Initial work* - [Marku Vinicius](https://gitlab.com/markuvinicius)


## License

This project is licensed under the MIT License 

## Acknowledgments
