package bookstore.service.author;

import bookstore.dto.AuthorDTO;
import bookstore.entity.Author;
import bookstore.repository.AuthorRepository;
import bookstore.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Catalysts on 9/9/2015.
 */
public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(int authorId) {
        return authorRepository.findOne(authorId);
    }

    @Override
    public Author create(AuthorDTO authorDTO) {
        return authorRepository.save(new Author(authorDTO.name));
    }
}
