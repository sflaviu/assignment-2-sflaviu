package bookstore.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Catalysts on 8/9/2015.
 */
public class BookDTO {
    @Size(min = 1, message ="Name is too short!")
    public String name;
    @Size(min=1,message ="Genre is required!")
    public String genre;
    @NotNull
    public int authorId;
    @Min(value=1, message ="Price must be a positive integer!")
    public int price;
    @Min(value=1,message ="Quantity must be a positive integer!")
    public int quantity;

    @Size(min = 5, max = 20, message ="ISBN is the wrong size")
    public String isbn;


}
