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
