package com.store.bookstore.dtos;

import static org.junit.jupiter.api.Assertions.*;

import com.store.bookstore.model.Author;
import com.store.bookstore.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

//Note: ctrl+shift+t for automatic test implementation
class BookDTOMapperTest {

    private BookDTOMapper bookDTOMapper;

    //Note: Ensures no test is affected by the side effects of another since a separate mapper will be generated for each @Test
    @BeforeEach
    void setUp() {
        bookDTOMapper = new BookDTOMapper();
    }

    @Test
    void testMapBookToBookDTO_WithAuthor() {
        Author author = new Author("John Smith", LocalDate.of(1970, 4, 4));
        Book book = new Book(
                "Sample Book",
                "123456789",
                LocalDate.of(2024, 4, 4),
                "Sample Publisher",
                "English",
                400,
                author
        );
        System.out.println("Id: " + book.getId() );
        BookDTO bookDTO = bookDTOMapper.apply(book);

        assertNotNull(bookDTO, "The mapped BookDTO should not be null.");
        assertEquals("Sample Book", bookDTO.title(), "Title should match.");
        assertEquals(LocalDate.of(2024, 4, 4), bookDTO.publishDate(), "Publish date should match.");
        assertEquals("Sample Publisher", bookDTO.publisher(), "Publisher should match.");
        assertEquals("English", bookDTO.language(), "Language should match.");
        assertEquals(400, bookDTO.pageCount(), "Page count should match.");
        assertEquals("John Smith", bookDTO.author(), "Author name should match.");
    }

    @Test
    void testMapBookToBookDTO_WithoutAuthor() {
        Book book = new Book(
                "Sample Book",
                "123456789",
                LocalDate.of(2024, 4, 4),
                "Sample Publisher",
                "English",
                400
        );
        BookDTO bookDTO = bookDTOMapper.apply(book);

        assertNotNull(bookDTO, "The mapped BookDTO should not be null.");
        assertEquals("Sample Book", bookDTO.title(), "Title should match.");
        assertEquals(LocalDate.of(2024, 4, 4), bookDTO.publishDate(), "Publish date should match.");
        assertEquals("Sample Publisher", bookDTO.publisher(), "Publisher should match.");
        assertEquals("English", bookDTO.language(), "Language should match.");
        assertEquals(400, bookDTO.pageCount(), "Page count should match.");
        assertEquals("", bookDTO.author(), "Author name should be an empty string if no author is set.");
    }
    //Note: We (should?) be concerned with literal data validity on the model/dto level
}
