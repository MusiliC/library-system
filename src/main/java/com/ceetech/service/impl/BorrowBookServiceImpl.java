package com.ceetech.service.impl;

import com.ceetech.db.DatabaseHandler;
import com.ceetech.model.Book;
import com.ceetech.model.BorrowedBook;
import com.ceetech.model.Student;
import com.ceetech.service.BookService;
import com.ceetech.service.BorrowBookService;
import com.ceetech.service.StudentService;
import com.ceetech.util.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

public class BorrowBookServiceImpl implements BorrowBookService {

    private final DatabaseHandler databaseHandler;
    private final Logger logger;
    private final StudentService studentService;
    private final BookService bookService;

    public BorrowBookServiceImpl(DatabaseHandler databaseHandler, Logger logger, StudentService studentService, BookService bookService) {
        this.databaseHandler = databaseHandler;
        this.logger = logger;
        this.studentService = studentService;
        this.bookService = bookService;
        //create table if not exists
        setupDB();
    }

    public void setupDB() {
        logger.info("Initializing table borrow book in DB..");


        try (Connection connection = databaseHandler.connect(Config.CONNECTION_URL, Config.DB_USER, Config.DB_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String query = "create table if not exists borrowedBooks(id int not null auto_increment primary key,book_id int not null unique,student_id int not null,borrowing_date date,return_date date,foreign key(book_id) references books(id),foreign key(student_id) references students(id));";
            statement.executeUpdate(query);

        } catch (ClassNotFoundException e) {
            logger.severe("Failed connecting to class " + e.getMessage());
            throw new RuntimeException(e);
        } catch (SQLException e) {
            logger.severe("Error when creating table " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BorrowedBook> getBorrowedBooks(String regNo) {
        return null;
    }

    @Override
    public boolean borrowBook(Book book, Student student) {
        Student dbStudentObject = studentService.getStudentByRegNo(student.getRegNo());
        Book dbBookObject = bookService.getBookByIsbn(book.getISBN());
        BorrowedBook borrowedBook = new BorrowedBook(dbBookObject, dbStudentObject, LocalDate.now(), null);

        logger.info("Saving borrowed in DB with title " + dbBookObject.getTitle());

        String booksQuery = "insert into borrowedBooks(book_id, student_id, borrowing_date)values(?,?,?);";
        try (Connection connection = databaseHandler.connect(Config.CONNECTION_URL, Config.DB_USER, Config.DB_PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(booksQuery)) {
            preparedStatement.setInt(1, borrowedBook.getBook().getId());
            preparedStatement.setInt(2, borrowedBook.getStudent().getId());
            preparedStatement.setString(3, borrowedBook.getBorrowedDate().toString());

            int noRowsInserted = preparedStatement.executeUpdate();
            return noRowsInserted > 0;

        } catch (SQLException | ClassNotFoundException e) {
            logger.info("Error when saving borrowed book " + e.getMessage());
            return false;
        }


    }

    @Override
    public boolean returnBook(Book book) {
        return false;
    }
}
