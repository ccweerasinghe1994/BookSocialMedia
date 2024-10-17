# 📗Exception Handling 

```java
package com.wchamara.book.handlers;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

import static com.wchamara.book.handlers.BusinessErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class
GlobalExceptionHandler {

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleException(LockedException exception) {
        return ResponseEntity.status(UNAUTHORIZED).body(
                ExceptionResponse.builder()
                        .businessErrorCode(ACCOUNT_LOCKED.getCode())
                        .businessErrorDescription(ACCOUNT_LOCKED.getDescription())
                        .error(exception.getMessage())
                        .build()
        );

    }


    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handleException(DisabledException exception) {
        return ResponseEntity.status(UNAUTHORIZED).body(
                ExceptionResponse.builder()
                        .businessErrorCode(ACCOUNT_DISABLED.getCode())
                        .businessErrorDescription(ACCOUNT_DISABLED.getDescription())
                        .error(exception.getMessage())
                        .build()
        );

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exception) {
        return ResponseEntity.status(UNAUTHORIZED).body(
                ExceptionResponse.builder()
                        .businessErrorCode(BAD_CREDENTIALS.getCode())
                        .businessErrorDescription(BAD_CREDENTIALS.getDescription())
                        .error(exception.getMessage())
                        .build()
        );

    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleException(MessagingException exception) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                ExceptionResponse.builder()
                        .error(exception.getMessage())
                        .build()
        );

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exception) {

        Set<String> errors = new HashSet<>();

        exception.getBindingResult().getFieldErrors().forEach(
                fieldError -> errors.add(fieldError.getDefaultMessage())
        );

        return ResponseEntity.status(BAD_REQUEST).body(
                ExceptionResponse.builder()
                        .validationErrors(errors)
                        .build()
        );

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {

        exception.printStackTrace();

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                ExceptionResponse.builder()
                        .businessErrorDescription("Internal server error please contact support")
                        .error(exception.getMessage())
                        .build()
        );


    }

}

```
```java
package com.wchamara.book.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {

    private Integer businessErrorCode;

    private String businessErrorDescription;

    private String error;

    private Set<String> validationErrors;

    private Map<String, String> errors;
}

```
```java
package com.wchamara.book.handlers;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessErrorCodes {
    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "No code"),
    INCORRECT_CURRENT_PASSWORD(300, HttpStatus.BAD_REQUEST, "Incorrect current password"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST, "new password does not match"),
    ACCOUNT_LOCKED(302, HttpStatus.FORBIDDEN, "Account is locked"),
    ACCOUNT_DISABLED(303, HttpStatus.FORBIDDEN, "Account is disabled"),
    // **General Errors**
    BAD_CREDENTIALS(304, HttpStatus.UNAUTHORIZED, "Bad credentials");


    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}

```
Your implementation of exception handling in your Spring Boot application demonstrates a solid foundation for managing and responding to various error scenarios in a consistent and structured manner. By utilizing Spring's `@RestControllerAdvice` and custom error codes, you ensure that clients receive clear and meaningful error responses. However, there are several areas where you can enhance this implementation to improve code quality, performance, security, and maintainability. Let's delve deeply into each component, explore how they interact, and discuss best practices and improvements with concrete examples.

---

## **1. Overview of Exception Handling in Spring Boot**

Exception handling is crucial in any application to manage unexpected scenarios gracefully, provide meaningful feedback to users, and maintain application stability. In Spring Boot, centralized exception handling can be achieved using `@ControllerAdvice` or `@RestControllerAdvice`, which allows you to intercept exceptions thrown by controllers and provide uniform responses.

Your implementation comprises three main components:

1. **`BusinessErrorCodes` Enum:** Defines business-specific error codes with associated HTTP statuses and descriptions.
2. **`ExceptionResponse` Class:** Represents the structure of the error response sent to clients.
3. **`GlobalExceptionHandler` Class:** Handles various exceptions and maps them to appropriate `ExceptionResponse` instances.

Let's explore each component in detail.

---

## **2. `BusinessErrorCodes` Enum**

### **Purpose**

The `BusinessErrorCodes` enum serves as a centralized repository for all business-related error codes, ensuring consistency across your application. Each enum constant encapsulates:

- **Code (`int`):** A unique numerical identifier for the error.
- **Description (`String`):** A human-readable message describing the error.
- **HTTP Status (`HttpStatus`):** The corresponding HTTP status code to be returned to the client.

### **Current Implementation**

```java
package com.wchamara.book.handlers;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessErrorCodes {
    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "No code"),
    INCORRECT_CURRENT_PASSWORD(300, HttpStatus.BAD_REQUEST, "Incorrect current password"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST, "new password does not match"),
    ACCOUNT_LOCKED(302, HttpStatus.FORBIDDEN, "Account is locked"),
    ACCOUNT_DISABLED(303, HttpStatus.FORBIDDEN, "Account is disabled"),
    // **General Errors**
    BAD_CREDENTIALS(304, HttpStatus.UNAUTHORIZED, "Bad credentials");

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
```

### **Strengths**

- **Centralization:** All business error codes are defined in one place, promoting consistency and ease of maintenance.
- **Clarity:** Each error code has a clear and descriptive name, making it easy to understand the associated error.
- **HTTP Alignment:** Each error code is mapped to an appropriate `HttpStatus`, ensuring that clients receive the correct HTTP response.

### **Areas for Improvement**

1. **Code Uniqueness and Scalability:**
   - Ensure that all error codes are unique to prevent ambiguity.
   - Plan for scalability by reserving ranges for different modules or error categories.

2. **Enum Naming Convention:**
   - Follow a consistent naming convention (e.g., all uppercase with underscores) for enum constants.

3. **Additional Error Codes:**
   - Introduce more error codes to handle various scenarios, such as database errors, authorization failures, and more.

4. **Documentation:**
   - Add JavaDoc comments to each enum constant to provide additional context.

### **Suggested Enhancements**

1. **Categorize Error Codes:**
   - Organize error codes into logical groups (e.g., Authentication, User Management, Validation) to improve readability and maintainability.

2. **Consistent Numbering:**
   - Use a numbering scheme that reflects the category and severity of errors. For example:
     - **100-199:** Authentication Errors
     - **200-299:** User Management Errors
     - **300-399:** Validation Errors
     - **400-499:** External Service Errors

3. **Expand Error Codes:**
   - Introduce more error codes to cover additional scenarios.

4. **JavaDoc Comments:**
   - Provide JavaDoc comments for each enum constant to enhance understanding.

### **Enhanced `BusinessErrorCodes` Enum Example**

