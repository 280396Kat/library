create table if not exists book_reader(
book_reader_id_pk serial primary key,
first_name varchar,
surname varchar,
middle_name varchar,
phone_number varchar,
date_of_birth date
)