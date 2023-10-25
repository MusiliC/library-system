package com.ceetech.service;

import com.ceetech.exception.BookNotFoundException;
import com.ceetech.model.Book;

import java.util.List;

public interface BookService {
    List<Book> GetAllBooks() throws  RuntimeException;
    Book getBookByIsbn(String isbn) throws BookNotFoundException;
    boolean saveBook(Book book) throws  RuntimeException;
}
