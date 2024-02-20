package com.example.library.mapper;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookItemDto;
import com.example.library.entity.BookEntity;
import org.mapstruct.*;

import java.util.List;
@Mapper(componentModel = "spring")
public interface BookMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "fullName", source = "entity.getName() + \" \" + entity.getNameAuthor() + \" \" + entity.getGenre")
    @Mapping(target = "amountOfDays", source = "ChronoUnit.DAYS.between(bookEntity.getStartDate(), bookEntity.getFinishDate())")
    // не знаю, правильно ли, как вообще тут быть. Скопировала из маппера
    BookDto toDto(BookEntity bookEntity);

    @Mapping(target = "entity.getName() + \" \" + entity.getNameAuthor() + \" \" + entity.getGenre", source = "fullName")
    @Mapping(target = "ChronoUnit.DAYS.between(bookEntity.getStartDate(), bookEntity.getFinishDate())", source = "amountOfDays")
    BookEntity toEntity(BookItemDto bookItemDto);

    List<BookDto> toDto(List<BookEntity> bookEntities);

    BookItemDto toItemDto(BookEntity bookEntity);

    List<BookItemDto> toItemDto(List<BookEntity> bookEntities);
}
