create table if not exists books(id int not null auto_increment primary key,isbn varchar(50) not null unique,title varchar(100) not null,author varchar(100) not null,book_edition varchar(50) not null,category ENUM("FICTION", "NON_FICTION") not null );
select * from books;
select * from books where isbn = ?;
insert into books(isbn, title, author, book_edition, category)values(?,?,?,?,?);

create table if not exists borrowedBooks(id int not null auto_increment primary key,book_id int not null unique,student_id int not null,borrowing_date date,return_date date,foreign key(book_id) references books(id),foreign key(student_id) references students(id));