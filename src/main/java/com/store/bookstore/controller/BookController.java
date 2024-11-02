package com.store.bookstore.controller;

import com.store.bookstore.model.Book;
import com.store.bookstore.service.BookService;
import com.store.bookstore.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/byAuthor/{id}")
    public ResponseEntity<List<Book>> getAllBooksForAuthor(@PathVariable Long id) {
        List<Book> allBooksForAuthor = bookService.getAllBooksForAuthor(id);
        return allBooksForAuthor != null ?
                ResponseEntity.ok(allBooksForAuthor) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book updateBook = bookService.updateBook(id, updatedBook);
        return updateBook != null ?
                ResponseEntity.ok(updateBook) : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}

