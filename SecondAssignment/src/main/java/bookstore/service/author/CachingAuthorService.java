package bookstore.service.author;

import bookstore.dto.AuthorDTO;
import bookstore.entity.Author;
import bookstore.service.author.AuthorService;

import java.util.List;

public class CachingAuthorService implements AuthorService {
    private AuthorService origin;

    public CachingAuthorService(AuthorService origin) {
        this.origin = origin;
    }

    @Override
    public List<Author> getAll() {
        //TODO: Add caching
        return origin.getAll();
    }

    @Override
    public Author findById(int authorId) {
        //TODO: Add caching
        return origin.findById(authorId);
    }

    @Override
    public Author create(AuthorDTO authorDTO) {
        return origin.create(authorDTO);
    }
}
