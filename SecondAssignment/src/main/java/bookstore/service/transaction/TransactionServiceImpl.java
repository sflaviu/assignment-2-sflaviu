package bookstore.service.transaction;

import bookstore.dto.TransactionDTO;
import bookstore.repository.BookRepository;
import bookstore.validation.TransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bookstore.entity.Book;

@Service
public class TransactionServiceImpl implements TransactionService{

    private BookRepository bookRepository;

    @Autowired
    public TransactionServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public int sellBook(TransactionDTO transactionDTO)
    {
        int sum=0;

        Book soldBook=bookRepository.findByIsbn(transactionDTO.isbn);

        TransactionValidator transactionValidator=new TransactionValidator(soldBook,transactionDTO.quantity);
        if(transactionValidator.validate())
        {
            soldBook.setQuantity(soldBook.getQuantity()-transactionDTO.quantity);
            bookRepository.save(soldBook);

            sum=soldBook.getPrice()*transactionDTO.quantity;
        }
        return sum;

    }
}
