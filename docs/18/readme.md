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

- [📗let's implement the /book-id method](#lets-implement-the-book-id-method)
    - [**1. BookController Class**](#1-bookcontroller-class)
      - [**Explanation:**](#explanation)
      - [**Improvements:**](#improvements)
    - [**2. BookResponse Class**](#2-bookresponse-class)
      - [**Explanation:**](#explanation-1)
      - [**Improvements:**](#improvements-1)
    - [**3. BookService Class**](#3-bookservice-class)
      - [**Explanation:**](#explanation-2)
      - [**Improvements:**](#improvements-2)
    - [**4. BookMapper Class**](#4-bookmapper-class)
      - [**Explanation:**](#explanation-3)
      - [**Improvements:**](#improvements-3)
    - [**5. BookRepository Interface**](#5-bookrepository-interface)
      - [**Explanation:**](#explanation-4)
      - [**Improvements:**](#improvements-4)
    - [**Best Practices Recap:**](#best-practices-recap)
  - [Table of Contents](#table-of-contents)
  - [1. Overview](#1-overview)
  - [2. Detailed Code Analysis](#2-detailed-code-analysis)
    - [1. BookController](#1-bookcontroller)
    - [2. BookResponse](#2-bookresponse)
    - [3. BookService](#3-bookservice)
    - [4. BookMapper](#4-bookmapper)
    - [5. BookRepository](#5-bookrepository)
  - [3. Improvements and Best Practices](#3-improvements-and-best-practices)
    - [a. Code Quality Enhancements](#a-code-quality-enhancements)
    - [b. Performance Optimizations](#b-performance-optimizations)
    - [b. Performance Optimizations](#b-performance-optimizations-1)
    - [c. Security Enhancements](#c-security-enhancements)

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