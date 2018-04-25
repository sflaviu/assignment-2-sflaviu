package bookstore.service;

import bookstore.dto.TransactionDTO;
import bookstore.repository.BookRepository;
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
        Book soldBook=bookRepository.findByIsbn(transactionDTO.isbn);

        //TODO validate transaction

        soldBook.setQuantity(soldBook.getQuantity()-transactionDTO.quantity);
        bookRepository.save(soldBook);

        return soldBook.getPrice()*transactionDTO.quantity;

    }
}
