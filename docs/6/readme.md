# 📗Repository Layer for Role, Token and User Entities

- [📗Repository Layer for Role, Token and User Entities](#repository-layer-for-role-token-and-user-entities)
  - [Table of Contents](#table-of-contents)
  - [1. Understanding Spring Data JPA Repositories](#1-understanding-spring-data-jpa-repositories)
    - [What is Spring Data JPA?](#what-is-spring-data-jpa)
    - [What is `JpaRepository`?](#what-is-jparepository)
    - [Benefits of Using Spring Data JPA Repositories](#benefits-of-using-spring-data-jpa-repositories)
  - [2. RoleRepository](#2-rolerepository)
    - [Code Overview](#code-overview)
    - [Detailed Explanation](#detailed-explanation)
    - [Example Usage](#example-usage)
      - [Role Service Example](#role-service-example)
      - [Usage in a Controller](#usage-in-a-controller)
      - [Explanation of Example Usage](#explanation-of-example-usage)
    - [Practical Example](#practical-example)
  - [3. TokenRepository](#3-tokenrepository)
    - [Code Overview](#code-overview-1)
    - [Detailed Explanation](#detailed-explanation-1)
    - [Example Usage](#example-usage-1)
      - [Token Service Example](#token-service-example)
      - [Usage in a Registration Flow](#usage-in-a-registration-flow)
      - [Explanation of Example Usage](#explanation-of-example-usage-1)
  - [4. UserRepository](#4-userrepository)
    - [Code Overview](#code-overview-2)
    - [Detailed Explanation](#detailed-explanation-2)
    - [Example Usage](#example-usage-2)
      - [User Service Example](#user-service-example)
      - [Explanation of Example Usage](#explanation-of-example-usage-2)
    - [Practical Example](#practical-example-1)
  - [5. Integration with Services and Controllers](#5-integration-with-services-and-controllers)
    - [User Registration Flow](#user-registration-flow)
    - [Email Verification Flow](#email-verification-flow)
    - [Example Controller for Authentication](#example-controller-for-authentication)
  - [6. Best Practices and Advanced Features](#6-best-practices-and-advanced-features)
    - [Custom Query Methods](#custom-query-methods)
    - [Handling Optional Results](#handling-optional-results)
    - [Transactions](#transactions)
    - [Pagination and Sorting](#pagination-and-sorting)
    - [Custom Queries with JPQL or Native SQL](#custom-queries-with-jpql-or-native-sql)
    - [Projections and DTOs](#projections-and-dtos)
    - [Indexing and Performance Optimization](#indexing-and-performance-optimization)
    - [Exception Handling](#exception-handling)
    - [Unit and Integration Testing](#unit-and-integration-testing)
  - [7. Conclusion](#7-conclusion)
  - [📗register endpoint](#register-endpoint)
  - [**Table of Contents**](#table-of-contents-1)
  - [1. **Project Dependencies**](#1-project-dependencies)
    - [**Code Overview**](#code-overview-3)
    - [**Explanation**](#explanation)
    - [**Version Alignment**](#version-alignment)
    - [**Additional Dependencies to Consider**](#additional-dependencies-to-consider)
  - [2. **Authentication Controller**](#2-authentication-controller)
    - [**Code Overview**](#code-overview-4)
    - [**Explanation**](#explanation-1)
    - [**Example Usage**](#example-usage-3)
  - [3. **Authentication Service**](#3-authentication-service)
    - [**Code Overview**](#code-overview-5)
    - [**Explanation**](#explanation-2)
    - [**Expected Functionality**](#expected-functionality)
    - [**Example Implementation**](#example-implementation)
    - [**Practical Example**](#practical-example-2)
  - [4. **Registration Request DTO**](#4-registration-request-dto)
    - [**Code Overview**](#code-overview-6)
    - [**Explanation**](#explanation-3)
    - [**Example Usage**](#example-usage-4)
  - [5. **Security Configuration**](#5-security-configuration)
    - [**Code Overview**](#code-overview-7)
    - [**Explanation**](#explanation-4)
    - [**Example Configuration Flow**](#example-configuration-flow)
    - [**Practical Example**](#practical-example-3)
  - [6. **JWT Filter**](#6-jwt-filter)
    - [**Code Overview**](#code-overview-8)
    - [**Explanation**](#explanation-5)
    - [**Practical Example**](#practical-example-4)
    - [**Potential Improvements and Considerations**](#potential-improvements-and-considerations)
  - [7. **User Details Service Implementation**](#7-user-details-service-implementation)
    - [**Code Overview**](#code-overview-9)
    - [**Explanation**](#explanation-6)
    - [**Practical Example**](#practical-example-5)
    - [**Integration with Spring Security**](#integration-with-spring-security)
  - [8. **Beans Configuration**](#8-beans-configuration)
    - [**Code Overview**](#code-overview-10)
    - [**Explanation**](#explanation-7)
    - [**Practical Example**](#practical-example-6)
    - [**Customization Options**](#customization-options)
  - [9. **JWT Service**](#9-jwt-service)
    - [**Code Overview**](#code-overview-11)
    - [**Explanation**](#explanation-8)
    - [**Potential Issues and Corrections**](#potential-issues-and-corrections)
    - [**Complete Corrected `JwtService` Example**](#complete-corrected-jwtservice-example)
  - [10. **Application Configuration (`application.yml`)**](#10-application-configuration-applicationyml)
    - [**Code Overview**](#code-overview-12)
    - [**Explanation**](#explanation-9)
      - [**1. Spring Datasource Configuration**](#1-spring-datasource-configuration)
      - [**2. JPA Configuration**](#2-jpa-configuration)
      - [**3. Mail Configuration**](#3-mail-configuration)
      - [**4. JWT Security Configuration**](#4-jwt-security-configuration)
  - [11. **Integration and Workflow**](#11-integration-and-workflow)
    - [**1. User Registration Flow**](#1-user-registration-flow)
    - [**2. Email Verification Flow**](#2-email-verification-flow)
    - [**3. User Authentication Flow**](#3-user-authentication-flow)
    - [**4. Accessing Secured Endpoints**](#4-accessing-secured-endpoints)
  - [12. **Best Practices and Recommendations**](#12-best-practices-and-recommendations)
    - [**1. Secure Secret Key Management**](#1-secure-secret-key-management)
    - [**2. Password Security**](#2-password-security)
    - [**3. JWT Token Management**](#3-jwt-token-management)
    - [**4. Exception Handling in JWT Filter**](#4-exception-handling-in-jwt-filter)
    - [**5. Logging and Monitoring**](#5-logging-and-monitoring)
    - [**6. Secure CORS Configuration**](#6-secure-cors-configuration)
    - [**7. Method-Level Security**](#7-method-level-security)
    - [**8. API Documentation**](#8-api-documentation)
    - [**9. Database Migrations**](#9-database-migrations)
    - [**10. Testing**](#10-testing)
    - [**11. Exception Handling and Global Error Responses**](#11-exception-handling-and-global-error-responses)
    - [**12. Ensuring HTTPS**](#12-ensuring-https)
  - [13. **Conclusion**](#13-conclusion)
    - [**Key Takeaways and Best Practices**:](#key-takeaways-and-best-practices)


```java
package com.wchamara.book.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String role);
}

```

```java
package com.wchamara.book.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer>{

    Optional<Token> findByToken(String token);
}

```

```java
package com.wchamara.book.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}

```

Certainly! Let's delve deeply into the three repository interfaces you've provided: `RoleRepository`, `TokenRepository`, and `UserRepository`. These repositories are fundamental components in your Spring Boot application's data access layer, leveraging Spring Data JPA to interact seamlessly with the underlying database. We'll explore each repository's structure, functionality, and practical usage with detailed examples.

---

## Table of Contents

1. [Understanding Spring Data JPA Repositories](#1-understanding-spring-data-jpa-repositories)
2. [RoleRepository](#2-rolerepository)
    - [Code Overview](#code-overview)
    - [Detailed Explanation](#detailed-explanation)
    - [Example Usage](#example-usage)
3. [TokenRepository](#3-tokenrepository)
    - [Code Overview](#code-overview-1)
    - [Detailed Explanation](#detailed-explanation-1)
    - [Example Usage](#example-usage-1)
4. [UserRepository](#4-userrepository)
    - [Code Overview](#code-overview-2)
    - [Detailed Explanation](#detailed-explanation-2)
    - [Example Usage](#example-usage-2)
5. [Integration with Services and Controllers](#5-integration-with-services-and-controllers)
    - [User Registration Flow](#user-registration-flow)
    - [Email Verification Flow](#email-verification-flow)
6. [Best Practices and Advanced Features](#6-best-practices-and-advanced-features)
    - [Custom Query Methods](#custom-query-methods)
    - [Handling Optional Results](#handling-optional-results)
    - [Transactions](#transactions)
    - [Pagination and Sorting](#pagination-and-sorting)
    - [Custom Queries with JPQL or Native SQL](#custom-queries-with-jpql-or-native-sql)
    - [Projections and DTOs](#projections-and-dtos)
    - [Indexing and Performance Optimization](#indexing-and-performance-optimization)
    - [Exception Handling](#exception-handling)
    - [Unit and Integration Testing](#unit-and-integration-testing)
7. [Conclusion](#7-conclusion)

---

## 1. Understanding Spring Data JPA Repositories

### What is Spring Data JPA?

Spring Data JPA is a part of the larger Spring Data family, aimed at simplifying data access and persistence in Spring applications. It provides a repository abstraction over the data access layer, eliminating much of the boilerplate code required to interact with databases.

### What is `JpaRepository`?

`JpaRepository` is a JPA-specific extension of the `Repository` interface in Spring Data. It provides a comprehensive set of CRUD (Create, Read, Update, Delete) operations and additional JPA-related functionality. By extending `JpaRepository`, your repository interfaces inherit these methods without needing to implement them manually.

**Key Features of `JpaRepository`:**

- **CRUD Operations**: Methods like `save()`, `findById()`, `findAll()`, `delete()`, etc.
- **Paging and Sorting**: Methods that support pagination and sorting, such as `findAll(Pageable pageable)`.
- **Custom Query Methods**: Ability to define query methods based on method naming conventions.
- **JPA Annotations Support**: Support for JPA-specific annotations and features.

### Benefits of Using Spring Data JPA Repositories

- **Reduced Boilerplate Code**: No need to implement common data access methods.
- **Consistency**: Standardized approach to data access across the application.
- **Customization**: Ability to define custom queries using method names or JPQL/SQL.
- **Integration**: Seamless integration with Spring's dependency injection and other Spring features.

---

## 2. RoleRepository

### Code Overview

```java
package com.wchamara.book.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String role);
}
```

### Detailed Explanation

- **Interface Definition**: `RoleRepository` is an interface that extends `JpaRepository<Role, Integer>`.
  
  - **`Role`**: The entity type that this repository manages.
  - **`Integer`**: The type of the primary key (ID) of the `Role` entity.

- **Inherited Methods**: By extending `JpaRepository`, `RoleRepository` inherits a suite of methods for performing CRUD operations, such as:
  
  - **`save(Role entity)`**: Save a role to the database.
  - **`findById(Integer id)`**: Find a role by its ID.
  - **`findAll()`**: Retrieve all roles.
  - **`delete(Role entity)`**: Delete a role.

- **Custom Method**: `Optional<Role> findByName(String role)`

  - **Purpose**: Retrieve a `Role` entity based on its `name` field.
  - **Return Type**: `Optional<Role>` ensures that the method handles the case where a role with the specified name might not exist, preventing `NullPointerException`.
  - **Method Naming Convention**: Spring Data JPA interprets `findByName` as a query that selects roles where the `name` field matches the provided parameter.

### Example Usage

Let's explore how to use `RoleRepository` in a service class to manage roles.

#### Role Service Example

```java
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Creates a new role if it does not already exist.
     */
    public Role createRole(String roleName) {
        // Check if the role already exists
        Optional<Role> existingRole = roleRepository.findByName(roleName);
        if (existingRole.isPresent()) {
            throw new IllegalArgumentException("Role already exists");
        }

        // Create and save the new role
        Role role = Role.builder()
                .name(roleName)
                .build();
        return roleRepository.save(role);
    }

    /**
     * Retrieves a role by name.
     */
    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    /**
     * Retrieves all roles.
     */
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
```

#### Usage in a Controller

```java
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleDto roleDto) {
        Role createdRole = roleService.createRole(roleDto.getName());
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) {
        Role role = roleService.getRoleByName(name);
        return ResponseEntity.ok(role);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
}
```

#### Explanation of Example Usage

1. **Creating a Role**:
   - **Endpoint**: `POST /api/roles`
   - **Request Body**: JSON containing the role name.
   - **Process**:
     - The controller calls `roleService.createRole()` with the provided role name.
     - `RoleService` checks if a role with that name already exists using `findByName()`.
     - If not, it creates a new `Role` entity and saves it using `save()`.

2. **Retrieving a Role by Name**:
   - **Endpoint**: `GET /api/roles/{name}`
   - **Path Variable**: `name` - the name of the role.
   - **Process**:
     - The controller calls `roleService.getRoleByName()` with the role name.
     - `RoleService` retrieves the role using `findByName()`.
     - If the role exists, it is returned; otherwise, an exception is thrown.

3. **Retrieving All Roles**:
   - **Endpoint**: `GET /api/roles`
   - **Process**:
     - The controller calls `roleService.getAllRoles()`.
     - `RoleService` uses `findAll()` to retrieve all roles from the database.

### Practical Example

Suppose you want to assign a role to a user during registration.

```java
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(UserRegistrationDto registrationDto) {
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Fetch the 'USER' role
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));

        // Create new user
        User user = User.builder()
                .firstname(registrationDto.getFirstname())
                .lastname(registrationDto.getLastname())
                .dateOfBirth(registrationDto.getDateOfBirth())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .accountLocked(false)
                .enabled(false) // Initially disabled until email verification
                .roles(List.of(userRole))
                .build();

        return userRepository.save(user);
    }
}
```

**Explanation:**

- The `UserService` class handles user registration.
- It uses `RoleRepository` to fetch the "USER" role.
- The role is then assigned to the new user by setting the `roles` list.
- Finally, the user is saved to the database.

---

## 3. TokenRepository

### Code Overview

```java
package com.wchamara.book.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer>{

    Optional<Token> findByToken(String token);
}
```

### Detailed Explanation

- **Interface Definition**: `TokenRepository` extends `JpaRepository<Token, Integer>`.
  
  - **`Token`**: The entity type managed by this repository.
  - **`Integer`**: The type of the primary key of the `Token` entity.

- **Inherited Methods**: Similar to `RoleRepository`, `TokenRepository` inherits CRUD and other standard methods.

- **Custom Method**: `Optional<Token> findByToken(String token)`

  - **Purpose**: Retrieve a `Token` entity based on its `token` field.
  - **Return Type**: `Optional<Token>` handles the case where no token matches the provided string.

### Example Usage

Let's consider scenarios such as token creation (e.g., for email verification or password reset) and token validation.

#### Token Service Example

```java
@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new token for the given user.
     */
    public Token createTokenForUser(User user) {
        String tokenString = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        Token token = Token.builder()
                .token(tokenString)
                .createdAt(now)
                .expiresAt(now.plusHours(24))
                .user(user)
                .build();

        return tokenRepository.save(token);
    }

    /**
     * Validates the token and returns the associated user if valid.
     */
    public User validateToken(String tokenString) {
        Token token = tokenRepository.findByToken(tokenString)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token has expired");
        }

        if (token.getValidatedAt() != null) {
            throw new IllegalArgumentException("Token has already been used");
        }

        // Mark token as validated
        token.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(token);

        return token.getUser();
    }
}
```

#### Usage in a Registration Flow

1. **User Registration**:
   - After creating a new user, generate a token for email verification.

2. **Email Verification**:
   - User clicks on the verification link containing the token.
   - The system validates the token and activates the user account.

**Example Code Snippets:**

```java
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       TokenService tokenService, EmailService emailService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenService = tokenService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(UserRegistrationDto registrationDto) {
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));

        User user = User.builder()
                .firstname(registrationDto.getFirstname())
                .lastname(registrationDto.getLastname())
                .dateOfBirth(registrationDto.getDateOfBirth())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .accountLocked(false)
                .enabled(false) // Initially disabled until email verification
                .roles(List.of(userRole))
                .build();

        user = userRepository.save(user);

        // Generate and save token
        Token token = tokenService.createTokenForUser(user);

        // Send verification email
        emailService.sendVerificationEmail(user.getEmail(), token.getToken());

        return user;
    }

    public void verifyUser(String tokenString) {
        User user = tokenService.validateToken(tokenString);
        user.setEnabled(true);
        userRepository.save(user);
    }
}
```

#### Explanation of Example Usage

1. **Registration**:
   - A new user is created and saved to the database with `enabled = false`.
   - A token is generated for this user using `TokenService.createTokenForUser()`.
   - An email is sent to the user containing the verification token.

2. **Verification**:
   - When the user clicks the verification link, the token is validated using `TokenService.validateToken()`.
   - If valid, the user's `enabled` field is set to `true`, allowing them to authenticate.

---

## 4. UserRepository

### Code Overview

```java
package com.wchamara.book.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
```

### Detailed Explanation

- **Interface Definition**: `UserRepository` extends `JpaRepository<User, Integer>`.
  
  - **`User`**: The entity type managed by this repository.
  - **`Integer`**: The type of the primary key of the `User` entity.

- **Inherited Methods**: Provides standard CRUD operations via `JpaRepository`.

- **Custom Method**: `Optional<User> findByEmail(String email)`

  - **Purpose**: Retrieve a `User` entity based on its `email` field.
  - **Return Type**: `Optional<User>` handles the case where a user with the specified email may not exist.

### Example Usage

Let's explore how to use `UserRepository` in various scenarios such as user registration, authentication, and profile management.

#### User Service Example

```java
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       TokenService tokenService, EmailService emailService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenService = tokenService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user and sends an email verification token.
     */
    public User registerNewUser(UserRegistrationDto registrationDto) {
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));

        User user = User.builder()
                .firstname(registrationDto.getFirstname())
                .lastname(registrationDto.getLastname())
                .dateOfBirth(registrationDto.getDateOfBirth())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        user = userRepository.save(user);

        // Generate and save token
        Token token = tokenService.createTokenForUser(user);

        // Send verification email
        emailService.sendVerificationEmail(user.getEmail(), token.getToken());

        return user;
    }

    /**
     * Loads a user by username (email) for Spring Security authentication.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    /**
     * Updates user profile.
     */
    public User updateUserProfile(Integer userId, UserProfileDto profileDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstname(profileDto.getFirstname());
        user.setLastname(profileDto.getLastname());
        user.setDateOfBirth(profileDto.getDateOfBirth());

        // Optionally update email or password
        if (profileDto.getEmail() != null && !profileDto.getEmail().equals(user.getEmail())) {
            if (userRepository.findByEmail(profileDto.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Email already in use");
            }
            user.setEmail(profileDto.getEmail());
            user.setEnabled(false); // Require re-verification
            Token token = tokenService.createTokenForUser(user);
            emailService.sendVerificationEmail(user.getEmail(), token.getToken());
        }

        if (profileDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(profileDto.getPassword()));
        }

        return userRepository.save(user);
    }

    /**
     * Retrieves user by ID.
     */
    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
```

#### Explanation of Example Usage

1. **Registering a New User**:
   - **Email Uniqueness Check**: `findByEmail()` is used to check if a user with the given email already exists.
   - **Role Assignment**: The "USER" role is fetched and assigned to the new user.
   - **Password Encryption**: The user's password is encoded using `PasswordEncoder`.
   - **Saving User**: The new user is saved to the database using `userRepository.save()`.
   - **Token Generation and Email Sending**: A token is created for email verification, and an email is sent to the user with the token.

2. **Loading User for Authentication**:
   - **Implementation of `UserDetailsService`**: The `UserService` implements `UserDetailsService` to integrate with Spring Security.
   - **`loadUserByUsername` Method**: Uses `findByEmail()` to retrieve the user based on the provided email. If not found, throws `UsernameNotFoundException`.

3. **Updating User Profile**:
   - **Fetching User by ID**: Uses `findById()` to retrieve the user to update.
   - **Updating Fields**: Updates fields like firstname, lastname, dateOfBirth, and optionally email and password.
   - **Email Update**: If the email is updated, checks for uniqueness using `findByEmail()`, disables the user, creates a new token, and sends a verification email.
   - **Password Update**: If the password is provided, it is encoded before saving.
   - **Saving Changes**: Updates are saved using `userRepository.save()`.

4. **Retrieving a User by ID**:
   - **`getUserById` Method**: Fetches a user by their primary key using `findById()`.

### Practical Example

**Authentication Flow with Spring Security:**

1. **User Attempts to Login**:
   - The user provides their email and password.
   
2. **Spring Security Calls `loadUserByUsername`**:
   - Spring Security uses the `UserService.loadUserByUsername()` method to load the user details.
   - `UserRepository.findByEmail(email)` retrieves the user entity.
   - If the user is found, their details (including roles) are returned to Spring Security.
   - If the user is not found, an exception is thrown, and authentication fails.

3. **Spring Security Validates Credentials**:
   - Spring Security compares the provided password with the encoded password from the user entity using `PasswordEncoder`.
   - It also checks other flags like `isAccountNonLocked`, `isEnabled`, etc., as defined in the `User` entity.

4. **Authentication Success or Failure**:
   - If all checks pass, the user is authenticated and granted access based on their roles.
   - If any check fails, authentication is denied.

**Example Code in Security Configuration:**

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Enable CSRF protection in production
            .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll() // Allow public access to auth endpoints
                .anyRequest().authenticated() // Secure all other endpoints
            .and()
            .formLogin(); // Enable form-based login
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

**Explanation:**

- **`configure(AuthenticationManagerBuilder auth)`**:
  - Configures Spring Security to use `UserService` as the `UserDetailsService`.
  - Specifies the `PasswordEncoder` to use for password matching.

- **`configure(HttpSecurity http)`**:
  - Disables CSRF protection for simplicity (not recommended in production).
  - Permits all requests to `/api/auth/**` (e.g., registration, login).
  - Requires authentication for all other requests.
  - Enables form-based login.

---

## 5. Integration with Services and Controllers

To fully understand how these repositories work together, let's walk through a typical user registration and email verification flow.

### User Registration Flow

1. **User Submits Registration Details**:
   - Endpoint: `POST /api/auth/register`
   - Request Body: JSON containing `firstname`, `lastname`, `email`, `password`, etc.

2. **Controller Receives Request**:
   - The controller delegates the registration process to the `UserService`.

3. **UserService Processes Registration**:
   - **Email Uniqueness Check**: Uses `UserRepository.findByEmail()` to ensure the email isn't already registered.
   - **Role Assignment**: Retrieves the "USER" role using `RoleRepository.findByName()`.
   - **Password Encryption**: Encodes the user's password.
   - **User Creation**: Constructs a new `User` entity and saves it using `UserRepository.save()`.
   - **Token Generation**: Creates a verification token using `TokenService.createTokenForUser()`.
   - **Email Sending**: Sends a verification email containing the token using `EmailService.sendVerificationEmail()`.

4. **Response Sent to Client**:
   - The controller responds with a success message indicating that registration was successful and that a verification email has been sent.

### Email Verification Flow

1. **User Clicks Verification Link**:
   - The link contains the token, e.g., `GET /api/auth/verify?token=abcdefg`.

2. **Controller Receives Verification Request**:
   - The controller extracts the token from the request parameters and delegates verification to the `UserService`.

3. **UserService Validates Token**:
   - **Token Retrieval**: Uses `TokenRepository.findByToken()` to fetch the token.
   - **Token Validation**:
     - Checks if the token exists.
     - Verifies that the token hasn't expired.
     - Ensures the token hasn't already been used.
   - **User Activation**: Sets the user's `enabled` field to `true` using `UserRepository.save()`.
   - **Token Update**: Marks the token as validated by setting `validatedAt`.

4. **Response Sent to Client**:
   - The controller responds with a success message indicating that the email has been verified and the account is now active.

### Example Controller for Authentication

```java
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public AuthController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        userService.registerNewUser(registrationDto);
        return new ResponseEntity<>("User registered successfully. Please check your email for verification.", HttpStatus.CREATED);
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam("token") String token) {
        userService.verifyUser(token);
        return ResponseEntity.ok("Email verified successfully. You can now log in.");
    }

    // Additional endpoints like login can be added here
}
```

**Explanation:**

- **`registerUser` Endpoint**:
  - Receives user registration details.
  - Calls `userService.registerNewUser()` to handle the registration logic.
  - Responds with a success message.

- **`verifyUser` Endpoint**:
  - Receives the verification token as a query parameter.
  - Calls `userService.verifyUser()` to handle the verification logic.
  - Responds with a success message upon successful verification.

---

## 6. Best Practices and Advanced Features

### Custom Query Methods

Spring Data JPA allows the definition of custom query methods based on method naming conventions. This feature eliminates the need for writing boilerplate JPQL or SQL queries.

**Example:**

```java
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findByFirstname(String firstname);
    List<User> findByLastnameAndEmail(String lastname, String email);
}
```

Spring Data JPA interprets these method names and automatically generates the corresponding queries.

### Handling Optional Results

The use of `Optional<T>` as return types in repository methods encourages handling of absent values gracefully, preventing `NullPointerException`s.

**Example:**

```java
Optional<User> optionalUser = userRepository.findByEmail(email);
if (optionalUser.isPresent()) {
    User user = optionalUser.get();
    // Proceed with user
} else {
    // Handle user not found
}
```

Alternatively, you can use `orElseThrow` for concise exception handling:

```java
User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
```

### Transactions

Spring Data JPA methods are transactional by default. However, for complex operations that span multiple repository calls or require atomicity, you should explicitly define transactions.

**Example:**

```java
@Service
public class UserService {

    // Inject repositories and other dependencies

    @Transactional
    public void performComplexOperation() {
        // Multiple repository operations
        // All operations succeed or fail together
    }
}
```

### Pagination and Sorting

Repositories extending `JpaRepository` can utilize methods that support pagination and sorting.

**Example:**

```java
public Page<User> getUsers(int page, int size, String sortBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return userRepository.findAll(pageable);
}
```

**Usage in Controller:**

```java
@GetMapping("/users")
public ResponseEntity<Page<User>> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy) {
    Page<User> users = userService.getUsers(page, size, sortBy);
    return ResponseEntity.ok(users);
}
```

### Custom Queries with JPQL or Native SQL

In cases where method naming conventions are insufficient, you can define custom queries using the `@Query` annotation.

**Example:**

```java
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email LIKE %:domain")
    List<User> findByEmailDomain(@Param("domain") String domain);
}
```

**Usage:**

```java
List<User> usersWithGmail = userRepository.findByEmailDomain("gmail.com");
```

### Projections and DTOs

To optimize performance and data transfer, use projections or Data Transfer Objects (DTOs) to fetch only necessary fields.

**Example:**

```java
public interface UserSummary {
    String getFirstname();
    String getLastname();
    String getEmail();
}

public interface UserRepository extends JpaRepository<User, Integer> {
    List<UserSummary> findAllProjectedBy();
}
```

**Usage:**

```java
List<UserSummary> summaries = userRepository.findAllProjectedBy();
summaries.forEach(summary -> {
    System.out.println(summary.getFirstname() + " " + summary.getLastname() + " - " + summary.getEmail());
});
```

### Indexing and Performance Optimization

Ensure that frequently queried fields (e.g., `email` in `User`, `name` in `Role`) are indexed in the database to improve query performance. This is already partially handled by JPA annotations like `@Column(unique = true)`, which often implies an index.

**Example:**

In the `Role` entity:

```java
@Column(unique = true, nullable = false)
private String name;
```

This typically creates a unique index on the `name` column.

### Exception Handling

Implement robust exception handling strategies to manage scenarios like entity not found, data integrity violations, etc.

**Example:**

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFound(UsernameNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgs(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handle other exceptions
}
```

### Unit and Integration Testing

Test your repositories and services to ensure they function correctly.

**Example:**

**Repository Test Using H2 In-Memory Database:**

```java
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        User user = User.builder()
                .firstname("Jane")
                .lastname("Doe")
                .email("jane.doe@example.com")
                .password("password")
                .enabled(true)
                .build();
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail("jane.doe@example.com");
        assertTrue(foundUser.isPresent());
        assertEquals("Jane", foundUser.get().getFirstname());
    }
}
```

**Explanation:**

- **`@DataJpaTest`**: Configures an in-memory database, scans for `@Entity` classes, and configures Spring Data JPA repositories.
- **Test Method**: Creates and saves a `User` entity, then retrieves it using `findByEmail()` to verify functionality.

---

## 7. Conclusion

The repository interfaces `RoleRepository`, `TokenRepository`, and `UserRepository` are integral components of your Spring Boot application's data access layer. They leverage Spring Data JPA’s capabilities to provide robust, efficient, and easy-to-use mechanisms for interacting with the database.

**Key Takeaways:**

- **Leverage `JpaRepository`**: Extending `JpaRepository` provides a wealth of functionality out-of-the-box, reducing the need for boilerplate code.
- **Use Method Naming Conventions**: Define custom query methods by following Spring Data JPA’s naming conventions, allowing for automatic query generation.
- **Handle `Optional` Properly**: Use `Optional<T>` to handle potential `null` values gracefully.
- **Integrate with Services and Controllers**: Repositories are typically used within service classes, which encapsulate business logic and are called by controllers handling HTTP requests.
- **Follow Best Practices**: Implement proper exception handling, security measures, transaction management, and testing to ensure a robust application.

By understanding and effectively utilizing Spring Data JPA repositories, you can build scalable and maintainable data access layers in your Spring Boot applications.

---

Feel free to ask if you need further clarification or more examples on any of these topics!

## 📗register endpoint

made all the depedencies the same version

```xml
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.6</version>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.6</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.12.6</version>
            <scope>runtime</scope>
        </dependency>
```


```java
package com.wchamara.book.auth;


import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) {

        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }


}

```

```java
package com.wchamara.book.auth;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public void register(RegistrationRequest request) {

    }
}

```

```java
package com.wchamara.book.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "First name is required")
    @NotBlank(message = "First name cannot be empty")
    private String firstname;

    @NotEmpty(message = "Last name is required")
    @NotBlank(message = "Last name cannot be empty")
    private String lastname;

    @NotEmpty(message = "Email is required")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Password is required")
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

}

```

```java
package com.wchamara.book.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(
                                        "/auth/**",
                                        "/v2/api-docs",
                                        "/v3/api-docs",
                                        "/v3/api-docs/**",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html"
                                )
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}

```

```java
package com.wchamara.book.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {


        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        final String jwt;
        final String userEmail;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            userEmail = jwtService.extractUsername(jwt);
            if (userEmail != null && !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                if (jwtService.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }
}

```

```java
package com.wchamara.book.security;

import com.wchamara.book.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may be case-sensitive, or case-insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested.
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

```

```java
package com.wchamara.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final UserDetailsService userDetailsService;

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
package com.wchamara.book.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.expiration}")
    private Long jwtExpirationInMs;

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);


    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSignedKey()).build().parseSignedClaims(token).getPayload();
    }

    private String generateToken(Map<String, Object> claims, UserDetails userDetails) {

        return buildToke(claims, userDetails, jwtExpirationInMs);
    }

    private String buildToke(Map<String, Object> extraClaims, UserDetails userDetails, Long jwtExpirationInMs) {
        var authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .claim("authorities", authorities)
                .signWith(getSignedKey())
                .compact();

    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private SecretKey getSignedKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

```

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
application:
  security:
    jwt:
      secret-key: secret
      expiration: 86400000
      token-prefix: Bearer
      header-string: Authorization
```

Certainly! Let's conduct an in-depth analysis of the provided Spring Boot application code snippets. These snippets form a comprehensive authentication system using JWT (JSON Web Tokens), Spring Security, and JJWT (Java JWT) library for handling JWT operations. We'll break down each component, understand their roles, and illustrate their interactions with practical examples.

---

## **Table of Contents**

1. [Project Dependencies](#1-project-dependencies)
2. [Authentication Controller](#2-authentication-controller)
3. [Authentication Service](#3-authentication-service)
4. [Registration Request DTO](#4-registration-request-dto)
5. [Security Configuration](#5-security-configuration)
6. [JWT Filter](#6-jwt-filter)
7. [User Details Service Implementation](#7-user-details-service-implementation)
8. [Beans Configuration](#8-beans-configuration)
9. [JWT Service](#9-jwt-service)
10. [Application Configuration (`application.yml`)](#10-application-configuration-applicationyml)
11. [Integration and Workflow](#11-integration-and-workflow)
12. [Best Practices and Recommendations](#12-best-practices-and-recommendations)
13. [Conclusion](#13-conclusion)

---

## 1. **Project Dependencies**

### **Code Overview**

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.6</version>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.6</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>
```

### **Explanation**

These dependencies are crucial for handling JWT operations in your Spring Boot application. Here's a breakdown of each:

1. **`jjwt-api`**:
   - **Purpose**: Contains the core API for JWT creation and parsing.
   - **Usage**: Provides interfaces and classes to build and verify JWTs.

2. **`jjwt-impl`**:
   - **Purpose**: Implements the APIs defined in `jjwt-api`.
   - **Usage**: Necessary for the runtime behavior of JWT operations.

3. **`jjwt-jackson`**:
   - **Purpose**: Provides JSON processing using Jackson.
   - **Usage**: Facilitates the serialization and deserialization of JWTs to and from JSON.

### **Version Alignment**

- **Consistency**: All three JJWT dependencies are set to the same version (`0.12.6`), ensuring compatibility and preventing class/method resolution issues.
- **Scope**: `jjwt-jackson` is marked with `<scope>runtime</scope>`, meaning it's only needed during runtime and not at compile-time.

### **Additional Dependencies to Consider**

While not shown, ensure your `pom.xml` includes necessary dependencies for Spring Boot, Spring Security, Lombok, PostgreSQL, and other libraries used in your project.

**Example:**

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- PostgreSQL Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.2.23</version>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.20</version>
        <scope>provided</scope>
    </dependency>

    <!-- JJWT Dependencies as above -->
</dependencies>
```

---

## 2. **Authentication Controller**

### **Code Overview**

```java
package com.wchamara.book.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) {
        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }

}
```

### **Explanation**

- **Annotations**:
  - **`@RestController`**: Indicates that this class is a REST controller, handling HTTP requests and returning responses.
  - **`@RequestMapping("auth")`**: Sets the base path for all endpoints in this controller to `/auth`.
  - **`@RequiredArgsConstructor`**: Lombok annotation that generates a constructor with required arguments (i.e., for `final` fields).
  - **`@Tag(name = "Authentication")`**: Swagger/OpenAPI annotation for grouping endpoints under the "Authentication" tag in API documentation.

- **Dependencies**:
  - **`private final AuthenticationService authenticationService;`**: Injected via constructor injection (thanks to Lombok's `@RequiredArgsConstructor`), this service handles the business logic for authentication.

- **Endpoints**:
  - **`@PostMapping("/register")`**:
    - **Path**: `/auth/register`
    - **Method**: `POST`
    - **Description**: Handles user registration.
    - **Parameters**:
      - **`@RequestBody @Valid RegistrationRequest request`**:
        - **`@RequestBody`**: Binds the HTTP request body to the `RegistrationRequest` object.
        - **`@Valid`**: Triggers validation based on constraints defined in `RegistrationRequest`.
    - **Response**:
      - **`ResponseEntity<?>`**: Returns an HTTP response.
      - **`HttpStatus.ACCEPTED` (202)**: Indicates that the request has been accepted for processing, but the processing is not yet complete.
      - **`ResponseEntity.accepted().build()`**: Constructs an empty response with status 202.

### **Example Usage**

Assume you have the following JSON payload for registration:

```json
{
    "firstname": "John",
    "lastname": "Doe",
    "email": "john.doe@example.com",
    "password": "SecureP@ssw0rd"
}
```

**HTTP Request:**

```
POST /auth/register
Content-Type: application/json

{
    "firstname": "John",
    "lastname": "Doe",
    "email": "john.doe@example.com",
    "password": "SecureP@ssw0rd"
}
```

**HTTP Response:**

```
HTTP/1.1 202 Accepted
```

**Process Flow:**

1. **Client** sends a `POST` request to `/auth/register` with user details.
2. **AuthenticationController** receives the request and binds the JSON payload to a `RegistrationRequest` object.
3. **Validation**: The `@Valid` annotation ensures that the request data adheres to the constraints defined in `RegistrationRequest`.
4. **AuthenticationService** is invoked to handle the registration logic.
5. **Response**: Returns a 202 Accepted status, indicating that the registration process has been initiated (e.g., sending verification email).

---

## 3. **Authentication Service**

### **Code Overview**

```java
package com.wchamara.book.auth;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public void register(RegistrationRequest request) {

    }
}
```

### **Explanation**

- **`@Service` Annotation**: Marks this class as a Spring service component, making it eligible for component scanning and dependency injection.

- **`register` Method**:
  - **Parameters**: Accepts a `RegistrationRequest` object containing user registration details.
  - **Functionality**: Currently empty, but intended to handle the business logic for user registration.

### **Expected Functionality**

The `register` method should perform the following operations:

1. **Validate Input**: Although validation is triggered by `@Valid` in the controller, additional business validations can be performed here.
2. **Check for Existing User**: Ensure that the email is not already registered.
3. **Encrypt Password**: Hash the user's password before storing it.
4. **Assign Roles**: Assign default roles (e.g., "USER") to the new user.
5. **Save User**: Persist the user entity to the database using `UserRepository`.
6. **Generate JWT Token**: Create a JWT for authentication or verification purposes.
7. **Send Verification Email**: Email the user with a verification link containing the JWT token.

### **Example Implementation**

Let's flesh out the `register` method to perform these tasks.

```java
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenService tokenService;
    private final EmailService emailService;

    public void register(RegistrationRequest request) {
        // 1. Check if user already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use");
        }

        // 2. Fetch 'USER' role
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));

        // 3. Create and save user
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(false) // Initially disabled until email verification
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);

        // 4. Generate verification token
        Token verificationToken = tokenService.createTokenForUser(user);

        // 5. Send verification email
        emailService.sendVerificationEmail(user.getEmail(), verificationToken.getToken());
    }
}
```

**Explanation of the Enhanced `register` Method:**

1. **User Existence Check**:
   - **`userRepository.findByEmail(request.getEmail())`**: Checks if a user with the provided email already exists.
   - **Exception Handling**: Throws an `IllegalArgumentException` if the email is already registered.

2. **Role Assignment**:
   - **`roleRepository.findByName("USER")`**: Fetches the "USER" role from the database.
   - **Exception Handling**: Throws a `RuntimeException` if the "USER" role is not found.

3. **User Creation and Saving**:
   - **Builder Pattern**: Utilizes Lombok's `@Builder` to create a new `User` instance.
   - **Password Encryption**: Uses `PasswordEncoder` to hash the user's password before saving.
   - **Enabled Flag**: Sets `enabled` to `false` initially, pending email verification.
   - **Role Assignment**: Assigns the "USER" role to the new user.
   - **`userRepository.save(user)`**: Persists the user to the database.

4. **Verification Token Generation**:
   - **`tokenService.createTokenForUser(user)`**: Generates a JWT token associated with the user for email verification.

5. **Email Sending**:
   - **`emailService.sendVerificationEmail(user.getEmail(), verificationToken.getToken())`**: Sends an email to the user containing the verification token.

### **Practical Example**

Assuming a user submits the following registration details:

```json
{
    "firstname": "Alice",
    "lastname": "Smith",
    "email": "alice.smith@example.com",
    "password": "StrongP@ssw0rd!"
}
```

**Process Flow:**

1. **Controller** receives the `POST /auth/register` request with the above payload.
2. **AuthenticationController** validates the request and delegates to `AuthenticationService.register(request)`.
3. **AuthenticationService**:
   - Checks if `alice.smith@example.com` is already registered.
   - Fetches the "USER" role.
   - Creates a new `User` entity with `enabled = false` and saves it.
   - Generates a verification token associated with Alice.
   - Sends a verification email to Alice with the token.
4. **Client** receives a `202 Accepted` response, indicating that registration is in progress.

---

## 4. **Registration Request DTO**

### **Code Overview**

```java
package com.wchamara.book.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "First name is required")
    @NotBlank(message = "First name cannot be empty")
    private String firstname;

    @NotEmpty(message = "Last name is required")
    @NotBlank(message = "Last name cannot be empty")
    private String lastname;

    @NotEmpty(message = "Email is required")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Password is required")
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

}
```

### **Explanation**

- **Annotations**:
  - **`@Getter` & `@Setter`**: Lombok annotations to generate getter and setter methods for all fields.
  - **`@Builder`**: Lombok annotation to implement the Builder pattern, allowing for more readable and maintainable object creation.

- **Validation Constraints**:
  - **`@NotEmpty`**: Ensures that the field is not `null` or empty (i.e., length > 0).
  - **`@NotBlank`**: Ensures that the field is not `null`, empty, or whitespace-only.
  - **`@Email`**: Validates that the field follows a valid email format.
  - **`@Size(min = 8)`**: Ensures that the password is at least 8 characters long.

- **Fields**:
  - **`firstname`**: User's first name.
  - **`lastname`**: User's last name.
  - **`email`**: User's email address.
  - **`password`**: User's password.

### **Example Usage**

**JSON Payload for Registration:**

```json
{
    "firstname": "Bob",
    "lastname": "Johnson",
    "email": "bob.johnson@example.com",
    "password": "P@ssw0rd123"
}
```

**Validation Process:**

1. **Controller** receives the `RegistrationRequest` object via `@RequestBody` and `@Valid`.
2. **Validation Annotations** ensure:
   - `firstname` and `lastname` are not empty or blank.
   - `email` is not empty, not blank, and follows a valid email format.
   - `password` is not empty, not blank, and at least 8 characters long.
3. **Constraint Violations**:
   - If any field fails validation, Spring Boot automatically returns a `400 Bad Request` with details about the validation errors.
   - **Example**: Submitting a password shorter than 8 characters would trigger a validation error.

**Error Response Example:**

```json
{
    "timestamp": "2024-04-27T12:34:56.789+00:00",
    "status": 400,
    "error": "Bad Request",
    "messages": [
        "Password must be at least 8 characters long"
    ],
    "path": "/auth/register"
}
```

---

## 5. **Security Configuration**

### **Code Overview**

```java
package com.wchamara.book.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(
                                        "/auth/**",
                                        "/v2/api-docs",
                                        "/v3/api-docs",
                                        "/v3/api-docs/**",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html"
                                )
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}
```

### **Explanation**

- **Annotations**:
  - **`@EnableWebSecurity`**: Enables Spring Security’s web security support.
  - **`@Configuration`**: Marks the class as a configuration class.
  - **`@RequiredArgsConstructor`**: Lombok annotation to generate a constructor with required arguments (i.e., for `final` fields).
  - **`@EnableMethodSecurity(securedEnabled = true)`**: Enables method-level security using annotations like `@Secured`.

- **Dependencies**:
  - **`private final AuthenticationProvider authenticationProvider;`**: Injected authentication provider to handle user authentication.
  - **`private final JwtFilter jwtAuthFilter;`**: Injected JWT filter to process JWT tokens in incoming requests.

- **`securityFilterChain` Bean**:
  - **Purpose**: Configures the security filter chain, defining how requests are secured and which filters are applied.
  
  - **Configurations**:
    1. **CORS Configuration**:
       - **`cors(withDefaults())`**: Enables Cross-Origin Resource Sharing with default settings. You can customize it further if needed.
    
    2. **CSRF Configuration**:
       - **`csrf(AbstractHttpConfigurer::disable)`**: Disables CSRF protection. This is common in stateless APIs using JWTs, as CSRF protection is primarily needed for stateful sessions.
       - **Security Note**: Ensure that your application doesn’t have other vulnerabilities that CSRF protection mitigates, especially if it's not entirely stateless.
    
    3. **Authorization Rules**:
       - **`authorizeHttpRequests`**: Defines authorization rules for incoming HTTP requests.
       - **`requestMatchers`**:
         - **Permitted Paths**:
           - **`/auth/**`**: All authentication-related endpoints (e.g., register, login).
           - **Swagger UI Paths**: Enables access to Swagger/OpenAPI documentation without authentication.
         - **`permitAll()`**: Allows unrestricted access to the specified paths.
       - **`anyRequest().authenticated()`**: Requires authentication for all other endpoints not explicitly permitted.
    
    4. **Session Management**:
       - **`sessionManagement(session -> session.sessionCreationPolicy(STATELESS))`**:
         - **`SessionCreationPolicy.STATELESS`**: Disables session creation. The application won't store any session data, relying entirely on JWTs for authentication.
    
    5. **Authentication Provider**:
       - **`authenticationProvider(authenticationProvider)`**: Sets the custom `AuthenticationProvider` for handling authentication logic.
    
    6. **JWT Filter**:
       - **`addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)`**: Adds the `JwtFilter` before the `UsernamePasswordAuthenticationFilter` in the security filter chain. This ensures that JWT processing occurs before Spring Security’s default authentication processing.

### **Example Configuration Flow**

1. **Incoming Request**:
   - **Path**: `/api/books`
   - **Header**: `Authorization: Bearer <JWT_TOKEN>`
   
2. **Security Filter Chain Processing**:
   - **CORS Handling**: Processes CORS headers.
   - **CSRF Disabled**: Skips CSRF checks.
   - **Authorization Rules**:
     - Checks if the request matches any of the permitted paths (`/auth/**`, Swagger paths).
     - Since `/api/books` is not in the permitted paths, it requires authentication.
   
3. **JWT Filter (`JwtFilter`)**:
   - **Extraction**: Extracts the JWT from the `Authorization` header.
   - **Validation**: Validates the JWT and sets the authentication in the `SecurityContext`.
   
4. **Authentication Check**:
   - If JWT is valid, the request proceeds to the secured endpoint (`/api/books`).
   - If JWT is invalid or missing, Spring Security responds with `401 Unauthorized`.

### **Practical Example**

**Scenario**: Accessing a secured endpoint `/api/books` with a valid JWT.

**HTTP Request:**

```
GET /api/books
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQGV4YW1wbGUuY29tIiwiaWF0IjoxNjEyMzQ1Njc4LCJleHAiOjE2MTIzODIyNzh9.DfGfYbW3nA3bYh5zLkXkHzYh1VqU1ZoQKFtGxVUgkX0
```

**Process Flow:**

1. **CORS Handling**: Processes any CORS preflight requests if necessary.
2. **CSRF Disabled**: Skips CSRF checks.
3. **Authorization Check**:
   - `/api/books` is not in the permitted paths; requires authentication.
4. **JWT Filter**:
   - Extracts the JWT from the `Authorization` header.
   - Validates the JWT using `JwtService`.
   - If valid, sets the authentication in `SecurityContext`.
5. **Endpoint Access**:
   - User is authenticated and authorized to access `/api/books`.
   - Controller handling `/api/books` processes the request and returns data.

---

## 6. **JWT Filter**

### **Code Overview**

```java
package com.wchamara.book.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {


        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        final String jwt;
        final String userEmail;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            userEmail = jwtService.extractUsername(jwt);
            if (userEmail != null && !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                if (jwtService.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
```

### **Explanation**

- **Class and Annotations**:
  - **`@Service`**: Marks this class as a Spring service component.
  - **`@RequiredArgsConstructor`**: Lombok annotation to generate a constructor with required arguments (i.e., for `final` fields).
  - **`JwtFilter`**: Extends `OncePerRequestFilter`, ensuring the filter is invoked once per request.

- **Dependencies**:
  - **`private final JwtService jwtService;`**: Service for handling JWT operations (generation, validation, extraction).
  - **`private final UserDetailsService userDetailsService;`**: Service to load user-specific data.

- **`doFilterInternal` Method**:
  - **Purpose**: Intercepts incoming HTTP requests to extract and validate JWTs, setting the security context accordingly.

- **Filter Logic**:
  1. **Path Exclusion**:
     - **`if (request.getServletPath().contains("/api/v1/auth"))`**:
       - **Purpose**: Excludes authentication-related endpoints from JWT filtering to prevent intercepting login or registration requests.
       - **Action**: Bypasses JWT processing and continues the filter chain.

  2. **Authorization Header Extraction**:
     - **`final String authorizationHeader = request.getHeader(AUTHORIZATION);`**:
       - Extracts the `Authorization` header from the incoming request.
       - **`AUTHORIZATION`** is statically imported from `org.springframework.http.HttpHeaders`.

  3. **JWT Extraction and Validation**:
     - **Check**: Ensures the `Authorization` header starts with `"Bearer "`.
     - **Extraction**: Retrieves the JWT by removing the `"Bearer "` prefix.
     - **Username Extraction**: Uses `jwtService.extractUsername(jwt)` to get the username (typically email) from the JWT claims.
     - **Authentication Check**:
       - **`userEmail != null`**: Ensures that the username was successfully extracted.
       - **`!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()`**: Checks if the user is not already authenticated in the current security context.
     - **User Details Loading**: Loads user details using `userDetailsService.loadUserByUsername(userEmail)`.
     - **Token Validation**: Validates the token against the loaded user details using `jwtService.validateToken(jwt, userDetails)`.
     - **Authentication Token Creation**:
       - **`UsernamePasswordAuthenticationToken`**: Creates an authentication token with user details and authorities.
       - **`setDetails`**: Sets additional authentication details from the request.
     - **Security Context Update**: Sets the authentication token in the `SecurityContextHolder`, marking the user as authenticated.

  4. **Filter Chain Continuation**:
     - **`filterChain.doFilter(request, response);`**: Continues processing the request through the filter chain.

### **Practical Example**

**Scenario**: A user with a valid JWT accesses a secured endpoint `/api/books`.

**HTTP Request:**

```
GET /api/books
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQGV4YW1wbGUuY29tIiwiaWF0IjoxNjEyMzQ1Njc4LCJleHAiOjE2MTIzODIyNzh9.DfGfYbW3nA3bYh5zLkXkHzYh1VqU1ZoQKFtGxVUgkX0
```

**Filter Processing:**

1. **Path Check**:
   - `/api/books` does not contain `/api/v1/auth`; proceeds to JWT processing.

2. **Authorization Header Extraction**:
   - Extracts the JWT token after the `"Bearer "` prefix.

3. **JWT Validation**:
   - **Username Extraction**: Retrieves `user@example.com` from the token.
   - **User Details Loading**: Loads user details for `user@example.com`.
   - **Token Validation**: Ensures the token is valid and not expired.

4. **Authentication Context Setup**:
   - Creates and sets `UsernamePasswordAuthenticationToken` in the `SecurityContextHolder`.

5. **Request Processing**:
   - The request proceeds to the `/api/books` controller with an authenticated user.

**Outcome**:

- **Valid Token**: User is authenticated and granted access to `/api/books`.
- **Invalid/Expired Token**: The filter does not set the authentication, and Spring Security responds with `401 Unauthorized`.

### **Potential Improvements and Considerations**

1. **Endpoint Path Consistency**:
   - The filter excludes paths containing `/api/v1/auth`, but the controller is mapped to `/auth`. Ensure consistency in path prefixes to avoid unintended filtering.
   - **Recommendation**: Adjust the path check to match your actual authentication endpoints.

   **Example**:
   ```java
   if (request.getServletPath().startsWith("/auth")) {
       filterChain.doFilter(request, response);
       return;
   }
   ```

2. **SecurityContext Authentication Check**:
   - **Current Check**: `!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()`
   - **Issue**: If the authentication is not set (`getAuthentication()` returns `null`), calling `isAuthenticated()` will throw a `NullPointerException`.
   - **Solution**: Modify the condition to handle `null` authentication.

   **Updated Condition**:
   ```java
   if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
       // Proceed with authentication
   }
   ```

3. **Exception Handling**:
   - **Current Implementation**: Any exceptions during JWT extraction or validation will propagate, potentially leading to generic error responses.
   - **Recommendation**: Implement exception handling within the filter to provide meaningful error responses.

   **Example**:
   ```java
   try {
       // JWT extraction and validation logic
   } catch (JwtException e) {
       response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
       response.getWriter().write("Invalid or expired JWT token");
       return;
   }
   ```

4. **Optimizing Token Parsing**:
   - **Repeated Parsing**: If multiple filters or components need to parse the JWT, consider optimizing by caching claims or using a centralized service.

---

## 7. **User Details Service Implementation**

### **Code Overview**

```java
package com.wchamara.book.security;

import com.wchamara.book.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may be case-sensitive, or case-insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested.
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
```

### **Explanation**

- **Class and Annotations**:
  - **`@Service`**: Marks this class as a Spring service component.
  - **`@RequiredArgsConstructor`**: Lombok annotation to generate a constructor with required arguments.

- **Implementation**:
  - **`UserDetailsService`**: An interface provided by Spring Security to load user-specific data.

- **`loadUserByUsername` Method**:
  - **Purpose**: Locates the user based on the provided username (in this case, email) and returns a `UserDetails` object containing user information and authorities.
  
  - **Parameters**:
    - **`String username`**: The username (email) of the user attempting to authenticate.
  
  - **Return Type**:
    - **`UserDetails`**: An interface that provides core user information to Spring Security.
  
  - **Logic**:
    1. **User Lookup**:
       - **`userRepository.findByEmail(username)`**: Searches for a user with the given email.
    2. **Exception Handling**:
       - **`orElseThrow`**: If no user is found, throws a `UsernameNotFoundException`, signaling Spring Security that authentication should fail.

### **Practical Example**

**Scenario**: A user attempts to log in with email `jane.doe@example.com`.

**Process Flow:**

1. **Authentication Request**:
   - User submits login credentials (email and password).

2. **Spring Security Integration**:
   - Spring Security invokes `loadUserByUsername("jane.doe@example.com")` to retrieve user details.

3. **User Lookup**:
   - **`UserRepository.findByEmail("jane.doe@example.com")`**:
     - **If Found**: Returns the `User` entity, which implements `UserDetails`.
     - **If Not Found**: Throws `UsernameNotFoundException`, leading to an authentication failure.

4. **Authentication Decision**:
   - **Successful Lookup**: Proceeds to password validation.
   - **Failed Lookup**: Responds with `401 Unauthorized`.

### **Integration with Spring Security**

Given that `User` implements `UserDetails`, returning the `User` entity directly satisfies the `UserDetailsService` contract.

**Example in `SecurityConfig`:**

```java
@Autowired
private UserDetailServiceImpl userDetailsService;

@Bean
public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
}
```

**Explanation:**

- **`DaoAuthenticationProvider`**:
  - **Purpose**: An `AuthenticationProvider` implementation that uses a `UserDetailsService` to retrieve user information and a `PasswordEncoder` to validate passwords.
  
- **Configuration**:
  - **`setUserDetailsService(userDetailsService)`**: Sets the custom `UserDetailsService` (`UserDetailServiceImpl`) for user retrieval.
  - **`setPasswordEncoder(passwordEncoder())`**: Sets the `PasswordEncoder` to validate user passwords.

**Authentication Flow:**

1. **User submits login credentials**.
2. **Spring Security** uses `DaoAuthenticationProvider` to:
   - Invoke `UserDetailServiceImpl.loadUserByUsername(email)` to fetch user details.
   - Validate the submitted password against the stored (hashed) password using `PasswordEncoder`.
3. **Authentication Decision**:
   - **Success**: Sets the authentication in the `SecurityContext`.
   - **Failure**: Returns `401 Unauthorized`.

---

## 8. **Beans Configuration**

### **Code Overview**

```java
package com.wchamara.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final UserDetailsService userDetailsService;

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

### **Explanation**

- **Class and Annotations**:
  - **`@Configuration`**: Indicates that this class contains bean definitions.
  - **`@RequiredArgsConstructor`**: Lombok annotation to generate a constructor with required arguments.

- **Dependencies**:
  - **`private final UserDetailsService userDetailsService;`**: Injected service to load user details during authentication.

- **Beans Defined**:
  1. **`AuthenticationProvider` Bean**:
     - **Type**: `DaoAuthenticationProvider`
     - **Purpose**: An `AuthenticationProvider` that uses a `UserDetailsService` and a `PasswordEncoder` to authenticate users.
     - **Configuration**:
       - **`setUserDetailsService(userDetailsService)`**: Sets the service to load user details.
       - **`setPasswordEncoder(passwordEncoder())`**: Sets the password encoder for validating passwords.
  
  2. **`PasswordEncoder` Bean**:
     - **Type**: `BCryptPasswordEncoder`
     - **Purpose**: Encodes and verifies passwords using the BCrypt hashing algorithm.
     - **Configuration**:
       - **Default Constructor**: Uses default strength (10). You can customize the strength if needed.

### **Practical Example**

**User Registration and Authentication**:

1. **Registration**:
   - User registers with a password.
   - **Password Encoding**: `passwordEncoder.encode(request.getPassword())` hashes the password using BCrypt before saving to the database.

2. **Authentication**:
   - User submits login credentials.
   - **AuthenticationProvider** (`DaoAuthenticationProvider`) uses:
     - **`UserDetailsService`**: Loads user details by email.
     - **`PasswordEncoder`**: Compares the submitted password (after encoding) with the stored hashed password.

3. **Security Context**:
   - Upon successful authentication, Spring Security sets the authentication in the `SecurityContext`, allowing access to secured resources.

### **Customization Options**

- **Password Encoder Strength**:
  - **Default**: BCrypt with strength 10.
  - **Customization**: You can specify a different strength based on security requirements.

  **Example**:
  ```java
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder(12); // Strength 12
  }
  ```

- **Alternative Password Encoders**:
  - Spring Security supports various password encoders like `Pbkdf2PasswordEncoder`, `Argon2PasswordEncoder`, etc.
  - **Usage**: Replace `BCryptPasswordEncoder` with the desired encoder.

  **Example**:
  ```java
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new Argon2PasswordEncoder();
  }
  ```

---

## 9. **JWT Service**

### **Code Overview**

```java
package com.wchamara.book.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.expiration}")
    private Long jwtExpirationInMs;

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);


    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSignedKey()).build().parseSignedClaims(token).getPayload();
    }

    private String generateToken(Map<String, Object> claims, UserDetails userDetails) {

        return buildToke(claims, userDetails, jwtExpirationInMs);
    }

    private String buildToke(Map<String, Object> extraClaims, UserDetails userDetails, Long jwtExpirationInMs) {
        var authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .claim("authorities", authorities)
                .signWith(getSignedKey())
                .compact();

    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private SecretKey getSignedKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
```

### **Explanation**

- **Annotations**:
  - **`@Service`**: Marks this class as a Spring service component.

- **Configuration Properties**:
  - **`@Value("${application.security.jwt.expiration}")`**:
    - **Purpose**: Injects the JWT expiration time from `application.yml`.
    - **Value Example**: `86400000` milliseconds (24 hours).
  - **`@Value("${application.security.jwt.secret-key}")`**:
    - **Purpose**: Injects the JWT secret key from `application.yml`.
    - **Value Example**: A Base64-encoded secret string.

- **Methods**:
  1. **`generateToken(UserDetails userDetails)`**:
     - **Purpose**: Generates a JWT token for the given user.
     - **Usage**: Called during authentication to issue a JWT.
  
  2. **`extractUsername(String token)`**:
     - **Purpose**: Extracts the username (subject) from the JWT.
     - **Usage**: Used in JWT validation and retrieval.

  3. **`validateToken(String token, UserDetails userDetails)`**:
     - **Purpose**: Validates the JWT token against the user details.
     - **Checks**:
       - The token's subject matches the user's username.
       - The token is not expired.
  
  4. **`extractClaim(String token, Function<Claims, T> claimsResolver)`**:
     - **Purpose**: Extracts a specific claim from the JWT using a resolver function.
     - **Generic**: Can extract any claim based on the provided function.

  5. **`extractAllClaims(String token)`**:
     - **Purpose**: Parses the JWT and retrieves all claims.
     - **Implementation**:
       - **`Jwts.parser()`**: Creates a JWT parser.
       - **`verifyWith(getSignedKey())`**: Sets the signing key for signature verification.
       - **`parseSignedClaims(token)`**: Parses the token and retrieves the claims.

  6. **`buildToke` (Typo: Should be `buildToken`)**:
     - **Purpose**: Constructs and signs the JWT with claims and user information.
     - **Parameters**:
       - **`extraClaims`**: Additional claims to include in the token.
       - **`userDetails`**: User information to include (e.g., username).
       - **`jwtExpirationInMs`**: Token expiration time.
     - **Implementation**:
       - **`setSubject(userDetails.getUsername())`**: Sets the token subject to the user's username.
       - **`setIssuedAt`**: Sets the issuance time.
       - **`setExpiration`**: Sets the token's expiration time.
       - **`claim("authorities", authorities)`**: Adds the user's authorities/roles as a claim.
       - **`signWith(getSignedKey())`**: Signs the token using the secret key.
       - **`compact()`**: Builds the JWT as a compact string.

  7. **`isTokenExpired(String token)`**:
     - **Purpose**: Checks if the token has expired.
     - **Usage**: Used in token validation.

  8. **`extractExpiration(String token)`**:
     - **Purpose**: Extracts the expiration date from the token.

  9. **`getSignedKey()`**:
     - **Purpose**: Retrieves the signing key from the Base64-encoded secret.
     - **Implementation**:
       - **`Decoders.BASE64.decode(secretKey)`**: Decodes the Base64-encoded secret.
       - **`Keys.hmacShaKeyFor(keyBytes)`**: Generates a `SecretKey` suitable for HMAC-SHA algorithms.

### **Potential Issues and Corrections**

1. **Method Typo**:
   - **Current**: `buildToke`
   - **Correction**: Rename to `buildToken` for clarity and to avoid confusion.

2. **JWT Parsing Method**:
   - **Current**:
     ```java
     return Jwts.parser().verifyWith(getSignedKey()).build().parseSignedClaims(token).getPayload();
     ```
   - **Potential Issue**: Earlier, the user faced an issue with `parseClaimsJws` not being resolved. In JJWT `0.12.6`, the correct method is `parseClaimsJws`.
   - **Correction**: Update to use `parseClaimsJws` appropriately.

   **Corrected `extractAllClaims` Method**:
   ```java
   private Claims extractAllClaims(String token) {
       return Jwts.parserBuilder()
                  .setSigningKey(getSignedKey())
                  .build()
                  .parseClaimsJws(token)
                  .getBody();
   }
   ```

3. **Security of Secret Key**:
   - **Current**: `secret-key: secret`
   - **Issue**: A short and simple secret key is insecure.
   - **Recommendation**: Use a sufficiently long and random Base64-encoded secret key.

   **Example**:
   ```yaml
   application:
     security:
       jwt:
         secret-key: c2VjdXJlLXNlY3JldC1rZXktMTIzNDU2Nzg5MA== # Base64-encoded 256-bit key
         expiration: 86400000
         token-prefix: Bearer
         header-string: Authorization
   ```

   **Generating a Secure Secret Key**:
   ```java
   import io.jsonwebtoken.io.Encoders;
   import io.jsonwebtoken.security.Keys;

   // Generate a 256-bit key
   SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
   String base64Key = Encoders.BASE64.encode(key.getEncoded());
   System.out.println("Base64-encoded secret key: " + base64Key);
   ```

4. **Enhancing Token Claims**:
   - **Current**: Only `subject` and `authorities` are included.
   - **Recommendation**: Consider adding other claims like `issuer`, `audience`, `issuedAt`, etc., based on application requirements.

   **Example**:
   ```java
   return Jwts
           .builder()
           .setClaims(extraClaims)
           .setSubject(userDetails.getUsername())
           .setIssuer("your-app-name")
           .setAudience("your-app-audience")
           .setIssuedAt(new Date(System.currentTimeMillis()))
           .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
           .claim("authorities", authorities)
           .signWith(getSignedKey(), SignatureAlgorithm.HS256)
           .compact();
   ```

### **Complete Corrected `JwtService` Example**

```java
@Service
public class JwtService {

    @Value("${application.security.jwt.expiration}")
    private Long jwtExpirationInMs;

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(getSignedKey())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    private String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return buildToken(claims, userDetails, jwtExpirationInMs);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, Long jwtExpirationInMs) {
        var authorities = userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .toList();

        return Jwts.builder()
                   .setClaims(extraClaims)
                   .setSubject(userDetails.getUsername())
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                   .claim("authorities", authorities)
                   .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                   .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private SecretKey getSignedKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
```

---

## 10. **Application Configuration (`application.yml`)**

### **Code Overview**

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
application:
  security:
    jwt:
      secret-key: secret
      expiration: 86400000
      token-prefix: Bearer
      header-string: Authorization
```

### **Explanation**

This YAML configuration file sets up various aspects of the Spring Boot application, including the datasource, JPA, mail, and JWT security settings.

#### **1. Spring Datasource Configuration**

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/book_social_network
    username: username
    password: password
    driver-class-name: org.postgresql.Driver
```

- **Purpose**: Configures the connection to the PostgreSQL database.
- **Parameters**:
  - **`url`**: JDBC URL for the PostgreSQL database.
  - **`username` & `password`**: Credentials for database access.
  - **`driver-class-name`**: Specifies the PostgreSQL JDBC driver.

**Security Note**: Avoid hard-coding sensitive information like `username` and `password`. Use environment variables or externalized configuration for production environments.

#### **2. JPA Configuration**

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

- **`ddl-auto: update`**:
  - **Purpose**: Automatically updates the database schema based on entity changes.
  - **Options**:
    - **`validate`**: Validates the schema, making no changes.
    - **`update`**: Updates the schema without dropping existing data.
    - **`create`**: Creates the schema, dropping existing data.
    - **`create-drop`**: Creates the schema on startup and drops it on shutdown.
  - **Recommendation**: Use `update` for development and consider manual migrations (e.g., Flyway, Liquibase) for production.

- **`show-sql: false`**:
  - **Purpose**: Disables logging of SQL statements.
  - **Recommendation**: Set to `true` for debugging purposes during development.

- **`format_sql: true`**:
  - **Purpose**: Formats SQL statements for better readability in logs.

- **`database-platform`**:
  - **Purpose**: Specifies the Hibernate dialect for PostgreSQL, ensuring correct SQL generation.

#### **3. Mail Configuration**

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

- **Purpose**: Configures the SMTP server for sending emails.
- **Parameters**:
  - **`host` & `port`**: SMTP server details.
  - **`username` & `password`**: Credentials for SMTP authentication.
  - **`properties.mail.smtp`**:
    - **`trust: "*"`**: Trusts all SMTP hosts. **Caution**: Insecure; specify trusted hosts explicitly.
    - **`auth: true`**: Enables SMTP authentication.
    - **`starttls.enable: true`**: Enables TLS encryption for SMTP.
    - **Timeouts**:
      - **`connectiontimeout`**: Maximum time to establish a connection.
      - **`timeout`**: Maximum time to wait for a response.
      - **`writetimeout`**: Maximum time to wait for data writing.

**Use Case**: Configured for a local SMTP server (e.g., [MailHog](https://github.com/mailhog/MailHog)) during development.

#### **4. JWT Security Configuration**

```yaml
application:
  security:
    jwt:
      secret-key: secret
      expiration: 86400000
      token-prefix: Bearer
      header-string: Authorization
```

- **`secret-key`**:
  - **Purpose**: The secret key used to sign and verify JWTs.
  - **Security Note**: 
    - **Current Value**: `"secret"` is insecure.
    - **Recommendation**: Use a strong, randomly generated Base64-encoded key of sufficient length (at least 256 bits for HS256).

- **`expiration`**:
  - **Purpose**: JWT token expiration time in milliseconds.
  - **Value**: `86400000` milliseconds equals 24 hours.

- **`token-prefix`**:
  - **Purpose**: Prefix for the JWT in the `Authorization` header.
  - **Value**: `"Bearer"` (standard convention).

- **`header-string`**:
  - **Purpose**: The HTTP header used to pass the JWT.
  - **Value**: `"Authorization"` (standard convention).

**Enhanced Configuration Example**:

```yaml
application:
  security:
    jwt:
      secret-key: c2VjdXJlLXNlY3JldC1rZXktMTIzNDU2Nzg5MA== # Base64-encoded 256-bit key
      expiration: 86400000
      token-prefix: Bearer
      header-string: Authorization
```

**Generating a Secure Secret Key**:

```java
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

// Generate a 256-bit (32-byte) key
SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
String base64Key = Encoders.BASE64.encode(key.getEncoded());
System.out.println("Base64-encoded secret key: " + base64Key);
```

**Output Example**:

```
Base64-encoded secret key: c2VjdXJlLXNlY3JldC1rZXktMTIzNDU2Nzg5MA==
```

**Security Note**: Store the secret key securely, avoiding hard-coding in configuration files. Use environment variables or secret management services.

---

## 11. **Integration and Workflow**

Let's map out how the components interact to facilitate user registration, authentication, and authorization.

### **1. User Registration Flow**

**Steps**:

1. **Client Registration Request**:
   - **Endpoint**: `POST /auth/register`
   - **Payload**:
     ```json
     {
         "firstname": "Alice",
         "lastname": "Wonderland",
         "email": "alice@example.com",
         "password": "SecureP@ssw0rd!"
     }
     ```

2. **AuthenticationController**:
   - Receives the request.
   - Validates the payload using `@Valid` annotations in `RegistrationRequest`.
   - Delegates to `AuthenticationService.register(request)`.

3. **AuthenticationService**:
   - **User Existence Check**: Uses `UserRepository.findByEmail(email)` to ensure the email isn't already registered.
   - **Role Assignment**: Fetches the "USER" role using `RoleRepository.findByName("USER")`.
   - **Password Encryption**: Hashes the password using `PasswordEncoder`.
   - **User Creation**: Creates a new `User` entity with `enabled = false` and assigns the "USER" role.
   - **Save User**: Persists the user to the database.
   - **Token Generation**: Creates a verification `Token` using `TokenService`.
   - **Email Sending**: Sends a verification email with the token using `EmailService`.

4. **Response**:
   - **Status**: `202 Accepted`
   - **Message**: "User registered successfully. Please check your email for verification."

### **2. Email Verification Flow**

**Steps**:

1. **User Clicks Verification Link**:
   - **Link Structure**: `GET /api/auth/verify?token=<JWT_TOKEN>`
   - **Assumption**: The email sent to the user contains a link like the above.

2. **Verification Endpoint**:
   - **Controller**: Typically handled by another endpoint in `AuthenticationController` (not provided in the snippets).
   - **Action**:
     - Extracts the token from the request.
     - Validates the token using `JwtService`.
     - Retrieves the associated user.
     - Sets `enabled = true` in the user entity.
     - Saves the updated user.

3. **SecurityContext Update**:
   - Upon successful verification, the user's account is active, allowing authentication.

### **3. User Authentication Flow**

**Steps**:

1. **Client Login Request**:
   - **Endpoint**: `POST /auth/login`
   - **Payload**:
     ```json
     {
         "email": "alice@example.com",
         "password": "SecureP@ssw0rd!"
     }
     ```

2. **AuthenticationController**:
   - Receives the request.
   - Delegates to `AuthenticationService.login(request)` (implementation not provided).

3. **AuthenticationService**:
   - **Authentication Logic**:
     - Authenticates the user using Spring Security's `AuthenticationManager`.
     - Generates a JWT token upon successful authentication.
   - **Response**:
     - Returns the JWT token to the client.

4. **Client Receives Token**:
   - **Usage**: Includes the JWT in the `Authorization` header for subsequent requests.

   **Example**:
   ```
   Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
   ```

5. **Secured Endpoint Access**:
   - **Filter Processing**: `JwtFilter` intercepts the request, extracts, and validates the JWT.
   - **Authentication**: Sets the authentication in the `SecurityContext` if valid.
   - **Controller Handling**: Proceeds to handle the secured request.

### **4. Accessing Secured Endpoints**

**Scenario**: Accessing `/api/books` with a valid JWT.

**Process**:

1. **HTTP Request**:
   ```
   GET /api/books
   Authorization: Bearer <JWT_TOKEN>
   ```

2. **Security Filter Chain**:
   - **CORS Handling**.
   - **CSRF Disabled**.
   - **Authorization Check**:
     - `/api/books` is not in the permitted paths; requires authentication.
   
3. **JWT Filter (`JwtFilter`)**:
   - Extracts the JWT.
   - Validates the token and extracts user details.
   - Sets the authentication in the `SecurityContext`.
   
4. **Controller Processing**:
   - Authenticated user accesses `/api/books`.
   - Controller processes the request and returns data.

---

## 12. **Best Practices and Recommendations**

Implementing a robust authentication system requires adherence to best practices to ensure security, maintainability, and scalability. Below are key recommendations based on the provided code.

### **1. Secure Secret Key Management**

- **Avoid Hard-Coding Secrets**: Never hard-code secret keys or sensitive information in your source code or configuration files.

- **Use Environment Variables or Secret Management Services**:
  - **Environment Variables**: Set secrets as environment variables and reference them in your configuration.
  - **Secret Management**: Utilize services like **HashiCorp Vault**, **AWS Secrets Manager**, or **Azure Key Vault** for enhanced security.

- **Example Using Environment Variables**:

  **`application.yml`:**
  ```yaml
  application:
    security:
      jwt:
        secret-key: ${JWT_SECRET_KEY}
        expiration: 86400000
        token-prefix: Bearer
        header-string: Authorization
  ```

  **Setting Environment Variable:**
  ```bash
  export JWT_SECRET_KEY=c2VjdXJlLXNlY3JldC1rZXktMTIzNDU2Nzg5MA==
  ```

### **2. Password Security**

- **Use Strong Password Encoders**:
  - **Current**: `BCryptPasswordEncoder`
  - **Recommendation**: Continue using `BCryptPasswordEncoder` as it's robust against brute-force attacks.

- **Password Policies**:
  - Enforce strong password policies (minimum length, complexity, etc.) using validation annotations, as done in `RegistrationRequest`.

- **Example**:
  ```java
  @Size(min = 12, message = "Password must be at least 12 characters long")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain lowercase, uppercase letters, and digits")
  private String password;
  ```

### **3. JWT Token Management**

- **Secure Secret Key**: Ensure the secret key is long and random enough to prevent brute-force attacks.

- **Token Expiration**:
  - **Short-Lived Tokens**: Use short expiration times to minimize risk if a token is compromised.
  - **Refresh Tokens**: Implement refresh tokens to allow users to obtain new access tokens without re-authenticating.

- **Claim Validation**:
  - **Issuer (`iss`)**: Validate the token issuer.
  - **Audience (`aud`)**: Validate the intended audience of the token.

- **Example**:
  ```java
  Jwts.builder()
      .setSubject(userDetails.getUsername())
      .setIssuer("your-app-name")
      .setAudience("your-app-audience")
      // Other claims
      .signWith(getSignedKey(), SignatureAlgorithm.HS256)
      .compact();
  ```

### **4. Exception Handling in JWT Filter**

- **Graceful Failure**: Handle exceptions during JWT parsing and validation to provide meaningful responses.

- **Example**:
  ```java
  @Override
  protected void doFilterInternal(
          @NonNull HttpServletRequest request,
          @NonNull HttpServletResponse response,
          @NonNull FilterChain filterChain) throws ServletException, IOException {

      try {
          // Existing JWT processing logic
      } catch (JwtException e) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          response.getWriter().write("Invalid or expired JWT token");
          return;
      }

      filterChain.doFilter(request, response);
  }
  ```

### **5. Logging and Monitoring**

- **Audit Logs**: Log authentication attempts, both successful and failed, for auditing purposes.
  
- **Monitor Suspicious Activities**: Detect and respond to unusual patterns, such as multiple failed login attempts.

- **Example Using SLF4J**:
  ```java
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;

  @Service
  public class JwtFilter extends OncePerRequestFilter {
      private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

      // Inside doFilterInternal
      catch (JwtException e) {
          logger.warn("JWT validation failed: {}", e.getMessage());
          // Handle response
      }
  }
  ```

### **6. Secure CORS Configuration**

- **Restrict Origins**: Specify allowed origins instead of allowing all.

- **Example**:
  ```java
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(Arrays.asList("https://your-domain.com"));
      configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
      configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
      configuration.setExposedHeaders(Arrays.asList("Authorization"));
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
  }
  ```

### **7. Method-Level Security**

- **Annotations**: Utilize method-level security annotations like `@Secured`, `@PreAuthorize`, or `@PostAuthorize` to enforce access controls on specific methods.

- **Example**:
  ```java
  @Service
  public class BookService {

      @Secured("ROLE_ADMIN")
      public void deleteBook(Integer bookId) {
          // Deletion logic
      }
  }
  ```

### **8. API Documentation**

- **Swagger/OpenAPI**: Utilize Swagger annotations (as seen with `@Tag`) to generate comprehensive API documentation.

- **Configuration**:
  - **Include Swagger UI**: Ensure Swagger UI is properly configured and secured.
  - **Example**: The permitted paths in `SecurityConfig` include Swagger endpoints.

### **9. Database Migrations**

- **Use Migration Tools**: Employ tools like **Flyway** or **Liquibase** for managing database schema changes, ensuring version control and consistency across environments.

- **Example with Flyway**:

  **`pom.xml` Dependency**:
  ```xml
  <dependency>
      <groupId>org.flywaydb</groupId>
      <artifactId>flyway-core</artifactId>
  </dependency>
  ```

  **Migration Script (`db/migration/V1__Initial_Setup.sql`)**:
  ```sql
  CREATE TABLE roles (
      id SERIAL PRIMARY KEY,
      name VARCHAR(50) UNIQUE NOT NULL,
      created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      last_modified_date_time TIMESTAMP
  );

  CREATE TABLE users (
      id SERIAL PRIMARY KEY,
      firstname VARCHAR(50) NOT NULL,
      lastname VARCHAR(50) NOT NULL,
      email VARCHAR(100) UNIQUE NOT NULL,
      password VARCHAR(100) NOT NULL,
      account_locked BOOLEAN DEFAULT FALSE,
      enabled BOOLEAN DEFAULT FALSE,
      created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      last_modified_date_time TIMESTAMP
  );

  CREATE TABLE tokens (
      id SERIAL PRIMARY KEY,
      token VARCHAR(255) NOT NULL,
      created_at TIMESTAMP NOT NULL,
      expires_at TIMESTAMP NOT NULL,
      validated_at TIMESTAMP,
      user_id INTEGER NOT NULL,
      FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
  );

  CREATE TABLE user_roles (
      user_id INTEGER NOT NULL,
      role_id INTEGER NOT NULL,
      PRIMARY KEY (user_id, role_id),
      FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
      FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
  );
  ```

### **10. Testing**

- **Unit Tests**: Write unit tests for services, ensuring individual components behave as expected.

- **Integration Tests**: Test the interaction between components, such as controllers and services, with a real database (use in-memory databases like H2 for testing).

- **Example Unit Test for `JwtService`**:

  ```java
  @ExtendWith(SpringExtension.class)
  @SpringBootTest
  public class JwtServiceTest {

      @Autowired
      private JwtService jwtService;

      @Autowired
      private UserRepository userRepository;

      @Autowired
      private PasswordEncoder passwordEncoder;

      @Test
      public void testGenerateAndValidateToken() {
          User user = User.builder()
                  .firstname("Test")
                  .lastname("User")
                  .email("test.user@example.com")
                  .password(passwordEncoder.encode("TestPass123"))
                  .enabled(true)
                  .build();
          userRepository.save(user);

          UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                  user.getEmail(),
                  user.getPassword(),
                  List.of(new SimpleGrantedAuthority("ROLE_USER"))
          );

          String token = jwtService.generateToken(userDetails);
          assertNotNull(token);

          String extractedUsername = jwtService.extractUsername(token);
          assertEquals(user.getEmail(), extractedUsername);

          boolean isValid = jwtService.validateToken(token, userDetails);
          assertTrue(isValid);
      }
  }
  ```

### **11. Exception Handling and Global Error Responses**

- **Controller Advice**: Implement a `@ControllerAdvice` to handle exceptions globally, ensuring consistent error responses.

- **Example**:

  ```java
  @ControllerAdvice
  public class GlobalExceptionHandler {

      @ExceptionHandler(UsernameNotFoundException.class)
      public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
          return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
      }

      @ExceptionHandler(IllegalArgumentException.class)
      public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
          return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
      }

      // Handle other exceptions
  }
  ```

### **12. Ensuring HTTPS**

- **Secure Transport**: Always serve your application over HTTPS to encrypt data in transit, especially JWT tokens and sensitive user information.

- **Configuration**: 
  - Use SSL certificates.
  - Configure your server (e.g., Tomcat) to support HTTPS.

- **Example in `application.yml`**:

  ```yaml
  server:
    ssl:
      key-store: classpath:keystore.jks
      key-store-password: changeit
      key-password: changeit
  ```

---

## 13. **Conclusion**

The provided code snippets collectively establish a robust JWT-based authentication system within a Spring Boot application. Here's a summary of the key components and their roles:

- **Project Dependencies**: Configured JJWT library for JWT operations, ensuring version consistency.

- **Authentication Controller**: Handles user registration requests, delegating to the `AuthenticationService`.

- **Authentication Service**: Manages the business logic for user registration, including user creation, role assignment, token generation, and email sending.

- **Registration Request DTO**: Defines the structure and validation constraints for user registration data.

- **Security Configuration**: Sets up Spring Security with JWT-based authentication, defining secured and permitted endpoints, session management, and integrating the JWT filter.

- **JWT Filter**: Intercepts incoming requests, extracts and validates JWT tokens, and sets the authentication context.

- **User Details Service Implementation**: Loads user details from the database based on the provided username (email), enabling Spring Security to authenticate users.

- **Beans Configuration**: Defines essential beans like `AuthenticationProvider` and `PasswordEncoder` for authentication processes.

- **JWT Service**: Handles JWT generation, extraction, and validation, ensuring secure token management.

- **Application Configuration**: Configures datasource, JPA, mail settings, and JWT security parameters via `application.yml`.

### **Key Takeaways and Best Practices**:

1. **Security First**:
   - Protect sensitive data like secret keys and user passwords.
   - Use strong, randomly generated secret keys for JWT signing.
   - Enforce robust password policies and use secure hashing algorithms.

2. **Validation and Exception Handling**:
   - Implement comprehensive validation on user inputs.
   - Handle exceptions gracefully, providing meaningful feedback to clients.

3. **Modular Design**:
   - Separate concerns by organizing code into controllers, services, repositories, and security configurations.
   - Utilize Spring's dependency injection and Lombok to reduce boilerplate and enhance maintainability.

4. **Testing and Monitoring**:
   - Write unit and integration tests to ensure functionality.
   - Monitor authentication processes and log relevant events for auditing and troubleshooting.

5. **Documentation and API Clarity**:
   - Use Swagger/OpenAPI annotations to document APIs, aiding both development and integration efforts.

6. **Use of Modern Libraries and Practices**:
   - Leverage Spring Security's powerful features for authentication and authorization.
   - Utilize JJWT's latest APIs for secure and efficient JWT handling.

7. **Performance and Scalability**:
   - Optimize security filters to handle high traffic efficiently.
   - Implement caching strategies if necessary for user details or tokens.

By adhering to these practices and continuously refining your authentication system based on evolving requirements and security standards, you can build a secure, efficient, and user-friendly application.

---

Feel free to ask if you need further clarification on any component or additional examples to enhance your understanding!