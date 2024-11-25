package com.store.bookstore.service;

import com.store.bookstore.dtos.AuthorDTO;
import com.store.bookstore.model.Author;
import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    List<AuthorDTO> getAllAuthorsDTO();
    AuthorDTO saveAuthor(Author author);
    AuthorDTO updateAuthor(Long id, Author author);
    Author getAuthorById(Long id);
    AuthorDTO getAuthorDTOById(Long id);
    void deleteAuthor(Long id);
}
