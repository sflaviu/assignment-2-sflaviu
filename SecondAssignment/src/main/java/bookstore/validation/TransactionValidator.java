package bookstore.validation;

import bookstore.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class TransactionValidator implements Validator {
    private final Book book;
    private final int quantity;

    private final List<String> errors;

    public TransactionValidator(Book book,int quantity) {

        this.book=book;
        this.quantity=quantity;
        errors = new ArrayList<>();
    }
    @Override
    public boolean validate() {
        validateQuantity();
        return errors.isEmpty();
    }

    private void validateQuantity()
    {
        if(book.getQuantity()<quantity)
            errors.add("There are not enough copies for this command!");
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }
}
