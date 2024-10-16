package com.store.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;
    private LocalDate publishDate;
    private String publisher;
    private String language;
    private int pageCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonBackReference  // Means this field gets ignored during serialisation, but updates still occur
    private Author author;

    public Book() {}

    public Book(String title, String isbn, LocalDate publishDate, String publisher, String language, int pageCount) {
        this.title = title;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.publisher = publisher;
        this.language = language;
        this.pageCount = pageCount;
    }

    public Book(String title, String isbn, LocalDate publishDate, String publisher, String language, int pageCount, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.publisher = publisher;
        this.language = language;
        this.pageCount = pageCount;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
