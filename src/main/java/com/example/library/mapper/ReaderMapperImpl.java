package com.example.library.mapper;

import com.example.library.dto.BookItemDto;
import com.example.library.dto.ReaderDto;
import com.example.library.dto.ReaderDtoInfo;
import com.example.library.dto.ReaderRegisterDto;
import com.example.library.entity.ReaderEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapperImpl implements ReaderMapper {
    @Override
    public ReaderDto toDto(ReaderEntity readerEntity) {
        return ReaderDto.builder()
                .id(readerEntity.getId())
                .fullName(readerEntity.getName() + " " + readerEntity.getSurname() + " " +
                        readerEntity.getMiddleName())
                .dateOfBirth(readerEntity.getDateOfBirth())
                .phoneNumber(readerEntity.getPhoneNumber())
                .readerTicket(readerEntity.getReaderTicket())
                .books(readerEntity.getBookEntities().stream()
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
    }

    @Override
    public ReaderEntity toEntity(ReaderRegisterDto readerRegisterDto) {
        return ReaderEntity.builder()
                .id(readerRegisterDto.getId())
                .surname(readerRegisterDto.getSurname())
                .name(readerRegisterDto.getName())
                .middleName(readerRegisterDto.getMiddleName())
                .phoneNumber(readerRegisterDto.getPhoneNumber())
                .dateOfBirth(readerRegisterDto.getDateOfBirth())
                .readerTicket(readerRegisterDto.getReaderTicket())
                .build();
    }

    @Override
    public List<ReaderDto> toDto(List<ReaderEntity> readerEntities) {
        return readerEntities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReaderDtoInfo toInfoDto(ReaderEntity readerEntity) {
        return ReaderDtoInfo.builder()
                .id(readerEntity.getId())
                .fullName(readerEntity.getName() + " " + readerEntity.getSurname() + " " +
                        readerEntity.getMiddleName())
                .phoneNumber(readerEntity.getPhoneNumber())
                .countBook(readerEntity.getBookEntities().size())
                .build();
    }
        @Override
        public ReaderRegisterDto toRegisterDto(ReaderEntity readerEntity) {
           return ReaderRegisterDto.builder()
                   .id(readerEntity.getId())
                   .surname(readerEntity.getSurname())
                   .name(readerEntity.getName())
                   .middleName(readerEntity.getMiddleName())
                   .phoneNumber(readerEntity.getPhoneNumber())
                   .dateOfBirth(readerEntity.getDateOfBirth())
                   .readerTicket(readerEntity.getReaderTicket())
                   .build();
        }
    }

