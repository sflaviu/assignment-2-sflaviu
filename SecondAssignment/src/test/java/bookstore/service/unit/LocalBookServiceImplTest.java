package bookstore.service.unit;

import bookstore.dto.BookDTO;
import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.repository.BookRepository;
import bookstore.service.author.AuthorService;
import bookstore.service.book.LocalBookService;
import bookstore.service.book.LocalBookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocalBookServiceImplTest {

    private LocalBookService localBookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService;

    private Book testBook;
    private BookDTO testUserDTO;

    public Book createdBook;

    private String title="Cartea testata";

    @Before
    public void initialize() {
        localBookService = new LocalBookServiceImpl(bookRepository, authorService);

        Author a=new Author("Autorul Testat");

        testBook=new Book(title,a,"12345","Genul testat",20,50);
        testBook.setId(1);
        when( bookRepository.save(any(Book.class))).thenReturn(testBook);

        testUserDTO=new BookDTO();
        testUserDTO.authorId=a.getId();
        testUserDTO.genre="Genul testat";
        testUserDTO.isbn="12345";
        testUserDTO.price=50;
        testUserDTO.quantity=20;

        List<Book> foundByName=new ArrayList<>();
        foundByName.add(testBook);
        when(bookRepository.findByName(title)).thenReturn(foundByName);

        createdBook=localBookService.create(testUserDTO);
    }

    @Test
    public void createTest()
    {
        Assert.assertTrue(createdBook.getGenre().equals(testBook.getGenre()));
        Assert.assertTrue(createdBook.getPrice()==(testBook.getPrice()));
        Assert.assertTrue(createdBook.getId()==1);
    }
    @Test
    public void findByNameTest() {
        Assert.assertTrue(localBookService.findByName(title).size()==1);
    }

}
