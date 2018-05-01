package bookstore.service.book;

import bookstore.entity.Book;

import java.util.List;

public interface GoogleBookService {
    List<Book> findSuggestion(String searchString);
    void saveSuggestion(String isbn);
}
