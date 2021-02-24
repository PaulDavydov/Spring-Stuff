package application.booklibrary;

public class BookNotFoundException extends RuntimeException{
    BookNotFoundException(Long id) {
        super("Could not find the book by the id= " + id);
    }
}
