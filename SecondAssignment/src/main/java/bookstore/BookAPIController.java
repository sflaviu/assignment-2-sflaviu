package bookstore;

import bookstore.dto.BookDTO;
import bookstore.dto.TransactionDTO;
import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.service.book.GoogleBookService;
import bookstore.service.book.LocalBookService;
import bookstore.service.TransactionService;
import bookstore.service.report.ReportGenerator;
import bookstore.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Catalysts on 8/9/2015.
 */
@RestController
public class BookAPIController {
    @Autowired
    LocalBookService localBookService;

    @Autowired
    GoogleBookService googleBookService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getAll() {
        return localBookService.getAll();
    }

    @RequestMapping(value = "/createBook", method = RequestMethod.POST)
    public String create(@RequestBody @Valid BookDTO book) {
        localBookService.create(book);
        return "redirect:createBook?success";
    }
    @RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
    public String delete(@RequestBody String isbn) {
        localBookService.delete(isbn.substring(0, isbn.length() - 1));
        return "redirect:deleteBook?success";
    }
    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public String update(@RequestBody BookDTO book) {
        localBookService.update(book);
        return "redirect:updateBook?success";
    }

    @RequestMapping(value = "/booksByName", method = RequestMethod.GET)
    public List<Book> findByName(String name) {
        return localBookService.findByName(name);
    }

    @RequestMapping(value = "/booksByGoogle", method = RequestMethod.GET)
    public List<Book> findByGoogle(String name) {
        return googleBookService.findSuggestion(name);
    }

    @RequestMapping(value = "/booksByGenre", method = RequestMethod.GET)
    public List<Book> findByGenre(String genre) {
        return localBookService.findByGenre(genre);
    }

    @RequestMapping(value = "/booksByAuthor", method = RequestMethod.GET)
    public List<Book> findByAuthor(Author author) {
        return localBookService.findByAuthor(author.getId());
    }

    @RequestMapping(value = "/sellBook", method = RequestMethod.POST)
    @ResponseBody
    public Integer sellBook(@RequestBody @Valid TransactionDTO transactionDTO) {
        return transactionService.sellBook(transactionDTO);
    }

    @RequestMapping(value = "/generateReport", method = RequestMethod.POST)
    public String generateReport(@RequestBody String type) {
        reportService.generateOutOfStockReport(type.substring(0, type.length() - 1));
        return "redirect:generateReport?success";
    }


}
