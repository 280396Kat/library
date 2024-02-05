package com.example.library.service;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookItemDto;
import com.example.library.dto.ReaderDto;
import com.example.library.dto.filter.BookDtoFilter;
import com.example.library.entity.BookEntity;
import com.example.library.entity.ReaderEntity;
import com.example.library.exception.NotFoundException;
import com.example.library.repository.BookEntityRepository;
import com.example.library.repository.ReaderEntityRepository;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl {

    private final BookEntityRepository bookEntityRepository;

    private final ReaderEntityRepository readerEntityRepository;

    public BookServiceImpl(BookEntityRepository bookEntityRepository, ReaderEntityRepository readerEntityRepository) {
        this.bookEntityRepository = bookEntityRepository;
        this.readerEntityRepository = readerEntityRepository;
    }

    public void save(BookItemDto bookItemDto) {
        BookEntity bookEntity = BookEntity.builder()
                .name(bookItemDto.getName())
                .nameAuthor(bookItemDto.getAuthor())
                .genre(bookItemDto.getGenre())
                .startDate(bookItemDto.getStartDate())
                .finishDate(bookItemDto.getFinishDate())
                .build();
        bookEntityRepository.save(bookEntity);
    }

    public void updateReaderBookById(List<Long> id) { // а этот метод для чего? Он обновляет/получает список книг, которые есть у читателя?
        id.forEach(bookId -> {
            BookEntity bookEntity = bookEntityRepository.findById(bookId)
                    .orElseThrow(() -> new NotFoundException(String.format("Book is not found id = %s", id)));
            bookEntity.setReaderEntity(null);
            bookEntityRepository.save(bookEntity);
        });
    }

    public BookItemDto update(BookItemDto bookItemDto) {
        BookEntity bookEntity = bookEntityRepository.findById(bookItemDto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Book is not found id = %s", bookItemDto)));
        bookEntity.setName(bookItemDto.getName());
        bookEntity.setNameAuthor(bookItemDto.getAuthor());
        bookEntity.setGenre(bookItemDto.getGenre());
        bookEntity.setStartDate(bookItemDto.getStartDate());
        bookEntity.setFinishDate(bookItemDto.getFinishDate());
        BookEntity entitySave = bookEntityRepository.save(bookEntity);
        BookItemDto bookItem = BookItemDto.builder()
                .id(entitySave.getId())
                .name(entitySave.getName())
                .author(entitySave.getNameAuthor())
                .genre(entitySave.getGenre())
                .startDate(entitySave.getStartDate())
                .finishDate(entitySave.getFinishDate())
                .build();
        return bookItem;
    }

    public List<BookItemDto> getAll() {
        return bookEntityRepository.findAll().stream()
                .map(bookEntity -> {
                    BookItemDto bookItemDto = new BookItemDto();
                    bookItemDto.setName(bookItemDto.getName());
                    bookItemDto.setAuthor(bookItemDto.getAuthor());
                    bookItemDto.setGenre(bookItemDto.getGenre());
                    bookItemDto.setStartDate(bookItemDto.getStartDate());
                    bookItemDto.setFinishDate(bookItemDto.getFinishDate());
                    return bookItemDto;
                })
                .collect(Collectors.toList());
    }

    public BookDto getBookDtoById(long id) {
        BookEntity bookEntity = bookEntityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book is not found id = %s", id)));
        BookDto bookDto = BookDto.builder()
                .id(bookEntity.getId())
                .fullName(bookEntity.getName() + " " + bookEntity.getNameAuthor() + " " +
                        bookEntity.getGenre())
                .build();
        return bookDto;
    }
}





