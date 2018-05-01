package bookstore.service.user;

import bookstore.dto.UserDTO;
import bookstore.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User create(UserDTO userDTO);
    void delete(String username);
    User update(UserDTO userDTO);
}
