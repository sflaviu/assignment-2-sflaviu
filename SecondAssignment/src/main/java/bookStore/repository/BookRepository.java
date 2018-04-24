package bookStore.repository;

import bookStore.entity.Author;
import bookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Catalysts on 8/9/2015.
 */

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByGenre(String genre);
    List<Book> findByName(String name);
    List<Book> findByAuthorId(int authorId);
    Book findByIsbn(String isbn);
}