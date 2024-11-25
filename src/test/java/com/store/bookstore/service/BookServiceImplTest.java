package com.store.bookstore.service;

import com.store.bookstore.dtos.BookDTO;
import com.store.bookstore.dtos.BookDTOMapper;
import com.store.bookstore.model.Author;
import com.store.bookstore.model.Book;
import com.store.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    //Note: We assume bookRepository works as intended due to it inheriting from JpaRepository<>
    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookDTOMapper bookDTOMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book sampleBook;
    private BookDTO sampleBookDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Author sampleAuthor = new Author();
        sampleAuthor.setId(1L);
        sampleAuthor.setName("John Smith");

        sampleBook = new Book("Sample Title", "1234567890", LocalDate.now(),
                "Sample Publisher", "English", 400, sampleAuthor);
        sampleBook.setId(1L);

        sampleBookDTO = new BookDTO("Sample Title", LocalDate.now(),
                "Sample Publisher", "English", 400, "John Smith");
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = List.of(sampleBook);
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleBook, result.get(0));

        //Note: Necessary?
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetAllBooksDTO() {
        List<Book> books = List.of(sampleBook);
        when(bookRepository.findAll()).thenReturn(books);
        when(bookDTOMapper.apply(sampleBook)).thenReturn(sampleBookDTO);

        List<BookDTO> result = bookService.getAllBooksDTO();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleBookDTO, result.get(0));

        //Note: Necessary x2?
        verify(bookRepository, times(1)).findAll();
        verify(bookDTOMapper, times(1)).apply(sampleBook);
    }

    @Test
    void testSaveBook() {
        when(bookRepository.save(sampleBook)).thenReturn(sampleBook);
        when(bookDTOMapper.apply(sampleBook)).thenReturn(sampleBookDTO);

        BookDTO result = bookService.saveBook(sampleBook);

        assertNotNull(result);
        assertEquals(sampleBookDTO, result);
    }

    @Test
    void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));

        Book result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals(sampleBook, result);
    }

    @Test
    void testGetBookByIdDTO() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));
        when(bookDTOMapper.apply(sampleBook)).thenReturn(sampleBookDTO);

        BookDTO result = bookService.getBookByIdDTO(1L);

        assertNotNull(result);
        assertEquals(sampleBookDTO, result);
    }

    @Test
    void testGetAllBooksForAuthor() {
        List<Book> books = List.of(sampleBook);
        when(bookRepository.findAll()).thenReturn(books);
        when(bookDTOMapper.apply(sampleBook)).thenReturn(sampleBookDTO);

        List<BookDTO> result = bookService.getAllBooksForAuthor(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleBookDTO, result.get(0));
    }

    @Test
    void testUpdateBook() {
        Book updatedBook = new Book("Updated Title", "0987654321", LocalDate.now(),
                "Updated Publisher", "French", 200);
        updatedBook.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));
        when(bookRepository.save(sampleBook)).thenReturn(sampleBook);
        when(bookDTOMapper.apply(sampleBook)).thenReturn(sampleBookDTO);

        BookDTO result = bookService.updateBook(1L, updatedBook);

        assertNotNull(result);
        assertEquals(sampleBookDTO, result);
    }
}
