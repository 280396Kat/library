package com.example.library.controller;

import com.example.library.dto.ReaderDto;
import com.example.library.dto.ReaderDtoInfo;
import com.example.library.dto.ReaderRegisterDto;
import com.example.library.dto.filter.ReaderDtoFilter;
import com.example.library.entity.ReaderEntity;
import com.example.library.service.ReaderService;
import com.example.library.service.ReaderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.io.Reader;
import java.util.Collection;
import java.util.List;

@RequestMapping("/readers")
@RestController
@RequiredArgsConstructor
@Api(tags = "Контроллер для работы с читателями.")
public class ReaderController {

    private final ReaderServiceImpl readerService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать читателя")
    public void save(@RequestBody ReaderRegisterDto readerRegisterDto) {
        readerService.save(readerRegisterDto);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить читателя")
    public void deleteById(@RequestParam long id) {
        readerService.deleteById(id);
    }

    @PutMapping("/update")
    @Operation(summary = "Обновить информацию о читателе")
    public ReaderRegisterDto update(@RequestBody ReaderRegisterDto readerRegisterDto) {
        return readerService.update(readerRegisterDto);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Получить читателя по id")
    public ReaderDto getById(@PathVariable long id) {
        return readerService.getById(id);
    }

    @GetMapping("/get-reader-entity-by-first-name-and-surname")
    @Operation(summary = "Получить имя и фамилию читателя")
    public List<ReaderDto> getReaderEntityByFirstNameAndSurname(@RequestParam String firstName, @RequestParam String surname) {
        return readerService.getReaderEntityByFirstNameAndSurname(firstName, surname);
    }
    @PostMapping("/get-reader-by-filter")
    @Operation(summary = "")
    public List<ReaderDtoInfo> getReaderByFilter(@RequestBody ReaderDtoFilter readerDtoFilter) {
        return readerService.getReaderByFilter(readerDtoFilter);
    }

}
