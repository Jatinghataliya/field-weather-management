# Field Weather Management

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Field weather management system track the field data with geometry and along with provide API for the weather data of specific fields.

Following are the way to execute field weather management service
  
To  run api using maven
```sh
./mvnw spring-boot:run
./mvnw package && java -jar target/field-weather-management-0.0.1.jar
```
### Containerize It
    
Docker has a simple "Dockerfile" file format that it uses to specify the "layers" of an image. So let’s go ahead and create a Dockerfile in our Spring Boot project:
You can run it (if you are using Maven) with
```sh
$ docker build -t springio/field-weather-management .
$ docker run -p 8080:8080 springio/field-weather-management
```
### Build a Docker Image with Maven
    
To get started quickly, you can run the Spring Boot image generator without even changing your pom.xml (and remember the Dockerfile if it is still there is ignored):

```sh
$ ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=springio/field-weather-management
```
To push to a Docker registry you will need to have permission to push, which you won’t have by default. Change the image prefix to your own Dockerhub ID, and docker login to make sure you are authenticated before you run Docker.

### After the Push
A "docker push" in the example will fail for you (unless you are part of the "springio" organization at Dockerhub), but if you change the configuration to match your own docker ID then it should succeed, and you will have a new tagged, deployed image.

You do NOT have to register with docker or publish anything to run a docker image that was built locally. If you built with Docker (from the command line or from Sprng Boot), you still have a locally tagged image, and you can run it like this:

```sh
$ docker run -p 8080:8080 -t springio/field-weather-management
```

to see container list use following docker command.
```sh
$ docker ps
81c723d22865        springio/field-weather-management:latest   "java -Djava.secur..."  34 seconds ago      Up 33 seconds       0.0.0.0:8080->8080/tcp   goofy_brown

```

and to shut it down again you can docker stop with the container ID from the listing above (yours will be different):
```sh
$ docker stop goofy_brown
81c723d22865
```

If you like you can also delete the container (it is persisted in your filesystem under /var/lib/docker somewhere) when you are finished with it:

```sh
$ docker rm goofy_brown
```