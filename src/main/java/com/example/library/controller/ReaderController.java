package com.example.library.controller;

import com.example.library.dto.ReaderDto;
import com.example.library.entity.ReaderEntity;
import com.example.library.service.ReaderService;
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

    private final ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ReaderEntity save(@RequestBody ReaderEntity readerEntity) {
        return readerService.save(readerEntity);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteById(@RequestParam long id) {
        return readerService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ReaderEntity update(@PathVariable long id, @RequestBody ReaderEntity readerEntity) {
        return readerService.update(id, readerEntity);
    }

    @GetMapping("/find/{id}")
    public ReaderEntity getById(@PathVariable long id) {
        return readerService.findById(id);
    }

    @GetMapping("/getAll")
    public List<ReaderEntity> getAll() {
        return readerService.findAll();
    }

    @GetMapping("/getReaderEntityByFirstNameAndSurname")
    public List<ReaderDto> getReaderEntityByFirstNameAndSurname(@RequestParam String firstName, @RequestParam String surname) {
        return readerService.getReaderEntityByFirstNameAndSurname(firstName, surname);
    }

}
