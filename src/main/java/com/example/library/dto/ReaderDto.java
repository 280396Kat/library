package com.example.library.dto;

import com.example.library.entity.BookEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "ReaderDto", description = "Модель данных описывает читателя")
public class ReaderDto {

    @ApiModelProperty("Идентификатор читателя")
    private Long id;

    @ApiModelProperty("ФИО читателя")
    private String fullName;

    @ApiModelProperty("Дата рождения читателя")
    private LocalDate dateOfBirth;

    @ApiModelProperty("Номер телефона читателя")
    private String phoneNumber;

    @ApiModelProperty("Читательский билет")
    private String readerTicket;

    @ApiModelProperty("Список книг читателя")
    private List<BookItemDto> books;
}