```java
package com.wchamara.book.handlers;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Enum representing business-specific error codes with corresponding HTTP statuses and descriptions.
 */
@Getter
public enum BusinessErrorCodes {
    // **General Errors (0-99)**
    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "No code provided"),
    UNKNOWN_ERROR(1, HttpStatus.INTERNAL_SERVER_ERROR, "An unknown error has occurred"),

    // **Authentication Errors (100-199)**
    AUTHENTICATION_FAILED(100, HttpStatus.UNAUTHORIZED, "Authentication failed"),
    ACCESS_DENIED(101, HttpStatus.FORBIDDEN, "Access denied"),

    // **User Management Errors (200-299)**
    USER_NOT_FOUND(200, HttpStatus.NOT_FOUND, "User not found"),
    EMAIL_ALREADY_REGISTERED(201, HttpStatus.CONFLICT, "Email is already registered"),
    INVALID_TOKEN(202, HttpStatus.BAD_REQUEST, "Invalid token"),
    TOKEN_EXPIRED(203, HttpStatus.BAD_REQUEST, "Token has expired"),
    PASSWORD_TOO_WEAK(204, HttpStatus.BAD_REQUEST, "Password is too weak"),
    PASSWORDS_DO_NOT_MATCH(205, HttpStatus.BAD_REQUEST, "Passwords do not match"),
    USER_DISABLED(206, HttpStatus.FORBIDDEN, "User account is disabled"),
    EMAIL_NOT_VERIFIED(207, HttpStatus.FORBIDDEN, "Email has not been verified"),
    ACCOUNT_LOCKED(208, HttpStatus.FORBIDDEN, "Account is locked"),

    // **Validation Errors (300-399)**
    INCORRECT_CURRENT_PASSWORD(300, HttpStatus.BAD_REQUEST, "Incorrect current password"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST, "New password does not match"),
    INVALID_INPUT(302, HttpStatus.BAD_REQUEST, "Invalid input provided"),
    MISSING_REQUIRED_FIELD(303, HttpStatus.BAD_REQUEST, "Missing required field"),

    // **External Service Errors (400-499)**
    EXTERNAL_SERVICE_FAILURE(400, HttpStatus.SERVICE_UNAVAILABLE, "External service is unavailable"),
    EXTERNAL_SERVICE_TIMEOUT(401, HttpStatus.GATEWAY_TIMEOUT, "External service request timed out"),

    // **Database Errors (500-599)**
    DATABASE_CONNECTION_FAILED(500, HttpStatus.INTERNAL_SERVER_ERROR, "Database connection failed"),
    DATABASE_TRANSACTION_FAILED(501, HttpStatus.INTERNAL_SERVER_ERROR, "Database transaction failed"),

    // **Other Errors (600-699)**
    BAD_CREDENTIALS(600, HttpStatus.UNAUTHORIZED, "Bad credentials");

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
```

### **Benefits of Enhancements**

- **Improved Readability:** Categorizing error codes makes it easier to locate and manage them.
- **Scalability:** A structured numbering scheme allows for easy expansion as the application grows.
- **Consistency:** Consistent naming and numbering conventions reduce confusion and potential errors.

---

## **3. `ExceptionResponse` Class**

### **Purpose**

The `ExceptionResponse` class defines the structure of the JSON response sent to clients when an exception occurs. It ensures that all error responses are consistent and contain relevant information about the error, facilitating easier debugging and user comprehension.

### **Current Implementation**

```java
package com.wchamara.book.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {

    private Integer businessErrorCode;

    private String businessErrorDescription;

    private String error;

    private Set<String> validationErrors;

    private Map<String, String> errors;
}
```

### **Strengths**

- **Flexibility:** The class accommodates various types of error information, including business error codes, descriptions, general errors, validation errors, and field-specific errors.
- **Clean JSON Responses:** The `@JsonInclude(JsonInclude.Include.NON_EMPTY)` annotation ensures that only non-empty fields are included in the JSON response, making responses cleaner and more concise.
- **Builder Pattern:** Leveraging Lombok's `@Builder` makes it easy to construct instances of `ExceptionResponse` in a readable and maintainable way.
- **Immutability:** The use of `@Getter` and `@Setter` provides controlled access to fields, enhancing encapsulation.

### **Areas for Improvement**

1. **Redundancy and Overlapping Fields:**
   - Fields like `error`, `validationErrors`, and `errors` may overlap in purpose, leading to potential confusion.
   - Clarify the distinction between these fields or streamline them for better clarity.

2. **Field Naming Consistency:**
   - Ensure consistent naming conventions for fields to avoid ambiguity (e.g., `error` vs. `businessErrorDescription`).

3. **Documentation:**
   - Add JavaDoc comments to explain the purpose of each field.

### **Suggested Enhancements**

1. **Streamline Fields:**
   - Define clear purposes for each field to prevent overlap.
   - For instance, use `error` for general error messages, `validationErrors` for input validation issues, and `businessErrorCode`/`businessErrorDescription` for business logic errors.

2. **Consistent Naming Conventions:**
   - Use camelCase consistently and ensure that field names clearly indicate their purpose.

3. **Add JavaDoc Comments:**
   - Enhance readability and maintainability by documenting each field.

4. **Extend for Internationalization (i18n):**
   - Consider supporting multiple languages by externalizing error messages.

### **Enhanced `ExceptionResponse` Class Example**

```java
package com.wchamara.book.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;
import java.util.Set;

/**
 * DTO representing the structure of an exception response sent to clients.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {

    /**
     * Unique business-specific error code.
     */
    private Integer businessErrorCode;

    /**
     * Description corresponding to the business error code.
     */
    private String businessErrorDescription;

    /**
     * General error message, typically technical details.
     */
    private String error;

    /**
     * Set of validation error messages, useful for input validation failures.
     */
    private Set<String> validationErrors;

    /**
     * Map of field-specific errors, where the key is the field name and the value is the error message.
     */
    private Map<String, String> fieldErrors;
}
```

### **Benefits of Enhancements**

- **Clarity:** Clear documentation and streamlined fields make the response structure easier to understand.
- **Maintainability:** Well-documented and organized code facilitates future modifications and debugging.
- **User Experience:** Consistent and clear error responses improve the client's ability to handle errors gracefully.

### **Example Usage**

Suppose a client sends a request with invalid input during user registration. The `MethodArgumentNotValidException` handler will capture the validation errors and respond with an `ExceptionResponse` containing the relevant validation error messages.

**Example JSON Response:**

```json
{
    "validationErrors": [
        "Email format is invalid",
        "Password must be at least 8 characters long"
    ]
}
```

---

## **4. `GlobalExceptionHandler` Class**

### **Purpose**

The `GlobalExceptionHandler` class is annotated with `@RestControllerAdvice`, making it a centralized location for handling exceptions thrown by controllers across the entire application. It intercepts specific exceptions and maps them to appropriate `ExceptionResponse` instances, ensuring that clients receive uniform and meaningful error responses.

### **Current Implementation**

```java
package com.wchamara.book.handlers;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

import static com.wchamara.book.handlers.BusinessErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleException(LockedException exception) {
        return ResponseEntity.status(UNAUTHORIZED).body(
                ExceptionResponse.builder()
                        .businessErrorCode(ACCOUNT_LOCKED.getCode())
                        .businessErrorDescription(ACCOUNT_LOCKED.getDescription())
                        .error(exception.getMessage())
                        .build()
        );

    }


    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handleException(DisabledException exception) {
        return ResponseEntity.status(UNAUTHORIZED).body(
                ExceptionResponse.builder()
                        .businessErrorCode(ACCOUNT_DISABLED.getCode())
                        .businessErrorDescription(ACCOUNT_DISABLED.getDescription())
                        .error(exception.getMessage())
                        .build()
        );

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exception) {
        return ResponseEntity.status(UNAUTHORIZED).body(
                ExceptionResponse.builder()
                        .businessErrorCode(BAD_CREDENTIALS.getCode())
                        .businessErrorDescription(BAD_CREDENTIALS.getDescription())
                        .error(exception.getMessage())
                        .build()
        );

    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleException(MessagingException exception) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                ExceptionResponse.builder()
                        .error(exception.getMessage())
                        .build()
        );

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exception) {

        Set<String> errors = new HashSet<>();

        exception.getBindingResult().getFieldErrors().forEach(
                fieldError -> errors.add(fieldError.getDefaultMessage())
        );

        return ResponseEntity.status(BAD_REQUEST).body(
                ExceptionResponse.builder()
                        .validationErrors(errors)
                        .build()
        );

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {

        exception.printStackTrace();

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                ExceptionResponse.builder()
                        .businessErrorDescription("Internal server error please contact support")
                        .error(exception.getMessage())
                        .build()
        );


    }

}
```

### **Strengths**

