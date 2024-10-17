# 📗Let's Implement the Email Service

```java
package com.wchamara.book.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendEmail(
            String to,
            String username,
            EmailTemplateName emailTemplateName,
            String confirmationUrl,
            String activationCode,
            String subject

    ) throws MessagingException {
        String templateName;

        if (emailTemplateName == null) {
            templateName = "confirm_email";
        } else {
            templateName = emailTemplateName.getName();
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                MULTIPART_MODE_MIXED,
                UTF_8.name()
        );

        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("activation_code", activationCode);

        Context context = new Context();
        context.setVariables(properties);

        helper.setFrom("ccweerasinghe1994@gmail.com");
        helper.setSubject(subject);
        helper.setTo(to);

        String template = templateEngine.process(templateName, context);

        helper.setText(template, true);

        mailSender.send(mimeMessage);

    }

}

```

```java
package com.wchamara.book.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {

    ACTIVATE_ACCOUNT("activate_account");

    private final String name;

    EmailTemplateName(String name) {
        this.name = name;
    }
}

```

```java
package com.wchamara.book.auth;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {

        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }


}

```

```java
package com.wchamara.book.auth;

import com.wchamara.book.email.EmailService;
import com.wchamara.book.email.EmailTemplateName;
import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import com.wchamara.book.user.Token;
import com.wchamara.book.user.TokenRepository;
import com.wchamara.book.user.User;
import com.wchamara.book.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    @Value("${application.mailing.frontend}")
    private String activationUrl;


    public void register(RegistrationRequest request) throws MessagingException {
        Role userRole = roleRepository.findByName("USER").orElseThrow(
                () -> new IllegalStateException("Role user was not initialized")
        );

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var token = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                token,
                "Account Activation"
        );
        
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);

        Token token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;

    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(characters.length());
            char charAt = characters.charAt(index);
            codeBuilder.append(charAt);
        }

        return codeBuilder.toString();
    }
}

```

```java
package com.wchamara.book;

import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class BookNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookNetworkApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {

            if (roleRepository.findByName("USER").isEmpty()) {
                roleRepository.save(Role.builder()
                        .name("USER")
                        .build());
            }

        };
    }

}

```
activate_account.html
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Account Activation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            max-width: 600px;
            margin: 10px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .activation-code {
            font-size: 36px;
            text-align: center;
            margin-bottom: 20px;
        }

        .activation-link {
            display: block;
            text-align: center;
            margin-top: 20px;
        }

        .activation-link a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Account Activation</h1>
    <p class="greeting" th:text="'Hello ' + ${username} + ','"></p>
    <p>Thank you for signing up! Please use the following activation code to activate your account:</p>
    <div class="activation-code"><span th:text="${activation_code}"></span></div>
    <div class="activation-link">
        <a target="_blank" th:href="${confirmationUrl}">Activate your account</a>
    </div>
</div>
</body>
</html>
```

application.yml
```yml
  mailing:
    frontend: http://localhost:4200/activate-account

server:
  port: 8088
