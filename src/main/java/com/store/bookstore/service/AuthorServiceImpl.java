package com.store.bookstore.service;

import com.store.bookstore.dtos.AuthorDTO;
import com.store.bookstore.dtos.AuthorDTOMapper;
import com.store.bookstore.dtos.BookDTO;
import com.store.bookstore.model.Author;
import com.store.bookstore.model.Book;
import com.store.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorDTOMapper authorDTOMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.authorDTOMapper = new AuthorDTOMapper();
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public List<AuthorDTO> getAllAuthorsDTO() {
        List<AuthorDTO> collect = authorRepository.findAll()
                .stream()
                .map(authorDTOMapper)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public AuthorDTO saveAuthor(Author author) {
        Author savedAuthor = authorRepository.save(author);
        return authorDTOMapper.apply(savedAuthor);
    }

    @Override
    public AuthorDTO updateAuthor(Long id, Author author) {
        Author existingAuthor = getAuthorById(id);
        if (existingAuthor == null) {
            return null;
        }
        existingAuthor.setName(author.getName());
        existingAuthor.setBirthdate(author.getBirthdate());
        return saveAuthor(author);
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public AuthorDTO getAuthorDTOById(Long id) {
        Author baseAuthor = authorRepository.findById(id).orElse(null);
        AuthorDTO appliedAuthor = authorDTOMapper.apply(baseAuthor);
        return appliedAuthor;
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
