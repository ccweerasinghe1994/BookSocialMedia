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
