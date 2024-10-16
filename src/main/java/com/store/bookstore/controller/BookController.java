package com.store.bookstore.controller;

import com.store.bookstore.model.Book;
import com.store.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    //test
    @GetMapping("/byAuthor/{id}")
    public List<Book> getAllBooksForAuthor(@PathVariable Long id) {
        List<Book> allBooks = bookService.getAllBooks();
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getAuthor().getId() == id) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book existingBook = bookService.getBookById(id);
        if (existingBook == null) {
            return ResponseEntity.notFound().build();
        }

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setPublishDate(updatedBook.getPublishDate());
        existingBook.setPublisher(updatedBook.getPublisher());
        existingBook.setLanguage(updatedBook.getLanguage());
        existingBook.setPageCount(updatedBook.getPageCount());
        if (updatedBook.getAuthor() != null) {
            existingBook.setAuthor(updatedBook.getAuthor());
        }

        bookService.saveBook(existingBook);
        return ResponseEntity.ok(existingBook);
    }


    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}

