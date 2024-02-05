package com.example.library.repository;

import com.example.library.entity.BookEntity;
import com.example.library.entity.ReaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {
    @Query(value = "select * from book where name_author ilike :author%", nativeQuery = true)
    List<BookEntity> findBookByNameAuthor(@Param("author") String authorName);

    @Query(value = "select * from book where name ilike :name% and name_author ilike :author% " +
            "and genre ilike :genre%" , nativeQuery = true)
    List<BookEntity> findBooksByFilter(@Param("name") String name,
                                       @Param("author") String author,
                                       @Param("genre") String genre);


}
