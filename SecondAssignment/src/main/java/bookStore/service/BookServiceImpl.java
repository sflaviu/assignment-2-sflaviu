package bookStore.service;

import bookStore.dto.BookDTO;
import bookStore.entity.Author;
import bookStore.entity.Book;
import bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Catalysts on 8/9/2015.
 */
@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private AuthorService authorService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, @Value("${bookService.maxBooks}") Integer maxBooks) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book create(BookDTO bookDTO) {
        Author author = authorService.findById(bookDTO.authorId);
        Book book = new Book(bookDTO.name,author, bookDTO.isbn,bookDTO.genre,bookDTO.quantity,bookDTO.price);
        return bookRepository.save(book);
    }


    @Override
    public void delete(String isbn)
    {
        bookRepository.delete(bookRepository.findByIsbn(isbn));
    }

    @Override
    public void update(BookDTO bookDTO) {
        Book book=bookRepository.findByIsbn(bookDTO.isbn);
        book.setName(bookDTO.name);
        book.setGenre(bookDTO.genre);
        Author a = authorService.findById(bookDTO.authorId);
        book.setAuthor(a);
        book.setPrice(bookDTO.price);
        book.setQuantity(bookDTO.quantity);
        bookRepository.save(book);
    }

    @Override
    public List<Book> findByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    @Override
    public List<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> findByAuthor(int authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}
