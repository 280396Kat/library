package com.example.library.repository;

import com.example.library.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {
    @Query(value = "select * from book where name_author ilike :author%", nativeQuery = true)
    List<BookEntity> findBookByNameAuthor(@Param("author") String authorName);
}
