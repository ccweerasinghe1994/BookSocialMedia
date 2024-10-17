# 📗Authentication Flow

- [📗Authentication Flow](#authentication-flow)
    - [Flow Breakdown:](#flow-breakdown)
    - [`pom.xml` Correlation:](#pomxml-correlation)
    - [Example Code Snippets:](#example-code-snippets)
    - [Conclusion:](#conclusion)


![alt text](image.png)

The diagram you shared outlines a security and request handling flow, likely from a Spring Security-based web application. Let me break it down step by step and correlate it with the `pom.xml` provided for better understanding:

### Flow Breakdown:

1. **HTTP Request:**
   - Requests (`GET`, `POST`, `PATCH`, `DELETE`) are sent to your backend. In Spring Boot, this would likely be handled by controllers.
   - In the diagram, the `DispatcherServlet` routes the request to the appropriate controller (`ControllerA`, `ControllerB`, etc.), which is part of the Spring MVC architecture.

2. **Security Filters:**
   - The **FilterChain** applies multiple filters to incoming requests. One of them is the `JwtAuthenticationFilter` (likely subclassing `OncePerRequestFilter`). This filter checks for a JWT (JSON Web Token) in the request header.
   
   - The `OncePerRequestFilter` ensures that this filter is only executed once per request.

3. **JWT Validation:**
   - The `JwtAuthenticationFilter` delegates the token validation to `JwtService`. If a valid token is found, it retrieves user details.
   - In case of an invalid token, a `TokenInvalidException` is thrown.

4. **UserDetailsService:**
   - Once the token is validated, the `UserDetailsService` attempts to find the user based on the email embedded in the token.
   - If the user is not found, a `UserNotFoundException` is thrown.

5. **SecurityContextHolder:**
   - If the user is authenticated successfully, the user details (including `Principal`, `Credentials`, and `Authorities`) are stored in `SecurityContextHolder`.
   - This security context is then available for use by the controllers to handle authorization logic.

6. **Response Handling:**
   - Once the request has passed through the filters and validation checks, the `DispatcherServlet` directs the request to the appropriate controller to process the business logic and generate a response.

### `pom.xml` Correlation:

In the `pom.xml`, we see the following dependencies related to the flow described in the diagram:

1. **Spring Security:**
   - The `spring-boot-starter-security` dependency provides the Spring Security framework, which includes filters, security context management, and authentication mechanisms.
   - The `spring-security-test` dependency is used for testing security-related components.

   Example:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-security</artifactId>
   </dependency>
   ```

2. **JWT Handling:**
   - The JWT processing is handled by `jjwt-api`, `jjwt-impl`, and `jjwt-jackson` dependencies. These provide the necessary functionality to create, parse, and validate JWT tokens.
   
   Example:
   ```xml
   <dependency>
       <groupId>io.jsonwebtoken</groupId>
       <artifactId>jjwt-api</artifactId>
       <version>0.12.3</version>
   </dependency>
   ```

3. **Controller Layer:**
   - The `spring-boot-starter-web` is responsible for creating controllers (e.g., `ControllerA`, `ControllerB`) that map HTTP requests to business logic.
   
   Example:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web</artifactId>
   </dependency>
   ```

4. **Thymeleaf:**
   - The `spring-boot-starter-thymeleaf` is used for server-side rendering of HTML pages, often used in conjunction with controllers to return views.

5. **Database Interaction:**
   - The `spring-boot-starter-data-jpa` is used to interact with databases, most likely tied to the `UserDetailsService` that fetches user data during the JWT validation process.
   - The PostgreSQL driver (`postgresql`) is also included for database interaction.
   
   Example:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-jpa</artifactId>
   </dependency>
   ```

### Example Code Snippets:

1. **JWT Filter Example (`JwtAuthenticationFilter`):**
   This filter checks for the JWT in the request header, validates it, and sets the authentication context.

   ```java
   public class JwtAuthenticationFilter extends OncePerRequestFilter {

       @Autowired
       private JwtService jwtService;

       @Autowired
       private UserDetailsServiceImpl userDetailsService;

       @Override
       protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
           String token = extractJwtFromRequest(request);

           if (StringUtils.hasText(token) && jwtService.validateToken(token)) {
               String email = jwtService.extractEmail(token);

               UserDetails userDetails = userDetailsService.loadUserByUsername(email);
               UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                       userDetails, null, userDetails.getAuthorities());

               SecurityContextHolder.getContext().setAuthentication(authentication);
           }

           filterChain.doFilter(request, response);
       }

       private String extractJwtFromRequest(HttpServletRequest request) {
           String bearerToken = request.getHeader("Authorization");
           if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
               return bearerToken.substring(7);
           }
           return null;
       }
   }
   ```

2. **JWT Service Example:**

   This service handles the logic for token validation and extraction.

   ```java
   @Service
   public class JwtService {

       private final String secretKey = "secret";

       public boolean validateToken(String token) {
           try {
               Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
               return true;
           } catch (JwtException | IllegalArgumentException e) {
               return false;
           }
       }

       public String extractEmail(String token) {
           Claims claims = Jwts.parser()
               .setSigningKey(secretKey)
               .parseClaimsJws(token)
               .getBody();
           return claims.getSubject();
       }
   }
   ```

3. **Security Configuration:**

   The Spring Security configuration ensures that the `JwtAuthenticationFilter` is applied before other filters.

   ```java
   @Configuration
   @EnableWebSecurity
   public class SecurityConfig extends WebSecurityConfigurerAdapter {

       @Autowired
       private JwtAuthenticationFilter jwtAuthenticationFilter;

       @Override
       protected void configure(HttpSecurity http) throws Exception {
           http
               .csrf().disable()
               .authorizeRequests()
               .antMatchers("/api/auth/**").permitAll()
               .anyRequest().authenticated()
               .and()
               .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
       }
   }
   ```

### Conclusion:

This architecture provides robust security using JWT for authentication. It filters each request, validates tokens, fetches user details, and secures user contexts. All dependencies in the `pom.xml` support these processes, especially JWT handling (`jjwt`) and security (`spring-boot-starter-security`).