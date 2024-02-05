package com.example.library.controller;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookItemDto;
import com.example.library.entity.BookEntity;
import com.example.library.exception.NotFoundException;
import com.example.library.service.BookService;
import com.example.library.service.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.Collection;
import java.util.List;

@RequestMapping("/book")
@RestController
public class BookController {

    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody BookItemDto bookItemDto) {
        bookService.save(bookItemDto);
    }


 /*   @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteById(@RequestParam long id) {
        return true;
    }*/

    @PutMapping("/update/{id}")
    public BookItemDto update(@RequestBody BookItemDto bookItemDto) {
        return bookService.update(bookItemDto);
    }


    @GetMapping("/getAll")
    public List<BookItemDto> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/find/{id}")
    public BookDto getById(@PathVariable long id) {
        return bookService.getBookDtoById(id);
    }
}
