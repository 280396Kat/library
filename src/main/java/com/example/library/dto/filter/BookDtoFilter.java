package com.example.library.dto.filter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDtoFilter {

    private String name;

    private String author;

    private String genre;

    private String surname;
}
