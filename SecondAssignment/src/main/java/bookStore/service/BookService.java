package bookStore.service;

import bookStore.dto.BookDTO;
import bookStore.entity.Book;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Catalysts on 8/9/2015.
 */

public interface BookService {
    List<Book> getAll();
    Book create(BookDTO bookDTO);
    void delete(String isbn);
    void update(BookDTO bookDTO);

    List<Book> findByGenre(String genre);
    List<Book> findByName(String name);
    List<Book> findByAuthor(int authorId);

}
