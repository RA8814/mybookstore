package com.store.bookstore.controller;

import com.store.bookstore.dtos.BookDTO;
import com.store.bookstore.model.Book;
import com.store.bookstore.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private Book sampleBook;
    private BookDTO sampleBookDTO;

    @BeforeEach
    void setUp() {
        //Note: Just instantiate (as opposed to just declares) the mocked classes
        MockitoAnnotations.openMocks(this);

        sampleBook = new Book(
                "Sample Title",
                "123-456-789",
                LocalDate.of(2024, 4, 4),
                "Sample Publisher",
                "English",
                200
        );

        sampleBookDTO = new BookDTO(
                "Sample Title",
                LocalDate.of(2024, 4, 4),
                "Sample Publisher",
                "English",
                200,
                "John Smith"
        );
    }

    @Test
    void testGetAllBooks() {
        List<BookDTO> books = Arrays.asList(sampleBookDTO);

        when(bookService.getAllBooksDTO()).thenReturn(books);

        ResponseEntity<List<BookDTO>> response = bookController.getAllBooks();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(sampleBookDTO, response.getBody().get(0));
    }

    @Test
    void testGetAllBooksForAuthor() {
        List<BookDTO> books = Arrays.asList(sampleBookDTO);
        Long authorId = 1L;

        when(bookService.getAllBooksForAuthor(authorId)).thenReturn(books);

        ResponseEntity<List<BookDTO>> response = bookController.getAllBooksForAuthor(authorId);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(sampleBookDTO, response.getBody().get(0));
    }

    @Test
    void testGetAllBooksForAuthor_NotFound() {
        Long authorId = 99L;

        when(bookService.getAllBooksForAuthor(authorId)).thenReturn(null);

        ResponseEntity<List<BookDTO>> response = bookController.getAllBooksForAuthor(authorId);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCreateBook() {
        when(bookService.saveBook(sampleBook)).thenReturn(sampleBookDTO);

        ResponseEntity<BookDTO> response = bookController.createBook(sampleBook);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(sampleBookDTO, response.getBody());
    }

    @Test
    void testGetBookById() {
        Long bookId = 1L;

        when(bookService.getBookByIdDTO(bookId)).thenReturn(sampleBookDTO);

        ResponseEntity<BookDTO> response = bookController.getBookById(bookId);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(sampleBookDTO, response.getBody());
    }

    @Test
    void testUpdateBook() {
        Long bookId = 1L;

        when(bookService.updateBook(bookId, sampleBook)).thenReturn(sampleBookDTO);

        ResponseEntity<BookDTO> response = bookController.updateBook(bookId, sampleBook);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(sampleBookDTO, response.getBody());
    }

    @Test
    void testUpdateBook_NotFound() {
        Long bookId = 99L;

        when(bookService.updateBook(bookId, sampleBook)).thenReturn(null);

        ResponseEntity<BookDTO> response = bookController.updateBook(bookId, sampleBook);

        assertEquals(404, response.getStatusCodeValue());
    }
}
