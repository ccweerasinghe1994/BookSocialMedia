# 📗User,Token and Role Entities

Certainly! Let's delve deeply into the provided Java code snippets, which form the backbone of a Spring Boot application centered around user management, roles, and token handling. We'll explore each component, understand their interactions, and provide examples to illustrate their functionality.

---

## 1. **Main Application Class**

### **Code Overview**

```java
package com.wchamara.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookNetworkApplication.class, args);
    }

}
```

### **Explanation**

- **`@SpringBootApplication`**: This is a convenience annotation that adds all of the following:
  - **`@Configuration`**: Tags the class as a source of bean definitions.
  - **`@EnableAutoConfiguration`**: Enables Spring Boot’s auto-configuration mechanism.
  - **`@ComponentScan`**: Enables component scanning so that the web controller classes and other components you create will be automatically discovered and registered as beans in Spring’s application context.

- **`@EnableJpaAuditing`**: Enables JPA Auditing in the application. This allows the use of annotations like `@CreatedDate` and `@LastModifiedDate` to automatically populate auditing fields in entities.

- **`main` Method**: The entry point of the Spring Boot application. It bootstraps the application, starting the embedded server and initializing the Spring context.

### **Example**

When you run this class, Spring Boot initializes the application context, sets up all the necessary configurations, scans for components (like controllers, services, repositories), and starts the embedded server (e.g., Tomcat) to listen for incoming HTTP requests.

---

## 2. **Role Entity**

### **Code Overview**

```java
package com.wchamara.book.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wchamara.book.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String name;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDateTime;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;
}
```

### **Explanation**

- **Lombok Annotations**:
  - **`@Getter` & `@Setter`**: Automatically generate getter and setter methods for all fields.
  - **`@Builder`**: Implements the builder pattern for the class, facilitating easier object creation.
  - **`@AllArgsConstructor` & `@NoArgsConstructor`**: Generate constructors with all arguments and a no-argument constructor, respectively.

- **JPA Annotations**:
  - **`@Entity`**: Marks the class as a JPA entity mapped to a database table.
  - **`@Id` & `@GeneratedValue`**: Specifies the primary key and its generation strategy.
  - **`@Column(unique = true)`**: Ensures that the `name` field is unique in the database.
  - **`@ManyToMany(mappedBy = "roles")`**: Defines a many-to-many relationship with the `User` entity, indicating that the `users` field is the inverse side of the relationship.

- **Auditing**:
  - **`@CreatedDate`**: Automatically populates the `createdDate` field when the entity is first persisted.
  - **`@LastModifiedDate`**: Automatically updates the `lastModifiedDateTime` field whenever the entity is updated.
  - **`@EntityListeners(AuditingEntityListener.class)`**: Registers the auditing entity listener to handle the auditing annotations.

- **`@JsonIgnore`**: Prevents the `users` list from being serialized into JSON, avoiding potential infinite recursion issues during serialization.

### **Example Usage**

**Creating a Role:**

```java
Role adminRole = Role.builder()
    .name("ADMIN")
    .build();
roleRepository.save(adminRole);
```

**Associating Roles with Users:**

Suppose you have a `User` entity and you want to assign the "ADMIN" role to a user.

```java
User user = userRepository.findById(userId).orElseThrow();
Role adminRole = roleRepository.findByName("ADMIN").orElseThrow();
user.getRoles().add(adminRole);
userRepository.save(user);
```

This establishes a many-to-many relationship between the `User` and `Role` entities.

---

## 3. **Empty Code Block**

```java
```java

