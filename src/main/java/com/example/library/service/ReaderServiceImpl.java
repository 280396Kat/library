package com.example.library.service;

import com.example.library.dto.ReaderDto;
import com.example.library.entity.BookEntity;
import com.example.library.entity.ReaderEntity;
import com.example.library.repository.ReaderEntityRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReaderServiceImpl implements ReaderService{

    private final ReaderEntityRepository readerEntityRepository;

    public ReaderServiceImpl(ReaderEntityRepository readerEntityRepository) {
        this.readerEntityRepository = readerEntityRepository;
    }

    @Override
    public ReaderEntity save(ReaderEntity readerEntity) {
        return readerEntityRepository.save(readerEntity);
    }

    @Override
    public boolean deleteById(long id) {
        Optional<ReaderEntity> getReaderEntity = readerEntityRepository.findById(id);
        if (getReaderEntity.isEmpty()) {
            return false;
        }
        ReaderEntity readerEntity = getReaderEntity.get();
        readerEntityRepository.delete(readerEntity);
        return true;
    }

    @Override
    public ReaderEntity update(long id, ReaderEntity readerEntity) {
        ReaderEntity readerEntity1 = readerEntityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No reader"));
        readerEntity1.setName(readerEntity.getName());
        readerEntity1.setSurname(readerEntity.getSurname());
        return readerEntityRepository.save(readerEntity1);
    }

    @Override
    public ReaderEntity findById(long id) {
        return readerEntityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No reader"));
    }

    @Override
    public List<ReaderEntity> findAll() {
        return readerEntityRepository.findAll();
    }

    @Override
    public List<ReaderDto> getReaderEntityByFirstNameAndSurname(String firstName, String surname) {
        return readerEntityRepository.findReaderEntityByFirstNameAndSurname(firstName, surname).stream()
                .map(tmp -> ReaderDto.builder()
                        .id(tmp.getId())
                        .fullName(tmp.getName() + " " + tmp.getSurname())
                        .nameBook(tmp.getBookEntity().getName())
                        .build())
                .collect(Collectors.toList());
    }




}
