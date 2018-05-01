package bookstore.dto;

import javax.validation.constraints.Size;

public class AuthorDTO {
    @Size(min=3, message = "Name is too short!")
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
