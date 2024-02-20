package com.example.library.service;

import com.example.library.dto.*;
import com.example.library.dto.filter.ReaderDtoFilter;
import com.example.library.entity.BookEntity;
import com.example.library.entity.ReaderEntity;
import com.example.library.exception.NotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.mapper.ReaderMapper;
import com.example.library.repository.ReaderEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl {
    private final ReaderEntityRepository readerEntityRepository;
    private final ReaderMapper readerMapper;
    private final BookMapper bookMapper;

    public void save(ReaderRegisterDto readerRegisterDto) {
        ReaderEntity readerEntity = readerMapper.toEntity(readerRegisterDto);
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
        return readerEntityRepository.findById(readerEntity.getId())
                .map(readerMapper::toRegisterDto)
                .orElseThrow(() -> new NotFoundException(String.format("Reader is not found id = %s", readerRegisterDto)));
    }

    //ReaderEntity entitySave = readerEntityRepository.save(readerEntity);
       /* ReaderRegisterDto registerDto = ReaderRegisterDto.builder()
                .id(entitySave.getId())
                .name(entitySave.getName())
                .surname(entitySave.getSurname())
                .middleName(entitySave.getMiddleName())
                .phoneNumber(entitySave.getPhoneNumber())
                .build();*/

    public ReaderDto getById(long id) {
        return readerEntityRepository.findById(id)
                .map(readerMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Reader is not found id = %s", id)));
    }

    public List<ReaderDto> getReaderEntityByFirstNameAndSurname(String firstName, String surname) {
        return readerEntityRepository.findReaderEntityByFirstNameAndSurname(firstName, surname).stream()
                .map(tmp -> ReaderDto.builder()
                        .id(tmp.getId())
                        .fullName(tmp.getName() + " " + tmp.getSurname() + " " + tmp.getMiddleName())
                        .books(tmp.getBookEntities().stream()
                                .map(bookMapper::toItemDto)
                                .collect(Collectors.toList())
                        )
                        .build())
                .collect(Collectors.toList());
    }

    public List<ReaderDtoInfo> getReaderByFilter(ReaderDtoFilter readerDtoFilter) {
        return readerEntityRepository.findReaderEntityByFilter(
                readerDtoFilter.getSurname(),
                readerDtoFilter.getName(),
                readerDtoFilter.getMiddleName(),
                readerDtoFilter.getDateOfBirth()
        ).stream()
                .map(readerMapper::toInfoDto
                ).collect(Collectors.toList());
    }
}
