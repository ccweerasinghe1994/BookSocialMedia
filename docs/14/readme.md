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