- **Centralized Handling:** All exceptions are handled in one place, promoting consistency and reducing redundancy.
- **Specific Handlers:** Handles specific exceptions (`LockedException`, `DisabledException`, `BadCredentialsException`, etc.) to provide tailored responses.
- **Fallback Handler:** A general `Exception` handler ensures that unforeseen exceptions are caught and managed gracefully.
- **Consistent Response Structure:** Utilizes the `ExceptionResponse` class to maintain a uniform response format.

### **Areas for Improvement**

1. **HTTP Status Alignment:**
   - Some HTTP status codes used may not align with the exception's nature (e.g., `LockedException` and `DisabledException` returning `UNAUTHORIZED` instead of more appropriate statuses).

2. **Redundancy and Repetition:**
   - Similar exception handlers share common response-building logic, leading to code duplication.

3. **Logging and Monitoring:**
   - Currently, only the generic `Exception` handler prints the stack trace. Other handlers lack logging, which is vital for monitoring and debugging.

4. **Security Considerations:**
   - Returning `exception.getMessage()` can potentially expose sensitive information.

5. **Validation Error Details:**
   - The `validationErrors` field only contains error messages without field names, which might not be sufficient for clients to identify and correct issues.

6. **Handling of `MessagingException`:**
   - The `MessagingException` handler doesn't utilize `BusinessErrorCodes`, leading to inconsistency in error responses.

### **Suggested Enhancements**

1. **Align HTTP Status Codes Appropriately:**
   - Use `FORBIDDEN` (403) for access-related issues like `LockedException` and `DisabledException` instead of `UNAUTHORIZED` (401).
   - `UNAUTHORIZED` should be reserved for authentication failures.

2. **Reduce Code Duplication:**
   - Implement helper methods to build `ExceptionResponse` objects, minimizing repetition.

3. **Implement Comprehensive Logging:**
   - Use a logging framework (e.g., SLF4J with Logback) to log exceptions consistently across all handlers.
   - Avoid printing stack traces directly; instead, log them appropriately.

4. **Enhance Security:**
   - Avoid exposing detailed exception messages to clients to prevent information leakage.
   - Provide generic error messages for unexpected exceptions.

5. **Improve Validation Error Details:**
   - Include field names alongside error messages to aid clients in identifying issues.

6. **Utilize `BusinessErrorCodes` Consistently:**
   - Ensure that all handled exceptions utilize `BusinessErrorCodes` for uniformity.

7. **Internationalization (i18n):**
   - Consider supporting multiple languages for error messages if your application serves a global audience.

8. **Handle Additional Exceptions:**
   - Identify and handle other relevant exceptions to cover more scenarios.

### **Enhanced `GlobalExceptionHandler` Class Example**

```java
package com.wchamara.book.handlers;

import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

import static com.wchamara.book.handlers.BusinessErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

/**
 * Global exception handler for the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles LockedException thrown when a user's account is locked.
     *
     * @param exception the LockedException
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleLockedException(LockedException exception) {
        logger.warn("Account locked: {}", exception.getMessage());
        return buildResponseEntity(ACCOUNT_LOCKED, exception.getMessage());
    }

    /**
     * Handles DisabledException thrown when a user's account is disabled.
     *
     * @param exception the DisabledException
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handleDisabledException(DisabledException exception) {
        logger.warn("Account disabled: {}", exception.getMessage());
        return buildResponseEntity(USER_DISABLED, exception.getMessage());
    }

    /**
     * Handles BadCredentialsException thrown during authentication failures.
     *
     * @param exception the BadCredentialsException
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException exception) {
        logger.warn("Bad credentials: {}", exception.getMessage());
        return buildResponseEntity(BAD_CREDENTIALS, "Invalid username or password");
    }

    /**
     * Handles MessagingException thrown during email operations.
     *
     * @param exception the MessagingException
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleMessagingException(MessagingException exception) {
        logger.error("Messaging exception: {}", exception.getMessage(), exception);
        return buildResponseEntity(UNKNOWN_ERROR, "Failed to process email request");
    }

    /**
     * Handles MethodArgumentNotValidException thrown during input validation failures.
     *
     * @param exception the MethodArgumentNotValidException
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        logger.warn("Validation failed: {}", exception.getMessage());

        Set<String> errors = new HashSet<>();
        exception.getBindingResult().getFieldErrors().forEach(
                fieldError -> errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage())
        );

        ExceptionResponse response = ExceptionResponse.builder()
                .validationErrors(errors)
                .build();

        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    /**
     * Handles all other exceptions not explicitly handled by other methods.
     *
     * @param exception the Exception
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        logger.error("Unhandled exception: {}", exception.getMessage(), exception);

        ExceptionResponse response = ExceptionResponse.builder()
                .businessErrorDescription("Internal server error. Please contact support.")
                .build();

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Builds a ResponseEntity with the given BusinessErrorCode and error message.
     *
     * @param errorCode the BusinessErrorCode
     * @param errorMsg  the error message
     * @return ResponseEntity containing the ExceptionResponse
     */
    private ResponseEntity<ExceptionResponse> buildResponseEntity(BusinessErrorCodes errorCode, String errorMsg) {
        ExceptionResponse response = ExceptionResponse.builder()
                .businessErrorCode(errorCode.getCode())
                .businessErrorDescription(errorCode.getDescription())
                .error(errorMsg)
                .build();

        return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
    }
}
```

### **Key Enhancements**

1. **Logging Improvements:**
   - Utilizes SLF4J's `Logger` to log exceptions at appropriate levels (`warn` for client-related issues, `error` for server-side issues).
   - Includes exception messages and stack traces where relevant for better traceability.

2. **HTTP Status Alignment:**
   - Correctly maps exceptions to their appropriate HTTP status codes based on the `BusinessErrorCodes` enum.
   - For example, `LockedException` and `DisabledException` now return `FORBIDDEN` (403) instead of `UNAUTHORIZED` (401), aligning with HTTP semantics.

3. **Security Enhancements:**
   - Avoids exposing detailed exception messages to clients, especially for sensitive errors like `BadCredentialsException`, by providing generic messages.
   - Prevents potential information leakage by not returning raw exception messages for critical errors.

4. **Reduced Code Duplication:**
   - Introduces a helper method `buildResponseEntity` to construct `ResponseEntity<ExceptionResponse>` instances, minimizing repetition across handlers.

5. **Enhanced Validation Error Details:**
   - Includes field names alongside error messages in `validationErrors` to aid clients in identifying and correcting issues.

6. **Consistent Use of `BusinessErrorCodes`:**
   - Ensures that all handled exceptions utilize `BusinessErrorCodes` for uniformity in error responses.
   - For exceptions not mapped in `BusinessErrorCodes`, uses a generic `UNKNOWN_ERROR` code.

7. **Exception Handling for External Services:**
   - Specifically handles `MessagingException` to manage email-related failures gracefully.

8. **Fallback Exception Handler:**
   - Catches all other exceptions, ensuring that the application doesn't expose stack traces or sensitive information to clients.

### **Benefits of Enhancements**

- **Improved Readability and Maintainability:** Organized and documented code with reduced redundancy makes the codebase easier to understand and maintain.
- **Enhanced Security:** By avoiding the exposure of sensitive exception details, the application mitigates potential security risks.
- **Better Client Experience:** Clear and consistent error responses help clients handle errors more effectively.
- **Scalability:** The structured approach allows for easy addition of new exception handlers and error codes as the application grows.

### **Example Scenarios and Responses**

Let's walk through some concrete examples to illustrate how the enhanced exception handling works in practice.

#### **a. Handling a Locked Account (`LockedException`)**

**Scenario:** A user attempts to log in to their account, but their account is locked due to multiple failed login attempts.

**Exception Thrown:**
```java
throw new LockedException("Your account has been locked due to multiple failed login attempts.");
```

**Response Sent to Client:**
```json
{
    "businessErrorCode": 208,
    "businessErrorDescription": "Account is locked",
    "error": "Your account has been locked due to multiple failed login attempts."
}
```

