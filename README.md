# Twiddler
A Twitter mockup made with React, Spring Boot, and MySQL

## Dependencies:

### Client:
- React, React-Dom, React-Scripts
  - for React
- Axios 
  - for HTTP requests
- Moment, React-Moment
  - for formatting dates and times
- JWT-Decode
  - for encoding/decoding JWT tokens
- React-Infinite-Scroller
  - for infinite scrolling
- React-SVG
  - for using vector images
- Bootstrap, Popper, jQuery
  - for CSS styling

### Server:
- (Parent) Spring Boot Starter Parent
  - parent Maven package to inherit from
- Spring Boot Web
  - for building a web application
- Spring Boot Data JPA
  - for integration with Java Persitence API
- Spring Boot Data REST
  - for building a RESTful web API
- Spring Boot Security
  - for securing a Spring application
- Spring Boot DevTools
  - for automatic restarts/live reloading during development
- Spring Boot Configuration Processor
  - for accessing configuration properties in the app
- Spring Boot Test
  - for unit testing/automated testing
- Spring Boot Security Test
  - for security unit testing/automated security testing
- Spring Cloud GCP Storage **(not implemented yet)**
  - for integration with Google Cloud Storage
- Spring Boot WebSocket **(not implemented yet)**
  - for integration with WebSockets (live messaging)
- JJWT (Java JSON Web Tokens)
  - for token-based authentication
- MySQL Connector Java
  - for connecting to a MySQL database from a Java application
  
## Resources:
- Full-Stack Spring Boot/React/Redux/MySQL tutorial course **<-- (highly recommended)**
  - https://www.udemy.com/course/full-stack-project-spring-boot-20-react-redux/
- Java Websockets Tutorial
  - https://www.baeldung.com/java-websockets
- Java/React/MySQL real-time data using the Pusher API
  - https://pusher.com/tutorials/realtime-mysql-java