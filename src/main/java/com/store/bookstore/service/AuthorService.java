package com.store.bookstore.service;

import com.store.bookstore.model.Author;
import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author saveAuthor(Author author);
    Author updateAuthor(Long id, Author author);
    Author getAuthorById(Long id);
    void deleteAuthor(Long id);
}
