package bookstore.service.integration;

import bookstore.Application;
import bookstore.entity.Book;
import bookstore.service.book.GoogleBookService;
import bookstore.service.book.LocalBookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@SpringBootApplication

public class GoogleBookServiceImplTest {

    @Autowired
    private GoogleBookService googleBookService;

    @Autowired
    private LocalBookService localBookService;

    private String searchField="Mihai Eminescu";

    @Test
    public void checkSearch()
    {
        Assert.assertTrue(googleBookService.findSuggestion(searchField).size()>0);
    }

    @Test
    public void checkSave()
    {
        String foundIsbn=googleBookService.findSuggestion(searchField).get(0).getIsbn();
        googleBookService.saveSuggestion(foundIsbn);

        List<Book> allBooks=localBookService.getAll();

        Boolean foundBook=false;
        for(Book q:allBooks)
            if(q.getIsbn().equals(foundIsbn))
                foundBook=true;

        Assert.assertTrue(foundBook);

        localBookService.delete(foundIsbn);
    }


}