```

Certainly! Let's delve deeply into your Spring Boot application, focusing on the registration and email activation process. We'll explore each provided code snippet, understand its role within the application, identify potential issues, and offer actionable solutions with examples. This comprehensive analysis aims to help you resolve the current validation error related to the `email` field and enhance the overall robustness of your application.

---

## 🛠️ **Table of Contents**

- [📗Let's Implement the Email Service](#lets-implement-the-email-service)
  - [🛠️ **Table of Contents**](#️-table-of-contents)
  - [📋 **1. Application Overview**](#-1-application-overview)
  - [🔍 **2. Detailed Code Analysis**](#-2-detailed-code-analysis)
    - [1. `EmailService` Class](#1-emailservice-class)
    - [2. `EmailTemplateName` Enum](#2-emailtemplatename-enum)
    - [3. `AuthenticationController` Class](#3-authenticationcontroller-class)
    - [4. `AuthenticationService` Class](#4-authenticationservice-class)
    - [5. `BookNetworkApplication` Class](#5-booknetworkapplication-class)
    - [6. `activate_account.html` Template](#6-activate_accounthtml-template)
    - [7. `application.yml` Configuration](#7-applicationyml-configuration)
  - [🧐 **3. Understanding the Validation Error**](#-3-understanding-the-validation-error)
  - [⚠️ **4. Potential Issues and Solutions**](#️-4-potential-issues-and-solutions)
    - [A. **Email Validation Failure**](#a-email-validation-failure)
    - [B. **Redundant Validation Annotations**](#b-redundant-validation-annotations)
    - [C. **JSON Deserialization Issues**](#c-json-deserialization-issues)
    - [D. **Exception Handling and Feedback**](#d-exception-handling-and-feedback)
  - [💡 **5. Best Practices and Recommendations**](#-5-best-practices-and-recommendations)
    - [1. **Streamline Validation Annotations**](#1-streamline-validation-annotations)
    - [2. **Enhance Exception Handling**](#2-enhance-exception-handling)
    - [3. **Improve Logging Mechanisms**](#3-improve-logging-mechanisms)
    - [4. **Strengthen Client-Side Validations**](#4-strengthen-client-side-validations)
    - [5. **Utilize API Documentation Tools**](#5-utilize-api-documentation-tools)
    - [6. **Secure Password Handling**](#6-secure-password-handling)
  - [🚀 **6. Comprehensive Example Implementation**](#-6-comprehensive-example-implementation)
    - [A. **Revised `RegistrationRequest` Class**](#a-revised-registrationrequest-class)
    - [B. **Updated `AuthenticationController` Class**](#b-updated-authenticationcontroller-class)
    - [C. **Enhanced `AuthenticationService` Class**](#c-enhanced-authenticationservice-class)
    - [D. **Improved `GlobalExceptionHandler` Class**](#d-improved-globalexceptionhandler-class)
  - [🔍 **7. Testing the Registration Flow**](#-7-testing-the-registration-flow)
    - [1. **Valid Registration Request**](#1-valid-registration-request)
    - [2. **Invalid Email Format**](#2-invalid-email-format)
    - [3. **Missing Email Field**](#3-missing-email-field)
    - [4. **Empty Email Field**](#4-empty-email-field)
  - [📈 **8. Conclusion**](#-8-conclusion)
  - [📚 **9. Additional Resources**](#-9-additional-resources)

---

## 📋 **1. Application Overview**

Your Spring Boot application facilitates user registration with email activation. The primary components involved in this process include:

- **Controllers:** Handle HTTP requests related to authentication (e.g., registration).
- **Services:** Contain business logic for registering users and sending activation emails.
- **Repositories:** Interact with the database to perform CRUD operations on entities like `User`, `Role`, and `Token`.
- **Email Templates:** Define the structure of activation emails sent to users.
- **Configuration Files:** Manage application settings, such as server ports and mailing configurations.

Understanding how these components interact is crucial for identifying and resolving the validation errors you're encountering.

---

## 🔍 **2. Detailed Code Analysis**

Let's examine each of your provided code snippets to understand their roles and interconnections.

### 1. `EmailService` Class

```java
package com.wchamara.book.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendEmail(
            String to,
            String username,
            EmailTemplateName emailTemplateName,
            String confirmationUrl,
            String activationCode,
            String subject

    ) throws MessagingException {
        String templateName;

        if (emailTemplateName == null) {
            templateName = "confirm_email";
        } else {
            templateName = emailTemplateName.getName();
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                MULTIPART_MODE_MIXED,
                UTF_8.name()
        );

        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("activation_code", activationCode);

        Context context = new Context();
        context.setVariables(properties);

        helper.setFrom("ccweerasinghe1994@gmail.com");
        helper.setSubject(subject);
        helper.setTo(to);

        String template = templateEngine.process(templateName, context);

        helper.setText(template, true);

        mailSender.send(mimeMessage);
    }
}
```

**🔑 Key Points:**

- **Purpose:** Sends emails using Thymeleaf templates.
- **Dependencies:**
  - `JavaMailSender`: For sending emails.
  - `SpringTemplateEngine`: For processing Thymeleaf templates.
- **`@Async` Annotation:** Enables asynchronous execution, allowing email sending to occur in the background without blocking the main thread.
- **Method Parameters:**
  - `to`: Recipient's email address.
  - `username`: Recipient's name for personalization.
  - `emailTemplateName`: Enum specifying the email template to use.
  - `confirmationUrl`: URL for account activation.
  - `activationCode`: Code for activating the account.
  - `subject`: Email subject line.

**🔄 Flow:**

1. **Template Selection:** Chooses the email template based on `emailTemplateName`. Defaults to `"confirm_email"` if none is provided.
2. **MimeMessage Creation:** Constructs the email message.
3. **Context Population:** Injects variables (`username`, `confirmationUrl`, `activation_code`) into the Thymeleaf context.
4. **Email Composition:**
   - Sets the sender's email.
   - Sets the email subject.
   - Sets the recipient's email.
   - Processes the Thymeleaf template with the provided context.
   - Sets the email content as HTML.
5. **Email Sending:** Dispatches the composed email.

**🛠️ Example Usage:**

Assuming you want to send an activation email to a user named "John Doe" with a confirmation URL and activation code:

```java
emailService.sendEmail(
    "john.doe@example.com",
    "John Doe",
    EmailTemplateName.ACTIVATE_ACCOUNT,
    "http://localhost:4200/activate-account",
    "123456",
    "Account Activation"
);
```

---

### 2. `EmailTemplateName` Enum

```java
package com.wchamara.book.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {

    ACTIVATE_ACCOUNT("activate_account");

    private final String name;

    EmailTemplateName(String name) {
        this.name = name;
    }
}
```

**🔑 Key Points:**

- **Purpose:** Enumerates the different email templates available.
- **Current Value:** `ACTIVATE_ACCOUNT` mapped to `"activate_account"`.
- **`@Getter` Annotation:** Generates a getter for the `name` field.

**🛠️ Example Usage:**

When sending an activation email, you use the `ACTIVATE_ACCOUNT` enum to specify the template:

```java
emailService.sendEmail(
    user.getEmail(),
    user.getFullName(),
    EmailTemplateName.ACTIVATE_ACCOUNT,
    activationUrl,
    token,
    "Account Activation"
);
```

---

### 3. `AuthenticationController` Class

```java
package com.wchamara.book.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {

        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }
}
```

**🔑 Key Points:**

- **Purpose:** Handles HTTP requests related to authentication, specifically user registration.
- **Annotations:**
  - `@RestController`: Marks the class as a RESTful controller.
  - `@RequestMapping("auth")`: Base path for all endpoints in this controller.
  - `@RequiredArgsConstructor`: Generates a constructor with required arguments (i.e., `final` fields).
  - `@Tag(name = "Authentication")`: Swagger annotation for API documentation.
- **Dependency:**
  - `AuthenticationService`: Handles the business logic for user registration.
- **Endpoint:**
  - **Path:** `POST /auth/register`
  - **Functionality:**
    - Accepts a `RegistrationRequest` payload.
    - Validates the request using `@Valid`.
    - Invokes the `register` method in `AuthenticationService`.
    - Returns an HTTP 202 (Accepted) status.

**🛠️ Example Request:**

```http
POST /auth/register HTTP/1.1
Content-Type: application/json

