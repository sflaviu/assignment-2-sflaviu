package bookstore.service.transaction;

import bookstore.dto.TransactionDTO;

public interface TransactionService {

    int sellBook(TransactionDTO transactionDTO);
}
