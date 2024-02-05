package com.example.library.controller;

import com.example.library.dto.ReaderDto;
import com.example.library.dto.ReaderRegisterDto;
import com.example.library.entity.ReaderEntity;
import com.example.library.service.ReaderService;
import com.example.library.service.ReaderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.io.Reader;
import java.util.Collection;
import java.util.List;

@RequestMapping("/reader")
@RestController
public class ReaderController {

    private final ReaderServiceImpl readerService;

    @Autowired
    public ReaderController(ReaderServiceImpl readerService) {
        this.readerService = readerService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ReaderRegisterDto readerRegisterDto) {
        readerService.save(readerRegisterDto);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@RequestParam long id) {
        readerService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ReaderRegisterDto update(@RequestBody ReaderRegisterDto readerRegisterDto) {
        return readerService.update(readerRegisterDto);
    }

    @GetMapping("/find/{id}")
    public ReaderDto getById(@PathVariable long id) {
        return readerService.getById(id);
    }

    @GetMapping("/getAll")
    public List<ReaderEntity> getAll() {
        return readerService.getAll();
    }

    @GetMapping("/getReaderEntityByFirstNameAndSurname")
    public List<ReaderDto> getReaderEntityByFirstNameAndSurname(@RequestParam String firstName, @RequestParam String surname) {
        return readerService.getReaderEntityByFirstNameAndSurname(firstName, surname);
    }

}