{
  "firstname": "John",
  "lastname": "Doe",
  "email": "john.doe@example.com",
  "password": "SecurePassword123"
}
```

**🛠️ Example Response:**

```http
HTTP/1.1 202 Accepted
Content-Type: application/json
```

*Note: The response body is empty as per the controller's implementation.*

---

### 4. `AuthenticationService` Class

```java
package com.wchamara.book.auth;

import com.wchamara.book.email.EmailService;
import com.wchamara.book.email.EmailTemplateName;
import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import com.wchamara.book.user.Token;
import com.wchamara.book.user.TokenRepository;
import com.wchamara.book.user.User;
import com.wchamara.book.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;

    @Value("${application.mailing.frontend}")
    private String activationUrl;


    public void register(RegistrationRequest request) throws MessagingException {
        Role userRole = roleRepository.findByName("USER").orElseThrow(
                () -> new IllegalStateException("Role user was not initialized")
        );

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var token = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                token,
                "Account Activation"
        );
        
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);

        Token token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;

    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(characters.length());
            char charAt = characters.charAt(index);
            codeBuilder.append(charAt);
        }

        return codeBuilder.toString();
    }
}
```

**🔑 Key Points:**

- **Purpose:** Handles the business logic for user registration, including saving the user, generating activation tokens, and sending activation emails.
- **Annotations:**
  - `@Service`: Marks the class as a service component in Spring.
  - `@RequiredArgsConstructor`: Generates a constructor with required arguments.
- **Dependencies:**
  - `RoleRepository`: For fetching user roles from the database.
  - `PasswordEncoder`: For hashing user passwords.
  - `UserRepository`: For CRUD operations on `User` entities.
  - `TokenRepository`: For managing activation tokens.
  - `EmailService`: For sending activation emails.
- **Configuration:**
  - `@Value("${application.mailing.frontend}")`: Injects the `activationUrl` from `application.yml`.
- **Methods:**
  - `register`: Main method to handle user registration.
  - `sendValidationEmail`: Sends the activation email to the user.
  - `generateAndSaveActivationToken`: Creates and saves an activation token.
  - `generateActivationCode`: Generates a random numeric activation code of specified length.

**🔄 Flow:**

1. **Role Retrieval:** Fetches the "USER" role from the database.
2. **User Creation:** Builds a `User` object with provided details and hashed password.
3. **User Saving:** Persists the `User` object to the database.
4. **Activation Token Generation:** Creates a 6-digit activation code and saves it with an expiration time.
5. **Email Dispatch:** Sends an activation email to the user with the activation code and confirmation URL.

**🛠️ Example Flow:**

1. **User Registration Request:**

   ```json
   {
     "firstname": "John",
     "lastname": "Doe",
     "email": "john.doe@example.com",
     "password": "SecurePassword123"
   }
   ```

2. **Service Processing:**

   - Retrieves the "USER" role.
   - Hashes the password.
   - Saves the user to the database.
   - Generates a 6-digit activation code (e.g., "482950").
   - Saves the activation token with an expiration time (15 minutes from creation).
   - Sends an activation email to `john.doe@example.com` with the activation code and confirmation URL.

---

### 5. `BookNetworkApplication` Class

```java
package com.wchamara.book;

import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class BookNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookNetworkApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {

            if (roleRepository.findByName("USER").isEmpty()) {
                roleRepository.save(Role.builder()
                        .name("USER")
                        .build());
            }

        };
    }

}
```

**🔑 Key Points:**

- **Purpose:** Entry point of the Spring Boot application.
- **Annotations:**
  - `@SpringBootApplication`: Indicates a Spring Boot application.
  - `@EnableJpaAuditing`: Enables JPA auditing features.
  - `@EnableAsync`: Enables asynchronous method execution (required for `@Async` in `EmailService`).
- **`CommandLineRunner` Bean:**
  - **Purpose:** Initializes the database with the "USER" role if it doesn't already exist.
  - **Flow:**
    - Checks if the "USER" role exists.
    - If absent, creates and saves it to the database.

**🛠️ Example Scenario:**

Upon starting the application, the `CommandLineRunner` ensures that there's at least one role ("USER") in the `RoleRepository`. This setup is crucial for assigning roles to users during registration.

---

### 6. `activate_account.html` Template

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Account Activation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            max-width: 600px;
            margin: 10px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .activation-code {
            font-size: 36px;
            text-align: center;
            margin-bottom: 20px;
        }

        .activation-link {
            display: block;
            text-align: center;
            margin-top: 20px;
        }

        .activation-link a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Account Activation</h1>
    <p class="greeting" th:text="'Hello ' + ${username} + ','"></p>
    <p>Thank you for signing up! Please use the following activation code to activate your account:</p>
    <div class="activation-code"><span th:text="${activation_code}"></span></div>
    <div class="activation-link">
        <a target="_blank" th:href="${confirmationUrl}">Activate your account</a>
    </div>
</div>
</body>
</html>
```

