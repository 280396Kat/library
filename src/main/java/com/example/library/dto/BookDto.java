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
public class BookDto {

    private Long id;

    private String fullName;

    private int amountOfDays; // может его нужно перенести в BookItemDto?


    public void setFullName(String name, String author, String genre) {
        this.fullName = name + " " + author + " " + genre;
    }

    public void setFullName(String surname, String name) {
        this.fullName = surname + " " +  name;
    }

    public void setAmountOfDays(LocalDate startDate, LocalDate finishDate) {
        this.amountOfDays = finishDate.getDayOfYear() - startDate.getDayOfYear();
    }
}
