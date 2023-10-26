package com.ceetech.service.impl;

import com.ceetech.db.DatabaseHandler;
import com.ceetech.model.Book;
import com.ceetech.model.BorrowedBook;
import com.ceetech.model.Student;
import com.ceetech.service.BorrowBookService;
import com.ceetech.util.Config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

public class BorrowBookServiceImpl implements BorrowBookService {

    private DatabaseHandler databaseHandler;
    private Logger logger;

    public BorrowBookServiceImpl(DatabaseHandler databaseHandler, Logger logger) {
        this.databaseHandler = databaseHandler;
        this.logger = logger;
        //create table if not exists
        setupDB();
    }

    public void setupDB() {
        logger.info("Initializing table borrow book in DB..");


        try (Connection connection = databaseHandler.connect(Config.CONNECTION_URL, Config.DEFAULT_USERNAME, Config.DB_PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            String query = "create table if not exists books(id int not null auto_increment primary key,isbn varchar(50) not null unique,title varchar(100) not null,author varchar(100) not null,book_edition varchar(50) not null,category ENUM('FICTION', 'NON_FICTION') not null );";
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
        return false;
    }

    @Override
    public boolean returnBook(Book book) {
        return false;
    }
}
