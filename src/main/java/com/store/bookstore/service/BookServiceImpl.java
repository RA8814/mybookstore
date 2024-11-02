package com.store.bookstore.service;

import com.store.bookstore.model.Book;
import com.store.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService  {
    private final BookRepository bookRepository;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    //test
    @Override
    public List<Book> getAllBooksForAuthor(Long id){
        List<Book> allBooks = getAllBooks();
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getAuthor().getId() == id) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks.isEmpty() ? null : filteredBooks;
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = getBookById(id);
        if (existingBook == null) {
            return null;
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

        return saveBook(existingBook);
    }
}

