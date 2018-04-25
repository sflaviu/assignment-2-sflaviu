package bookstore;

import bookstore.dto.UserDTO;
import bookstore.entity.User;
import bookstore.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserAPIController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String create(@RequestBody @Valid UserDTO userDTO) {
        userService.create(userDTO);
        return "redirect:createUser?success";
    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String delete(@RequestBody String delUsername) {
        userService.delete(delUsername.substring(0, delUsername.length() - 1));
        return "redirect:deleteUser?success";
    }
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String update(@RequestBody UserDTO user) {
        userService.update(user);
        return "redirect:updateUser?success";
    }
}
