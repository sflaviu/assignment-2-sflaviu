package bookStore.service;

import bookStore.dto.UserDTO;
import bookStore.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User create(UserDTO userDTO);
    void delete(String username);
    void update(UserDTO userDTO);
}
