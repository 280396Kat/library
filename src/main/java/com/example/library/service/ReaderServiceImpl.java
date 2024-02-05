package com.example.library.service;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookItemDto;
import com.example.library.dto.ReaderDto;
import com.example.library.dto.ReaderRegisterDto;
import com.example.library.entity.BookEntity;
import com.example.library.entity.ReaderEntity;
import com.example.library.exception.NotFoundException;
import com.example.library.repository.ReaderEntityRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReaderServiceImpl {

    private final ReaderEntityRepository readerEntityRepository;

    public ReaderServiceImpl(ReaderEntityRepository readerEntityRepository) {
        this.readerEntityRepository = readerEntityRepository;
    }

    public void save(ReaderRegisterDto readerRegisterDto) {
        ReaderEntity readerEntity = ReaderEntity.builder()
                .dateOfBirth(readerRegisterDto.getDateOfBirth())
                .middleName(readerRegisterDto.getMiddleName())
                .surname(readerRegisterDto.getSurname())
                .name(readerRegisterDto.getName())
                .phoneNumber(readerRegisterDto.getPhoneNumber())
                .build();
        readerEntityRepository.save(readerEntity);
    }

    public void deleteById(long id) {
        ReaderEntity readerEntity = readerEntityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Reader is not found id = %s", id)));
        readerEntityRepository.delete(readerEntity);
    }

    public ReaderRegisterDto update(ReaderRegisterDto readerRegisterDto) {
        ReaderEntity readerEntity = readerEntityRepository.findById(readerRegisterDto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Reader is not found id = %s", readerRegisterDto)));
        readerEntity.setName(readerRegisterDto.getName());
        readerEntity.setSurname(readerRegisterDto.getSurname());
        readerEntity.setMiddleName(readerRegisterDto.getMiddleName());
        readerEntity.setPhoneNumber(readerRegisterDto.getPhoneNumber());
        ReaderEntity entitySave = readerEntityRepository.save(readerEntity);
        ReaderRegisterDto registerDto = ReaderRegisterDto.builder()
                .id(entitySave.getId())
                .name(entitySave.getName())
                .surname(entitySave.getSurname())
                .middleName(entitySave.getMiddleName())
                .phoneNumber(entitySave.getPhoneNumber())
                .build();
        return registerDto;
    }

    public ReaderDto getById(long id) {
        ReaderEntity readerEntity = readerEntityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Reader is not found id = %s", id)));
        ReaderDto readerDto = ReaderDto.builder()
                .id(readerEntity.getId())
                .fullName(readerEntity.getName() + " " + readerEntity.getSurname() + " " +
                        readerEntity.getMiddleName())
                .phoneNumber(readerEntity.getPhoneNumber())
                .dateOfBirth(readerEntity.getDateOfBirth())
                .books(readerEntity.getBookEntities().stream() // тут получаем книги, которые брал читатель. Верно?
                        .map(bookEntity -> {
                            BookItemDto bookItemDto = new BookItemDto();
                            bookItemDto.setName(bookItemDto.getName());
                            bookItemDto.setAuthor(bookItemDto.getAuthor());
                            bookItemDto.setGenre(bookItemDto.getGenre());
                            bookItemDto.setStartDate(bookItemDto.getStartDate());
                            bookItemDto.setFinishDate(bookItemDto.getFinishDate());
                            return bookItemDto;
                        })
                        .collect(Collectors.toList()))
                .build();
        return readerDto;
    }

    public List<ReaderEntity> getAll() {
        return readerEntityRepository.findAll();
    } // этот метод получается не нужен?


    public List<ReaderDto> getReaderEntityByFirstNameAndSurname(String firstName, String surname) {
        return readerEntityRepository.findReaderEntityByFirstNameAndSurname(firstName, surname).stream()
                .map(tmp -> ReaderDto.builder()
                        .id(tmp.getId())
                        .fullName(tmp.getName() + " " + tmp.getSurname())
                        .books(tmp.getBookEntities().stream()
                                .map(bookEntity -> BookItemDto.builder()
                                        .id(bookEntity.getId())
                                        .name(bookEntity.getName())
                                        .author(bookEntity.getNameAuthor())
                                        .genre(bookEntity.getGenre())
                                        .startDate(bookEntity.getStartDate())
                                        .finishDate(bookEntity.getFinishDate())
                                        .build()
                                )
                                .collect(Collectors.toList())
                        )
                        .build())
                .collect(Collectors.toList());
    }
}
