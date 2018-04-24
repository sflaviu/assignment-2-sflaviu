package bookStore;

import bookStore.dto.BookDTO;
import bookStore.entity.Author;
import bookStore.entity.Book;
import bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalysts on 8/9/2015.
 */
@RestController
public class BookAPIController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @RequestMapping(value = "/createBook", method = RequestMethod.POST)
    public String create(@RequestBody @Valid BookDTO book) {
        bookService.create(book);
        return "redirect:createBook?successCreate";
    }
    @RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
    public String delete(@RequestBody String isbn) {
        bookService.delete(isbn.substring(0, isbn.length() - 1));
        return "redirect:books?successDelete";
    }
    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public String update(@RequestBody BookDTO book) {
        bookService.update(book);
        return "redirect:books?succesUpdate";
    }

    @RequestMapping(value = "/booksByName", method = RequestMethod.GET)
    public List<Book> findByName(String name) {
        return bookService.findByName(name);
    }

    @RequestMapping(value = "/booksByGenre", method = RequestMethod.GET)
    public List<Book> findByGenre(String genre) {
        return bookService.findByGenre(genre);
    }
    @RequestMapping(value = "/booksByAuthor", method = RequestMethod.GET)
    public List<Book> findByAuthor(Author author) {
        return bookService.findByAuthor(author.getId());
    }
}
