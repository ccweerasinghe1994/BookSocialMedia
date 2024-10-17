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