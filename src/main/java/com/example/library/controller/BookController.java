package com.example.library.controller;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookItemDto;
import com.example.library.entity.BookEntity;
import com.example.library.exception.NotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.BookEntityRepository;
import com.example.library.service.BookService;
import com.example.library.service.BookServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.Collection;
import java.util.List;

@RequestMapping("/books")
@RestController
@RequiredArgsConstructor
@Api(tags = "Контроллер для работы с книгами.")
public class BookController {

    private final BookServiceImpl bookService;
    private final BookMapper bookMapper;
    private final BookEntityRepository bookEntityRepository;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать книгу")
    public void save(@RequestBody BookItemDto bookItemDto) {
        BookEntity toEntity = bookMapper.toEntity(bookItemDto);
        BookEntity tmp = bookEntityRepository.save(toEntity);
        bookMapper.toDto(tmp);
    }

    // не поняла как писать контроллеры с мапперами немного. Через репозиторий? К нему, а не к сервисам обращаться?
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить из списка книгу, если читатель ее нам вернул")
    public void removeBookFromReadersList(@RequestParam Long bookId) {
        bookService.removeBookFromReadersList(bookId);
    }

    @GetMapping("/assign-book-to-reader")
    @Operation(summary = "Получить книгу и добавить ее читателю")
    public void assignBookToReader(@RequestParam Long bookId, @RequestParam Long readerId){
        bookService.assignBookToReader(bookId, readerId);
    }

    @PutMapping("/update")
    @Operation(summary = "Обновить книги")
    public BookItemDto update(@RequestBody BookItemDto bookItemDto) {
        return bookService.update(bookItemDto);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Получить все книги")
    public List<BookItemDto> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Получить книгу по id")
    public BookDto getBookDtoById(@PathVariable long id) {
        return bookService.getBookDtoById(id);
    }
}