**🔑 Key Points:**

- **Purpose:** Thymeleaf template for the account activation email.
- **Structure:**
  - **Styling:** Inline CSS for basic styling of the email.
  - **Content:**
    - Greeting with the user's name.
    - Activation code displayed prominently.
    - Activation link button directing to the frontend's activation page.
- **Thymeleaf Attributes:**
  - `th:text`: Replaces the element's text content with the provided value.
  - `th:href`: Sets the `href` attribute dynamically based on the provided URL.

**🛠️ Example Rendered Email:**

When processed with the following context:

```java
Map<String, Object> properties = new HashMap<>();
properties.put("username", "John Doe");
properties.put("confirmationUrl", "http://localhost:4200/activate-account");
properties.put("activation_code", "123456");
```

**Rendered Email Content:**

---

**Subject:** Account Activation

---

**Body:**

```
Hello John Doe,

Thank you for signing up! Please use the following activation code to activate your account:

123456

[Activate your account](http://localhost:4200/activate-account)
```

---

### 7. `application.yml` Configuration

```yaml
mailing:
  frontend: http://localhost:4200/activate-account

server:
  port: 8088
```

**🔑 Key Points:**

- **Purpose:** Configures application-specific properties.
- **Properties:**
  - `mailing.frontend`: URL pointing to the frontend's account activation page.
  - `server.port`: Specifies that the application runs on port `8088`.

**🛠️ Example Usage:**

- **Activation URL:** Utilized in the `AuthenticationService` to provide users with a link to activate their accounts.

---

## 🧐 **3. Understanding the Validation Error**

**Latest Log Entry:**

```
2024-09-19T15:43:27.446+05:30  WARN 26968 --- [nio-8088-exec-7] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.bind.MethodArgumentNotValidException: Validation failed for argument [0] in public org.springframework.http.ResponseEntity<?> com.wchamara.book.auth.AuthenticationController.register(com.wchamara.book.auth.RegistrationRequest) throws jakarta.mail.MessagingException: [Field error in object 'registrationRequest' on field 'email': rejected value [ccweerasinghe1994]; codes [Email.registrationRequest.email,Email.email,Email.java.lang.String,Email]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [registrationRequest.email,email]; arguments []; default message [email],[Ljakarta.validation.constraints.Pattern$Flag;@661689e5,.*]; default message [Invalid email]] ]
```

**🔑 Key Points:**

- **Exception Type:** `MethodArgumentNotValidException`
- **Affected Argument:** The first argument (`[0]`) in the `register` method of `AuthenticationController`.
- **Field with Error:** `email`
- **Rejected Value:** `ccweerasinghe1994`
- **Validation Error Message:** `Invalid email`
- **Validation Constraint Triggered:** `@Email`

**🔍 Interpretation:**

The `email` field in the `RegistrationRequest` object received the value `ccweerasinghe1994`, which fails the `@Email` validation constraint. The `@Email` annotation ensures that the provided string adheres to a valid email format (e.g., contains an `@` symbol and a domain).

**📈 Impact:**

- **User Experience:** Users attempting to register with invalid email formats receive clear error messages indicating the issue.
- **Data Integrity:** Prevents the creation of user accounts with malformed email addresses, ensuring reliable communication channels.

---

## ⚠️ **4. Potential Issues and Solutions**

Given the current validation error related to the `email` field, let's explore potential causes and provide solutions.

### A. **Email Validation Failure**

**Cause:**

The email `ccweerasinghe1994` lacks essential components of a valid email address, such as the `@` symbol and domain (e.g., `.com`, `.org`). The `@Email` annotation in your `RegistrationRequest` class is correctly identifying this as an invalid email format.

**Solution:**

1. **Client-Side Validation:**

   - **Purpose:** Prevent users from submitting invalid email formats, enhancing user experience and reducing server-side validation errors.
   - **Implementation:**
     - **HTML5 Validation:**
       ```html
       <form name="registrationForm" onsubmit="return validateForm()">
         <input type="text" name="firstname" required>
         <input type="text" name="lastname" required>
         <input type="email" name="email" required>
         <input type="password" name="password" required>
         <button type="submit">Register</button>
       </form>
       ```
       - **Explanation:** The `type="email"` attribute ensures that browsers perform basic email format validation.

     - **JavaScript Validation:**
       ```javascript
       function validateForm() {
           const email = document.forms["registrationForm"]["email"].value;
           const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
           if (!emailPattern.test(email)) {
               alert("Please enter a valid email address.");
               return false;
           }
           // Additional validations...
           return true;
       }
       ```
       - **Explanation:** This script uses a regex pattern to validate the email format before allowing form submission.

2. **Server-Side Validation Enhancements:**

   - **Purpose:** Ensure data integrity and security by validating inputs on the server, regardless of client-side validations.
   - **Implementation:**
     - **Maintain `@Email` Annotation:** Continue using `@Email` in your `RegistrationRequest` class to enforce valid email formats.
     - **Provide Clear Error Messages:** Ensure that the `GlobalExceptionHandler` returns informative messages to guide users in correcting their inputs.

