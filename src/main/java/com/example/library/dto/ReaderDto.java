package com.example.library.dto;

import com.example.library.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReaderDto {

    private Long id;

    private String fullName;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    private List<BookItemDto> books;
}
