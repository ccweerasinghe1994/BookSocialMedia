# 📗Implementing the Enail Sender Service

```java
package com.wchamara.book.auth;

import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import com.wchamara.book.user.Token;
import com.wchamara.book.user.TokenRepository;
import com.wchamara.book.user.User;
import com.wchamara.book.user.UserRepository;
import lombok.RequiredArgsConstructor;
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


    public void register(RegistrationRequest request) {
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

    private void sendValidationEmail(User user) {
        var token = generateAndSaveActivationToken(user);

        // send email
        return;

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

Certainly! Let's delve deeply into the provided Java `AuthenticationService` class, which is part of a Spring-based application responsible for handling user registration and authentication. We'll break down each component, explain its purpose, and provide examples to illustrate how the service operates within the application.

## Overview

The `AuthenticationService` class is a Spring `@Service` that manages user registration, including role assignment, password encoding, activation token generation, and sending validation emails. Here's a high-level view of what the class does:

1. **Registers a new user**: Takes registration details, encodes the password, assigns a default role, and saves the user to the database.
2. **Generates an activation token**: Creates a unique activation code for email validation.
3. **Sends a validation email**: Sends the activation token to the user's email for account verification.

Let's break down each part of the code.

## Package and Imports

```java
package com.wchamara.book.auth;