3. **Feedback Mechanism:**

   - **Purpose:** Inform users promptly about validation errors, enabling them to rectify issues without frustration.
   - **Implementation:**
     - **Frontend Integration:** Design the frontend to display error messages received from the server, highlighting specific fields that require attention.
     - **Example:**
       ```javascript
       // Example using Fetch API
       fetch('http://localhost:8088/auth/register', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json'
           },
           body: JSON.stringify({
               firstname: 'John',
               lastname: 'Doe',
               email: 'johndoe', // Invalid email
               password: 'SecurePassword123'
           })
       })
       .then(response => response.json())
       .then(data => {
           if (response.status === 400) {
               // Display error message to the user
               alert(data.email); // "Invalid email"
           } else {
               // Proceed with success flow
               alert('Registration successful!');
           }
       })
       .catch(error => console.error('Error:', error));
       ```

### B. **Redundant Validation Annotations**

**Cause:**

In earlier interactions, there were validation errors related to the `firstname` field, where both `@NotBlank` and `@NotEmpty` annotations were applied. This redundancy led to multiple error messages for the same validation failure.

**Solution:**

1. **Streamline Validation Annotations:**

   - **Use `@NotBlank` Alone for String Fields:**
     - **Reason:** `@NotBlank` ensures that the string is not `null`, not empty, and contains at least one non-whitespace character.
     - **Remove `@NotEmpty`:** Since `@NotBlank` already covers non-emptiness, `@NotEmpty` becomes redundant.
   - **Revised `RegistrationRequest` Example:**
     ```java
     import javax.validation.constraints.Email;
     import javax.validation.constraints.NotBlank;
     import com.fasterxml.jackson.annotation.JsonProperty;
     import lombok.Data;

     @Data
     public class RegistrationRequest {
         
         @NotBlank(message = "First name cannot be empty")
         @JsonProperty("firstname")
         private String firstname;
         
         @NotBlank(message = "Last name cannot be empty")
         @JsonProperty("lastname")
         private String lastname;
         
         @Email(message = "Invalid email")
         @NotBlank(message = "Email cannot be empty")
         @JsonProperty("email")
         private String email;
         
         @NotBlank(message = "Password cannot be empty")
         @JsonProperty("password")
         private String password;
     }
     ```
   
2. **Benefits:**

   - **Clarity:** Eliminates multiple error messages for the same validation issue.
   - **Maintainability:** Reduces complexity in validation annotations, making the codebase easier to manage.

### C. **JSON Deserialization Issues**

**Cause:**

Mismatch between JSON keys and Java class field names can lead to fields not being populated correctly, resulting in `null` values and subsequent validation failures.

**Solution:**

1. **Ensure Consistent Naming:**

   - **Match JSON Keys with Java Fields:**
     - **Example:** If the Java field is `firstname`, the JSON key should also be `"firstname"`.
     - **Incorrect Example:** Java field `firstname` vs. JSON key `"firstName"`.
   - **Use `@JsonProperty` for Mapping:**
     - **Purpose:** Maps JSON keys to Java fields when naming conventions differ.
     - **Example:**
       ```java
       @NotBlank(message = "First name cannot be empty")
       @JsonProperty("firstName")
       private String firstname;
       ```

2. **Global Naming Strategies (Optional):**

   - **Purpose:** Applies a naming convention across all JSON-serialized/deserialized fields.
   - **Implementation:**
     - **Configure in `application.yml`:**
       ```yaml
       spring:
         jackson:
           property-naming-strategy: SNAKE_CASE
       ```
     - **Effect:** Translates Java's `firstName` to JSON's `first_name` automatically.
   - **Note:** Ensure that frontend and backend agree on the naming conventions to prevent deserialization issues.

3. **Utilize Lombok for Getters and Setters:**

   - **Purpose:** Facilitates proper serialization/deserialization by ensuring all fields have appropriate accessors.
   - **Implementation:**
     - **Use `@Data` Annotation:**
       ```java
       import lombok.Data;

       @Data
       public class RegistrationRequest {
           // Fields with annotations
       }
       ```
     - **Benefit:** Automatically generates getters and setters, ensuring Jackson can access and populate fields correctly.

4. **Debugging Tips:**

   - **Enable Detailed Logging:**
     - **Purpose:** Helps identify how incoming JSON payloads are being deserialized.
     - **Implementation:**
       - **Set Logging Level to DEBUG:**
         ```properties
         logging.level.org.springframework.web=DEBUG
         logging.level.com.wchamara.book=DEBUG
         ```
       - **Benefit:** Logs detailed information about request handling and deserialization.

   - **Inspect Deserialized Objects:**
     - **Use Breakpoints:** In your controller method to inspect the state of the `RegistrationRequest` object after deserialization.
     - **Verify Field Values:** Ensure that all expected fields are populated correctly.

### D. **Exception Handling and Feedback**

**Cause:**

Without proper exception handling, clients may receive generic error messages that are not informative, making it difficult for them to understand and rectify input issues.

**Solution:**

