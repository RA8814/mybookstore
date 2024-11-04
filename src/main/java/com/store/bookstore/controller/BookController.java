package com.store.bookstore.controller;

import com.store.bookstore.dtos.BookDTO;
import com.store.bookstore.model.Book;
import com.store.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooksDTO());
    }

    @GetMapping("/byAuthor/{id}")
    public ResponseEntity<List<BookDTO>> getAllBooksForAuthor(@PathVariable Long id) {
        List<BookDTO> allBooksForAuthor = bookService.getAllBooksForAuthor(id);
        return allBooksForAuthor != null ?
                ResponseEntity.ok(allBooksForAuthor) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO book = bookService.getBookByIdDTO(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        BookDTO updateBook = bookService.updateBook(id, updatedBook);
        return updateBook != null ?
                ResponseEntity.ok(updateBook) : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}

