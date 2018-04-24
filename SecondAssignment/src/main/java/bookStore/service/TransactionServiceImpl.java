package bookStore.service;

import bookStore.dto.TransactionDTO;
import bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bookStore.entity.Book;

@Service
public class TransactionServiceImpl implements TransactionService{

    private BookRepository bookRepository;

    @Autowired
    public TransactionServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void sellBook(TransactionDTO transactionDTO)
    {
        Book soldBook=bookRepository.findByIsbn(transactionDTO.isbn);

        //TODO validate transaction

        soldBook.setQuantity(soldBook.getQuantity()-transactionDTO.quantity);
        bookRepository.save(soldBook);

        //TODO return some info about transaction ?

    }
}
