package com.ceetech.service;

import com.ceetech.model.Book;
import com.ceetech.model.BorrowedBook;
import com.ceetech.model.Student;

import java.util.List;

public interface BorrowBookService {
    List<BorrowedBook> getBorrowedBooks(String regNo);
    boolean borrowBook(Book book, Student student);
    boolean returnBook(Book book);


}
