# Twiddler
A Twitter mockup made with React, Spring Boot, and MySQL

## Getting Started:
1. Clone the project to your machine and open the project folder in your IDE

2. Ensure that you have a local MySQL server running on port 3306 with a schema called "twiddler"

3. Install dependencies:
    - Client:
        - Run `npm install` in the `client/` folder
    - Server:
        - (IDE): Enable Maven auto-import
        - (CLI): run `mvn clean package` in the project folder

4. Environment Variables:
    - Client:
        - `REACT_APP_API_URL` is set for the production environment in `/client/.env.production` and **_should be overridden_** for local development & other environments
            - Method 1: create a file called `.env.local` and add the line `REACT_APP_API_URL=http://localhost:8080/api`
            - Method 2: run `EXPORT REACT_APP_API_URL=http://localhost:8080/api` in your terminal before running `npm start`
    - Server:
        - These default environment variables are defined in `application.yml`:
            - `spring.profiles.active`: `local`
            - `database.url`: `jdbc:mysql://localhost:3306/twiddler`
            - `database.username`: `root`
            - `database.password`:
            - `JWT_SECRET_KEY`: `TOP_SECRET`
        - These values can be overriden if needed either by passing them in as arguments when running in the command line, or setting them in the run configuration before running in an IDE

5. Run:
    - Client:
        - (IDE): Run as Node.js application with `npm start` command
        - (CLI): Run `npm start` from the `client/` folder
    - Server:
        - (IDE): Click the Run button in the IDE
        - (CLI): run `mvn spring-boot:run` from the project folder
            - with custom environment vaeriables: `mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=local -Ddatabase.url=jdbc:mysql://localhost:3306/twiddler -Ddatabase.username=YOUR_MYSQL_USERNAME -Ddatabase.password=YOUR_MYSQL_PASSWORD -DJWT_SECRET_KEY=ANY_STRING_YOU_WANT"`

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
- Full-Stack Spring Boot/React/Redux/MySQL tutorial course
  - https://www.udemy.com/course/full-stack-project-spring-boot-20-react-redux/
- Java Websockets Tutorial
  - https://www.baeldung.com/java-websockets
- Java/React/MySQL real-time data using the Pusher API
  - https://pusher.com/tutorials/realtime-mysql-java