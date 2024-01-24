package com.example.library.dto;

import com.example.library.entity.BookEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Data
@Builder
public class ReaderDto {

    private Long id;

    private String fullName;


    private String nameBook;
}
