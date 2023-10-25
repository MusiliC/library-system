package com.ceetech.model;

import java.util.Objects;

public class Book {
    private  int id;
    private String ISBN;
    private String author;
    private String title;
    private  String edition;
    private  Category category;

    public Book() {
    }

    public Book(int id, String ISBN, String author, String title, String edition, Category category) {
        this.id = id;
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;
        this.edition = edition;
        this.category = category;
    }

    public Book(String ISBN, String author, String title, String edition, Category category) {
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;
        this.edition = edition;
        this.category = category;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", edition='" + edition + '\'' +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(ISBN, book.ISBN) && Objects.equals(author, book.author) && Objects.equals(title, book.title) && Objects.equals(edition, book.edition) && category == book.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ISBN, author, title, edition, category);
    }
}