1. **Implement a Global Exception Handler:**

   - **Purpose:** Catches and processes exceptions uniformly across the application, providing structured and meaningful error responses.
   - **Implementation:**
     ```java
     package com.wchamara.book.auth;

     import org.springframework.http.HttpStatus;
     import org.springframework.http.ResponseEntity;
     import org.springframework.validation.FieldError;
     import org.springframework.web.bind.MethodArgumentNotValidException;
     import org.springframework.web.bind.annotation.ControllerAdvice;
     import org.springframework.web.bind.annotation.ExceptionHandler;

     import java.util.HashMap;
     import java.util.Map;

     @ControllerAdvice
     public class GlobalExceptionHandler {
         
         @ExceptionHandler(MethodArgumentNotValidException.class)
         public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
             Map<String, String> errors = new HashMap<>();
             ex.getBindingResult().getAllErrors().forEach((error) -> {
                 String fieldName = ((FieldError) error).getField();
                 String errorMessage = error.getDefaultMessage();
                 errors.put(fieldName, errorMessage);
             });
             return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
         }
         
         // Additional exception handlers can be added here
     }
     ```

   - **Benefits:**
     - **Clarity:** Provides clients with specific error messages tied to particular fields.
     - **Consistency:** Ensures that all validation errors are handled uniformly.

2. **Enhance Error Messages:**

   - **Provide Contextual Information:** Ensure that error messages clearly indicate what went wrong and how to fix it.
   - **Example Error Response:**
     ```json
     {
       "email": "Invalid email",
       "firstname": "First name cannot be empty"
     }
     ```

3. **Frontend Integration:**

   - **Display Errors Appropriately:** Design the frontend to parse and display error messages in a user-friendly manner.
   - **Example:**
     ```javascript
     fetch('http://localhost:8088/auth/register', {
         method: 'POST',
         headers: {
             'Content-Type': 'application/json'
         },
         body: JSON.stringify({
             firstname: 'John',
             lastname: 'Doe',
             email: 'johndoe', // Invalid email
             password: 'SecurePassword123'
         })
     })
     .then(response => {
         if (!response.ok) {
             return response.json().then(data => { throw data; });
         }
         return response.json();
     })
     .then(data => {
         alert('Registration successful!');
     })
     .catch(error => {
         for (const field in error) {
             console.error(`${field}: ${error[field]}`);
             // Display error messages next to form fields
         }
     });
     ```

---

## 💡 **5. Best Practices and Recommendations**

To enhance your application's robustness, user experience, and security, consider implementing the following best practices.

### 1. **Streamline Validation Annotations**

- **Use Appropriate Annotations:**
  - **`@NotBlank` vs. `@NotEmpty`:**
    - **`@NotBlank`:** Validates that the string is not `null`, not empty, and contains at least one non-whitespace character.
    - **`@NotEmpty`:** Validates that the string is not `null` and not empty (does not check for whitespace).
  - **Recommendation:** Use `@NotBlank` for string fields to ensure comprehensive validation.

- **Avoid Redundancy:**
  - **Issue:** Applying both `@NotBlank` and `@NotEmpty` on the same field leads to multiple error messages for a single validation failure.
  - **Solution:** Retain only the most appropriate annotation based on the validation requirements.

### 2. **Enhance Exception Handling**

- **Implement Comprehensive Exception Handlers:**
  - **Purpose:** To catch various exceptions (e.g., `MethodArgumentNotValidException`, `DataIntegrityViolationException`) and provide meaningful responses.
  - **Example:**
    ```java
    @ControllerAdvice
    public class GlobalExceptionHandler {
        
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        
        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
            // Log the exception details for debugging
            // Return a user-friendly message
            return new ResponseEntity<>("Data integrity violation occurred.", HttpStatus.CONFLICT);
        }
        
        // Additional exception handlers...
    }
    ```

- **Benefits:**
  - **User Guidance:** Helps users understand what went wrong and how to fix it.
  - **Developer Insights:** Provides developers with detailed logs for debugging.

### 3. **Improve Logging Mechanisms**

- **Enable Detailed Logging:**
  - **Purpose:** To gain insights into application behavior and identify issues promptly.
  - **Implementation:**
    ```properties
    # application.properties
    logging.level.org.springframework.web=DEBUG
    logging.level.com.wchamara.book=DEBUG
    ```

- **Log Important Events:**
  - **Example:** Log when an email is sent or when a user is successfully registered.
    ```java
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    @Service
    @RequiredArgsConstructor
    public class EmailService {
        private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
        // Existing code...

        @Async
        public void sendEmail(...) throws MessagingException {
            // Existing code...
            logger.info("Sending email to {}", to);
            mailSender.send(mimeMessage);
            logger.info("Email sent successfully to {}", to);
        }
    }
    ```

- **Benefits:**
  - **Debugging:** Facilitates tracking the flow of data and identifying bottlenecks or failures.
  - **Monitoring:** Assists in monitoring application health and performance.

### 4. **Strengthen Client-Side Validations**

- **Purpose:** To provide immediate feedback to users, enhancing user experience and reducing server load.
- **Implementation:**
  - **HTML5 Validations:** Utilize form input types and attributes for basic validations.
  - **JavaScript Validations:** Implement advanced validations using JavaScript or frontend frameworks (e.g., React, Angular).

- **Example:**
  ```html
  <form id="registrationForm">
      <input type="text" name="firstname" required placeholder="First Name">
      <input type="text" name="lastname" required placeholder="Last Name">
      <input type="email" name="email" required placeholder="Email Address">
      <input type="password" name="password" required placeholder="Password">
      <button type="submit">Register</button>
  </form>

  <script>
      document.getElementById('registrationForm').addEventListener('submit', function(event) {
          const email = this.email.value;
          const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
          if (!emailPattern.test(email)) {
              alert('Please enter a valid email address.');
              event.preventDefault();
          }
          // Additional validations...
      });
  </script>
  ```

