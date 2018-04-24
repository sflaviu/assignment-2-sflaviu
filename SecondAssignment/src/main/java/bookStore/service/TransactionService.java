package bookStore.service;

import bookStore.dto.TransactionDTO;

public interface TransactionService {

    void sellBook(TransactionDTO transactionDTO);
}
