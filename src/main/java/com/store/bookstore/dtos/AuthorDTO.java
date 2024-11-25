package com.store.bookstore.dtos;

import java.time.LocalDate;

public record AuthorDTO(String name,
                        LocalDate birthdate
) {
}
