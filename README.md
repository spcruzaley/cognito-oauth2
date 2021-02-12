# Getting Started

## Login OAuth2 with Cognito

## Previous steps

Before to start with this integration, **you need to have an AWS account**, you create it ***[here](https://aws.amazon.com/)***. Basically, we need to create a **User Pool** and a **Client Application**.

### Creating an User Pool

![Create User Pool - Step 1](readme-resources/CreateUserPool-1.png)

![Create User Pool - Step 2](readme-resources/CreateUserPool-2.png)

![Create User Pool - Step 3](readme-resources/CreateUserPool-3.png)

![Create User Pool - Step 4](readme-resources/CreateUserPool-4.png)

### Creating an Application Client

![Create App Client - Step 1](readme-resources/CreateAppClient-1.png)

![Create App Client - Step 2](readme-resources/CreateAppClient-2.png)

![Create App Client - Step 3](readme-resources/CreateAppClient-3.png)

![Create App Client - Step 4](readme-resources/CreateAppClient-4.png)

![Create App Client - Step 5](readme-resources/CreateAppClient-5.png)

![Create App Client - Step 6](readme-resources/CreateAppClient-6.png)


*OAuth2 flow*
![Cognito OAuth2 flow](readme-resources/CognitoOAuth2Flow.jpg) 

## Execution

### Requirements
* JDK 11
* Maven 3.*

Execution steps
```bash
$ mvn clean spring-boot:run
```

Open the browser and go to `http://localhost:8080` (*its configured in port 8080 by default*)

*Index page*
![Index page](readme-resources/CognitoLogin-1.png)

*Login form*
![Login form](readme-resources/CognitoLogin-2.png)

*Index page logged in*
![Logged in](readme-resources/CognitoLogin-3.png)

*Authentication information*
![Auth Information](readme-resources/CognitoLogin-4.png)


### Reference Documentation
For further reference, please consider the following sections:

* [Understanding Amazon Cognito](https://aws.amazon.com/blogs/mobile/understanding-amazon-cognito-user-pool-oauth-2-0-grants/)

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.2/maven-plugin/reference/html/#build-image)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-security)
* [OAuth2 Client](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-security-oauth2-client)
* [OAuth2 Resource Server](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-security-oauth2-server)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

___
*[Source information](https://www.harshajayamanna.com/)* 