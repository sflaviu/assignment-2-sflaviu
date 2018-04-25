package bookstore.repository;

import bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Catalysts on 8/9/2015.
 */

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByGenre(String genre);
    List<Book> findByName(String name);
    List<Book> findByAuthorId(int authorId);
    Book findByIsbn(String isbn);

    List<Book> findByQuantity(int quantity);
}