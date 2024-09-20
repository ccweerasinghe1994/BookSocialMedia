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
}
