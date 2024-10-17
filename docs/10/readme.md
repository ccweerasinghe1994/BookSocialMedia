# 📗Implementing /activate-account endpoint

- [📗Implementing /activate-account endpoint](#implementing-activate-account-endpoint)
  - [**1. Enhancements to the Activation Flow**](#1-enhancements-to-the-activation-flow)
    - [**a. Use Secure and Unique Tokens**](#a-use-secure-and-unique-tokens)
    - [**b. Make the Activation URL User-Friendly**](#b-make-the-activation-url-user-friendly)
    - [**c. Implement Token Expiry and Reusability**](#c-implement-token-expiry-and-reusability)
    - [**d. Transaction Management**](#d-transaction-management)
    - [**e. Improve Exception Handling and Response Feedback**](#e-improve-exception-handling-and-response-feedback)
  - [**2. Additional Recommendations**](#2-additional-recommendations)
    - [**a. Validate Input Parameters**](#a-validate-input-parameters)
    - [**b. Logging**](#b-logging)
    - [**c. Rate Limiting**](#c-rate-limiting)
    - [**d. Frontend Integration**](#d-frontend-integration)
    - [**e. Security Considerations**](#e-security-considerations)
  - [**3. Complete Example with Improvements**](#3-complete-example-with-improvements)
    - [**AuthenticationService.java**](#authenticationservicejava)
    - [**AuthenticationController.java**](#authenticationcontrollerjava)
    - [**Token.java**](#tokenjava)
    - [**Additional Configurations**](#additional-configurations)
      - [**a. Enable JPA Auditing**](#a-enable-jpa-auditing)
      - [**b. Email Service Implementation**](#b-email-service-implementation)
  - [**4. Testing the Activation Endpoint**](#4-testing-the-activation-endpoint)
    - [**a. Unit Tests**](#a-unit-tests)
    - [**b. Integration Tests**](#b-integration-tests)
  - [**5. Final Thoughts**](#5-final-thoughts)


```java
package com.wchamara.book.user;

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
package com.wchamara.book.auth;

import com.wchamara.book.email.EmailService;
import com.wchamara.book.email.EmailTemplateName;
import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import com.wchamara.book.security.JwtService;
import com.wchamara.book.user.Token;
import com.wchamara.book.user.TokenRepository;
import com.wchamara.book.user.User;
import com.wchamara.book.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

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

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        var claims = new HashMap<String, Object>();

        User user = (User) authenticate.getPrincipal();
        claims.put("fullName", user.getFullName());

        var jwtToken = jwtService.generateToken(claims, user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    //    @Transactional
    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token).orElseThrow(
                () -> new RuntimeException("Invalid token")
        );

        if (savedToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation Token expired, new token will be sent to your email");
        }

        User user = userRepository.findById(savedToken.getUser().getId()).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );

        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);

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

    @GetMapping("/activate-account")
    public void activateAccount(@RequestParam String token) throws MessagingException {
        authenticationService.activateAccount(token);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
```
Your implementation of the `/activate-account` endpoint is well-structured and covers the essential aspects of account activation, including token generation, email sending, and user enabling. However, there are several areas where you can enhance the functionality, security, and user experience. Below are detailed recommendations and improvements to ensure a robust and efficient activation process.

## **1. Enhancements to the Activation Flow**

### **a. Use Secure and Unique Tokens**

Currently, you're generating a 6-digit numerical token. While this is simple, it's susceptible to brute-force attacks and may not be sufficiently unique, especially as your user base grows.

**Recommendation:**

- **Use UUIDs or Secure Random Tokens:** Instead of a simple numerical code, use UUIDs or a more complex secure random string for tokens.

```java
import java.util.UUID;

// ...

private String generateActivationToken() {
    return UUID.randomUUID().toString();
}
```

**Advantages:**

- **Uniqueness:** UUIDs are practically unique, reducing the chance of token collisions.
- **Security:** More complex tokens are harder to guess or brute-force.

### **b. Make the Activation URL User-Friendly**

Ensure that the activation URL directs users to a meaningful page, possibly on your frontend application, which can provide a better user experience.

**Recommendation:**

- **Construct a Complete Activation Link:** Instead of just sending the token, send a full URL that the user can click to activate their account.

```java
private void sendValidationEmail(User user) throws MessagingException {
    var token = generateAndSaveActivationToken(user);
    String activationLink = activationUrl + "/activate-account?token=" + token.getToken();

    emailService.sendEmail(
            user.getEmail(),
            user.getFullName(),
            EmailTemplateName.ACTIVATE_ACCOUNT,
            activationLink,
            "Account Activation"
    );
}
```

**Note:** Ensure that `activationUrl` includes the frontend domain and the appropriate endpoint to handle the activation.

### **c. Implement Token Expiry and Reusability**

Ensure that tokens expire after a certain period and cannot be reused once the account is activated.

**Recommendation:**

- **Mark Tokens as Used:** Add a field like `validatedAt` to mark tokens as used.
- **Check Token Status Before Activation:** Ensure that the token hasn't been used already.

```java
public void activateAccount(String token) throws MessagingException {
    Token savedToken = tokenRepository.findByToken(token)
            .orElseThrow(() -> new RuntimeException("Invalid token"));

    if (savedToken.getValidatedAt() != null) {
        throw new RuntimeException("Token has already been used.");
    }

    if (savedToken.getExpiresAt().isBefore(LocalDateTime.now())) {
        sendValidationEmail(savedToken.getUser());
        throw new RuntimeException("Activation Token expired, new token will be sent to your email.");
    }

    User user = savedToken.getUser();
    user.setEnabled(true);
    userRepository.save(user);

    savedToken.setValidatedAt(LocalDateTime.now());
    tokenRepository.save(savedToken);
}
```

### **d. Transaction Management**

Ensure that the activation process is atomic to maintain data consistency.

**Recommendation:**

- **Add `@Transactional` Annotation:** This ensures that all database operations within the method are completed successfully or rolled back in case of an error.

```java
import org.springframework.transaction.annotation.Transactional;

// ...

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    // Existing code...

    @Transactional
    public void activateAccount(String token) throws MessagingException {
        // Activation logic...
    }
}
```

### **e. Improve Exception Handling and Response Feedback**

Currently, your `/activate-account` endpoint returns `void`, which doesn't provide feedback to the client. It's essential to inform the user about the activation status.

**Recommendation:**

- **Return Meaningful Responses:** Modify the controller to return `ResponseEntity` with appropriate HTTP status codes and messages.

```java
import org.springframework.web.server.ResponseStatusException;

// ...

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    // Existing endpoints...

    @GetMapping("/activate-account")
    public ResponseEntity<String> activateAccount(@RequestParam String token) {
        try {
            authenticationService.activateAccount(token);
            return ResponseEntity.ok("Account activated successfully.");
        } catch (RuntimeException | MessagingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
```

**Advantages:**

- **User Feedback:** Users receive clear messages about the activation outcome.
- **Error Handling:** Proper HTTP status codes inform the client of success or failure.

## **2. Additional Recommendations**

### **a. Validate Input Parameters**

Ensure that the token received is valid and follows the expected format to prevent potential security issues.

**Example:**

```java
import org.apache.commons.validator.routines.RegexValidator;

// ...

public void activateAccount(String token) throws MessagingException {
    RegexValidator validator = new RegexValidator("^[a-zA-Z0-9-]{36}$"); // Adjust regex based on token format
    if (!validator.isValid(token)) {
        throw new RuntimeException("Invalid token format.");
    }

    // Existing activation logic...
}
```

### **b. Logging**

Implement logging to monitor activation attempts, successes, and failures. This is crucial for debugging and security auditing.

**Example:**

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// ...

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    
    // Existing code...

    @Transactional
    public void activateAccount(String token) throws MessagingException {
        try {
            Token savedToken = tokenRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Invalid token"));

            if (savedToken.getValidatedAt() != null) {
                logger.warn("Attempt to reuse token: {}", token);
                throw new RuntimeException("Token has already been used.");
            }

            if (savedToken.getExpiresAt().isBefore(LocalDateTime.now())) {
                logger.info("Token expired for user: {}", savedToken.getUser().getEmail());
                sendValidationEmail(savedToken.getUser());
                throw new RuntimeException("Activation Token expired, new token will be sent to your email.");
            }

            User user = savedToken.getUser();
            user.setEnabled(true);
            userRepository.save(user);

            savedToken.setValidatedAt(LocalDateTime.now());
            tokenRepository.save(savedToken);

            logger.info("User activated successfully: {}", user.getEmail());
        } catch (Exception e) {
            logger.error("Activation failed for token {}: {}", token, e.getMessage());
            throw e;
        }
    }
}
```

### **c. Rate Limiting**

To prevent abuse, implement rate limiting on the activation endpoint to restrict the number of activation attempts from a single IP address or user within a specific timeframe.

**Recommendation:**

- **Use Spring Security with Rate Limiting Libraries:** Libraries like [Bucket4j](https://github.com/vladimir-bukhtoyarov/bucket4j) can be integrated to manage rate limiting.

### **d. Frontend Integration**

Ensure that the activation link directs users to a user-friendly frontend page that communicates the activation status clearly.

**Steps:**

1. **Frontend Route:** Create a route like `/activate-account` that accepts the token as a query parameter.
2. **API Call:** The frontend can call the `/auth/activate-account` endpoint and display messages based on the response.
3. **User Experience:** Show success or error messages in a user-friendly manner, possibly redirecting to the login page upon successful activation.

### **e. Security Considerations**

- **HTTPS:** Ensure that all endpoints, especially those handling sensitive operations like activation, are served over HTTPS.
- **Token Security:** Store tokens securely and consider encrypting them in the database.
- **Prevent Enumeration Attacks:** Avoid revealing whether an email is registered when sending activation tokens to prevent attackers from enumerating user accounts.

## **3. Complete Example with Improvements**

Below is a refined version of your `AuthenticationService` and `AuthenticationController` incorporating the recommended improvements.

### **AuthenticationService.java**

```java
package com.wchamara.book.auth;

import com.wchamara.book.email.EmailService;
import com.wchamara.book.email.EmailTemplateName;
import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import com.wchamara.book.security.JwtService;
import com.wchamara.book.user.Token;
import com.wchamara.book.user.TokenRepository;
import com.wchamara.book.user.User;
import com.wchamara.book.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Value("${application.mailing.frontend}")
    private String activationUrl;

    public void register(RegistrationRequest request) throws MessagingException {
        Role userRole = roleRepository.findByName("USER").orElseThrow(
                () -> new IllegalStateException("Role USER was not initialized")
        );

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("Email already registered.");
        }

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
        String activationLink = activationUrl + "/activate-account?token=" + token.getToken();

        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationLink,
                "Account Activation"
        );
    }

    private Token generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationToken();

        Token token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusHours(24)) // Extended expiry time
                .user(user)
                .build();
        return tokenRepository.save(token);
    }

    private String generateActivationToken() {
        return UUID.randomUUID().toString();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = 
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        var claims = new HashMap<String, Object>();

        User user = (User) authenticate.getPrincipal();
        claims.put("fullName", user.getFullName());

        var jwtToken = jwtService.generateToken(claims, user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Transactional
    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (savedToken.getValidatedAt() != null) {
            throw new RuntimeException("Token has already been used.");
        }

        if (savedToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation Token expired, new token will be sent to your email.");
        }

        User user = savedToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }
}
```

### **AuthenticationController.java**

```java
package com.wchamara.book.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> register(@RequestBody @Valid RegistrationRequest request) {
        try {
            authenticationService.register(request);
            return ResponseEntity.accepted().body("Registration successful. Please check your email to activate your account.");
        } catch (IllegalStateException | MessagingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/activate-account")
    public ResponseEntity<String> activateAccount(@RequestParam String token) {
        try {
            authenticationService.activateAccount(token);
            return ResponseEntity.ok("Account activated successfully. You can now log in.");
        } catch (RuntimeException | MessagingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        try {
            AuthenticationResponse response = authenticationService.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed.");
        }
    }
}
```

### **Token.java**

Ensure your `Token` entity has a unique token field and `validatedAt`.

```java
package com.wchamara.book.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String token;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
```

### **Additional Configurations**

#### **a. Enable JPA Auditing**

Since you're using `@CreatedDate` and `@LastModifiedDate` in the `User` entity, ensure that JPA auditing is enabled.

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    // Additional JPA configurations if needed
}
```

#### **b. Email Service Implementation**

Ensure that your `EmailService` is correctly implemented to handle sending emails with the activation link.

```java
package com.wchamara.book.email;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmail(String to, String name, EmailTemplateName templateName, String activationLink, String subject) throws MessagingException;
}
```

**Example Implementation:**

```java
package com.wchamara.book.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String name, EmailTemplateName templateName, String activationLink, String subject) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(getEmailContent(name, activationLink, templateName), true);

        mailSender.send(message);
    }

    private String getEmailContent(String name, String activationLink, EmailTemplateName templateName) {
        // Implement template selection based on EmailTemplateName
        // For simplicity, returning a basic HTML email
        return "<html><body>"
                + "<p>Hi " + name + ",</p>"
                + "<p>Please click the link below to activate your account:</p>"
                + "<a href=\"" + activationLink + "\">Activate Account</a>"
                + "<p>If you did not register, please ignore this email.</p>"
                + "</body></html>";
    }
}
```

## **4. Testing the Activation Endpoint**

Ensure that your activation flow works as expected by performing thorough testing.

### **a. Unit Tests**

Write unit tests for the `AuthenticationService` to test various scenarios like successful activation, expired tokens, reused tokens, and invalid tokens.

**Example:**

```java
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.wchamara.book.auth.AuthenticationService;
import com.wchamara.book.user.Token;
import com.wchamara.book.user.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class AuthenticationServiceTest {

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    public void testActivateAccount_Success() throws Exception {
        String tokenStr = "valid-token";
        User user = User.builder().id(1).enabled(false).build();
        Token token = Token.builder()
                .token(tokenStr)
                .user(user)
                .expiresAt(LocalDateTime.now().plusMinutes(10))
                .validatedAt(null)
                .build();

        when(tokenRepository.findByToken(tokenStr)).thenReturn(Optional.of(token));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        authenticationService.activateAccount(tokenStr);

        assertTrue(user.getEnabled());
        assertNotNull(token.getValidatedAt());
        verify(userRepository, times(1)).save(user);
        verify(tokenRepository, times(1)).save(token);
    }

    // Additional tests for expired token, invalid token, reused token, etc.
}
```

### **b. Integration Tests**

Test the entire flow from registration to activation to ensure all components work seamlessly together.

**Example:**

1. **Register a User:**
   - Send a POST request to `/auth/register` with user details.
   - Verify that the user is created with `enabled = false`.
   - Check that an activation email is sent.

2. **Activate the Account:**
   - Extract the activation token from the email (mocked in tests).
   - Send a GET request to `/auth/activate-account?token=...`.
   - Verify that `enabled` is set to `true`.

3. **Authenticate the User:**
   - Send a POST request to `/auth/authenticate` with user credentials.
   - Verify that authentication is successful and a JWT is returned.

## **5. Final Thoughts**

Implementing the `/activate-account` endpoint involves not just the backend logic but also ensuring security, reliability, and a smooth user experience. By following the recommendations above, you can enhance your activation flow to be more secure, user-friendly, and maintainable.

**Key Takeaways:**

- **Security:** Use secure, unique tokens and implement proper validation.
- **User Experience:** Provide clear feedback and handle errors gracefully.
- **Reliability:** Ensure atomic operations with transaction management and thorough testing.
- **Maintainability:** Write clean, well-documented code and follow best practices.

Feel free to reach out if you need further assistance with specific parts of the implementation or have additional questions!