package application.booklibrary;

import javax.persistence.GeneratedValue;

public class Book {
    private @GeneratedValue Long id;
    private String book_Name;
    private String author;
    private int publication_Year;

    public Book () {}
    public Book(String name, String author, int year) {
        this.book_Name = name;
        this.author = author;
        this.publication_Year = year;
    }

    public Long getId() {
        return this.id;
    }
    public String getBook_Name() {
        return this.book_Name;
    }
    public String getAuthor() {
        return this.author;
    }
    public int getPublication_Year() {
        return this.publication_Year;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