```

### **Explanation**

This code block is empty. It might have been included by mistake or intended for future content. If there's specific content you intended to share or discuss here, please provide it for further assistance.

---

## 4. **Token Entity**

### **Code Overview**

```java
package com.wchamara.book.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue
    private Integer id;

    private String token;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "userId")
    private User user;
}
```

### **Explanation**

- **Lombok Annotations**:
  - Similar to the `Role` entity, Lombok is used to generate boilerplate code like getters, setters, constructors, and the builder pattern.

- **JPA Annotations**:
  - **`@Entity`**: Marks the class as a JPA entity.
  - **`@Id` & `@GeneratedValue`**: Define the primary key and its generation strategy.
  - **`@ManyToOne`**: Establishes a many-to-one relationship with the `User` entity, meaning multiple tokens can be associated with a single user.
  - **`@JoinColumn`**: Specifies the foreign key column (`userId`) in the `Token` table that references the `User` entity. The `nullable = false` constraint ensures that every token is associated with a user.

- **Fields**:
  - **`token`**: Represents the actual token string (e.g., JWT, UUID).
  - **`createdAt`**: Timestamp when the token was created.
  - **`expiresAt`**: Timestamp when the token expires.
  - **`validatedAt`**: Timestamp when the token was validated or used.

### **Example Usage**

**Generating and Saving a Token:**

```java
User user = userRepository.findById(userId).orElseThrow();
String tokenString = UUID.randomUUID().toString();
LocalDateTime now = LocalDateTime.now();

Token token = Token.builder()
    .token(tokenString)
    .createdAt(now)
    .expiresAt(now.plusHours(24))
    .user(user)
    .build();

tokenRepository.save(token);
```

**Validating a Token:**

```java
Optional<Token> optionalToken = tokenRepository.findByToken(tokenString);
if (optionalToken.isPresent()) {
    Token token = optionalToken.get();
    if (token.getExpiresAt().isAfter(LocalDateTime.now()) && token.getValidatedAt() == null) {
        token.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(token);
        // Proceed with authentication or other logic
    } else {
        // Token is expired or already validated
    }
} else {
    // Token does not exist
}
```

---

## 5. **User Entity**

### **Code Overview**

```java
package com.wchamara.book.user;

import com.wchamara.book.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
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
        return false;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
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
        return false;
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

### **Explanation**

- **Lombok Annotations**:
  - **`@Getter` & `@Setter`**: Generate getters and setters.
  - **`@Builder`**: Implements the builder pattern.
  - **`@AllArgsConstructor` & `@NoArgsConstructor`**: Generate all-argument and no-argument constructors.

- **JPA Annotations**:
  - **`@Entity`**: Marks the class as a JPA entity.
  - **`@Table(name = "_users")`**: Specifies the table name as `_users` to avoid conflicts with reserved keywords like `USER` in some databases.
  - **`@Id` & `@GeneratedValue`**: Define the primary key and its generation strategy.
  - **`@Column(unique = true)`**: Ensures the `email` field is unique.
  - **`@ManyToMany(fetch = FetchType.EAGER)`**: Establishes a many-to-many relationship with `Role`. The `EAGER` fetch type ensures that roles are loaded immediately with the user, which is essential for authentication.
  - **`@EntityListeners(AuditingEntityListener.class)`**: Enables auditing for fields like `createdDate` and `lastModifiedDateTime`.

- **Auditing**:
  - **`@CreatedDate`**: Automatically sets the `createdDate` when the user is first persisted.
  - **`@LastModifiedDate`**: Updates the `lastModifiedDateTime` whenever the user entity is updated.

- **Spring Security Interfaces**:
  - **`UserDetails`**: Provides core user information to Spring Security.
  - **`Principal`**: Represents the currently authenticated user.

- **Methods Implemented from `UserDetails` and `Principal`**:
  - **`getName()`**: Returns the user's email as the principal name.
  - **`getAuthorities()`**: Translates the user's roles into Spring Security's `GrantedAuthority` objects.
  - **`getPassword()` & `getUsername()`**: Return the user's password and email, respectively.
  - **`isAccountNonExpired()`, `isAccountNonLocked()`, `isCredentialsNonExpired()`**: These methods currently return `false`, which implies:
    - **`isAccountNonExpired()`**: The account is expired.
    - **`isAccountNonLocked()`**: The account is locked.
    - **`isCredentialsNonExpired()`**: The credentials are expired.
    
    **Note**: Returning `false` for these methods will prevent the user from authenticating successfully. Typically, these should be dynamically determined based on user status.

  - **`isEnabled()`**: Returns the `enabled` field, indicating whether the user is active.

