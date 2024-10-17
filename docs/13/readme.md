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
