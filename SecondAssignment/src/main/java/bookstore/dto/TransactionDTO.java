package bookstore.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class TransactionDTO {

    @Size(min = 5, max = 20,message="ISBN is the wrong size")
    public String isbn;
    @Min(value=1, message="Quantity must be a positive integer!")
    public int quantity;
}
