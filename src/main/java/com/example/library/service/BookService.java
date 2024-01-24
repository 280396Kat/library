package com.example.library.service;

import com.example.library.entity.BookEntity;



import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity save(BookEntity bookEntity);

    boolean deleteById(long id);

     BookEntity update(long id, BookEntity bookEntity);

    BookEntity findById(long id);

    List<BookEntity> findAll();

}
