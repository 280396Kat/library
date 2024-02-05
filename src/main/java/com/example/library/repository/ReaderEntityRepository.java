package com.example.library.repository;

import com.example.library.entity.ReaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReaderEntityRepository extends JpaRepository<ReaderEntity, Long> {
    @Query(value = "select *  from book_reader\n" +
            "where book_reader.first_name ilike %:firstName%\n" +
            "AND (book_reader.surname ilike %:surname%\n" +
            "OR book_reader.surname is null)", nativeQuery = true)
    List<ReaderEntity> findReaderEntityByFirstNameAndSurname(@Param(value = "firstName") String firstName,
                                                             @Param(value = "surname") String surname);

    @Query(value = "select * from book_reader\n" +
            "         join public.book b on b.book_id_pk = book_reader.book_id_fk\n" +
            "         where surname ilike :surname%", nativeQuery = true)
    List<ReaderEntity> findReaderByLastName(@Param("surname") String surname);
}
