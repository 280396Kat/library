package com.example.library.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Reader;
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

    private String nameAuthor;

    private String genre;

    @OneToMany(mappedBy = "bookEntity")
    private List<ReaderEntity> readers = new ArrayList<>();
}
