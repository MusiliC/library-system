package com.ceetech.model;

import java.time.LocalDate;
import java.util.Objects;

public class BorrowedBook {

    private  Book book;
    private Student student;

    private LocalDate borrowedDate;

    private  LocalDate returnDate;

    public BorrowedBook() {
    }

    public BorrowedBook(Book book, Student student, LocalDate borrowedDate, LocalDate returnDate) {
        this.book = book;
        this.student = student;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "BorrowedBook{" +
                "book=" + book +
                ", student=" + student +
                ", borrowedDate=" + borrowedDate +
                ", returnDate=" + returnDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowedBook that = (BorrowedBook) o;
        return Objects.equals(book, that.book) && Objects.equals(student, that.student) && Objects.equals(borrowedDate, that.borrowedDate) && Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, student, borrowedDate, returnDate);
    }
}
