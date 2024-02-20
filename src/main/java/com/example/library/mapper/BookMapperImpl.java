package com.example.library.mapper;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookItemDto;
import com.example.library.entity.BookEntity;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapperImpl implements BookMapper {
    @Override
    public BookDto toDto(BookEntity bookEntity) {
        return BookDto.builder()
                .id(bookEntity.getId())
                .fullName(bookEntity.getName() + " " + bookEntity.getNameAuthor() + " " +
                        bookEntity.getGenre())
                .amountOfDays(ChronoUnit.DAYS.between(bookEntity.getStartDate(), bookEntity.getFinishDate()))
                .build();
    }

    @Override
    public BookEntity toEntity(BookItemDto bookItemDto) {
        return BookEntity.builder()
                .id(bookItemDto.getId())
                .name(bookItemDto.getName())
                .nameAuthor(bookItemDto.getAuthor())
                .genre(bookItemDto.getGenre())
                .startDate(bookItemDto.getStartDate())
                .finishDate(bookItemDto.getFinishDate())
                .build();
    }

     /* String fullName = bookDto.getFullName();
        String[] parts = fullName.split(" ");
        String name = parts[0];
        String author = parts[1];
        String genre = parts[2];*/

    @Override
    public List<BookDto> toDto(List<BookEntity> bookEntities) {
        return bookEntities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookItemDto toItemDto(BookEntity bookEntity) {
        return BookItemDto.builder()
                .id(bookEntity.getId())
                .name(bookEntity.getName())
                .author(bookEntity.getNameAuthor())
                .genre(bookEntity.getGenre())
                .startDate(bookEntity.getStartDate())
                .finishDate(bookEntity.getFinishDate())
                .build();
    }

    @Override
    public List<BookItemDto> toItemDto(List<BookEntity> bookEntities) {
        return bookEntities.stream()
                .map(this::toItemDto)
                .collect(Collectors.toList());
    }
}
