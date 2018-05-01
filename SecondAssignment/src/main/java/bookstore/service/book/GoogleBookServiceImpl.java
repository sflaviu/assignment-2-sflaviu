package bookstore.service.book;

import bookstore.dto.AuthorDTO;
import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.repository.AuthorRepository;
import bookstore.repository.BookRepository;
import bookstore.service.author.AuthorService;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleBookServiceImpl implements GoogleBookService {

    private JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

    List<Book> lastSearch;

    BookRepository bookRepository;
    AuthorRepository authorRepository;


    @Autowired
    public GoogleBookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    private final String apiKey = "AIzaSyDAAr0BYZNOJtbQT_aXO-JZvyorjCpoS5M";
    private final String appName = "SamarghitanFlaviuSDAssignment";

    private final String nameQuery="inauthor:";
    private final String genreQuery="subject:";
    private final String authorQuery="intitle:";




    @Override
    public List<Book> findSuggestion(String searchedString) {

        List<Book> foundByName=null;
        List<Book> foundByAuthor=null;
        List<Book> foundByGenre=null;
        try {
            foundByName=queryGoogleBooks(nameQuery+searchedString);
            foundByAuthor=queryGoogleBooks(authorQuery+searchedString);
            foundByGenre=queryGoogleBooks(genreQuery+searchedString);

        } catch (Exception e) {
            e.printStackTrace();
        }
        foundByName.addAll(foundByAuthor);
        foundByName.addAll(foundByGenre);

        lastSearch=foundByName;
        return foundByName;
    }

    @Override
    public void saveSuggestion(String isbn) {
        for(Book q:lastSearch)
            if(q.getIsbn().equals(isbn))
            {
                authorRepository.save(q.getAuthor());
                bookRepository.save(q);
                break;
            }
    }

    private List<Book> queryGoogleBooks(String query) throws Exception {

        // Set up Books client.
        final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
                .setApplicationName(appName)
                .setGoogleClientRequestInitializer(new BooksRequestInitializer(apiKey))
                .build();

        // Set query string and filter only Google eBooks.
        Books.Volumes.List volumesList = books.volumes().list(query);
        volumesList.setFilter("ebooks");

        List<Book> foundBooks=new ArrayList<Book>();

        // Execute the query.
        Volumes volumes = volumesList.execute();
        if (volumes.getTotalItems() != 0 && volumes.getItems() != null) {

            // Output results.
            for (Volume volume : volumes.getItems()) {

                Book newBook=new Book();

                Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
                Volume.SaleInfo saleInfo = volume.getSaleInfo();

                // Title.
                newBook.setName(volumeInfo.getTitle());

                String authorName;
                if(volumeInfo.getAuthors()==null)
                    authorName="No known author";
                else
                    authorName=volumeInfo.getAuthors().get(0);

                Author author=new Author(authorName);
                //authoDTO.setName(authorName);
                //Author author=authorService.create(authoDTO);

                newBook.setAuthor(author);
                //Genre
                String genre="No genre";
                if(volumeInfo.getCategories()!=null)
                    genre=volumeInfo.getCategories().get(0);
                newBook.setGenre(genre);

                // Price (if any).
                int price=0;
                if (saleInfo != null && "FOR_SALE".equals(saleInfo.getSaleability())) {
                    price= (int)Math.round(saleInfo.getRetailPrice().getAmount());
                }
                newBook.setPrice(price);

                //ISBN
                String isbn="";

                if(volumeInfo.getIndustryIdentifiers()!=null)
                    isbn=volumeInfo.getIndustryIdentifiers().get(0).getIdentifier();
                newBook.setIsbn(isbn);

                newBook.setQuantity(0);

                foundBooks.add(newBook);
            }

        }
        return foundBooks;
    }

}
