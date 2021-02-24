package application.booklibrary;

import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@RestController
public class LibraryController {
    private final LibraryRepository repository;
    private Validator validator;

    public LibraryController(LibraryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/books")
    public List<Book> all() {
        return repository.findAll();
    }

    @PostMapping("/books")
    public Book add(@RequestBody Book newBook) {
        return repository.save(newBook);
    }

    @GetMapping("/books/{id}")
    public Book one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/books/{id}")
    public Book update(@RequestBody Book newBook, @PathVariable Long id) {
        return repository.findById(id).map(book -> {
            book.setBook_Name(newBook.getBook_Name());
            book.setAuthor(newBook.getAuthor());
            book.setPublication_Year(newBook.getPublication_Year());
            return repository.save(book);
        }).orElseGet(() -> {
            newBook.setId(id);
            return repository.save(newBook);
        });
    }
}
