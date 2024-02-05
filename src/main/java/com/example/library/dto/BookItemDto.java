package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookItemDto {

    private Long id;

    private String name;

    private String author;

    private String genre;

    private LocalDate startDate;

    private LocalDate finishDate;
}
