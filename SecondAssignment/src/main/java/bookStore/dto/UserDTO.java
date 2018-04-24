package bookStore.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {

    @Size(min=6)
    public String username;
    @Size(min = 6)
    public String password;
}