- **Custom Method**:
  - **`getFullName()`**: Concatenates the `firstname` and `lastname` to provide the user's full name.

### **Potential Issues and Recommendations**

1. **Authentication Blocked**:
   - **Issue**: The methods `isAccountNonExpired()`, `isAccountNonLocked()`, and `isCredentialsNonExpired()` all return `false`.
   - **Consequence**: Users will not be able to authenticate because Spring Security checks these flags and requires them to be `true` for successful authentication.
   - **Recommendation**: Modify these methods to reflect the actual status of the user. For example:

     ```java
     @Override
     public boolean isAccountNonExpired() {
         // Implement logic to determine if the account is non-expired
         return true; // or based on a field
     }

     @Override
     public boolean isAccountNonLocked() {
         // Implement logic to determine if the account is non-locked
         return !accountLocked;
     }

     @Override
     public boolean isCredentialsNonExpired() {
         // Implement logic to determine if credentials are non-expired
         return true; // or based on a field
     }
     ```

2. **Password Handling**:
   - **Issue**: The `password` field is stored as plain text.
   - **Recommendation**: Always store passwords in an encrypted or hashed form. Use Spring Security’s `PasswordEncoder` to hash passwords before saving them to the database.

3. **EAGER Fetching**:
   - **Consideration**: Using `FetchType.EAGER` for roles means that every time a user is fetched, all associated roles are also fetched. While this is necessary for authentication, be cautious of performance implications if the number of roles is large.

### **Example Usage**

**Creating a New User:**

```java
Role userRole = roleRepository.findByName("USER").orElseThrow();

User newUser = User.builder()
    .firstname("John")
    .lastname("Doe")
    .dateOfBirth(LocalDateTime.of(1990, 1, 1, 0, 0))
    .email("john.doe@example.com")
    .password(passwordEncoder.encode("securePassword"))
    .accountLocked(false)
    .enabled(true)
    .roles(List.of(userRole))
    .build();

userRepository.save(newUser);
```

**Authenticating a User:**

When a user attempts to log in, Spring Security uses the `UserDetailsService` to load the user by email, then checks the credentials and the flags (`isAccountNonLocked`, `isEnabled`, etc.) to determine if authentication should succeed.

---

## 6. **Interconnections and Application Flow**

### **Entity Relationships**

1. **User ↔ Role (Many-to-Many)**:
   - A user can have multiple roles (e.g., USER, ADMIN).
   - A role can be assigned to multiple users.
   - Managed via a join table (automatically handled by JPA unless specified otherwise).

2. **User ↔ Token (One-to-Many)**:
   - A user can have multiple tokens (e.g., for password resets, email verification).
   - Each token is associated with one user.

### **Application Flow Example**

**Scenario**: A user registers, gets assigned a role, and receives a token for email verification.

1. **Registration**:
   - User submits registration details (firstname, lastname, email, password).
   - Password is hashed using `PasswordEncoder`.
   - A new `User` entity is created with `enabled = false`.
   - The "USER" role is assigned to the user.
   - The user is saved to the database.

2. **Email Verification Token Generation**:
   - A `Token` is generated with a unique token string, `createdAt`, `expiresAt`, and associated with the user.
   - The token is saved to the database.
   - An email is sent to the user containing the verification link with the token.

3. **Email Verification**:
   - User clicks the verification link.
   - The application retrieves the `Token` by the token string.
   - Checks if the token is valid (not expired, not already validated).
   - Sets `validatedAt` to the current time.
   - Sets `enabled = true` on the user.
   - Saves the updated user and token.

4. **Authentication**:
   - User logs in with email and password.
   - Spring Security's `UserDetailsService` loads the user.
   - Checks credentials and flags (`isAccountNonLocked`, `isEnabled`, etc.).
   - If valid, authentication succeeds, and the user gains access based on their roles.

### **Code Integration Example**

**User Registration Service:**

