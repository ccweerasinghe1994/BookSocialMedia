# 📖📗📘📙🔖Book Social Media 📖📗📘📙🔖
- [📖📗📘📙🔖Book Social Media 📖📗📘📙🔖](#book-social-media-)
- [📗pom.xml](#pomxml)
- [📗adding docker compose to the project](#adding-docker-compose-to-the-project)
- [📗application.yml files](#applicationyml-files)
- [📗Authentication Flow](#authentication-flow)
- [📗User,Token and Role Entities](#usertoken-and-role-entities)
- [📗Repository Layer for Role, Token and User Entities](#repository-layer-for-role-token-and-user-entities)
- [📗Implementing the Enail Sender Service](#implementing-the-enail-sender-service)
- [📗Let's Implement the Email Service](#lets-implement-the-email-service)
- [📗Implemneting /authenticate endpoint](#implemneting-authenticate-endpoint)
- [📗Implementing /activate-account endpoint](#implementing-activate-account-endpoint)
- [](#)
- [](#-1)
- [](#-2)
- [](#-3)
- [](#-4)
- [](#-5)
- [](#-6)
- [](#-7)
- [📗Exception Handling](#exception-handling)
  - [**1. Overview of Exception Handling in Spring Boot**](#1-overview-of-exception-handling-in-spring-boot)
  - [**2. `BusinessErrorCodes` Enum**](#2-businesserrorcodes-enum)
    - [**Purpose**](#purpose)
    - [**Current Implementation**](#current-implementation)
    - [**Strengths**](#strengths)
    - [**Areas for Improvement**](#areas-for-improvement)
    - [**Suggested Enhancements**](#suggested-enhancements)
    - [**Enhanced `BusinessErrorCodes` Enum Example**](#enhanced-businesserrorcodes-enum-example)
    - [**Benefits of Enhancements**](#benefits-of-enhancements)
  - [**3. `ExceptionResponse` Class**](#3-exceptionresponse-class)
    - [**Purpose**](#purpose-1)
    - [**Current Implementation**](#current-implementation-1)
    - [**Strengths**](#strengths-1)
    - [**Areas for Improvement**](#areas-for-improvement-1)
    - [**Suggested Enhancements**](#suggested-enhancements-1)
    - [**Enhanced `ExceptionResponse` Class Example**](#enhanced-exceptionresponse-class-example)
    - [**Benefits of Enhancements**](#benefits-of-enhancements-1)
    - [**Example Usage**](#example-usage)
  - [**4. `GlobalExceptionHandler` Class**](#4-globalexceptionhandler-class)
    - [**Purpose**](#purpose-2)
    - [**Current Implementation**](#current-implementation-2)
    - [**Strengths**](#strengths-2)
    - [**Areas for Improvement**](#areas-for-improvement-2)
    - [**Suggested Enhancements**](#suggested-enhancements-2)
    - [**Enhanced `GlobalExceptionHandler` Class Example**](#enhanced-globalexceptionhandler-class-example)
    - [**Key Enhancements**](#key-enhancements)
    - [**Benefits of Enhancements**](#benefits-of-enhancements-2)
    - [**Example Scenarios and Responses**](#example-scenarios-and-responses)
      - [**a. Handling a Locked Account (`LockedException`)**](#a-handling-a-locked-account-lockedexception)
      - [**b. Handling Bad Credentials (`BadCredentialsException`)**](#b-handling-bad-credentials-badcredentialsexception)
      - [**c. Handling Validation Errors (`MethodArgumentNotValidException`)**](#c-handling-validation-errors-methodargumentnotvalidexception)
      - [**d. Handling Messaging Errors (`MessagingException`)**](#d-handling-messaging-errors-messagingexception)
      - [**e. Handling Unknown Exceptions (`Exception`)**](#e-handling-unknown-exceptions-exception)
  - [**5. Best Practices and Recommendations**](#5-best-practices-and-recommendations)
    - [**a. Align HTTP Status Codes Appropriately**](#a-align-http-status-codes-appropriately)
    - [**b. Implement Comprehensive Logging**](#b-implement-comprehensive-logging)
    - [**c. Enhance Security by Avoiding Information Leakage**](#c-enhance-security-by-avoiding-information-leakage)
    - [**d. Reduce Code Duplication with Helper Methods**](#d-reduce-code-duplication-with-helper-methods)
    - [**e. Provide Detailed Validation Errors**](#e-provide-detailed-validation-errors)
    - [**f. Handle Additional Exceptions**](#f-handle-additional-exceptions)
    - [**g. Internationalization (i18n) Support**](#g-internationalization-i18n-support)
    - [**h. Use Custom Exceptions for Better Abstraction**](#h-use-custom-exceptions-for-better-abstraction)
    - [**Benefits of Enhancements**](#benefits-of-enhancements-3)
  - [**6. Code Quality and Performance Improvements**](#6-code-quality-and-performance-improvements)
    - [**a. Code Consistency and Readability**](#a-code-consistency-and-readability)
    - [**b. Avoid Unnecessary Object Creation**](#b-avoid-unnecessary-object-creation)
    - [**c. Optimize Validation Error Collection**](#c-optimize-validation-error-collection)
  - [**1. Overview of Exception Handling in Spring Boot**](#1-overview-of-exception-handling-in-spring-boot-1)
  - [**2. `BusinessErrorCodes` Enum**](#2-businesserrorcodes-enum-1)
    - [**Purpose**](#purpose-3)
    - [**Current Implementation**](#current-implementation-3)
    - [**Strengths**](#strengths-3)
    - [**Areas for Improvement**](#areas-for-improvement-3)
    - [**Suggested Enhancements**](#suggested-enhancements-3)
    - [**Enhanced `BusinessErrorCodes` Enum Example**](#enhanced-businesserrorcodes-enum-example-1)
    - [**Benefits of Enhancements**](#benefits-of-enhancements-4)
  - [**3. `ExceptionResponse` Class**](#3-exceptionresponse-class-1)
    - [**Purpose**](#purpose-4)
    - [**Current Implementation**](#current-implementation-4)
    - [**Strengths**](#strengths-4)
    - [**Areas for Improvement**](#areas-for-improvement-4)
    - [**Suggested Enhancements**](#suggested-enhancements-4)
    - [**Enhanced `ExceptionResponse` Class Example**](#enhanced-exceptionresponse-class-example-1)
    - [**Benefits of Enhancements**](#benefits-of-enhancements-5)
    - [**Example Usage**](#example-usage-1)
  - [**4. `GlobalExceptionHandler` Class**](#4-globalexceptionhandler-class-1)
    - [**Purpose**](#purpose-5)
    - [**Current Implementation**](#current-implementation-5)
    - [**Strengths**](#strengths-5)
    - [**Areas for Improvement**](#areas-for-improvement-5)
    - [**Suggested Enhancements**](#suggested-enhancements-5)
    - [**Enhanced `GlobalExceptionHandler` Class Example**](#enhanced-globalexceptionhandler-class-example-1)
    - [**Key Enhancements**](#key-enhancements-1)
- [📗Create the Book Entity](#create-the-book-entity)
  - [**1. Overview of the `Book` Entity**](#1-overview-of-the-book-entity)
    - [**Current Implementation**](#current-implementation-6)
    - [**Explanation**](#explanation)
  - [**2. Detailed Breakdown and Analysis**](#2-detailed-breakdown-and-analysis)
    - [**a. JPA Annotations and Entity Mapping**](#a-jpa-annotations-and-entity-mapping)
    - [**b. Auditing with Spring Data Annotations**](#b-auditing-with-spring-data-annotations)
    - [**c. Field Types and Constraints**](#c-field-types-and-constraints)
    - [**d. Constructor and Accessors**](#d-constructor-and-accessors)
    - [**e. Performance Considerations**](#e-performance-considerations)
    - [**f. Security Considerations**](#f-security-considerations)
    - [**g. Best Practices and Recommendations**](#g-best-practices-and-recommendations)
    - [**h. Example Use Cases**](#h-example-use-cases)
      - [**a. Creating a New Book**](#a-creating-a-new-book)
- [📗Let's create Feedback Entity](#lets-create-feedback-entity)
    - [**1. Entity Declaration**](#1-entity-declaration)
      - [**Improvement: Table Name**](#improvement-table-name)
    - [**2. Primary Key (`id`)**](#2-primary-key-id)
    - [**3. Field Definitions**](#3-field-definitions)
      - [**Improvement: Validation**](#improvement-validation)
    - [**4. Auditing Fields**](#4-auditing-fields)
      - [**Improvements:**](#improvements)
    - [**5. Additional Suggestions**](#5-additional-suggestions)
    - [**6. Final Suggested Class Implementation**](#6-final-suggested-class-implementation)
    - [**Summary of Improvements**](#summary-of-improvements)
- [📗Create Base Entity](#create-base-entity)
    - [**1. BaseEntity Class**](#1-baseentity-class)
      - [**Purpose:**](#purpose-6)
      - [**Current Implementation:**](#current-implementation-7)
      - [**Analysis and Suggestions:**](#analysis-and-suggestions)
      - [**Security Improvement (Audit):**](#security-improvement-audit)
    - [**2. `Book` and `Feedback` Entities**](#2-book-and-feedback-entities)
      - [**Book Entity:**](#book-entity)
      - [**Feedback Entity:**](#feedback-entity)
      - [**Security Improvement:**](#security-improvement)
    - [**3. Performance Considerations**](#3-performance-considerations)
    - [**4. Data Consistency and Integrity**](#4-data-consistency-and-integrity)
    - [**Final Enhanced Implementation:**](#final-enhanced-implementation)
      - [**BaseEntity Class:**](#baseentity-class)
      - [**Book Entity:**](#book-entity-1)
      - [**Feedback Entity:**](#feedback-entity-1)
    - [**Summary of Best Practices and Improvements:**](#summary-of-best-practices-and-improvements)
- [📗Let's add the relatioship between Entities](#lets-add-the-relatioship-between-entities)
  - [Table of Contents](#table-of-contents)
  - [1. Overview of the Existing System](#1-overview-of-the-existing-system)
  - [2. Entity Relationships Explained](#2-entity-relationships-explained)
    - [a. User ↔ Role (Many-to-Many)](#a-user--role-many-to-many)
    - [b. User ↔ Book (One-to-Many)](#b-user--book-one-to-many)
    - [c. Book ↔ Feedback (One-to-Many)](#c-book--feedback-one-to-many)
    - [d. User ↔ BookTransactionHistory (One-to-Many)](#d-user--booktransactionhistory-one-to-many)
    - [e. Book ↔ BookTransactionHistory (One-to-Many)](#e-book--booktransactionhistory-one-to-many)
  - [3. Code Quality Improvements](#3-code-quality-improvements)
    - [a. Use `Long` for ID Fields](#a-use-long-for-id-fields)
    - [b. Consistent ID Generation Strategy](#b-consistent-id-generation-strategy)
    - [c. Enforce Field Validations](#c-enforce-field-validations)
    - [d. Optimize Lombok Annotations](#d-optimize-lombok-annotations)
    - [e. Manage Bidirectional Relationships Properly](#e-manage-bidirectional-relationships-properly)
    - [f. Implement `equals()` and `hashCode()`](#f-implement-equals-and-hashcode)
    - [g. Refine Access Modifiers](#g-refine-access-modifiers)
    - [h. Remove Redundant or Incorrect Fields](#h-remove-redundant-or-incorrect-fields)
  - [4. Performance Enhancements](#4-performance-enhancements)
    - [a. Use `FetchType.LAZY` for Collections](#a-use-fetchtypelazy-for-collections)
    - [b. Implement Pagination for Large Collections](#b-implement-pagination-for-large-collections)
    - [c. Optimize Database Indexing](#c-optimize-database-indexing)
    - [d. Utilize Caching](#d-utilize-caching)
    - [e. Optimize Queries](#e-optimize-queries)
  - [5. Security Enhancements](#5-security-enhancements)
    - [a. Secure Password Storage](#a-secure-password-storage)
    - [b. Implement Proper Authentication and Authorization](#b-implement-proper-authentication-and-authorization)
    - [c. Prevent Mass Assignment Vulnerabilities](#c-prevent-mass-assignment-vulnerabilities)
    - [d. Secure Sensitive Endpoints](#d-secure-sensitive-endpoints)
    - [e. Protect Against Cross-Site Request Forgery (CSRF)](#e-protect-against-cross-site-request-forgery-csrf)
    - [f. Validate Input to Prevent Injection Attacks](#f-validate-input-to-prevent-injection-attacks)
  - [6. Database Schema Improvements](#6-database-schema-improvements)
    - [a. Correct Sequence Increments](#a-correct-sequence-increments)
    - [b. Remove Redundant Columns](#b-remove-redundant-columns)
    - [c. Enforce Foreign Key Constraints with ON DELETE Behavior](#c-enforce-foreign-key-constraints-with-on-delete-behavior)
    - [d. Index Commonly Queried Columns](#d-index-commonly-queried-columns)
    - [e. Normalize Data Where Necessary](#e-normalize-data-where-necessary)
  - [7. Best Practices Summary](#7-best-practices-summary)
    - [a. Follow SOLID Principles](#a-follow-solid-principles)
    - [b. Use DTOs and Mappers](#b-use-dtos-and-mappers)
    - [c. Implement Exception Handling](#c-implement-exception-handling)
    - [d. Leverage Spring Boot Features](#d-leverage-spring-boot-features)
    - [e. Write Unit and Integration Tests](#e-write-unit-and-integration-tests)
    - [f. Document Your Code](#f-document-your-code)
  - [Conclusion](#conclusion)
    - [**1. Entity Relationships and Annotations**](#1-entity-relationships-and-annotations)
      - [**Feedback Entity:**](#feedback-entity-2)
        - [**Improvements:**](#improvements-1)
      - [**BookTransactionHistory Entity:**](#booktransactionhistory-entity)
        - [**Improvements:**](#improvements-2)
      - [**User Entity:**](#user-entity)
        - [**Improvements:**](#improvements-3)
      - [**Book Entity:**](#book-entity-2)
        - [**Improvements:**](#improvements-4)
    - [**2. Database Schema and Structure**](#2-database-schema-and-structure)
      - [**Current Schema**:](#current-schema)
        - [**Improvements to the Schema**:](#improvements-to-the-schema)
    - [**3. Security Considerations**](#3-security-considerations)
    - [**4. Best Practices and Code Quality**](#4-best-practices-and-code-quality)
    - [**Final Thoughts**](#final-thoughts)
- [📗Adding application Auditor aware](#adding-application-auditor-aware)
  - [Table of Contents](#table-of-contents-1)
  - [1. Overview of the Added Components](#1-overview-of-the-added-components)
  - [2. Detailed Code Explanation](#2-detailed-code-explanation)
    - [a. ApplicationAuditAware](#a-applicationauditaware)
    - [b. BeansConfig](#b-beansconfig)
    - [c. BookNetworkApplication](#c-booknetworkapplication)
  - [3. Improvements and Best Practices](#3-improvements-and-best-practices)
    - [a. Code Quality Enhancements](#a-code-quality-enhancements)
    - [b. Performance Optimizations](#b-performance-optimizations)
    - [c. Security Enhancements](#c-security-enhancements)
  - [4. Additional Recommendations](#4-additional-recommendations)
  - [5. Conclusion](#5-conclusion)
    - [**1. ApplicationAuditAware Class**](#1-applicationauditaware-class)
      - [**Purpose:**](#purpose-7)
      - [**Breakdown:**](#breakdown)
      - [**Improvements \& Best Practices:**](#improvements--best-practices)
    - [**2. BeansConfig Class**](#2-beansconfig-class)
      - [**Purpose:**](#purpose-8)
      - [**Breakdown:**](#breakdown-1)
      - [**Improvements \& Best Practices:**](#improvements--best-practices-1)
    - [**3. BookNetworkApplication Class**](#3-booknetworkapplication-class)
      - [**Purpose:**](#purpose-9)
      - [**Improvements \& Best Practices:**](#improvements--best-practices-2)
    - [**Additional Recommendations for Security:**](#additional-recommendations-for-security)
    - [**Summary of Improvements:**](#summary-of-improvements-1)
- [📗Implementing Book Save Method](#implementing-book-save-method)
  - [first analysis](#first-analysis)
    - [**1. BookController Class**](#1-bookcontroller-class)
      - [**Explanation:**](#explanation-1)
      - [**Improvements:**](#improvements-5)
    - [**2. BookRequest Class**](#2-bookrequest-class)
      - [**Explanation:**](#explanation-2)
      - [**Improvements:**](#improvements-6)
    - [**3. BookService Class**](#3-bookservice-class)
      - [**Explanation:**](#explanation-3)
      - [**Improvements:**](#improvements-7)
    - [**4. BookMapper Class**](#4-bookmapper-class)
      - [**Explanation:**](#explanation-4)
      - [**Improvements:**](#improvements-8)
    - [**5. BookRepository Interface**](#5-bookrepository-interface)
      - [**Explanation:**](#explanation-5)
      - [**Improvements:**](#improvements-9)
    - [\*\*Security and Performance Enhancements:](#security-and-performance-enhancements)
    - [**Summary of Improvements:**](#summary-of-improvements-2)
  - [second analysis](#second-analysis)
  - [Table of Contents](#table-of-contents-2)
  - [1. Overview](#1-overview)
  - [2. Detailed Code Analysis](#2-detailed-code-analysis)
    - [1. BookController](#1-bookcontroller)
    - [2. BookRequest](#2-bookrequest)
    - [3. BookService](#3-bookservice)
    - [4. BookMapper](#4-bookmapper)
    - [5. BookRepository](#5-bookrepository)
  - [3. Improvements and Best Practices](#3-improvements-and-best-practices-1)
    - [a. Code Quality Enhancements](#a-code-quality-enhancements-1)
    - [b. Performance Optimizations](#b-performance-optimizations-1)
    - [c. Security Enhancements](#c-security-enhancements-1)
    - [b. Performance Optimizations](#b-performance-optimizations-2)
    - [c. Security Enhancements](#c-security-enhancements-2)
  - [4. Additional Recommendations](#4-additional-recommendations-1)
  - [5. Conclusion](#5-conclusion-1)
- [📗let's implement the /book-id method](#lets-implement-the-book-id-method)
    - [**1. BookController Class**](#1-bookcontroller-class-1)
      - [**Explanation:**](#explanation-6)
      - [**Improvements:**](#improvements-10)
    - [**2. BookResponse Class**](#2-bookresponse-class)
      - [**Explanation:**](#explanation-7)
      - [**Improvements:**](#improvements-11)
    - [**3. BookService Class**](#3-bookservice-class-1)
      - [**Explanation:**](#explanation-8)
      - [**Improvements:**](#improvements-12)
    - [**4. BookMapper Class**](#4-bookmapper-class-1)
      - [**Explanation:**](#explanation-9)
      - [**Improvements:**](#improvements-13)
    - [**5. BookRepository Interface**](#5-bookrepository-interface-1)
      - [**Explanation:**](#explanation-10)
      - [**Improvements:**](#improvements-14)
    - [**Best Practices Recap:**](#best-practices-recap)
  - [Table of Contents](#table-of-contents-3)
  - [1. Overview](#1-overview-1)
  - [2. Detailed Code Analysis](#2-detailed-code-analysis-1)
    - [1. BookController](#1-bookcontroller-1)
    - [2. BookResponse](#2-bookresponse)
    - [3. BookService](#3-bookservice-1)
    - [4. BookMapper](#4-bookmapper-1)
    - [5. BookRepository](#5-bookrepository-1)
  - [3. Improvements and Best Practices](#3-improvements-and-best-practices-2)
    - [a. Code Quality Enhancements](#a-code-quality-enhancements-2)
    - [b. Performance Optimizations](#b-performance-optimizations-3)
    - [b. Performance Optimizations](#b-performance-optimizations-4)
    - [c. Security Enhancements](#c-security-enhancements-3)
- [📗Find All Books](#find-all-books)
  - [**1. Overview of the Provided Code**](#1-overview-of-the-provided-code)
    - [**a. `BookController`**](#a-bookcontroller)
    - [**b. `PageResponse<T>`**](#b-pageresponset)
    - [**c. `BookService`**](#c-bookservice)
    - [**d. `BookRepository`**](#d-bookrepository)
  - [**2. Deep Dive into `findAllBooks` Method**](#2-deep-dive-into-findallbooks-method)
    - [**Current Implementation**](#current-implementation-8)
    - [**Functionality**](#functionality)
  - [**3. Identified Issues and Suggested Improvements**](#3-identified-issues-and-suggested-improvements)
    - [**a. Code Quality Improvements**](#a-code-quality-improvements)
    - [**b. Performance Improvements**](#b-performance-improvements)
    - [**c. Security Improvements**](#c-security-improvements)
    - [**d. Best Practices**](#d-best-practices)
  - [**4. Enhanced Implementation Example**](#4-enhanced-implementation-example)
    - [**a. Updated `BookRepository`**](#a-updated-bookrepository)
    - [**b. Updated `BookService`**](#b-updated-bookservice)
    - [**c. Updated `BookController`**](#c-updated-bookcontroller)
    - [**d. Added `BookConstants`**](#d-added-bookconstants)
    - [**e. Enhanced Exception Handling**](#e-enhanced-exception-handling)
    - [**f. Defined `ErrorResponse` DTO**](#f-defined-errorresponse-dto)
  - [**5. Additional Recommendations**](#5-additional-recommendations)
    - [**a. Implementing Pagination Metadata**](#a-implementing-pagination-metadata)
    - [**b. Utilizing MapStruct for DTO Mapping**](#b-utilizing-mapstruct-for-dto-mapping)
    - [**c. Testing**](#c-testing)
    - [**d. Monitoring and Metrics**](#d-monitoring-and-metrics)
  - [**6. Conclusion**](#6-conclusion)
    - [Current Flow](#current-flow)
    - [Key Areas for Improvements](#key-areas-for-improvements)
    - [Summary of Improvements:](#summary-of-improvements-3)
- [📗Get Book By Owner](#get-book-by-owner)
    - [**1. BookController.java**](#1-bookcontrollerjava)
      - [**Explanation:**](#explanation-11)
      - [**Proposed Change and Explanation:**](#proposed-change-and-explanation)
    - [**2. BookService.java**](#2-bookservicejava)
      - [**Explanation:**](#explanation-12)
      - [**Proposed Change and Explanation:**](#proposed-change-and-explanation-1)
      - [**Additional Improvements:**](#additional-improvements)
    - [**3. BookSpecification.java**](#3-bookspecificationjava)
      - [**Explanation:**](#explanation-13)
      - [**Proposed Change and Explanation:**](#proposed-change-and-explanation-2)
    - [**4. BookRepository.java**](#4-bookrepositoryjava)
      - [**Explanation:**](#explanation-14)
      - [**No Changes Suggested:**](#no-changes-suggested)
  - [**Overall Improvements and Best Practices**](#overall-improvements-and-best-practices)
    - [**1. Validation of Parameters**](#1-validation-of-parameters)
    - [**2. Exception Handling**](#2-exception-handling)
    - [**3. Security Enhancements**](#3-security-enhancements)
    - [**4. Performance Enhancements**](#4-performance-enhancements-1)
    - [**5. Documentation**](#5-documentation)


# [📗pom.xml](./docs/1/readme.md)

# [📗adding docker compose to the project](./docs/2/readme.md)

# [📗application.yml files](./docs/3/readme.md)

# [📗Authentication Flow](./docs/4/readme.md)


# [📗User,Token and Role Entities](./docs/5/readme.md)

# [📗Repository Layer for Role, Token and User Entities](./docs/6/readme.md)

# [📗Implementing the Enail Sender Service](./docs/7/readme.md)

# [📗Let's Implement the Email Service](./docs/8/readme.md)

# [📗Implemneting /authenticate endpoint](./docs/9/readme.md)

# [📗Implementing /activate-account endpoint](./docs/10/readme.md)

# [](./docs/11/readme.md)
# [](./docs/12/readme.md)
# [](./docs/13/readme.md)
# [](./docs/14/readme.md)
# [](./docs/15/readme.md)
# [](./docs/16/readme.md)
# [](./docs/17/readme.md)
# [](./docs/18/readme.md)


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

# 📗Create the Book Entity

  ```java
  package com.wchamara.book.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity

public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private String author;

    private String isbn;

    private String synopsis;

    private String bookCover;

    private boolean archived;

    private boolean shareable;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(updatable = false, nullable = false)
    private Integer createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Integer lastModifiedBy;
}
```

Your `Book` entity class serves as the foundational representation of a book within your application. It leverages Java Persistence API (JPA) annotations to map the class to a database table, enabling seamless interactions with the underlying database. Let's delve deeply into each component of your implementation, explore its strengths, identify areas for improvement, and discuss best practices to enhance code quality, performance, and security.

## **1. Overview of the `Book` Entity**

### **Current Implementation**

```java
package com.wchamara.book.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private String author;

    private String isbn;

    private String synopsis;

    private String bookCover;

    private boolean archived;

    private boolean shareable;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(updatable = false, nullable = false)
    private Integer createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Integer lastModifiedBy;
}
```

### **Explanation**

1. **Package Declaration:**
   - The class resides in the `com.wchamara.book.book` package, which seems redundant (`book.book`). Consider revising the package structure for better organization.

2. **Imports:**
   - **JPA Annotations (`jakarta.persistence`):** Used to map the class and its fields to a database table and columns.
   - **Spring Data Annotations (`org.springframework.data.annotation`):** Facilitate auditing features like tracking creation and modification details.
   - **Java Time (`java.time.LocalDateTime`):** Handles date and time fields.

3. **Class Declaration:**
   - Annotated with `@Entity`, indicating that it's a JPA entity mapped to a database table.

4. **Fields:**
   - **`id`:** Primary key, auto-generated.
   - **`title`, `author`, `isbn`, `synopsis`, `bookCover`:** Attributes describing the book.
   - **`archived`, `shareable`:** Boolean flags indicating the book's status.
   - **Auditing Fields:**
     - **`createdDate`, `lastModifiedDate`:** Timestamps for creation and last modification.
     - **`createdBy`, `lastModifiedBy`:** User IDs for who created and last modified the book.

5. **Annotations:**
   - **`@Id` and `@GeneratedValue`:** Define the primary key and its auto-generation strategy.
   - **`@Column`:** Customizes column properties like `updatable`, `insertable`, and `nullable`.
   - **Spring Data Auditing Annotations:**
     - **`@CreatedDate`, `@LastModifiedDate`:** Automatically populate timestamps.
     - **`@CreatedBy`, `@LastModifiedBy`:** Automatically populate user IDs responsible for changes.

## **2. Detailed Breakdown and Analysis**

### **a. JPA Annotations and Entity Mapping**

1. **`@Entity`:**
   - Marks the class as a JPA entity, which will be mapped to a database table.
   - **Best Practice:** Specify the table name using `@Table` if it differs from the class name or to adhere to naming conventions.

   **Example:**

   ```java
   @Entity
   @Table(name = "books")
   public class Book {
       // ...
   }
   ```

2. **`@Id` and `@GeneratedValue`:**
   - **`@Id`:** Denotes the primary key.
   - **`@GeneratedValue`:** Specifies that the primary key is auto-generated.
     - **Default Strategy:** Depends on the underlying database. It's often safer to specify the strategy explicitly.
     - **Recommendation:** Use `@GeneratedValue(strategy = GenerationType.IDENTITY)` or another appropriate strategy based on your database.

   **Example:**

   ```java
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   ```

3. **`@Column`:**
   - Customizes column attributes.
   - **Current Usage:**
     - **`createdDate`:** `updatable = false`, `nullable = false`.
     - **`lastModifiedDate`:** `insertable = false`.
     - **`createdBy`:** `updatable = false`, `nullable = false`.
     - **`lastModifiedBy`:** `insertable = false`.
   - **Recommendation:**
     - Ensure consistency in column naming conventions (e.g., snake_case vs. camelCase).
     - Consider adding `@Column` annotations to other fields to define constraints like `nullable`, `unique`, `length`, etc., to enforce data integrity at the database level.

   **Example:**

   ```java
   @Column(nullable = false, length = 255)
   private String title;
   ```

### **b. Auditing with Spring Data Annotations**

1. **Annotations:**
   - **`@CreatedDate` and `@LastModifiedDate`:** Automatically manage timestamp fields.
   - **`@CreatedBy` and `@LastModifiedBy`:** Automatically track the user responsible for creating or modifying the entity.

2. **Configuration Requirements:**
   - To enable auditing, you need to configure Spring Data JPA to recognize these annotations.
   - **Steps:**
     - **Enable JPA Auditing:** Annotate a configuration class with `@EnableJpaAuditing`.
     - **Implement AuditorAware:** Provide an implementation to supply the current user's ID.

   **Example Configuration:**

   ```java
   import org.springframework.context.annotation.Configuration;
   import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
   import org.springframework.data.domain.AuditorAware;
   import org.springframework.context.annotation.Bean;

   import java.util.Optional;

   @Configuration
   @EnableJpaAuditing(auditorAwareRef = "auditorProvider")
   public class JpaConfig {

       @Bean
       public AuditorAware<Integer> auditorProvider() {
           // Implement logic to retrieve the current user's ID
           // This is a placeholder; integrate with your security context
           return () -> Optional.ofNullable(getCurrentUserId());
       }

       private Integer getCurrentUserId() {
           // Example: Retrieve from SecurityContext
           // return SecurityContextHolder.getContext().getAuthentication().getPrincipal().getId();
           return 1; // Placeholder
       }
   }
   ```

3. **Benefits:**
   - **Automated Tracking:** Reduces boilerplate code by automatically handling auditing fields.
   - **Consistency:** Ensures that all entities have accurate and consistent auditing information.

4. **Areas for Improvement:**
   - **Type of Auditor Fields:** Currently, `createdBy` and `lastModifiedBy` are of type `Integer`. Depending on your user identification system, consider using `Long`, `UUID`, or even a `User` entity reference.
   - **Null Handling:** Ensure that `auditorProvider` correctly handles scenarios where no authenticated user is present (e.g., system operations).

### **c. Field Types and Constraints**

1. **`id`:** `Integer`
   - **Recommendation:** Consider using `Long` for primary keys to accommodate a larger range of values, especially if you expect the table to grow significantly.

   **Example:**

   ```java
   private Long id;
   ```

2. **String Fields (`title`, `author`, `isbn`, `synopsis`, `bookCover`):**
   - **Constraints:**
     - Define maximum lengths using `@Column(length = X)` to optimize database storage and enforce data integrity.
     - Use `@NotNull`, `@NotBlank`, or other validation annotations if using bean validation to enforce constraints at the application level.
   - **Example:**

   ```java
   @Column(nullable = false, length = 255)
   private String title;

   @Column(nullable = false, length = 255)
   private String author;

   @Column(nullable = false, unique = true, length = 13)
   private String isbn;
   ```

3. **`synopsis` and `bookCover`:**
   - **Considerations:**
     - **`synopsis`:** Likely requires a larger storage capacity; consider using `@Lob` for storing large texts.
     - **`bookCover`:** If storing image data, use appropriate data types or store URLs/paths to the images.
   - **Example:**

   ```java
   @Lob
   @Column
   private String synopsis;

   @Column(length = 500)
   private String bookCoverUrl;
   ```

4. **Boolean Fields (`archived`, `shareable`):**
   - **Default Values:**
     - Define default values to prevent `NULL` values in the database.
     - Use `@Column(nullable = false)` and set default values either via the database or in the application logic.
   - **Example:**

   ```java
   @Column(nullable = false)
   private boolean archived = false;

   @Column(nullable = false)
   private boolean shareable = true;
   ```

### **d. Constructor and Accessors**

1. **Current Implementation:**
   - The class relies on default constructors and getter/setter methods.
   - No constructors with arguments, which might be necessary for certain operations or integrations.

2. **Recommendations:**
   - **Encapsulation:**
     - Consider making fields `private` and providing only necessary getters and setters to maintain encapsulation.
   - **Immutable Fields:**
     - Fields that shouldn't change after creation (e.g., `id`, `createdDate`, `createdBy`) can be made immutable by omitting setters or using `final` keyword.
   - **Builder Pattern:**
     - Utilize Lombok's `@Builder` for more flexible and readable object creation.

   **Example Adjustment:**

   ```java
   @Entity
   @Getter
   @Setter
   @Builder
   @AllArgsConstructor
   @NoArgsConstructor
   @Table(name = "books")
   public class Book {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @Column(nullable = false, length = 255)
       private String title;

       @Column(nullable = false, length = 255)
       private String author;

       @Column(nullable = false, unique = true, length = 13)
       private String isbn;

       @Lob
       @Column
       private String synopsis;

       @Column(length = 500)
       private String bookCoverUrl;

       @Column(nullable = false)
       private boolean archived = false;

       @Column(nullable = false)
       private boolean shareable = true;

       @CreatedDate
       @Column(updatable = false, nullable = false)
       private LocalDateTime createdDate;

       @LastModifiedDate
       @Column(insertable = false)
       private LocalDateTime lastModifiedDate;

       @CreatedBy
       @Column(updatable = false, nullable = false)
       private Integer createdBy;

       @LastModifiedBy
       @Column(insertable = false)
       private Integer lastModifiedBy;
   }
   ```

### **e. Performance Considerations**

1. **Lazy Loading and Fetch Strategies:**
   - Currently, the `Book` entity doesn't have any relationships (e.g., to `Author` or `Category`), but if you plan to introduce relationships, define fetch strategies (`EAGER` vs. `LAZY`) appropriately to optimize performance.

2. **Indexing:**
   - **`isbn`:** Marked as `unique`, which likely creates an index. Ensure that other frequently queried fields are indexed to improve query performance.
   - **Example:**

   ```java
   @Column(nullable = false, unique = true, length = 13)
   @Index(name = "idx_book_isbn")
   private String isbn;
   ```

3. **Bulk Operations:**
   - For bulk insertions or updates, ensure that batch processing is configured correctly to prevent performance bottlenecks.

4. **Caching:**
   - Utilize second-level caching for entities that are frequently read but rarely updated to reduce database load.
   - **Example:**
     ```java
     @Entity
     @Cacheable
     @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
     public class Book {
         // ...
     }
     ```

### **f. Security Considerations**

1. **Data Exposure:**
   - **Sensitive Information:** Ensure that no sensitive information is inadvertently exposed via the `Book` entity (e.g., private notes or internal IDs).
   - **DTO Usage:** Use Data Transfer Objects (DTOs) to control the data exposed to clients, preventing overexposure of entity fields.

   **Example:**

   ```java
   public class BookDTO {
       private Long id;
       private String title;
       private String author;
       private String isbn;
       private String synopsis;
       private String bookCoverUrl;
       private boolean archived;
       private boolean shareable;
       // Getters and setters
   }
   ```

2. **Input Validation:**
   - Validate inputs to prevent SQL injection, cross-site scripting (XSS), and other injection attacks.
   - Use bean validation annotations (e.g., `@NotNull`, `@Size`, `@Pattern`) on DTOs to enforce input constraints.

   **Example:**

   ```java
   public class BookDTO {
       private Long id;

       @NotBlank(message = "Title is mandatory")
       @Size(max = 255, message = "Title cannot exceed 255 characters")
       private String title;

       @NotBlank(message = "Author is mandatory")
       @Size(max = 255, message = "Author cannot exceed 255 characters")
       private String author;

       @NotBlank(message = "ISBN is mandatory")
       @Pattern(regexp = "\\d{13}", message = "ISBN must be a 13-digit number")
       private String isbn;

       private String synopsis;

       @Size(max = 500, message = "Book cover URL cannot exceed 500 characters")
       private String bookCoverUrl;

       private boolean archived = false;

       private boolean shareable = true;
       // Getters and setters
   }
   ```

3. **Access Control:**
   - Ensure that only authorized users can perform certain operations on the `Book` entity (e.g., only admins can archive books).
   - Utilize Spring Security's method-level security annotations (e.g., `@PreAuthorize`, `@PostAuthorize`).

   **Example:**

   ```java
   import org.springframework.security.access.prepost.PreAuthorize;

   @Service
   public class BookService {

       @PreAuthorize("hasRole('ADMIN')")
       public void archiveBook(Long bookId) {
           // Implementation
       }
   }
   ```

4. **Prevent Mass Assignment:**
   - Avoid allowing clients to set fields like `createdBy`, `createdDate`, etc., directly through request payloads.
   - Control field assignment within service layers or through mapping frameworks like MapStruct.

### **g. Best Practices and Recommendations**

1. **Use of Lombok:**
   - While Lombok reduces boilerplate code, excessive use can obscure the code's behavior.
   - **Recommendation:** Use Lombok judiciously. For entities, it can be helpful to include `@Data` or `@Getter`/`@Setter`, but ensure that the generated methods align with your application's requirements.

   **Example:**

   ```java
   @Entity
   @Getter
   @Setter
   @Builder
   @AllArgsConstructor
   @NoArgsConstructor
   @Table(name = "books")
   public class Book {
       // ...
   }
   ```

2. **DTOs vs. Entities:**
   - **Separation of Concerns:** Use DTOs to separate the persistence layer from the presentation layer, enhancing security and flexibility.
   - **Mapping Tools:** Utilize mapping libraries like MapStruct or ModelMapper to convert between entities and DTOs efficiently.

   **Example Using MapStruct:**

   ```java
   import org.mapstruct.Mapper;
   import org.mapstruct.factory.Mappers;

   @Mapper
   public interface BookMapper {
       BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

       BookDTO toDTO(Book book);

       Book toEntity(BookDTO bookDTO);
   }
   ```

3. **Immutable Entities:**
   - Consider making entities immutable by removing setters and using constructors or builders exclusively for object creation. This enhances thread safety and predictability.
   - **Trade-off:** This approach can complicate certain JPA operations like updating entities, so weigh the benefits against the complexity.

   **Example:**

   ```java
   @Entity
   @Getter
   @Builder
   @AllArgsConstructor
   @NoArgsConstructor
   @Table(name = "books")
   public class Book {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @Column(nullable = false, length = 255)
       private final String title;

       @Column(nullable = false, length = 255)
       private final String author;

       @Column(nullable = false, unique = true, length = 13)
       private final String isbn;

       @Lob
       @Column
       private final String synopsis;

       @Column(length = 500)
       private final String bookCoverUrl;

       @Column(nullable = false)
       private final boolean archived;

       @Column(nullable = false)
       private final boolean shareable;

       @CreatedDate
       @Column(updatable = false, nullable = false)
       private final LocalDateTime createdDate;

       @LastModifiedDate
       @Column(insertable = false)
       private final LocalDateTime lastModifiedDate;

       @CreatedBy
       @Column(updatable = false, nullable = false)
       private final Integer createdBy;

       @LastModifiedBy
       @Column(insertable = false)
       private final Integer lastModifiedBy;
   }
   ```

4. **Entity Lifecycle Callbacks:**
   - Utilize JPA lifecycle callbacks (`@PrePersist`, `@PostPersist`, `@PreUpdate`, `@PostUpdate`, etc.) for additional logic during entity state transitions.
   - **Example:**

   ```java
   @Entity
   @Getter
   @Setter
   @Builder
   @AllArgsConstructor
   @NoArgsConstructor
   @Table(name = "books")
   public class Book {
       // ... fields

       @PrePersist
       protected void onCreate() {
           createdDate = LocalDateTime.now();
       }

       @PreUpdate
       protected void onUpdate() {
           lastModifiedDate = LocalDateTime.now();
       }
   }
   ```

5. **Validation Annotations:**
   - Apply JPA and Bean Validation annotations to enforce data integrity both at the database and application levels.
   - **Example:**

   ```java
   @NotBlank(message = "Title is mandatory")
   @Size(max = 255, message = "Title cannot exceed 255 characters")
   @Column(nullable = false, length = 255)
   private String title;
   ```

6. **Soft Deletes:**
   - Implement soft delete functionality using the `archived` field to mark records as inactive instead of physically deleting them.
   - **Implementation:**
     - Modify repository queries to exclude archived records by default.
     - Utilize Hibernate filters or Spring Data JPA's `@Where` annotation for automatic filtering.

   **Example Using `@Where`:**

   ```java
   import org.hibernate.annotations.Where;

   @Entity
   @Getter
   @Setter
   @Builder
   @AllArgsConstructor
   @NoArgsConstructor
   @Table(name = "books")
   @Where(clause = "archived = false")
   public class Book {
       // ... fields
   }
   ```

7. **Serialization Considerations:**
   - Control how the `Book` entity is serialized to JSON to prevent exposure of internal fields.
   - Use annotations like `@JsonIgnore`, `@JsonProperty`, or custom serializers/deserializers as needed.

   **Example:**

   ```java
   @JsonIgnore
   private Integer createdBy;

   @JsonIgnore
   private Integer lastModifiedBy;
   ```

8. **Optimistic Locking:**
   - Implement optimistic locking to prevent concurrent modifications leading to data inconsistencies.
   - **Implementation:**
     - Add a `@Version` field to handle versioning.

   **Example:**

   ```java
   @Version
   private Integer version;
   ```

   - **Usage:**
     - Hibernate will automatically increment the `version` field on updates and throw an `OptimisticLockException` if a version mismatch occurs.

### **h. Example Use Cases**

Let's explore some practical scenarios illustrating how the `Book` entity operates within your application, highlighting potential improvements.

#### **a. Creating a New Book**

**Current Workflow:**

1. **Request:** A client sends a POST request with book details.
2. **Controller:** Receives the request, maps it to a `Book` entity, and saves it using a repository.
3. **Auditing:** `createdDate` and `createdBy` are automatically populated.

**Potential Issues and Improvements:**

- **Validation:** Ensure that all required fields are validated before persistence.
- **DTO Usage:** Use a `BookDTO` to capture client input and map it to the `Book` entity to prevent overexposure.

**Example:**

```java
// BookDTO.java
public class BookDTO {
    @NotBlank(message = "Title is mandatory")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @NotBlank(message = "Author is mandatory")
    @Size(max = 255, message = "Author cannot exceed 255 characters")
    private String author;

    @NotBlank(message = "ISBN is mandatory")
    @Pattern(regexp = "\\d{13}", message = "ISBN must be a 13-digit number")
    private String isbn;

    private String synopsis;
    private String bookCoverUrl;
    private boolean shareable = true;
    // Getters and setters
}

// BookController.java
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = bookService.createBook(bookMapper.toEntity(bookDTO));
        return new ResponseEntity<>(bookMapper.toDTO(book), HttpStatus.CREATED);
    }
}
```
# 📗Let's create Feedback Entity

```java
package com.wchamara.book.feedback;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Feedback {
    @Id
    @GeneratedValue
    private Integer id;

    private Double note; // 1 to 5

    private String comment;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(updatable = false, nullable = false)
    private Integer createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Integer lastModifiedBy;
}
```

Your `Feedback` entity class models feedback data with a `note`, `comment`, and audit fields (`createdDate`, `lastModifiedDate`, `createdBy`, `lastModifiedBy`). This implementation is already well-structured, leveraging JPA and Spring Data’s auditing capabilities. However, there are several areas for improvement in terms of best practices, performance, and security. Let's walk through each part of the code and suggest improvements.

### **1. Entity Declaration**

The `Feedback` class is annotated with `@Entity`, which marks it as a JPA entity to be mapped to a database table.

```java
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Feedback {
```

- **Entity Listeners**: The `@EntityListeners(AuditingEntityListener.class)` annotation enables automatic population of auditing fields (`createdBy`, `createdDate`, etc.) by listening for JPA lifecycle events (e.g., `@PrePersist`, `@PreUpdate`).

#### **Improvement: Table Name**

- **Best Practice**: Explicitly define the table name using the `@Table` annotation to avoid issues with database naming conventions, especially if using different case or pluralization strategies (e.g., `feedback` vs. `feedbacks`).

```java
@Entity
@Table(name = "feedbacks")
@EntityListeners(AuditingEntityListener.class)
public class Feedback {
```

### **2. Primary Key (`id`)**

The primary key is annotated with `@Id` and `@GeneratedValue`, indicating that it's an auto-generated field.

```java
@Id
@GeneratedValue
private Integer id;
```

- **Improvement**: Consider specifying a generation strategy to optimize the ID generation process. For example, using `GenerationType.IDENTITY` is often recommended for MySQL or `GenerationType.SEQUENCE` for PostgreSQL.

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
```

### **3. Field Definitions**

```java
private Double note; // 1 to 5
private String comment;
```

#### **Improvement: Validation**

- **`note` (Rating)**: The `note` field represents a rating (1 to 5). A `Double` is appropriate, but it lacks validation to ensure it falls within this range. Use `@Min` and `@Max` annotations to enforce the valid range at the model level.
  
  ```java
  @Min(1)
  @Max(5)
  private Double note; // 1 to 5
  ```

- **`comment`**: The `comment` field could also benefit from validation (e.g., not too long or empty).

  ```java
  @Size(max = 500, message = "Comment cannot exceed 500 characters.")
  private String comment;
  ```

- **Improvement**: Consider changing `Double` to `BigDecimal` if you need high precision for `note`, or `Float` if storage size is a concern, as `Double` can introduce inaccuracies in financial or statistical calculations. For a simple rating system, `Integer` may also suffice.

  ```java
  @Min(1)
  @Max(5)
  private Integer note; // If the rating is an integer
  ```

### **4. Auditing Fields**

```java
@CreatedDate
@Column(updatable = false, nullable = false)
private LocalDateTime createdDate;

@LastModifiedDate
@Column(insertable = false)
private LocalDateTime lastModifiedDate;

@CreatedBy
@Column(updatable = false, nullable = false)
private Integer createdBy;

@LastModifiedBy
@Column(insertable = false)
private Integer lastModifiedBy;
```

- **`@CreatedDate` and `@LastModifiedDate`**: Automatically populated by Spring Data JPA auditing, these fields record when the feedback was created and last modified.
  
- **`@CreatedBy` and `@LastModifiedBy`**: Automatically record the user who created and last modified the feedback. These annotations work when security and auditing are configured properly.

#### **Improvements:**

1. **Security Concerns (Auditing)**:
   - Ensure that `@CreatedBy` and `@LastModifiedBy` are correctly populated with the current authenticated user. This typically requires configuring Spring Security and enabling JPA auditing in your `@Configuration` class:

   ```java
   @Configuration
   @EnableJpaAuditing(auditorAwareRef = "auditorProvider")
   public class PersistenceConfig {
   
       @Bean
       public AuditorAware<Integer> auditorProvider() {
           return new SpringSecurityAuditorAware(); // or custom implementation
       }
   }
   ```

   In `SpringSecurityAuditorAware`, you would retrieve the current user's ID from the `SecurityContext`.

2. **Handling `insertable` and `updatable` Flags**:
   - **`insertable = false` and `updatable = false`**: While these flags prevent updating or inserting certain columns, you need to ensure that the application’s logic and database requirements align. For instance, if `lastModifiedBy` is set as `insertable = false`, it will not be inserted into the database during creation, which might be problematic if you need to track this on creation.
   
   Typically, `updatable = false` is more appropriate for `@CreatedDate` and `@CreatedBy`, but `insertable = false` for `@LastModifiedBy` or `@LastModifiedDate` could prevent them from being inserted correctly on entity creation.

   Correct it like this:

   ```java
   @LastModifiedBy
   @Column(nullable = false) // remove insertable flag
   private Integer lastModifiedBy;
   ```

3. **Immutable Creation Fields**:
   - Fields like `createdDate` and `createdBy` should not be updated once they are set. Your use of `updatable = false` for these fields is correct, but ensure that your application logic enforces this restriction at the service level (e.g., don’t expose these fields for editing in an API).

### **5. Additional Suggestions**

1. **Indexing Frequently Queried Fields**:
   - If `note`, `createdBy`, or `createdDate` fields are frequently used for querying or sorting, consider adding indexes to these fields to improve database performance.

   ```java
   @Column(name = "note", nullable = false)
   @Index(name = "idx_note")
   private Integer note;
   ```

2. **Auditor Type**:
   - You are using `Integer` for `createdBy` and `lastModifiedBy`. Ensure that this type aligns with your security system (e.g., if you’re using usernames instead of user IDs, you might use `String`).

3. **Field Nullability**:
   - Ensure that `@Column(nullable = false)` is used where appropriate. For example, if `comment` can be optional, you can leave it nullable, but `note` should be required.

4. **Best Practices for Data Consistency**:
   - If you require the same user who creates the feedback to be the one who modifies it, you can add logic at the service layer to enforce such a rule.
  
5. **Security Considerations**:
   - **Validation for Input Fields**: Validate the input data coming from external sources, such as `note` and `comment`, to prevent SQL injections or malicious data.
   - **User Permissions**: Ensure that only authorized users can modify certain fields (e.g., only admins or owners should be able to update `archived` or `shareable` if those fields existed in this model).

### **6. Final Suggested Class Implementation**

```java
package com.wchamara.book.feedback;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "feedbacks")
@EntityListeners(AuditingEntityListener.class)
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Integer note; // 1 to 5

    @Size(max = 500, message = "Comment cannot exceed 500 characters.")
    private String comment;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(updatable = false, nullable = false)
    private Integer createdBy;

    @LastModifiedBy
    @Column(nullable = false)
    private Integer lastModifiedBy;
}
```

### **Summary of Improvements**

1. **Field Validation**: Added `@Min`, `@Max`, and `@Size` annotations to validate `note` and `comment` fields.
2. **Correct Handling of `insertable` and `updatable`**: Ensured appropriate usage of these flags for auditing fields to avoid insertion or update issues.
3. **Indexing**: Suggested adding indexes to frequently queried fields.
4. **Security**: Recommended properly configuring Spring Security with JPA auditing and ensuring that the auditing fields (`createdBy`, `lastModifiedBy`) are populated correctly.
5. **Database Performance**: Proposed indexing key columns for better query performance.

By implementing these improvements, you will enhance the functionality, performance, security, and maintainability of your `Feedback` entity, ensuring robust handling of feedback records in your system.

# 📗Create Base Entity

```java
package com.wchamara.book.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {


    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(updatable = false, nullable = false)
    private Integer createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Integer lastModifiedBy;
}

```

```java
package com.wchamara.book.book;

import com.wchamara.book.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {


    private String title;

    private String author;

    private String isbn;

    private String synopsis;

    private String bookCover;

    private boolean archived;

    private boolean shareable;

}

```

```java
package com.wchamara.book.feedback;

import com.wchamara.book.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Feedback extends BaseEntity {

    private Double note;

    private String comment;

}

```

The use of a **BaseEntity** class in your system is a good design decision that promotes **code reusability** and **consistency** across multiple entities. It allows you to centralize common auditing functionality such as creation, modification timestamps, and user tracking. Additionally, you've provided good examples of domain entities (`Book` and `Feedback`) that inherit from this base class. This approach simplifies your entity design by avoiding repeated code across entities.

Let’s explore the implementation in detail, focusing on areas like **code quality**, **performance**, and **security** improvements, as well as suggesting best practices.

### **1. BaseEntity Class**

#### **Purpose:**
The `BaseEntity` class contains common fields and annotations shared by different entities, such as `createdDate`, `lastModifiedDate`, `createdBy`, and `lastModifiedBy`. This class makes your design more maintainable by avoiding code duplication.

#### **Current Implementation:**
```java
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(updatable = false, nullable = false)
    private Integer createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Integer lastModifiedBy;
}
```

#### **Analysis and Suggestions:**

- **Inheritance (`@MappedSuperclass`):**
  - `@MappedSuperclass` ensures that the fields in `BaseEntity` are included in child entities, but `BaseEntity` itself is not mapped to a database table. This is correct for your use case because `BaseEntity` is just a common structure, and you don’t want a table for it.

- **Auditing Fields (`@CreatedDate`, `@LastModifiedDate`, `@CreatedBy`, `@LastModifiedBy`):**
  - These fields rely on Spring Data’s auditing feature to automatically populate the `createdBy`, `createdDate`, `lastModifiedBy`, and `lastModifiedDate` fields when records are created or updated. This enhances security by tracking who created or modified a record and when it happened.
  
  **Improvements:**
  - **Use of `@GeneratedValue(strategy = GenerationType.IDENTITY)` for `id`:** 
    While `@GeneratedValue` works fine, specifying the `GenerationType` (e.g., `IDENTITY` or `SEQUENCE`) is a good practice to align with the database strategy for better optimization.

    ```java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    ```

  - **Insertable and Updatable Flags:**
    The `@Column(insertable = false)` and `@Column(updatable = false)` flags are correct but require careful management to avoid blocking updates or inserts when needed. Ensure these fields are correctly populated and cannot be altered by the end user.

    **For `@LastModifiedBy` and `@LastModifiedDate`:**
    Remove `insertable = false` because these fields should be inserted when the record is first created, not only on update. This ensures that a record always has an initial value for these fields.

    ```java
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;
    ```

  - **Use of `BigInteger` for `createdBy` and `lastModifiedBy`:**
    In a larger application where you might track users with bigger ID ranges, `Integer` could become a limitation. Consider using `Long` or `BigInteger` for user ID fields.

    ```java
    @CreatedBy
    @Column(updatable = false, nullable = false)
    private Long createdBy;

    @LastModifiedBy
    @Column(nullable = false)
    private Long lastModifiedBy;
    ```

#### **Security Improvement (Audit):**
- **Tracking Auditing with Spring Security:**
  To ensure proper user auditing, you need to configure Spring Security's authentication details to provide the correct `createdBy` and `lastModifiedBy` values.

  Implement `AuditorAware` to retrieve the current user's ID:

  ```java
  @Component
  public class AuditorAwareImpl implements AuditorAware<Long> {

      @Override
      public Optional<Long> getCurrentAuditor() {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          if (authentication == null || !authentication.isAuthenticated()) {
              return Optional.empty();
          }
          return Optional.of(((CustomUserDetails) authentication.getPrincipal()).getId());
      }
  }
  ```

  Then enable auditing in your configuration class:

  ```java
  @Configuration
  @EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
  public class JpaConfig {
  }
  ```

### **2. `Book` and `Feedback` Entities**

#### **Book Entity:**
```java
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {

    private String title;
    private String author;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean archived;
    private boolean shareable;
}
```

- **Field Types:**
  - **`isbn`**: Ensure validation for proper ISBN formats (13 digits for ISBN-13, 10 for ISBN-10). You can use regular expressions or a custom validator for this.

    ```java
    @Pattern(regexp = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- 0-9]{10}$",
             message = "Invalid ISBN format")
    private String isbn;
    ```

- **Booleans (`archived` and `shareable`):**
  - These fields should have default values (either in the entity itself or in the database schema). You can annotate these fields with `@Column(columnDefinition = "boolean default false")` to ensure default values at the database level.

    ```java
    @Column(columnDefinition = "boolean default false")
    private boolean archived;
    ```

#### **Feedback Entity:**
```java
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Feedback extends BaseEntity {

    private Double note;
    private String comment;
}
```

- **Rating (`note`) Validation:**
  - You are using `Double` for `note`, which represents a rating (1 to 5). Ensure that this field is properly validated with minimum and maximum constraints.

    ```java
    @Min(1)
    @Max(5)
    private Double note; // or Integer depending on precision needed
    ```

- **Field Length and Input Validation:**
  - **`comment`**: Limit the size of the `comment` field to prevent excessively large inputs that can affect performance. Use `@Size` to limit this field.

    ```java
    @Size(max = 500, message = "Comment cannot exceed 500 characters.")
    private String comment;
    ```

#### **Security Improvement:**
- **Restricting Update Permissions:**
  Depending on your use case, you might want to restrict who can modify certain fields such as `archived` and `shareable`. Use security annotations or apply restrictions in the service layer.

- **Input Validation:**
  Ensure that all fields exposed in APIs are properly validated using annotations like `@NotNull`, `@Size`, and others to prevent potential SQL injection or malicious data from being stored.

### **3. Performance Considerations**

- **Indexing Fields**:
  If you anticipate frequent searches based on fields like `title`, `isbn`, `createdBy`, or `note`, consider adding indexes for these fields. Indexes can dramatically improve query performance but come with additional storage and write costs.

  ```java
  @Column(nullable = false)
  @Index(name = "idx_book_title")
  private String title;
  ```

- **Lazy vs. Eager Loading**:
  Consider the data fetching strategy based on relationships between entities. By default, related entities are loaded eagerly, which can impact performance for large datasets. Use `@ManyToOne(fetch = FetchType.LAZY)` for better performance if needed.

### **4. Data Consistency and Integrity**

- **Unique Constraints**:
  For fields like `isbn` (which should be unique for each book), use a `@UniqueConstraint` to ensure database-level enforcement of uniqueness.

  ```java
  @Column(unique = true, nullable = false)
  private String isbn;
  ```

- **Nullable Constraints**:
  Ensure that fields such as `title`, `author`, and `isbn` are not nullable in the database schema. This enforces data integrity and avoids saving invalid records.

### **Final Enhanced Implementation:**

#### **BaseEntity Class:**
```java
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(updatable = false, nullable =

 false)
    private Long createdBy;

    @LastModifiedBy
    @Column(nullable = false)
    private Long lastModifiedBy;
}
```

#### **Book Entity:**
```java
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Pattern(regexp = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- 0-9]{10}$",
             message = "Invalid ISBN format")
    @Column(unique = true, nullable = false)
    private String isbn;

    @Size(max = 1000)
    private String synopsis;

    private String bookCover;

    @Column(columnDefinition = "boolean default false")
    private boolean archived;

    @Column(columnDefinition = "boolean default false")
    private boolean shareable;
}
```

#### **Feedback Entity:**
```java
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Feedback extends BaseEntity {

    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Double note;

    @Size(max = 500)
    private String comment;
}
```

### **Summary of Best Practices and Improvements:**

1. **Security**: Ensure proper auditing by configuring Spring Security to automatically track `createdBy` and `lastModifiedBy`. Limit updates to sensitive fields like `archived` or `shareable`.
2. **Field Validation**: Use `@Pattern`, `@Min`, `@Max`, and `@Size` annotations to validate inputs and prevent invalid data.
3. **Performance**: Consider adding indexes to frequently queried fields like `title` or `isbn`. Ensure correct lazy or eager loading strategies for related entities.
4. **Consistency**: Use nullable constraints and unique constraints to enforce data integrity.
5. **Auditing**: Properly configure Spring Data auditing to populate the user and timestamp fields automatically.

This approach results in more secure, maintainable, and performant code for handling books and feedback in your system.

# 📗Let's add the relatioship between Entities

```java
package com.wchamara.book.feedback;

import com.wchamara.book.book.Book;
import com.wchamara.book.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Feedback extends BaseEntity {

    private Double note;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}

```

```java
package com.wchamara.book.history;

import com.wchamara.book.book.Book;
import com.wchamara.book.common.BaseEntity;
import com.wchamara.book.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookTransactionHistory extends BaseEntity {
//    user relationship
//    book relationship

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean returned;
    private boolean returnApproved;
}

```

```java
package com.wchamara.book.user;

import com.wchamara.book.book.Book;
import com.wchamara.book.history.BookTransactionHistory;
import com.wchamara.book.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_users")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal {


    @Id
    @GeneratedValue
    private Integer id;

    private String firstname;
    private String lastname;
    private LocalDateTime dateOfBirth;

    @Column(unique = true)
    private String email;

    private String password;
    private Boolean accountLocked;
    private Boolean enabled;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDateTime;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    @OneToMany(mappedBy = "user")
    private List<BookTransactionHistory> histories;

    /**
     * Returns the name of this {@code Principal}.
     *
     * @return the name of this {@code Principal}.
     */
    @Override
    public String getName() {
        return email;
    }

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username used to authenticate the user. Cannot return
     * <code>null</code>.
     *
     * @return the username (never <code>null</code>)
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getFullName() {
        return firstname + " " + lastname;
    }
}

```

```java
package com.wchamara.book.book;

import com.wchamara.book.common.BaseEntity;
import com.wchamara.book.feedback.Feedback;
import com.wchamara.book.history.BookTransactionHistory;
import com.wchamara.book.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {


    private String title;

    private String author;

    private String isbn;

    private String synopsis;

    private String bookCover;

    private boolean archived;

    private boolean shareable;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "book")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;
}

```

```java
package com.wchamara.book.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {


    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(updatable = false, nullable = false)
    private Integer createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Integer lastModifiedBy;
}

```

Current Database Structure

```sql
create database book_social_network
    with owner username;

create sequence public._users_seq
    increment by 50;

alter sequence public._users_seq owner to username;

create sequence public.role_seq
    increment by 50;

alter sequence public.role_seq owner to username;

create sequence public.token_seq
    increment by 50;

alter sequence public.token_seq owner to username;

create sequence public.book_seq
    increment by 50;

alter sequence public.book_seq owner to username;

create sequence public.feedback_seq
    increment by 50;

alter sequence public.feedback_seq owner to username;

create sequence public.book_transaction_history_seq
    increment by 50;

alter sequence public.book_transaction_history_seq owner to username;

create table public._users
(
    id                      integer      not null
        primary key,
    account_locked          boolean,
    created_date            timestamp(6) not null,
    date_of_birth           timestamp(6),
    email                   varchar(255)
        constraint ukhchfjvwnaa27i27bfwv0y6n1x
            unique,
    enabled                 boolean,
    firstname               varchar(255),
    last_modified_date_time timestamp(6),
    lastname                varchar(255),
    password                varchar(255)
);

alter table public._users
    owner to username;

create table public.role
(
    id                      integer      not null
        primary key,
    created_date            timestamp(6) not null,
    last_modified_date_time timestamp(6),
    name                    varchar(255)
        constraint uk8sewwnpamngi6b1dwaa88askk
            unique
);

alter table public.role
    owner to username;

create table public._users_roles
(
    users_id integer not null
        constraint fknbeyl44f4qwwil6ktqn4b1kib
            references public._users,
    roles_id integer not null
        constraint fk56bij62ihguijqsj0ydwnd6by
            references public.role
);

alter table public._users_roles
    owner to username;

create table public.token
(
    id           integer not null
        primary key,
    created_at   timestamp(6),
    expires_at   timestamp(6),
    token        varchar(255),
    validated_at timestamp(6),
    user_id      integer not null
        constraint fkitpc2cx3eub3b0cvcakffdc3q
            references public._users
);

alter table public.token
    owner to username;

create table public.book
(
    id                 integer not null
        primary key,
    archived           boolean not null,
    author             varchar(255),
    book_cover         varchar(255),
    created_by         integer,
    created_date       timestamp(6),
    isbn               varchar(255),
    last_modified_by   integer,
    last_modified_date timestamp(6),
    shareable          boolean not null,
    synopsis           varchar(255),
    title              varchar(255),
    owner              bytea,
    owner_id           integer
        constraint fk7jfkt7o7g4hkdwcgvixa92n6s
            references public._users
);

alter table public.book
    owner to username;

create table public.feedback
(
    id                 integer      not null
        primary key,
    note               double precision,
    comment            varchar(255),
    created_by         integer      not null,
    created_date       timestamp(6) not null,
    last_modified_by   integer,
    last_modified_date timestamp(6),
    book_id            integer
        constraint fkgclyi456gw0lcd6xcfj2l7r6s
            references public.book
);

alter table public.feedback
    owner to username;

create table public.book_transaction_history
(
    id                 integer      not null
        primary key,
    created_by         integer      not null,
    created_date       timestamp(6) not null,
    last_modified_by   integer,
    last_modified_date timestamp(6),
    returned           boolean      not null,
    return_approved    boolean      not null,
    book_id            integer
        constraint fketks95hi6ay47e16sj6vdv9g9
            references public.book,
    user_id            integer
        constraint fk5251obyit7w5o3mlkuflluxwx
            references public._users
);

alter table public.book_transaction_history
    owner to username;


```

Certainly! Let's delve deeply into your Java application, focusing on entity relationships, code quality, performance, and security. We'll analyze each part of your codebase, explain the relationships between entities, and suggest comprehensive improvements aligned with best practices.

## Table of Contents

1. [Overview of the Existing System](#overview)
2. [Entity Relationships Explained](#relationships)
3. [Code Quality Improvements](#code-quality)
4. [Performance Enhancements](#performance)
5. [Security Enhancements](#security)
6. [Database Schema Improvements](#database-schema)
7. [Best Practices Summary](#best-practices)

---

<a name="overview"></a>
## 1. Overview of the Existing System

Your application appears to be a **Book Social Network** where users can:

- **Register and authenticate** themselves.
- **Own books**, which can be shared or archived.
- **Provide feedback** on books.
- **Track transaction histories** related to book borrowing and returning.

The primary entities in your system are:

- **User**: Represents application users with roles and authentication details.
- **Role**: Defines user roles for authorization.
- **Book**: Represents books that users can own and share.
- **Feedback**: Allows users to leave notes and comments on books.
- **BookTransactionHistory**: Tracks the borrowing and returning of books.
- **BaseEntity**: A mapped superclass providing common fields like `id`, `createdDate`, etc.

---

<a name="relationships"></a>
## 2. Entity Relationships Explained

Understanding the relationships between entities is crucial for maintaining data integrity and optimizing database operations. Below is a detailed explanation of each relationship in your system, accompanied by examples.

### a. User ↔ Role (Many-to-Many)

**Explanation**: A user can have multiple roles (e.g., `ADMIN`, `USER`), and a role can be assigned to multiple users.

**Code Snippet**:
```java
@ManyToMany(fetch = FetchType.EAGER)
private List<Role> roles;
```

**Database Schema**:
```sql
create table public._users_roles (
    users_id integer not null references public._users,
    roles_id integer not null references public.role,
    primary key (users_id, roles_id)
);
```

**Example**:
- **User1**: Roles = [`ADMIN`, `USER`]
- **User2**: Roles = [`USER`]

### b. User ↔ Book (One-to-Many)

**Explanation**: A user can own multiple books, but each book is owned by only one user.

**Code Snippet in `User`**:
```java
@OneToMany(mappedBy = "owner")
private List<Book> books;
```

**Code Snippet in `Book`**:
```java
@ManyToOne
@JoinColumn(name = "owner_id")
private User owner;
```

**Database Schema**:
```sql
create table public.book (
    ...
    owner_id integer references public._users
);
```

**Example**:
- **User1**: Owns **Book1**, **Book2**
- **Book1**: Owned by **User1**

### c. Book ↔ Feedback (One-to-Many)

**Explanation**: A book can have multiple feedback entries, but each feedback is associated with only one book.

**Code Snippet in `Book`**:
```java
@OneToMany(mappedBy = "book")
private List<Feedback> feedbacks;
```

**Code Snippet in `Feedback`**:
```java
@ManyToOne
@JoinColumn(name = "book_id")
private Book book;
```

**Database Schema**:
```sql
create table public.feedback (
    ...
    book_id integer references public.book
);
```

**Example**:
- **Book1**: Feedbacks = [**Feedback1**, **Feedback2**]
- **Feedback1**: Associated with **Book1**

### d. User ↔ BookTransactionHistory (One-to-Many)

**Explanation**: A user can have multiple transaction histories (borrowing/returning books), but each transaction is linked to only one user.

**Code Snippet in `User`**:
```java
@OneToMany(mappedBy = "user")
private List<BookTransactionHistory> histories;
```

**Code Snippet in `BookTransactionHistory`**:
```java
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
```

**Database Schema**:
```sql
create table public.book_transaction_history (
    ...
    user_id integer references public._users
);
```

**Example**:
- **User1**: Histories = [**History1**, **History2**]
- **History1**: Linked to **User1**

### e. Book ↔ BookTransactionHistory (One-to-Many)

**Explanation**: A book can have multiple transaction histories, but each transaction is associated with only one book.

**Code Snippet in `Book`**:
```java
@OneToMany(mappedBy = "book")
private List<BookTransactionHistory> histories;
```

**Code Snippet in `BookTransactionHistory`**:
```java
@ManyToOne
@JoinColumn(name = "book_id")
private Book book;
```

**Database Schema**:
```sql
create table public.book_transaction_history (
    ...
    book_id integer references public.book
);
```

**Example**:
- **Book1**: Histories = [**History1**, **History2**]
- **History1**: Linked to **Book1**

---

<a name="code-quality"></a>
## 3. Code Quality Improvements

Improving code quality enhances maintainability, readability, and scalability. Below are suggestions tailored to your codebase.

### a. Use `Long` for ID Fields

**Issue**: Currently, `Integer` is used for ID fields, which may not suffice for large datasets.

**Improvement**:
- Replace `Integer` with `Long` for all ID fields in entities and database sequences.

**Example**:
```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

**Database Schema**:
```sql
id bigint not null primary key,
```

### b. Consistent ID Generation Strategy

**Issue**: The current database uses sequences with an increment of 50, which is unconventional and may lead to gaps.

**Improvement**:
- Use `GenerationType.IDENTITY` or `GenerationType.SEQUENCE` with `increment by 1` for IDs.

**Example**:
```java
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
@SequenceGenerator(name = "user_seq", sequenceName = "public._users_seq", allocationSize = 1)
private Long id;
```

**Database Schema**:
```sql
create sequence public._users_seq
    increment by 1
    start with 1;
```

### c. Enforce Field Validations

**Issue**: Fields lack validation annotations, potentially leading to invalid data entries.

**Improvement**:
- Utilize Bean Validation annotations to enforce data integrity.

**Example in `User`**:
```java
@Column(unique = true, nullable = false)
@Email(message = "Email should be valid")
@NotBlank(message = "Email is mandatory")
private String email;

@NotBlank(message = "Password is mandatory")
@Size(min = 8, message = "Password must be at least 8 characters")
private String password;
```

**Example in `Book`**:
```java
@NotBlank(message = "Title is mandatory")
private String title;

@NotBlank(message = "Author is mandatory")
private String author;

@Pattern(regexp = "^[0-9-]*$", message = "ISBN must contain only numbers and dashes")
private String isbn;
```

### d. Optimize Lombok Annotations

**Issue**: The use of multiple Lombok annotations can be streamlined for clarity.

**Improvement**:
- Use `@Data` for getters, setters, `toString`, `equals`, and `hashCode` methods where appropriate.
- Use `@Builder` instead of `@SuperBuilder` unless inheritance is necessary.

**Example**:
```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends BaseEntity {
    // Fields...
}
```

**Note**: Use `@SuperBuilder` only if you have class hierarchies that require it.

### e. Manage Bidirectional Relationships Properly

**Issue**: Bidirectional relationships can lead to issues like infinite recursion during JSON serialization.

**Improvement**:
- Use Jackson annotations like `@JsonManagedReference` and `@JsonBackReference` or `@JsonIgnore` to prevent serialization problems.

**Example in `User`**:
```java
@OneToMany(mappedBy = "owner")
@JsonManagedReference
private List<Book> books;
```

**Example in `Book`**:
```java
@ManyToOne
@JoinColumn(name = "owner_id")
@JsonBackReference
private User owner;
```

### f. Implement `equals()` and `hashCode()`

**Issue**: Entities lack overridden `equals()` and `hashCode()`, which can cause issues in collections and caching.

**Improvement**:
- Use Lombok's `@EqualsAndHashCode` with `onlyExplicitlyIncluded = true` and include only immutable fields like `id`.

**Example**:
```java
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class User extends BaseEntity {
    @Id
    @EqualsAndHashCode.Include
    private Long id;
    // Other fields...
}
```

### g. Refine Access Modifiers

**Issue**: Fields are set to package-private by default when using Lombok's `@Getter` and `@Setter`.

**Improvement**:
- Ensure fields are `private` to encapsulate data properly.

**Example**:
```java
@Getter
@Setter
private Double note;
```

### h. Remove Redundant or Incorrect Fields

**Issue**: In the `Book` entity, there is an `owner` field of type `bytea` in the database, which seems incorrect.

**Improvement**:
- Remove the `owner` field of type `bytea` if it doesn't correspond to any field in the `Book` entity.

**Database Schema Correction**:
```sql
-- Remove or alter the 'owner' bytea field if it's not needed
ALTER TABLE public.book DROP COLUMN IF EXISTS owner;
```

---

<a name="performance"></a>
## 4. Performance Enhancements

Optimizing performance ensures that your application remains responsive and efficient, especially as the data grows.

### a. Use `FetchType.LAZY` for Collections

**Issue**: Using `FetchType.EAGER` for collections can lead to unnecessary data loading and performance bottlenecks.

**Improvement**:
- Set `FetchType.LAZY` for all `@OneToMany` and `@ManyToMany` relationships unless immediate loading is essential.

**Example**:
```java
@ManyToMany(fetch = FetchType.LAZY)
private List<Role> roles;

@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
private List<Feedback> feedbacks;
```

### b. Implement Pagination for Large Collections

**Issue**: Loading large collections can consume significant memory and slow down responses.

**Improvement**:
- Use pagination when fetching collections like `feedbacks` or `histories`.

**Example in Repository**:
```java
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Page<Feedback> findByBookId(Long bookId, Pageable pageable);
}
```

**Example in Service**:
```java
public Page<Feedback> getFeedbacks(Long bookId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
    return feedbackRepository.findByBookId(bookId, pageable);
}
```

### c. Optimize Database Indexing

**Issue**: Missing indexes on frequently queried columns can degrade performance.

**Improvement**:
- Ensure that foreign keys and commonly searched fields have indexes.

**Example**:
```sql
CREATE INDEX idx_feedback_book_id ON public.feedback(book_id);
CREATE INDEX idx_book_transaction_history_book_id ON public.book_transaction_history(book_id);
CREATE INDEX idx_book_transaction_history_user_id ON public.book_transaction_history(user_id);
```

### d. Utilize Caching

**Issue**: Repeatedly fetching unchanged data can be inefficient.

**Improvement**:
- Implement caching mechanisms (e.g., Spring Cache with Ehcache or Redis) for read-heavy operations.

**Example**:
```java
@Cacheable(value = "books", key = "#id")
public Book getBookById(Long id) {
    return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
}
```

### e. Optimize Queries

**Issue**: N+1 query problems can occur with lazy loading in certain scenarios.

**Improvement**:
- Use **JPQL** or **Criteria API** with `JOIN FETCH` to retrieve necessary associations in a single query.

**Example**:
```java
@Query("SELECT b FROM Book b JOIN FETCH b.feedbacks WHERE b.id = :id")
Optional<Book> findByIdWithFeedbacks(@Param("id") Long id);
```

---

<a name="security"></a>
## 5. Security Enhancements

Ensuring the security of your application protects both your data and your users. Below are security-focused improvements.

### a. Secure Password Storage

**Issue**: Storing plain-text passwords poses a significant security risk.

**Improvement**:
- Always store passwords hashed using strong algorithms like BCrypt.

**Implementation**:
- Utilize Spring Security's `PasswordEncoder`.

**Example**:
```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

**Service Example**:
```java
@Autowired
private PasswordEncoder passwordEncoder;

public void registerUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
}
```

### b. Implement Proper Authentication and Authorization

**Issue**: Relying solely on entity fields like `enabled` and `accountLocked` without proper handling can lead to security loopholes.

**Improvement**:
- Ensure that Spring Security's `UserDetailsService` is correctly implemented to respect these fields.

**Example**:
```java
@Override
public boolean isAccountNonLocked() {
    return !accountLocked;
}

@Override
public boolean isEnabled() {
    return enabled;
}
```

### c. Prevent Mass Assignment Vulnerabilities

**Issue**: Using entities directly in controllers can expose fields unintentionally.

**Improvement**:
- Use Data Transfer Objects (DTOs) to control which fields can be set by clients.

**Example**:
```java
public class UserRegistrationDto {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    // Other fields...
}
```

**Controller Example**:
```java
@PostMapping("/register")
public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto dto) {
    User user = User.builder()
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    // Set other fields...
                    .build();
    userRepository.save(user);
    return ResponseEntity.ok("User registered successfully");
}
```

### d. Secure Sensitive Endpoints

**Issue**: Sensitive operations may not be adequately protected.

**Improvement**:
- Use method-level security annotations like `@PreAuthorize` to restrict access.

**Example**:
```java
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/users/{id}")
public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    userRepository.deleteById(id);
    return ResponseEntity.ok("User deleted successfully");
}
```

### e. Protect Against Cross-Site Request Forgery (CSRF)

**Issue**: CSRF attacks can exploit authenticated sessions.

**Improvement**:
- Enable CSRF protection in Spring Security, especially for state-changing operations.

**Configuration Example**:
```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and()
        .authorizeRequests()
            .antMatchers("/api/public/**").permitAll()
            .anyRequest().authenticated()
        .and()
        .formLogin()
        .and()
        .httpBasic();
}
```

### f. Validate Input to Prevent Injection Attacks

**Issue**: Unvalidated inputs can lead to SQL injection or other injection attacks.

**Improvement**:
- Use parameterized queries and validate all inputs rigorously.

**Example**:
- The use of **Spring Data JPA** inherently protects against SQL injection through parameter binding.

---

<a name="database-schema"></a>
## 6. Database Schema Improvements

A well-designed database schema ensures data integrity, optimal performance, and scalability.

### a. Correct Sequence Increments

**Issue**: Sequences are set to `increment by 50`, which is unusual and can lead to large gaps in IDs.

**Improvement**:
- Set sequences to `increment by 1` unless there's a specific need for batching.

**SQL Correction**:
```sql
create sequence public._users_seq
    increment by 1
    start with 1
    owned by public._users.id;
```

### b. Remove Redundant Columns

**Issue**: The `book` table contains an `owner` column of type `bytea`, which doesn't align with the `owner` relationship in the `Book` entity.

**Improvement**:
- Remove the redundant `owner` column to prevent confusion and maintain consistency.

**SQL Correction**:
```sql
ALTER TABLE public.book DROP COLUMN IF EXISTS owner;
```

### c. Enforce Foreign Key Constraints with ON DELETE Behavior

**Issue**: The current foreign keys lack explicit `ON DELETE` actions, which can lead to orphaned records.

**Improvement**:
- Define `ON DELETE` behavior based on business logic (e.g., `CASCADE`, `SET NULL`).

**Example**:
```sql
ALTER TABLE public.book
    DROP CONSTRAINT fk7jfkt7o7g4hkdwcgvixa92n6s;

ALTER TABLE public.book
    ADD CONSTRAINT fk7jfkt7o7g4hkdwcgvixa92n6s
    FOREIGN KEY (owner_id) REFERENCES public._users(id)
    ON DELETE SET NULL;
```

### d. Index Commonly Queried Columns

**Issue**: Without proper indexing, queries on large tables can be slow.

**Improvement**:
- Add indexes to columns frequently used in `WHERE`, `JOIN`, and `ORDER BY` clauses.

**SQL Example**:
```sql
CREATE INDEX idx_users_email ON public._users(email);
CREATE INDEX idx_book_owner_id ON public.book(owner_id);
CREATE INDEX idx_feedback_book_id ON public.feedback(book_id);
CREATE INDEX idx_book_transaction_history_user_id ON public.book_transaction_history(user_id);
CREATE INDEX idx_book_transaction_history_book_id ON public.book_transaction_history(book_id);
```

### e. Normalize Data Where Necessary

**Issue**: Ensure that data is normalized to avoid redundancy and maintain integrity.

**Improvement**:
- For instance, if `bookCover` is a URL or a path, ensure it's stored consistently.

**Example**:
- If multiple books share the same cover image, consider creating a separate `CoverImage` entity and link it to `Book`.

---

<a name="best-practices"></a>
## 7. Best Practices Summary

To encapsulate the improvements and ensure long-term maintainability and scalability, adhere to the following best practices:

### a. Follow SOLID Principles

- **Single Responsibility**: Each class should have one responsibility.
- **Open/Closed**: Classes should be open for extension but closed for modification.
- **Liskov Substitution**: Subclasses should be substitutable for their base classes.
- **Interface Segregation**: Prefer small, specific interfaces over large, general ones.
- **Dependency Inversion**: Depend on abstractions, not concretions.

### b. Use DTOs and Mappers

- **Data Transfer Objects (DTOs)**: Use DTOs to transfer data between layers, especially between the API and service layers.
- **Mapping Libraries**: Utilize libraries like MapStruct to automate the mapping process.

**Example**:
```java
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto dto);
}
```

### c. Implement Exception Handling

- **Global Exception Handlers**: Use `@ControllerAdvice` to handle exceptions globally and return meaningful error responses.

**Example**:
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ErrorResponse("Resource not found", ex.getMessage()));
    }
    
    // Other exception handlers...
}
```

### d. Leverage Spring Boot Features

- **Configuration Properties**: Externalize configuration using `application.properties` or `application.yml`.
- **Profiles**: Use Spring profiles to manage different environments (e.g., `dev`, `prod`).

### e. Write Unit and Integration Tests

- **Unit Tests**: Test individual components using frameworks like JUnit and Mockito.
- **Integration Tests**: Test the interaction between components, possibly using Testcontainers for database interactions.

**Example**:
```java
@SpringBootTest
public class UserServiceIntegrationTest {
    
    @Autowired
    private UserService userService;
    
    @Test
    public void testRegisterUser() {
        UserRegistrationDto dto = new UserRegistrationDto("test@example.com", "password123");
        userService.registerUser(dto);
        
        User user = userRepository.findByEmail("test@example.com");
        assertNotNull(user);
        assertTrue(passwordEncoder.matches("password123", user.getPassword()));
    }
}
```

### f. Document Your Code

- **JavaDoc**: Use JavaDoc to document classes and methods for better maintainability.
- **API Documentation**: Use Swagger/OpenAPI to generate interactive API documentation.

**Example**:
```java
/**
 * Registers a new user in the system.
 *
 * @param dto The user registration data transfer object.
 * @return A confirmation message upon successful registration.
 */
@PostMapping("/register")
public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto dto) {
    // Implementation...
}
```

---

## Conclusion

By implementing the above improvements and adhering to best practices, your application will benefit from enhanced **code quality**, **performance**, and **security**. These changes not only address immediate concerns but also lay a solid foundation for future development and scalability.

Your implementation of entities, their relationships, and the corresponding database schema for a **Book Social Network** demonstrates a good foundation for managing books, users, feedback, and transaction histories. Let's walk through your entities, relationships, and database structure in detail, while exploring improvements in **code quality**, **performance**, **security**, and **best practices**.

### **1. Entity Relationships and Annotations**

#### **Feedback Entity:**
```java
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Feedback extends BaseEntity {

    private Double note;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
```

- **`@ManyToOne` with `Book`**: The relationship here indicates that each feedback is associated with a specific book. The `@JoinColumn(name = "book_id")` defines the foreign key column in the `feedback` table.

##### **Improvements:**

1. **Validation of Fields**:
    - Add validation for the `note` field, ensuring the value is between 1 and 5. Use `@Min` and `@Max` for validation:
    ```java
    @Min(1)
    @Max(5)
    private Double note;
    ```
    - Limit the size of the `comment` to avoid storing overly large inputs. You can use `@Size`:
    ```java
    @Size(max = 500)
    private String comment;
    ```

2. **Handling Nullability**:
    - The `book` field is essential for feedback, so the `book_id` column should be non-nullable:
    ```java
    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    ```

#### **BookTransactionHistory Entity:**
```java
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookTransactionHistory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean returned;
    private boolean returnApproved;
}
```

- **`@ManyToOne` with `Book` and `User`**: The `BookTransactionHistory` entity captures transactions (like borrowing a book) between a `Book` and a `User`. The `@JoinColumn` ensures that the relationship is properly mapped in the database.

##### **Improvements:**

1. **Use Enumerations Instead of Booleans**:
    - For fields like `returned` and `returnApproved`, consider using an `enum` to improve readability and scalability. For instance:
    ```java
    public enum TransactionStatus {
        PENDING, APPROVED, RETURNED
    }

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    ```

    This will allow you to extend the statuses in the future without needing multiple boolean fields.

2. **Non-Nullable Fields**:
    - Both `book_id` and `user_id` are essential fields in `BookTransactionHistory`, so these should be non-nullable:
    ```java
    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    ```

3. **Validation of Booleans/Statuses**:
    - You may want to enforce rules regarding `returned` and `returnApproved` (for example, a book cannot be approved for return before being returned). These should be handled in the service layer or with database constraints (like triggers), ensuring business logic integrity.

#### **User Entity:**
```java
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_users")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal {

    // Fields and annotations...
    
    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    @OneToMany(mappedBy = "user")
    private List<BookTransactionHistory> histories;
}
```

- **`@OneToMany` with `Book` and `BookTransactionHistory`**: The `User` entity maintains a list of `books` they own and a list of transaction `histories` for borrowed books.

##### **Improvements:**

1. **Cascade Types**:
    - When a user is deleted, consider what should happen to the associated books and transaction histories. Use cascading carefully. For instance:
    ```java
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;
    ```

    Here, cascading `ALL` ensures that if a user is deleted, all their books are also deleted. However, be cautious with this setting, especially for relationships where the dependent entity should persist independently (like `BookTransactionHistory`).

2. **FetchType**:
    - For lists such as `books` and `histories`, consider using `FetchType.LAZY` to avoid performance issues. Eager fetching loads all related entities, which could lead to inefficiencies for large datasets:
    ```java
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Book> books;
    ```

#### **Book Entity:**
```java
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {

    private String title;
    private String author;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean archived;
    private boolean shareable;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "book")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;
}
```

##### **Improvements:**

1. **Unique Constraints**:
    - The `isbn` should have a unique constraint, as it uniquely identifies a book. This is already defined in your schema, but make sure it’s explicitly marked in the entity as well:
    ```java
    @Column(unique = true)
    private String isbn;
    ```

2. **Field Validation**:
    - Ensure that important fields such as `title` and `author` are non-null and constrained:
    ```java
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;
    ```

3. **Archiving Books**:
    - The `archived` field indicates whether a book is archived. It may be beneficial to introduce a method to soft-delete books, ensuring you don't lose data permanently.

4. **Fetch Strategies**:
    - For feedback and transaction histories, consider lazy fetching to avoid unnecessary data loading:
    ```java
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Feedback> feedbacks;
    ```

---

### **2. Database Schema and Structure**

#### **Current Schema**:
- The schema uses sequences and defines tables for users, roles, tokens, books, feedback, and transaction histories.

##### **Improvements to the Schema**:

1. **Indexes for Foreign Keys**:
    - Index foreign key columns like `book_id` and `user_id` in tables like `feedback` and `book_transaction_history` to improve join performance.
    ```sql
    CREATE INDEX idx_feedback_book_id ON public.feedback(book_id);
    CREATE INDEX idx_book_transaction_history_user_id ON public.book_transaction_history(user_id);
    ```

2. **Using Cascade Deletes**:
    - When a `book` is deleted, you may want to delete associated `feedback` and `transaction_history` records automatically. This can be handled with `ON DELETE CASCADE` constraints in the schema:
    ```sql
    ALTER TABLE public.feedback
    ADD CONSTRAINT fk_feedback_book
    FOREIGN KEY (book_id)
    REFERENCES public.book(id)
    ON DELETE CASCADE;
    ```

3. **Sequence Management**:
    - The sequences increment by 50, which is appropriate for systems where a high volume of records is expected. However, ensure that the sequence strategy matches your database configuration for smooth operation (e.g., PostgreSQL sequences vs. MySQL auto-increments).

4. **Nullable and Unique Constraints**:
    - Ensure that important fields like `email`, `isbn`, and `created_date` are always non-null at the database level, enforcing data integrity.
    ```sql
    ALTER TABLE public.book
    ALTER COLUMN isbn SET NOT NULL;
    ```

5. **Transactions and Concurrency Control**:
    - Consider adding optimistic locking to your entities to handle concurrency issues. This can be achieved using `@Version` in JPA, which adds a version column to handle updates in a concurrent environment:
    ```java
    @Version
    private Integer version;
    ```

---

### **3. Security Considerations**

1. **Auditing Fields**:
    - Ensure that the `createdBy` and `lastModifiedBy` fields are populated with the correct user IDs through proper configuration of the `AuditorAware` interface in Spring Security. This ensures that each action in the system is traceable to a specific user.

    Implement `AuditorAware<Long>` to fetch the currently logged-in user from the `SecurityContext`.

2. **Role-Based Access Control (RBAC)**:
    - Define role-based permissions for actions like book management, transaction approvals

, and feedback posting. Ensure that the user roles (e.g., Admin, Regular User) have appropriate access to CRUD operations.

3. **Input Validation**:
    - Protect your application from SQL injection and XSS attacks by validating all user inputs, especially in fields like `comment` (which might be shown in the UI). Utilize both server-side and client-side validation.

4. **Encryption of Sensitive Data**:
    - Ensure that sensitive fields like passwords are encrypted in the database using hashing algorithms such as `BCrypt`.

---

### **4. Best Practices and Code Quality**

1. **DTOs for Data Transfer**:
    - Use Data Transfer Objects (DTOs) to interact with the client. This separates your entity models from the API contract, making it easier to control which fields are exposed.
    - Example:
    ```java
    public class BookDTO {
        private String title;
        private String author;
        private String isbn;
        // getters and setters
    }
    ```

2. **Service Layer Logic**:
    - Move the business logic (such as managing book transactions) out of the entities and into service classes. This ensures that the domain entities remain clean and focused on representing the data structure.

3. **Transaction Management**:
    - Use `@Transactional` in service methods that modify multiple entities to ensure database consistency. For example, approving a book return might update both `BookTransactionHistory` and the `Book` itself.

---

### **Final Thoughts**

Your design provides a strong foundation for a book and feedback management system. By refining field validation, fetch strategies, cascading rules, and indexing, you can significantly improve the performance and maintainability of the system. Additionally, incorporating security measures and optimizing the database schema will ensure data integrity, scalability, and protection against vulnerabilities.

# 📗Adding application Auditor aware


```java
package com.wchamara.book.config;

import com.wchamara.book.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditAware implements AuditorAware<Integer> {
    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        User user = (User) authentication.getPrincipal();

        return Optional.ofNullable(user.getId());
    }
}

```

```java
package com.wchamara.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final UserDetailsService userDetailsService;


    @Bean
    public AuditorAware<Integer> auditorAware() {
        return new ApplicationAuditAware();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
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

Certainly! Let's thoroughly analyze the newly added components to your **Book Social Network** application, focusing on **application auditing**, **authentication**, and **security configurations**. We'll explain each code snippet in detail, followed by comprehensive suggestions for improvements and adherence to best practices concerning **code quality**, **performance**, and **security**.

## Table of Contents

1. [Overview of the Added Components](#overview)
2. [Detailed Code Explanation](#code-explanation)
    - [1. ApplicationAuditAware](#applicationauditaware)
    - [2. BeansConfig](#beansconfig)
    - [3. BookNetworkApplication](#booknetworkapplication)
3. [Improvements and Best Practices](#improvements)
    - [a. Code Quality Enhancements](#code-quality)
    - [b. Performance Optimizations](#performance)
    - [c. Security Enhancements](#security)
4. [Additional Recommendations](#additional-recommendations)
5. [Conclusion](#conclusion)

---

<a name="overview"></a>
## 1. Overview of the Added Components

Your application now includes components responsible for:

- **Auditing**: Automatically capturing the creator and last modifier of entities.
- **Authentication**: Managing user authentication processes using Spring Security.
- **Password Encoding**: Securing user passwords with encryption.
- **Application Initialization**: Setting up initial roles in the database upon application startup.

These components are crucial for maintaining data integrity, ensuring secure access, and automating the tracking of changes within your application.

---

<a name="code-explanation"></a>
## 2. Detailed Code Explanation

Let's dissect each of the provided code snippets to understand their roles and functionalities.

### a. ApplicationAuditAware

```java
package com.wchamara.book.config;

import com.wchamara.book.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditAware implements AuditorAware<Integer> {
    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        User user = (User) authentication.getPrincipal();

        return Optional.ofNullable(user.getId());
    }
}
```

**Purpose**: Implements `AuditorAware` to automatically populate auditing fields (`createdBy`, `lastModifiedBy`) in your entities with the current user's ID.

**Functionality**:
- Retrieves the current `Authentication` object from the `SecurityContextHolder`.
- Checks if the user is authenticated and not anonymous.
- Casts the `Principal` to your custom `User` entity.
- Returns the `id` of the authenticated user wrapped in an `Optional`.

**Example Scenario**:
- When a user creates a new `Book`, the `createdBy` field in `BaseEntity` is automatically set to the user's ID without manual intervention.

### b. BeansConfig

```java
package com.wchamara.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final UserDetailsService userDetailsService;


    @Bean
    public AuditorAware<Integer> auditorAware() {
        return new ApplicationAuditAware();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
```

**Purpose**: Configures essential beans for auditing, authentication, and password encoding within the Spring context.

**Key Components**:

1. **AuditorAware Bean**:
    - Registers `ApplicationAuditAware` as the bean responsible for providing auditing information.
    - Enables automatic population of auditing fields in entities.

2. **AuthenticationManager Bean**:
    - Exposes the `AuthenticationManager` for use in authentication processes.
    - Facilitates manual authentication if needed (e.g., JWT token generation).

3. **AuthenticationProvider Bean**:
    - Configures a `DaoAuthenticationProvider` that uses your `UserDetailsService` and `PasswordEncoder`.
    - Responsible for validating user credentials during authentication.

4. **PasswordEncoder Bean**:
    - Provides a `BCryptPasswordEncoder` for hashing passwords.
    - Ensures passwords are stored securely in the database.

**Example Scenario**:
- When a user logs in, `DaoAuthenticationProvider` uses `UserDetailsService` to load user details and `BCryptPasswordEncoder` to verify the password.

### c. BookNetworkApplication

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
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
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

**Purpose**: The main application class that bootstraps the Spring Boot application, enables JPA auditing and asynchronous processing, and initializes default roles in the database.

**Key Components**:

1. **@SpringBootApplication**:
    - Combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.
    - Signals Spring Boot to start component scanning and auto-configuration.

2. **@EnableJpaAuditing**:
    - Enables JPA auditing features.
    - References the `auditorAware` bean to populate auditing fields.

3. **@EnableAsync**:
    - Enables asynchronous method execution using `@Async`.
    - Allows for non-blocking operations within your services.

4. **CommandLineRunner Bean**:
    - Executes code after the Spring application context is loaded.
    - Checks if the "USER" role exists; if not, creates it.
    - Ensures that essential roles are present in the database upon startup.

**Example Scenario**:
- On application startup, if the "USER" role is absent, it's automatically created, ensuring that user registration can proceed without role-related issues.

---

<a name="improvements"></a>
## 3. Improvements and Best Practices

While your current implementation is functional, there are several enhancements and best practices you can adopt to improve **code quality**, **performance**, and **security**.

### a. Code Quality Enhancements

1. **Use `Long` for ID Fields**:
    - **Issue**: Using `Integer` for ID fields may lead to limitations with large datasets.
    - **Improvement**: Replace `Integer` with `Long` in all entities and database sequences.

    **Example**:
    ```java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ```

    **Database Schema**:
    ```sql
    id bigint not null primary key,
    ```

2. **Consistent ID Generation Strategy**:
    - **Issue**: Sequences are incremented by 50, which can create large gaps in IDs.
    - **Improvement**: Use `GenerationType.SEQUENCE` with `increment by 1` for more conventional and predictable ID generation.

    **Example**:
    ```java
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "public._users_seq", allocationSize = 1)
    private Long id;
    ```

    **Database Schema**:
    ```sql
    create sequence public._users_seq
        increment by 1
        start with 1;
    ```

3. **Enhance Bean Validation**:
    - **Issue**: Entities lack comprehensive validation annotations, risking invalid data entries.
    - **Improvement**: Use Bean Validation (e.g., `@NotBlank`, `@Email`, `@Size`) to enforce data integrity at the entity level.

    **Example in `User` Entity**:
    ```java
    @Column(unique = true, nullable = false)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    ```

4. **Optimize Lombok Annotations**:
    - **Issue**: Overuse or misuse of Lombok annotations can lead to reduced code clarity.
    - **Improvement**: Use `@Data` for classes where appropriate, but avoid it in entities to prevent unintended behavior in `toString()`, `equals()`, and `hashCode()` methods.

    **Example**:
    ```java
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    public class Role extends BaseEntity {
        @Column(unique = true, nullable = false)
        private String name;
    }
    ```

    **Note**: Avoid `@Data` in entities to prevent recursive calls in bidirectional relationships. Instead, use `@Getter` and `@Setter` selectively.

5. **Implement `equals()` and `hashCode()` Carefully**:
    - **Issue**: Default implementations can cause issues in collections and Hibernate caching.
    - **Improvement**: Use Lombok's `@EqualsAndHashCode` with `onlyExplicitlyIncluded = true` and include immutable fields like `id`.

    **Example**:
    ```java
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public class Role extends BaseEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @EqualsAndHashCode.Include
        private Long id;

        @Column(unique = true, nullable = false)
        private String name;
    }
    ```

6. **Manage Bidirectional Relationships Properly**:
    - **Issue**: Bidirectional relationships can lead to infinite recursion during JSON serialization.
    - **Improvement**: Use Jackson annotations like `@JsonManagedReference` and `@JsonBackReference` or `@JsonIgnore` to prevent serialization issues.

    **Example in `User` Entity**:
    ```java
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Book> books;
    ```

    **Example in `Book` Entity**:
    ```java
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private User owner;
    ```

7. **Refine Access Modifiers**:
    - **Issue**: Fields should encapsulate data by being `private`.
    - **Improvement**: Ensure all entity fields are marked as `private` to enforce encapsulation.

    **Example**:
    ```java
    @Getter
    @Setter
    @Entity
    public class Book extends BaseEntity {
        @NotBlank(message = "Title is mandatory")
        private String title;

        // Other fields...
    }
    ```

8. **Remove Redundant or Incorrect Fields**:
    - **Issue**: The `Book` entity might have an incorrect `owner` field of type `bytea` in the database.
    - **Improvement**: Ensure the database schema aligns with the entity definitions.

    **Database Schema Correction**:
    ```sql
    ALTER TABLE public.book DROP COLUMN IF EXISTS owner;
    ```

### b. Performance Optimizations

1. **Use `FetchType.LAZY` for Collections**:
    - **Issue**: Eager fetching can lead to performance bottlenecks by loading unnecessary data.
    - **Improvement**: Set `FetchType.LAZY` for `@OneToMany` and `@ManyToMany` relationships to load data on-demand.

    **Example**:
    ```java
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Feedback> feedbacks;
    ```

2. **Implement Pagination for Large Collections**:
    - **Issue**: Loading large datasets into memory can degrade performance.
    - **Improvement**: Use pagination when querying large collections to limit the amount of data fetched.

    **Example in Repository**:
    ```java
    public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
        Page<Feedback> findByBookId(Long bookId, Pageable pageable);
    }
    ```

    **Example in Service**:
    ```java
    @Service
    public class FeedbackService {
        @Autowired
        private FeedbackRepository feedbackRepository;

        public Page<Feedback> getFeedbacksByBookId(Long bookId, int page, int size) {
            Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
            return feedbackRepository.findByBookId(bookId, pageable);
        }
    }
    ```

3. **Optimize Database Indexing**:
    - **Issue**: Missing indexes on frequently queried columns can slow down queries.
    - **Improvement**: Ensure that foreign keys and commonly searched fields are indexed.

    **SQL Example**:
    ```sql
    CREATE INDEX idx_feedback_book_id ON public.feedback(book_id);
    CREATE INDEX idx_book_transaction_history_book_id ON public.book_transaction_history(book_id);
    CREATE INDEX idx_book_transaction_history_user_id ON public.book_transaction_history(user_id);
    CREATE INDEX idx_users_email ON public._users(email);
    ```

4. **Utilize Caching Mechanisms**:
    - **Issue**: Repeatedly fetching unchanged data can be inefficient.
    - **Improvement**: Implement caching for read-heavy operations using Spring Cache with providers like Ehcache or Redis.

    **Example**:
    ```java
    @Service
    public class BookService {
        @Autowired
        private BookRepository bookRepository;

        @Cacheable(value = "books", key = "#id")
        public Book getBookById(Long id) {
            return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        }
    }
    ```

5. **Optimize Queries to Prevent N+1 Problems**:
    - **Issue**: N+1 query problems occur when lazy loading causes multiple queries for associated entities.
    - **Improvement**: Use `JOIN FETCH` in JPQL or Entity Graphs to retrieve necessary associations in a single query.

    **Example**:
    ```java
    @Query("SELECT b FROM Book b JOIN FETCH b.feedbacks WHERE b.id = :id")
    Optional<Book> findByIdWithFeedbacks(@Param("id") Long id);
    ```

### c. Security Enhancements

1. **Secure Password Storage**:
    - **Issue**: Storing plain-text passwords is a critical security flaw.
    - **Improvement**: Use `BCryptPasswordEncoder` to hash passwords before storing them.

    **Implementation in Registration Service**:
    ```java
    @Service
    public class UserService {
        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        public User registerUser(UserRegistrationDto dto) {
            User user = User.builder()
                            .email(dto.getEmail())
                            .password(passwordEncoder.encode(dto.getPassword()))
                            .enabled(true)
                            .accountLocked(false)
                            .build();
            return userRepository.save(user);
        }
    }
    ```

2. **Implement Proper Authentication and Authorization**:
    - **Issue**: Incomplete handling of user states (`enabled`, `accountLocked`) can lead to unauthorized access.
    - **Improvement**: Ensure that `UserDetailsService` correctly reflects user states in authentication logic.

    **Example in `User` Entity**:
    ```java
    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    ```

3. **Prevent Mass Assignment Vulnerabilities**:
    - **Issue**: Directly using entities in controllers can expose sensitive fields to client manipulation.
    - **Improvement**: Use Data Transfer Objects (DTOs) to control data flow between client and server.

    **Example DTO**:
    ```java
    public class UserRegistrationDto {
        @Email
        @NotBlank
        private String email;

        @NotBlank
        @Size(min = 8)
        private String password;

        // Getters and Setters
    }
    ```

    **Controller Example**:
    ```java
    @RestController
    @RequestMapping("/api/auth")
    public class AuthController {
        @Autowired
        private UserService userService;

        @PostMapping("/register")
        public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto dto) {
            userService.registerUser(dto);
            return ResponseEntity.ok("User registered successfully");
        }
    }
    ```

4. **Secure Sensitive Endpoints with Method-Level Security**:
    - **Issue**: Critical operations may be accessible without proper authorization.
    - **Improvement**: Use annotations like `@PreAuthorize` to restrict access based on roles.

    **Example**:
    ```java
    @RestController
    @RequestMapping("/api/admin")
    public class AdminController {
        @PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping("/users/{id}")
        public ResponseEntity<?> deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        }
    }
    ```

    **Configuration**:
    - Ensure that method-level security is enabled by adding `@EnableGlobalMethodSecurity(prePostEnabled = true)` to your security configuration class.

    ```java
    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
        // Security configurations...
    }
    ```

5. **Protect Against Cross-Site Request Forgery (CSRF)**:
    - **Issue**: CSRF attacks can exploit authenticated sessions to perform unwanted actions.
    - **Improvement**: Enable CSRF protection, especially for state-changing operations.

    **Configuration Example**:
    ```java
    @Configuration
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeRequests()
                    .antMatchers("/api/public/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
        }
    }
    ```

6. **Validate Inputs to Prevent Injection Attacks**:
    - **Issue**: Unvalidated inputs can lead to SQL injection and other vulnerabilities.
    - **Improvement**: Use parameterized queries and validate all inputs using Bean Validation.

    **Example**:
    - Using Spring Data JPA's method naming conventions inherently protects against SQL injection.
    - Additionally, ensure all DTOs have appropriate validation annotations.

    ```java
    public class BookDto {
        @NotBlank(message = "Title is mandatory")
        private String title;

        @NotBlank(message = "Author is mandatory")
        private String author;

        @Pattern(regexp = "^[0-9-]*$", message = "ISBN must contain only numbers and dashes")
        private String isbn;

        // Other fields, Getters, and Setters
    }
    ```

7. **Implement Content Security Policy (CSP)**:
    - **Issue**: Without CSP, the application is vulnerable to Cross-Site Scripting (XSS) attacks.
    - **Improvement**: Define a strict Content Security Policy in HTTP headers.

    **Configuration Example**:
    ```java
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .headers()
                .contentSecurityPolicy("default-src 'self'")
            .and()
            .and()
            // Other configurations...
    }
    ```

---

<a name="additional-recommendations"></a>
## 4. Additional Recommendations

To further enhance your application, consider the following recommendations:

1. **Use DTOs and Mapping Libraries**:
    - **Issue**: Directly exposing entities can lead to tight coupling and security issues.
    - **Improvement**: Use DTOs to encapsulate data transferred between layers. Employ mapping libraries like MapStruct for efficient mapping.

    **Example with MapStruct**:
    ```java
    @Mapper(componentModel = "spring")
    public interface UserMapper {
        UserDto toDto(User user);
        User toEntity(UserDto dto);
    }
    ```

2. **Implement Global Exception Handling**:
    - **Issue**: Without centralized error handling, managing exceptions uniformly is challenging.
    - **Improvement**: Use `@ControllerAdvice` to handle exceptions globally and provide consistent error responses.

    **Example**:
    ```java
    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
            ErrorResponse error = new ErrorResponse("Resource Not Found", ex.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
            List<String> errors = ex.getBindingResult()
                                    .getFieldErrors()
                                    .stream()
                                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                    .collect(Collectors.toList());
            ErrorResponse errorResponse = new ErrorResponse("Validation Failed", errors.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // Other exception handlers...
    }
    ```

3. **Leverage Spring Boot Profiles**:
    - **Issue**: Managing different environments (development, testing, production) can be cumbersome.
    - **Improvement**: Use Spring Boot profiles to externalize configuration and switch settings based on the active profile.

    **Example**:
    - **application.yml**:
    ```yaml
    spring:
      profiles:
        active: dev
    ---
    spring:
      config:
        activate:
          on-profile: dev
      datasource:
        url: jdbc:h2:mem:testdb
        username: sa
        password:
    ---
    spring:
      config:
        activate:
          on-profile: prod
      datasource:
        url: jdbc:postgresql://localhost:5432/book_social_network
        username: username
        password: password
        driver-class-name: org.postgresql.Driver
    ```

4. **Implement Logging Best Practices**:
    - **Issue**: Inadequate logging can hinder debugging and monitoring.
    - **Improvement**: Use structured logging with appropriate log levels. Avoid logging sensitive information.

    **Example with SLF4J and Logback**:
    ```java
    @Service
    public class UserService {
        private static final Logger logger = LoggerFactory.getLogger(UserService.class);

        public User registerUser(UserRegistrationDto dto) {
            logger.info("Registering user with email: {}", dto.getEmail());
            // Registration logic...
        }
    }
    ```

5. **Write Comprehensive Tests**:
    - **Issue**: Lack of testing can lead to undetected bugs and regressions.
    - **Improvement**: Develop unit tests for individual components and integration tests for end-to-end scenarios.

    **Example with JUnit and Mockito**:
    ```java
    @ExtendWith(MockitoExtension.class)
    public class UserServiceTest {

        @Mock
        private UserRepository userRepository;

        @Mock
        private PasswordEncoder passwordEncoder;

        @InjectMocks
        private UserService userService;

        @Test
        public void testRegisterUser_Success() {
            UserRegistrationDto dto = new UserRegistrationDto("test@example.com", "password123");
            User user = User.builder()
                            .email(dto.getEmail())
                            .password("hashedPassword")
                            .enabled(true)
                            .accountLocked(false)
                            .build();

            Mockito.when(passwordEncoder.encode(dto.getPassword())).thenReturn("hashedPassword");
            Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

            User registeredUser = userService.registerUser(dto);

            assertNotNull(registeredUser);
            assertEquals("test@example.com", registeredUser.getEmail());
            assertEquals("hashedPassword", registeredUser.getPassword());
        }

        // Additional tests...
    }
    ```

6. **Implement API Documentation**:
    - **Issue**: Without proper documentation, integrating with your API can be challenging for developers.
    - **Improvement**: Use Swagger/OpenAPI to generate interactive API documentation.

    **Integration with Springfox Swagger**:
    ```java
    @Configuration
    @EnableSwagger2
    public class SwaggerConfig {
        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                      .select()
                      .apis(RequestHandlerSelectors.basePackage("com.wchamara.book"))
                      .paths(PathSelectors.any())
                      .build();
        }
    }
    ```

    **Access**: Navigate to `http://localhost:8080/swagger-ui.html` to view the API documentation.

7. **Implement Rate Limiting**:
    - **Issue**: APIs are susceptible to abuse through excessive requests.
    - **Improvement**: Implement rate limiting to protect against Denial of Service (DoS) attacks.

    **Example with Bucket4j**:
    ```java
    @RestController
    @RequestMapping("/api")
    public class ApiController {

        private final Bucket bucket;

        public ApiController() {
            Refill refill = Refill.greedy(10, Duration.ofMinutes(1));
            Bandwidth limit = Bandwidth.classic(10, refill);
            this.bucket = Bucket4j.builder().addLimit(limit).build();
        }

        @GetMapping("/books")
        public ResponseEntity<?> getBooks() {
            if (bucket.tryConsume(1)) {
                // Proceed with handling the request
            } else {
                return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests");
            }
        }
    }
    ```

8. **Use HTTPS**:
    - **Issue**: Transmitting data over unsecured channels can lead to data interception.
    - **Improvement**: Configure your application to use HTTPS in production environments.

    **Configuration Example in `application.properties`**:
    ```properties
    server.port=8443
    server.ssl.key-store=classpath:keystore.p12
    server.ssl.key-store-password=changeit
    server.ssl.key-store-type=PKCS12
    server.ssl.key-alias=tomcat
    ```

    **Note**: Obtain a valid SSL certificate for production use.

---

<a name="conclusion"></a>
## 5. Conclusion

Your application is well-structured, with a solid foundation for handling user authentication, authorization, and auditing. By implementing the suggested improvements and adhering to best practices, you can significantly enhance **code quality**, **performance**, and **security**. These enhancements not only address immediate concerns but also ensure that your application remains robust, maintainable, and scalable as it evolves.

**Key Takeaways**:

- **Code Quality**: Utilize Lombok judiciously, implement comprehensive validation, and manage relationships carefully.
- **Performance**: Optimize data fetching strategies, implement caching, and ensure proper indexing.
- **Security**: Secure password storage, implement robust authentication and authorization, and protect against common web vulnerabilities.

Feel free to reach out if you need further clarification on any of these points or assistance with specific implementations!


Your implementation of **auditing with Spring Security** is well-structured and follows the necessary steps to integrate auditing in your Spring Boot application. This approach allows you to automatically capture and store information about which user created or last modified an entity by leveraging `AuditorAware`.

Let’s go through the components you've provided and explore how they interact. I’ll also suggest **improvements**, **best practices**, **security enhancements**, and **code quality suggestions** for each section.

---

### **1. ApplicationAuditAware Class**

```java
package com.wchamara.book.config;

import com.wchamara.book.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditAware implements AuditorAware<Integer> {
    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        User user = (User) authentication.getPrincipal();

        return Optional.ofNullable(user.getId());
    }
}
```

#### **Purpose:**
- This class retrieves the currently authenticated user’s ID and supplies it to the auditing fields (`createdBy`, `lastModifiedBy`). It implements `AuditorAware<Integer>`, where `Integer` refers to the user’s ID type.
  
#### **Breakdown:**
- **`SecurityContextHolder.getContext().getAuthentication()`**: Fetches the current authentication token (which contains details about the logged-in user).
  
- **Checking if the user is authenticated**:
  - If there is no authentication (`authentication == null`), or if the user is not authenticated (`!authentication.isAuthenticated()`), or if the authentication is anonymous (`authentication instanceof AnonymousAuthenticationToken`), it returns `Optional.empty()`.
  
- **Casting the Principal to `User`**:
  - The authenticated `User` is retrieved using `authentication.getPrincipal()`, which is then cast to your custom `User` object.
  
- **Returning the user ID**:
  - The user ID is returned as an `Optional<Integer>`, which is passed to the auditing fields.

---

#### **Improvements & Best Practices:**

1. **Handle Security Edge Cases**:
   - In some cases, `getPrincipal()` may return a `String` (like a username) rather than a `User` object, especially if an authentication mechanism such as OAuth is used. Consider adding a type check before casting:
     ```java
     if (!(authentication.getPrincipal() instanceof User)) {
         return Optional.empty();
     }
     ```

2. **Audit Anonymous Actions**:
   - If you allow certain actions to be performed by unauthenticated (anonymous) users, you could return a special "anonymous" user ID (or log these actions differently). Modify `getCurrentAuditor()` to handle anonymous auditing:
     ```java
     if (authentication instanceof AnonymousAuthenticationToken) {
         return Optional.of(-1); // Return a default value for anonymous actions
     }
     ```

3. **Auditing Non-User Operations**:
   - Consider edge cases where automated processes or admin tasks (not triggered by a user) might occur. It might be good to design a fallback (e.g., system-admin user ID) for operations not performed by logged-in users.

4. **Handle SecurityContext Corruption**:
   - Add logging for scenarios where `SecurityContext` may be null or corrupted, to help track potential issues in production environments:
     ```java
     if (authentication == null || !authentication.isAuthenticated()) {
         log.warn("No authenticated user found in SecurityContext");
         return Optional.empty();
     }
     ```

---

### **2. BeansConfig Class**

```java
package com.wchamara.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public AuditorAware<Integer> auditorAware() {
        return new ApplicationAuditAware();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

#### **Purpose:**
This configuration class defines beans for security-related components such as **auditor-aware**, **authentication manager**, and **password encoder**.

#### **Breakdown:**
- **`AuditorAware` Bean**: Defines the auditor-aware implementation used for auditing.
  
- **`AuthenticationManager` Bean**: Returns the authentication manager, responsible for managing authentication processes.

- **`AuthenticationProvider` Bean**: A `DaoAuthenticationProvider` that integrates the custom `UserDetailsService` for fetching user information and uses `BCryptPasswordEncoder` for password encoding.

- **`BCryptPasswordEncoder`**: A password encoder using the BCrypt hashing algorithm to securely store passwords.

---

#### **Improvements & Best Practices:**

1. **Error Handling for `AuthenticationManager`**:
   - Ensure that `authenticationManager()` handles potential exceptions gracefully. Instead of simply throwing `Exception`, consider wrapping it in a custom exception for better context:
     ```java
     @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
         try {
             return configuration.getAuthenticationManager();
         } catch (Exception e) {
             throw new CustomAuthenticationException("Error configuring AuthenticationManager", e);
         }
     }
     ```

2. **Password Encoder Strength**:
   - `BCryptPasswordEncoder()` has a default strength of 10. For added security, consider increasing the strength if performance permits (e.g., 12 or 14). The strength controls how many rounds of hashing are applied:
     ```java
     @Bean
     public PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder(12);  // Increase strength for better security
     }
     ```

3. **Preventing Timing Attacks**:
   - To prevent timing attacks during authentication, ensure the `equals()` comparison used to verify passwords is constant-time. However, this is already handled internally by `BCryptPasswordEncoder`.

4. **`UserDetailsService` Enhancements**:
   - Consider adding caching to your `UserDetailsService` for improved performance when frequently retrieving user data, especially if your application has a large number of users.

---

### **3. BookNetworkApplication Class**

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
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
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

#### **Purpose:**
- **`@EnableJpaAuditing(auditorAwareRef = "auditorAware")`**: Enables JPA auditing and references your custom `ApplicationAuditAware` bean, allowing the application to automatically track `createdBy` and `lastModifiedBy` fields based on the currently authenticated user.
  
- **`@EnableAsync`**: Enables asynchronous method execution, allowing methods annotated with `@Async` to run asynchronously.
  
- **CommandLineRunner**: Initializes roles in the system (such as creating a default "USER" role) at application startup.

---

#### **Improvements & Best Practices:**

1. **Handling Role Creation at Startup**:
   - For better flexibility and security, consider externalizing the initial role creation to a properties file or database migration script (e.g., Flyway or Liquibase). This ensures that changes to roles don't require code changes.
   ```sql
   -- Example Flyway SQL script to insert roles
   INSERT INTO role (name) VALUES ('USER');
   ```

2. **CommandLineRunner Robustness**:
   - When checking if a role exists, ensure that you handle edge cases where `roleRepository.findByName()` could return null, and avoid concurrent modification issues:
     ```java
     public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
         return args -> {
             if (!roleRepository.existsByName("USER")) {
                 roleRepository.save(Role.builder()
                         .name("USER")
                         .build());
             }
         };
     }
     ```

3. **Security in Asynchronous Methods**:
   -

 Be cautious when using `@EnableAsync` with methods that involve security contexts. Spring Security’s `SecurityContext` is thread-local, meaning it won’t automatically propagate across threads in async methods. You may need to configure Spring Security to propagate the `SecurityContext` to asynchronous threads.

---

### **Additional Recommendations for Security:**

1. **Securing Passwords and Authentication**:
   - Ensure the proper use of `BCryptPasswordEncoder` to hash and store passwords securely.
   - Use **salt** with password hashing to further protect against dictionary and rainbow table attacks (though BCrypt handles this internally).

2. **Auditing Operations**:
   - For better audit trail security, store audit logs in a separate database or secured log system (if needed), preventing any tampering by regular users or attackers.

3. **Role-Based Access Control (RBAC)**:
   - Implement fine-grained role-based access control (RBAC) by ensuring users only have access to resources based on their roles. You can implement this using annotations like `@PreAuthorize` in controller or service methods:
     ```java
     @PreAuthorize("hasRole('ADMIN')")
     public ResponseEntity<?> createBook() {
         // Admin-only book creation
     }
     ```

4. **Avoid Hardcoded Roles**:
   - Instead of hardcoding roles like "USER" or "ADMIN", consider defining them in an enum or externalize them to configuration. This will make them easier to maintain and modify without altering the code.

---

### **Summary of Improvements:**

1. **Auditing Best Practices**: Handle edge cases in `ApplicationAuditAware`, such as anonymous or non-user actions.
2. **Security Enhancements**: Improve password hashing strength, cache `UserDetailsService`, and ensure thread-safety with asynchronous methods.
3. **Configuration**: Externalize role creation and ensure proper handling in `CommandLineRunner`.
4. **Code Quality**: Implement error handling, improve method robustness, and handle null values gracefully.

By adopting these best practices and improvements, your application will become more secure, flexible, and maintainable. Additionally, your auditing mechanism will work seamlessly across various use cases while maintaining security and performance.

# 📗Implementing Book Save Method

```java
package com.wchamara.book.book;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Integer> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
        return ResponseEntity.ok(bookService.saveBook(bookRequest, connectedUser));
    }

}

```

```java
package com.wchamara.book.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BookRequest(
        Integer id,

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String title,

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String authorName,

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String isbn,

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String synopsis,
        
        boolean shareable
) {
}

```

```java
package com.wchamara.book.book;

import com.wchamara.book.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    private final BookRepository bookRepository;

    public Integer saveBook(BookRequest bookRequest, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();

        Book book = BookMapper.toBook(bookRequest);
        book.setOwner(user);

        return bookRepository.save(book).getId();
    }
}

```

```java
package com.wchamara.book.book;

import org.springframework.stereotype.Service;

@Service

public class BookMapper {
    public static Book toBook(BookRequest bookRequest) {

        return Book
                .builder()
                .id(bookRequest.id())
                .title(bookRequest.title())
                .authorName(bookRequest.authorName())
                .isbn(bookRequest.isbn())
                .synopsis(bookRequest.synopsis())
                .archived(false)
                .shareable(bookRequest.shareable())
                .build();
    }
}

```

```java
package com.wchamara.book.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}

```

## first analysis
Your implementation of the **Book save method** follows a clean structure with a layered architecture, leveraging **Spring Boot**'s core components like validation, dependency injection, and data persistence. Let’s break down each part of your code and suggest **improvements**, **best practices**, and **security enhancements** that can make the implementation more robust, secure, and maintainable.

---

### **1. BookController Class**

```java
package com.wchamara.book.book;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Integer> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
        return ResponseEntity.ok(bookService.saveBook(bookRequest, connectedUser));
    }
}
```

#### **Explanation:**

- **`@RestController`**: Marks this class as a REST controller. All methods return a `ResponseEntity`, which simplifies returning both the status code and body.
  
- **`@Valid`**: Ensures that the `BookRequest` object is validated before reaching the service layer.
  
- **`Authentication` Parameter**: The authenticated user is passed in through the `Authentication` object, making it easy to access the currently logged-in user and associate them with the book.

- **`@RequestMapping("books")`**: Sets the base URL for book-related actions. In this case, it’s `/books`.
  
- **`@PostMapping`**: Maps this method to handle POST requests for creating or updating a book.

---

#### **Improvements:**

1. **Security Check for Ownership**:
   - While this method assumes that any authenticated user can create a book, it’s important to verify if the user has the right permissions, especially in an application that handles role-based access.
     - You can use `@PreAuthorize` to restrict access:
     ```java
     @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
     @PostMapping
     public ResponseEntity<Integer> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
         return ResponseEntity.ok(bookService.saveBook(bookRequest, connectedUser));
     }
     ```
     This ensures that only authenticated users with the appropriate roles can perform this operation.

2. **Return a More Descriptive Response**:
   - Instead of just returning the book ID, you could return a more detailed response, such as a message or the `Location` header pointing to the newly created resource:
   ```java
   URI location = ServletUriComponentsBuilder
                   .fromCurrentRequest()
                   .path("/{id}")
                   .buildAndExpand(savedBook.getId())
                   .toUri();
   
   return ResponseEntity.created(location).body(savedBook.getId());
   ```

3. **Validation Error Handling**:
   - Add custom exception handling for validation errors using `@ControllerAdvice`. This provides clearer error messages to the client.

---

### **2. BookRequest Class**

```java
package com.wchamara.book.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BookRequest(
        Integer id,

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String title,

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String authorName,

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String isbn,

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String synopsis,

        boolean shareable
) {
}
```

#### **Explanation:**

- **`@NotNull` and `@NotEmpty`**: Ensures that the fields `title`, `authorName`, `isbn`, and `synopsis` are not null or empty.
  
- **Use of Record**: Using `record` in Java provides a concise way to define immutable data transfer objects (DTOs).

---

#### **Improvements:**

1. **Custom Validation for ISBN**:
   - ISBN follows a specific format. Consider adding a custom validation annotation for the `isbn` field to ensure that the string conforms to the ISBN-10 or ISBN-13 standard:
     ```java
     @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "Invalid ISBN format")
     String isbn;
     ```

2. **More Descriptive Validation Messages**:
   - Instead of using `"100"` as a validation message, use more descriptive text:
     ```java
     @NotEmpty(message = "Title cannot be empty.")
     ```

3. **Additional Constraints**:
   - You might want to add size constraints to certain fields like `title` or `synopsis` to ensure they don’t exceed a certain length:
     ```java
     @Size(max = 255, message = "Title cannot exceed 255 characters.")
     String title;
     ```

---

### **3. BookService Class**

```java
package com.wchamara.book.book;

import com.wchamara.book.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public Integer saveBook(BookRequest bookRequest, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();

        Book book = BookMapper.toBook(bookRequest);
        book.setOwner(user);

        return bookRepository.save(book).getId();
    }
}
```

#### **Explanation:**

- **Service Layer**: The `BookService` layer is responsible for business logic. It handles the interaction between the controller and the persistence layer.
  
- **`Authentication`**: The `connectedUser` is passed from the controller and used to determine the owner of the book.

- **Mapping**: The `BookMapper` converts `BookRequest` into a `Book` entity.

---

#### **Improvements:**

1. **Handling `null` ID in BookRequest**:
   - If `bookRequest.id()` is not null, this suggests an update operation. Handle this case in the service:
     ```java
     if (bookRequest.id() != null) {
         // Fetch the existing book and update its details
         Book existingBook = bookRepository.findById(bookRequest.id())
             .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
         // Update existingBook fields here
     }
     ```

2. **Transaction Management**:
   - If you have multiple steps in the `saveBook` method (e.g., updating relationships), consider using `@Transactional` to ensure that all operations are atomic.

3. **Security Check**:
   - When updating an existing book, ensure the user owns the book:
     ```java
     if (!existingBook.getOwner().equals(user)) {
         throw new AccessDeniedException("You are not authorized to update this book.");
     }
     ```

---

### **4. BookMapper Class**

```java
package com.wchamara.book.book;

import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public static Book toBook(BookRequest bookRequest) {
        return Book
                .builder()
                .id(bookRequest.id())
                .title(bookRequest.title())
                .authorName(bookRequest.authorName())
                .isbn(bookRequest.isbn())
                .synopsis(bookRequest.synopsis())
                .archived(false)
                .shareable(bookRequest.shareable())
                .build();
    }
}
```

#### **Explanation:**

- **Mapping DTO to Entity**: This utility class handles the conversion from a `BookRequest` DTO to a `Book` entity.

---

#### **Improvements:**

1. **Null-Handling for ID**:
   - Since the `id` can be `null` during a create operation, consider only mapping the `id` when it's non-null. This will prevent unnecessary issues during entity persistence.

2. **Mapper as a Utility Class**:
   - The `BookMapper` can be turned into a `static` utility class rather than a `@Service` if it contains only static methods and doesn’t need Spring’s DI:
     ```java
     public class BookMapper {
         public static Book toBook(BookRequest bookRequest) {
             // Mapping logic
         }
     }
     ```

---

### **5. BookRepository Interface**

```java
package com.wchamara.book.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
```

#### **Explanation:**

- **JPA Repository**: This interface extends `JpaRepository`, which provides built-in CRUD operations for the `Book` entity.

---

#### **Improvements:**

1. **Custom Query Methods**:
   - Add custom query methods to fetch books by certain criteria if needed (e.g., finding books by title, author, or owner):
     ```java
     List<Book> findByOwner(User owner);
     List<Book> findByTitleContaining(String title);
     ```

2. **Pagination and Sorting**:
   - To improve performance when dealing with large datasets, consider adding methods that return paginated results:
     ```java
     Page<Book> findByOwner(User owner, Pageable pageable);
     ```

---

### **Security and Performance Enhancements:

**

1. **Input Validation**:
   - Use proper validation annotations (`@NotNull`, `@Size`, etc.) in the `BookRequest` to prevent bad data from reaching the service layer.

2. **SQL Injection Protection**:
   - Since you're using Spring Data JPA and method parameters are bound directly in JPQL or HQL queries, SQL injection risks are mitigated. However, always use parameterized queries when writing custom JPQL.

3. **Authorization**:
   - Ensure that a user can only perform operations on resources they own. If an operation involves editing a resource (e.g., updating a book), ensure that only the owner or an admin can modify the resource.

4. **Response Caching**:
   - If you have read-heavy endpoints (like fetching a list of books), consider using caching mechanisms such as **Spring Cache** or **Redis**.

5. **Rate Limiting**:
   - Implement rate limiting for certain endpoints to prevent abuse, particularly if you're dealing with public-facing APIs.

---

### **Summary of Improvements:**

1. **Security**:
   - Ensure role-based access control using `@PreAuthorize`.
   - Validate that users can only modify resources they own.

2. **Code Quality**:
   - Improve validation with more descriptive messages and custom validation for fields like ISBN.
   - Handle the update case where an ID is present in the request.
   - Use `@Transactional` for atomic operations.
  
3. **Performance**:
   - Consider caching read-heavy endpoints and implementing pagination for large datasets.

4. **Error Handling**:
   - Add proper error handling using `@ControllerAdvice` for consistent validation error responses.

By following these best practices and improvements, you can create a more secure, efficient, and maintainable book management system.
## second analysis

Certainly! Let's perform a comprehensive analysis of your **Book Save Method** implementation within your **Book Social Network** application. We'll examine each component in detail, identify areas for improvement, and suggest best practices to enhance **code quality**, **performance**, and **security**.

## Table of Contents

1. [Overview](#overview)
2. [Detailed Code Analysis](#detailed-code-analysis)
    - [1. BookController](#1-bookcontroller)
    - [2. BookRequest](#2-bookrequest)
    - [3. BookService](#3-bookservice)
    - [4. BookMapper](#4-bookmapper)
    - [5. BookRepository](#5-bookrepository)
3. [Improvements and Best Practices](#improvements-and-best-practices)
    - [a. Code Quality Enhancements](#a-code-quality-enhancements)
    - [b. Performance Optimizations](#b-performance-optimizations)
    - [c. Security Enhancements](#c-security-enhancements)
4. [Additional Recommendations](#additional-recommendations)
5. [Conclusion](#conclusion)

---

<a name="overview"></a>
## 1. Overview

Your implementation allows authenticated users to save books to the system. The flow involves:

1. **BookController**: Exposes a REST endpoint to handle HTTP POST requests for saving books.
2. **BookRequest**: Represents the incoming request payload with validation constraints.
3. **BookService**: Contains business logic to process the `BookRequest` and persist the `Book` entity.
4. **BookMapper**: Converts `BookRequest` to the `Book` entity.
5. **BookRepository**: Extends `JpaRepository` to provide CRUD operations for `Book` entities.

This modular approach separates concerns, adhering to the **Controller-Service-Repository** pattern, which is a widely accepted best practice in Spring Boot applications.

---

<a name="detailed-code-analysis"></a>
## 2. Detailed Code Analysis

Let's examine each component individually.

### 1. BookController

```java
package com.wchamara.book.book;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Integer> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
        return ResponseEntity.ok(bookService.saveBook(bookRequest, connectedUser));
    }

}
```

**Functionality:**

- **Endpoint**: `POST /books` to save a new book.
- **Request Body**: Expects a `BookRequest` object, validated using `@Valid`.
- **Authentication**: Receives the authenticated user via the `Authentication` parameter.
- **Response**: Returns the ID of the saved book wrapped in a `ResponseEntity`.

**Observations:**

- Uses Lombok's `@RequiredArgsConstructor` to inject `BookService`.
- Swagger's `@Tag` annotation for API documentation.

### 2. BookRequest

```java
package com.wchamara.book.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BookRequest(
        Integer id,

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String title,

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String authorName,

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String isbn,

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String synopsis,
        
        boolean shareable
) {
}
```

**Functionality:**

- **Data Carrier**: Immutable record representing the data required to create a `Book`.
- **Validation**: Uses `@NotNull` and `@NotEmpty` annotations to enforce non-null and non-empty fields.

**Observations:**

- The `id` field is present but typically not required for creation operations.
- The validation messages are all set to `"100"`, which is unclear and not descriptive.

### 3. BookService

```java
package com.wchamara.book.book;

import com.wchamara.book.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    private final BookRepository bookRepository;

    public Integer saveBook(BookRequest bookRequest, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();

        Book book = BookMapper.toBook(bookRequest);
        book.setOwner(user);

        return bookRepository.save(book).getId();
    }
}
```

**Functionality:**

- **Business Logic**: Converts `BookRequest` to `Book`, sets the owner, and saves the entity.
- **Returns**: The ID of the saved book.

**Observations:**

- Uses `BookMapper.toBook` statically, which might not leverage Spring's dependency injection effectively.
- Direct casting of `connectedUser.getPrincipal()` to `User` assumes that the principal is always a `User` instance.

### 4. BookMapper

```java
package com.wchamara.book.book;

import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public static Book toBook(BookRequest bookRequest) {

        return Book
                .builder()
                .id(bookRequest.id())
                .title(bookRequest.title())
                .authorName(bookRequest.authorName())
                .isbn(bookRequest.isbn())
                .synopsis(bookRequest.synopsis())
                .archived(false)
                .shareable(bookRequest.shareable())
                .build();
    }
}
```

**Functionality:**

- **Mapping**: Converts a `BookRequest` to a `Book` entity.
- **Default Values**: Sets `archived` to `false` by default.

**Observations:**

- The `toBook` method is static, which negates the need for Spring's `@Service` annotation since static methods don't leverage dependency injection.
- Uses `authorName` in `BookRequest` but in `Book` entity, it's expected to have `author`. Potential inconsistency.

### 5. BookRepository

```java
package com.wchamara.book.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
```

**Functionality:**

- **CRUD Operations**: Inherits all CRUD methods from `JpaRepository`.
- **Entity**: `Book` with primary key type `Integer`.

**Observations:**

- As per previous suggestions, consider using `Long` for ID types for scalability.

---

<a name="improvements-and-best-practices"></a>
## 3. Improvements and Best Practices

We'll address improvements in three categories: **Code Quality**, **Performance**, and **Security**.

### a. Code Quality Enhancements

1. **Use `Long` for ID Fields**

   **Issue**: Using `Integer` for IDs can lead to limitations as the number of records grows.

   **Improvement**: Replace `Integer` with `Long` for all ID fields.

   **Example**:

   ```java
   // In Book entity
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   ```

   **Repository Adjustment**:

   ```java
   public interface BookRepository extends JpaRepository<Book, Long> {
   }
   ```

   **Controller and Service Adjustment**:

   ```java
   @PostMapping
   public ResponseEntity<Long> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
       return ResponseEntity.ok(bookService.saveBook(bookRequest, connectedUser));
   }
   ```

   ```java
   public Long saveBook(BookRequest bookRequest, Authentication connectedUser) {
       // Implementation...
   }
   ```

2. **Refine `BookRequest` Validation Messages**

   **Issue**: The validation messages are set to `"100"`, which is not descriptive.

   **Improvement**: Provide meaningful messages for better client feedback and debugging.

   **Example**:

   ```java
   import jakarta.validation.constraints.Email;
   import jakarta.validation.constraints.Pattern;
   import jakarta.validation.constraints.Size;
   import jakarta.validation.constraints.NotBlank;

   public record BookRequest(
           // Removed id as it's typically generated by the system
           
           @NotBlank(message = "Title is mandatory")
           String title,

           @NotBlank(message = "Author name is mandatory")
           String authorName,

           @NotBlank(message = "ISBN is mandatory")
           @Pattern(regexp = "^[0-9-]*$", message = "ISBN must contain only numbers and dashes")
           String isbn,

           @NotBlank(message = "Synopsis is mandatory")
           String synopsis,
           
           boolean shareable
   ) {
   }
   ```

   **Notes**:

   - Removed the `id` field from `BookRequest` as it's unnecessary during creation.
   - Added `@Pattern` to `isbn` for format validation.

3. **Leverage Lombok Effectively**

   **Issue**: Overuse of `@RequiredArgsConstructor` and static methods can hinder code maintainability.

   **Improvement**: Use Lombok's `@Builder` for entity creation and avoid static methods in mapper classes.

   **Example**:

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       private final BookMapper bookMapper;

       private final BookRepository bookRepository;

       public Long saveBook(BookRequest bookRequest, Authentication connectedUser) {
           User user = (User) connectedUser.getPrincipal();

           Book book = bookMapper.toBook(bookRequest);
           book.setOwner(user);

           return bookRepository.save(book).getId();
       }
   }
   ```

   ```java
   @Service
   public class BookMapper {
       public Book toBook(BookRequest bookRequest) {

           return Book
                   .builder()
                   .title(bookRequest.title())
                   .author(bookRequest.authorName()) // Ensure consistency in field names
                   .isbn(bookRequest.isbn())
                   .synopsis(bookRequest.synopsis())
                   .archived(false)
                   .shareable(bookRequest.shareable())
                   .build();
       }
   }
   ```

   **Notes**:

   - Removed the `id` from mapping as it's auto-generated.
   - Changed `authorName` to `author` to match the `Book` entity.
   - Made `toBook` a non-static method to leverage Spring's dependency injection.

4. **Consistent Naming Conventions**

   **Issue**: Inconsistent naming between `BookRequest` (`authorName`) and `Book` entity (`author`).

   **Improvement**: Ensure consistent naming to avoid confusion.

   **Example**:

   ```java
   public record BookRequest(
           @NotBlank(message = "Title is mandatory")
           String title,

           @NotBlank(message = "Author is mandatory")
           String author,

           @NotBlank(message = "ISBN is mandatory")
           @Pattern(regexp = "^[0-9-]*$", message = "ISBN must contain only numbers and dashes")
           String isbn,

           @NotBlank(message = "Synopsis is mandatory")
           String synopsis,
           
           boolean shareable
   ) {
   }
   ```

   Update `BookMapper` accordingly:

   ```java
   public Book toBook(BookRequest bookRequest) {
       return Book.builder()
               .title(bookRequest.title())
               .author(bookRequest.author())
               .isbn(bookRequest.isbn())
               .synopsis(bookRequest.synopsis())
               .archived(false)
               .shareable(bookRequest.shareable())
               .build();
   }
   ```

5. **Implement `equals()` and `hashCode()`**

   **Issue**: Entities lack overridden `equals()` and `hashCode()` methods, which can cause issues in collections and JPA caching.

   **Improvement**: Use Lombok's `@EqualsAndHashCode` with proper configuration.

   **Example**:

   ```java
   @Entity
   @Getter
   @Setter
   @NoArgsConstructor
   @AllArgsConstructor
   @Builder
   @EqualsAndHashCode(onlyExplicitlyIncluded = true)
   public class Book extends BaseEntity {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @EqualsAndHashCode.Include
       private Long id;

       @NotBlank(message = "Title is mandatory")
       private String title;

       private String author;

       private String isbn;

       private String synopsis;

       private String bookCover;

       private boolean archived;

       private boolean shareable;

       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "owner_id")
       @JsonBackReference
       private User owner;

       @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
       @JsonManagedReference
       private List<Feedback> feedbacks;

       @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
       @JsonManagedReference
       private List<BookTransactionHistory> histories;
   }
   ```

   **Notes**:

   - Included only the `id` field in `equals()` and `hashCode()`.
   - Adjusted `fetch` types to `LAZY` to enhance performance.
   - Added Jackson annotations to manage JSON serialization.

6. **Handle Bidirectional Relationships**

   **Issue**: Bidirectional relationships can lead to infinite recursion during JSON serialization.

   **Improvement**: Use Jackson's `@JsonManagedReference` and `@JsonBackReference` to manage serialization.

   **Example**:

   ```java
   // In User entity
   @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
   @JsonManagedReference
   private List<Book> books;
   ```

   ```java
   // In Book entity
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "owner_id")
   @JsonBackReference
   private User owner;
   ```

7. **Use DTOs and Mapping Libraries**

   **Issue**: Manual mapping can lead to boilerplate code and potential errors.

   **Improvement**: Utilize mapping libraries like **MapStruct** for efficient and type-safe mapping.

   **Example**:

   **Add MapStruct Dependency**:

   ```xml
   <!-- In pom.xml -->
   <dependency>
       <groupId>org.mapstruct</groupId>
       <artifactId>mapstruct</artifactId>
       <version>1.5.5.Final</version>
   </dependency>
   <dependency>
       <groupId>org.mapstruct</groupId>
       <artifactId>mapstruct-processor</artifactId>
       <version>1.5.5.Final</version>
       <scope>provided</scope>
   </dependency>
   ```

   **Create a Mapper Interface**:

   ```java
   package com.wchamara.book.book;

   import org.mapstruct.Mapper;
   import org.mapstruct.Mapping;

   @Mapper(componentModel = "spring")
   public interface BookMapper {
       
       @Mapping(target = "archived", constant = "false")
       @Mapping(target = "owner", ignore = true) // Will set manually in service
       Book toBook(BookRequest bookRequest);
   }
   ```

   **Update BookService**:

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       private final BookMapper bookMapper;

       private final BookRepository bookRepository;

       public Long saveBook(BookRequest bookRequest, Authentication connectedUser) {
           User user = (User) connectedUser.getPrincipal();

           Book book = bookMapper.toBook(bookRequest);
           book.setOwner(user);

           return bookRepository.save(book).getId();
       }
   }
   ```

   **Benefits**:

   - Reduces boilerplate code.
   - Ensures type safety and consistency in mappings.
   - Easier maintenance and scalability.

8. **Handle Optional Fields and Default Values**

   **Issue**: Fields like `bookCover` are not present in `BookRequest` but exist in the `Book` entity.

   **Improvement**: Ensure that all necessary fields are either provided in the request or handled with default values.

   **Example**:

   ```java
   @Mapper(componentModel = "spring")
   public interface BookMapper {
       
       @Mapping(target = "archived", constant = "false")
       @Mapping(target = "bookCover", ignore = true) // Set default or handle separately
       @Mapping(target = "owner", ignore = true)
       Book toBook(BookRequest bookRequest);
   }
   ```

   **Alternative Approach**: Provide separate DTOs for creation and updates to manage fields appropriately.

9. **Remove Redundant `id` in `BookRequest`**

   **Issue**: Including `id` in `BookRequest` can lead to confusion as the ID is typically auto-generated.

   **Improvement**: Remove `id` from `BookRequest`.

   **Example**:

   ```java
   public record BookRequest(
           @NotBlank(message = "Title is mandatory")
           String title,

           @NotBlank(message = "Author is mandatory")
           String author,

           @NotBlank(message = "ISBN is mandatory")
           @Pattern(regexp = "^[0-9-]*$", message = "ISBN must contain only numbers and dashes")
           String isbn,

           @NotBlank(message = "Synopsis is mandatory")
           String synopsis,
           
           boolean shareable
   ) {
   }
   ```

### b. Performance Optimizations

1. **Use `FetchType.LAZY` for Associations**

   **Issue**: Eager fetching can lead to performance issues by loading unnecessary data.

   **Improvement**: Set `FetchType.LAZY` for associations unless immediate loading is required.

   **Example**:

   ```java
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "owner_id")
   @JsonBackReference
   private User owner;

   @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
   @JsonManagedReference
   private List<Feedback> feedbacks;

   @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
   @JsonManagedReference
   private List<BookTransactionHistory> histories;
   ```

2. **Implement Caching**

   **Issue**: Repeatedly fetching the same data can lead to unnecessary database hits.

   **Improvement**: Implement caching for frequently accessed data.

   **Example**:

   **Enable Caching in Application Class**:

   ```java
   @SpringBootApplication
   @EnableJpaAuditing(auditorAwareRef = "auditorAware")
   @EnableAsync
   @EnableCaching
   public class BookNetworkApplication {
       //...
   }
   ```

   **Configure Cache (e.g., using Ehcache)**:

   ```xml
   <!-- In pom.xml -->
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-cache</artifactId>
   </dependency>
   <dependency>
       <groupId>org.ehcache</groupId>
       <artifactId>ehcache</artifactId>
   </dependency>
   ```

   **Configure Cache in `application.yml`**:

   ```yaml
   spring:
     cache:
       type: ehcache
   ```

   **Annotate Service Method**:

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       // Existing fields...

       @Cacheable(value = "books", key = "#bookRequest.title")
       public Long saveBook(BookRequest bookRequest, Authentication connectedUser) {
           // Existing implementation...
       }
   }
   ```

   **Notes**:

   - Choose appropriate cache keys based on uniqueness.
   - Be cautious with caching write operations; consider cache eviction strategies.

3. **Implement Pagination for Large Collections**

   **Issue**: Saving operations are less likely to require pagination, but retrieving large collections (e.g., fetching all books) can benefit from it.

   **Improvement**: Implement pagination for `GET` endpoints to handle large data efficiently.

   **Example**:

   ```java
   @GetMapping
   public ResponseEntity<Page<BookResponse>> getBooks(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "10") int size) {
       Page<Book> books = bookService.getBooks(PageRequest.of(page, size));
       Page<BookResponse> response = books.map(bookMapper::toResponse);
       return ResponseEntity.ok(response);
   }
   ```

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       // Existing fields...

       public Page<Book> getBooks(Pageable pageable) {
           return bookRepository.findAll(pageable);
       }
   }
   ```

4. **Optimize Database Indexing**

   **Issue**: Without proper indexing, database queries can become slow as data grows.

   **Improvement**: Ensure that commonly queried fields are indexed.

   **Example**:

   ```java
   @Entity
   @Table(name = "book", indexes = {
           @Index(name = "idx_book_title", columnList = "title"),
           @Index(name = "idx_book_author", columnList = "author"),
           @Index(name = "idx_book_isbn", columnList = "isbn")
   })
   public class Book extends BaseEntity {
       // Existing fields...
   }
   ```

   **Notes**:

   - Indexes improve read performance but can slow down write operations. Balance based on application needs.
   - Utilize database-specific indexing features if necessary.

### c. Security Enhancements

1. **Validate and Sanitize Input**

   **Issue**: Unsanitized inputs can lead to security vulnerabilities like SQL injection and Cross-Site Scripting (XSS).

   **Improvement**: Ensure that all inputs are validated and sanitized.

   **Example**:

   - Use Bean Validation annotations (as shown in `BookRequest`).
   - Escape or sanitize inputs before processing or displaying them.

2. **Ensure Proper Authorization**

   **Issue**: Authenticated users should only perform actions they are authorized for.

   **Improvement**: Implement authorization checks to ensure users can only modify their own resources.

   **Example**:

   ```java
   @PostMapping
   @PreAuthorize("isAuthenticated()")
   public ResponseEntity<Long> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
       return ResponseEntity.ok(bookService.saveBook(bookRequest, connectedUser));
   }
   ```

   **Service Layer Authorization**:

   ```java
   public Long saveBook(BookRequest bookRequest, Authentication connectedUser) {
       User user = (User) connectedUser.getPrincipal();

       // Additional authorization logic if necessary

       Book book = bookMapper.toBook(bookRequest);
       book.setOwner(user);

       return bookRepository.save(book).getId();
   }
   ```

3. **Implement CSRF Protection**

   **Issue**: Cross-Site Request Forgery (CSRF) attacks can exploit authenticated sessions to perform unwanted actions.

   **Improvement**: Enable CSRF protection, especially for state-changing operations.

   **Example**:

   ```java
   @Configuration
   @EnableWebSecurity
   @EnableGlobalMethodSecurity(prePostEnabled = true)
   public class SecurityConfig extends WebSecurityConfigurerAdapter {

       @Override
       protected void configure(HttpSecurity http) throws Exception {
           http
               .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
               .and()
               .authorizeRequests()
                   .antMatchers("/api/public/**").permitAll()
                   .anyRequest().authenticated()
               .and()
               .formLogin()
               .and()
               .httpBasic();
       }
   }
   ```

4. **Secure Sensitive Data in Responses**

   **Issue**: Exposing sensitive fields like `owner` details in API responses can lead to information leakage.

   **Improvement**: Use response DTOs to control the data exposed to clients.

   **Example**:

   **Create `BookResponse` DTO**:

   ```java
   package com.wchamara.book.book;

   public record BookResponse(
           Long id,
           String title,
           String author,
           String isbn,
           String synopsis,
           boolean archived,
           boolean shareable,
           Long ownerId,
           String ownerEmail
   ) {
   }
   ```

   **Update `BookMapper` to Include Mapping**:

   ```java
   @Mapper(componentModel = "spring")
   public interface BookMapper {
       
       @Mapping(target = "archived", constant = "false")
       @Mapping(target = "owner", ignore = true)
       Book toBook(BookRequest bookRequest);

       @Mapping(source = "owner.id", target = "ownerId")
       @Mapping(source = "owner.email", target = "ownerEmail")
       BookResponse toResponse(Book book);
   }
   ```

   **Update Controller to Return DTO**:

   ```java
   @PostMapping
   public ResponseEntity<BookResponse> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
       Book savedBook = bookService.saveBook(bookRequest, connectedUser);
       BookResponse response = bookMapper.toResponse(savedBook);
       return ResponseEntity.ok(response);
   }
   ```

   **Update BookService**:

   ```java
   public Book saveBook(BookRequest bookRequest, Authentication connectedUser) {
       User user = (User) connectedUser.getPrincipal();

       Book book = bookMapper.toBook(bookRequest);
       book.setOwner(user);

       return bookRepository.save(book);
   }
   ```

5. **Handle Exceptions Gracefully**

   **Issue**: Unhandled exceptions can lead to application crashes and expose stack traces to clients.

   **Improvement**: Implement global exception handling using `@ControllerAdvice`.

   **Example**:

   ```java
   package com.wchamara.book.exception;

   import org.springframework.http.HttpStatus;
   import org.springframework.http.ResponseEntity;
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
           ex.getBindingResult().getFieldErrors().forEach(error -> 
               errors.put(error.getField(), error.getDefaultMessage())
           );
           return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
       }

       @ExceptionHandler(ResourceNotFoundException.class)
       public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
           return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
       }

       @ExceptionHandler(Exception.class)
       public ResponseEntity<String> handleGeneralException(Exception ex) {
           return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
   ```

6. **Leverage Spring's Validation Groups**

   **Issue**: Different operations (e.g., create vs. update) may require different validation rules.

   **Improvement**: Use validation groups to apply conditional validation.

   **Example**:

   **Define Validation Groups**:

   ```java
   public interface ValidationGroups {
       interface Create {}
       interface Update {}
   }
   ```

   **Apply to `BookRequest`**:

   ```java
   import jakarta.validation.constraints.NotBlank;
   import jakarta.validation.constraints.Pattern;
   import jakarta.validation.constraints.NotNull;

   public record BookRequest(
           @NotNull(groups = ValidationGroups.Update.class, message = "Id is required for update")
           Long id,

           @NotBlank(message = "Title is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
           String title,

           @NotBlank(message = "Author is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
           String author,

           @NotBlank(message = "ISBN is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
           @Pattern(regexp = "^[0-9-]*$", message = "ISBN must contain only numbers and dashes", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
           String isbn,

           @NotBlank(message = "Synopsis is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
           String synopsis,
           
           boolean shareable
   ) {
   }
   ```

   **Update Controller for Create and Update Operations**:

   ```java
   @PostMapping
   public ResponseEntity<BookResponse> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
       Book savedBook = bookService.saveBook(bookRequest, connectedUser);
       BookResponse response = bookMapper.toResponse(savedBook);
       return ResponseEntity.ok(response);
   }

   @PutMapping("/{id}")
   public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody @Validated(ValidationGroups.Update.class) BookRequest bookRequest, Authentication connectedUser) {
       Book updatedBook = bookService.updateBook(id, bookRequest, connectedUser);
       BookResponse response = bookMapper.toResponse(updatedBook);
       return ResponseEntity.ok(response);
   }
   ```

### b. Performance Optimizations

1. **Batch Operations**

   **Issue**: Saving multiple books individually can be inefficient.

   **Improvement**: Implement batch saving if the application allows bulk operations.

   **Example**:

   ```java
   @PostMapping("/batch")
   public ResponseEntity<List<Long>> saveBooks(@RequestBody @Valid List<BookRequest> bookRequests, Authentication connectedUser) {
       List<Long> bookIds = bookService.saveBooks(bookRequests, connectedUser);
       return ResponseEntity.ok(bookIds);
   }
   ```

   ```java
   public List<Long> saveBooks(List<BookRequest> bookRequests, Authentication connectedUser) {
       User user = (User) connectedUser.getPrincipal();

       List<Book> books = bookRequests.stream()
               .map(bookMapper::toBook)
               .peek(book -> book.setOwner(user))
               .collect(Collectors.toList());

       List<Book> savedBooks = bookRepository.saveAll(books);
       return savedBooks.stream().map(Book::getId).collect(Collectors.toList());
   }
   ```

2. **Asynchronous Processing**

   **Issue**: Saving books synchronously can lead to longer response times, especially under heavy load.

   **Improvement**: Utilize Spring's `@Async` to handle save operations asynchronously.

   **Example**:

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       private final BookMapper bookMapper;
       private final BookRepository bookRepository;

       @Async
       public CompletableFuture<Long> saveBookAsync(BookRequest bookRequest, Authentication connectedUser) {
           User user = (User) connectedUser.getPrincipal();

           Book book = bookMapper.toBook(bookRequest);
           book.setOwner(user);

           Book savedBook = bookRepository.save(book);
           return CompletableFuture.completedFuture(savedBook.getId());
       }
   }
   ```

   **Update Controller**:

   ```java
   @PostMapping
   public CompletableFuture<ResponseEntity<Long>> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
       return bookService.saveBookAsync(bookRequest, connectedUser)
               .thenApply(ResponseEntity::ok);
   }
   ```

   **Notes**:

   - Ensure that the application is configured with `@EnableAsync`.
   - Handle asynchronous exceptions appropriately.

3. **Optimizing Mapper Performance**

   **Issue**: Manual mapping or reflection-based mappers can be slower for large datasets.

   **Improvement**: Use compile-time mappers like **MapStruct** for better performance.

   **Example**: As shown earlier, using MapStruct can enhance mapping performance and reduce overhead.

4. **Database Connection Pooling**

   **Issue**: Insufficient or inefficient database connections can lead to performance bottlenecks.

   **Improvement**: Configure connection pooling with optimal settings.

   **Example**:

   **Add HikariCP Configuration in `application.yml`**:

   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/book_social_network
       username: username
       password: password
       hikari:
         maximum-pool-size: 20
         minimum-idle: 5
         idle-timeout: 30000
         max-lifetime: 1800000
         connection-timeout: 30000
   ```

   **Notes**:

   - Adjust pool sizes based on application load and database capabilities.
   - Monitor connection pool metrics to ensure optimal performance.

### c. Security Enhancements

1. **Restrict Access to Endpoints**

   **Issue**: Not all endpoints may need to be publicly accessible.

   **Improvement**: Secure endpoints based on user roles and permissions.

   **Example**:

   ```java
   @RestController
   @RequestMapping("/api/books")
   @RequiredArgsConstructor
   @Tag(name = "Book")
   public class BookController {

       private final BookService bookService;

       @PostMapping
       @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
       public ResponseEntity<BookResponse> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
           Book savedBook = bookService.saveBook(bookRequest, connectedUser);
           BookResponse response = bookMapper.toResponse(savedBook);
           return ResponseEntity.ok(response);
       }

       // Other endpoints...
   }
   ```

   **Notes**:

   - Use `@PreAuthorize` or `@Secured` annotations to enforce role-based access.
   - Ensure that Spring Security is properly configured to handle these annotations.

2. **Protect Against Overposting/Mass Assignment**

   **Issue**: Exposing entity fields directly in request objects can lead to overposting vulnerabilities.

   **Improvement**: Use DTOs (as implemented) and ensure that only intended fields are mapped.

   **Example**:

   - Already addressed by using `BookRequest` and `BookResponse` DTOs.

3. **Implement Input Sanitization**

   **Issue**: Inputs containing malicious scripts can lead to XSS attacks.

   **Improvement**: Sanitize inputs before processing or storing them.

   **Example**:

   ```java
   import org.jsoup.Jsoup;
   import org.jsoup.safety.Whitelist;

   public Book toBook(BookRequest bookRequest) {
       String safeTitle = Jsoup.clean(bookRequest.title(), Whitelist.basic());
       String safeAuthor = Jsoup.clean(bookRequest.author(), Whitelist.basic());
       String safeSynopsis = Jsoup.clean(bookRequest.synopsis(), Whitelist.basic());

       return Book.builder()
               .title(safeTitle)
               .author(safeAuthor)
               .isbn(bookRequest.isbn())
               .synopsis(safeSynopsis)
               .archived(false)
               .shareable(bookRequest.shareable())
               .build();
   }
   ```

   **Notes**:

   - Use libraries like **Jsoup** for sanitizing HTML inputs.
   - Validate and sanitize all user inputs rigorously.

4. **Implement Rate Limiting**

   **Issue**: APIs are susceptible to abuse through excessive requests, leading to DoS attacks.

   **Improvement**: Implement rate limiting to control the number of requests a user can make.

   **Example Using Bucket4j**:

   **Add Dependency**:

   ```xml
   <!-- In pom.xml -->
   <dependency>
       <groupId>com.github.vladimir-bukhtoyarov</groupId>
       <artifactId>bucket4j-core</artifactId>
       <version>7.6.0</version>
   </dependency>
   ```

   **Implement Rate Limiting Filter**:

   ```java
   package com.wchamara.book.security;

   import io.github.bucket4j.Bandwidth;
   import io.github.bucket4j.Bucket;
   import io.github.bucket4j.Refill;
   import org.springframework.stereotype.Component;

   import javax.servlet.FilterChain;
   import javax.servlet.ServletException;
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   import java.io.IOException;
   import java.time.Duration;

   @Component
   public class RateLimitingFilter extends OncePerRequestFilter {

       private final Bucket bucket;

       public RateLimitingFilter() {
           Refill refill = Refill.greedy(100, Duration.ofMinutes(1));
           Bandwidth limit = Bandwidth.classic(100, refill);
           this.bucket = Bucket.builder()
                   .addLimit(limit)
                   .build();
       }

       @Override
       protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
               throws ServletException, IOException {
           if (bucket.tryConsume(1)) {
               filterChain.doFilter(request, response);
           } else {
               response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
               response.getWriter().write("Too many requests");
           }
       }
   }
   ```

   **Register Filter**:

   ```java
   @Configuration
   public class SecurityConfig extends WebSecurityConfigurerAdapter {

       private final RateLimitingFilter rateLimitingFilter;

       public SecurityConfig(RateLimitingFilter rateLimitingFilter) {
           this.rateLimitingFilter = rateLimitingFilter;
       }

       @Override
       protected void configure(HttpSecurity http) throws Exception {
           http
               .csrf().disable()
               .authorizeRequests()
                   .antMatchers("/api/public/**").permitAll()
                   .anyRequest().authenticated()
               .and()
               .addFilterBefore(rateLimitingFilter, UsernamePasswordAuthenticationFilter.class)
               .httpBasic();
       }
   }
   ```

   **Notes**:

   - Adjust the rate limits (`100 requests per minute`) based on application requirements.
   - Customize error messages and responses as needed.

5. **Use HTTPS**

   **Issue**: Transmitting data over unsecured channels can lead to data interception and tampering.

   **Improvement**: Enforce HTTPS in production environments.

   **Example**:

   **Configure HTTPS in `application.yml`**:

   ```yaml
   server:
     port: 8443
     ssl:
       key-store: classpath:keystore.p12
       key-store-password: changeit
       key-store-type: PKCS12
       key-alias: tomcat
   ```

   **Notes**:

   - Obtain a valid SSL certificate from a trusted Certificate Authority (CA).
   - Redirect HTTP traffic to HTTPS to enforce secure connections.

6. **Implement Content Security Policy (CSP)**

   **Issue**: Without CSP, the application is vulnerable to XSS attacks.

   **Improvement**: Define a strict Content Security Policy in HTTP headers.

   **Example**:

   ```java
   @Configuration
   @EnableWebSecurity
   public class SecurityConfig extends WebSecurityConfigurerAdapter {

       @Override
       protected void configure(HttpSecurity http) throws Exception {
           http
               .headers()
                   .contentSecurityPolicy("default-src 'self'; script-src 'self'; object-src 'none';")
               .and()
                   .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
               .and()
                   .authorizeRequests()
                       .antMatchers("/api/public/**").permitAll()
                       .anyRequest().authenticated()
               .and()
                   .formLogin()
               .and()
                   .httpBasic();
       }
   }
   ```

   **Notes**:

   - Adjust CSP directives based on application needs.
   - Use tools like [CSP Evaluator](https://csp-evaluator.withgoogle.com/) to test and refine your policy.

7. **Implement Logging and Monitoring**

   **Issue**: Lack of proper logging can make debugging and monitoring difficult.

   **Improvement**: Implement structured logging and integrate monitoring tools.

   **Example**:

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       private final BookMapper bookMapper;
       private final BookRepository bookRepository;
       private final Logger logger = LoggerFactory.getLogger(BookService.class);

       public Long saveBook(BookRequest bookRequest, Authentication connectedUser) {
           try {
               User user = (User) connectedUser.getPrincipal();
               Book book = bookMapper.toBook(bookRequest);
               book.setOwner(user);
               Long bookId = bookRepository.save(book).getId();
               logger.info("Book saved successfully with ID: {}", bookId);
               return bookId;
           } catch (Exception e) {
               logger.error("Error saving book: {}", e.getMessage());
               throw e;
           }
       }
   }
   ```

   **Notes**:

   - Use logging frameworks like **SLF4J** with **Logback** or **Log4j2**.
   - Integrate with monitoring tools like **Prometheus**, **Grafana**, or **ELK Stack** for real-time monitoring and alerting.

8. **Use HTTP Status Codes Appropriately**

   **Issue**: Always returning `200 OK` might not accurately represent the outcome of operations.

   **Improvement**: Use appropriate HTTP status codes to convey the result of API operations.

   **Example**:

   ```java
   @PostMapping
   public ResponseEntity<BookResponse> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
       Book savedBook = bookService.saveBook(bookRequest, connectedUser);
       BookResponse response = bookMapper.toResponse(savedBook);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
   }
   ```

   **Notes**:

   - Use `201 CREATED` for successful resource creation.
   - Use other status codes (`400`, `404`, `500`, etc.) as appropriate.

---

<a name="additional-recommendations"></a>
## 4. Additional Recommendations

1. **Implement API Versioning**

   **Issue**: As your API evolves, changes can break existing clients.

   **Improvement**: Implement versioning to manage API changes gracefully.

   **Example**:

   **URL-Based Versioning**:

   ```java
   @RestController
   @RequestMapping("/api/v1/books")
   public class BookControllerV1 {
       // V1 endpoints
   }

   @RestController
   @RequestMapping("/api/v2/books")
   public class BookControllerV2 {
       // V2 endpoints with enhancements
   }
   ```

2. **Use OpenAPI/Swagger for Documentation**

   **Issue**: Without proper documentation, integrating with your API can be challenging for developers.

   **Improvement**: Use Swagger/OpenAPI to generate interactive API documentation.

   **Example**:

   **Add Dependencies**:

   ```xml
   <!-- In pom.xml -->
   <dependency>
       <groupId>org.springdoc</groupId>
       <artifactId>springdoc-openapi-ui</artifactId>
       <version>1.6.15</version>
   </dependency>
   ```

   **Access Documentation**:

   - Navigate to `http://localhost:8080/swagger-ui.html` or `http://localhost:8080/swagger-ui/index.html` to view the interactive API documentation.

3. **Implement Rate Limiting**

   **Issue**: APIs are susceptible to abuse through excessive requests.

   **Improvement**: Implement rate limiting to protect against Denial of Service (DoS) attacks.

   **Example**: As previously shown using **Bucket4j**.

4. **Implement Unit and Integration Tests**

   **Issue**: Lack of testing can lead to undetected bugs and regressions.

   **Improvement**: Develop comprehensive tests to ensure code reliability.

   **Example**:

   ```java
   @ExtendWith(MockitoExtension.class)
   public class BookServiceTest {

       @Mock
       private BookMapper bookMapper;

       @Mock
       private BookRepository bookRepository;

       @InjectMocks
       private BookService bookService;

       @Test
       public void testSaveBook_Success() {
           BookRequest bookRequest = new BookRequest("Title", "Author", "123-4567890123", "Synopsis", true);
           User user = User.builder()
                   .id(1L)
                   .email("user@example.com")
                   .build();
           Authentication auth = mock(Authentication.class);
           when(auth.getPrincipal()).thenReturn(user);

           Book book = Book.builder()
                   .title("Title")
                   .author("Author")
                   .isbn("123-4567890123")
                   .synopsis("Synopsis")
                   .archived(false)
                   .shareable(true)
                   .owner(user)
                   .build();
           when(bookMapper.toBook(bookRequest)).thenReturn(book);
           when(bookRepository.save(book)).thenReturn(Book.builder().id(1L).build());

           Long savedBookId = bookService.saveBook(bookRequest, auth);
           assertEquals(1L, savedBookId);

           verify(bookMapper, times(1)).toBook(bookRequest);
           verify(bookRepository, times(1)).save(book);
       }

       // Additional tests...
   }
   ```

5. **Implement Exception Handling**

   **Issue**: Unhandled exceptions can lead to application crashes and expose sensitive information.

   **Improvement**: Implement comprehensive exception handling to manage errors gracefully.

   **Example**: As shown earlier using `@ControllerAdvice`.

6. **Use Transaction Management**

   **Issue**: Ensuring data consistency across multiple operations is crucial.

   **Improvement**: Use Spring's `@Transactional` annotation to manage transactions.

   **Example**:

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       // Existing fields...

       @Transactional
       public Long saveBook(BookRequest bookRequest, Authentication connectedUser) {
           User user = (User) connectedUser.getPrincipal();

           Book book = bookMapper.toBook(bookRequest);
           book.setOwner(user);

           return bookRepository.save(book).getId();
       }
   }
   ```

   **Notes**:

   - Ensures that all database operations within the method are executed within a transaction.
   - Rolls back the transaction in case of runtime exceptions.

7. **Implement Auditing**

   **Issue**: Tracking who created or modified a book can be valuable for auditing purposes.

   **Improvement**: Use JPA Auditing to automatically populate auditing fields.

   **Example**:

   **Add Auditing Fields in `BaseEntity`** (Assuming you have one):

   ```java
   @MappedSuperclass
   @EntityListeners(AuditingEntityListener.class)
   public abstract class BaseEntity {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @CreatedDate
       @Column(updatable = false)
       private LocalDateTime createdDate;

       @LastModifiedDate
       private LocalDateTime lastModifiedDate;

       @CreatedBy
       @Column(updatable = false)
       private Long createdBy;

       @LastModifiedBy
       private Long lastModifiedBy;

       // Getters and Setters...
   }
   ```

   **Configure AuditorAware**:

   ```java
   @Configuration
   @EnableJpaAuditing(auditorAwareRef = "auditorAware")
   public class JpaConfig {

       @Bean
       public AuditorAware<Long> auditorAware() {
           return () -> {
               Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
               if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
                   return Optional.empty();
               }
               User user = (User) authentication.getPrincipal();
               return Optional.ofNullable(user.getId());
           };
       }
   }
   ```

   **Notes**:

   - Automatically populates `createdBy` and `lastModifiedBy` fields.
   - Ensure that `User` implements `UserDetails` and contains the necessary fields.

8. **Use ResponseEntity Properly**

   **Issue**: Always returning `200 OK` may not accurately reflect the outcome of operations.

   **Improvement**: Use appropriate HTTP status codes and include meaningful response bodies.

   **Example**:

   ```java
   @PostMapping
   public ResponseEntity<BookResponse> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
       Book savedBook = bookService.saveBook(bookRequest, connectedUser);
       BookResponse response = bookMapper.toResponse(savedBook);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
   }
   ```

   **Notes**:

   - `201 CREATED` indicates successful resource creation.
   - Include `Location` header if applicable to point to the newly created resource.

---

<a name="conclusion"></a>
## 5. Conclusion

Your implementation of the **Book Save Method** is on the right track, adhering to the **Controller-Service-Repository** pattern and utilizing modern Java features like records. By implementing the suggested improvements and best practices, you can significantly enhance the **code quality**, **performance**, and **security** of your application.

**Key Takeaways:**

- **Code Quality**: Use descriptive validation messages, leverage mapping libraries, maintain consistent naming conventions, and manage bidirectional relationships effectively.
- **Performance**: Optimize data fetching strategies, implement caching, and ensure efficient database operations.
- **Security**: Enforce strict validation, implement authorization checks, protect against common web vulnerabilities, and secure sensitive data transmission.

By addressing these areas, you'll build a robust, maintainable, and secure **Book Social Network** application that scales well and provides a seamless experience for your users.

If you have any specific questions or need further assistance with any of the topics discussed, feel free to ask!

# 📗let's implement the /book-id method

```java
package com.wchamara.book.book;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Integer> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
        return ResponseEntity.ok(bookService.saveBook(bookRequest, connectedUser));
    }

    @GetMapping("{book-id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("book-id") Integer bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

}

```

```java
package com.wchamara.book.book;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Integer id;
    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String owner;
    private byte[] cover;
    private double rating;
    private boolean archived;
    private boolean sharable;

}

```

```java

package com.wchamara.book.book;

import com.wchamara.book.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    private final BookRepository bookRepository;

    public Integer saveBook(BookRequest bookRequest, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();

        Book book = BookMapper.toBook(bookRequest);
        book.setOwner(user);

        return bookRepository.save(book).getId();
    }

    public BookResponse getBookById(Integer bookId) {

        return bookRepository
                .findById(bookId)
                .map(bookMapper::toBookResponse)
                .orElseThrow(() -> new EntityNotFoundException("No Book found with id: " + bookId));
    }
}

```
```java
package com.wchamara.book.book;

import org.springframework.stereotype.Service;

@Service

public class BookMapper {
    public static Book toBook(BookRequest bookRequest) {

        return Book
                .builder()
                .id(bookRequest.id())
                .title(bookRequest.title())
                .authorName(bookRequest.authorName())
                .isbn(bookRequest.isbn())
                .synopsis(bookRequest.synopsis())
                .archived(false)
                .shareable(bookRequest.shareable())
                .build();
    }

    public BookResponse toBookResponse(Book book) {
        return BookResponse
                .builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .isbn(book.getIsbn())
                .synopsis(book.getSynopsis())
                .owner(book.getOwner().getFullName())
//                todo
//                .cover(new byte[0])
                .rating(book.getRating())
                .archived(book.isArchived())
                .sharable(book.isShareable())
                .build();
    }
}

```

The implementation of the `/books/{book-id}` method for retrieving a book by its ID in your project looks quite structured and follows standard practices. However, there are some areas where improvements can be made in terms of code quality, performance, and security.

Let’s break down each part of the implementation, highlight what’s working well, and suggest best practices and improvements.

---

### **1. BookController Class**

```java
package com.wchamara.book.book;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Integer> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
        return ResponseEntity.ok(bookService.saveBook(bookRequest, connectedUser));
    }

    @GetMapping("{book-id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("book-id") Integer bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

}
```

#### **Explanation:**
- **`@RestController`**: Handles HTTP requests and returns data in a RESTful format (typically JSON).
- **`@GetMapping("{book-id}")`**: Maps the GET request to the method `getBookById`. The `@PathVariable` annotation binds the `book-id` value from the URL to the `bookId` parameter.
- **`ResponseEntity`**: Wrapping the response in a `ResponseEntity` allows you to return both the HTTP status and the body.

#### **Improvements:**

1. **Error Handling:**
   - If the book ID is invalid or doesn't exist, you throw an `EntityNotFoundException`. It would be useful to add global exception handling to return appropriate HTTP status codes and error messages (e.g., `404 NOT FOUND` for missing entities).
   - Use `@ControllerAdvice` to handle exceptions globally:
     ```java
     @ControllerAdvice
     public class GlobalExceptionHandler {
         @ExceptionHandler(EntityNotFoundException.class)
         public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
         }
     }
     ```

2. **Security Improvements:**
   - Ensure only authorized users can view a book. You might want to check if the current user has permission to access the requested book (e.g., the user may only be allowed to view their own books). Add a role-based or ownership check:
     ```java
     @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
     ```

3. **Consistency in Method Naming:**
   - Use consistent naming patterns. Consider renaming `book-id` to `id`:
     ```java
     @GetMapping("{id}")
     public ResponseEntity<BookResponse> getBookById(@PathVariable("id") Integer bookId) {
         return ResponseEntity.ok(bookService.getBookById(bookId));
     }
     ```

---

### **2. BookResponse Class**

```java
package com.wchamara.book.book;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Integer id;
    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String owner;
    private byte[] cover;
    private double rating;
    private boolean archived;
    private boolean sharable;
}
```

#### **Explanation:**
- **`BookResponse` DTO**: Used to return a structured response to the client. This decouples the entity from the response model, making it easier to customize the returned data.

#### **Improvements:**

1. **Data Type for `cover`:**
   - Currently, `cover` is of type `byte[]`. While this works for storing images, consider encoding the image as a **Base64** string if it will be passed in JSON responses to avoid binary data issues in JSON serialization:
     ```java
     private String cover;
     ```

   - You can convert the byte array to Base64 in the `BookMapper`:
     ```java
     String coverBase64 = Base64.getEncoder().encodeToString(book.getCover());
     ```

2. **Rating Default Value:**
   - Make sure the rating has a valid default value, e.g., zero for unrated books:
     ```java
     private double rating = 0.0;
     ```

3. **Field Naming Consistency:**
   - You use `sharable` but the Book entity uses `shareable`. Make sure the field names match in the DTO and entity.

---

### **3. BookService Class**

```java
package com.wchamara.book.book;

import com.wchamara.book.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public Integer saveBook(BookRequest bookRequest, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();

        Book book = BookMapper.toBook(bookRequest);
        book.setOwner(user);

        return bookRepository.save(book).getId();
    }

    public BookResponse getBookById(Integer bookId) {
        return bookRepository
                .findById(bookId)
                .map(bookMapper::toBookResponse)
                .orElseThrow(() -> new EntityNotFoundException("No Book found with id: " + bookId));
    }
}
```

#### **Explanation:**
- **`saveBook()`**: Maps the `BookRequest` to the `Book` entity, assigns the `owner`, and saves it.
- **`getBookById()`**: Fetches a book by ID and maps the result to a `BookResponse`.

#### **Improvements:**

1. **Transaction Management:**
   - Use `@Transactional` in methods that interact with the database. This ensures that all database operations within a method are atomic (either all succeed or all fail).
     ```java
     @Transactional(readOnly = true)
     public BookResponse getBookById(Integer bookId) {
         //...
     }
     ```

2. **Performance Optimization:**
   - If the `book` entity has large fields (e.g., a binary cover image), ensure that you're only fetching required fields when querying the database. You can use **Projections** or **DTOs directly in JPA** to avoid loading the entire entity.
     - Example with projections:
     ```java
     public interface BookSummary {
         Integer getId();
         String getTitle();
         String getAuthorName();
         // Add other necessary fields
     }

     @Query("SELECT b.id as id, b.title as title, b.authorName as authorName FROM Book b WHERE b.id = :bookId")
     BookSummary findSummaryById(Integer bookId);
     ```

---

### **4. BookMapper Class**

```java
package com.wchamara.book.book;

import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public static Book toBook(BookRequest bookRequest) {
        return Book
                .builder()
                .id(bookRequest.id())
                .title(bookRequest.title())
                .authorName(bookRequest.authorName())
                .isbn(bookRequest.isbn())
                .synopsis(bookRequest.synopsis())
                .archived(false)
                .shareable(bookRequest.shareable())
                .build();
    }

    public BookResponse toBookResponse(Book book) {
        return BookResponse
                .builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .isbn(book.getIsbn())
                .synopsis(book.getSynopsis())
                .owner(book.getOwner().getFullName())
//                .cover(Base64.getEncoder().encodeToString(book.getCover()))
                .rating(book.getRating())
                .archived(book.isArchived())
                .sharable(book.isShareable())
                .build();
    }
}
```

#### **Explanation:**
- **`toBook()`**: Converts the `BookRequest` DTO to a `Book` entity.
- **`toBookResponse()`**: Converts a `Book` entity to a `BookResponse` DTO.

#### **Improvements:**

1. **Base64 Encoding of Cover Image:**
   - You have a placeholder for the image cover. If the image is stored as a `byte[]` in the database, encode it to a Base64 string for the response:
     ```java
     .cover(Base64.getEncoder().encodeToString(book.getCover()))
     ```

2. **DTO to Entity Null Handling:**
   - When mapping from `BookRequest` to `Book`, ensure you handle null values properly. For example, when updating an existing book, check whether fields like `id` or `cover` are provided.

3. **Static vs Instance Method:**
   - In `toBook()`, you are using a static method, which makes `BookMapper` stateless. This is fine if the mapper doesn’t have dependencies. However, consider using non-static methods for flexibility and future extensions.

---

### **5. BookRepository Interface**

```java
package com.wchamara.book.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
```

#### **Explanation:**
- **`JpaRepository<Book,

 Integer>`**: The repository provides basic CRUD operations for the `Book` entity, using `Integer` as the ID type.

#### **Improvements:**

1. **Custom Queries:**
   - If you're frequently accessing specific fields (e.g., title, author), consider adding custom query methods that select only the required fields to improve performance, especially for large datasets.
     ```java
     @Query("SELECT b FROM Book b WHERE b.owner.id = :ownerId")
     List<Book> findByOwnerId(Integer ownerId);
     ```

---

### **Best Practices Recap:**

1. **Security Enhancements:**
   - Add role-based access control using `@PreAuthorize` for ensuring proper permissions.
   - Ensure ownership checks before allowing users to view or modify a book.

2. **Error Handling:**
   - Use `@ControllerAdvice` for consistent error handling across the application.

3. **Performance Optimization:**
   - Consider using projections or DTOs directly in JPA queries to avoid loading unnecessary data.
   - Base64 encode the cover image when sending it in a response to avoid issues with binary data in JSON.

4. **Transactional Management:**
   - Use `@Transactional` for database operations to ensure atomicity and proper transaction management.

By applying these practices, you’ll enhance the security, performance, and maintainability of your book-related API methods.

Absolutely! Let's perform a comprehensive analysis of your newly implemented `/books/{book-id}` endpoint within your **Book Social Network** application. We'll examine each component in detail, identify areas for improvement, and suggest best practices to enhance **code quality**, **performance**, and **security**.

## Table of Contents

1. [Overview](#overview)
2. [Detailed Code Analysis](#detailed-code-analysis)
    - [1. BookController](#1-bookcontroller)
    - [2. BookResponse](#2-bookresponse)
    - [3. BookService](#3-bookservice)
    - [4. BookMapper](#4-bookmapper)
    - [5. BookRepository](#5-bookrepository)
3. [Improvements and Best Practices](#improvements-and-best-practices)
    - [a. Code Quality Enhancements](#a-code-quality-enhancements)
    - [b. Performance Optimizations](#b-performance-optimizations)
    - [c. Security Enhancements](#c-security-enhancements)
4. [Additional Recommendations](#additional-recommendations)
5. [Conclusion](#conclusion)

---

<a name="overview"></a>
## 1. Overview

Your implementation introduces a new **GET** endpoint to retrieve a book by its ID. The flow involves:

1. **BookController**: Exposes a REST endpoint to handle HTTP GET requests for fetching a specific book.
2. **BookResponse**: Represents the response payload with book details.
3. **BookService**: Contains business logic to process the request and retrieve the `Book` entity.
4. **BookMapper**: Converts `Book` entity to `BookResponse` DTO.
5. **BookRepository**: Extends `JpaRepository` to provide CRUD operations for `Book` entities.

This addition complements the existing **POST** endpoint for creating books, adhering to the **Controller-Service-Repository** pattern.

---

<a name="detailed-code-analysis"></a>
## 2. Detailed Code Analysis

Let's examine each component individually to understand their roles, functionalities, and areas for improvement.

### 1. BookController

```java
package com.wchamara.book.book;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Integer> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
        return ResponseEntity.ok(bookService.saveBook(bookRequest, connectedUser));
    }

    @GetMapping("{book-id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("book-id") Integer bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

}
```

**Functionality:**

- **Endpoints:**
  - `POST /books`: Saves a new book.
  - `GET /books/{book-id}`: Retrieves a book by its ID.
  
- **Annotations:**
  - `@RestController`: Marks the class as a RESTful controller.
  - `@RequestMapping("books")`: Base URL mapping for all endpoints in this controller.
  - `@RequiredArgsConstructor`: Lombok annotation to generate a constructor with required arguments (i.e., `BookService`).
  - `@Tag(name = "Book")`: Swagger/OpenAPI annotation for API documentation.

**Observations:**

- The controller follows RESTful principles by using appropriate HTTP methods.
- Uses `Authentication` object to access the connected user in the `saveBook` method.
- The `getBookById` method directly returns a `BookResponse` wrapped in `ResponseEntity`.

### 2. BookResponse

```java
package com.wchamara.book.book;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Integer id;
    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String owner;
    private byte[] cover;
    private double rating;
    private boolean archived;
    private boolean sharable;

}
```

**Functionality:**

- **Data Carrier**: Immutable class representing the response payload sent to clients.
- **Fields**: Contains various attributes of a book, including `id`, `title`, `authorName`, `isbn`, `synopsis`, `owner`, `cover`, `rating`, `archived`, and `sharable`.

**Observations:**

- Uses Lombok annotations to generate boilerplate code.
- The `cover` field is commented as a `todo`, indicating it may need further implementation.
- The field `sharable` seems to have a typo compared to the entity's `shareable`.

### 3. BookService

```java
package com.wchamara.book.book;

import com.wchamara.book.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    private final BookRepository bookRepository;

    public Integer saveBook(BookRequest bookRequest, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();

        Book book = BookMapper.toBook(bookRequest);
        book.setOwner(user);

        return bookRepository.save(book).getId();
    }

    public BookResponse getBookById(Integer bookId) {

        return bookRepository
                .findById(bookId)
                .map(bookMapper::toBookResponse)
                .orElseThrow(() -> new EntityNotFoundException("No Book found with id: " + bookId));
    }
}
```

**Functionality:**

- **Methods:**
  - `saveBook`: Converts `BookRequest` to `Book` entity, sets the owner, saves it, and returns the book's ID.
  - `getBookById`: Retrieves a `Book` by ID, maps it to `BookResponse`, or throws an exception if not found.

**Observations:**

- Uses `@Service` and `@RequiredArgsConstructor` for dependency injection.
- Directly casts `connectedUser.getPrincipal()` to `User`, assuming the principal is always a `User` instance.
- Returns `Integer` as the book ID, aligning with the current repository configuration.

### 4. BookMapper

```java
package com.wchamara.book.book;

import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public static Book toBook(BookRequest bookRequest) {

        return Book
                .builder()
                .id(bookRequest.id())
                .title(bookRequest.title())
                .authorName(bookRequest.authorName())
                .isbn(bookRequest.isbn())
                .synopsis(bookRequest.synopsis())
                .archived(false)
                .shareable(bookRequest.shareable())
                .build();
    }

    public BookResponse toBookResponse(Book book) {
        return BookResponse
                .builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .isbn(book.getIsbn())
                .synopsis(book.getSynopsis())
                .owner(book.getOwner().getFullName())
//                todo
//                .cover(new byte[0])
                .rating(book.getRating())
                .archived(book.isArchived())
                .sharable(book.isShareable())
                .build();
    }
}
```

**Functionality:**

- **Methods:**
  - `toBook`: Converts a `BookRequest` to a `Book` entity.
  - `toBookResponse`: Converts a `Book` entity to a `BookResponse` DTO.

**Observations:**

- The `toBook` method is `static`, which negates the need for Spring's dependency injection (`@Service` annotation is redundant for static methods).
- In `toBookResponse`, the `cover` field is commented out, indicating incomplete implementation.
- The `authorName` field in `BookResponse` corresponds to the `authorName` in `BookRequest`, but in the `Book` entity, it might be named differently (`author`).
- The method `toBookResponse` accesses `book.getOwner().getFullName()`, assuming the owner is always present and valid.

### 5. BookRepository

```java
package com.wchamara.book.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
```

**Functionality:**

- **CRUD Operations**: Inherits all CRUD methods from `JpaRepository`.
- **Entity**: `Book` with primary key type `Integer`.

**Observations:**

- As previously suggested, consider using `Long` for ID types for scalability.
- Currently, no custom query methods are defined, but may be needed for future functionalities.

---

<a name="improvements-and-best-practices"></a>
## 3. Improvements and Best Practices

We'll address improvements in three categories: **Code Quality**, **Performance**, and **Security**.

### a. Code Quality Enhancements

1. **Use `Long` for ID Fields**

   **Issue**: Using `Integer` for IDs can limit scalability as the number of records increases.

   **Improvement**: Replace `Integer` with `Long` for all ID fields in entities and repositories.

   **Example:**

   ```java
   // In Book entity
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   ```

   **Repository Adjustment:**

   ```java
   public interface BookRepository extends JpaRepository<Book, Long> {
   }
   ```

   **Controller and Service Adjustment:**

   ```java
   @PostMapping
   public ResponseEntity<Long> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
       return ResponseEntity.ok(bookService.saveBook(bookRequest, connectedUser));
   }

   @GetMapping("{book-id}")
   public ResponseEntity<BookResponse> getBookById(@PathVariable("book-id") Long bookId) {
       return ResponseEntity.ok(bookService.getBookById(bookId));
   }
   ```

   ```java
   public Long saveBook(BookRequest bookRequest, Authentication connectedUser) {
       // Implementation...
   }

   public BookResponse getBookById(Long bookId) {
       // Implementation...
   }
   ```

2. **Refine `BookRequest` Validation Messages**

   **Issue**: The validation messages are set to `"100"`, which are not descriptive and can confuse clients.

   **Improvement**: Provide meaningful messages for better client feedback and debugging.

   **Example:**

   ```java
   package com.wchamara.book.book;

   import jakarta.validation.constraints.NotBlank;
   import jakarta.validation.constraints.Pattern;

   public record BookRequest(

           @NotBlank(message = "Title is mandatory")
           String title,

           @NotBlank(message = "Author name is mandatory")
           String authorName,

           @NotBlank(message = "ISBN is mandatory")
           @Pattern(regexp = "^[0-9-]*$", message = "ISBN must contain only numbers and dashes")
           String isbn,

           @NotBlank(message = "Synopsis is mandatory")
           String synopsis,
           
           boolean shareable
   ) {
   }
   ```

   **Notes:**

   - Removed the `id` field as it is typically auto-generated during creation.
   - Added `@Pattern` to `isbn` for format validation.
   - Changed validation annotations to more appropriate ones (`@NotBlank` instead of `@NotEmpty` and `@NotNull` for strings).

3. **Leverage Lombok Effectively**

   **Issue**: Overuse of `@RequiredArgsConstructor` and static methods can hinder code maintainability and testability.

   **Improvement**: Use Lombok's `@Builder` for entity creation and avoid static methods in mapper classes to leverage Spring's dependency injection.

   **Example:**

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       private final BookMapper bookMapper;
       private final BookRepository bookRepository;

       public Long saveBook(BookRequest bookRequest, Authentication connectedUser) {
           User user = (User) connectedUser.getPrincipal();

           Book book = bookMapper.toBook(bookRequest);
           book.setOwner(user);

           return bookRepository.save(book).getId();
       }

       public BookResponse getBookById(Long bookId) {
           return bookRepository
                   .findById(bookId)
                   .map(bookMapper::toBookResponse)
                   .orElseThrow(() -> new EntityNotFoundException("No Book found with id: " + bookId));
       }
   }
   ```

   ```java
   @Service
   public class BookMapper {
       public Book toBook(BookRequest bookRequest) {
           return Book.builder()
                   .title(bookRequest.title())
                   .author(bookRequest.authorName()) // Ensure consistency in field names
                   .isbn(bookRequest.isbn())
                   .synopsis(bookRequest.synopsis())
                   .archived(false)
                   .shareable(bookRequest.shareable())
                   .build();
       }

       public BookResponse toBookResponse(Book book) {
           return BookResponse.builder()
                   .id(book.getId())
                   .title(book.getTitle())
                   .authorName(book.getAuthorName())
                   .isbn(book.getIsbn())
                   .synopsis(book.getSynopsis())
                   .owner(book.getOwner().getFullName())
                   // .cover(new byte[0]) // Implement cover handling
                   .rating(book.getRating())
                   .archived(book.isArchived())
                   .sharable(book.isShareable())
                   .build();
       }
   }
   ```

   **Notes:**

   - Removed `static` modifier from `toBook` method.
   - Ensured consistent naming (`authorName` vs. `author`).
   - Facilitates easier testing and mocking.

4. **Consistent Naming Conventions**

   **Issue**: Inconsistent naming between `BookRequest` (`authorName`) and `Book` entity (`authorName` vs. `author`).

   **Improvement**: Ensure consistent naming to avoid confusion and mapping errors.

   **Example:**

   ```java
   // In BookRequest
   @NotBlank(message = "Author is mandatory")
   String author;

   // In Book entity
   private String author;
   ```

   ```java
   @Mapper(componentModel = "spring")
   public interface BookMapper {
       
       @Mapping(target = "archived", constant = "false")
       @Mapping(target = "owner", ignore = true) // Will set manually in service
       Book toBook(BookRequest bookRequest);

       @Mapping(source = "owner.id", target = "ownerId")
       @Mapping(source = "owner.email", target = "ownerEmail")
       BookResponse toBookResponse(Book book);
   }
   ```

5. **Implement `equals()` and `hashCode()`**

   **Issue**: Entities lack overridden `equals()` and `hashCode()` methods, which can cause issues in collections and JPA caching.

   **Improvement**: Use Lombok's `@EqualsAndHashCode` with proper configuration to include only relevant fields.

   **Example:**

   ```java
   @Entity
   @Getter
   @Setter
   @NoArgsConstructor
   @AllArgsConstructor
   @Builder
   @EqualsAndHashCode(onlyExplicitlyIncluded = true)
   public class Book extends BaseEntity {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @EqualsAndHashCode.Include
       private Long id;

       @NotBlank(message = "Title is mandatory")
       private String title;

       private String author;

       private String isbn;

       private String synopsis;

       private String bookCover;

       private boolean archived;

       private boolean shareable;

       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "owner_id")
       @JsonBackReference
       private User owner;

       @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
       @JsonManagedReference
       private List<Feedback> feedbacks;

       @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
       @JsonManagedReference
       private List<BookTransactionHistory> histories;

       private double rating; // Assuming getter and setter are present
   }
   ```

6. **Handle Bidirectional Relationships Properly**

   **Issue**: Bidirectional relationships can lead to infinite recursion during JSON serialization.

   **Improvement**: Use Jackson's `@JsonManagedReference` and `@JsonBackReference` annotations to manage serialization.

   **Example:**

   ```java
   // In User entity
   @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
   @JsonManagedReference
   private List<Book> books;
   ```

   ```java
   // In Book entity
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "owner_id")
   @JsonBackReference
   private User owner;
   ```

7. **Use DTOs and Mapping Libraries**

   **Issue**: Manual mapping can lead to boilerplate code and potential errors.

   **Improvement**: Utilize mapping libraries like **MapStruct** for efficient and type-safe mapping.

   **Example:**

   **Add MapStruct Dependency:**

   ```xml
   <!-- In pom.xml -->
   <dependency>
       <groupId>org.mapstruct</groupId>
       <artifactId>mapstruct</artifactId>
       <version>1.5.5.Final</version>
   </dependency>
   <dependency>
       <groupId>org.mapstruct</groupId>
       <artifactId>mapstruct-processor</artifactId>
       <version>1.5.5.Final</version>
       <scope>provided</scope>
   </dependency>
   ```

   **Create a Mapper Interface:**

   ```java
   package com.wchamara.book.book;

   import org.mapstruct.Mapper;
   import org.mapstruct.Mapping;

   @Mapper(componentModel = "spring")
   public interface BookMapper {
       
       @Mapping(target = "archived", constant = "false")
       @Mapping(target = "owner", ignore = true) // Will set manually in service
       Book toBook(BookRequest bookRequest);

       @Mapping(source = "owner.id", target = "ownerId")
       @Mapping(source = "owner.email", target = "ownerEmail")
       BookResponse toBookResponse(Book book);
   }
   ```

   **Update BookService:**

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       private final BookMapper bookMapper;
       private final BookRepository bookRepository;

       public Long saveBook(BookRequest bookRequest, Authentication connectedUser) {
           User user = (User) connectedUser.getPrincipal();

           Book book = bookMapper.toBook(bookRequest);
           book.setOwner(user);

           return bookRepository.save(book).getId();
       }

       public BookResponse getBookById(Long bookId) {
           return bookRepository
                   .findById(bookId)
                   .map(bookMapper::toBookResponse)
                   .orElseThrow(() -> new EntityNotFoundException("No Book found with id: " + bookId));
       }
   }
   ```

   **Benefits:**

   - Reduces boilerplate code.
   - Ensures type safety and consistency in mappings.
   - Easier maintenance and scalability.

8. **Remove Redundant `id` in `BookRequest`**

   **Issue**: Including `id` in `BookRequest` can lead to confusion as the ID is typically auto-generated.

   **Improvement**: Remove `id` from `BookRequest` to align with creation semantics.

   **Example:**

   ```java
   package com.wchamara.book.book;

   import jakarta.validation.constraints.NotBlank;
   import jakarta.validation.constraints.Pattern;

   public record BookRequest(

           @NotBlank(message = "Title is mandatory")
           String title,

           @NotBlank(message = "Author is mandatory")
           String author,

           @NotBlank(message = "ISBN is mandatory")
           @Pattern(regexp = "^[0-9-]*$", message = "ISBN must contain only numbers and dashes")
           String isbn,

           @NotBlank(message = "Synopsis is mandatory")
           String synopsis,
           
           boolean shareable
   ) {
   }
   ```

   **Notes:**

   - Ensures that clients do not provide an `id` during creation, preventing potential conflicts or security issues.

9. **Implement Consistent Field Naming**

   **Issue**: Inconsistent field names between `BookRequest`, `Book`, and `BookResponse` can cause confusion.

   **Improvement**: Ensure that field names are consistent across different layers.

   **Example:**

   - Use `author` consistently instead of `authorName`.

   ```java
   public record BookResponse(
           Long id,
           String title,
           String author,
           String isbn,
           String synopsis,
           String owner,
           byte[] cover,
           double rating,
           boolean archived,
           boolean sharable
   ) {
   }
   ```

   ```java
   public BookResponse toBookResponse(Book book) {
       return BookResponse.builder()
               .id(book.getId())
               .title(book.getTitle())
               .author(book.getAuthor())
               .isbn(book.getIsbn())
               .synopsis(book.getSynopsis())
               .owner(book.getOwner().getFullName())
               // .cover(book.getBookCover()) // Implement cover handling
               .rating(book.getRating())
               .archived(book.isArchived())
               .sharable(book.isShareable())
               .build();
   }
   ```

### b. Performance Optimizations

1. **Use `FetchType.LAZY` for Associations**

   **Issue**: Eager fetching can lead to performance issues by loading unnecessary data.

   **Improvement**: Set `FetchType.LAZY` for associations unless immediate loading is required.

   **Example:**

   ```java
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "owner_id")
   @JsonBackReference
   private User owner;

   @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
   @JsonManagedReference
   private List<Feedback> feedbacks;

   @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
   @JsonManagedReference
   private List<BookTransactionHistory> histories;
   ```

   **Notes:**

   - Lazy loading ensures that related entities are fetched only when accessed, reducing initial load times and memory consumption.

2. **Implement Caching**

   **Issue**: Repeatedly fetching the same data can lead to unnecessary database hits.

   **Improvement**: Implement caching for frequently accessed data using Spring Cache with providers like Ehcache or Redis.

   **Example:**

   **Enable Caching in Application Class:**

   ```java
   @SpringBootApplication
   @EnableJpaAuditing(auditorAwareRef = "auditorAware")
   @EnableAsync
   @EnableCaching
   public class BookNetworkApplication {
       public static void main(String[] args) {
           SpringApplication.run(BookNetworkApplication.class, args);
       }

       // Existing CommandLineRunner...
   }
   ```

   **Configure Cache (e.g., using Ehcache):**

   **Add Dependencies:**

   ```xml
   <!-- In pom.xml -->
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-cache</artifactId>
   </dependency>
   <dependency>
       <groupId>org.ehcache</groupId>
       <artifactId>ehcache</artifactId>
   </dependency>
   ```

   **Configure Cache in `application.yml`:**

   ```yaml
   spring:
     cache:
       type: ehcache
   ```

   **Annotate Service Method:**

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       private final BookMapper bookMapper;
       private final BookRepository bookRepository;

       @Cacheable(value = "books", key = "#bookId")
       public BookResponse getBookById(Long bookId) {
           return bookRepository
                   .findById(bookId)
                   .map(bookMapper::toBookResponse)
                   .orElseThrow(() -> new EntityNotFoundException("No Book found with id: " + bookId));
       }

       // Existing saveBook method...
   }
   ```

   **Notes:**

   - Caching the `getBookById` method can significantly reduce database load for frequently accessed books.
   - Ensure that cache eviction policies are in place to handle updates and deletions.

3. **Implement Pagination for Large Collections**

   **Issue**: Although the current GET endpoint fetches a single book, other endpoints fetching lists of books may benefit from pagination.

   **Improvement**: Use pagination when querying large collections to limit the amount of data fetched and improve response times.

   **Example:**

   ```java
   @GetMapping
   public ResponseEntity<Page<BookResponse>> getAllBooks(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "10") int size) {
       Page<Book> books = bookService.getAllBooks(PageRequest.of(page, size));
       Page<BookResponse> response = books.map(bookMapper::toBookResponse);
       return ResponseEntity.ok(response);
   }
   ```

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       private final BookMapper bookMapper;
       private final BookRepository bookRepository;

       // Existing methods...

       public Page<Book> getAllBooks(Pageable pageable) {
           return bookRepository.findAll(pageable);
       }
   }
   ```

   **Notes:**

   - Pagination improves performance by limiting data transfer and processing.
   - Clients can navigate through pages using `page` and `size` parameters.

4. **Optimize Database Indexing**

   **Issue**: Without proper indexing, database queries can become slow as data grows.

   **Improvement**: Ensure that commonly queried fields are indexed.

   **Example:**

   ```java
   @Entity
   @Table(name = "book", indexes = {
           @Index(name = "idx_book_title", columnList = "title"),
           @Index(name = "idx_book_author", columnList = "author"),
           @Index(name = "idx_book_isbn", columnList = "isbn")
   })
   public class Book extends BaseEntity {
       // Existing fields...
   }
   ```

   **Notes:**

   - Indexes on `title`, `author`, and `isbn` can speed up search queries.
   - Be cautious with the number of indexes as they can slow down write operations.

5. **Implement Asynchronous Processing**

   **Issue**: Although saving a single book is generally quick, under heavy load, it can lead to longer response times.

   **Improvement**: Utilize Spring's `@Async` to handle save operations asynchronously if necessary.

   **Example:**

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       private final BookMapper bookMapper;
       private final BookRepository bookRepository;

       @Async
       public CompletableFuture<Long> saveBookAsync(BookRequest bookRequest, Authentication connectedUser) {
           User user = (User) connectedUser.getPrincipal();

           Book book = bookMapper.toBook(bookRequest);
           book.setOwner(user);

           Book savedBook = bookRepository.save(book);
           return CompletableFuture.completedFuture(savedBook.getId());
       }

       public BookResponse getBookById(Long bookId) {
           return bookRepository
                   .findById(bookId)
                   .map(bookMapper::toBookResponse)
                   .orElseThrow(() -> new EntityNotFoundException("No Book found with id: " + bookId));
       }
   }
   ```

   **Update Controller:**

   ```java
   @PostMapping
   public CompletableFuture<ResponseEntity<Long>> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
       return bookService.saveBookAsync(bookRequest, connectedUser)
               .thenApply(id -> ResponseEntity.status(HttpStatus.CREATED).body(id));
   }
   ```

   **Notes:**

   - Ensure that the application is configured with `@EnableAsync` (already present in `BookNetworkApplication`).
   - Handle asynchronous exceptions appropriately.

6. **Optimize Mapper Performance**

   **Issue**: Manual mapping or reflection-based mappers can be slower for large datasets.

   **Improvement**: Use compile-time mappers like **MapStruct** for better performance.

   **Example:**

   As shown in the previous section, using MapStruct can enhance mapping performance and reduce overhead.

7. **Implement Transaction Management**

   **Issue**: Ensuring data consistency across multiple operations is crucial.

   **Improvement**: Use Spring's `@Transactional` annotation to manage transactions.

   **Example:**

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       private final BookMapper bookMapper;
       private final BookRepository bookRepository;

       @Transactional
       public Long saveBook(BookRequest bookRequest, Authentication connectedUser) {
           User user = (User) connectedUser.getPrincipal();

           Book book = bookMapper.toBook(bookRequest);
           book.setOwner(user);

           return bookRepository.save(book).getId();
       }

       @Transactional(readOnly = true)
       public BookResponse getBookById(Long bookId) {
           return bookRepository
                   .findById(bookId)
                   .map(bookMapper::toBookResponse)
                   .orElseThrow(() -> new EntityNotFoundException("No Book found with id: " + bookId));
       }
   }
   ```

   **Notes:**

   - `@Transactional` ensures that all database operations within the method are executed within a transaction.
   - `readOnly = true` for read operations can optimize performance by hinting to the database that no data modifications will occur.

8. **Implement Auditing**

   **Issue**: Tracking who created or modified a book can be valuable for auditing purposes.

   **Improvement**: Use JPA Auditing to automatically populate auditing fields.

   **Example:**

   **Add Auditing Fields in `BaseEntity` (Assuming you have one):**

   ```java
   @MappedSuperclass
   @EntityListeners(AuditingEntityListener.class)
   public abstract class BaseEntity {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @CreatedDate
       @Column(updatable = false)
       private LocalDateTime createdDate;

       @LastModifiedDate
       private LocalDateTime lastModifiedDate;

       @CreatedBy
       @Column(updatable = false)
       private Long createdBy;

       @LastModifiedBy
       private Long lastModifiedBy;

       // Getters and Setters...
   }
   ```

   **Configure `AuditorAware`:**

   Ensure that `AuditorAware` is correctly implemented to provide the current user's ID. From your previous code:

   ```java
   @Configuration
   @RequiredArgsConstructor
   public class BeansConfig {

       private final UserDetailsService userDetailsService;

       @Bean
       public AuditorAware<Long> auditorAware() {
           return new ApplicationAuditAware();
       }

       // Existing beans...
   }
   ```

   **Update `ApplicationAuditAware`:**

   Ensure that `ApplicationAuditAware` returns `Long` instead of `Integer` to match the `createdBy` and `lastModifiedBy` fields.

   ```java
   public class ApplicationAuditAware implements AuditorAware<Long> {
       /**
        * Returns the current auditor of the application.
        *
        * @return the current auditor.
        */
       @Override
       public Optional<Long> getCurrentAuditor() {
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

           if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
               return Optional.empty();
           }

           User user = (User) authentication.getPrincipal();

           return Optional.ofNullable(user.getId());
       }
   }
   ```

9. **Use DTOs and Response Entities Properly**

   **Issue**: The current implementation returns the book ID directly, but clients may benefit from receiving detailed book information.

   **Improvement**: Return a comprehensive `BookResponse` DTO that includes all necessary book details.

   **Example:**

   As updated in the `BookController` and `BookService`, the `getBookById` method returns a `BookResponse`.

10. **Implement Content Negotiation**

    **Issue**: Clients may expect different response formats (e.g., JSON, XML).

    **Improvement**: Ensure that your API can handle multiple content types as needed.

    **Example:**

    Configure `ContentNegotiationConfigurer` if necessary, or rely on Spring Boot's default settings.

    ```java
    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
            configurer
                .favorPathExtension(false)
                .favorParameter(true)
                .parameterName("mediaType")
                .ignoreAcceptHeader(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
        }
    }
    ```

    **Notes:**

    - Ensure that appropriate message converters are configured to handle different media types.

### b. Performance Optimizations

1. **Implement Caching**

   **Issue**: Repeatedly fetching the same book can lead to unnecessary database hits.

   **Improvement**: Implement caching for the `getBookById` method.

   **Example:**

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       private final BookMapper bookMapper;
       private final BookRepository bookRepository;

       @Transactional
       public Long saveBook(BookRequest bookRequest, Authentication connectedUser) {
           User user = (User) connectedUser.getPrincipal();

           Book book = bookMapper.toBook(bookRequest);
           book.setOwner(user);

           return bookRepository.save(book).getId();
       }

       @Cacheable(value = "books", key = "#bookId")
       @Transactional(readOnly = true)
       public BookResponse getBookById(Long bookId) {
           return bookRepository
                   .findById(bookId)
                   .map(bookMapper::toBookResponse)
                   .orElseThrow(() -> new EntityNotFoundException("No Book found with id: " + bookId));
       }
   }
   ```

   **Notes:**

   - Ensure that caching is appropriately configured and cache invalidation strategies are in place.
   - Use appropriate cache keys to avoid cache collisions.

2. **Optimize Database Indexing**

   **Issue**: Without proper indexing, queries can become slow as the database grows.

   **Improvement**: Ensure that commonly searched fields are indexed.

   **Example:**

   ```java
   @Entity
   @Table(name = "book", indexes = {
           @Index(name = "idx_book_title", columnList = "title"),
           @Index(name = "idx_book_author", columnList = "author"),
           @Index(name = "idx_book_isbn", columnList = "isbn")
   })
   public class Book extends BaseEntity {
       // Existing fields...
   }
   ```

   **Notes:**

   - Indexes on `title`, `author`, and `isbn` can speed up search queries.
   - Be cautious with the number of indexes as they can slow down write operations.

3. **Implement Asynchronous Processing**

   **Issue**: While saving a single book is generally quick, under heavy load, it can lead to longer response times.

   **Improvement**: Utilize Spring's `@Async` to handle save operations asynchronously if necessary.

   **Example:**

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       private final BookMapper bookMapper;
       private final BookRepository bookRepository;

       @Async
       public CompletableFuture<Long> saveBookAsync(BookRequest bookRequest, Authentication connectedUser) {
           User user = (User) connectedUser.getPrincipal();

           Book book = bookMapper.toBook(bookRequest);
           book.setOwner(user);

           Book savedBook = bookRepository.save(book);
           return CompletableFuture.completedFuture(savedBook.getId());
       }

       @Cacheable(value = "books", key = "#bookId")
       @Transactional(readOnly = true)
       public BookResponse getBookById(Long bookId) {
           return bookRepository
                   .findById(bookId)
                   .map(bookMapper::toBookResponse)
                   .orElseThrow(() -> new EntityNotFoundException("No Book found with id: " + bookId));
       }
   }
   ```

   **Update Controller:**

   ```java
   @PostMapping
   public CompletableFuture<ResponseEntity<Long>> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
       return bookService.saveBookAsync(bookRequest, connectedUser)
               .thenApply(id -> ResponseEntity.status(HttpStatus.CREATED).body(id));
   }
   ```

   **Notes:**

   - Ensure that the application is configured with `@EnableAsync` (already present in `BookNetworkApplication`).
   - Handle asynchronous exceptions appropriately.

4. **Batch Operations**

   **Issue**: While not directly related to the current endpoint, considering batch operations can be beneficial for future enhancements.

   **Improvement**: Implement batch saving if the application allows bulk operations.

   **Example:**

   ```java
   @PostMapping("/batch")
   public ResponseEntity<List<Long>> saveBooks(@RequestBody @Valid List<BookRequest> bookRequests, Authentication connectedUser) {
       List<Long> bookIds = bookService.saveBooks(bookRequests, connectedUser);
       return ResponseEntity.status(HttpStatus.CREATED).body(bookIds);
   }
   ```

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       private final BookMapper bookMapper;
       private final BookRepository bookRepository;

       public List<Long> saveBooks(List<BookRequest> bookRequests, Authentication connectedUser) {
           User user = (User) connectedUser.getPrincipal();

           List<Book> books = bookRequests.stream()
                   .map(bookMapper::toBook)
                   .peek(book -> book.setOwner(user))
                   .collect(Collectors.toList());

           List<Book> savedBooks = bookRepository.saveAll(books);
           return savedBooks.stream().map(Book::getId).collect(Collectors.toList());
       }

       // Existing methods...
   }
   ```

   **Notes:**

   - Batch operations can improve performance by reducing the number of database transactions.
   - Ensure that proper validation is in place for batch requests.

### c. Security Enhancements

1. **Ensure Proper Authorization**

   **Issue**: Authenticated users should only access or modify their own resources or those they are authorized to.

   **Improvement**: Implement authorization checks to ensure users can only access books they own or are permitted to view.

   **Example:**

   ```java
   @RestController
   @RequestMapping("books")
   @RequiredArgsConstructor
   @Tag(name = "Book")
   public class BookController {

       private final BookService bookService;

       @PostMapping
       @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
       public ResponseEntity<Long> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
           return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRequest, connectedUser));
       }

       @GetMapping("{book-id}")
       @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
       public ResponseEntity<BookResponse> getBookById(@PathVariable("book-id") Long bookId, Authentication connectedUser) {
           return ResponseEntity.ok(bookService.getBookById(bookId, connectedUser));
       }
   }
   ```

   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       private final BookMapper bookMapper;
       private final BookRepository bookRepository;

       @Transactional
       public Long saveBook(BookRequest bookRequest, Authentication connectedUser) {
           User user = (User) connectedUser.getPrincipal();

           Book book = bookMapper.toBook(bookRequest);
           book.setOwner(user);

           return bookRepository.save(book).getId();
       }

       @Transactional(readOnly = true)
       public BookResponse getBookById(Long bookId, Authentication connectedUser) {
           User user = (User) connectedUser.getPrincipal();

           Book book = bookRepository.findById(bookId)
                   .orElseThrow(() -> new EntityNotFoundException("No Book found with id: " + bookId));

           // Authorization: Allow access if the user is the owner or has admin role
           if (!book.getOwner().equals(user) && !user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"))) {
               throw new AccessDeniedException("You do not have permission to access this book.");
           }

           return bookMapper.toBookResponse(book);
       }
   }
   ```

   **Notes:**

   - Use Spring Security's `@PreAuthorize` to enforce role-based access.
   - Implement additional authorization logic in the service layer to restrict access based on ownership or roles.
   - Throw appropriate exceptions (e.g., `AccessDeniedException`) when access is denied.

2. **Prevent Mass Assignment Vulnerabilities**

   **Issue**: Directly using entities in controllers can expose fields unintentionally, leading to mass assignment vulnerabilities.

   **Improvement**: Use Data Transfer Objects (DTOs) to control data flow between client and server.

   **Example:**

   As implemented, `BookRequest` and `BookResponse` act as DTOs, limiting the exposure of internal entity structures.

3. **Implement Input Sanitization**

   **Issue**: Inputs containing malicious scripts can lead to Cross-Site Scripting (XSS) attacks.

   **Improvement**: Sanitize inputs before processing or storing them.

   **Example:**

   ```java
   import org.jsoup.Jsoup;
   import org.jsoup.safety.Whitelist;

   public Book toBook(BookRequest bookRequest) {
       String safeTitle = Jsoup.clean(bookRequest.title(), Whitelist.basic());
       String safeAuthor = Jsoup.clean(bookRequest.author(), Whitelist.basic());
       String safeSynopsis = Jsoup.clean(bookRequest.synopsis(), Whitelist.basic());

       return Book.builder()
               .title(safeTitle)
               .author(safeAuthor)
               .isbn(bookRequest.isbn())
               .synopsis(safeSynopsis)
               .archived(false)
               .shareable(bookRequest.shareable())
               .build();
   }
   ```

   **Notes:**

   - Use libraries like **Jsoup** for sanitizing HTML inputs.
   - Ensure all user inputs are validated and sanitized before processing or displaying them.

4. **Implement Rate Limiting**

   **Issue**: APIs are susceptible to abuse through excessive requests, leading to Denial of Service (DoS) attacks.

   **Improvement**: Implement rate limiting to control the number of requests a user can make.

   **Example Using Bucket4j:**

   **Add Dependency:**

   ```xml
   <!-- In pom.xml -->
   <dependency>
       <groupId>com.github.vladimir-bukhtoyarov</groupId>
       <artifactId>bucket4j-core</artifactId>
       <version>7.6.0</version>
   </dependency>
   ```

   **Implement Rate Limiting Filter:**

   ```java
   package com.wchamara.book.security;

   import io.github.bucket4j.Bandwidth;
   import io.github.bucket4j.Bucket;
   import io.github.bucket4j.Refill;
   import org.springframework.http.HttpStatus;
   import org.springframework.stereotype.Component;
   import org.springframework.web.filter.OncePerRequestFilter;

   import javax.servlet.FilterChain;
   import javax.servlet.ServletException;
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   import java.io.IOException;
   import java.time.Duration;

   @Component
   public class RateLimitingFilter extends OncePerRequestFilter {

       private final Bucket bucket;

       public RateLimitingFilter() {
           Refill refill = Refill.greedy(100, Duration.ofMinutes(1));
           Bandwidth limit = Bandwidth.classic(100, refill);
           this.bucket = Bucket.builder()
                   .addLimit(limit)
                   .build();
       }

       @Override
       protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
               throws ServletException, IOException {
           if (bucket.tryConsume(1)) {
               filterChain.doFilter(request, response);
           } else {
               response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
               response.getWriter().write("Too many requests");
           }
       }
   }
   ```

   **Register Filter:**

   ```java
   @Configuration
   @EnableWebSecurity
   @EnableGlobalMethodSecurity(prePostEnabled = true)
   public class SecurityConfig extends WebSecurityConfigurerAdapter {

       private final RateLimitingFilter rateLimitingFilter;

       public SecurityConfig(RateLimitingFilter rateLimitingFilter) {
           this.rateLimitingFilter = rateLimitingFilter;
       }

       @Override
       protected void configure(HttpSecurity http) throws Exception {
           http
               .csrf().disable()
               .authorizeRequests()
                   .antMatchers("/api/public/**").permitAll()
                   .anyRequest().authenticated()
               .and()
                   .addFilterBefore(rateLimitingFilter, UsernamePasswordAuthenticationFilter.class)
               .httpBasic();
       }
   }
   ```

   **Notes:**

   - Adjust the rate limits (`100 requests per minute`) based on application requirements.
   - Customize error messages and responses as needed.
   - Consider using more advanced features of Bucket4j for dynamic rate limiting.

5. **Use HTTPS**

   **Issue**: Transmitting data over unsecured channels can lead to data interception and tampering.

   **Improvement**: Enforce HTTPS in production environments.

   **Example:**

   **Configure HTTPS in `application.yml`:**

   ```yaml
   server:
     port: 8443
     ssl:
       key-store: classpath:keystore.p12
       key-store-password: changeit
       key-store-type: PKCS12
       key-alias: tomcat
   ```

   **Notes:**

   - Obtain a valid SSL certificate from a trusted Certificate Authority (CA).
   - Redirect HTTP traffic to HTTPS to enforce secure connections.

6. **Implement Content Security Policy (CSP)**

   **Issue**: Without CSP, the application is vulnerable to Cross-Site Scripting (XSS) attacks.

   **Improvement**: Define a strict Content Security Policy in HTTP headers.

   **Example:**

   ```java
   @Configuration
   @EnableWebSecurity
   public class SecurityConfig extends WebSecurityConfigurerAdapter {

       @Override
       protected void configure(HttpSecurity http) throws Exception {
           http
               .headers()
                   .contentSecurityPolicy("default-src 'self'; script-src 'self'; object-src 'none';")
               .and()
                   .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
               .and()
                   .authorizeRequests()
                       .antMatchers("/api/public/**").permitAll()
                       .anyRequest().authenticated()
               .and()
                   .formLogin()
               .and()
                   .httpBasic();
       }
   }
   ```

   **Notes:**

   - Adjust CSP directives based on application needs.
   - Use tools like [CSP Evaluator](https://csp-evaluator.withgoogle.com/) to test and refine your policy.

# 📗Find All Books

```java
package com.wchamara.book.book;

import com.wchamara.book.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Integer> saveBook(@RequestBody @Valid BookRequest bookRequest, Authentication connectedUser) {
        return ResponseEntity.ok(bookService.saveBook(bookRequest, connectedUser));
    }

    @GetMapping("{book-id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("book-id") Integer bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findAllBooks(page, size, connectedUser));
    }

}

```

```java
package com.wchamara.book.common;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {

    private List<T> content;

    private int number;

    private int size;

    private long totalElements;

    private int totalPages;

    private boolean last;

    private boolean first;
}

```

```java
package com.wchamara.book.book;

import com.wchamara.book.common.PageResponse;
import com.wchamara.book.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    private final BookRepository bookRepository;

    public Integer saveBook(BookRequest bookRequest, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();

        Book book = BookMapper.toBook(bookRequest);
        book.setOwner(user);

        return bookRepository.save(book).getId();
    }

    public BookResponse getBookById(Integer bookId) {

        return bookRepository
                .findById(bookId)
                .map(bookMapper::toBookResponse)
                .orElseThrow(() -> new EntityNotFoundException("No Book found with id: " + bookId));
    }

    public PageResponse<BookResponse> findAllBooks(Integer page, Integer size, Authentication connectedUser) {

        User user = (User) connectedUser.getPrincipal();

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());

        Page<Book> books = bookRepository.findAllDisplayableBooks(pageable, user.getId());

        List<BookResponse> bookResponses = books.stream().map(bookMapper::toBookResponse).toList();
        return new PageResponse<>(
                bookResponses,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isLast(),
                books.isFirst());
    }
}

```

```java
package com.wchamara.book.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer> {


    @Query("""
            SELECT b FROM Book b
             WHERE b.owner.id = :id
             and b.archived = false and b.archived = false
            """)
    Page<Book> findAllDisplayableBooks(Pageable pageable, Integer id);
}

```

Certainly! Let's delve deeply into the provided Java Spring Boot code, focusing primarily on the `findAllBooks` method. We'll explore its current implementation, identify areas for improvement in code quality, performance, and security, and suggest best practices to enhance the overall robustness and maintainability of the application.

---

## **1. Overview of the Provided Code**

### **a. `BookController`**

- **Endpoints:**
  - `POST /books`: Saves a new book.
  - `GET /books/{book-id}`: Retrieves a book by its ID.
  - `GET /books`: Retrieves all books with pagination.

### **b. `PageResponse<T>`**

- A generic class to encapsulate paginated responses, including content, pagination details, and metadata.

### **c. `BookService`**

- **Methods:**
  - `saveBook`: Saves a new book associated with the authenticated user.
  - `getBookById`: Retrieves a book by its ID.
  - `findAllBooks`: Retrieves all books for the authenticated user with pagination.

### **d. `BookRepository`**

- Extends `JpaRepository` with a custom query `findAllDisplayableBooks` to fetch books that are not archived and belong to a specific user.

---

## **2. Deep Dive into `findAllBooks` Method**

### **Current Implementation**

```java
public PageResponse<BookResponse> findAllBooks(Integer page, Integer size, Authentication connectedUser) {

    User user = (User) connectedUser.getPrincipal();

    Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());

    Page<Book> books = bookRepository.findAllDisplayableBooks(pageable, user.getId());

    List<BookResponse> bookResponses = books.stream().map(bookMapper::toBookResponse).toList();
    return new PageResponse<>(
            bookResponses,
            books.getNumber(),
            books.getSize(),
            books.getTotalElements(),
            books.getTotalPages(),
            books.isLast(),
            books.isFirst());
}
```

### **Functionality**

1. **Authentication Handling:**
   - Retrieves the authenticated `User` from the `Authentication` object.

2. **Pagination Setup:**
   - Creates a `Pageable` object with the requested page number, size, and sorting order (by `createdDate` descending).

3. **Data Retrieval:**
   - Calls `bookRepository.findAllDisplayableBooks` to fetch books belonging to the user that are not archived.

4. **Response Mapping:**
   - Maps the retrieved `Book` entities to `BookResponse` DTOs.

5. **Response Construction:**
   - Wraps the mapped DTOs and pagination metadata into a `PageResponse` object.

---

## **3. Identified Issues and Suggested Improvements**

### **a. Code Quality Improvements**

1. **Redundant Query Condition in `BookRepository`:**

   **Issue:**
   ```java
   WHERE b.owner.id = :id
   and b.archived = false and b.archived = false
   ```
   - The condition `b.archived = false` is duplicated.

   **Improvement:**
   - Remove the redundant condition to prevent confusion and potential maintenance issues.

   **Revised Query:**
   ```java
   @Query("""
           SELECT b FROM Book b
            WHERE b.owner.id = :id
            AND b.archived = false
           """)
   Page<Book> findAllDisplayableBooks(Pageable pageable, Integer id);
   ```

2. **Unsafe Casting of `Authentication.getPrincipal()`:**

   **Issue:**
   ```java
   User user = (User) connectedUser.getPrincipal();
   ```
   - Directly casting assumes that the principal is always an instance of `User`. If, for some reason, it's not (e.g., due to changes in authentication mechanism), this can lead to a `ClassCastException`.

   **Improvement:**
   - Use safer casting with `instanceof` checks or leverage Spring Security's `@AuthenticationPrincipal` annotation.

   **Example Using `instanceof`:**
   ```java
   if (!(connectedUser.getPrincipal() instanceof User user)) {
       throw new UnauthorizedException("Invalid user authentication.");
   }
   ```

   **Alternative Using `@AuthenticationPrincipal`:**
   - Modify the controller method to accept the authenticated user directly.

   ```java
   public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
           @RequestParam(value = "page", defaultValue = "0") Integer page,
           @RequestParam(value = "size", defaultValue = "10") Integer size,
           @AuthenticationPrincipal User user
   ) {
       return ResponseEntity.ok(bookService.findAllBooks(page, size, user));
   }
   ```

   - Adjust the service method accordingly:
   ```java
   public PageResponse<BookResponse> findAllBooks(Integer page, Integer size, User user) {
       // ... existing logic ...
   }
   ```

3. **Hardcoding Sorting Field:**

   **Issue:**
   ```java
   Sort.by("createdDate").descending()
   ```
   - Hardcoding field names can lead to errors if the field name changes in the `Book` entity.

   **Improvement:**
   - Use constants or enums to define sortable fields, enhancing maintainability and reducing typos.

   **Example Using Constants:**
   ```java
   public class BookConstants {
       public static final String CREATED_DATE = "createdDate";
       // Add other sortable fields as needed
   }
   ```

   - Use in `Pageable`:
   ```java
   Pageable pageable = PageRequest.of(page, size, Sort.by(BookConstants.CREATED_DATE).descending());
   ```

4. **Handling Null `Authentication`:**

   **Issue:**
   - The method assumes that `connectedUser` is always non-null. If, due to misconfiguration or other issues, it's null, it will result in a `NullPointerException`.

   **Improvement:**
   - Add null checks and handle scenarios where authentication might be absent.

   **Example:**
   ```java
   if (connectedUser == null || !connectedUser.isAuthenticated()) {
       throw new UnauthorizedException("User is not authenticated.");
   }
   ```

5. **Use of Magic Numbers:**

   **Issue:**
   - Default page number and size are hardcoded as `"0"` and `"10"` respectively.

   **Improvement:**
   - Define constants for default pagination parameters to enhance readability and maintainability.

   **Example:**
   ```java
   public class PaginationConstants {
       public static final int DEFAULT_PAGE_NUMBER = 0;
       public static final int DEFAULT_PAGE_SIZE = 10;
   }
   ```

   - Use in controller:
   ```java
   @RequestParam(value = "page", defaultValue = String.valueOf(PaginationConstants.DEFAULT_PAGE_NUMBER)) Integer page,
   @RequestParam(value = "size", defaultValue = String.valueOf(PaginationConstants.DEFAULT_PAGE_SIZE)) Integer size,
   ```

### **b. Performance Improvements**

1. **Optimizing the Query for Fetching Necessary Fields:**

   **Issue:**
   - The current JPQL query selects entire `Book` entities. If only specific fields are needed for the response, fetching the entire entity can be inefficient.

   **Improvement:**
   - Use **DTO Projections** in the repository to fetch only required fields, reducing memory footprint and improving performance.

   **Example:**
   - Define a `BookSummary` DTO:
     ```java
     public class BookSummary {
         private Integer id;
         private String title;
         private String author;
         private LocalDate createdDate;
         // Constructors, getters, setters
     }
     ```

   - Modify the repository method:
     ```java
     @Query("""
             SELECT new com.wchamara.book.book.BookSummary(b.id, b.title, b.author, b.createdDate)
             FROM Book b
             WHERE b.owner.id = :id
             AND b.archived = false
             """)
     Page<BookSummary> findAllDisplayableBookSummaries(Pageable pageable, Integer id);
     ```

   - Adjust the service method to map `BookSummary` to `BookResponse`.

2. **Leveraging Caching:**

   **Issue:**
   - Frequently accessed data can lead to repeated database hits, impacting performance.

   **Improvement:**
   - Implement caching mechanisms (e.g., Spring Cache) for read-heavy endpoints like `findAllBooks`.

   **Example:**
   - Enable caching in the service:
     ```java
     @Service
     @RequiredArgsConstructor
     public class BookService {

         // ... existing fields ...

         @Cacheable(value = "books", key = "#user.id + '-' + #page + '-' + #size")
         public PageResponse<BookResponse> findAllBooks(Integer page, Integer size, User user) {
             // ... existing logic ...
         }
     }
     ```

   - Configure a cache manager (e.g., using EhCache, Redis).

3. **Asynchronous Processing:**

   **Issue:**
   - For large datasets, fetching and processing data synchronously can lead to increased response times.

   **Improvement:**
   - Implement asynchronous processing or streaming for handling large volumes of data.

   **Example:**
   - Use Spring WebFlux for reactive programming, allowing non-blocking data retrieval and processing.

### **c. Security Improvements**

1. **Ensuring Proper Authorization:**

   **Issue:**
   - The current implementation assumes that any authenticated user can access their books. However, there might be role-based access controls or ownership validations needed.

   **Improvement:**
   - Implement **method-level security** using annotations like `@PreAuthorize` to enforce access controls.

   **Example:**
   - Annotate the controller method:
     ```java
     @GetMapping
     @PreAuthorize("hasRole('USER')")
     public ResponseEntity<PageResponse<BookResponse>> findAllBooks(...) { ... }
     ```

   - Alternatively, enforce ownership checks within the service layer to prevent unauthorized data access.

2. **Input Validation:**

   **Issue:**
   - The `page` and `size` parameters are integers with default values, but there’s no validation to ensure they are within acceptable ranges.

   **Improvement:**
   - Validate input parameters to prevent misuse or potential Denial of Service (DoS) attacks.

   **Example:**
   - Use validation annotations:
     ```java
     @GetMapping
     public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
             @RequestParam(value = "page", defaultValue = "0") @Min(0) Integer page,
             @RequestParam(value = "size", defaultValue = "10") @Min(1) @Max(100) Integer size,
             Authentication connectedUser
     ) { ... }
     ```

   - Handle validation errors gracefully by customizing exception handlers.

3. **Preventing Information Leakage:**

   **Issue:**
   - Error messages like `No Book found with id: ...` can inadvertently leak information about the existence of resources.

   **Improvement:**
   - Provide generic error messages or implement rate-limiting to prevent attackers from enumerating resources.

   **Example:**
   - Customize exception messages:
     ```java
     throw new EntityNotFoundException("Requested resource not found.");
     ```

4. **Securing Data Transmission:**

   **Issue:**
   - Although not directly shown in the code, ensuring that data is transmitted securely is crucial.

   **Improvement:**
   - Enforce HTTPS for all endpoints to protect data in transit.

   **Example:**
   - Configure Spring Security to require HTTPS:
     ```java
     @Configuration
     public class SecurityConfig extends WebSecurityConfigurerAdapter {
         @Override
         protected void configure(HttpSecurity http) throws Exception {
             http
                 .requiresChannel()
                 .anyRequest()
                 .requiresSecure();
             // ... other security configurations ...
         }
     }
     ```

### **d. Best Practices**

1. **Use of DTOs for Responses:**

   **Current Implementation:**
   - Utilizes `BookResponse` DTOs to decouple the internal `Book` entity from API responses.

   **Best Practice:**
   - Continue using DTOs to ensure that internal representations are not exposed, allowing for flexibility in API evolution.

2. **Exception Handling:**

   **Issue:**
   - The service throws `EntityNotFoundException`, which, if unhandled, can result in generic error responses.

   **Improvement:**
   - Implement a global exception handler using `@ControllerAdvice` to provide consistent and meaningful error responses.

   **Example:**
   ```java
   @ControllerAdvice
   public class GlobalExceptionHandler {

       @ExceptionHandler(EntityNotFoundException.class)
       public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
           ErrorResponse error = new ErrorResponse("NOT_FOUND", ex.getMessage());
           return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
       }

       // Handle other exceptions...
   }
   ```

3. **Logging:**

   **Issue:**
   - The current code lacks logging, which is essential for monitoring and debugging.

   **Improvement:**
   - Integrate logging using frameworks like SLF4J with Logback or Log4j2.

   **Example:**
   ```java
   @Service
   @RequiredArgsConstructor
   public class BookService {

       private static final Logger logger = LoggerFactory.getLogger(BookService.class);

       // ... existing fields ...

       public PageResponse<BookResponse> findAllBooks(Integer page, Integer size, User user) {
           logger.info("Fetching books for user ID: {}, page: {}, size: {}", user.getId(), page, size);
           // ... existing logic ...
       }
   }
   ```

4. **API Documentation:**

   **Current Implementation:**
   - Uses `@Tag` from Swagger for API documentation.

   **Best Practice:**
   - Ensure comprehensive API documentation with clear descriptions, parameter explanations, and response schemas using Swagger/OpenAPI annotations.

   **Example:**
   ```java
   @GetMapping
   @Operation(summary = "Retrieve all books for the authenticated user with pagination.")
   public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
           @Parameter(description = "Page number (0-based)", example = "0")
           @RequestParam(value = "page", defaultValue = "0") Integer page,
           @Parameter(description = "Number of records per page", example = "10")
           @RequestParam(value = "size", defaultValue = "10") Integer size,
           Authentication connectedUser
   ) { ... }
   ```

5. **Consistent Naming Conventions:**

   **Issue:**
   - The repository method `findAllDisplayableBooks` is descriptive but could be more intuitive.

   **Improvement:**
   - Follow Spring Data JPA naming conventions to make method purposes clearer.

   **Example:**
   ```java
   Page<Book> findByOwnerIdAndArchivedFalse(Pageable pageable, Integer ownerId);
   ```

   - This eliminates the need for a custom `@Query` unless necessary.

---

## **4. Enhanced Implementation Example**

Incorporating the above improvements, here's how the `findAllBooks` method and related components can be refactored:

### **a. Updated `BookRepository`**

```java
package com.wchamara.book.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    // Leveraging Spring Data JPA method naming conventions
    Page<Book> findByOwnerIdAndArchivedFalse(Integer ownerId, Pageable pageable);
}
```

### **b. Updated `BookService`**

```java
package com.wchamara.book.book;

import com.wchamara.book.common.PageResponse;
import com.wchamara.book.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Cacheable(value = "books", key = "#user.id + '-' + #page + '-' + #size")
    public PageResponse<BookResponse> findAllBooks(Integer page, Integer size, User user) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(BookConstants.CREATED_DATE).descending());

        Page<Book> books = bookRepository.findByOwnerIdAndArchivedFalse(user.getId(), pageable);

        List<BookResponse> bookResponses = books.stream()
                .map(bookMapper::toBookResponse)
                .toList();

        return PageResponse.<BookResponse>builder()
                .content(bookResponses)
                .number(books.getNumber())
                .size(books.getSize())
                .totalElements(books.getTotalElements())
                .totalPages(books.getTotalPages())
                .last(books.isLast())
                .first(books.isFirst())
                .build();
    }

    // ... other methods ...
}
```

### **c. Updated `BookController`**

```java
package com.wchamara.book.book;

import com.wchamara.book.common.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Retrieve all books for the authenticated user with pagination.")
    public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
            @Parameter(description = "Page number (0-based)", example = "0")
            @RequestParam(value = "page", defaultValue = "0") @Min(0) Integer page,

            @Parameter(description = "Number of records per page", example = "10")
            @RequestParam(value = "size", defaultValue = "10") @Min(1) @Max(100) Integer size,

            @AuthenticationPrincipal User user
    ) {
        PageResponse<BookResponse> response = bookService.findAllBooks(page, size, user);
        return ResponseEntity.ok(response);
    }

    // ... other endpoints ...
}
```

### **d. Added `BookConstants`**

```java
package com.wchamara.book.common;

public class BookConstants {
    public static final String CREATED_DATE = "createdDate";
    // Add other constants as needed
}
```

### **e. Enhanced Exception Handling**

```java
package com.wchamara.book.exception;

import com.wchamara.book.common.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .code("NOT_FOUND")
                .message("Requested resource not found.")
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .code("UNAUTHORIZED")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    // Handle other exceptions...

}
```

### **f. Defined `ErrorResponse` DTO**

```java
package com.wchamara.book.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String code;
    private String message;
}
```

---

## **5. Additional Recommendations**

### **a. Implementing Pagination Metadata**

While the `PageResponse` class provides essential pagination details, consider aligning it with standard pagination structures like those provided by Spring's `Page` interface. Alternatively, leveraging libraries like [Spring HATEOAS](https://spring.io/projects/spring-hateoas) can enrich responses with navigational links.

### **b. Utilizing MapStruct for DTO Mapping**

Instead of manual mapping with `bookMapper::toBookResponse`, using [MapStruct](https://mapstruct.org/) can automate and optimize the mapping process, reducing boilerplate code.

**Example:**

```java
@Mapper(componentModel = "spring")
public interface BookMapper {

    BookResponse toBookResponse(Book book);

    // Define other mapping methods as needed
}
```

### **c. Testing**

Ensure comprehensive unit and integration tests for the `findAllBooks` method to validate its behavior under various scenarios, including edge cases.

**Example Using JUnit and Mockito:**

```java
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    void testFindAllBooks_Success() {
        User user = new User();
        user.setId(1);

        Pageable pageable = PageRequest.of(0, 10, Sort.by(BookConstants.CREATED_DATE).descending());

        Book book = new Book();
        book.setId(1);
        book.setTitle("Test Book");
        book.setArchived(false);

        Page<Book> bookPage = new PageImpl<>(List.of(book), pageable, 1);

        when(bookRepository.findByOwnerIdAndArchivedFalse(user.getId(), pageable)).thenReturn(bookPage);
        when(bookMapper.toBookResponse(book)).thenReturn(new BookResponse(/* ... */));

        PageResponse<BookResponse> response = bookService.findAllBooks(0, 10, user);

        assertEquals(1, response.getTotalElements());
        // Additional assertions...
    }

    // Additional tests...
}
```

### **d. Monitoring and Metrics**

Integrate monitoring tools (e.g., Spring Boot Actuator, Micrometer) to track the performance and usage of the `findAllBooks` endpoint, enabling proactive performance tuning and issue detection.

---

## **6. Conclusion**

The `findAllBooks` method, while functional, presents several areas where enhancements can be made to improve code quality, performance, and security. By addressing redundant code, ensuring safe casting, optimizing queries, implementing caching, enforcing robust security measures, and adhering to best practices, the application can achieve higher efficiency, maintainability, and resilience.

Implementing these recommendations will not only refine the `findAllBooks` method but also contribute to the overall robustness and scalability of the application.

The `findAllBooks` method in your Spring-based project is designed to return paginated book data for a specific user, taking into account parameters such as page number and size. It queries the database for non-archived books belonging to the authenticated user and maps the result to a paginated response. While the implementation is quite clear, there are several areas where you can improve its functionality, performance, and security.

Let’s break it down step-by-step, highlight best practices, and suggest improvements.

### Current Flow

1. **Authentication and Authorization:**
   - The method relies on `Authentication` to identify the connected user. It retrieves the user from `Authentication.getPrincipal()`.
   - The query in `BookRepository` fetches books based on the user ID and whether the book is archived or not.

2. **Pagination:**
   - Pagination is handled using Spring Data’s `PageRequest` and the `Page` result is mapped into a custom `PageResponse` object.

3. **Querying the Database:**
   - A custom query fetches all books that are non-archived for a specific user.
   
### Key Areas for Improvements

1. **Redundant `archived = false` Clause in Query**
   - In your query, the condition `b.archived = false and b.archived = false` appears to be redundant.
   - Fix this by using the condition just once: `b.archived = false`.

   **Improvement:**
   ```java
   @Query("""
           SELECT b FROM Book b
           WHERE b.owner.id = :id
           and b.archived = false
           """)
   Page<Book> findAllDisplayableBooks(Pageable pageable, Integer id);
   ```

2. **Handling Null or Empty User Principal**
   - While you are directly casting `connectedUser.getPrincipal()` to `User`, this can fail if the principal object is not an instance of `User`. To make this code safer, perform proper type-checking.
   - You may want to add a check for null or unexpected types and throw a custom exception or handle this more gracefully.

   **Improvement:**
   ```java
   User user;
   if (connectedUser != null && connectedUser.getPrincipal() instanceof User) {
       user = (User) connectedUser.getPrincipal();
   } else {
       throw new UnauthorizedAccessException("User is not authenticated");
   }
   ```

3. **Potential Security Issue:**
   - It’s essential to check the authorization context properly. Although `connectedUser.getPrincipal()` gives you the current user, additional validation may be required, particularly for multi-tenancy scenarios or situations where different users share roles. Ensure proper role-based checks if applicable.
   
   **Improvement:**
   - You can integrate a proper security mechanism using annotations like `@PreAuthorize("hasRole('ROLE_USER')")` or access control logic in service methods.

4. **Use of DTOs:**
   - While you are already mapping your `Book` entities to a `BookResponse` DTO, ensure that no sensitive fields are exposed in the DTO. Be careful not to return internal fields like IDs that shouldn't be exposed to the front-end.

   **Improvement:**
   ```java
   public class BookResponse {
       private String title;
       private String author;
       private Date publishedDate;
       // Avoid including sensitive information such as internal IDs
   }
   ```

5. **Optimizing the `findAllBooks` Method:**
   - **N+1 Query Problem:** Ensure that lazy-loaded relationships (such as the owner or any related entities) aren’t causing an N+1 query problem. You can use `@EntityGraph` to eagerly fetch necessary relationships in a single query.
   - **Pagination Efficiency:** The pagination mechanism is already efficient with the use of `PageRequest`. However, you can further improve by limiting the fields fetched in the query (if the database query is slow).
   - If the `Book` entity contains large objects (e.g., file data, descriptions), make sure you're not over-fetching unnecessary data. Use JPQL projections to return only the required fields for listing purposes.

   **Improvement:**
   ```java
   @Query("""
           SELECT new com.wchamara.book.book.BookResponse(b.title, b.author, b.publishedDate)
           FROM Book b
           WHERE b.owner.id = :id
           and b.archived = false
           """)
   Page<BookResponse> findAllDisplayableBooks(Pageable pageable, Integer id);
   ```

6. **Error Handling and Exception Management:**
   - In the current method, the `findAllBooks` doesn’t handle scenarios where the user doesn't have any books or where there is an error in the database query.
   - Consider adding error handling and user feedback mechanisms, such as throwing custom exceptions that can be caught and translated into meaningful HTTP responses.

   **Improvement:**
   ```java
   public PageResponse<BookResponse> findAllBooks(Integer page, Integer size, Authentication connectedUser) {
       User user;
       if (connectedUser != null && connectedUser.getPrincipal() instanceof User) {
           user = (User) connectedUser.getPrincipal();
       } else {
           throw new UnauthorizedAccessException("User is not authenticated");
       }

       Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
       Page<Book> books;

       try {
           books = bookRepository.findAllDisplayableBooks(pageable, user.getId());
       } catch (Exception e) {
           throw new DatabaseQueryException("Failed to query books from the database", e);
       }

       if (books.isEmpty()) {
           throw new ResourceNotFoundException("No books found for the user.");
       }

       List<BookResponse> bookResponses = books.stream().map(bookMapper::toBookResponse).toList();
       return new PageResponse<>(
               bookResponses,
               books.getNumber(),
               books.getSize(),
               books.getTotalElements(),
               books.getTotalPages(),
               books.isLast(),
               books.isFirst());
   }
   ```

7. **Validation of Pagination Inputs:**
   - Validate the `page` and `size` parameters to ensure they fall within an acceptable range. Returning an empty page or invalid page size could lead to performance issues.
   - Consider setting minimum and maximum boundaries for `size`.

   **Improvement:**
   ```java
   if (size > 100) {
       throw new IllegalArgumentException("Page size too large, must be 100 or less.");
   }
   if (page < 0) {
       throw new IllegalArgumentException("Page number cannot be negative.");
   }
   ```

8. **Caching:**
   - If the book data is not frequently updated, consider caching the results of `findAllBooks` to reduce load on the database for repeated requests. Spring provides several caching mechanisms that can be easily integrated.

   **Improvement:**
   ```java
   @Cacheable("books")
   public PageResponse<BookResponse> findAllBooks(Integer page, Integer size, Authentication connectedUser) {
       // Method logic
   }
   ```

### Summary of Improvements:
1. **Fix redundant query clauses** (`archived = false` twice).
2. **Type-safe user principal access** with null/role checks.
3. **Security enhancements**: Add role checks and validation.
4. **Use DTO projections** in the query to avoid fetching unnecessary data.
5. **Graceful error handling** for database access and no-results scenarios.
6. **Input validation** for pagination parameters.
7. **Caching** for better performance if books aren't frequently updated.

By implementing these improvements, you enhance both the **performance** and **security** of your application while improving the overall **code quality**.

# 📗Get Book By Owner

```java
package com.wchamara.book.book;

import com.wchamara.book.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    
    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BookResponse>> getAllBooksByOwner(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findAllBooksByOwner(page, size, connectedUser));
    }

}
```

```java
package com.wchamara.book.book;

import com.wchamara.book.common.PageResponse;
import com.wchamara.book.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

private final BookMapper bookMapper;
private final BookRepository bookRepository;


 public PageResponse<BookResponse> findAllBooksByOwner(Integer page, Integer size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Book> books = bookRepository.findAll(BookSpecification.withOwnerId(user.getId()), pageable);

        List<BookResponse> bookResponses = books.stream().map(bookMapper::toBookResponse).toList();
        return new PageResponse<>(
                bookResponses,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isLast(),
                books.isFirst());
    }

}
```

```java
package com.wchamara.book.book;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> withOwnerId(Integer ownerId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner").get("id"), ownerId));
    }
}

```

```java
package com.wchamara.book.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {}

```
To provide a deep explanation of the code changes in your Spring Boot application for managing books, I'll walk through each section of your code, explaining the existing functionality, proposed changes, and improvements with examples.

### **1. BookController.java**

```java
package com.wchamara.book.book;

import com.wchamara.book.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BookResponse>> getAllBooksByOwner(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bookService.findAllBooksByOwner(page, size, connectedUser));
    }
}
```

#### **Explanation:**
1. **Controller Class:**
   - The `BookController` class is responsible for handling HTTP requests related to books.
   - It is annotated with `@RestController`, which marks it as a controller that returns data in response to web requests (commonly JSON).
   - `@RequestMapping("books")` defines the base URL path for all endpoints in this controller.
   - The `@Tag(name = "Book")` annotation is used to categorize this controller under the "Book" tag in your API documentation (for Swagger or OpenAPI).

2. **Method - getAllBooksByOwner:**
   - This method handles the `GET /books/owner` request, which retrieves all books owned by the authenticated user.
   - The method accepts two query parameters, `page` (with a default value of `0`) and `size` (with a default value of `10`), which allow pagination.
   - The `Authentication connectedUser` parameter automatically injects the current authenticated user.
   - `ResponseEntity.ok()` wraps the result and returns a successful HTTP 200 response with the result body.

#### **Proposed Change and Explanation:**

In this method, consider adding more detailed validation for the pagination parameters, such as ensuring `page` is non-negative and `size` is within a reasonable range. Here’s an example:

```java
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@GetMapping("/owner")
public ResponseEntity<PageResponse<BookResponse>> getAllBooksByOwner(
        @RequestParam(value = "page", defaultValue = "0") @Min(0) Integer page,
        @RequestParam(value = "size", defaultValue = "10") @Min(1) @Max(100) Integer size,
        Authentication connectedUser
) {
    return ResponseEntity.ok(bookService.findAllBooksByOwner(page, size, connectedUser));
}
```

- The `@Min` and `@Max` annotations ensure that the `page` and `size` parameters are within a valid range, avoiding potential performance and security issues like large requests causing server strain.

---

### **2. BookService.java**

```java
package com.wchamara.book.book;

import com.wchamara.book.common.PageResponse;
import com.wchamara.book.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public PageResponse<BookResponse> findAllBooksByOwner(Integer page, Integer size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Book> books = bookRepository.findAll(BookSpecification.withOwnerId(user.getId()), pageable);

        List<BookResponse> bookResponses = books.stream()
                .map(bookMapper::toBookResponse)
                .toList();
        return new PageResponse<>(
                bookResponses,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isLast(),
                books.isFirst()
        );
    }
}
```

#### **Explanation:**
1. **Service Class:**
   - The `BookService` class contains business logic related to books.
   - It is annotated with `@Service`, indicating that it's a service layer component in the Spring framework.

2. **Method - findAllBooksByOwner:**
   - This method retrieves a paginated list of books owned by the authenticated user.
   - It takes in pagination parameters (`page` and `size`) and the `Authentication` object representing the current user.
   - It extracts the `User` object from the `Authentication` object and constructs a `Pageable` object with sorting applied (`Sort.by("createdDate").descending()`).
   - It uses the `BookRepository` to query for books that belong to the user, using the `BookSpecification.withOwnerId(user.getId())` method to filter the books.
   - The results are converted into `BookResponse` objects using `bookMapper` and returned as a `PageResponse`.

#### **Proposed Change and Explanation:**

Consider adding caching for frequently accessed data to reduce database load and improve performance. For example, using Spring Cache:

```java
import org.springframework.cache.annotation.Cacheable;

@Cacheable(value = "booksByOwner", key = "#connectedUser.getName() + '-' + #page + '-' + #size")
public PageResponse<BookResponse> findAllBooksByOwner(Integer page, Integer size, Authentication connectedUser) {
    User user = (User) connectedUser.getPrincipal();
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
    Page<Book> books = bookRepository.findAll(BookSpecification.withOwnerId(user.getId()), pageable);

    List<BookResponse> bookResponses = books.stream().map(bookMapper::toBookResponse).toList();
    return new PageResponse<>(
            bookResponses,
            books.getNumber(),
            books.getSize(),
            books.getTotalElements(),
            books.getTotalPages(),
            books.isLast(),
            books.isFirst()
    );
}
```

- **Caching**: By using `@Cacheable`, we can cache the result of `findAllBooksByOwner` to reduce database calls when the same request is made repeatedly.

#### **Additional Improvements:**
- **Logging**: Add logging for debugging and tracing purposes.

```java
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookService {
    public PageResponse<BookResponse> findAllBooksByOwner(Integer page, Integer size, Authentication connectedUser) {
        log.debug("Fetching books for user: {}, page: {}, size: {}", connectedUser.getPrincipal(), page, size);
        // Other logic
    }
}
```

---

### **3. BookSpecification.java**

```java
package com.wchamara.book.book;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> withOwnerId(Integer ownerId) {
        return ((root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("owner").get("id"), ownerId));
    }
}
```

#### **Explanation:**
1. **Specification Class:**
   - The `BookSpecification` class provides a dynamic query for filtering books based on the owner's ID.
   - It implements the `Specification<Book>` interface, allowing the query to be built dynamically based on the owner’s ID.

2. **Method - withOwnerId:**
   - This method returns a specification that filters books where the owner's ID matches the given `ownerId`.

#### **Proposed Change and Explanation:**

If you have relationships that could potentially cause the N+1 problem (where each entity loads its relationships in a separate query), consider eager fetching relationships like the owner in the specification.

**Example:**

```java
public static Specification<Book> withOwnerId(Integer ownerId) {
    return (root, query, criteriaBuilder) -> {
        root.fetch("owner", JoinType.INNER); // Fetch owner eagerly
        return criteriaBuilder.equal(root.get("owner").get("id"), ownerId);
    };
}
```

- **Fetch Optimization**: This modification ensures that when books are retrieved, their owners are eagerly fetched in a single query rather than triggering multiple separate queries for each book's owner.

---

### **4. BookRepository.java**

```java
package com.wchamara.book.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {}
```

#### **Explanation:**
1. **Repository Interface:**
   - The `BookRepository` interface extends `JpaRepository` to provide basic CRUD functionality.
   - It also extends `JpaSpecificationExecutor`, allowing the use of specifications (like `withOwnerId`) to create dynamic queries.

#### **No Changes Suggested:**
- The current implementation is appropriate and follows best practices by using the Spring Data JPA repository pattern.

---

## **Overall Improvements and Best Practices**

### **1. Validation of Parameters**
Ensure that all inputs are validated, especially for pagination parameters. This prevents potential security risks, such as excessively large or negative values.

### **2. Exception Handling**
Implement global exception handling using `@ControllerAdvice` to handle common exceptions, such as `EntityNotFoundException

`, and provide meaningful error responses to the client.

### **3. Security Enhancements**
Ensure that only authenticated users can access the `/owner` endpoint by adding proper security annotations, such as `@PreAuthorize`.

```java
@PreAuthorize("hasRole('ROLE_USER')")
@GetMapping("/owner")
public ResponseEntity<PageResponse<BookResponse>> getAllBooksByOwner() {
    // method body
}
```

### **4. Performance Enhancements**
Add caching using Spring Cache for frequently accessed data and use eager fetching in specifications to avoid the N+1 select problem.

### **5. Documentation**
Improve the API documentation using Swagger annotations (`@Operation`, `@ApiResponse`) to provide better documentation for API consumers.

---

By incorporating these changes and improvements, your code will be more robust, performant, and secure. It will also follow best practices, making it easier to maintain and extend in the future.