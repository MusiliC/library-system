package com.ceetech.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.ceetech.model.Book;
import com.ceetech.model.Category;
import com.ceetech.model.Student;
import com.ceetech.service.AuthenticationService;
import com.ceetech.service.LibraryService;
import com.ceetech.util.Config;

public class LibraryServiceImpl implements LibraryService {

    private Student Student;
    private Book Book;
    private AuthenticationService authenticationService;
    private BookServiceImpl bookServiceImpl;
    private Logger logger;

    private StudentServiceImpl studentServiceImpl;

    Scanner scanner;

    public LibraryServiceImpl(AuthenticationService authenticationService, BookServiceImpl bookServiceImpl,
            StudentServiceImpl studentServiceImpl,
            Logger logger) {
        scanner = new Scanner(System.in);
        this.authenticationService = authenticationService;
        this.bookServiceImpl = bookServiceImpl;
        this.studentServiceImpl = studentServiceImpl;
        this.logger = logger;

    }

    @Override
    public void startApplication() {
        logger.info("In Library Management System");
        boolean isLoggedIn = authenticateUser();
        logger.info("User authenticated: " + isLoggedIn);
        System.out.println();
        getBookIsbn();
        // allBooks();
        // saving student
        // Student addStudent = newStudent();
        // studentServiceImpl.saveStudent(addStudent);
        // saving a book
        // Book addBook = newBook();
        // bookServiceImpl.saveBook(addBook);
        //Get all books

    }

    // private void showMenu() {
    //     System.out.println("---------------");
    //     System.out.println();
    //     System.out.println("LIBRARY MANAGEMENT SYSTEM");
    //     System.out.println();
    //     System.out.println("---------------");
    //     System.out.println();
    //     System.out.println("1. BORROW A BOOK");
    //     System.out.println("2. VIEW BORROWED BOOKS");
    //     System.out.println("3. RETURN A BOOK");
    //     System.out.println("4. QUIT");
    //     System.out.println();
    // }

    // private Student newStudent() {
   
    //     System.out.print("Enter student reg number: ");
    //     String regNo = scanner.nextLine();
    //     System.out.print("Enter student name: ");
    //     String studentName = scanner.nextLine();

    //     Student = new Student(regNo, studentName);

    //     return Student;
    // }

    // private Book newBook() {
   
      
    //     System.out.print("Enter book ISBN: ");
    //     String ISBN = scanner.nextLine();
    //     System.out.print("Enter book author: ");
    //     String author = scanner.nextLine();
    //     System.out.print("Enter book title: ");
    //     String title = scanner.nextLine();
    //     System.out.print("Enter book edition: ");
    //     String edition = scanner.nextLine();
    //     System.out.println("Enter the category (FICTION, NON_FICTION): ");
    //     String category = scanner.nextLine().toUpperCase();
    //     Category selectedCategory = Category.valueOf(category);
    //     Book = new Book(ISBN, author, title, edition, selectedCategory);

    //     return Book;
    // }

    // private void allBooks(){
    //     System.out.println("All books in the library");
    //     System.out.println();
    //    List<Book> books = bookServiceImpl.GetAllBooks();
    //     System.out.println(books);
    //     System.out.println("Id " + " ISBN " + " Author " + " Title " + " Edition " + " Category ");

    // }

    private void getBookIsbn(){
        System.out.print("Enter book ISBN number: ");
        String ISBN = scanner.nextLine();
        Book retrievedBook = bookServiceImpl.getBookByIsbn(ISBN);
        System.out.println(retrievedBook);
    }

    private boolean authenticateUser() {
        System.out.println();
        logger.info("Authenticating the user");

        int loginEntries = 1;

        while (loginEntries <= Config.LOGIN_RETRIES_LIMIT) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            try {
                if (authenticationService.authenticate(username, password)) {
                    return true;
                }
                loginEntries++;
                logger.info("Wrong credentials!");
                System.out.println();
            } catch (ClassNotFoundException e) {
                logger.info("Driver class error " + e.getMessage());
                e.printStackTrace();
                return false;
            } catch (SQLException e) {
                logger.info("Error communicating to db " + e.getMessage());
                e.printStackTrace();
                return false;
            }

        }
        return false;
    }

}
