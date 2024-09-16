# Book Social Media
- [Book Social Media](#book-social-media)
- [pom.xml](#pomxml)
  - [**1. Overview of `pom.xml`**](#1-overview-of-pomxml)
    - [**Key Sections:**](#key-sections)
  - [**2. Detailed Breakdown**](#2-detailed-breakdown)
    - [**2.1. Project Coordinates**](#21-project-coordinates)
    - [**2.2. Parent**](#22-parent)
    - [**2.3. Properties**](#23-properties)
    - [**2.4. Dependencies**](#24-dependencies)
      - [**2.4.1. Spring Boot Starters**](#241-spring-boot-starters)
      - [**2.4.2. Development Tools**](#242-development-tools)
      - [**2.4.3. JSON Web Tokens (JWT)**](#243-json-web-tokens-jwt)
      - [**2.4.4. API Documentation**](#244-api-documentation)
      - [**2.4.5. Testing Dependencies**](#245-testing-dependencies)
    - [**2.5. Build Configuration**](#25-build-configuration)
      - [**2.5.1. Spring Boot Maven Plugin**](#251-spring-boot-maven-plugin)
  - [**3. Putting It All Together: Example Workflow**](#3-putting-it-all-together-example-workflow)
    - [**3.1. Building the Project**](#31-building-the-project)
    - [**3.2. Running the Application**](#32-running-the-application)
    - [**3.3. Testing the Application**](#33-testing-the-application)
  - [**4. Advanced Topics and Best Practices**](#4-advanced-topics-and-best-practices)
    - [**4.1. Dependency Management**](#41-dependency-management)
    - [**4.2. Profiles**](#42-profiles)
    - [**4.3. Plugin Management**](#43-plugin-management)
    - [**4.4. Spring Boot Configuration**](#44-spring-boot-configuration)
    - [**4.5. Lombok Best Practices**](#45-lombok-best-practices)
    - [**4.6. Security Considerations**](#46-security-considerations)
  - [**5. Sample Project Structure**](#5-sample-project-structure)
  - [**6. Additional Recommendations**](#6-additional-recommendations)
    - [**6.1. Managing Secrets**](#61-managing-secrets)
    - [**6.2. Dockerization**](#62-dockerization)
    - [**6.3. Continuous Integration/Continuous Deployment (CI/CD)**](#63-continuous-integrationcontinuous-deployment-cicd)
    - [**6.4. API Documentation and Testing**](#64-api-documentation-and-testing)
    - [**6.5. Exception Handling**](#65-exception-handling)
    - [**6.6. Logging**](#66-logging)
  - [**7. Conclusion**](#7-conclusion)
- [adding docker compose to the project](#adding-docker-compose-to-the-project)
  - [**Table of Contents**](#table-of-contents)
  - [**1. Overview of Docker Compose**](#1-overview-of-docker-compose)
  - [**2. Detailed Breakdown of `docker-compose.yml`**](#2-detailed-breakdown-of-docker-composeyml)
    - [**2.1. Services**](#21-services)
      - [**2.1.1. PostgreSQL Service**](#211-postgresql-service)
      - [**Common Issues \& Troubleshooting:**](#common-issues--troubleshooting)
      - [**Example Commands:**](#example-commands)
      - [**Additional Configuration Options:**](#additional-configuration-options)
      - [**Security Considerations:**](#security-considerations)
      - [**Full PostgreSQL Service Example with Enhancements:**](#full-postgresql-service-example-with-enhancements)
      - [**Further Reading:**](#further-reading)
      - [**2.1.2. MailDev Service**](#212-maildev-service)
      - [**Common Issues \& Troubleshooting:**](#common-issues--troubleshooting-1)
      - [**Additional Configuration Options:**](#additional-configuration-options-1)
    - [**2.2. Networks**](#22-networks)
    - [**2.3. Volumes**](#23-volumes)
      - [**Additional Volume Configuration Options:**](#additional-volume-configuration-options)
      - [**Security Considerations:**](#security-considerations-1)
  - [**3. Integration with Spring Boot Application**](#3-integration-with-spring-boot-application)
    - [**3.1. Database Configuration**](#31-database-configuration)
    - [**3.2. Mail Configuration**](#32-mail-configuration)
    - [**3.3. Application Startup Dependencies**](#33-application-startup-dependencies)
  - [**4. Running the Docker Compose Setup**](#4-running-the-docker-compose-setup)
    - [**4.1. Prerequisites**](#41-prerequisites)
    - [**4.2. Starting the Services**](#42-starting-the-services)
    - [**4.3. Stopping the Services**](#43-stopping-the-services)
    - [**4.4. Rebuilding Services**](#44-rebuilding-services)
    - [**4.5. Managing Services**](#45-managing-services)
  - [**5. Advanced Configurations and Best Practices**](#5-advanced-configurations-and-best-practices)
    - [**5.1. Environment Variables and `.env` Files**](#51-environment-variables-and-env-files)
    - [**5.2. Health Checks**](#52-health-checks)
    - [**5.3. Using Docker Networks for Multiple Environments**](#53-using-docker-networks-for-multiple-environments)
    - [**5.4. Volume Backups and Restoration**](#54-volume-backups-and-restoration)
    - [**5.5. Securing Services**](#55-securing-services)
    - [**5.6. Optimizing Dockerfile for Spring Boot Application**](#56-optimizing-dockerfile-for-spring-boot-application)
    - [**5.7. Health Checks for MailDev Service**](#57-health-checks-for-maildev-service)
    - [**5.8. Scaling Services**](#58-scaling-services)
    - [**5.9. Using a Reverse Proxy (e.g., Nginx) for Load Balancing**](#59-using-a-reverse-proxy-eg-nginx-for-load-balancing)
    - [**5.10. Environment-Specific Configurations**](#510-environment-specific-configurations)
    - [**5.11. Monitoring and Logging**](#511-monitoring-and-logging)
    - [**5.12. Handling Data Migrations**](#512-handling-data-migrations)
    - [**5.13. Using Docker Secrets for Sensitive Data (Advanced)**](#513-using-docker-secrets-for-sensitive-data-advanced)
    - [**5.14. Optimizing Docker Images for Faster Builds**](#514-optimizing-docker-images-for-faster-builds)
    - [**5.15. Handling Service Dependencies**](#515-handling-service-dependencies)
    - [**5.16. Scaling and Load Balancing**](#516-scaling-and-load-balancing)
    - [**3.2. Configuring Spring Boot to Use MailDev for Email Testing**](#32-configuring-spring-boot-to-use-maildev-for-email-testing)
  - [**4. Running the Docker Compose Setup**](#4-running-the-docker-compose-setup-1)
    - [**4.1. Prerequisites**](#41-prerequisites-1)
    - [**4.2. Building and Running Services**](#42-building-and-running-services)
    - [**4.3. Stopping and Removing Services**](#43-stopping-and-removing-services)
    - [**4.4. Rebuilding Services**](#44-rebuilding-services-1)
    - [**4.5. Scaling Services**](#45-scaling-services)
  - [**5. Advanced Configurations and Best Practices**](#5-advanced-configurations-and-best-practices-1)
    - [**5.1. Using `.env` Files for Environment Variables**](#51-using-env-files-for-environment-variables)
    - [**5.2. Implementing Health Checks**](#52-implementing-health-checks)
    - [**5.3. Securing Sensitive Information**](#53-securing-sensitive-information)
    - [**5.4. Implementing Logging and Monitoring**](#54-implementing-logging-and-monitoring)
    - [**5.5. Automating Backups**](#55-automating-backups)
    - [**5.6. Optimizing Docker Images**](#56-optimizing-docker-images)
    - [**5.7. Using Docker Compose Overrides for Development**](#57-using-docker-compose-overrides-for-development)
    - [**5.8. Leveraging Docker Compose Commands**](#58-leveraging-docker-compose-commands)
    - [**5.9. Managing Data with Docker Volumes**](#59-managing-data-with-docker-volumes)
    - [**5.10. Testing the Setup**](#510-testing-the-setup)
    - [**5.11. Optimizing Docker Compose for Production**](#511-optimizing-docker-compose-for-production)
    - [**5.12. Using External Services and Integrations**](#512-using-external-services-and-integrations)
  - [**6. Conclusion**](#6-conclusion)
- [application.yml files](#applicationyml-files)


# pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.3.3</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.wchamara</groupId>
	<artifactId>book-network</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>book-network</name>
	<description>Social Media App for Book Sharing</description>
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-mail</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.12.3</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.12.6</version>
		</dependency>
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.3.0</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>0.12.5</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>

```

Certainly! Let's dive deep into the provided Maven `pom.xml` file for your Spring Boot project, **book-network**, a social media app for book sharing. We'll explore each section, explain the purpose of various dependencies and plugins, and provide examples to illustrate how they fit into your project.

---

## **1. Overview of `pom.xml`**

The `pom.xml` (Project Object Model) is the core of a Maven project. It manages project configuration, dependencies, build settings, plugins, and more. Here's the structure of your `pom.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!-- Project Information and Configuration -->
    <modelVersion>4.0.0</modelVersion>
    <parent>...</parent>
    <groupId>com.wchamara</groupId>
    <artifactId>book-network</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>book-network</name>
    <description>Social Media App for Book Sharing</description>
    <url/>
    <licenses>
        <license/>
    </licenses>
    <developers>
        <developer/>
    </developers>
    <scm>
        <connection/>
        <developerConnection/>
        <tag/>
        <url/>
    </scm>
    <properties>
        <java.version>17</java.version>
    </properties>
    <!-- Dependencies -->
    <dependencies>
        <!-- Various dependencies listed here -->
    </dependencies>
    <!-- Build Configuration -->
    <build>
        <plugins>
            <!-- Plugins configuration here -->
        </plugins>
    </build>
</project>
```

### **Key Sections:**

1. **Project Coordinates**: Uniquely identify your project (`groupId`, `artifactId`, `version`).
2. **Parent**: Inherits configurations from the Spring Boot Starter Parent.
3. **Properties**: Define project-wide properties, such as Java version.
4. **Dependencies**: Libraries your project relies on.
5. **Build**: Configuration for building the project, including plugins.

---

## **2. Detailed Breakdown**

### **2.1. Project Coordinates**

```xml
<groupId>com.wchamara</groupId>
<artifactId>book-network</artifactId>
<version>0.0.1-SNAPSHOT</version>
<name>book-network</name>
<description>Social Media App for Book Sharing</description>
```

- **`groupId`**: Typically follows the reverse domain name convention. Here, `com.wchamara` likely represents your organization's domain.

- **`artifactId`**: The name of the project. `book-network` suggests it's a networking application centered around books.

- **`version`**: Denotes the current version of your project. `0.0.1-SNAPSHOT` indicates an initial development version.

- **`name` & `description`**: Provide human-readable information about your project.

**Example Usage:**

When you build your project, Maven packages it as `book-network-0.0.1-SNAPSHOT.jar`, making it identifiable.

---

### **2.2. Parent**

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.3.3</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```

- **Purpose**: Inherits default configurations, dependency management, and plugin settings from the Spring Boot Starter Parent.

- **Benefits**:
    - **Dependency Management**: Automatically manages versions of common dependencies.
    - **Plugin Configuration**: Pre-configured plugins, reducing the need for explicit configurations.
    - **Standardization**: Ensures consistency across Spring Boot projects.

**Example Scenario:**

Without a parent, you'd need to specify versions for all dependencies and plugins. By inheriting from Spring Boot's parent, you simplify `pom.xml` and ensure compatibility.

---

### **2.3. Properties**

```xml
<properties>
    <java.version>17</java.version>
</properties>
```

- **Purpose**: Defines properties used throughout the `pom.xml`. Here, it sets the Java version to 17.

- **Usage**:
    - The Spring Boot parent uses this property to set the compiler plugin's target and source versions.

**Example Configuration:**

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>${java.version}</source>
                <target>${java.version}</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```

Here, `${java.version}` ensures consistency and easy updates.

---

### **2.4. Dependencies**

Dependencies are external libraries your project needs. Let's examine each one:

```xml
<dependencies>
    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Spring Mail -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>

    <!-- Spring Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Thymeleaf Template Engine -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

    <!-- Spring Boot Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Spring Web (MVC) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot DevTools -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>

    <!-- Lombok for Boilerplate Code Reduction -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- JSON Web Token (JWT) APIs -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.12.3</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.12.6</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.12.5</version>
    </dependency>

    <!-- SpringDoc OpenAPI for API Documentation -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.3.0</version>
    </dependency>

    <!-- Testing Dependencies -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

Let's break down each dependency:

---

#### **2.4.1. Spring Boot Starters**

Spring Boot Starters are dependency descriptors that simplify adding commonly used dependencies. They include transitive dependencies required for various functionalities.

1. **`spring-boot-starter-data-jpa`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    ```

    - **Purpose**: Provides Spring Data JPA functionalities, enabling interaction with relational databases using JPA (Java Persistence API).

    - **Transitive Dependencies**:
        - Hibernate (as the default JPA provider)
        - Spring ORM
        - Spring Data Commons

    - **Example Usage**:

      Define an Entity and Repository to interact with the database.

      ```java
      // User.java
      @Entity
      public class User {
          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          private Long id;

          private String username;
          private String email;
          // Getters and Setters
      }

      // UserRepository.java
      public interface UserRepository extends JpaRepository<User, Long> {
          Optional<User> findByUsername(String username);
      }
      ```

2. **`spring-boot-starter-mail`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
    ```

    - **Purpose**: Simplifies sending emails using JavaMailSender.

    - **Transitive Dependencies**:
        - JavaMail API
        - Spring Framework's mail support

    - **Example Usage**:

      ```java
      @Service
      public class EmailService {
          @Autowired
          private JavaMailSender mailSender;

          public void sendWelcomeEmail(String to, String username) {
              SimpleMailMessage message = new SimpleMailMessage();
              message.setTo(to);
              message.setSubject("Welcome to Book Network!");
              message.setText("Hello " + username + ", welcome to our platform.");
              mailSender.send(message);
          }
      }
      ```

3. **`spring-boot-starter-security`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    ```

    - **Purpose**: Adds Spring Security for authentication and authorization.

    - **Transitive Dependencies**:
        - Spring Security Core
        - Spring Security Config
        - Spring Security Web

    - **Example Usage**:

      Configure security settings.

      ```java
      @Configuration
      @EnableWebSecurity
      public class SecurityConfig extends WebSecurityConfigurerAdapter {
          @Override
          protected void configure(HttpSecurity http) throws Exception {
              http
                  .csrf().disable()
                  .authorizeRequests()
                  .antMatchers("/api/public/**").permitAll()
                  .anyRequest().authenticated()
                  .and()
                  .httpBasic();
          }
      }
      ```

4. **`spring-boot-starter-thymeleaf`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    ```

    - **Purpose**: Integrates Thymeleaf as the templating engine for server-side rendering of HTML.

    - **Transitive Dependencies**:
        - Thymeleaf
        - Spring Template
        - Spring Boot's Thymeleaf integration

    - **Example Usage**:

      Create a Thymeleaf template and controller.

      ```html
      <!-- src/main/resources/templates/home.html -->
      <!DOCTYPE html>
      <html xmlns:th="http://www.thymeleaf.org">
      <head>
          <title>Home</title>
      </head>
      <body>
          <h1 th:text="'Welcome, ' + ${username} + '!'">Welcome!</h1>
      </body>
      </html>
      ```

      ```java
      @Controller
      public class HomeController {
          @GetMapping("/home")
          public String home(Model model) {
              model.addAttribute("username", "John Doe");
              return "home";
          }
      }
      ```

5. **`spring-boot-starter-validation`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    ```

    - **Purpose**: Provides support for bean validation using Hibernate Validator.

    - **Transitive Dependencies**:
        - Hibernate Validator
        - Bean Validation API

    - **Example Usage**:

      Validate request data in controllers.

      ```java
      public class UserRegistrationDto {
          @NotBlank
          private String username;

          @Email
          private String email;

          @NotBlank
          private String password;

          // Getters and Setters
      }

      @RestController
      @RequestMapping("/api/auth")
      public class AuthController {
          @PostMapping("/register")
          public ResponseEntity<String> register(@Valid @RequestBody UserRegistrationDto registrationDto) {
              // Registration logic
              return ResponseEntity.ok("User registered successfully");
          }
      }
      ```

6. **`spring-boot-starter-web`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    ```

    - **Purpose**: Sets up Spring MVC for building web applications, including RESTful services.

    - **Transitive Dependencies**:
        - Spring MVC
        - Jackson (for JSON processing)
        - Embedded Tomcat server

    - **Example Usage**:

      Create a REST controller.

      ```java
      @RestController
      @RequestMapping("/api/books")
      public class BookController {
          @GetMapping
          public List<Book> getAllBooks() {
              // Fetch books from database
              return new ArrayList<>();
          }

          @PostMapping
          public Book createBook(@RequestBody Book book) {
              // Save book to database
              return book;
          }
      }
      ```

---

#### **2.4.2. Development Tools**

1. **`spring-boot-devtools`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    ```

    - **Purpose**: Provides development-time features like automatic restarts, live reload, and enhanced debugging.

    - **Transitive Dependencies**:
        - Spring Boot DevTools

    - **Configuration**:
        - **Scope**: Set to `runtime` to include it only during development.
        - **Optional**: Not included in the final build artifact.

    - **Example Usage**:

      Modify your code, and Spring Boot DevTools automatically restarts the application, reflecting changes without manual restarts.

2. **`lombok`**

    ```xml
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    ```

    - **Purpose**: Reduces boilerplate code by generating getters, setters, constructors, and more using annotations.

    - **Transitive Dependencies**:
        - Lombok

    - **Example Usage**:

      Use Lombok annotations in your entities and DTOs.

      ```java
      @Data
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
      }
      ```

      The `@Data` annotation generates getters, setters, `toString()`, `equals()`, and `hashCode()` methods.

    - **Note**: IDE support is required for Lombok annotations to be processed correctly. Ensure Lombok plugins are installed in your IDE (e.g., IntelliJ IDEA, Eclipse).

---

#### **2.4.3. JSON Web Tokens (JWT)**

JWT is used for secure token-based authentication.

1. **`jjwt-api`**

    ```xml
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.12.3</version>
    </dependency>
    ```

    - **Purpose**: Provides the API for creating and parsing JWTs.

2. **`jjwt-impl`**

    ```xml
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.12.6</version>
    </dependency>
    ```

    - **Purpose**: Provides the implementation of the JJWT API.

3. **`jjwt-jackson`**

    ```xml
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.12.5</version>
    </dependency>
    ```

    - **Purpose**: Adds Jackson support for JSON processing within JWTs.

    - **Version Note**: Ensure that the versions of `jjwt-api`, `jjwt-impl`, and `jjwt-jackson` are compatible. Ideally, align their patch versions (e.g., all `0.12.x`).

**Example Usage:**

Generate and validate JWT tokens.

```java
@Component
public class JwtUtil {

    private final String secretKey = "your-secret-key"; // Use a secure key and store it safely

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                   .setSubject(userDetails.getUsername())
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                   .signWith(SignatureAlgorithm.HS256, secretKey)
                   .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                   .setSigningKey(secretKey)
                   .parseClaimsJws(token)
                   .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
```

---

#### **2.4.4. API Documentation**

1. **`springdoc-openapi-starter-webmvc-ui`**

    ```xml
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.3.0</version>
    </dependency>
    ```

    - **Purpose**: Integrates SpringDoc OpenAPI with Spring MVC, automatically generating API documentation and providing a Swagger UI interface.

    - **Transitive Dependencies**:
        - SpringDoc OpenAPI
        - Swagger UI

    - **Example Usage**:

      Access API documentation at `http://localhost:8080/swagger-ui.html` after running the application.

      ```java
      // No additional configuration required for basic setup
      @RestController
      @RequestMapping("/api/books")
      public class BookController {
          // Controller methods
      }
      ```

      SpringDoc automatically scans your controllers and generates the OpenAPI specification.

---

#### **2.4.5. Testing Dependencies**

1. **`spring-boot-starter-test`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    ```

    - **Purpose**: Provides a comprehensive set of testing libraries, including JUnit 5, Mockito, Spring Test, AssertJ, and more.

    - **Transitive Dependencies**:
        - JUnit Jupiter (JUnit 5)
        - Mockito
        - Spring Test
        - AssertJ

    - **Example Usage**:

      Write unit tests for your services.

      ```java
      @SpringBootTest
      public class UserServiceTest {

          @Autowired
          private UserService userService;

          @MockBean
          private UserRepository userRepository;

          @Test
          public void testFindUserByUsername() {
              User mockUser = new User();
              mockUser.setUsername("john_doe");
              when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(mockUser));

              User user = userService.findByUsername("john_doe");
              assertNotNull(user);
              assertEquals("john_doe", user.getUsername());
          }
      }
      ```

2. **`spring-security-test`**

    ```xml
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
    ```

    - **Purpose**: Provides support for testing Spring Security features, including mock authentication.

    - **Transitive Dependencies**:
        - Spring Security Test utilities

    - **Example Usage**:

      Test secured endpoints with mock users.

      ```java
      @WebMvcTest(BookController.class)
      public class BookControllerTest {

          @Autowired
          private MockMvc mockMvc;

          @Test
          @WithMockUser(username = "admin", roles = {"ADMIN"})
          public void testGetBooksAsAdmin() throws Exception {
              mockMvc.perform(get("/api/books"))
                     .andExpect(status().isOk());
          }

          @Test
          @WithAnonymousUser
          public void testGetBooksAsAnonymous() throws Exception {
              mockMvc.perform(get("/api/books"))
                     .andExpect(status().isUnauthorized());
          }
      }
      ```

---

### **2.5. Build Configuration**

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <excludes>
                    <exclude>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                    </exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

#### **2.5.1. Spring Boot Maven Plugin**

- **Artifact**: `spring-boot-maven-plugin`

- **Purpose**: Facilitates packaging, running, and deploying Spring Boot applications. It can repackage existing JARs with dependencies, run the application, and more.

- **Key Features**:
    - **Repackaging**: Creates an executable JAR or WAR with embedded dependencies.
    - **Run**: Allows you to run the Spring Boot application directly using Maven commands.
    - **Build Information**: Adds build information to the JAR.

- **Configuration Explained**:

    ```xml
    <configuration>
        <excludes>
            <exclude>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
            </exclude>
        </excludes>
    </configuration>
    ```

    - **Excluding Lombok**: Lombok is a compile-time dependency that isn't needed at runtime. Excluding it from the repackaged JAR prevents unnecessary inclusion.

- **Example Usage**:

    - **Packaging the Application**:

        ```bash
        mvn clean package
        ```

      This command cleans the previous build artifacts and packages the application into an executable JAR located in the `target/` directory.

    - **Running the Application**:

        ```bash
        mvn spring-boot:run
        ```

      Starts the Spring Boot application without needing to build the JAR first.

    - **Running the Executable JAR**:

      After packaging, you can run the JAR directly:

        ```bash
        java -jar target/book-network-0.0.1-SNAPSHOT.jar
        ```

---

## **3. Putting It All Together: Example Workflow**

Let's walk through an example workflow using your `pom.xml` to build, run, and test the **book-network** application.

### **3.1. Building the Project**

1. **Compile the Code**:

    ```bash
    mvn compile
    ```

    - Maven compiles the Java source files in `src/main/java`.

2. **Package the Application**:

    ```bash
    mvn clean package
    ```

    - **`clean`**: Removes previous build artifacts.
    - **`package`**: Compiles the code and packages it into a JAR.

   **Result**: An executable JAR named `book-network-0.0.1-SNAPSHOT.jar` in the `target/` directory.

### **3.2. Running the Application**

1. **Using Maven**:

    ```bash
    mvn spring-boot:run
    ```

    - Starts the Spring Boot application.
    - Benefits from DevTools features like automatic restarts on code changes.

2. **Using the Executable JAR**:

    ```bash
    java -jar target/book-network-0.0.1-SNAPSHOT.jar
    ```

    - Runs the packaged application.
    - Suitable for production-like environments.

### **3.3. Testing the Application**

1. **Running Unit Tests**:

    ```bash
    mvn test
    ```

    - Executes tests located in `src/test/java`.
    - Utilizes `spring-boot-starter-test` and `spring-security-test` for comprehensive testing.

2. **Example Test Execution**:

   Suppose you have a `UserServiceTest` as previously shown. Running `mvn test` executes this test along with others, ensuring your services behave as expected.

---

## **4. Advanced Topics and Best Practices**

### **4.1. Dependency Management**

- **Avoid Version Conflicts**: By inheriting from `spring-boot-starter-parent`, many dependency versions are managed for you. Ensure any additional dependencies you add are compatible.

- **Using BOMs**: For managing versions across multiple dependencies, consider using a Bill of Materials (BOM). However, the Spring Boot parent often handles this.

### **4.2. Profiles**

Maven profiles allow you to customize builds for different environments (e.g., development, testing, production).

**Example Configuration:**

```xml
<profiles>
    <profile>
        <id>dev</id>
        <properties>
            <spring.profiles.active>dev</spring.profiles.active>
        </properties>
    </profile>
    <profile>
        <id>prod</id>
        <properties>
            <spring.profiles.active>prod</spring.profiles.active>
        </properties>
    </profile>
</profiles>
```

**Usage:**

```bash
mvn clean package -Pprod
```

Activates the `prod` profile during the build.

### **4.3. Plugin Management**

Beyond the Spring Boot Maven Plugin, you can add other plugins for tasks like code analysis, documentation, and more.

**Example: Adding Checkstyle for Code Quality**

```xml
<build>
    <plugins>
        <!-- Existing plugins -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-checkstyle-plugin</artifactId>
            <version>3.1.2</version>
            <configuration>
                <configLocation>checkstyle.xml</configLocation>
                <encoding>UTF-8</encoding>
                <consoleOutput>true</consoleOutput>
                <failsOnError>true</failsOnError>
            </configuration>
            <executions>
                <execution>
                    <phase>validate</phase>
                    <goals>
                        <goal>check</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

**Usage:**

```bash
mvn checkstyle:check
```

Ensures code adheres to defined style guidelines.

### **4.4. Spring Boot Configuration**

Leverage Spring Boot's externalized configuration features to manage settings via `application.properties` or `application.yml`.

**Example `application.properties`:**

```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/booknetwork
spring.datasource.username=root
spring.datasource.password=secret
spring.jpa.hibernate.ddl-auto=update

# Mail Configuration
spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=your-email@example.com
spring.mail.password=your-email-password

# JWT Configuration
jwt.secret=your-secret-key
jwt.expiration=3600000
```

**Example Usage:**

Inject properties into your Spring components.

```java
@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    // Getters and Setters
}
```

### **4.5. Lombok Best Practices**

- **Use Selective Annotations**: Instead of using `@Data` (which includes setters, getters, `equals`, `hashCode`, `toString`), use more specific annotations like `@Getter`, `@Setter`, or `@Builder` to avoid unintended method generation.

- **Immutable Objects**: Use Lombok's `@Value` for creating immutable classes.

**Example:**

```java
@Value
public class BookDto {
    Long id;
    String title;
    String author;
    String description;
}
```

This generates a final class with private final fields, getters, and a constructor, but no setters.

### **4.6. Security Considerations**

- **Secure JWT Secret**: Never hardcode secrets. Use environment variables or secure vaults.

- **HTTPS**: Ensure your application runs over HTTPS in production.

- **CSRF Protection**: Configure CSRF protection as needed, especially for state-changing operations.

**Example Security Configuration Enhancements:**

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable() // Enable CSRF protection in production
        .authorizeRequests()
        .antMatchers("/api/public/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
}
```

---

## **5. Sample Project Structure**

A typical Spring Boot project structure aligns with Maven's standard directory layout.

```
book-network/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── wchamara/
│   │   │           └── booknetwork/
│   │   │               ├── BookNetworkApplication.java
│   │   │               ├── controller/
│   │   │               │   └── BookController.java
│   │   │               ├── model/
│   │   │               │   └── Book.java
│   │   │               ├── repository/
│   │   │               │   └── BookRepository.java
│   │   │               ├── service/
│   │   │               │   └── BookService.java
│   │   │               └── config/
│   │   │                   └── SecurityConfig.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/
│   │           └── home.html
│   └── test/
│       └── java/
│           └── com/
│               └── wchamara/
│                   └── booknetwork/
│                       └── BookControllerTest.java
├── pom.xml
└── README.md
```

**Key Components:**

- **`BookNetworkApplication.java`**: The entry point of the Spring Boot application.

    ```java
    @SpringBootApplication
    public class BookNetworkApplication {
        public static void main(String[] args) {
            SpringApplication.run(BookNetworkApplication.class, args);
        }
    }
    ```

- **Controllers**: Handle HTTP requests.

    ```java
    @RestController
    @RequestMapping("/api/books")
    public class BookController {
        @Autowired
        private BookService bookService;

        @GetMapping
        public List<Book> getAllBooks() {
            return bookService.findAllBooks();
        }

        @PostMapping
        public Book addBook(@RequestBody Book book) {
            return bookService.saveBook(book);
        }
    }
    ```

- **Models**: Represent the data structure.

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

        // Lombok annotations for getters and setters
    }
    ```

- **Repositories**: Interface for database operations.

    ```java
    public interface BookRepository extends JpaRepository<Book, Long> {
        List<Book> findByAuthor(String author);
    }
    ```

- **Services**: Business logic.

    ```java
    @Service
    public class BookService {
        @Autowired
        private BookRepository bookRepository;

        public List<Book> findAllBooks() {
            return bookRepository.findAll();
        }

        public Book saveBook(Book book) {
            return bookRepository.save(book);
        }
    }
    ```

- **Configuration**: Security settings, etc.

    ```java
    @Configuration
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // Security configuration
        }
    }
    ```

- **Resources**: Static and template resources.

    - **`application.properties`**: Configuration properties.
    - **`templates/`**: Thymeleaf templates like `home.html`.

- **Tests**: Verify application behavior.

    ```java
    @SpringBootTest
    public class BookControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @Test
        public void testGetAllBooks() throws Exception {
            mockMvc.perform(get("/api/books"))
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$.length()").value(0));
        }
    }
    ```

---

## **6. Additional Recommendations**

### **6.1. Managing Secrets**

Never commit sensitive information (like JWT secrets, database passwords) to version control. Use environment variables or externalized configuration.

**Example: Using Environment Variables**

```properties
# application.properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
jwt.secret=${JWT_SECRET}
```

**Setting Environment Variables:**

On Unix-based systems:

```bash
export DB_URL=jdbc:mysql://localhost:3306/booknetwork
export DB_USERNAME=root
export DB_PASSWORD=secret
export JWT_SECRET=your-secret-key
```

### **6.2. Dockerization**

Containerize your application for consistent deployment environments.

**Dockerfile Example:**

```dockerfile
# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/book-network-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]
```

**Building and Running the Docker Image:**

```bash
# Build the Docker image
docker build -t book-network .

# Run the Docker container
docker run -p 8080:8080 book-network
```

### **6.3. Continuous Integration/Continuous Deployment (CI/CD)**

Automate your build, test, and deployment processes using CI/CD tools like GitHub Actions, GitLab CI, Jenkins, etc.

**Example: GitHub Actions Workflow (`.github/workflows/ci.yml`)**

```yaml
name: CI

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - name: Checkout Code
      uses: actions/checkout@v3

    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'adopt'

    - name: Build with Maven
      run: mvn clean package --no-transfer-progress

    - name: Run Tests
      run: mvn test --no-transfer-progress
```

This workflow triggers on pushes and pull requests to the `main` branch, checks out the code, sets up JDK 17, builds the project, and runs tests.

### **6.4. API Documentation and Testing**

Utilize SpringDoc OpenAPI to generate interactive API documentation and integrate API testing tools.

**Accessing Swagger UI:**

After running the application, navigate to `http://localhost:8080/swagger-ui.html` to view and interact with your API documentation.

**Example: Documenting an Endpoint**

```java
@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Operations pertaining to books in Book Network")
public class BookController {

    @Operation(summary = "Retrieve all books", description = "Returns a list of all books")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @Operation(summary = "Add a new book", description = "Creates a new book entry")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Book created successfully")
    })
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }
}
```

**Result**: These annotations enhance the generated OpenAPI documentation, providing clear descriptions and response information.

### **6.5. Exception Handling**

Implement global exception handling to manage errors gracefully.

**Example: Global Exception Handler**

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Additional exception handlers
}
```

**Usage**:

When validation fails or a resource isn't found, these handlers return structured error responses.

### **6.6. Logging**

Incorporate logging for monitoring and debugging.

**Example: Using SLF4J with Logback (default in Spring Boot)**

```java
@RestController
@RequestMapping("/api/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @GetMapping
    public List<Book> getAllBooks() {
        logger.info("Fetching all books");
        return bookService.findAllBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        logger.info("Adding new book: {}", book.getTitle());
        return bookService.saveBook(book);
    }
}
```

**Configuration**: Customize logging levels in `application.properties`.

```properties
logging.level.com.wchamara.booknetwork=DEBUG
```

---

## **7. Conclusion**

Your `pom.xml` is well-structured, leveraging Spring Boot's powerful features and best practices. Here's a recap of key points:

- **Parent POM**: Inherits configurations from Spring Boot, simplifying dependency management.
- **Dependencies**: Comprehensive set covering web, security, data access, validation, mail, JWT, documentation, and testing.
- **Build Plugins**: Spring Boot Maven Plugin facilitates building and running the application.
- **Best Practices**: Externalized configuration, testing, logging, exception handling, and documentation enhance the application's robustness and maintainability.

By understanding each component and how they interrelate, you can effectively manage and scale your **book-network** application. Continue adhering to best practices, keep dependencies updated, and utilize Spring Boot's extensive ecosystem to build a feature-rich social media platform for book sharing.

---

If you have specific areas you'd like to delve deeper into or additional questions about your project setup, feel free to ask!

# adding docker compose to the project

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
  mail-dev:
    container_name: mail-dev-bsn
    image: maildev/maildev
    ports:
      - "1080:1080"
      - "1025:1025"


networks:
  spring-demo:
    driver: bridge


volumes:
  postgres:
    driver: local


```

Certainly! The YAML snippet you provided is a **Docker Compose** configuration file designed to orchestrate and manage multiple Docker containers for your **Book Social Network** application. This setup includes a PostgreSQL database and a MailDev service for handling emails during development. Let's delve deep into each component of this `docker-compose.yml` file, understand its purpose, configuration options, and see how it integrates into your application with practical examples.

---

## **Table of Contents**

1. [Overview of Docker Compose](#1-overview-of-docker-compose)
2. [Detailed Breakdown of `docker-compose.yml`](#2-detailed-breakdown-of-docker-composeyml)
   - [2.1. Services](#21-services)
     - [2.1.1. PostgreSQL Service](#211-postgresql-service)
     - [2.1.2. MailDev Service](#212-maildev-service)
   - [2.2. Networks](#22-networks)
   - [2.3. Volumes](#23-volumes)
3. [Integration with Spring Boot Application](#3-integration-with-spring-boot-application)
4. [Running the Docker Compose Setup](#4-running-the-docker-compose-setup)
5. [Advanced Configurations and Best Practices](#5-advanced-configurations-and-best-practices)
6. [Conclusion](#6-conclusion)

---

## **1. Overview of Docker Compose**

**Docker Compose** is a tool for defining and managing multi-container Docker applications. With a simple YAML file (`docker-compose.yml`), you can configure your application's services, networks, and volumes. This simplifies the process of setting up, running, and scaling applications by allowing you to manage multiple containers with ease.

**Key Benefits:**

- **Simplified Configuration:** Define all services in a single file.
- **Reproducibility:** Ensure consistent environments across development, testing, and production.
- **Scalability:** Easily scale services up or down as needed.
- **Isolation:** Services run in separate containers, preventing conflicts.

In your case, the `docker-compose.yml` file sets up two primary services: a PostgreSQL database and a MailDev service for email testing.

---

## **2. Detailed Breakdown of `docker-compose.yml`**

Let's dissect each part of your `docker-compose.yml` to understand its configuration and role in your application.

### **2.1. Services**

The `services` section defines the different containers that make up your application. Each service represents a container that Docker will manage.

#### **2.1.1. PostgreSQL Service**

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

**Explanation:**

1. **Service Name (`postgres`):**
   - This is the identifier for the service within Docker Compose. You can reference this service by its name in other parts of the configuration or when linking services.

2. **Container Name (`postgres-sql-bsn`):**
   - Assigns a custom name to the container instead of the default naming convention (which typically combines the project name and service name). This can be helpful for clarity and management.

3. **Image (`postgres:16`):**
   - Specifies the Docker image to use for the container. Here, it uses PostgreSQL version 16 from Docker Hub.
   - **Example:**
     - Pulls the image from [Docker Hub](https://hub.docker.com/_/postgres).

4. **Environment Variables:**
   - **`POSTGRES_USER`:** Sets the default PostgreSQL user.
   - **`POSTGRES_PASSWORD`:** Sets the password for the PostgreSQL user.
   - **`PG_DATA`:** Specifies the directory where PostgreSQL will store its data. *Note:* The correct environment variable for data directory in PostgreSQL Docker images is `PGDATA` (without an underscore). Ensure consistency to prevent configuration issues.
   - **`POSTGRES_DB`:** Creates a default database named `book_social_network` upon initialization.
   - **Example Usage:**
     ```yaml
     environment:
       POSTGRES_USER: myuser
       POSTGRES_PASSWORD: mypassword
       PGDATA: /var/lib/postgresql/data
       POSTGRES_DB: mydatabase
     ```

5. **Volumes:**
   - **`- postgres:/data/postgres`:**
     - Mounts a Docker-managed volume named `postgres` to the container's `/data/postgres` directory.
     - **Purpose:** Persists PostgreSQL data outside the container, ensuring data isn't lost when the container stops or is removed.
   - **Example Usage:**
     ```yaml
     volumes:
       - postgres:/var/lib/postgresql/data
     ```

6. **Ports:**
   - **`- "5432:5432"`:**
     - Maps port **5432** on the host machine to port **5432** in the container.
     - **Purpose:** Allows your application (running on the host or other containers) to communicate with PostgreSQL via `localhost:5432`.
   - **Example Usage:**
     ```yaml
     ports:
       - "5432:5432"
     ```

7. **Networks:**
   - **`- spring-demo`:**
     - Connects the container to a Docker network named `spring-demo`.
     - **Purpose:** Facilitates communication between containers in the same network using service names as hostnames.
   - **Example Usage:**
     ```yaml
     networks:
       - my-network
     ```

8. **Restart Policy (`restart: unless-stopped`):**
   - **Purpose:** Automatically restarts the container unless it is explicitly stopped.
   - **Other Options:**
     - `no`: Do not automatically restart.
     - `on-failure`: Restart only if the container exits with a non-zero status.
     - `always`: Always restart the container if it stops.
   - **Example Usage:**
     ```yaml
     restart: always
     ```

**Practical Example: Connecting Your Spring Boot Application to PostgreSQL**

In your Spring Boot `application.properties` or `application.yml`, you'd configure the datasource to connect to PostgreSQL.

**`application.properties`:**
```properties
spring.datasource.url=jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

**Explanation:**
- **`jdbc:postgresql://postgres-sql-bsn:5432/book_social_network`:**
  - **`postgres-sql-bsn`** is the container name, acting as the hostname within the Docker network `spring-demo`.
  - **Port:** `5432` is the default PostgreSQL port.
  - **Database:** `book_social_network` is the database created via the `POSTGRES_DB` environment variable.

---

#### **Common Issues & Troubleshooting:**
- **Incorrect Environment Variable (`PG_DATA` vs. `PGDATA`):**
  - Ensure the correct variable is used. PostgreSQL Docker images expect `PGDATA` (all uppercase, no underscore).
  - **Fix:**
    ```yaml
    environment:
      PGDATA: /var/lib/postgresql/data
    ```

- **Port Conflicts:**
  - If port `5432` is already in use on the host, you can map to a different host port.
  - **Example:**
    ```yaml
    ports:
      - "15432:5432"
    ```
    - Access PostgreSQL via `localhost:15432`.

---

#### **Example Commands:**
- **Starting the PostgreSQL Service:**
  ```bash
  docker-compose up -d postgres
  ```
- **Accessing PostgreSQL CLI:**
  ```bash
  docker exec -it postgres-sql-bsn psql -U username -d book_social_network
  ```

---

#### **Additional Configuration Options:**
- **Health Checks:**
  - Ensure PostgreSQL is ready before your application starts.
  ```yaml
  healthcheck:
    test: ["CMD-SHELL", "pg_isready -U username"]
    interval: 10s
    timeout: 5s
    retries: 5
  ```
- **Environment Variable for Locale:**
  ```yaml
  environment:
    LANG: en_US.UTF-8
    LC_ALL: en_US.UTF-8
  ```

---

#### **Security Considerations:**
- **Secrets Management:**
  - Avoid hardcoding sensitive information like `POSTGRES_PASSWORD` in the `docker-compose.yml`.
  - **Use `.env` Files:**
    - Create a `.env` file:
      ```env
      POSTGRES_USER=username
      POSTGRES_PASSWORD=password
      POSTGRES_DB=book_social_network
      ```
    - Reference in `docker-compose.yml`:
      ```yaml
      environment:
        POSTGRES_USER: ${POSTGRES_USER}
        POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
        POSTGRES_DB: ${POSTGRES_DB}
      ```
    - **Note:** Ensure `.env` is added to `.gitignore` to prevent committing secrets.

---

#### **Full PostgreSQL Service Example with Enhancements:**
```yaml
services:
  postgres:
    container_name: postgres-sql-bsn
    image: postgres:16
    environment:
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
      PGDATA: /var/lib/postgresql/data
      POSTGRES_DB: ${POSTGRES_DB}
    volumes:
      - postgres:/var/lib/postgresql/data
    ports:
      - "5432:5432"
    networks:
      - spring-demo
    restart: unless-stopped
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U ${POSTGRES_USER}"]
      interval: 10s
      timeout: 5s
      retries: 5
```

---

#### **Further Reading:**
- [Official PostgreSQL Docker Image Documentation](https://hub.docker.com/_/postgres)
- [Docker Compose Networking](https://docs.docker.com/compose/networking/)
- [Managing Secrets in Docker Compose](https://docs.docker.com/compose/environment-variables/#the-env_file-attribute)

---

#### **2.1.2. MailDev Service**

```yaml
mail-dev:
  container_name: mail-dev-bsn
  image: maildev/maildev
  ports:
    - "1080:1080"
    - "1025:1025"
```

**Explanation:**

1. **Service Name (`mail-dev`):**
   - Identifies the MailDev service within Docker Compose.

2. **Container Name (`mail-dev-bsn`):**
   - Assigns a custom name to the MailDev container for clarity.

3. **Image (`maildev/maildev`):**
   - Uses the [MailDev](https://maildev.github.io/maildev/) Docker image.
   - **Purpose:** Provides a simple way to test email sending during development. It acts as a dummy SMTP server and web interface to view sent emails.

4. **Ports:**
   - **`- "1080:1080"`:**
     - Maps port **1080** on the host to port **1080** in the container.
     - **Purpose:** Access the MailDev web interface via `http://localhost:1080`.
   - **`- "1025:1025"`:**
     - Maps port **1025** on the host to port **1025** in the container.
     - **Purpose:** MailDev's SMTP server listens on port `1025`. Configure your application to send emails via `localhost:1025`.
   - **Example Usage:**
     - **Sending Email Configuration in Spring Boot:**
       ```properties
       spring.mail.host=localhost
       spring.mail.port=1025
       spring.mail.username=
       spring.mail.password=
       spring.mail.properties.mail.smtp.auth=false
       spring.mail.properties.mail.smtp.starttls.enable=false
       ```

5. **No Additional Configuration:**
   - MailDev is lightweight and typically doesn't require extensive configuration for development purposes.

**Practical Example: Testing Email Sending in Development**

Suppose your Spring Boot application sends a welcome email upon user registration. With MailDev configured, you can test this functionality without sending real emails.

**Steps:**

1. **Start the MailDev Service:**
   ```bash
   docker-compose up -d mail-dev
   ```

2. **Configure Spring Boot to Use MailDev SMTP:**
   **`application.properties`:**
   ```properties
   spring.mail.host=localhost
   spring.mail.port=1025
   spring.mail.username=
   spring.mail.password=
   spring.mail.properties.mail.smtp.auth=false
   spring.mail.properties.mail.smtp.starttls.enable=false
   ```

3. **Send an Email from Your Application:**
   ```java
   @Service
   public class EmailService {
       @Autowired
       private JavaMailSender mailSender;

       public void sendWelcomeEmail(String to, String username) {
           SimpleMailMessage message = new SimpleMailMessage();
           message.setTo(to);
           message.setSubject("Welcome to Book Network!");
           message.setText("Hello " + username + ", welcome to our platform.");
           mailSender.send(message);
       }
   }
   ```

4. **View Sent Emails:**
   - Navigate to [http://localhost:1080](http://localhost:1080) in your browser.
   - You'll see the sent welcome email listed there.

**Advantages of Using MailDev:**

- **No Real Emails Sent:** Ensures that testing doesn't spam real email addresses.
- **Immediate Feedback:** View sent emails in real-time via the web interface.
- **Lightweight:** Minimal resource usage and easy to set up.

---

#### **Common Issues & Troubleshooting:**

- **Port Conflicts:**
  - Ensure that ports `1080` and `1025` are free on your host machine.
  - **Solution:** Change host ports if conflicts occur.
    ```yaml
    ports:
      - "2080:1080" # Host port 2080 maps to container port 1080
      - "2025:1025" # Host port 2025 maps to container port 1025
    ```
  
- **Application Not Sending Emails:**
  - Verify SMTP configuration in `application.properties`.
  - Ensure that MailDev is running and accessible.
  - Check Docker container logs for MailDev:
    ```bash
    docker logs mail-dev-bsn
    ```

---

#### **Additional Configuration Options:**

- **Persistent Storage:**
  - By default, MailDev doesn't require persistent storage as it only holds emails in memory.
  - However, if you wish to persist emails across container restarts:
    ```yaml
    volumes:
      - maildev-data:/data
    ```
    **Note:** MailDev's default setup doesn't support data persistence, so this might require custom configurations or alternative tools.

- **Custom MailDev Configuration:**
  - Set environment variables to customize MailDev's behavior.
  ```yaml
  environment:
    - MAILDEV_INCOMING_USER=youruser
    - MAILDEV_INCOMING_PASS=yourpass
    - MAILDEV_SMTP_PORT=1025
    - MAILDEV_WEB_PORT=1080
  ```

---

### **2.2. Networks**

```yaml
networks:
  spring-demo:
    driver: bridge
```

**Explanation:**

1. **Network Name (`spring-demo`):**
   - Defines a custom Docker network named `spring-demo`.
   - **Purpose:** Facilitates communication between services. Containers on the same network can communicate using service names as DNS names.

2. **Driver (`bridge`):**
   - Specifies the network driver. `bridge` is the default driver for Docker networks, suitable for standalone containers.
   - **Other Drivers:**
     - `host`: Shares the host's networking namespace.
     - `overlay`: For multi-host networking.
     - `macvlan`: Assigns a MAC address to containers.
   - **Example Usage:**
     ```yaml
     networks:
       my-network:
         driver: bridge
     ```

**Practical Example: Service Communication**

With both `postgres` and `mail-dev` services connected to the `spring-demo` network, your Spring Boot application (if part of the same network) can communicate with them using their service names.

- **Database Connection URL:**
  - `jdbc:postgresql://postgres-sql-bsn:5432/book_social_network`

- **Mail Server Host:**
  - `localhost` (if the application runs on the host) or `mail-dev-bsn` (if the application runs within another container on the same network).

**Adding Your Application to the Network:**

If you have another service, say `app`, add it to the `spring-demo` network to enable inter-service communication.

```yaml
services:
  app:
    build: .
    ports:
      - "8080:8080"
    networks:
      - spring-demo
    depends_on:
      - postgres
      - mail-dev
```

**Benefits:**

- **Isolation:** Services are isolated from other Docker networks unless explicitly connected.
- **DNS Resolution:** Use service names to refer to containers, simplifying configuration.

---

### **2.3. Volumes**

```yaml
volumes:
  postgres:
    driver: local
```

**Explanation:**

1. **Volume Name (`postgres`):**
   - Defines a Docker-managed volume named `postgres`.
   - **Purpose:** Persists data generated by and used by Docker containers. In this case, it stores PostgreSQL data to ensure data persistence across container restarts or recreations.

2. **Driver (`local`):**
   - Specifies the volume driver. `local` is the default driver, storing data on the host filesystem.
   - **Other Drivers:**
     - `nfs`: Network File System.
     - `aws-ebs`: AWS Elastic Block Store.
     - Custom drivers for specialized storage solutions.
   - **Example Usage:**
     ```yaml
     volumes:
       my-data:
         driver: local
     ```

**Practical Example: Data Persistence**

Without volumes, any data stored inside a container would be lost when the container is removed. Volumes ensure that your PostgreSQL database retains its data even if the container is recreated or updated.

**Steps to Verify Data Persistence:**

1. **Start Services:**
   ```bash
   docker-compose up -d
   ```

2. **Create a Table and Insert Data:**
   ```sql
   CREATE TABLE books (
       id SERIAL PRIMARY KEY,
       title VARCHAR(255) NOT NULL,
       author VARCHAR(255) NOT NULL
   );

   INSERT INTO books (title, author) VALUES ('1984', 'George Orwell');
   ```

3. **Stop and Remove Containers:**
   ```bash
   docker-compose down
   ```

4. **Recreate Containers:**
   ```bash
   docker-compose up -d
   ```

5. **Verify Data Persistence:**
   - Connect to PostgreSQL and check if the `books` table and data still exist.

**Advantages of Using Volumes:**

- **Data Persistence:** Ensures critical data isn't lost.
- **Performance:** Volumes are optimized for I/O operations.
- **Isolation:** Data is stored separately from the container lifecycle.

---

#### **Additional Volume Configuration Options:**

- **Bind Mounts:**
  - Mount a specific host directory into the container.
  - **Example:**
    ```yaml
    volumes:
      - ./data/postgres:/var/lib/postgresql/data
    ```
  - **Use Case:** Access and modify database files directly from the host for advanced configurations or backups.

- **Named vs. Anonymous Volumes:**
  - **Named Volumes:** Have a specific name (e.g., `postgres`).
  - **Anonymous Volumes:** Do not have a specified name and are managed by Docker.
  - **Example:**
    ```yaml
    volumes:
      - /var/lib/postgresql/data
    ```

---

#### **Security Considerations:**

- **Access Permissions:**
  - Ensure that the mounted directories or volumes have appropriate permissions to prevent unauthorized access.
  - **Example:**
    ```bash
    chmod 700 ./data/postgres
    chown -R 999:999 ./data/postgres # Assuming PostgreSQL runs as UID 999
    ```

- **Backup Strategies:**
  - Regularly back up volume data to prevent data loss.
  - **Example:**
    ```bash
    docker run --rm -v postgres:/data/postgres -v $(pwd):/backup ubuntu tar cvf /backup/postgres_backup.tar /data/postgres
    ```

---

## **3. Integration with Spring Boot Application**

Your `docker-compose.yml` sets up the necessary infrastructure components—PostgreSQL and MailDev—for your Spring Boot application. Here's how to integrate these services into your Spring Boot project.

### **3.1. Database Configuration**

In your `application.properties` or `application.yml`, configure the datasource to connect to the PostgreSQL service.

**`application.properties`:**
```properties
spring.datasource.url=jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

**Explanation:**

- **`jdbc:postgresql://postgres-sql-bsn:5432/book_social_network`:**
  - **Host:** `postgres-sql-bsn` is the container name defined in Docker Compose. Docker's internal DNS resolves this to the PostgreSQL container's IP within the `spring-demo` network.
  - **Port:** `5432` is the default PostgreSQL port, mapped to the host's `5432`.
  - **Database:** `book_social_network` is the database name created via the `POSTGRES_DB` environment variable.

### **3.2. Mail Configuration**

Configure Spring Boot to use MailDev's SMTP server for sending emails during development.

**`application.properties`:**
```properties
spring.mail.host=localhost
spring.mail.port=1025
spring.mail.username=
spring.mail.password=
spring.mail.properties.mail.smtp.auth=false
spring.mail.properties.mail.smtp.starttls.enable=false
```

**Explanation:**

- **`spring.mail.host=localhost`:** Points to the SMTP server running on the host machine.
  - **Note:** If your Spring Boot application runs inside another Docker container on the same network, use the service name `mail-dev-bsn` instead of `localhost`.
  - **Example:**
    ```properties
    spring.mail.host=mail-dev-bsn
    ```
- **`spring.mail.port=1025`:** The SMTP port exposed by MailDev.
- **`spring.mail.username` & `spring.mail.password`:** Not required for MailDev.
- **`mail.smtp.auth=false` & `mail.smtp.starttls.enable=false`:** MailDev doesn't require authentication or TLS.

### **3.3. Application Startup Dependencies**

To ensure that your Spring Boot application starts only after PostgreSQL is ready, use the `depends_on` option in Docker Compose.

**Updated `docker-compose.yml`:**
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

**Explanation:**

- **Service `app`:**
  - Represents your Spring Boot application.
  - **Build:** Assumes a `Dockerfile` exists in the project root to build the application image.
  - **Environment Variables:** Overrides Spring Boot properties for datasource and mail configurations.
  - **Ports:** Maps container port `8080` to host port `8080`.
  - **Depends_on:** Ensures `postgres` and `mail-dev` services start before `app`.

**`Dockerfile` Example for Spring Boot Application:**

```dockerfile
# Use official OpenJDK image
FROM openjdk:17-jdk-alpine

# Set environment variables
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS=""

# Set working directory
WORKDIR /app

# Copy the executable JAR
COPY target/book-network-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
```

**Building and Running the Entire Stack:**

1. **Build the Spring Boot Application:**
   ```bash
   mvn clean package
   ```

2. **Build the Docker Image:**
   ```bash
   docker build -t book-network:latest .
   ```

3. **Start Services with Docker Compose:**
   ```bash
   docker-compose up -d
   ```

4. **Access the Application:**
   - **Spring Boot App:** [http://localhost:8080](http://localhost:8080)
   - **MailDev Interface:** [http://localhost:1080](http://localhost:1080)

---

## **4. Running the Docker Compose Setup**

Here's a step-by-step guide to running and managing your Docker Compose setup.

### **4.1. Prerequisites**

- **Docker:** Ensure Docker is installed on your machine. [Install Docker](https://docs.docker.com/get-docker/)
- **Docker Compose:** Typically included with Docker Desktop. Verify installation:
  ```bash
  docker-compose --version
  ```

### **4.2. Starting the Services**

1. **Navigate to the Project Directory:**
   ```bash
   cd path/to/your/project
   ```

2. **Start Services in Detached Mode:**
   ```bash
   docker-compose up -d
   ```

   - **Flags:**
     - `-d`: Runs containers in the background (detached mode).
   - **Output:**
     - Docker pulls the required images if not already present.
     - Containers `postgres-sql-bsn` and `mail-dev-bsn` are started.
     - (If `app` service is defined) Your Spring Boot application container starts and connects to PostgreSQL and MailDev.

3. **Verify Running Containers:**
   ```bash
   docker ps
   ```

   **Sample Output:**
   ```
   CONTAINER ID   IMAGE                      COMMAND                  CREATED          STATUS          PORTS                    NAMES
   abc123def456   postgres:16                "docker-entrypoint.s…"   10 minutes ago   Up 10 minutes   0.0.0.0:5432->5432/tcp   postgres-sql-bsn
   def456ghi789   maildev/maildev            "bin/maildev"            10 minutes ago   Up 10 minutes   0.0.0.0:1080->1080/tcp   mail-dev-bsn
   ```

4. **Check Logs (Optional):**
   ```bash
   docker-compose logs -f
   ```

   - **`-f`:** Follows the logs in real-time.
   - **Use Case:** Monitor application startup, database connections, or troubleshoot issues.

### **4.3. Stopping the Services**

```bash
docker-compose down
```

- **Effect:** Stops and removes containers, networks, and volumes (unless `volumes` are declared as external).

**Additional Flags:**

- **`--volumes` (`-v`):** Removes named volumes declared in the `volumes` section.
  ```bash
  docker-compose down -v
  ```

- **`--rmi` `all`:** Removes all images used by any service.
  ```bash
  docker-compose down --rmi all
  ```

- **`--remove-orphans`:** Removes containers for services not defined in the `docker-compose.yml`.
  ```bash
  docker-compose down --remove-orphans
  ```

### **4.4. Rebuilding Services**

If you make changes to your application code or Dockerfile, rebuild the affected services.

```bash
docker-compose up -d --build
```

- **`--build`:** Forces a rebuild of the images before starting containers.

### **4.5. Managing Services**

- **List All Services:**
  ```bash
  docker-compose ps
  ```

- **View Logs for a Specific Service:**
  ```bash
  docker-compose logs mail-dev
  ```

- **Restart a Service:**
  ```bash
  docker-compose restart postgres
  ```

---

## **5. Advanced Configurations and Best Practices**

To optimize your Docker Compose setup for development and potential production use, consider the following advanced configurations and best practices.

### **5.1. Environment Variables and `.env` Files**

**Using `.env` Files:**

- **Purpose:** Store environment variables separately from the `docker-compose.yml` file for better security and flexibility.
- **Create a `.env` File:**
  ```env
  POSTGRES_USER=username
  POSTGRES_PASSWORD=password
  POSTGRES_DB=book_social_network
  ```
- **Reference in `docker-compose.yml`:**
  ```yaml
  environment:
    POSTGRES_USER: ${POSTGRES_USER}
    POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
    POSTGRES_DB: ${POSTGRES_DB}
  ```
- **Benefits:**
  - Keeps sensitive information out of version control.
  - Facilitates different configurations for development, testing, and production.

**Example `.env` File:**
```env
POSTGRES_USER=bookuser
POSTGRES_PASSWORD=securepassword
POSTGRES_DB=book_social_network
MAILDEV_SMTP_PORT=1025
MAILDEV_WEB_PORT=1080
```

### **5.2. Health Checks**

**Implementing Health Checks:**

- **Purpose:** Ensure that services are healthy and ready to accept connections before dependent services start.
- **Example:**
  ```yaml
  services:
    postgres:
      ...
      healthcheck:
        test: ["CMD-SHELL", "pg_isready -U ${POSTGRES_USER}"]
        interval: 10s
        timeout: 5s
        retries: 5

    app:
      ...
      depends_on:
        postgres:
          condition: service_healthy
        mail-dev:
          condition: service_started
  ```
- **Explanation:**
  - **Postgres Health Check:** Uses `pg_isready` to check if PostgreSQL is ready to accept connections.
  - **Service Conditions:**
    - **`service_healthy`:** Waits until the health check passes.
    - **`service_started`:** Waits until the service starts (no health check).

**Note:** In Docker Compose version 3.4 and above, `depends_on` conditions are deprecated in favor of external health check tools or orchestration systems.

### **5.3. Using Docker Networks for Multiple Environments**

**Segregate Environments:**

- **Purpose:** Isolate different environments (e.g., development, testing, production) using separate Docker networks.
- **Example:**
  ```yaml
  networks:
    dev-network:
      driver: bridge
    prod-network:
      driver: bridge

  services:
    postgres:
      networks:
        - dev-network
    app:
      networks:
        - dev-network
  ```

**Benefits:**

- Prevents services from different environments from interfering with each other.
- Enhances security by restricting access within networks.

### **5.4. Volume Backups and Restoration**

**Backing Up Volumes:**

- **Command:**
  ```bash
  docker run --rm -v postgres:/data/postgres -v $(pwd):/backup ubuntu tar cvf /backup/postgres_backup.tar /data/postgres
  ```
- **Explanation:**
  - **Mount Volumes:**
    - **`-v postgres:/data/postgres`:** Mounts the `postgres` volume.
    - **`-v $(pwd):/backup`:** Mounts the current directory to `/backup` inside the container.
  - **Command:** Uses `tar` to archive the `/data/postgres` directory into `postgres_backup.tar` in the host's current directory.

**Restoring Volumes:**

- **Command:**
  ```bash
  docker run --rm -v postgres:/data/postgres -v $(pwd):/backup ubuntu bash -c "cd /data/postgres && tar xvf /backup/postgres_backup.tar --strip 1"
  ```

**Note:** Ensure that PostgreSQL is stopped or the volume is not in use during restoration to prevent data corruption.

### **5.5. Securing Services**

**Restricting Access to Services:**

- **Bind Services to Internal Networks:**
  - **Purpose:** Prevent external access to services that shouldn't be exposed (e.g., databases).
  - **Example:**
    ```yaml
    services:
      postgres:
        ports:
          - "5432:5432" # To allow host access
        # To restrict, remove or limit port mapping
    ```
    - **Internal Only:**
      ```yaml
      services:
        postgres:
          networks:
            - spring-demo
          # No ports mapping
      ```
    - **Access via Docker Network Only:**
      - The service is accessible only to other services on the same network, not from the host.

**Using Environment Variables for Sensitive Data:**

- **Avoid Hardcoding:**
  - Use environment variables or Docker secrets to manage sensitive information like passwords.

**Example: Using Secrets (Docker Swarm):**

1. **Create a Secret:**
   ```bash
   echo "securepassword" | docker secret create postgres_password -
   ```

2. **Reference in `docker-compose.yml`:**
   ```yaml
   services:
     postgres:
       image: postgres:16
       secrets:
         - postgres_password
       environment:
         POSTGRES_PASSWORD_FILE: /run/secrets/postgres_password
   secrets:
     postgres_password:
       external: true
   ```

**Note:** Docker secrets are more suited for Docker Swarm and not standalone Docker Compose setups.

### **5.6. Optimizing Dockerfile for Spring Boot Application**

**Example Optimized `Dockerfile`:**

```dockerfile
# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/book-network-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Explanation:**

- **Multi-Stage Build:**
  - **Stage 1 (Build):**
    - Uses Maven Docker image to build the application.
    - Copies `pom.xml` and `src` directory.
    - Runs `mvn clean package` to compile and package the application.
  - **Stage 2 (Run):**
    - Uses a lightweight OpenJDK image.
    - Copies the packaged JAR from the build stage.
    - Exposes port `8080`.
    - Sets the entry point to run the application.

**Benefits:**

- **Smaller Final Image:** Only the necessary JAR is included, reducing image size.
- **Separation of Concerns:** Build and runtime environments are isolated.
- **Security:** Fewer components in the runtime image reduce attack surfaces.

### **5.7. Health Checks for MailDev Service**

**Adding Health Checks:**

```yaml
services:
  mail-dev:
    container_name: mail-dev-bsn
    image: maildev/maildev
    ports:
      - "1080:1080"
      - "1025:1025"
    networks:
      - spring-demo
    restart: unless-stopped
    healthcheck:
      test: ["CMD", "curl", "-f", "http://localhost:1080"]
      interval: 30s
      timeout: 10s
      retries: 5
```

**Explanation:**

- **Health Check Command:**
  - Uses `curl` to make a request to the MailDev web interface.
  - **`-f`:** Fails silently on server errors (non-2xx responses).

- **Intervals:**
  - **`interval`:** Time between health checks (30 seconds).
  - **`timeout`:** Maximum time to wait for a response (10 seconds).
  - **`retries`:** Number of consecutive failures before marking the service as unhealthy.

**Use Case:**

Ensures that MailDev is up and running before dependent services (if any) start interacting with it.

---

### **5.8. Scaling Services**

**Scaling with Docker Compose:**

- **Command:**
  ```bash
  docker-compose up -d --scale app=3
  ```

- **Explanation:**
  - **`--scale app=3`:** Runs three instances of the `app` service.
  
- **Considerations:**
  - Ensure that your application can handle multiple instances, especially concerning shared resources like databases.
  - Load balancing might be necessary if scaling web services.

**Example: Scaling a Web Service**

```yaml
services:
  app:
    build: .
    ports:
      - "8080:8080"
    networks:
      - spring-demo
    depends_on:
      - postgres
      - mail-dev
    restart: unless-stopped
```

- **Scaling:**
  ```bash
  docker-compose up -d --scale app=3
  ```

- **Result:**
  - Three instances of `app` are running, accessible via port `8080` on the host. To avoid port conflicts, consider using a reverse proxy or load balancer like Nginx.

---

### **5.9. Using a Reverse Proxy (e.g., Nginx) for Load Balancing**

**Adding Nginx to `docker-compose.yml`:**

```yaml
services:
  nginx:
    image: nginx:latest
    container_name: nginx-bsn
    ports:
      - "80:80"
    volumes:
      - ./nginx.conf:/etc/nginx/nginx.conf
    networks:
      - spring-demo
    depends_on:
      - app
    restart: unless-stopped

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
    deploy:
      replicas: 3
```

**`nginx.conf` Example:**

```nginx
events { }

http {
    upstream app_servers {
        server book-network-app:8080;
        server book-network-app:8080;
        server book-network-app:8080;
    }

    server {
        listen 80;

        location / {
            proxy_pass http://app_servers;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
        }
    }
}
```

**Explanation:**

- **Nginx Service:**
  - Acts as a reverse proxy and load balancer for the `app` service.
  - **Volumes:**
    - Mounts a custom `nginx.conf` to configure Nginx.
  - **Ports:**
    - Maps host port `80` to container port `80`.

- **App Service:**
  - **Replicas:**
    - Defines three instances (`replicas: 3`) of the application.

- **Nginx Configuration:**
  - **`upstream app_servers`:**
    - Lists multiple `app` service instances. However, note that in Docker Compose, service names are unique. For multiple replicas, Docker assigns unique container names with suffixes.
  - **Dynamic Discovery:**
    - To correctly reference multiple instances, use Docker's DNS and service discovery features or use a different naming strategy.
    - **Example Update:**
      ```nginx
      upstream app_servers {
          server app:8080;
      }
      ```
      - Docker's internal load balancing ensures traffic is distributed across replicas.

**Note:** Managing multiple replicas and service discovery can be more seamlessly handled using orchestration tools like Kubernetes. Docker Compose is primarily suited for development and simple deployments.

---

### **5.10. Environment-Specific Configurations**

**Using Multiple Compose Files:**

- **Purpose:** Define environment-specific settings (e.g., development, production) by extending the base `docker-compose.yml` with additional configurations.
  
- **Example:**
  - **`docker-compose.prod.yml`:**
    ```yaml
    services:
      app:
        environment:
          SPRING_PROFILES_ACTIVE: prod
        deploy:
          replicas: 5
          resources:
            limits:
              cpus: "0.5"
              memory: "512M"
    ```
  
- **Running with Multiple Files:**
  ```bash
  docker-compose -f docker-compose.yml -f docker-compose.prod.yml up -d
  ```

**Benefits:**

- **Flexibility:** Customize services for different environments without duplicating configurations.
- **Maintainability:** Keep base configurations separate from environment-specific overrides.

---

### **5.11. Monitoring and Logging**

**Centralized Logging:**

- **Use Docker Logging Drivers:** Forward logs to centralized systems like ELK Stack, Fluentd, or Splunk.
- **Example: Forwarding to a File:**
  ```yaml
  services:
    app:
      logging:
        driver: "json-file"
        options:
          max-size: "10m"
          max-file: "3"
  ```

**Monitoring Containers:**

- **Tools:**
  - **Portainer:** Web-based Docker management UI.
  - **Prometheus & Grafana:** For metrics collection and visualization.
  - **cAdvisor:** Collects container metrics.

**Example: Adding cAdvisor for Monitoring:**
```yaml
services:
  cadvisor:
    image: google/cadvisor:latest
    container_name: cadvisor
    ports:
      - "8081:8080"
    volumes:
      - /:/rootfs:ro
      - /var/run:/var/run:rw
      - /sys:/sys:ro
      - /var/lib/docker/:/var/lib/docker:ro
    networks:
      - spring-demo
    restart: unless-stopped
```

**Accessing Metrics:**
- Navigate to [http://localhost:8081](http://localhost:8081) to view container metrics.

---

### **5.12. Handling Data Migrations**

**Using Flyway or Liquibase:**

- **Purpose:** Automate database schema migrations.
- **Integration with Spring Boot:**
  - **Flyway:** Integrates seamlessly with Spring Boot.
  - **Example Configuration:**
    ```properties
    spring.flyway.enabled=true
    spring.flyway.locations=classpath:db/migration
    ```
  - **Migration Scripts:**
    - Place SQL scripts in `src/main/resources/db/migration`.
    - **Example:** `V1__Create_users_table.sql`
      ```sql
      CREATE TABLE users (
          id SERIAL PRIMARY KEY,
          username VARCHAR(50) NOT NULL UNIQUE,
          email VARCHAR(100) NOT NULL UNIQUE,
          password VARCHAR(255) NOT NULL
      );
      ```

**Benefits:**

- **Version Control:** Track and manage database changes over time.
- **Automation:** Automatically apply migrations during application startup.

---

### **5.13. Using Docker Secrets for Sensitive Data (Advanced)**

**Note:** Docker Secrets are more suited for Docker Swarm and not standard Docker Compose. For standalone Compose, environment variables or external secret management tools are recommended.

**Example with Docker Swarm:**

1. **Initialize Swarm:**
   ```bash
   docker swarm init
   ```

2. **Create a Secret:**
   ```bash
   echo "securepassword" | docker secret create postgres_password -
   ```

3. **Reference the Secret in `docker-compose.yml`:**
   ```yaml
   services:
     postgres:
       image: postgres:16
       secrets:
         - postgres_password
       environment:
         POSTGRES_PASSWORD_FILE: /run/secrets/postgres_password
       volumes:
         - postgres:/var/lib/postgresql/data
       networks:
         - spring-demo
       restart: unless-stopped
       deploy:
         replicas: 1

   secrets:
     postgres_password:
       external: true
   ```

**Explanation:**

- **Secrets:**
  - Stored securely by Docker.
  - Mounted as files inside containers, accessed via file paths.

- **Environment Variable:**
  - **`POSTGRES_PASSWORD_FILE`:** Points PostgreSQL to read the password from the mounted secret file.

---

### **5.14. Optimizing Docker Images for Faster Builds**

**Caching Dependencies:**

- **Leverage Docker Layer Caching:**
  - Structure your `Dockerfile` to cache dependencies separately from application code.
  - **Example:**
    ```dockerfile
    # Stage 1: Build
    FROM maven:3.8.4-openjdk-17 AS build
    WORKDIR /app
    COPY pom.xml .
    RUN mvn dependency:go-offline
    COPY src ./src
    RUN mvn clean package -DskipTests

    # Stage 2: Run
    FROM openjdk:17-jdk-alpine
    WORKDIR /app
    COPY --from=build /app/target/book-network-0.0.1-SNAPSHOT.jar app.jar
    EXPOSE 8080
    ENTRYPOINT ["java", "-jar", "app.jar"]
    ```
  - **Explanation:**
    - **`RUN mvn dependency:go-offline`:** Downloads all dependencies in advance.
    - **Cache Benefit:** If only application code changes, Docker can reuse the cached dependencies layer, speeding up builds.

**Minimizing Image Size:**

- **Use Multi-Stage Builds:** As shown above, separate build and runtime environments.
- **Choose Minimal Base Images:** Use lightweight images like `alpine` variants.
- **Remove Unnecessary Files:** Exclude development tools or temporary files from the final image.

---

### **5.15. Handling Service Dependencies**

**Ensuring Services Start in Order:**

- **`depends_on`:** Ensures that a service starts after its dependencies. However, it doesn't wait for the dependencies to be "ready".
- **Solution:** Use health checks to wait for dependencies to be healthy before starting dependent services.

**Example: Using Wait-for-it Script:**

1. **Add `wait-for-it.sh` to Your Project:**
   - [wait-for-it](https://github.com/vishnubob/wait-for-it)

2. **Modify Dockerfile:**
   ```dockerfile
   COPY wait-for-it.sh /wait-for-it.sh
   RUN chmod +x /wait-for-it.sh
   ```

3. **Update `docker-compose.yml`:**
   ```yaml
   services:
     app:
       ...
       entrypoint: ["./wait-for-it.sh", "postgres-sql-bsn:5432", "--", "java", "-jar", "app.jar"]
   ```

**Explanation:**

- **`wait-for-it.sh`:** Waits for a service (e.g., PostgreSQL) to be ready before executing the application.

---

### **5.16. Scaling and Load Balancing**

For production-grade deployments, consider integrating load balancers and scaling services appropriately. Docker Compose is limited in this aspect, so orchestration tools like **Kubernetes** or **Docker Swarm** are recommended for advanced scaling and load balancing.

**Example: Using Kubernetes with Docker Compose:**

- **Convert `docker-compose.yml` to Kubernetes Manifests:**
  - Use tools like **Kompose** to automate the conversion.
    ```bash
    kompose convert
    ```
  
- **Deploy to Kubernetes Cluster:**
  ```bash
  kubectl apply -f postgres-deployment.yaml
  kubectl apply -f maildev-deployment.yaml
  kubectl apply -f app-deployment.yaml
  kubectl apply -f nginx-deployment.yaml
    ```

---

## **3. Integration with Spring Boot Application**

Your Docker Compose setup includes services that your Spring Boot application will interact with: PostgreSQL for data storage and MailDev for email handling during development. Here's how to integrate these services seamlessly.

### **3.1. Configuring Spring Boot to Connect to PostgreSQL**

Ensure your Spring Boot application is configured to connect to the PostgreSQL database defined in your `docker-compose.yml`.

**`application.properties`:**
```properties
spring.datasource.url=jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

**Explanation:**

- **`jdbc:postgresql://postgres-sql-bsn:5432/book_social_network`:**
  - **Host:** `postgres-sql-bsn` is the container name specified in Docker Compose, acting as the hostname within the `spring-demo` network.
  - **Port:** `5432` is the PostgreSQL port mapped to the host.
  - **Database:** `book_social_network` is the database created via the `POSTGRES_DB` environment variable.

**Spring Data JPA Configuration:**

- **`spring.jpa.hibernate.ddl-auto=update`:**
  - Automatically updates the database schema based on your JPA entities.
  - **Note:** For production environments, consider using `validate` or `none` to prevent unintended schema changes.

### **3.2. Configuring Spring Boot to Use MailDev for Email Testing**

Configure your application to send emails via MailDev's SMTP server during development.

**`application.properties`:**
```properties
spring.mail.host=localhost
spring.mail.port=1025
spring.mail.username=
spring.mail.password=
spring.mail.properties.mail.smtp.auth=false
spring.mail.properties.mail.smtp.starttls.enable=false
```

**Explanation:**

- **`spring.mail.host=localhost`:** Points to the SMTP server running on the host machine. If your Spring Boot app runs inside another container on the same network, use `mail-dev-bsn`.
  - **Example for Containerized App:**
    ```properties
    spring.mail.host=mail-dev-bsn
    ```
- **`spring.mail.port=1025`:** SMTP port exposed by MailDev.
- **`spring.mail.username` & `spring.mail.password`:** Not required for MailDev.
- **`mail.smtp.auth=false` & `mail.smtp.starttls.enable=false`:** MailDev doesn't require authentication or TLS.

**Sending Emails in Spring Boot:**

**Email Service Example:**
```java
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendWelcomeEmail(String to, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Welcome to Book Network!");
        message.setText("Hello " + username + ", welcome to our platform.");
        mailSender.send(message);
    }
}
```

**Controller Usage:**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        // Registration logic
        emailService.sendWelcomeEmail(userDto.getEmail(), userDto.getUsername());
        return ResponseEntity.ok("User registered successfully");
    }
}
```

**Viewing Sent Emails:**

- Navigate to [http://localhost:1080](http://localhost:1080) to access the MailDev web interface and view sent emails.

**Advantages:**

- **No Real Emails Sent:** Prevents accidental spamming during development.
- **Immediate Feedback:** View and debug emails directly via the MailDev UI.

---

## **4. Running the Docker Compose Setup**

To effectively utilize your `docker-compose.yml`, follow these steps to build, run, and manage your Dockerized environment.

### **4.1. Prerequisites**

- **Docker Installed:** Ensure Docker is installed on your machine. [Download Docker](https://www.docker.com/get-started)
- **Docker Compose Installed:** Typically included with Docker Desktop. Verify installation:
  ```bash
  docker-compose --version
  ```

### **4.2. Building and Running Services**

1. **Navigate to Your Project Directory:**
   ```bash
   cd path/to/your/project
   ```

2. **Start Services in Detached Mode:**
   ```bash
   docker-compose up -d
   ```

   - **`-d`:** Runs containers in the background (detached mode).

3. **Verify Running Containers:**
   ```bash
   docker-compose ps
   ```

   **Sample Output:**
   ```
   Name                  Command                  State           Ports
   -----------------------------------------------------------------------------------
   mail-dev-bsn          "bin/maildev"            Up      0.0.0.0:1080->1080/tcp, 0.0.0.0:1025->1025/tcp
   postgres-sql-bsn     "docker-entrypoint.s…"   Up      0.0.0.0:5432->5432/tcp
   ```

4. **View Logs (Optional):**
   ```bash
   docker-compose logs -f
   ```

   - **`-f`:** Follows the logs in real-time.
   - **Use Case:** Monitor service startups, application logs, or troubleshoot issues.

### **4.3. Stopping and Removing Services**

1. **Stop Services:**
   ```bash
   docker-compose stop
   ```

2. **Remove Containers, Networks, and Volumes:**
   ```bash
   docker-compose down
   ```

   - **Flags:**
     - `-v`: Removes named volumes declared in the `volumes` section.
     - `--rmi all`: Removes all images used by any service.
     - `--remove-orphans`: Removes containers for services not defined in the `docker-compose.yml`.

   **Example:**
   ```bash
   docker-compose down -v --rmi all --remove-orphans
   ```

### **4.4. Rebuilding Services**

If you make changes to your application code or Dockerfile, rebuild the affected services.

```bash
docker-compose up -d --build
```

- **`--build`:** Forces a rebuild of the images before starting containers.

### **4.5. Scaling Services**

**Example: Scaling the Application Service**

If your `docker-compose.yml` includes an `app` service (Spring Boot application), you can scale it.

```bash
docker-compose up -d --scale app=3
```

**Explanation:**

- **`--scale app=3`:** Runs three instances of the `app` service.
- **Note:** Ensure that your application can handle multiple instances, especially regarding shared resources like databases.

**Accessing Scaled Services:**

- **Load Balancing:** Use a reverse proxy (e.g., Nginx) to distribute traffic across multiple `app` instances.
- **Service Discovery:** Utilize Docker's internal DNS to reference services by name.

---

## **5. Advanced Configurations and Best Practices**

To enhance the robustness, security, and maintainability of your Docker Compose setup, consider implementing the following advanced configurations and best practices.

### **5.1. Using `.env` Files for Environment Variables**

**Purpose:** Store environment-specific variables outside the `docker-compose.yml` to keep configurations flexible and secure.

**Steps:**

1. **Create a `.env` File in the Project Root:**
   ```env
   POSTGRES_USER=username
   POSTGRES_PASSWORD=password
   POSTGRES_DB=book_social_network
   MAILDEV_SMTP_PORT=1025
   MAILDEV_WEB_PORT=1080
   ```

2. **Reference Variables in `docker-compose.yml`:**
   ```yaml
   environment:
     POSTGRES_USER: ${POSTGRES_USER}
     POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
     POSTGRES_DB: ${POSTGRES_DB}
   ```

3. **Security Consideration:**
   - **Add `.env` to `.gitignore`:**
     ```gitignore
     # .gitignore
     .env
     ```
   - **Purpose:** Prevents sensitive information from being committed to version control.

**Advantages:**

- **Flexibility:** Easily switch configurations for different environments (development, testing, production).
- **Security:** Keeps sensitive data out of the source code.

---

### **5.2. Implementing Health Checks**

**Purpose:** Ensure that services are up and running correctly before dependent services start.

**Example: Health Check for PostgreSQL**

```yaml
services:
  postgres:
    ...
    healthcheck:
      test: ["CMD", "pg_isready", "-U", "username"]
      interval: 10s
      timeout: 5s
      retries: 5
```

**Explanation:**

- **`pg_isready`:** Utility to check PostgreSQL server readiness.
- **`interval`:** Time between each health check attempt.
- **`timeout`:** Maximum time to wait for a response.
- **`retries`:** Number of consecutive failures before marking the service as unhealthy.

**Dependent Service Waits:**

While `depends_on` ensures container startup order, it doesn't wait for health checks to pass. For more granular control, use scripts like `wait-for-it` or orchestration tools that support health-based dependencies.

---

### **5.3. Securing Sensitive Information**

**Avoid Hardcoding Secrets:**

- **Use Environment Variables:** As shown with `.env` files.
- **Docker Secrets:** Suitable for Docker Swarm environments.

**Example: Using Docker Secrets (Swarm Mode):**

1. **Create a Secret:**
   ```bash
   echo "supersecretpassword" | docker secret create postgres_password -
   ```

2. **Reference in `docker-compose.yml`:**
   ```yaml
   services:
     postgres:
       ...
       secrets:
         - postgres_password
       environment:
         POSTGRES_PASSWORD_FILE: /run/secrets/postgres_password
   secrets:
     postgres_password:
       external: true
   ```

**Explanation:**

- **`postgres_password`:** Secret stored securely by Docker.
- **`POSTGRES_PASSWORD_FILE`:** Environment variable pointing to the secret file inside the container.

**Note:** Docker secrets are primarily designed for Docker Swarm and not for standalone Compose.

---

### **5.4. Implementing Logging and Monitoring**

**Centralized Logging:**

- **Use Logging Drivers:**
  - **Example:** Forward logs to a centralized logging system like ELK Stack or Fluentd.
    ```yaml
    services:
      app:
        ...
        logging:
          driver: "json-file"
          options:
            max-size: "10m"
            max-file: "3"
    ```

**Monitoring Services:**

- **Use Tools like cAdvisor or Prometheus:**
  - **Example: Adding cAdvisor to Monitor Containers:**
    ```yaml
    services:
      cadvisor:
        image: google/cadvisor:latest
        container_name: cadvisor-bsn
        ports:
          - "8081:8080"
        volumes:
          - /:/rootfs:ro
          - /var/run:/var/run:rw
          - /sys:/sys:ro
          - /var/lib/docker/:/var/lib/docker:ro
        networks:
          - spring-demo
        restart: unless-stopped
    ```
  - **Access Metrics:** Navigate to [http://localhost:8081](http://localhost:8081)

**Advantages:**

- **Proactive Monitoring:** Detect and respond to issues promptly.
- **Centralized Logs:** Simplifies debugging and auditing.

---

### **5.5. Automating Backups**

**Database Backups:**

1. **Create a Backup Script:**
   ```bash
   # backup.sh
   #!/bin/bash
   docker exec postgres-sql-bsn pg_dump -U username book_social_network > backup.sql
   echo "Backup completed successfully."
   ```

2. **Make the Script Executable:**
   ```bash
   chmod +x backup.sh
   ```

3. **Run the Backup:**
   ```bash
   ./backup.sh
   ```

**Explanation:**

- **`pg_dump`:** Utility to back up PostgreSQL databases.
- **Output:** `backup.sql` contains SQL commands to recreate the database.

**Automate with Cron Jobs:**

- **Example: Daily Backups at Midnight**
  ```bash
  crontab -e
  ```
  Add the following line:
  ```cron
  0 0 * * * /path/to/backup.sh
  ```

**Advantages:**

- **Data Safety:** Protects against data loss.
- **Recovery:** Enables restoring the database to a previous state.

---

### **5.6. Optimizing Docker Images**

**Use Multi-Stage Builds:**

- **Purpose:** Reduce final image size by separating build and runtime environments.
- **Example `Dockerfile`:**
  ```dockerfile
  # Stage 1: Build
  FROM maven:3.8.4-openjdk-17 AS build
  WORKDIR /app
  COPY pom.xml .
  RUN mvn dependency:go-offline
  COPY src ./src
  RUN mvn clean package -DskipTests

  # Stage 2: Run
  FROM openjdk:17-jdk-alpine
  WORKDIR /app
  COPY --from=build /app/target/book-network-0.0.1-SNAPSHOT.jar app.jar
  EXPOSE 8080
  ENTRYPOINT ["java", "-jar", "app.jar"]
  ```

**Explanation:**

- **Stage 1 (Build):**
  - Uses a Maven image to compile and package the application.
  - **`mvn dependency:go-offline`:** Downloads all dependencies, leveraging Docker's caching to speed up subsequent builds.
  
- **Stage 2 (Run):**
  - Uses a lightweight OpenJDK Alpine image.
  - Copies the packaged JAR from the build stage.
  - **`ENTRYPOINT`:** Defines the command to run the application.

**Benefits:**

- **Smaller Image Size:** Excludes build tools and source code from the final image.
- **Security:** Reduces the attack surface by minimizing installed packages.
- **Faster Deployment:** Smaller images are quicker to transfer and start.

---

### **5.7. Using Docker Compose Overrides for Development**

**Purpose:** Customize configurations for different environments without altering the base `docker-compose.yml`.

**Example: `docker-compose.override.yml`:**

```yaml
services:
  postgres:
    environment:
      POSTGRES_PASSWORD: devpassword
    volumes:
      - ./data/postgres:/var/lib/postgresql/data
    ports:
      - "5432:5432"

  mail-dev:
    ports:
      - "1080:1080"
      - "1025:1025"

  app:
    environment:
      SPRING_PROFILES_ACTIVE: dev
    ports:
      - "8080:8080"
    volumes:
      - ./logs:/app/logs
```

**Explanation:**

- **`docker-compose.override.yml`:**
  - Automatically applied when running `docker-compose up`.
  - **Customizations:**
    - **PostgreSQL:**
      - Overrides `POSTGRES_PASSWORD` for development.
      - Mounts a host directory for data persistence.
    - **App:**
      - Activates the `dev` profile.
      - Mounts a host directory for log files.

**Usage:**

- **Run with Overrides:**
  ```bash
  docker-compose up -d
  ```
  - Docker Compose automatically merges `docker-compose.yml` and `docker-compose.override.yml`.

**Benefits:**

- **Separation of Concerns:** Keeps base configurations clean and environment-specific customizations separate.
- **Flexibility:** Easily switch configurations by using different override files.

---

### **5.8. Leveraging Docker Compose Commands**

**Common Commands:**

- **Start Services:**
  ```bash
  docker-compose up -d
  ```
- **Stop Services:**
  ```bash
  docker-compose down
  ```
- **Rebuild Services:**
  ```bash
  docker-compose up -d --build
  ```
- **View Logs:**
  ```bash
  docker-compose logs -f
  ```
- **Scale Services:**
  ```bash
  docker-compose up -d --scale app=3
  ```
- **List Services:**
  ```bash
  docker-compose ps
  ```

**Tips:**

- **Use `-d` for Detached Mode:** Prevents logs from cluttering the terminal.
- **Combine Commands for Efficiency:**
  - **Example:** Rebuild and start services in one command.
    ```bash
    docker-compose up -d --build
    ```

---

### **5.9. Managing Data with Docker Volumes**

**Listing Docker Volumes:**
```bash
docker volume ls
```

**Inspecting a Volume:**
```bash
docker volume inspect postgres
```

**Removing Unused Volumes:**
```bash
docker volume prune
```

**Note:** Be cautious when removing volumes as it deletes all data stored within them.

---

### **5.10. Testing the Setup**

**Verify PostgreSQL Connection:**

1. **Connect via psql:**
   ```bash
   docker exec -it postgres-sql-bsn psql -U username -d book_social_network
   ```
2. **Run SQL Commands:**
   ```sql
   SELECT NOW();
   ```

**Verify MailDev Functionality:**

1. **Send a Test Email from Application:**
   - Trigger an email sending action in your application.
2. **Check MailDev UI:**
   - Navigate to [http://localhost:1080](http://localhost:1080) to view the sent email.

---

### **5.11. Optimizing Docker Compose for Production**

While the provided `docker-compose.yml` is suitable for development, production environments often require additional considerations:

- **Use Docker Swarm or Kubernetes:** For orchestration, scaling, and advanced networking.
- **Secure Network Configurations:** Limit network access to essential services only.
- **Implement TLS:** Secure communication between services and external clients.
- **Resource Limits:** Define CPU and memory limits to prevent resource exhaustion.
  ```yaml
  services:
    app:
      deploy:
        resources:
          limits:
            cpus: "1.0"
            memory: "512M"
  ```
- **Monitoring and Alerting:** Integrate with monitoring tools to track application health and performance.

---

### **5.12. Using External Services and Integrations**

**Example: Integrating Redis for Caching**

1. **Add Redis Service:**
   ```yaml
   services:
     redis:
       image: redis:7
       container_name: redis-bsn
       ports:
         - "6379:6379"
       networks:
         - spring-demo
       restart: unless-stopped
   ```

2. **Configure Spring Boot to Use Redis:**
   **`application.properties`:**
   ```properties
   spring.cache.type=redis
   spring.redis.host=redis-bsn
   spring.redis.port=6379
   ```

3. **Enable Caching in Spring Boot:**
   ```java
   @EnableCaching
   @SpringBootApplication
   public class BookNetworkApplication {
       public static void main(String[] args) {
           SpringApplication.run(BookNetworkApplication.class, args);
       }
   }
   ```

**Advantages:**

- **Performance:** Caching improves application responsiveness.
- **Scalability:** Redis can handle high-throughput scenarios.

---

## **6. Conclusion**

Your `docker-compose.yml` serves as the backbone for managing essential services required by your **Book Social Network** application. By leveraging Docker Compose, you've streamlined the setup process, ensuring that services like PostgreSQL and MailDev are consistently configured and easily manageable. Here's a recap of what we've covered:

- **Services:** Defined PostgreSQL and MailDev with configurations for environment variables, volumes, ports, networks, and restart policies.
- **Networks:** Created a bridge network (`spring-demo`) to facilitate communication between services.
- **Volumes:** Established persistent storage for PostgreSQL data to ensure data durability.
- **Integration:** Illustrated how to configure your Spring Boot application to interact with these services.
- **Advanced Configurations:** Explored best practices for environment variables, health checks, security, logging, backups, and more.

**Next Steps:**

1. **Implement Continuous Integration/Continuous Deployment (CI/CD):** Automate your build, test, and deployment processes using tools like GitHub Actions, Jenkins, or GitLab CI.

2. **Enhance Security:** Secure sensitive data, enforce network policies, and implement TLS for encrypted communications.

3. **Optimize Performance:** Monitor application performance, scale services as needed, and implement caching strategies.

4. **Expand Infrastructure:** Integrate additional services (e.g., Redis, Elasticsearch) to enhance application features and capabilities.

5. **Documentation:** Maintain comprehensive documentation for your Docker setup to facilitate onboarding and collaboration.

By adhering to these practices and continuously refining your Docker Compose configurations, you'll ensure a robust, scalable, and maintainable environment for your social media application focused on book sharing.

---

**Additional Resources:**

- [Docker Compose Official Documentation](https://docs.docker.com/compose/)
- [Spring Boot and Docker Guide](https://spring.io/guides/topicals/spring-boot-docker/)
- [MailDev GitHub Repository](https://github.com/maildev/maildev)
- [PostgreSQL Docker Image Documentation](https://hub.docker.com/_/postgres)

Feel free to reach out if you have further questions or need assistance with specific configurations!

# application.yml files

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

```