package com.example.library.entity;

import lombok.*;

import javax.persistence.*;
import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "book_reader")
public class ReaderEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_reader_id_pk")
    private Long id;

    @Column(name = "first_name")
    private String name;

    private String surname;

    private String middleName;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private String readerTicket;

    @OneToMany(mappedBy = "readerEntity")
    private List<BookEntity> bookEntities = new ArrayList<>();
}
