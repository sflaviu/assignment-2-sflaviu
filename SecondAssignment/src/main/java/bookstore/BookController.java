package bookstore;

import bookstore.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
    @Autowired
    AuthorService authorService;

    @RequestMapping("/admin")
    String admin()
    {
        return "admin";
    }

    @RequestMapping("/crudBooks")
    String crudBooks(Model model) {
        model.addAttribute("authors", authorService.getAll());
        return "crudBooks";
    }

    @RequestMapping("/crudUsers")
    String crudUsers() {
        return "crudUsers";
    }

    @RequestMapping("/user")
    String user(Model model) {
        model.addAttribute("authors", authorService.getAll());
        return "bookOperations";
    }

}
