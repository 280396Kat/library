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
public class ReaderRegisterDto {

    private Long id;

    private String middleName;

    private String surname;

    private String name;

    private LocalDate dateOfBirth;

    private String phoneNumber;
}
