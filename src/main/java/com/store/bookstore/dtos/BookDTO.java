package com.store.bookstore.dtos;

import java.time.LocalDate;

//Title, Publisher, Language, PageCount
public record BookDTO(String title,
                      LocalDate publishDate,
                      String publisher,
                      String language,
                      int pageCount,
                      String author
) {

}
