package com.store.bookstore.controller;

import com.store.bookstore.model.Author;
import com.store.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //note: works with JSON data
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor) {
        Author existingAuthor = authorService.getAuthorById(id);
        if (existingAuthor == null) {
            return ResponseEntity.notFound().build();
        }
        existingAuthor.setName(updatedAuthor.getName());
        existingAuthor.setBirthdate(updatedAuthor.getBirthdate());
        authorService.saveAuthor(existingAuthor);
        return ResponseEntity.ok(existingAuthor);

    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
