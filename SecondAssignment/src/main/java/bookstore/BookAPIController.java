package bookstore;

import bookstore.dto.BookDTO;
import bookstore.dto.TransactionDTO;
import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.service.book.GoogleBookService;
import bookstore.service.book.LocalBookService;
import bookstore.service.transaction.TransactionService;
import bookstore.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
    public List<String> create(@RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult) {

        List<String> result;
        if(bindingResult.hasErrors()) {
            result=ErrorGenerator.getErrorsFromBinding(bindingResult);
        }
        else {
            result=new ArrayList<>();
            if(localBookService.create(bookDTO)!=null)
                result.add("Creating successful!");
            else
                result.add("Saving not successful!");
        }
        return result;

    }
    @RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
    public void delete(@RequestBody  String isbn) {
        localBookService.delete(isbn.substring(0, isbn.length() - 1));
    }

    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public List<String> update(@RequestBody BookDTO bookDTO,BindingResult bindingResult) {
        List<String> result;
        if(bindingResult.hasErrors()) {
            result=ErrorGenerator.getErrorsFromBinding(bindingResult);
        }
        else {
            result=new ArrayList<>();
            if(localBookService.update(bookDTO)!=null)
                result.add("Updating successful!");
            else
                result.add("Updating not successful!");
        }
        return result;
    }

    @RequestMapping(value = "/booksByName", method = RequestMethod.GET)
    public List<Book> findByName(@RequestBody String name) {
        return localBookService.findByName(name);
    }

    @RequestMapping(value = "/booksByGoogle", method = RequestMethod.GET)
    public List<Book> findByGoogle(@RequestBody String name) {
        return googleBookService.findSuggestion(name);
    }

    @RequestMapping(value = "/booksByGenre", method = RequestMethod.GET)
    public List<Book> findByGenre(@RequestBody String genre) {
        return localBookService.findByGenre(genre);
    }

    @RequestMapping(value = "/booksByAuthor", method = RequestMethod.GET)
    public List<Book> findByAuthor(@RequestBody Author author) {
        return localBookService.findByAuthor(author.getId());
    }

    @RequestMapping(value = "/sellBook", method = RequestMethod.POST)
    public Integer sellBook(@RequestBody @Valid TransactionDTO transactionDTO) {
        return transactionService.sellBook(transactionDTO);
    }

    @RequestMapping(value = "/generateReport", method = RequestMethod.POST)
    public void generateReport(@RequestBody String type) {
        reportService.generateOutOfStockReport(type.substring(0, type.length() - 1));
    }

    @RequestMapping(value = "/saveSuggestion", method = RequestMethod.POST)
    public void saveSuggestion(@RequestBody String googleISBN) {
        googleBookService.saveSuggestion(googleISBN.substring(0,googleISBN.length()-1));
    }


}
