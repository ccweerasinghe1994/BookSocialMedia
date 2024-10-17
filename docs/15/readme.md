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