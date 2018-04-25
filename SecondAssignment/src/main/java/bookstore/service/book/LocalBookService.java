package bookstore.service.book;

import bookstore.dto.BookDTO;
import bookstore.entity.Book;

import java.util.List;

/**
 * Created by Catalysts on 8/9/2015.
 */

public interface LocalBookService {
    List<Book> getAll();
    Book create(BookDTO bookDTO);
    void delete(String isbn);
    void update(BookDTO bookDTO);

    List<Book> findByGenre(String genre);
    List<Book> findByName(String name);
    List<Book> findByAuthor(int authorId);

}
