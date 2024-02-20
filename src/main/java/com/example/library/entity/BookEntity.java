package com.example.library.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id_pk")
    private Long id;

    private String name;

    private String genre;

    private String nameAuthor;

    @Column(name = "start_read_date")
    private LocalDate startDate;

    @Column(name = "end_read_date")
    private LocalDate finishDate;

    @ManyToOne
    @JoinColumn(name = "reader_id_fk")
    private ReaderEntity readerEntity;
}
