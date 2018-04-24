package bookStore.service;

import bookStore.dto.UserDTO;
import bookStore.entity.User;
import bookStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO userDTO) {
        User user=new User(userDTO.username,passwordEncoder.encodePassword(userDTO.password,null));
        return userRepository.save(user);
    }

    @Override
    public void delete(String username) {
        userRepository.delete(username);
    }

    @Override
    public void update(UserDTO userDTO) {
        User user=userRepository.findOne(userDTO.username);
        user.setPassword(passwordEncoder.encodePassword(userDTO.password,null));
        userRepository.save(user);

    }

}
