package bookstore.service.book;

import bookstore.dto.BookDTO;
import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.repository.BookRepository;
import bookstore.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Catalysts on 8/9/2015.
 */
@Service
public class LocalBookServiceImpl implements LocalBookService {
    private BookRepository bookRepository;
    private AuthorService authorService;

    @Autowired
    public LocalBookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
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
    public Book update(BookDTO bookDTO) {
        Book book=bookRepository.findByIsbn(bookDTO.isbn);
        book.setName(bookDTO.name);
        book.setGenre(bookDTO.genre);
        Author a = authorService.findById(bookDTO.authorId);
        book.setAuthor(a);
        book.setPrice(bookDTO.price);
        book.setQuantity(bookDTO.quantity);
        return bookRepository.save(book);
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
