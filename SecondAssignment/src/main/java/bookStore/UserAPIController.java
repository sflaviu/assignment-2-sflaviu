package bookStore;

import bookStore.dto.UserDTO;
import bookStore.entity.User;
import bookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        System.out.println("\n\n"+userDTO.username+"\n\n");
        userService.create(userDTO);
        return "redirect:crudUsers?successCreate";
    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String delete(@RequestBody String username) {
        userService.delete(username.substring(0, username.length() - 1));
        return "redirect:crudUsers?successDelete";
    }
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String update(@RequestBody UserDTO user) {
        userService.update(user);
        return "redirect:crudUsers?succesUpdate";
    }
}