import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import com.wchamara.book.user.Token;
import com.wchamara.book.user.TokenRepository;
import com.wchamara.book.user.User;
import com.wchamara.book.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
```

### Explanation

- **Package Declaration**: The class is part of the `com.wchamara.book.auth` package, indicating it's related to authentication within the `book` application.

- **Imports**: The class imports necessary classes for user, role, and token management (`User`, `Role`, `Token`, and their respective repositories). It also imports `PasswordEncoder` for encoding passwords, `Service` and `RequiredArgsConstructor` annotations from Spring and Lombok respectively, and utility classes like `SecureRandom` and `LocalDateTime`.

### Example

Assume the application manages books, users, and roles (e.g., USER, ADMIN). This service handles the creation of new users and their initial setup.

## Class Declaration and Annotations

```java
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    // ...
}
```

### Explanation

- **@Service**: Marks the class as a Spring service component, making it eligible for component scanning and dependency injection.

- **@RequiredArgsConstructor**: A Lombok annotation that generates a constructor with required arguments (i.e., final fields). This facilitates dependency injection without boilerplate code.

### Example

When Spring initializes the application context, it detects `AuthenticationService` as a service and injects its dependencies automatically via the generated constructor.

## Dependencies

```java
private final RoleRepository roleRepository;
private final PasswordEncoder passwordEncoder;
private final UserRepository userRepository;
private final TokenRepository tokenRepository;
```

### Explanation

These are the dependencies required by the `AuthenticationService`:

- **RoleRepository**: Interface for CRUD operations on `Role` entities.
- **PasswordEncoder**: Interface for encoding passwords securely.
- **UserRepository**: Interface for CRUD operations on `User` entities.
- **TokenRepository**: Interface for CRUD operations on `Token` entities.

### Example

When registering a new user, the service needs to fetch the default role (`USER`), encode the user's password, save the user to the database, and create an activation token.

## The `register` Method

```java
public void register(RegistrationRequest request) {
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
```

### Explanation

1. **Fetching the User Role**:
    - The service retrieves the `USER` role from the `RoleRepository`.
    - If the role isn't found, it throws an `IllegalStateException`, indicating a misconfiguration.

2. **Building the User Object**:
    - Uses a builder pattern (via Lombok's `@Builder`) to create a new `User` instance.
    - Sets the user's first name, last name, email, and encodes the password using `PasswordEncoder`.
    - Initializes `accountLocked` to `false` and `enabled` to `false` (account not yet activated).
    - Assigns the `USER` role to the new user.

3. **Saving the User**:
    - Persists the new user to the database via `UserRepository`.

4. **Sending Validation Email**:
    - Calls `sendValidationEmail(user)` to initiate the account activation process.

### Example

Imagine a user named Jane Doe wants to register:

- She submits her first name, last name, email, and password via a registration form.
- The service encodes her password, assigns the `USER` role, saves her data to the database with `enabled` set to `false`.
- An activation token is generated and emailed to Jane for account verification.

## The `sendValidationEmail` Method

```java
private void sendValidationEmail(User user) {
    var token = generateAndSaveActivationToken(user);

    // send email
    return;
}
```

### Explanation

1. **Generating and Saving the Activation Token**:
    - Calls `generateAndSaveActivationToken(user)` to create a unique token associated with the user.

2. **Sending the Email**:
    - Placeholder comment indicates where the email-sending logic would be implemented.
    - Typically, this would involve using an email service to send the activation token to the user's email address.

### Example

Continuing with Jane Doe's registration:

- An activation token (e.g., "123456") is generated and saved to the database linked to Jane's user record.
- An email is sent to Jane containing the activation code or a link with the token for account verification.

## The `generateAndSaveActivationToken` Method

```java
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
```

### Explanation

1. **Generating the Token**:
    - Calls `generateActivationCode(6)` to create a 6-digit numerical activation code.

2. **Building the Token Object**:
    - Uses a builder to create a new `Token` instance.
    - Sets the token value, creation time (`createdAt`), expiration time (`expiresAt`), and associates it with the user.

3. **Saving the Token**:
    - Persists the token to the database via `TokenRepository`.

4. **Returning the Token**:
    - Returns the generated token string for further use (e.g., inclusion in the validation email).

### Example

For Jane Doe:

- Generates a token like "835192".
- Saves this token with the current timestamp and an expiration time 15 minutes later.
- Returns "835192" to be included in the activation email.

## The `generateActivationCode` Method

```java
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
```

### Explanation

1. **Character Set**:
    - Defines the characters used in the activation code. Here, only digits (0-9) are used.

2. **SecureRandom**:
    - Uses `SecureRandom` for generating cryptographically strong random numbers, enhancing security.

3. **Building the Code**:
    - Iterates `length` times (6 in this case).
    - In each iteration, selects a random character from the `characters` string and appends it to `codeBuilder`.

4. **Returning the Code**:
    - Converts the `StringBuilder` to a `String` and returns it as the activation code.

### Example

For a length of 6:

- Possible activation codes include "482093", "174829", "560391", etc.
- Each code is randomly generated and hard to predict, ensuring security.

## Putting It All Together: Registration Flow Example

Let's walk through a complete registration flow using the `AuthenticationService`.

### Scenario

1. **User Registration**:
    - **User Input**: John Smith submits a registration form with the following details:
        - First Name: John
        - Last Name: Smith
        - Email: john.smith@example.com
        - Password: securePassword123

2. **Registration Request Handling**:
    - **RegistrationRequest**: An instance containing the user's input is created and passed to the `register` method.

3. **Role Assignment**:
    - The service fetches the `USER` role from `RoleRepository`.
    - If the role doesn't exist, registration fails with an exception.

4. **User Creation**:
    - A new `User` object is built with the provided details.
    - The password is encoded (e.g., hashed) using `PasswordEncoder` for security.
    - `accountLocked` is set to `false`, and `enabled` is set to `false` to indicate that the account is not yet active.
    - The `USER` role is assigned to the user.
    - The user is saved to the database via `UserRepository`.

5. **Activation Token Generation**:
    - The service generates a 6-digit activation code (e.g., "827364") using `SecureRandom`.
    - A new `Token` object is created with the token value, current timestamp, expiration time (15 minutes later), and associated with the user.
    - The token is saved to the database via `TokenRepository`.

6. **Sending Validation Email**:
    - An email is sent to `john.smith@example.com` containing the activation code "827364" or a link with the token.
    - This step ensures that the user owns the provided email address and verifies their identity.

7. **Account Activation**:
    - **User Action**: John receives the email and clicks on the activation link or enters the activation code.
    - **Service Handling**: Another service method (not shown) validates the token:
        - Checks if the token exists and is associated with the user.
        - Verifies that the token hasn't expired.
        - If valid, sets `enabled` to `true` for the user, allowing them to log in.
        - Optionally deletes or invalidates the token to prevent reuse.

### Example Flow Diagram

1. **User Registration Form** → 
2. **AuthenticationService.register()** → 
3. **Fetch USER Role** → 
4. **Create User** → 
5. **Save User** → 
6. **Generate Token** → 
7. **Save Token** → 
8. **Send Validation Email** → 
9. **User Receives Email and Activates Account** → 
10. **Enable User Account**

## Security Considerations

1. **Password Encoding**:
    - **Importance**: Storing plain-text passwords is a severe security risk.
    - **Implementation**: Uses `PasswordEncoder` (e.g., BCrypt) to hash passwords before saving them.
    - **Example**: If John’s password is "securePassword123", it might be stored as `$2a$10$E9h8...` (hashed).

2. **Activation Token Security**:
    - **Randomness**: Uses `SecureRandom` to prevent predictability of tokens.
    - **Expiration**: Tokens expire after 15 minutes, reducing the window for potential misuse.
    - **Uniqueness**: Ensures that each token is unique per user and not reused.

3. **Account States**:
    - **accountLocked**: Prevents login attempts if set to `true`, useful for security measures like locking accounts after multiple failed login attempts.
    - **enabled**: Controls whether the user can log in. Only set to `true` after successful email verification.

4. **Exception Handling**:
    - Throws an exception if the `USER` role isn't initialized, ensuring that the application doesn't proceed with incomplete configurations.

## Potential Enhancements

1. **Email Sending Implementation**:
    - The `sendValidationEmail` method currently has a placeholder. Implementing actual email sending (e.g., using Spring's `JavaMailSender`) is essential for production.

    ```java
    private final EmailService emailService; // Assume EmailService is defined

    private void sendValidationEmail(User user) {
        var token = generateAndSaveActivationToken(user);
        String subject = "Activate Your Account";
        String message = "Your activation code is: " + token;
        emailService.sendEmail(user.getEmail(), subject, message);
    }
    ```

2. **Token Reuse Prevention**:
    - Ensure tokens are single-use by deleting them after successful activation.
    - Prevent generating multiple tokens for the same user within a short timeframe.

3. **Enhanced Token Security**:
    - Use longer and more complex tokens (e.g., UUIDs or alphanumeric strings) to increase security.
    - Implement rate limiting to prevent brute-force attempts on activation codes.

4. **User Feedback**:
    - Provide clear feedback to users during registration, activation, and error scenarios.
    - Handle edge cases, such as expired tokens or invalid activation attempts.

5. **Logging and Monitoring**:
    - Implement logging for registration attempts, token generation, and activation processes to monitor and audit activities.

6. **Internationalization (i18n)**:
    - Support multiple languages in emails and messages for a broader user base.

## Conclusion

The `AuthenticationService` class provides a robust foundation for user registration and account activation within a Spring-based application. By leveraging Spring's dependency injection, Lombok for reducing boilerplate code, and secure practices like password encoding and secure token generation, the service ensures both functionality and security.

Understanding each component and its role allows for effective maintenance, extension, and enhancement of the authentication system. Implementing the suggested enhancements can further strengthen the service, making it more secure, user-friendly, and scalable.