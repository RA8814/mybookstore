package com.store.bookstore.service;

import com.store.bookstore.dtos.BookDTO;
import com.store.bookstore.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    List<BookDTO> getAllBooksDTO();
    BookDTO saveBook(Book book);
    Book getBookById(Long id);
    BookDTO getBookByIdDTO(Long id);
    void deleteBook(Long id);
    List<BookDTO> getAllBooksForAuthor(Long id);
    BookDTO updateBook(Long id, Book updatedBook);
}
