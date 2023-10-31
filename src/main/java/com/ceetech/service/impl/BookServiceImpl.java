package com.ceetech.service.impl;

import com.ceetech.db.DatabaseHandler;
import com.ceetech.exception.BookNotFoundException;
import com.ceetech.model.Book;
import com.ceetech.model.Category;
import com.ceetech.service.BookService;
import com.ceetech.util.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BookServiceImpl implements BookService {

    private DatabaseHandler databaseHandler;
    private Logger logger;

    public BookServiceImpl(DatabaseHandler databaseHandler, Logger logger) {
        this.databaseHandler = databaseHandler;
        this.logger = logger;
        //create table if not exists
        setupDB();
    }

    public void setupDB() {
        logger.info("Initializing table book in DB..");


        try (Connection connection = databaseHandler.connect(Config.CONNECTION_URL, Config.DB_USER, Config.DB_PASSWORD);
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
    public List<Book> GetAllBooks() throws RuntimeException {
        List<Book> books = new ArrayList<>();
        logger.info("Getting all books");
        try (Connection connection = databaseHandler.connect(Config.CONNECTION_URL, Config.DB_USER, Config.DB_PASSWORD); Statement statement = connection.createStatement()) {
            String booksQuery = "select * from books;";
            ResultSet resultSet = statement.executeQuery(booksQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String isbn = resultSet.getString("isbn");
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                String edition = resultSet.getString("book_edition");
                Category category = Category.valueOf(resultSet.getString("category"));

                Book book = new Book(id, isbn, author, title, edition, category);
                books.add(book);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
        return books;
    }

    @Override
    public Book getBookByIsbn(String isbn) throws BookNotFoundException {
        logger.info("Getting book by isbn " + isbn);
        String booksQuery = "SELECT * FROM books WHERE isbn = ?;";
        try (Connection connection = databaseHandler.connect(Config.CONNECTION_URL, Config.DB_USER, Config.DB_PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(booksQuery)) {
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery(booksQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                String edition = resultSet.getString("book_edition");
                Category category = Category.valueOf(resultSet.getString("category"));
                return new Book(id, isbn, author, title, edition, category);
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.info("Error wen fetching book " + e.getMessage());
            throw new RuntimeException(e);
        }
        throw new BookNotFoundException("Book not found with this " + isbn + " isbn");
    }

    @Override
    public boolean saveBook(Book book) throws RuntimeException {
        logger.info("Saving book " + book.getTitle());
        String booksQuery = "insert into books(isbn, title, author, book_edition, category)values(?,?,?,?,?);";
        try (Connection connection = databaseHandler.connect(Config.CONNECTION_URL, Config.DB_USER, Config.DB_PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(booksQuery)) {
            preparedStatement.setString(1, book.getISBN());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getEdition());
            preparedStatement.setString(5, book.getCategory().name());
            int noRowsInserted = preparedStatement.executeUpdate();
            return noRowsInserted > 0;

        } catch (SQLException | ClassNotFoundException e) {
            logger.info("Error when fetching book " + e.getMessage());
            return false;
        }

    }
}
