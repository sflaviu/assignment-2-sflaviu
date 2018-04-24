package bookStore.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class TransactionDTO {

    @Size(min = 5, max = 5)
    public String isbn;
    public int quantity;
}
