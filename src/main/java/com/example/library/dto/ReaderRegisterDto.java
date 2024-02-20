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
@ApiModel(value = "ReaderDto", description = "Модель данных описывает регистрацию читателя")
public class ReaderRegisterDto {

    @ApiModelProperty("Идентификатор читателя")
    private Long id;

    @ApiModelProperty("Отчество читателя")
    private String middleName;

    @ApiModelProperty("Фамилия читателя")
    private String surname;

    @ApiModelProperty("Имя читателя")
    private String name;

    @ApiModelProperty("Читательский билет")
    private String readerTicket;

    @ApiModelProperty("Дата рождения читателя")
    private LocalDate dateOfBirth;

    @ApiModelProperty("Номер телефона читателя")
    private String phoneNumber;
}
