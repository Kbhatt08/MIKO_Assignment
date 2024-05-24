# Read Me First
The following was discovered as part of building this project:

* No Docker Compose services found. As of now, the application won't start! Please add at least one service to the `compose.yaml` file.
* The original package name 'ai.miko.assembly-lang' is invalid and this project uses 'ai.miko.assemblylang' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.5/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#web)
* [Docker Compose Support](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#features.docker-compose)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#using.devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Docker Compose support
This project contains a Docker Compose file named `compose.yaml`.

However, no services were found. As of now, the application won't start!

Please make sure to add at least one service in the `compose.yaml` file.

# Database Setup

    podman run --rm -d --name mysql   -e MYSQL_ROOT_PASSWORD=foobar  -p 3306:3306 -e MYSQL_USER=springuser   -e MYSQL_PASSWORD=ThePassword   -e MYSQL_DATABASE=assemblyDB   docker.io/library/mysql

    docker run -d --name mysql   -e MYSQL_ROOT_PASSWORD=foobar  -p 3306:3306 -e MYSQL_USER=springuser   -e MYSQL_PASSWORD=ThePassword   -e MYSQL_DATABASE=assemblyDB   docker.io/library/mysql

# Containerization

## Build Jar
    mvn clean package

## Build Image
    docker build -t assembly-lang:v1 -f Dockerfile .
    podman build -t assembly-lang:v1 -f Dockerfile .

# Run Container
    docker run --name assembly-lang -e 'MYSQL_HOST=192.168.122.1' -p 8080:8080 localhost/assembly-lang:v1
    podman run --name assembly-lang -e 'MYSQL_HOST=192.168.122.1' -p 8080:8080 localhost/assembly-lang:v1


## Curl to execute process:

    curl --location 'http://localhost:8080/assembly/process' --header 'Content-Type: application/json' --data 'MV REG1,#2000
    MV REG2,#4000
    ADD REG1,REG2
    ADD REG1,600
    SHOW REG1'


## Example Response

    4600
