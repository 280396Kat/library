create table if not exists book(
book_id_pk serial primary key,
name varchar,
genre varchar,
name_author varchar,
start_read_date date,
end_read_date date,
reader_id_fk int references book_reader(book_reader_id_pk)
)