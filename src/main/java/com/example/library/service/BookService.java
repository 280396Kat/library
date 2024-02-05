package com.example.library.service;

import com.example.library.dto.BookDto;
import com.example.library.dto.filter.BookDtoFilter;
import com.example.library.entity.BookEntity;



import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity save(BookEntity bookEntity);

    boolean updateById(long id);

     BookEntity update(long id, BookEntity bookEntity);

    BookEntity getById(long id);

    List<BookEntity> getAll();

    List<BookDto> getBooksByFilter(BookDtoFilter bookDtoFilter);

    BookDto getBookDtoById(long id);
}
