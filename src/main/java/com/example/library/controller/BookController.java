package com.example.library.controller;

import com.example.library.entity.BookEntity;
import com.example.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.Collection;
import java.util.List;

@RequestMapping("/book")
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public BookEntity save(@RequestBody BookEntity bookEntity) {
        return bookService.save(bookEntity);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteById(@RequestParam long id) {
        return bookService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public BookEntity update(@PathVariable long id, @RequestBody BookEntity bookEntity) {
        return bookService.update(id, bookEntity);
    }

    @GetMapping("/find/{id}")
    public BookEntity getById(@PathVariable long id) {
        return bookService.findById(id);
    }

    @GetMapping("/getAll")
    public List<BookEntity> getAll() {
        return bookService.findAll();
    }

}
