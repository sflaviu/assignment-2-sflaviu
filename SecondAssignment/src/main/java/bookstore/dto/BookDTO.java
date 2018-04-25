package bookstore.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Catalysts on 8/9/2015.
 */
public class BookDTO {
    @Size(min = 1)
    public String name;
    public String genre;
    @NotNull
    public int authorId;
    @Min(0)
    public int price;
    @Min(0)
    public int quantity;
    public String isbn;


}