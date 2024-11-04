package com.store.bookstore.dtos;

import com.store.bookstore.model.Book;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BookDTOMapper implements Function<Book, BookDTO> {
    @Override
    public BookDTO apply(Book book) {
        return new BookDTO(
                book.getTitle(),
                book.getPublishDate(),
                book.getPublisher(),
                book.getLanguage(),
                book.getPageCount(),
                book.getAuthor() != null ? book.getAuthor().getName() : ""
        );
    }
}
