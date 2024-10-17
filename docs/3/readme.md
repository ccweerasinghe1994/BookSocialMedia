# 📗application.yml files

- [📗application.yml files](#applicationyml-files)
  - [**Table of Contents**](#table-of-contents)
  - [**1. Overview of Spring Boot Configuration**](#1-overview-of-spring-boot-configuration)
  - [**2. Understanding `application.yml`**](#2-understanding-applicationyml)
    - [**2.1. Spring Profiles**](#21-spring-profiles)
    - [**2.2. Servlet Multipart Configuration**](#22-servlet-multipart-configuration)
    - [**2.3. SpringDoc OpenAPI Configuration**](#23-springdoc-openapi-configuration)
    - [**2.4. Server Configuration**](#24-server-configuration)
  - [**3. Understanding `application-dev.yml`**](#3-understanding-application-devyml)
    - [**3.1. DataSource Configuration**](#31-datasource-configuration)
    - [**3.2. JPA (Hibernate) Configuration**](#32-jpa-hibernate-configuration)
    - [**3.3. Mail Configuration**](#33-mail-configuration)
  - [**4. Integration with Docker Compose**](#4-integration-with-docker-compose)
    - [**4.1. PostgreSQL Integration**](#41-postgresql-integration)
    - [**4.2. MailDev Integration**](#42-maildev-integration)
  - [**5. Practical Examples and Usage**](#5-practical-examples-and-usage)
    - [**5.1. Switching Between Profiles**](#51-switching-between-profiles)
    - [**5.2. Handling File Uploads**](#52-handling-file-uploads)
    - [**5.3. Accessing API Documentation**](#53-accessing-api-documentation)
    - [**5.4. Sending Emails During Development**](#54-sending-emails-during-development)
  - [**6. Best Practices and Recommendations**](#6-best-practices-and-recommendations)
    - [**6.1. Securely Managing Secrets**](#61-securely-managing-secrets)
    - [**6.2. Externalizing Configuration**](#62-externalizing-configuration)
    - [**6.3. Optimizing Performance**](#63-optimizing-performance)
    - [**6.4. Comprehensive Logging**](#64-comprehensive-logging)
  - [**7. Conclusion**](#7-conclusion)
  - [**8. Additional Resources**](#8-additional-resources)


application.yml

```yml
spring:
  profiles:
    active: dev
  servlet:
    multipart:
      max-file-size: 50MB
springdoc:
  default-produces-media-type: application/json
server:
  servlet:
    context-path: /api/v1/
```

application-dev.yml
```yml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/book_social_network
    username: username
    password: password
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
  mail:
    host: localhost
    port: 1025
    username: username
    password: password
    properties:
      mail:
        smtp:
          trust: "*"
        auth: true
        starttls:
          enable: true
        connectiontimeout: 5000
        timeout: 3000
        writetimeout: 5000
```

Certainly! Let's delve deeply into your **Spring Boot** application's configuration files: `application.yml` and `application-dev.yml`. We'll explore each configuration property, understand their roles, and see how they integrate with your **Docker Compose** setup for a seamless development environment.

---

## **Table of Contents**

- [📗application.yml files](#applicationyml-files)
  - [**Table of Contents**](#table-of-contents)
  - [**1. Overview of Spring Boot Configuration**](#1-overview-of-spring-boot-configuration)
  - [**2. Understanding `application.yml`**](#2-understanding-applicationyml)
    - [**2.1. Spring Profiles**](#21-spring-profiles)
    - [**2.2. Servlet Multipart Configuration**](#22-servlet-multipart-configuration)
    - [**2.3. SpringDoc OpenAPI Configuration**](#23-springdoc-openapi-configuration)
    - [**2.4. Server Configuration**](#24-server-configuration)
  - [**3. Understanding `application-dev.yml`**](#3-understanding-application-devyml)
    - [**3.1. DataSource Configuration**](#31-datasource-configuration)
    - [**3.2. JPA (Hibernate) Configuration**](#32-jpa-hibernate-configuration)
    - [**3.3. Mail Configuration**](#33-mail-configuration)
  - [**4. Integration with Docker Compose**](#4-integration-with-docker-compose)
    - [**4.1. PostgreSQL Integration**](#41-postgresql-integration)
    - [**4.2. MailDev Integration**](#42-maildev-integration)
  - [**5. Practical Examples and Usage**](#5-practical-examples-and-usage)
    - [**5.1. Switching Between Profiles**](#51-switching-between-profiles)
    - [**5.2. Handling File Uploads**](#52-handling-file-uploads)
    - [**5.3. Accessing API Documentation**](#53-accessing-api-documentation)
    - [**5.4. Sending Emails During Development**](#54-sending-emails-during-development)
  - [**6. Best Practices and Recommendations**](#6-best-practices-and-recommendations)
    - [**6.1. Securely Managing Secrets**](#61-securely-managing-secrets)
    - [**6.2. Externalizing Configuration**](#62-externalizing-configuration)
    - [**6.3. Optimizing Performance**](#63-optimizing-performance)
    - [**6.4. Comprehensive Logging**](#64-comprehensive-logging)
  - [**7. Conclusion**](#7-conclusion)
  - [**8. Additional Resources**](#8-additional-resources)

---

## **1. Overview of Spring Boot Configuration**

Spring Boot simplifies application configuration through **externalized configuration**. This approach allows you to define configuration properties outside your code, making your application more flexible and easier to manage across different environments (e.g., development, testing, production).

- **`application.yml`**: The primary configuration file containing common settings and specifying active profiles.
- **`application-{profile}.yml`**: Profile-specific configuration files that override or extend properties defined in `application.yml` based on the active profile.

Your setup includes:
- **`application.yml`**: Defines general settings and activates the `dev` profile.
- **`application-dev.yml`**: Contains configurations specific to the development environment, such as database and mail server settings.

---

## **2. Understanding `application.yml`**

Let's break down each section of your `application.yml` to understand its purpose and configuration.

```yaml
spring:
  profiles:
    active: dev
  servlet:
    multipart:
      max-file-size: 50MB
springdoc:
  default-produces-media-type: application/json
server:
  servlet:
    context-path: /api/v1/
```

### **2.1. Spring Profiles**

```yaml
spring:
  profiles:
    active: dev
```

- **Purpose**: Specifies the active profile(s) for the application.
- **Explanation**:
  - **`spring.profiles.active`**: Determines which profile-specific configuration file to load. Here, it's set to `dev`, so `application-dev.yml` will be loaded alongside `application.yml`.
  - **Profiles**: Allow you to define environment-specific configurations without changing your codebase. Common profiles include `dev`, `test`, `prod`.

- **Multiple Profiles**:
  - You can activate multiple profiles by separating them with commas:
    ```yaml
    spring:
      profiles:
        active: dev,featureX
    ```

- **Example Usage**:
  - **Development (`dev`)**: Uses local resources, debug settings.
  - **Production (`prod`)**: Uses optimized settings, production databases.

### **2.2. Servlet Multipart Configuration**

```yaml
spring:
  servlet:
    multipart:
      max-file-size: 50MB
```

- **Purpose**: Configures the handling of multipart (file upload) requests.
- **Explanation**:
  - **`spring.servlet.multipart.max-file-size`**: Sets the maximum size allowed for uploaded files.
    - **`50MB`**: Files larger than 50MB will be rejected.
  
- **Related Properties**:
  - **`spring.servlet.multipart.max-request-size`**: Maximum size allowed for multipart/form-data requests.
  - **`spring.servlet.multipart.enabled`**: Enables or disables multipart support.
  
- **Example Usage**:
  ```yaml
  spring:
    servlet:
      multipart:
        max-file-size: 100MB
        max-request-size: 100MB
        enabled: true
  ```

- **Practical Example**:
  - **Controller Handling File Upload**:
    ```java
    @RestController
    @RequestMapping("/files")
    public class FileController {
      
        @PostMapping("/upload")
        public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }
            // Handle file storage
            return ResponseEntity.ok("File uploaded successfully");
        }
    }
    ```

### **2.3. SpringDoc OpenAPI Configuration**

```yaml
springdoc:
  default-produces-media-type: application/json
```

- **Purpose**: Configures SpringDoc OpenAPI, which automatically generates API documentation.
- **Explanation**:
  - **`springdoc.default-produces-media-type`**: Sets the default `Content-Type` for API responses.
    - **`application/json`**: Indicates that APIs produce JSON responses by default.
  
- **Related Properties**:
  - **`springdoc.api-docs.path`**: Path where the OpenAPI documentation is served (default is `/v3/api-docs`).
  - **`springdoc.swagger-ui.path`**: Path for accessing Swagger UI (default is `/swagger-ui.html`).

- **Example Usage**:
  ```yaml
  springdoc:
    api-docs:
      path: /api-docs
    swagger-ui:
      path: /swagger-ui.html
    default-produces-media-type: application/json
  ```

- **Practical Example**:
  - After starting the application, access [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to view the interactive API documentation.
  - **Controller with OpenAPI Annotations**:
    ```java
    @RestController
    @RequestMapping("/api/books")
    @Tag(name = "Books", description = "Operations related to books")
    public class BookController {
      
        @Operation(summary = "Get all books")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
        })
        @GetMapping
        public ResponseEntity<List<Book>> getAllBooks() {
            List<Book> books = bookService.findAllBooks();
            return ResponseEntity.ok(books);
        }
        
        // Other endpoints
    }
    ```

### **2.4. Server Configuration**

```yaml
server:
  servlet:
    context-path: /api/v1/
```

- **Purpose**: Sets the base URL path for the application.
- **Explanation**:
  - **`server.servlet.context-path`**: Defines a common prefix for all endpoints in the application.
    - **`/api/v1/`**: All your REST endpoints will be accessible under this path.
  
- **Effect**:
  - **Example**:
    - If you have a controller mapped to `/books`, the full URL becomes `/api/v1/books`.
  
- **Example Usage**:
  ```yaml
  server:
    servlet:
      context-path: /myapp/
  ```

  - **Result**:
    - Controller mapped to `/users` becomes accessible at `/myapp/users`.

- **Practical Considerations**:
  - **Versioning APIs**: Using `/api/v1/` allows for future versions (e.g., `/api/v2/`) without breaking existing clients.
  - **Consistent Base Path**: Simplifies reverse proxy configurations and API gateway routing.

---

## **3. Understanding `application-dev.yml`**

The `application-dev.yml` file contains configurations specific to the development environment. These settings override or extend those in `application.yml` when the `dev` profile is active.

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/book_social_network
    username: username
    password: password
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
  mail:
    host: localhost
    port: 1025
    username: username
    password: password
    properties:
      mail:
        smtp:
          trust: "*"
        auth: true
        starttls:
          enable: true
        connectiontimeout: 5000
        timeout: 3000
        writetimeout: 5000
```

### **3.1. DataSource Configuration**

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/book_social_network
    username: username
    password: password
    driver-class-name: org.postgresql.Driver
```

- **Purpose**: Configures the database connection for the application.
- **Explanation**:
  - **`spring.datasource.url`**: JDBC URL to connect to the PostgreSQL database.
    - **`jdbc:postgresql://localhost:5432/book_social_network`**:
      - **Host**: `localhost` (in development, assuming the database runs on the local machine).
      - **Port**: `5432` (default PostgreSQL port).
      - **Database**: `book_social_network`.
  
  - **`spring.datasource.username` & `spring.datasource.password`**: Credentials for authenticating with PostgreSQL.
  
  - **`spring.datasource.driver-class-name`**: Specifies the JDBC driver. Not strictly necessary as Spring Boot can infer it, but included for clarity.
    - **`org.postgresql.Driver`**: PostgreSQL JDBC driver.

- **Practical Example**:
  - **Connecting to Dockerized PostgreSQL**:
    - If PostgreSQL is running in a Docker container with `container_name: postgres-sql-bsn`, update the URL accordingly:
      ```yaml
      spring:
        datasource:
          url: jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
      ```
    - **Note**: Replace `localhost` with the Docker service name (`postgres-sql-bsn`) to enable inter-container communication.

- **Security Considerations**:
  - **Avoid Hardcoding Credentials**:
    - Use environment variables or external secret management tools.
    - **Example with Environment Variables**:
      ```yaml
      spring:
        datasource:
          url: ${DB_URL}
          username: ${DB_USERNAME}
          password: ${DB_PASSWORD}
      ```

### **3.2. JPA (Hibernate) Configuration**

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
```

- **Purpose**: Configures JPA (Java Persistence API) and Hibernate settings for ORM (Object-Relational Mapping).
- **Explanation**:
  - **`spring.jpa.hibernate.ddl-auto`**:
    - **`update`**: Automatically updates the database schema to match entity definitions without dropping tables.
    - **Other Options**:
      - **`none`**: No action.
      - **`validate`**: Validates the schema without making changes.
      - **`create`**: Creates the schema, dropping existing data.
      - **`create-drop`**: Creates the schema on startup and drops it on shutdown.
  
  - **`spring.jpa.show-sql`**:
    - **`false`**: Disables logging of SQL statements.
    - **`true`**: Enables logging of SQL for debugging.
  
  - **`spring.jpa.properties.hibernate.format_sql`**:
    - **`true`**: Formats the SQL statements in logs for readability.
  
  - **`spring.jpa.database`**:
    - **`postgresql`**: Specifies the database type.
  
  - **`spring.jpa.database-platform`**:
    - **`org.hibernate.dialect.PostgreSQLDialect`**: Specifies the Hibernate dialect for PostgreSQL, optimizing SQL generation.

- **Practical Example**:
  - **Entity Definition**:
    ```java
    @Entity
    public class Book {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        private String title;

        @NotBlank
        private String author;

        private String description;

        // Getters and Setters
    }
    ```
  - **Result**:
    - On application startup, Hibernate scans entities and updates the `book_social_network` database schema accordingly.

- **Caution with `ddl-auto=update`**:
  - **Development Use**: Suitable for development where schema changes are frequent.
  - **Production Use**: Not recommended. Prefer managed migrations with tools like Flyway or Liquibase to have controlled, versioned schema changes.

### **3.3. Mail Configuration**

```yaml
spring:
  mail:
    host: localhost
    port: 1025
    username: username
    password: password
    properties:
      mail:
        smtp:
          trust: "*"
        auth: true
        starttls:
          enable: true
        connectiontimeout: 5000
        timeout: 3000
        writetimeout: 5000
```

- **Purpose**: Configures the mail server settings for sending emails from the application.
- **Explanation**:
  - **`spring.mail.host`**: The SMTP server host.
    - **`localhost`**: In development, pointing to a local mail server or a Dockerized service like MailDev.
  
  - **`spring.mail.port`**: The SMTP server port.
    - **`1025`**: Commonly used by MailDev for SMTP.
  
  - **`spring.mail.username` & `spring.mail.password`**:
    - Credentials for SMTP authentication.
    - **In MailDev**: Typically not required or can be left blank.
  
  - **`spring.mail.properties.mail.smtp.trust`**:
    - **`*`**: Trusts all hosts. Useful in development to bypass SSL certificate issues.
  
  - **`spring.mail.properties.mail.smtp.auth`**:
    - **`true`**: Enables SMTP authentication.
  
  - **`spring.mail.properties.mail.smtp.starttls.enable`**:
    - **`true`**: Enables STARTTLS for secure communication.
    - **Note**: MailDev may not require TLS; adjust based on the mail server used.
  
  - **`spring.mail.properties.mail.smtp.connectiontimeout`**:
    - **`5000`**: Connection timeout in milliseconds.
  
  - **`spring.mail.properties.mail.smtp.timeout`**:
    - **`3000`**: I/O timeout in milliseconds.
  
  - **`spring.mail.properties.mail.smtp.writetimeout`**:
    - **`5000`**: Write timeout in milliseconds.

- **Practical Example**:
  - **Using MailDev**:
    - **Configuration**:
      ```yaml
      spring:
        mail:
          host: localhost
          port: 1025
          username:
          password:
          properties:
            mail:
              smtp:
                trust: "*"
              auth: false
              starttls:
                enable: false
      ```
    - **Reason**: MailDev doesn't require authentication or TLS, simplifying email testing.
  
  - **Sending Emails**:
    ```java
    @Service
    public class EmailService {
      
        @Autowired
        private JavaMailSender mailSender;

        public void sendWelcomeEmail(String to, String username) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Welcome to Book Social Network!");
            message.setText("Hello " + username + ", welcome to our platform.");
            mailSender.send(message);
        }
    }
    ```
    - **Result**: Emails are captured by MailDev and accessible via its web interface (e.g., [http://localhost:1080](http://localhost:1080)) without being sent to real email addresses.

- **Adjusting for Different Mail Servers**:
  - **Using Gmail SMTP**:
    ```yaml
    spring:
      mail:
        host: smtp.gmail.com
        port: 587
        username: your-email@gmail.com
        password: your-email-password
        properties:
          mail:
            smtp:
              trust: "smtp.gmail.com"
            auth: true
            starttls:
              enable: true
            connectiontimeout: 5000
            timeout: 3000
            writetimeout: 5000
    ```
    - **Note**: Ensure less secure app access is enabled or use App Passwords for Gmail.

---

## **4. Integration with Docker Compose**

Your **Docker Compose** setup includes two primary services:

1. **PostgreSQL**: Database service.
2. **MailDev**: Development mail server.

Let's see how these services integrate with your Spring Boot application's configurations.

### **4.1. PostgreSQL Integration**

- **Docker Compose Service**:
  ```yaml
  services:
    postgres:
      container_name: postgres-sql-bsn
      image: postgres:16
      environment:
        POSTGRES_USER: username
        POSTGRES_PASSWORD: password
        PG_DATA: /var/lib/postgresql/data
        POSTGRES_DB: book_social_network
      volumes:
        - postgres:/data/postgres
      ports:
        - "5432:5432"
      networks:
        - spring-demo
      restart: unless-stopped
  ```
  
- **Spring Boot Configuration**:
  ```yaml
  spring:
    datasource:
      url: jdbc:postgresql://localhost:5432/book_social_network
      username: username
      password: password
      driver-class-name: org.postgresql.Driver
    # ... other properties
  ```

- **Integration Steps**:
  1. **Start PostgreSQL Service**:
     ```bash
     docker-compose up -d postgres
     ```
  
  2. **Ensure Database is Accessible**:
     - **From Host**: Via `localhost:5432`.
     - **From Other Containers**: Via service name `postgres-sql-bsn:5432`.
  
  3. **Update Spring Boot Configuration (if necessary)**:
     - If Spring Boot runs on the host, `localhost:5432` is correct.
     - If Spring Boot runs inside another Docker container on the same network (`spring-demo`), use `jdbc:postgresql://postgres-sql-bsn:5432/book_social_network`.
  
  4. **Example Dockerized Spring Boot Service**:
     ```yaml
     services:
       app:
         build: .
         container_name: book-network-app
         image: book-network:latest
         environment:
           SPRING_DATASOURCE_URL: jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
           SPRING_DATASOURCE_USERNAME: username
           SPRING_DATASOURCE_PASSWORD: password
         ports:
           - "8080:8080"
         networks:
           - spring-demo
         depends_on:
           - postgres
           - mail-dev
         restart: unless-stopped
     ```
  
  - **Explanation**:
    - **Environment Variables**: Overrides Spring Boot's `application-dev.yml` properties to use Docker service names.
    - **`depends_on`**: Ensures `postgres` starts before `app`.
  
  - **Spring Boot Configuration Update**:
    ```yaml
    spring:
      datasource:
        url: jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
        username: username
        password: password
        driver-class-name: org.postgresql.Driver
      # ... other properties
    ```

### **4.2. MailDev Integration**

- **Docker Compose Service**:
  ```yaml
  services:
    mail-dev:
      container_name: mail-dev-bsn
      image: maildev/maildev
      ports:
        - "1080:1080"
        - "1025:1025"
  ```
  
- **Spring Boot Configuration**:
  ```yaml
  spring:
    mail:
      host: localhost
      port: 1025
      username: username
      password: password
      properties:
        mail:
          smtp:
            trust: "*"
          auth: true
          starttls:
            enable: true
          connectiontimeout: 5000
          timeout: 3000
          writetimeout: 5000
  ```
  
- **Integration Steps**:
  1. **Start MailDev Service**:
     ```bash
     docker-compose up -d mail-dev
     ```
  
  2. **Access MailDev Web Interface**:
     - Navigate to [http://localhost:1080](http://localhost:1080) to view sent emails.
  
  3. **Configure Spring Boot**:
     - **Host**: `localhost` (if Spring Boot runs on the host).
     - **Port**: `1025` (MailDev's SMTP port).
  
  - **If Spring Boot Runs Inside Docker**:
    - Use service name `mail-dev-bsn`:
      ```yaml
      spring:
        mail:
          host: mail-dev-bsn
          port: 1025
          username:
          password:
          properties:
            mail:
              smtp:
                trust: "*"
              auth: false
              starttls:
                enable: false
      ```
    - **Note**: MailDev in Docker doesn't require authentication or TLS.

- **Example Spring Boot Service in Docker**:
  ```yaml
  services:
    app:
      build: .
      container_name: book-network-app
      image: book-network:latest
      environment:
        SPRING_DATASOURCE_URL: jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
        SPRING_DATASOURCE_USERNAME: username
        SPRING_DATASOURCE_PASSWORD: password
        SPRING_MAIL_HOST: mail-dev-bsn
        SPRING_MAIL_PORT: 1025
      ports:
        - "8080:8080"
      networks:
        - spring-demo
      depends_on:
        - postgres
        - mail-dev
      restart: unless-stopped
  ```

- **Practical Example**:
  - **Sending a Welcome Email**:
    1. **Trigger Email**: Register a new user, which calls `sendWelcomeEmail()`.
    2. **MailDev Captures Email**: The email is not sent to a real address but is captured by MailDev.
    3. **View Email**: Access [http://localhost:1080](http://localhost:1080) to see the email content, subject, and recipient.

---

## **5. Practical Examples and Usage**

Let's explore how these configurations work in practice, including switching profiles, handling file uploads, accessing API documentation, and sending emails during development.

### **5.1. Switching Between Profiles**

**Active Profile**: Defined in `application.yml` as `dev`.

**Scenario**: Switching to a `prod` profile for production deployment.

1. **Create `application-prod.yml`**:
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://prod-db-server:5432/book_social_network
       username: prod_user
       password: prod_password
       driver-class-name: org.postgresql.Driver
     jpa:
       hibernate:
         ddl-auto: validate
       show-sql: false
       properties:
         hibernate:
           format_sql: false
       database: postgresql
       database-platform: org.hibernate.dialect.PostgreSQLDialect
     mail:
       host: smtp.prod-mail-server.com
       port: 587
       username: prod_email_user
       password: prod_email_password
       properties:
         mail:
           smtp:
             trust: "smtp.prod-mail-server.com"
           auth: true
           starttls:
             enable: true
           connectiontimeout: 10000
           timeout: 5000
           writetimeout: 10000
   ```

2. **Update `application.yml` to Use `prod` Profile**:
   ```yaml
   spring:
     profiles:
       active: prod
     # ... other configurations
   ```

3. **Deploying with `prod` Profile**:
   - **Docker Compose Adjustment**:
     - If deploying via Docker Compose, set the `SPRING_PROFILES_ACTIVE` environment variable to `prod`.
     ```yaml
     services:
       app:
         environment:
           SPRING_PROFILES_ACTIVE: prod
           # ... other environment variables
     ```
   - **Command-Line Override**:
     - Run the application with a different profile.
     ```bash
     java -jar book-network-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
     ```

4. **Benefits**:
   - **Separation of Concerns**: Different configurations for development and production.
   - **Controlled Schema Changes**: Using `ddl-auto=validate` in production to prevent unintended schema updates.

### **5.2. Handling File Uploads**

**Configuration**:
```yaml
spring:
  servlet:
    multipart:
      max-file-size: 50MB
```

**Scenario**: Uploading book cover images to the application.

1. **Controller to Handle File Upload**:
   ```java
   @RestController
   @RequestMapping("/api/books")
   public class BookController {
     
       @PostMapping("/upload")
       public ResponseEntity<String> uploadBookCover(@RequestParam("file") MultipartFile file) {
           if (file.isEmpty()) {
               return ResponseEntity.badRequest().body("Please select a file to upload.");
           }
           
           // Save the file to the desired location
           try {
               byte[] bytes = file.getBytes();
               Path path = Paths.get("uploads/" + file.getOriginalFilename());
               Files.write(path, bytes);
           } catch (IOException e) {
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
           }
           
           return ResponseEntity.ok("File uploaded successfully.");
       }
   }
   ```

2. **Uploading a File**:
   - **Using Postman**:
     - **Method**: POST
     - **URL**: `http://localhost:8080/api/v1/books/upload`
     - **Body**: Form-data with key `file` and attach the desired file.
  
3. **Handling File Size Limits**:
   - **Beyond 50MB**:
     - If a user tries to upload a file larger than 50MB, Spring Boot will reject the request with a `MaxUploadSizeExceededException`.
     - **Global Exception Handler**:
       ```java
       @RestControllerAdvice
       public class GlobalExceptionHandler {
         
           @ExceptionHandler(MaxUploadSizeExceededException.class)
           public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
               return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File too large!");
           }
           
           // Other exception handlers
       }
       ```

### **5.3. Accessing API Documentation**

**Configuration**:
```yaml
springdoc:
  default-produces-media-type: application/json
```

**Scenario**: Viewing and interacting with API endpoints using Swagger UI.

1. **Ensure SpringDoc Dependency**:
   - Make sure `springdoc-openapi-starter-webmvc-ui` is included in your `pom.xml`.
     ```xml
     <dependency>
         <groupId>org.springdoc</groupId>
         <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
         <version>2.3.0</version>
     </dependency>
     ```

2. **Accessing Swagger UI**:
   - **URL**: `http://localhost:8080/swagger-ui.html` or `http://localhost:8080/swagger-ui/index.html`
  
3. **Interacting with APIs**:
   - Swagger UI provides an interactive interface to test API endpoints without external tools.
   - **Example**:
     - **GET /api/v1/books**: Fetch all books.
     - **POST /api/v1/books**: Add a new book.
     - **POST /api/v1/books/upload**: Upload a book cover image.

4. **Customizing OpenAPI Documentation**:
   - **Adding Descriptions and Tags**:
     ```java
     @RestController
     @RequestMapping("/api/books")
     @Tag(name = "Books", description = "Operations related to books")
     public class BookController {
         
         @Operation(summary = "Get all books", description = "Retrieve a list of all available books.")
         @GetMapping
         public ResponseEntity<List<Book>> getAllBooks() {
             // ...
         }
         
         // Other endpoints
     }
     ```

5. **Benefits**:
   - **Documentation**: Automatically generated, keeping documentation up-to-date with code.
   - **Testing**: Easily test APIs during development.
   - **Client Generation**: Generate client code in various languages based on the OpenAPI spec.

### **5.4. Sending Emails During Development**

**Configuration**:
```yaml
spring:
  mail:
    host: localhost
    port: 1025
    username: username
    password: password
    properties:
      mail:
        smtp:
          trust: "*"
        auth: true
        starttls:
          enable: true
        connectiontimeout: 5000
        timeout: 3000
        writetimeout: 5000
```

**Scenario**: Sending welcome emails to users upon registration.

1. **MailDev Setup**:
   - **Start MailDev Service**:
     ```bash
     docker-compose up -d mail-dev
     ```
  
   - **Access MailDev UI**:
     - **URL**: [http://localhost:1080](http://localhost:1080)
  
2. **Email Service Implementation**:
   ```java
   @Service
   public class EmailService {
       
       @Autowired
       private JavaMailSender mailSender;

       public void sendWelcomeEmail(String to, String username) {
           SimpleMailMessage message = new SimpleMailMessage();
           message.setTo(to);
           message.setSubject("Welcome to Book Social Network!");
           message.setText("Hello " + username + ", welcome to our platform.");
           mailSender.send(message);
       }
   }
   ```

3. **Controller Triggering Email**:
   ```java
   @RestController
   @RequestMapping("/api/users")
   public class UserController {
       
       @Autowired
       private EmailService emailService;
       
       @PostMapping("/register")
       public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
           // Registration logic (save user to database)
           
           // Send welcome email
           emailService.sendWelcomeEmail(userDto.getEmail(), userDto.getUsername());
           
           return ResponseEntity.ok("User registered successfully");
       }
   }
   ```

4. **Viewing Sent Emails**:
   - After registering a user, navigate to [http://localhost:1080](http://localhost:1080) to see the captured email.
  
5. **Adjusting SMTP Properties for MailDev**:
   - **Disable Authentication and TLS** (if MailDev doesn't require them):
     ```yaml
     spring:
       mail:
         host: localhost
         port: 1025
         username:
         password:
         properties:
           mail:
             smtp:
               trust: "*"
             auth: false
             starttls:
               enable: false
             connectiontimeout: 5000
             timeout: 3000
             writetimeout: 5000
     ```

---

## **6. Best Practices and Recommendations**

Adhering to best practices ensures your application is secure, maintainable, and scalable. Here's how you can enhance your configuration:

### **6.1. Securely Managing Secrets**

- **Avoid Hardcoding Credentials**:
  - Do not store sensitive information directly in configuration files, especially if they're part of version control.
  
- **Use Environment Variables**:
  - **Example**:
    ```yaml
    spring:
      datasource:
        url: ${DB_URL}
        username: ${DB_USERNAME}
        password: ${DB_PASSWORD}
      mail:
        host: ${MAIL_HOST}
        port: ${MAIL_PORT}
        username: ${MAIL_USERNAME}
        password: ${MAIL_PASSWORD}
    ```
  - **Setting Environment Variables**:
    - **Unix/Linux**:
      ```bash
      export DB_URL=jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
      export DB_USERNAME=username
      export DB_PASSWORD=password
      export MAIL_HOST=mail-dev-bsn
      export MAIL_PORT=1025
      export MAIL_USERNAME=username
      export MAIL_PASSWORD=password
      ```
    - **Windows**:
      ```cmd
      set DB_URL=jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
      set DB_USERNAME=username
      set DB_PASSWORD=password
      set MAIL_HOST=mail-dev-bsn
      set MAIL_PORT=1025
      set MAIL_USERNAME=username
      set MAIL_PASSWORD=password
      ```

- **Use `.env` Files with Docker Compose**:
  - **Create a `.env` File**:
    ```env
    DB_URL=jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
    DB_USERNAME=username
    DB_PASSWORD=password
    MAIL_HOST=mail-dev-bsn
    MAIL_PORT=1025
    MAIL_USERNAME=username
    MAIL_PASSWORD=password
    ```
  - **Reference in `docker-compose.yml`**:
    ```yaml
    services:
      app:
        environment:
          SPRING_DATASOURCE_URL: ${DB_URL}
          SPRING_DATASOURCE_USERNAME: ${DB_USERNAME}
          SPRING_DATASOURCE_PASSWORD: ${DB_PASSWORD}
          SPRING_MAIL_HOST: ${MAIL_HOST}
          SPRING_MAIL_PORT: ${MAIL_PORT}
          SPRING_MAIL_USERNAME: ${MAIL_USERNAME}
          SPRING_MAIL_PASSWORD: ${MAIL_PASSWORD}
    ```
  - **Security Tip**: Add `.env` to `.gitignore` to prevent committing sensitive data.
    ```gitignore
    # .gitignore
    .env
    ```

### **6.2. Externalizing Configuration**

- **Use Separate Configuration Files for Different Environments**:
  - Maintain `application-dev.yml`, `application-prod.yml`, etc., to segregate environment-specific settings.
  
- **Leverage Spring Boot's Config Hierarchy**:
  - Properties defined in `application-dev.yml` override those in `application.yml` when the `dev` profile is active.
  
- **Example**:
  - **`application.yml`**:
    ```yaml
    server:
      port: 8080
    spring:
      profiles:
        active: dev
      datasource:
        url: jdbc:postgresql://localhost:5432/book_social_network
        username: username
        password: password
    ```
  - **`application-prod.yml`**:
    ```yaml
    server:
      port: 80
    spring:
      datasource:
        url: jdbc:postgresql://prod-db-server:5432/book_social_network
        username: prod_user
        password: prod_password
    ```

### **6.3. Optimizing Performance**

- **Adjust Hibernate Settings**:
  - **`hibernate.show_sql`**: Disable in production to reduce logging overhead.
    ```yaml
    spring:
      jpa:
        show-sql: false
    ```
  
  - **`hibernate.format_sql`**: Disable formatting in production for performance.
    ```yaml
    spring:
      jpa:
        properties:
          hibernate:
            format_sql: false
    ```
  
  - **`ddl-auto` Setting**:
    - Use `validate` or `none` in production to prevent automatic schema changes.
    - Implement controlled migrations with Flyway or Liquibase.

- **Configure Connection Pooling**:
  - **Spring Boot uses HikariCP by default**:
    - **Example**:
      ```yaml
      spring:
        datasource:
          hikari:
            maximum-pool-size: 20
            minimum-idle: 5
            idle-timeout: 30000
            connection-timeout: 30000
      ```

- **Enable Caching**:
  - Utilize caching for frequently accessed data to reduce database load.
    ```yaml
    spring:
      cache:
        type: redis
    ```
  
  - **Integration with Redis**:
    - Add Redis dependency.
      ```xml
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-data-redis</artifactId>
      </dependency>
      ```
    - Configure Redis properties.
      ```yaml
      spring:
        redis:
          host: redis-server
          port: 6379
      ```

### **6.4. Comprehensive Logging**

- **Configure Logging Levels**:
  - **Example**:
    ```yaml
    logging:
      level:
        root: INFO
        com.wchamara.booknetwork: DEBUG
    ```
  
  - **Explanation**:
    - **`root: INFO`**: Sets the default logging level to INFO.
    - **`com.wchamara.booknetwork: DEBUG`**: Enables DEBUG level logging for your application's packages.

- **Customize Log Formats**:
  ```yaml
  logging:
    pattern:
      console: "%d{yyyy-MM-dd HH:mm:ss} - %logger{36} - %msg%n"
  ```

- **External Log Configuration**:
  - Use `logback-spring.xml` or `log4j2-spring.xml` for advanced logging configurations.
  
- **Practical Example**:
  - **Logback Configuration (`logback-spring.xml`)**:
    ```xml
    <configuration>
        <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
            <encoder>
                <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
            </encoder>
        </appender>

        <logger name="com.wchamara.booknetwork" level="DEBUG" />

        <root level="INFO">
            <appender-ref ref="STDOUT" />
        </root>
    </configuration>
    ```

---

## **7. Conclusion**

Your Spring Boot application's configuration is thoughtfully segmented into general settings (`application.yml`) and environment-specific settings (`application-dev.yml`). This approach ensures flexibility, maintainability, and scalability across different environments.

**Key Takeaways**:

- **Profiles**: Utilize Spring Boot profiles to manage environment-specific configurations effectively.
- **Externalized Configuration**: Avoid hardcoding sensitive information; leverage environment variables and `.env` files.
- **Integration with Docker Compose**: Seamlessly connect your application to Dockerized services like PostgreSQL and MailDev.
- **Best Practices**: Emphasize security, performance optimization, and comprehensive logging to enhance application robustness.

By understanding and implementing these configurations, you ensure that your **Book Social Network** application is well-equipped to handle development needs and is poised for smooth transitions to production environments.

---

## **8. Additional Resources**

To further enhance your understanding and application of Spring Boot configurations and Docker integration, consider exploring the following resources:

1. **Spring Boot Documentation**:
   - [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
   - [Externalized Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config)
  
2. **SpringDoc OpenAPI**:
   - [SpringDoc Official Documentation](https://springdoc.org/)
   - [Integrating SpringDoc with Spring Boot](https://springdoc.org/quickstart.html)
  
3. **Docker Compose Documentation**:
   - [Docker Compose Overview](https://docs.docker.com/compose/)
   - [Best Practices for Docker Compose](https://docs.docker.com/compose/best-practices/)
  
4. **Flyway and Liquibase for Database Migrations**:
   - [Flyway Documentation](https://flywaydb.org/documentation/)
   - [Liquibase Documentation](https://www.liquibase.org/documentation/index.html)
  
5. **Spring Boot Security**:
   - [Spring Security Reference](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)
   - [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
  
6. **Spring Boot File Upload**:
   - [Handling File Uploads with Spring Boot](https://www.baeldung.com/spring-boot-file-upload)
  
7. **Logging in Spring Boot**:
   - [Spring Boot Logging Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging)

Feel free to explore these resources to deepen your understanding and enhance your application's capabilities.