**HTTP Status:** `403 FORBIDDEN`

**Explanation:** The `LockedException` is caught by the `handleLockedException` method, which maps it to the `ACCOUNT_LOCKED` error code and returns a `403 FORBIDDEN` status with a relevant error message.

#### **b. Handling Bad Credentials (`BadCredentialsException`)**

**Scenario:** A user provides incorrect credentials during authentication.

**Exception Thrown:**
```java
throw new BadCredentialsException("Invalid username or password.");
```

**Response Sent to Client:**
```json
{
    "businessErrorCode": 600,
    "businessErrorDescription": "Bad credentials",
    "error": "Invalid username or password"
}
```

**HTTP Status:** `401 UNAUTHORIZED`

**Explanation:** The `BadCredentialsException` is handled by the `handleBadCredentialsException` method, mapping it to the `BAD_CREDENTIALS` error code and returning a `401 UNAUTHORIZED` status with a generic error message to avoid exposing sensitive information.

#### **c. Handling Validation Errors (`MethodArgumentNotValidException`)**

**Scenario:** A user submits a registration form with invalid input, such as an improperly formatted email and a weak password.

**Exception Thrown:**
```java
throw new MethodArgumentNotValidException(bindingResult);
```

**Response Sent to Client:**
```json
{
    "validationErrors": [
        "email: Email format is invalid",
        "password: Password must be at least 8 characters long"
    ]
}
```

**HTTP Status:** `400 BAD_REQUEST`

**Explanation:** The `MethodArgumentNotValidException` is handled by the `handleMethodArgumentNotValidException` method, which collects field-specific validation errors and returns them in the `validationErrors` set. Including field names alongside error messages aids the client in identifying and correcting the issues.

#### **d. Handling Messaging Errors (`MessagingException`)**

**Scenario:** The application fails to send an activation email due to SMTP server issues.

**Exception Thrown:**
```java
throw new MessagingException("Failed to send activation email.");
```

**Response Sent to Client:**
```json
{
    "error": "Failed to process email request"
}
```

**HTTP Status:** `503 SERVICE_UNAVAILABLE` (assuming `UNKNOWN_ERROR` maps to `SERVICE_UNAVAILABLE`)

**Explanation:** The `MessagingException` is caught by the `handleMessagingException` method, which maps it to a generic `UNKNOWN_ERROR` with a non-specific error message to prevent exposing internal details.

#### **e. Handling Unknown Exceptions (`Exception`)**

**Scenario:** An unexpected `NullPointerException` occurs within the application.

**Exception Thrown:**
```java
throw new NullPointerException("Unexpected null value encountered.");
```

**Response Sent to Client:**
```json
{
    "businessErrorDescription": "Internal server error. Please contact support."
}
```

**HTTP Status:** `500 INTERNAL_SERVER_ERROR`

**Explanation:** The generic `Exception` handler catches all unforeseen exceptions, logs the error, and returns a generic message to the client to avoid revealing sensitive information about the application's internals.

---

## **5. Best Practices and Recommendations**

To further enhance your exception handling mechanism, consider the following best practices and recommendations:

### **a. Align HTTP Status Codes Appropriately**

Ensure that the HTTP status codes used in responses accurately reflect the nature of the error:

- **401 Unauthorized:** Authentication failures (e.g., invalid credentials).
- **403 Forbidden:** Authorization failures (e.g., access to a resource is denied).
- **400 Bad Request:** Client-side input validation errors.
- **500 Internal Server Error:** Server-side errors.

**Example Adjustment:**

```java
@ExceptionHandler(LockedException.class)
public ResponseEntity<ExceptionResponse> handleLockedException(LockedException exception) {
    logger.warn("Account locked: {}", exception.getMessage());
    return buildResponseEntity(ACCOUNT_LOCKED, "Your account is locked. Please contact support.");
}
```

Ensure that `ACCOUNT_LOCKED` maps to `FORBIDDEN (403)` as per the enhanced `BusinessErrorCodes` enum.

### **b. Implement Comprehensive Logging**

Proper logging is essential for monitoring, debugging, and auditing:

- **Use Logging Frameworks:** Utilize SLF4J with Logback or Log4j2 for structured and configurable logging.
- **Log at Appropriate Levels:**
  - **WARN:** For client-related issues like validation failures or authentication errors.
  - **ERROR:** For server-side issues like exceptions that should not occur during normal operations.
- **Include Stack Traces for Debugging:** For `ERROR` level logs, include stack traces to aid in debugging.

**Example Logging:**

```java
@ExceptionHandler(Exception.class)
public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
    logger.error("Unhandled exception: {}", exception.getMessage(), exception);
    ExceptionResponse response = ExceptionResponse.builder()
            .businessErrorDescription("Internal server error. Please contact support.")
            .build();
    return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(response);
}
```

### **c. Enhance Security by Avoiding Information Leakage**

- **Generic Error Messages:** Avoid sending detailed exception messages to clients, especially for sensitive errors.
- **Sanitize Error Responses:** Ensure that error responses do not contain stack traces, SQL queries, or any sensitive internal information.
- **Consistent Error Responses:** Use a standardized error response structure to prevent unintended information disclosure.

**Example Adjustment:**

```java
@ExceptionHandler(BadCredentialsException.class)
public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException exception) {
    logger.warn("Bad credentials: {}", exception.getMessage());
    return buildResponseEntity(BAD_CREDENTIALS, "Invalid username or password.");
}
```

### **d. Reduce Code Duplication with Helper Methods**

Implement helper methods to construct `ExceptionResponse` objects, reducing repetition and enhancing maintainability.

**Example Helper Method:**

```java
/**
 * Builds a ResponseEntity with the given BusinessErrorCode and error message.
 *
 * @param errorCode the BusinessErrorCode
 * @param errorMsg  the error message
 * @return ResponseEntity containing the ExceptionResponse
 */
private ResponseEntity<ExceptionResponse> buildResponseEntity(BusinessErrorCodes errorCode, String errorMsg) {
    ExceptionResponse response = ExceptionResponse.builder()
            .businessErrorCode(errorCode.getCode())
            .businessErrorDescription(errorCode.getDescription())
            .error(errorMsg)
            .build();

    return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
}
```

Use this helper method in exception handlers to streamline response construction.

### **e. Provide Detailed Validation Errors**

When handling validation exceptions, include both field names and error messages to help clients identify and correct issues effectively.

**Example Adjustment:**

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    logger.warn("Validation failed: {}", exception.getMessage());

    Set<String> errors = new HashSet<>();
    exception.getBindingResult().getFieldErrors().forEach(
            fieldError -> errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage())
    );

    ExceptionResponse response = ExceptionResponse.builder()
            .validationErrors(errors)
            .build();

    return ResponseEntity.status(BAD_REQUEST).body(response);
}
```

**Example JSON Response:**

```json
{
    "validationErrors": [
        "email: Email format is invalid",
        "password: Password must be at least 8 characters long"
    ]
}
```

### **f. Handle Additional Exceptions**

Identify and handle other relevant exceptions to cover more scenarios, enhancing the robustness of your application.

**Examples:**

- **`AccessDeniedException`:** When a user lacks permissions to access a resource.
- **`DataIntegrityViolationException`:** When database constraints are violated.
- **`HttpMessageNotReadableException`:** When request bodies are malformed.

**Example Handler:**

```java
import org.springframework.security.access.AccessDeniedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;

@ExceptionHandler(AccessDeniedException.class)
public ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException exception) {
    logger.warn("Access denied: {}", exception.getMessage());
    return buildResponseEntity(ACCESS_DENIED, "You do not have permission to perform this action.");
}

@ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
    logger.error("Data integrity violation: {}", exception.getMessage(), exception);
    return buildResponseEntity(DATABASE_TRANSACTION_FAILED, "A data integrity violation occurred.");
}

