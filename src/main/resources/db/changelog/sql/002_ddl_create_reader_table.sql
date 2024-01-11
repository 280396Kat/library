create table if not exists reader(
reader_id_pk serial primary key,
name varchar,
book_id_fk int references book(book_id_pk)
)