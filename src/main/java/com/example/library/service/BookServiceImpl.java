package com.example.library.service;

import com.example.library.dto.BookDto;
import com.example.library.dto.BookItemDto;
import com.example.library.entity.BookEntity;
import com.example.library.entity.ReaderEntity;
import com.example.library.exception.NotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.BookEntityRepository;
import com.example.library.repository.ReaderEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl {
    private final BookEntityRepository bookEntityRepository;
    private final ReaderEntityRepository readerEntityRepository;
    private final BookMapper bookMapper;


    public void save(BookItemDto bookItemDto) {
        BookEntity bookEntity = bookMapper.toEntity(bookItemDto);
        bookEntityRepository.save(bookEntity);
    }

    // Удалить из списка книгу, если читатель ее нам вернул.
    public void removeBookFromReadersList(Long bookId) {
        BookEntity bookEntity = bookEntityRepository.findById(bookId) // достаем книгу из репозитория
                .orElseThrow(() -> new NotFoundException(String.format("Book is not found id = %s", bookId)));
        if (bookEntity.getReaderEntity() != null) { // проверяем, есть ли у неее читатель
            bookEntity.setReaderEntity(null); // если книга имеет читателя,то обнуляем поле readerEntity и сохраняемв репозитории
            bookEntityRepository.save(bookEntity);
        } else {
            throw new NotFoundException("Book is not assigned to any reader.");
        }
    }

    // Из списка книг, нужно выбрать книгу и назначить читателю, если у него нет ее на руках
    public void assignBookToReader(Long bookId, Long readerId) {
        BookEntity bookEntity = bookEntityRepository.findById(bookId) // достаем книгу
                .orElseThrow(() -> new NotFoundException(String.format("Book is not found id = %s", bookId)));
        ReaderEntity readerEntity = readerEntityRepository.findById(readerId) // достаем читателя
                .orElseThrow(() -> new NotFoundException(String.format("Reader is not found id = %s", readerId)));
        if (bookEntity.getReaderEntity() == null) { // проверяем, что книга не имеет читателя
            List<BookEntity> readerBooks = readerEntity.getBookEntities();// получаем список книг, которые есть у читателя
            boolean bookAlreadyAssigned = readerBooks.stream() // проверяем, назначена ли книга читателю
                    .anyMatch(book -> book.getId().equals(bookId)); // anyMatch(есть ли в с стриме хоть один подходящий
            // элемент проверяет, есть ли в триме книга с нужным id
            if (!bookAlreadyAssigned) {
                bookEntity.setReaderEntity(readerEntity); // назначаем книгу
                bookEntityRepository.save(bookEntity); // сохраняем
            } else {
                throw new NotFoundException("There is already such a book");
            }
        }
    }

    public void updateReaderBookById(List<Long> id) { // а этот метод для чего? Он обновляет/получает список книг, которые есть у читателя?
        id.forEach(bookId -> {
            BookEntity bookEntity = bookEntityRepository.findById(bookId)
                    .orElseThrow(() -> new NotFoundException(String.format("Book is not found id = %s", id)));
            bookEntity.setReaderEntity(null);
            bookEntityRepository.save(bookEntity);
        });
    }

    public BookItemDto update(BookItemDto bookItemDto) {
        BookEntity bookEntity = bookEntityRepository.findById(bookItemDto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Book is not found id = %s", bookItemDto)));
        bookEntity.setName(bookItemDto.getName());
        bookEntity.setNameAuthor(bookItemDto.getAuthor());
        bookEntity.setGenre(bookItemDto.getGenre());
        bookEntity.setStartDate(bookItemDto.getStartDate());
        bookEntity.setFinishDate(bookItemDto.getFinishDate());
        // BookEntity entitySave = bookEntityRepository.save(bookEntity);
        return bookEntityRepository.findById(bookEntity.getId())
                .map(bookMapper::toItemDto)
                .orElseThrow(() -> new NotFoundException(String.format("Book is not found id = %s", bookItemDto)));
    }
      /*  BookItemDto bookItem = BookItemDto.builder()
                .id(entitySave.getId())
                .name(entitySave.getName())
                .author(entitySave.getNameAuthor())
                .genre(entitySave.getGenre())
                .startDate(entitySave.getStartDate())
                .finishDate(entitySave.getFinishDate())
                .build();*/


    public List<BookItemDto> getAll() {
        return bookEntityRepository.findAll().stream()
                .map(bookMapper::toItemDto)
                .collect(Collectors.toList());
    }

    /*  .stream()
      .map(bookEntity -> {
          BookItemDto bookItemDto = new BookItemDto();
          bookItemDto.setName(bookItemDto.getName());
          bookItemDto.setAuthor(bookItemDto.getAuthor());
          bookItemDto.setGenre(bookItemDto.getGenre());
          bookItemDto.setStartDate(bookItemDto.getStartDate());
          bookItemDto.setFinishDate(bookItemDto.getFinishDate());
          return bookItemDto;
      })
      .collect(Collectors.toList());*/
    public BookDto getBookDtoById(long id) {
        return bookEntityRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Book is not found id = %s", id)));
    }
}