```java
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserRegistrationDto registrationDto) {
        // Check if email already exists
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Encode the password
        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());

        // Assign the USER role
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));

        // Create the user
        User user = User.builder()
                .firstname(registrationDto.getFirstname())
                .lastname(registrationDto.getLastname())
                .dateOfBirth(registrationDto.getDateOfBirth())
                .email(registrationDto.getEmail())
                .password(encodedPassword)
                .accountLocked(false)
                .enabled(false) // Initially disabled until email verification
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);

        // Generate email verification token
        String tokenString = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        Token token = Token.builder()
                .token(tokenString)
                .createdAt(now)
                .expiresAt(now.plusHours(24))
                .user(user)
                .build();

        tokenRepository.save(token);

        // Send verification email (pseudo-code)
        emailService.sendVerificationEmail(user.getEmail(), tokenString);
    }
}
```

**Email Verification Controller:**

```java
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String tokenString) {
        Token token = tokenRepository.findByToken(tokenString)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token expired");
        }

        if (token.getValidatedAt() != null) {
            return ResponseEntity.badRequest().body("Token already used");
        }

        // Validate the token
        token.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(token);

        // Enable the user
        User user = token.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        return ResponseEntity.ok("Email verified successfully");
    }
}
```

---

## 7. **Security Configuration**

To integrate the `User` entity with Spring Security, you need to configure security settings. Here's a basic example:

```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService; // Custom UserDetailsService

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // For simplicity; enable in production
            .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .httpBasic(); // Or formLogin, JWT, etc.
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

**Custom `UserDetailsService` Implementation:**

```java
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                      .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user; // Since User implements UserDetails
    }
}
```

---

## 8. **Database Schema Representation**

Based on the entities, here's how the database schema might look:

1. **Table: `_users`**
   - **id**: Integer (Primary Key)
   - **firstname**: String
   - **lastname**: String
   - **date_of_birth**: Timestamp
   - **email**: String (Unique)
   - **password**: String
   - **account_locked**: Boolean
   - **enabled**: Boolean
   - **created_date**: Timestamp
   - **last_modified_date_time**: Timestamp

2. **Table: `role`**
   - **id**: Integer (Primary Key)
   - **name**: String (Unique)
   - **created_date**: Timestamp
   - **last_modified_date_time**: Timestamp

3. **Table: `token`**
   - **id**: Integer (Primary Key)
   - **token**: String
   - **created_at**: Timestamp
   - **expires_at**: Timestamp
   - **validated_at**: Timestamp
   - **user_id**: Integer (Foreign Key referencing `_users.id`)

4. **Join Table: `user_roles`** (Automatically managed by JPA for the Many-to-Many relationship)
   - **user_id**: Integer (Foreign Key referencing `_users.id`)
   - **role_id**: Integer (Foreign Key referencing `role.id`)

---

## 9. **Conclusion and Best Practices**

The provided code snippets form a solid foundation for a user management system with role-based access control and token-based operations (like email verification or password resets). Here are some best practices and recommendations to enhance the system:

1. **Password Security**:
   - Always store hashed passwords using a strong algorithm like BCrypt.
   - Implement password strength validation during registration.

2. **User Status Flags**:
   - Ensure that `isAccountNonExpired()`, `isAccountNonLocked()`, and `isCredentialsNonExpired()` methods accurately reflect the user's status to facilitate proper authentication behavior.

3. **Token Security**:
   - Use secure, random token generation methods.
   - Implement token expiration and invalidation mechanisms to prevent misuse.

4. **Role Management**:
   - Define roles clearly and manage them via an admin interface or secure backend processes.
   - Consider using hierarchical roles if the application requires complex access control.

5. **Exception Handling**:
   - Implement comprehensive exception handling to manage scenarios like duplicate emails, invalid tokens, etc., providing meaningful feedback to the client.

6. **Logging and Monitoring**:
   - Integrate logging to track important events like user registrations, logins, and token validations.
   - Monitor authentication attempts to detect and prevent potential security threats.

7. **API Documentation**:
   - Use tools like Swagger/OpenAPI to document the API endpoints, making it easier for frontend developers and third parties to integrate.

8. **Testing**:
   - Write unit and integration tests to ensure that the authentication flow, role assignments, and token validations work as expected.

By adhering to these practices and continually refining the system based on requirements and feedback, you can build a robust and secure user management system within your Spring Boot application.