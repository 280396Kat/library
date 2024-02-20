package com.example.library.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "ReaderDtoInfo", description = "Модель данных описывает читателя")
public class ReaderDtoInfo {

    @ApiModelProperty("Идентификатор читателя")
    private Long id;

    @ApiModelProperty("ФИО читателя")
    private String fullName;

    @ApiModelProperty("Номер телефона читателя")
    private String phoneNumber;

    @ApiModelProperty("Количество книг у читателя")
    private int countBook;
}
