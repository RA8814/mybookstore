package com.store.bookstore.dtos;

import com.store.bookstore.model.Author;
import com.store.bookstore.model.Book;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AuthorDTOMapper implements Function<Author, AuthorDTO> {
    @Override
    public AuthorDTO apply(Author author) {
        return new AuthorDTO(author.getName(),
                author.getBirthdate()
        );
    }
}
