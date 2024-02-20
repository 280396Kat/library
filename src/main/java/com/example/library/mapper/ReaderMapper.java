package com.example.library.mapper;

import com.example.library.dto.ReaderDto;
import com.example.library.dto.ReaderDtoInfo;
import com.example.library.dto.ReaderRegisterDto;
import com.example.library.entity.ReaderEntity;
import org.mapstruct.*;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ReaderMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "name", source = "entity.getName() + \" \" + entity.getSurname() + \" \" + entity.getMiddleName())") // тут получается мы меняем только то, что отличается в entity и DTO?
    ReaderDto toDto(ReaderEntity readerEntity);

    ReaderEntity toEntity(ReaderRegisterDto readerRegisterDto); // тут всё совпадает

    List<ReaderDto> toDto(List<ReaderEntity> readerEntities); // не пойму как лист делать

    @Mapping(target = "countBook", source = "entity.bookEntities.size()") // хз правильно или нет
    @Mapping(target = "name", source = "entity.getName() + \" \" + entity.getSurname() + \" \" + entity.getMiddleName())")
    ReaderDtoInfo toInfoDto(ReaderEntity readerEntity);

    ReaderRegisterDto toRegisterDto(ReaderEntity readerEntity);
}
