package bookstore.service.integration;

import bookstore.Application;
import bookstore.dto.AuthorDTO;
import bookstore.dto.BookDTO;
import bookstore.dto.TransactionDTO;
import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.service.author.AuthorService;
import bookstore.service.book.LocalBookService;
import bookstore.service.transaction.TransactionService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@SpringBootApplication

public class TransactionServiceImplTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private LocalBookService bookService;

    @Autowired
    private AuthorService authorService;

    Book testBook;
    Author testAuthor;

    @Before
    public void setUp()
    {
        AuthorDTO authorDTO=new AuthorDTO();
        authorDTO.setName("Test Author");
        testAuthor=authorService.create(authorDTO);

        BookDTO bookDTO=new BookDTO();
        bookDTO.name="Test Book";
        bookDTO.authorId=testAuthor.getId();
        bookDTO.isbn="59315";
        bookDTO.genre="Test genre";
        bookDTO.quantity=30;
        bookDTO.price=10;
        testBook=bookService.create(bookDTO);
    }

    @Test
    public void checkTransactions()
    {
        TransactionDTO successTransactionDTO=new TransactionDTO();
        successTransactionDTO.isbn="59315";
        successTransactionDTO.quantity=10;

        Assert.assertTrue(transactionService.sellBook(successTransactionDTO)==10*testBook.getPrice());

        TransactionDTO failTransactionDTO=new TransactionDTO();
        successTransactionDTO.isbn="59315";
        successTransactionDTO.quantity=30;

        Assert.assertFalse(transactionService.sellBook(successTransactionDTO)==30*testBook.getPrice());

    }

    @After
    public void clean()
    {
        bookService.delete("59315");
    }

}
