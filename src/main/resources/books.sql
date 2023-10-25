create table if not exists books(id int not null auto_increment primary key,isbn varchar(50) not null unique,title varchar(100) not null,author varchar(100) not null,book_edition varchar(50) not null,category ENUM("FICTION", "NON_FICTION") not null );
select * from books;
select * from books where isbn = ?;
insert into books(isbn, title, author, book_edition, category)values(?,?,?,?,?);