### 5. **Utilize API Documentation Tools**

- **Purpose:** To provide clear and interactive API documentation, facilitating easier integration for frontend developers and third-party clients.
- **Implementation with Springdoc OpenAPI:**
  - **Add Dependency (Maven):**
    ```xml
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-ui</artifactId>
        <version>1.6.14</version>
    </dependency>
    ```
  
  - **Access Swagger UI:**
    - Typically available at `http://localhost:8088/swagger-ui.html` or `http://localhost:8088/swagger-ui/index.html`.

- **Benefits:**
  - **Interactivity:** Allows developers to test API endpoints directly from the browser.
  - **Clarity:** Auto-generates documentation based on controllers and models, ensuring accuracy.

### 6. **Secure Password Handling**

- **Purpose:** To protect user credentials and prevent unauthorized access.
- **Implementation:**
  - **Password Encoding:** Utilize `PasswordEncoder` (e.g., BCrypt) to hash passwords before storing them.
    ```java
    @Service
    @RequiredArgsConstructor
    public class AuthenticationService {
        private final PasswordEncoder passwordEncoder;
        // Existing code...

        public void register(RegistrationRequest request) {
            // Existing code...
            User user = User.builder()
                    .password(passwordEncoder.encode(request.getPassword()))
                    // Other fields...
                    .build();
            // Existing code...
        }
    }
    ```

  - **Password Strength Validation:** Enforce strong password policies using regex patterns.
    ```java
    import javax.validation.constraints.Pattern;

    public class RegistrationRequest {
        
        // Existing fields...

        @NotBlank(message = "Password cannot be empty")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
                 message = "Password must be minimum eight characters, at least one letter and one number")
        @JsonProperty("password")
        private String password;
    }
    ```

- **Benefits:**
  - **Security:** Protects user data from breaches and unauthorized access.
  - **Compliance:** Meets industry standards for password security.

---

## 🚀 **6. Comprehensive Example Implementation**

To address the current validation issue and enhance your application, let's implement the recommended changes step-by-step.

### A. **Revised `RegistrationRequest` Class**

```java
package com.wchamara.book.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistrationRequest {
    
    @NotBlank(message = "First name cannot be empty")
    @JsonProperty("firstname")
    private String firstname;
    
    @NotBlank(message = "Last name cannot be empty")
    @JsonProperty("lastname")
    private String lastname;
    
    @Email(message = "Invalid email")
    @NotBlank(message = "Email cannot be empty")
    @JsonProperty("email")
    private String email;
    
    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
             message = "Password must be minimum eight characters, at least one letter and one number")
    @JsonProperty("password")
    private String password;
}
```

**🔑 Enhancements:**

- **Password Strength Validation:** Ensures that passwords are at least eight characters long and contain both letters and numbers.
- **Consistent Field Mapping:** Uses `@JsonProperty` to align JSON keys with Java fields.
- **Lombok's `@Data` Annotation:** Generates getters, setters, `toString`, `equals`, and `hashCode` methods, reducing boilerplate code.

### B. **Updated `AuthenticationController` Class**

```java
package com.wchamara.book.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {
        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }
}
```

**🔑 Enhancements:**

- **Removal of `@ResponseStatus`:** Relying on `ResponseEntity` to set the HTTP status, providing more flexibility.
- **Consistent Exception Handling:** Delegates exception handling to the `GlobalExceptionHandler`.

### C. **Enhanced `AuthenticationService` Class**

```java
package com.wchamara.book.auth;

import com.wchamara.book.email.EmailService;
import com.wchamara.book.email.EmailTemplateName;
import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import com.wchamara.book.user.Token;
import com.wchamara.book.user.TokenRepository;
import com.wchamara.book.user.User;
import com.wchamara.book.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;

    @Value("${mailing.frontend}")
    private String activationUrl; // Updated to match application.yml

    public void register(RegistrationRequest request) throws MessagingException {
        Role userRole = roleRepository.findByName("USER").orElseThrow(
                () -> new IllegalStateException("Role 'USER' was not initialized")
        );

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var token = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                token,
                "Account Activation"
        );
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);

        Token token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(characters.length());
            char charAt = characters.charAt(index);
            codeBuilder.append(charAt);
        }

        return codeBuilder.toString();
    }
}
```

**🔑 Enhancements:**

- **Configuration Property Correction:**
  - **Previous:** `@Value("${application.mailing.frontend}")`
  - **Updated:** `@Value("${mailing.frontend}")` to match the `application.yml` configuration.
- **Consistent Error Messages:** Enhanced the exception message for role initialization.

### D. **Improved `GlobalExceptionHandler` Class**

```java
package com.wchamara.book.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
    // Additional exception handlers can be added here
}
```

**🔑 Enhancements:**

- **Structured Error Responses:** Converts validation errors into a JSON map, mapping field names to their respective error messages.
- **Flexibility for Additional Exceptions:** Placeholder for handling other types of exceptions.

**🛠️ Example Error Response:**

```json
{
  "email": "Invalid email",
  "firstname": "First name cannot be empty"
}
```

---

## 🔍 **7. Testing the Registration Flow**

To ensure that the registration and email activation process works as intended, perform thorough testing using API tools like **Postman**, **Insomnia**, or **cURL**.

