package bookstore.service.unit;

import bookstore.repository.MockAuthorRepository;
import bookstore.service.author.AuthorService;
import bookstore.service.author.AuthorServiceImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AuthorServiceImplTest {
    private static AuthorService target;

    @BeforeClass
    public static void init() {
        target = new AuthorServiceImpl(new MockAuthorRepository());
    }

    @Test
    public void testAll() {
        Assert.assertTrue(target.getAll().size() == 2);
    }
}