@ExceptionHandler(HttpMessageNotReadableException.class)
public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
    logger.warn("Malformed JSON request: {}", exception.getMessage());
    return buildResponseEntity(INVALID_INPUT, "Malformed JSON request.");
}
```

### **g. Internationalization (i18n) Support**

If your application serves a global audience, consider supporting multiple languages for error messages.

**Implementation Steps:**

1. **Externalize Messages:** Move error descriptions to message properties files (e.g., `messages_en.properties`, `messages_es.properties`).
2. **Use `MessageSource`:** Inject `MessageSource` to retrieve localized messages based on the client's locale.
3. **Modify `BusinessErrorCodes` to Support Message Keys:** Instead of hardcoding descriptions, use message keys.

**Example Adjustment:**

```java
// BusinessErrorCodes Enum
public enum BusinessErrorCodes {
    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "error.no_code"),
    AUTHENTICATION_FAILED(100, HttpStatus.UNAUTHORIZED, "error.authentication_failed"),
    // ... other codes
}

// ExceptionResponse Builder in GlobalExceptionHandler
@Autowired
private MessageSource messageSource;

private ResponseEntity<ExceptionResponse> buildResponseEntity(BusinessErrorCodes errorCode, String errorMsg, Locale locale) {
    String localizedDescription = messageSource.getMessage(errorCode.getDescription(), null, locale);
    ExceptionResponse response = ExceptionResponse.builder()
            .businessErrorCode(errorCode.getCode())
            .businessErrorDescription(localizedDescription)
            .error(errorMsg)
            .build();

    return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
}
```

### **h. Use Custom Exceptions for Better Abstraction**

Instead of directly handling framework-specific exceptions, define custom exceptions to abstract underlying implementation details.

**Example Custom Exception:**

```java
package com.wchamara.book.exception;

import com.wchamara.book.handlers.BusinessErrorCodes;
import lombok.Getter;

/**
 * Custom exception representing business-specific errors.
 */
@Getter
public class BusinessException extends RuntimeException {
    private final BusinessErrorCodes errorCode;

