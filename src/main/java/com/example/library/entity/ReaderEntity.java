package com.example.library.entity;

import lombok.*;

import javax.persistence.*;
import java.awt.print.Book;

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

    @ManyToOne
    @JoinColumn(name = "book_id_fk")
    private BookEntity bookEntity;
}
