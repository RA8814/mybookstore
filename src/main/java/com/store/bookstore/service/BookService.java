package com.store.bookstore.service;

import com.store.bookstore.model.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book saveBook(Book book);
    Book getBookById(Long id);
    void deleteBook(Long id);
    List<Book> getAllBooksForAuthor(Long id);
    Book updateBook(Long id, Book updatedBook);
}
