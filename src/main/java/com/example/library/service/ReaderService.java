package com.example.library.service;

import com.example.library.dto.ReaderDto;
import com.example.library.entity.ReaderEntity;
import org.springframework.data.repository.query.Param;

import java.awt.print.Book;
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReaderService {
    ReaderEntity save(ReaderEntity readerEntity);

    boolean deleteById(long id);

    ReaderEntity update(long id, ReaderEntity readerEntity);

    ReaderEntity findById(long id);

    List<ReaderEntity> findAll();

    List<ReaderDto> getReaderEntityByFirstNameAndSurname(String firstName, String surname);
}
