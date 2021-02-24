package application.booklibrary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    @GetMapping("/book")
    public String inputForm(Model model) {
        model.addAttribute("book",new Book());
        return "book";
    }
    @PostMapping("/book")
    public String inputSubmit(@ModelAttribute Book book, Model model) {
        model.addAttribute("book", book);
        return "result";
    }
}