### 1. **Valid Registration Request**

**Request Payload:**

```json
{
  "firstname": "Alice",
  "lastname": "Smith",
  "email": "alice.smith@example.com",
  "password": "StrongPassword123"
}
```

**Expected Outcome:**

- **HTTP Status:** `202 Accepted`
- **Response Body:** Empty (as per the controller's implementation).
- **Email Sent:** An activation email to `alice.smith@example.com` containing the activation code and activation link.

**Verification Steps:**

1. **Send the Request:**
   ```bash
   curl -X POST http://localhost:8088/auth/register \
   -H "Content-Type: application/json" \
   -d '{
         "firstname": "Alice",
         "lastname": "Smith",
         "email": "alice.smith@example.com",
         "password": "StrongPassword123"
       }'
   ```

2. **Check the Response:**
   - Ensure that the response status is `202 Accepted`.
   
3. **Verify Email Receipt:**
   - Check the recipient's inbox for the activation email.
   - Confirm that the activation code and link are present and correctly formatted.

### 2. **Invalid Email Format**

**Request Payload:**

```json
{
  "firstname": "Bob",
  "lastname": "Johnson",
  "email": "bobjohnson", // Invalid email
  "password": "SecurePass456"
}
```

**Expected Outcome:**

- **HTTP Status:** `400 Bad Request`
- **Response Body:**
  ```json
  {
    "email": "Invalid email"
  }
  ```

**Verification Steps:**

1. **Send the Request:**
   ```bash
   curl -X POST http://localhost:8088/auth/register \
   -H "Content-Type: application/json" \
   -d '{
         "firstname": "Bob",
         "lastname": "Johnson",
         "email": "bobjohnson",
         "password": "SecurePass456"
       }'
   ```

2. **Check the Response:**
   - Ensure that the response status is `400 Bad Request`.
   - Verify that the response body contains the error message for the `email` field.

### 3. **Missing Email Field**

**Request Payload:**

```json
{
  "firstname": "Charlie",
  "lastname": "Brown",
  "password": "AnotherPass789"
}
```

**Expected Outcome:**

- **HTTP Status:** `400 Bad Request`
- **Response Body:**
  ```json
  {
    "email": "Email cannot be empty"
  }
  ```

**Verification Steps:**

1. **Send the Request:**
   ```bash
   curl -X POST http://localhost:8088/auth/register \
   -H "Content-Type: application/json" \
   -d '{
         "firstname": "Charlie",
         "lastname": "Brown",
         "password": "AnotherPass789"
       }'
   ```

2. **Check the Response:**
   - Ensure that the response status is `400 Bad Request`.
   - Verify that the response body indicates that the `email` field cannot be empty.

### 4. **Empty Email Field**

**Request Payload:**

```json
{
  "firstname": "Diana",
  "lastname": "Prince",
  "email": "",
  "password": "WonderWoman123"
}
```

**Expected Outcome:**

- **HTTP Status:** `400 Bad Request`
- **Response Body:**
  ```json
  {
    "email": "Email cannot be empty"
  }
  ```

**Verification Steps:**

1. **Send the Request:**
   ```bash
   curl -X POST http://localhost:8088/auth/register \
   -H "Content-Type: application/json" \
   -d '{
         "firstname": "Diana",
         "lastname": "Prince",
         "email": "",
         "password": "WonderWoman123"
       }'
   ```

2. **Check the Response:**
   - Ensure that the response status is `400 Bad Request`.
   - Verify that the response body indicates that the `email` field cannot be empty.

---

## 📈 **8. Conclusion**

Your Spring Boot application's registration and email activation process is well-structured, leveraging Spring's robust features such as validation, asynchronous processing, and template-based email generation. However, the validation errors you're encountering, particularly with the `email` field, stem from invalid input formats and potential misconfigurations in request handling.

By implementing the recommended solutions—streamlining validation annotations, ensuring consistent JSON field mappings, enhancing exception handling, and strengthening client-side validations—you can effectively resolve these issues. Additionally, adopting best practices in logging, API documentation, and security will further fortify your application, ensuring a seamless and secure user experience.

---

## 📚 **9. Additional Resources**

- **Spring Boot Validation:**
  - [Spring Boot Validation Guide](https://spring.io/guides/gs/validating-form-input/)
  
- **Jackson Annotations:**
  - [Jackson Annotations Reference](https://github.com/FasterXML/jackson-annotations)
  
- **Project Lombok:**
  - [Project Lombok Official Site](https://projectlombok.org/)
  
- **Springdoc OpenAPI:**
  - [Springdoc OpenAPI Documentation](https://springdoc.org/)
  
- **Thymeleaf Documentation:**
  - [Thymeleaf Spring Boot Integration](https://www.thymeleaf.org/documentation.html#springboot)
  
- **JavaMailSender Documentation:**
  - [JavaMailSender Interface](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/mail/javamail/JavaMailSender.html)
  
- **PasswordEncoder Interface:**
  - [PasswordEncoder Documentation](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/password/PasswordEncoder.html)
  
- **Spring Boot Logging:**
  - [Spring Boot Logging Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging)
  
- **Testing Spring Boot Applications:**
  - [Testing Spring Boot](https://spring.io/guides/gs/testing-web/)

---

Feel free to reach out with further questions or updates on your progress! Happy coding! 🚀