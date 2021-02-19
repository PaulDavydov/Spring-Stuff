package application.booklibrary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

//Correlates with a table in the database
@Entity
public class Book {
    private @Id
    @GeneratedValue Long id;
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
    public void setBook_Name(String name) {
        this.book_Name = name;
    }
    public void setAuthor(String word) {
        this.author = word;
    }
    public void setPublication_Year(int year) {
        this.publication_Year = year;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof Book)) {
            return false;
        }

        Book bookObject = (Book) o;
        return Objects.equals(this.id,bookObject.id) && Objects.equals(this.book_Name,bookObject.book_Name)
                && Objects.equals(this.author,bookObject.author)
                && Objects.equals(this.publication_Year,bookObject.publication_Year);
    }
     public int hashCode() {
        return Objects.hash(this.id,this.book_Name,this,author,this.publication_Year);
     }
     public String toString() {
        return "Books in Library(" + "id= " + this.id + ", BookName= " + this.book_Name +
                ", AuthorName= " + this.author + ", PublicationYear= " + this.publication_Year +
                ")";
     }
}
