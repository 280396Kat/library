package com.example.library.service;

import com.example.library.entity.BookEntity;
import com.example.library.repository.BookEntityRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookEntityRepository bookEntityRepository;

    public BookServiceImpl(BookEntityRepository bookEntityRepository) {
        this.bookEntityRepository = bookEntityRepository;
    }

    @Override
    public BookEntity save(BookEntity bookEntity) {
        return bookEntityRepository.save(bookEntity);
    }

    @Override
    public boolean deleteById(long id) {
        Optional<BookEntity> getBookEntity = bookEntityRepository.findById(id);
        if (getBookEntity.isEmpty()) {
            return false;
        }
        BookEntity bookEntity = getBookEntity.get();
        bookEntityRepository.delete(bookEntity);
        return true;
    }

    @Override
    public BookEntity update(long id, BookEntity bookEntity) {
        BookEntity bookEntity1 = bookEntityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No book"));
        bookEntity1.setName(bookEntity.getName());
        bookEntity1.setNameAuthor(bookEntity.getNameAuthor());
        bookEntity1.setGenre(bookEntity.getGenre());
        return bookEntityRepository.save(bookEntity1);
    }

    @Override
    public BookEntity findById(long id) {
        return bookEntityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No book"));
    }

    @Override
    public List<BookEntity> findAll() {
        return bookEntityRepository.findAll();
    }


}
