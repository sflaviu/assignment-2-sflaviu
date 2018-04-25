package bookstore.service;

import bookstore.dto.TransactionDTO;

public interface TransactionService {

    int sellBook(TransactionDTO transactionDTO);
}