    public BusinessException(BusinessErrorCodes errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public BusinessException(BusinessErrorCodes errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
```

**Usage in Service Layer:**

```java
package com.wchamara.book.service;

import com.wchamara.book.exception.BusinessException;
import com.wchamara.book.handlers.BusinessErrorCodes;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void updatePassword(String userId, String currentPassword, String newPassword) {
        // Assume some logic to verify current password
        boolean isPasswordCorrect = verifyCurrentPassword(userId, currentPassword);
        if (!isPasswordCorrect) {
            throw new BusinessException(BusinessErrorCodes.INCORRECT_CURRENT_PASSWORD);
        }

        // Further logic to update password
    }

    private boolean verifyCurrentPassword(String userId, String currentPassword) {
        // Placeholder for password verification logic
        return false;
    }
}
```

**Handler Adjustment:**

```java
@ExceptionHandler(BusinessException.class)
public ResponseEntity<ExceptionResponse> handleBusinessException(BusinessException exception) {
    logger.warn("Business exception: {}", exception.getMessage());
    BusinessErrorCodes errorCode = exception.getErrorCode();
    return buildResponseEntity(errorCode, exception.getMessage());
}
```

### **Benefits of Enhancements**

- **Abstraction:** Custom exceptions abstract underlying frameworks, making the application less coupled and easier to maintain.
- **Flexibility:** Facilitates handling a broader range of error scenarios with precise control.
- **Scalability:** Simplifies adding new error types without modifying existing exception handlers.

---

## **6. Code Quality and Performance Improvements**

### **a. Code Consistency and Readability**

- **Consistent Naming Conventions:** Ensure that enum constants, method names, and variables follow consistent naming conventions (e.g., `ACCOUNT_LOCKED` vs. `ACCOUNT_DISABLED`).
- **Proper Indentation and Formatting:** Maintain consistent code formatting for better readability.
- **Use of Lombok:** Continue leveraging Lombok to reduce boilerplate code but be cautious of overusing it, which can obscure the code's behavior.

### **b. Avoid Unnecessary Object Creation**

- **Immutable ExceptionResponse:** Consider making `ExceptionResponse` immutable by removing setters and using only getters with the builder pattern. This enhances thread safety and predictability.

**Example Adjustment:**

```java
@Getter
@Builder
@AllArgsConstructor
public class ExceptionResponse {

    private final Integer businessErrorCode;
    private final String businessErrorDescription;
    private final String error;
    private final Set<String> validationErrors;
    private final Map<String, String> fieldErrors;
}
```

### **c. Optimize Validation Error Collection**

- **Stream API Optimization:** Utilize Java Streams more effectively for collecting validation errors.

**Example Adjustment:**

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    logger.warn("Validation failed: {}", exception.getMessage());

    Set<String> errors = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
            .collect(Collectors.toSet());

```   

Your implementation of exception handling in your Spring Boot application demonstrates a thoughtful approach to managing and responding to various error scenarios in a consistent and structured manner. By leveraging Spring's `@RestControllerAdvice` and custom error codes, you ensure that clients receive clear and meaningful error responses. However, there are several areas where you can enhance this implementation to improve code quality, performance, security, and maintainability. Let's delve deeply into each component, explore how they interact, and discuss best practices and improvements with concrete examples.

---

## **1. Overview of Exception Handling in Spring Boot**

Exception handling is crucial in any application to manage unexpected scenarios gracefully, provide meaningful feedback to users, and maintain application stability. In Spring Boot, centralized exception handling can be achieved using `@ControllerAdvice` or `@RestControllerAdvice`, which allows you to intercept exceptions thrown by controllers and provide uniform responses.

Your implementation comprises three main components:

1. **`BusinessErrorCodes` Enum:** Defines business-specific error codes with associated HTTP statuses and descriptions.
2. **`ExceptionResponse` Class:** Represents the structure of the error response sent to clients.
3. **`GlobalExceptionHandler` Class:** Handles various exceptions and maps them to appropriate `ExceptionResponse` instances.

Let's explore each component in detail.

---

## **2. `BusinessErrorCodes` Enum**

### **Purpose**

The `BusinessErrorCodes` enum serves as a centralized repository for all business-related error codes, ensuring consistency across your application. Each enum constant encapsulates:

- **Code (`int`):** A unique numerical identifier for the error.
- **Description (`String`):** A human-readable message describing the error.
- **HTTP Status (`HttpStatus`):** The corresponding HTTP status code to be returned to the client.

### **Current Implementation**

```java
package com.wchamara.book.handlers;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessErrorCodes {
    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "No code"),
    INCORRECT_CURRENT_PASSWORD(300, HttpStatus.BAD_REQUEST, "Incorrect current password"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST, "new password does not match"),
    ACCOUNT_LOCKED(302, HttpStatus.FORBIDDEN, "Account is locked"),
    ACCOUNT_DISABLED(303, HttpStatus.FORBIDDEN, "Account is disabled"),
    // **General Errors**
    BAD_CREDENTIALS(304, HttpStatus.UNAUTHORIZED, "Bad credentials");


    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
```

### **Strengths**

- **Centralization:** All business error codes are defined in one place, promoting consistency and ease of maintenance.
- **Clarity:** Each error code has a clear and descriptive name, making it easy to understand the associated error.
- **HTTP Alignment:** Each error code is mapped to an appropriate `HttpStatus`, ensuring that clients receive the correct HTTP response.

### **Areas for Improvement**

1. **Code Uniqueness and Scalability:**
   - **Uniqueness:** Ensure that all error codes are unique to prevent ambiguity.
   - **Scalability:** Plan for scalability by reserving ranges for different modules or error categories.

2. **Enum Naming Convention:**
   - Follow a consistent naming convention (e.g., all uppercase with underscores) for enum constants.

3. **Additional Error Codes:**
   - Introduce more error codes to handle various scenarios, such as database errors, authorization failures, and more.

4. **Documentation:**
   - Add JavaDoc comments to each enum constant to provide additional context.

### **Suggested Enhancements**

1. **Categorize Error Codes:**
   - Organize error codes into logical groups (e.g., Authentication, User Management, Validation) to improve readability and maintainability.

2. **Consistent Numbering:**
   - Use a numbering scheme that reflects the category and severity of errors. For example:
     - **100-199:** Authentication Errors
     - **200-299:** User Management Errors
     - **300-399:** Validation Errors
     - **400-499:** External Service Errors

3. **Expand Error Codes:**
   - Introduce more error codes to cover additional scenarios.

4. **JavaDoc Comments:**
   - Provide JavaDoc comments for each enum constant to enhance understanding.

### **Enhanced `BusinessErrorCodes` Enum Example**

```java
package com.wchamara.book.handlers;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Enum representing business-specific error codes with corresponding HTTP statuses and descriptions.
 */
@Getter
public enum BusinessErrorCodes {
    // **General Errors (0-99)**
    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "No code provided"),
    UNKNOWN_ERROR(1, HttpStatus.INTERNAL_SERVER_ERROR, "An unknown error has occurred"),

    // **Authentication Errors (100-199)**
    AUTHENTICATION_FAILED(100, HttpStatus.UNAUTHORIZED, "Authentication failed"),
    ACCESS_DENIED(101, HttpStatus.FORBIDDEN, "Access denied"),

    // **User Management Errors (200-299)**
    USER_NOT_FOUND(200, HttpStatus.NOT_FOUND, "User not found"),
    EMAIL_ALREADY_REGISTERED(201, HttpStatus.CONFLICT, "Email is already registered"),
    INVALID_TOKEN(202, HttpStatus.BAD_REQUEST, "Invalid token"),
    TOKEN_EXPIRED(203, HttpStatus.BAD_REQUEST, "Token has expired"),
    PASSWORD_TOO_WEAK(204, HttpStatus.BAD_REQUEST, "Password is too weak"),
    PASSWORDS_DO_NOT_MATCH(205, HttpStatus.BAD_REQUEST, "Passwords do not match"),
    USER_DISABLED(206, HttpStatus.FORBIDDEN, "User account is disabled"),
    EMAIL_NOT_VERIFIED(207, HttpStatus.FORBIDDEN, "Email has not been verified"),
    ACCOUNT_LOCKED(208, HttpStatus.FORBIDDEN, "Account is locked"),

    // **Validation Errors (300-399)**
    INCORRECT_CURRENT_PASSWORD(300, HttpStatus.BAD_REQUEST, "Incorrect current password"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST, "New password does not match"),
    INVALID_INPUT(302, HttpStatus.BAD_REQUEST, "Invalid input provided"),
    MISSING_REQUIRED_FIELD(303, HttpStatus.BAD_REQUEST, "Missing required field"),

    // **External Service Errors (400-499)**
    EXTERNAL_SERVICE_FAILURE(400, HttpStatus.SERVICE_UNAVAILABLE, "External service is unavailable"),
    EXTERNAL_SERVICE_TIMEOUT(401, HttpStatus.GATEWAY_TIMEOUT, "External service request timed out"),

    // **Database Errors (500-599)**
    DATABASE_CONNECTION_FAILED(500, HttpStatus.INTERNAL_SERVER_ERROR, "Database connection failed"),
    DATABASE_TRANSACTION_FAILED(501, HttpStatus.INTERNAL_SERVER_ERROR, "Database transaction failed"),

    // **Other Errors (600-699)**
    BAD_CREDENTIALS(600, HttpStatus.UNAUTHORIZED, "Bad credentials");

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
```

### **Benefits of Enhancements**

- **Improved Readability:** Categorizing error codes makes it easier to locate and manage them.
- **Scalability:** A structured numbering scheme allows for easy expansion as the application grows.
- **Consistency:** Consistent naming and numbering conventions reduce confusion and potential errors.
- **Better Documentation:** JavaDoc comments provide additional context, aiding future developers and maintainers.

---

## **3. `ExceptionResponse` Class**

### **Purpose**

The `ExceptionResponse` class defines the structure of the JSON response sent to clients when an exception occurs. It ensures that all error responses are consistent and contain relevant information about the error, facilitating easier debugging and user comprehension.

### **Current Implementation**

```java
package com.wchamara.book.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {

    private Integer businessErrorCode;

    private String businessErrorDescription;

    private String error;

    private Set<String> validationErrors;

    private Map<String, String> errors;
}
```

### **Strengths**

- **Flexibility:** The class accommodates various types of error information, including business error codes, descriptions, general errors, validation errors, and field-specific errors.
- **Clean JSON Responses:** The `@JsonInclude(JsonInclude.Include.NON_EMPTY)` annotation ensures that only non-empty fields are included in the JSON response, making responses cleaner and more concise.
- **Builder Pattern:** Leveraging Lombok's `@Builder` makes it easy to construct instances of `ExceptionResponse` in a readable and maintainable way.
- **Immutability:** The use of `@Getter` and `@Setter` provides controlled access to fields, enhancing encapsulation.

### **Areas for Improvement**

1. **Redundancy and Overlapping Fields:**
   - Fields like `error`, `validationErrors`, and `errors` may overlap in purpose, leading to potential confusion.
   - Clarify the distinction between these fields or streamline them for better clarity.

2. **Field Naming Consistency:**
   - Ensure consistent naming conventions for fields to avoid ambiguity (e.g., `error` vs. `businessErrorDescription`).

3. **Documentation:**
   - Add JavaDoc comments to explain the purpose of each field.

4. **Extend for Internationalization (i18n):**
   - Consider supporting multiple languages by externalizing error messages.

### **Suggested Enhancements**

1. **Streamline Fields:**
   - Define clear purposes for each field to prevent overlap.
   - For instance, use `error` for general error messages, `validationErrors` for input validation issues, and `businessErrorCode`/`businessErrorDescription` for business logic errors.

2. **Consistent Naming Conventions:**
   - Use camelCase consistently and ensure that field names clearly indicate their purpose.

3. **Add JavaDoc Comments:**
   - Enhance readability and maintainability by documenting each field.

4. **Immutable Design:**
   - Make the class immutable by removing setters and only providing getters, enhancing thread safety and predictability.

5. **Internationalization (i18n) Support:**
   - Externalize error messages to support multiple languages.

### **Enhanced `ExceptionResponse` Class Example**

```java
package com.wchamara.book.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;
import java.util.Set;

/**
 * DTO representing the structure of an exception response sent to clients.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {

    /**
     * Unique business-specific error code.
     */
    private Integer businessErrorCode;

    /**
     * Description corresponding to the business error code.
     */
    private String businessErrorDescription;

    /**
     * General error message, typically technical details.
     */
    private String error;

    /**
     * Set of validation error messages, useful for input validation failures.
     */
    private Set<String> validationErrors;

    /**
     * Map of field-specific errors, where the key is the field name and the value is the error message.
     */
    private Map<String, String> fieldErrors;
}
```

### **Benefits of Enhancements**

- **Clarity:** Clear documentation and streamlined fields make the response structure easier to understand.
- **Maintainability:** Well-documented and organized code facilitates future modifications and debugging.
- **User Experience:** Consistent and clear error responses improve the client's ability to handle errors gracefully.
- **Thread Safety:** Immutable design enhances thread safety, especially important in concurrent environments.

### **Example Usage**

Suppose a client sends a request with invalid input during user registration, such as a weak password and mismatched password confirmation. The `MethodArgumentNotValidException` handler will capture the validation errors and respond with an `ExceptionResponse` containing the relevant validation error messages.

**Example JSON Response:**

```json
{
    "validationErrors": [
        "email: Email format is invalid",
        "password: Password must be at least 8 characters long"
    ]
}
```

---

## **4. `GlobalExceptionHandler` Class**

### **Purpose**

The `GlobalExceptionHandler` class is annotated with `@RestControllerAdvice`, making it a centralized location for handling exceptions thrown by controllers across the entire application. It intercepts specific exceptions and maps them to appropriate `ExceptionResponse` instances, ensuring that clients receive uniform and meaningful error responses.

### **Current Implementation**

```java
package com.wchamara.book.handlers;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

import static com.wchamara.book.handlers.BusinessErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleException(LockedException exception) {
        return ResponseEntity.status(UNAUTHORIZED).body(
                ExceptionResponse.builder()
                        .businessErrorCode(ACCOUNT_LOCKED.getCode())
                        .businessErrorDescription(ACCOUNT_LOCKED.getDescription())
                        .error(exception.getMessage())
                        .build()
        );

    }


    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handleException(DisabledException exception) {
        return ResponseEntity.status(UNAUTHORIZED).body(
                ExceptionResponse.builder()
                        .businessErrorCode(ACCOUNT_DISABLED.getCode())
                        .businessErrorDescription(ACCOUNT_DISABLED.getDescription())
                        .error(exception.getMessage())
                        .build()
        );

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exception) {
        return ResponseEntity.status(UNAUTHORIZED).body(
                ExceptionResponse.builder()
                        .businessErrorCode(BAD_CREDENTIALS.getCode())
                        .businessErrorDescription(BAD_CREDENTIALS.getDescription())
                        .error(exception.getMessage())
                        .build()
        );

    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleException(MessagingException exception) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                ExceptionResponse.builder()
                        .error(exception.getMessage())
                        .build()
        );

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exception) {

        Set<String> errors = new HashSet<>();

        exception.getBindingResult().getFieldErrors().forEach(
                fieldError -> errors.add(fieldError.getDefaultMessage())
        );

        return ResponseEntity.status(BAD_REQUEST).body(
                ExceptionResponse.builder()
                        .validationErrors(errors)
                        .build()
        );

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {

        exception.printStackTrace();

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                ExceptionResponse.builder()
                        .businessErrorDescription("Internal server error please contact support")
                        .error(exception.getMessage())
                        .build()
        );


    }

}
```

### **Strengths**

- **Centralized Handling:** All exceptions are handled in one place, promoting consistency and reducing redundancy.
- **Specific Handlers:** Handles specific exceptions (`LockedException`, `DisabledException`, `BadCredentialsException`, etc.) to provide tailored responses.
- **Fallback Handler:** A general `Exception` handler ensures that unforeseen exceptions are caught and managed gracefully.
- **Consistent Response Structure:** Utilizes the `ExceptionResponse` class to maintain a uniform response format.

### **Areas for Improvement**

1. **HTTP Status Alignment:**
   - Some HTTP status codes used may not align with the exception's nature (e.g., `LockedException` and `DisabledException` returning `UNAUTHORIZED` instead of more appropriate statuses).

2. **Redundancy and Repetition:**
   - Similar exception handlers share common response-building logic, leading to code duplication.

3. **Logging and Monitoring:**
   - Currently, only the generic `Exception` handler prints the stack trace. Other handlers lack logging, which is vital for monitoring and debugging.

4. **Security Considerations:**
   - Returning `exception.getMessage()` can potentially expose sensitive information.

5. **Validation Error Details:**
   - The `validationErrors` field only contains error messages without field names, which might not be sufficient for clients to identify and correct issues.

6. **Handling of `MessagingException`:**
   - The `MessagingException` handler doesn't utilize `BusinessErrorCodes`, leading to inconsistency in error responses.

### **Suggested Enhancements**

1. **Align HTTP Status Codes Appropriately:**
   - Use `FORBIDDEN` (403) for access-related issues like `LockedException` and `DisabledException` instead of `UNAUTHORIZED` (401).
   - Reserve `UNAUTHORIZED` for authentication failures.

2. **Reduce Code Duplication:**
   - Implement helper methods to build `ExceptionResponse` objects, minimizing repetition across handlers.

3. **Implement Comprehensive Logging:**
   - Use a logging framework (e.g., SLF4J with Logback) to log exceptions consistently across all handlers.
   - Avoid printing stack traces directly; instead, log them appropriately.

4. **Enhance Security:**
   - Avoid exposing detailed exception messages to clients to prevent information leakage.
   - Provide generic error messages for unexpected exceptions.

5. **Improve Validation Error Details:**
   - Include field names alongside error messages to aid clients in identifying issues.

6. **Utilize `BusinessErrorCodes` Consistently:**
   - Ensure that all handled exceptions utilize `BusinessErrorCodes` for uniformity.
   - For exceptions not mapped in `BusinessErrorCodes`, use a generic `UNKNOWN_ERROR` code.

7. **Internationalization (i18n):**
   - Consider supporting multiple languages for error messages if your application serves a global audience.

8. **Use Custom Exceptions for Better Abstraction:**
   - Instead of directly handling framework-specific exceptions, define custom exceptions to abstract underlying implementation details.

### **Enhanced `GlobalExceptionHandler` Class Example**

```java
package com.wchamara.book.handlers;

import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;
import java.util.stream.Collectors;

import static com.wchamara.book.handlers.BusinessErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

/**
 * Global exception handler for the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles LockedException thrown when a user's account is locked.
     *
     * @param exception the LockedException
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleLockedException(LockedException exception) {
        logger.warn("Account locked: {}", exception.getMessage());
        return buildResponseEntity(ACCOUNT_LOCKED, "Your account is locked. Please contact support.");
    }

    /**
     * Handles DisabledException thrown when a user's account is disabled.
     *
     * @param exception the DisabledException
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handleDisabledException(DisabledException exception) {
        logger.warn("Account disabled: {}", exception.getMessage());
        return buildResponseEntity(USER_DISABLED, "Your account is disabled. Please contact support.");
    }

    /**
     * Handles BadCredentialsException thrown during authentication failures.
     *
     * @param exception the BadCredentialsException
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException exception) {
        logger.warn("Bad credentials: {}", exception.getMessage());
        return buildResponseEntity(BAD_CREDENTIALS, "Invalid username or password.");
    }

    /**
     * Handles AccessDeniedException thrown when a user lacks necessary permissions.
     *
     * @param exception the AccessDeniedException
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException exception) {
        logger.warn("Access denied: {}", exception.getMessage());
        return buildResponseEntity(ACCESS_DENIED, "You do not have permission to perform this action.");
    }

    /**
     * Handles MessagingException thrown during email operations.
     *
     * @param exception the MessagingException
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleMessagingException(MessagingException exception) {
        logger.error("Messaging exception: {}", exception.getMessage(), exception);
        return buildResponseEntity(UNKNOWN_ERROR, "Failed to process email request.");
    }

    /**
     * Handles MethodArgumentNotValidException thrown during input validation failures.
     *
     * @param exception the MethodArgumentNotValidException
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        logger.warn("Validation failed: {}", exception.getMessage());

        Set<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toSet());

        ExceptionResponse response = ExceptionResponse.builder()
                .validationErrors(errors)
                .build();

        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    /**
     * Handles DataIntegrityViolationException thrown when database constraints are violated.
     *
     * @param exception the DataIntegrityViolationException
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        logger.error("Data integrity violation: {}", exception.getMessage(), exception);
        return buildResponseEntity(DATABASE_TRANSACTION_FAILED, "A data integrity violation occurred.");
    }

    /**
     * Handles HttpMessageNotReadableException thrown when request bodies are malformed.
     *
     * @param exception the HttpMessageNotReadableException
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        logger.warn("Malformed JSON request: {}", exception.getMessage());
        return buildResponseEntity(INVALID_INPUT, "Malformed JSON request.");
    }

    /**
     * Handles BusinessException thrown for business logic related errors.
     *
     * @param exception the BusinessException
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionResponse> handleBusinessException(BusinessException exception) {
        BusinessErrorCodes errorCode = exception.getErrorCode();
        logger.warn("Business exception: {}", errorCode.getDescription());
        return buildResponseEntity(errorCode, exception.getMessage());
    }

    /**
     * Handles all other exceptions not explicitly handled by other methods.
     *
     * @param exception the Exception
     * @return ResponseEntity containing the ExceptionResponse
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        logger.error("Unhandled exception: {}", exception.getMessage(), exception);
        ExceptionResponse response = ExceptionResponse.builder()
                .businessErrorDescription("Internal server error. Please contact support.")
                .build();
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Builds a ResponseEntity with the given BusinessErrorCode and error message.
     *
     * @param errorCode the BusinessErrorCode
     * @param errorMsg  the error message
     * @return ResponseEntity containing the ExceptionResponse
     */
    private ResponseEntity<ExceptionResponse> buildResponseEntity(BusinessErrorCodes errorCode, String errorMsg) {
        ExceptionResponse response = ExceptionResponse.builder()
                .businessErrorCode(errorCode.getCode())
                .businessErrorDescription(errorCode.getDescription())
                .error(errorMsg)
                .build();

        return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
    }
}
```

### **Key Enhancements**

1. **Align HTTP Status Codes Appropriately:**
   - **Correct Usage:** Use `FORBIDDEN` (403) for access-related issues like `LockedException` and `DisabledException` instead of `UNAUTHORIZED` (401). Reserve `UNAUTHORIZED` for authentication failures.
   - **Example Adjustment:**
     ```java
     @ExceptionHandler(LockedException.class)
     public ResponseEntity<ExceptionResponse> handleLockedException(LockedException exception) {
         logger.warn("Account locked: {}", exception.getMessage());
         return buildResponseEntity(ACCOUNT_LOCKED, "Your account is locked. Please contact support.");
     }
     ```
     Ensure that `ACCOUNT_LOCKED` in `BusinessErrorCodes` maps to `FORBIDDEN (403)`.

2. **Reduce Code Duplication:**
   - **Helper Method:** Implement helper methods to build `ExceptionResponse` objects, minimizing repetition across handlers.
   - **Example Helper Method:**
     ```java
     /**
      * Builds a ResponseEntity with the given BusinessErrorCode and error message.
      *
      * @param errorCode the BusinessErrorCode
      * @param errorMsg  the error message
      * @return ResponseEntity containing the ExceptionResponse
      */
     private ResponseEntity<ExceptionResponse> buildResponseEntity(BusinessErrorCodes errorCode, String errorMsg) {
         ExceptionResponse response = ExceptionResponse.builder()
                 .businessErrorCode(errorCode.getCode())
                 .businessErrorDescription(errorCode.getDescription())
                 .error(errorMsg)
                 .build();

         return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
     }
     ```

3. **Implement Comprehensive Logging:**
   - **Use Logging Frameworks:** Utilize SLF4J with Logback or Log4j2 for structured and configurable logging.
   - **Log at Appropriate Levels:**
     - **WARN:** For client-related issues like validation failures or authentication errors.
     - **ERROR:** For server-side issues like exceptions that should not occur during normal operations.
   - **Include Stack Traces for Debugging:** For `ERROR` level logs, include stack traces to aid in debugging.
   - **Example Logging:**
     ```java
     @ExceptionHandler(Exception.class)
     public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
         logger.error("Unhandled exception: {}", exception.getMessage(), exception);
         ExceptionResponse response = ExceptionResponse.builder()
                 .businessErrorDescription("Internal server error. Please contact support.")
                 .build();
         return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(response);
     }
     ```

4. **Enhance Security:**
   - **Generic Error Messages:** Avoid sending detailed exception messages to clients, especially for sensitive errors like `BadCredentialsException`, by providing generic messages.
   - **Sanitize Error Responses:** Ensure that error responses do not contain stack traces, SQL queries, or any sensitive internal information.
   - **Consistent Error Responses:** Use a standardized error response structure to prevent unintended information disclosure.
   - **Example Adjustment:**
     ```java
     @ExceptionHandler(BadCredentialsException.class)
     public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException exception) {
         logger.warn("Bad credentials: {}", exception.getMessage());
         return buildResponseEntity(BAD_CREDENTIALS, "Invalid username or password.");
     }
     ```

5. **Improve Validation Error Details:**
   - **Include Field Names:** When handling validation exceptions, include both field names and error messages to help clients identify and correct issues effectively.
   - **Example Adjustment:**
     ```java
     @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
         logger.warn("Validation failed: {}", exception.getMessage());

         Set<String> errors = exception.getBindingResult()
                 .getFieldErrors()
                 .stream()
                 .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                 .collect(Collectors.toSet());

         ExceptionResponse response = ExceptionResponse.builder()
                 .validationErrors(errors)
                 .build();

         return ResponseEntity.status(BAD_REQUEST).body(response);
     }
     ```

     **Example JSON Response:**
     ```json
     {
         "validationErrors": [
             "email: Email format is invalid",
             "password: Password must be at least 8 characters long"
         ]
     }
     ```

6. **Utilize `BusinessErrorCodes` Consistently:**
   - **Uniformity:** Ensure that all handled exceptions utilize `BusinessErrorCodes` for uniformity.
   - **Fallback Code:** For exceptions not mapped in `BusinessErrorCodes`, use a generic `UNKNOWN_ERROR` code.

7. **Internationalization (i18n):**
   - **Externalize Messages:** Move error descriptions to message properties files (e.g., `messages_en.properties`, `messages_es.properties`).
   - **Use `MessageSource`:** Inject `MessageSource` to retrieve localized messages based on the client's locale.
   - **Modify `BusinessErrorCodes` to Support Message Keys:** Instead of hardcoding descriptions, use message keys.
   - **Example Adjustment:**
     ```java
     // BusinessErrorCodes Enum
     public enum BusinessErrorCodes {
         NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "error.no_code"),
         AUTHENTICATION_FAILED(100, HttpStatus.UNAUTHORIZED, "error.authentication_failed"),
         // ... other codes
     }

     // ExceptionResponse Builder in GlobalExceptionHandler
     @Autowired
     private MessageSource messageSource;

     private ResponseEntity<ExceptionResponse> buildResponseEntity(BusinessErrorCodes errorCode, String errorMsg, Locale locale) {
         String localizedDescription = messageSource.getMessage(errorCode.getDescription(), null, locale);
         ExceptionResponse response = ExceptionResponse.builder()
                 .businessErrorCode(errorCode.getCode())
                 .businessErrorDescription(localizedDescription)
                 .error(errorMsg)
                 .build();

         return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
     }
     ```

8. **Use Custom Exceptions for Better Abstraction:**
   - **Define Custom Exceptions:** Instead of directly handling framework-specific exceptions, define custom exceptions to abstract underlying implementation details.
   - **Example Custom Exception:**
     ```java
     package com.wchamara.book.exception;

     import com.wchamara.book.handlers.BusinessErrorCodes;
     import lombok.Getter;

     /**
      * Custom exception representing business-specific errors.
      */
     @Getter
     public class BusinessException extends RuntimeException {
         private final BusinessErrorCodes errorCode;

         public BusinessException(BusinessErrorCodes errorCode) {
             super(errorCode.getDescription());
             this.errorCode = errorCode;
         }

         public BusinessException(BusinessErrorCodes errorCode, String message) {
             super(message);
             this.errorCode = errorCode;
         }
     }
     ```
