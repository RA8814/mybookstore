package com.store.bookstore.service;

import com.store.bookstore.dtos.BookDTO;
import com.store.bookstore.dtos.BookDTOMapper;
import com.store.bookstore.model.Book;
import com.store.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService  {
    private final BookRepository bookRepository;
    private final BookDTOMapper bookDTOMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookDTOMapper bookDTOMapper) {
        this.bookRepository = bookRepository;
        this.bookDTOMapper = bookDTOMapper;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookDTO> getAllBooksDTO() {
        List<BookDTO> collect = bookRepository.findAll()
                .stream()
                .map(bookDTOMapper)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public BookDTO saveBook(Book book) {
        Book savedBook = bookRepository.save(book);
        return bookDTOMapper.apply(savedBook);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public BookDTO getBookByIdDTO(Long id) {
        Book baseBook = bookRepository.findById(id).orElse(null);
        BookDTO appliedBook = bookDTOMapper.apply(baseBook);
        return appliedBook;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    //test
    @Override
    public List<BookDTO> getAllBooksForAuthor(Long id){
        List<Book> allBooks = getAllBooks();
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getAuthor() != null && book.getAuthor().getId() == id) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks.isEmpty() ? null : filteredBooks
                                                .stream()
                                                .map(bookDTOMapper)
                                                .collect(Collectors.toList());
    }

    @Override
    public BookDTO updateBook(Long id, Book updatedBook) {
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

