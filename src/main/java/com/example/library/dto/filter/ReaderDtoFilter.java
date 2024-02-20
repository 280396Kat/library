package com.example.library.dto.filter;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReaderDtoFilter {

    private String name;

    private String  middleName;

    private String surname;

    private LocalDate dateOfBirth;
}
