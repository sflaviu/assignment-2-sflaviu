package bookstore;

import bookstore.dto.UserDTO;
import bookstore.entity.User;
import bookstore.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public List<String> create(@RequestBody @Valid UserDTO userDTO,BindingResult bindingResult)
    {
        List<String> result =new ArrayList<>();
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error->result.add(error.getDefaultMessage()));
        }
        else {

            if(userService.create(userDTO)!=null)
                result.add("Creating successful!");
            else
                result.add("Saving not successful!");
        }
        return result;

    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public void delete(@RequestBody String delUsername) {
        userService.delete(delUsername.substring(0, delUsername.length() - 1));
   }
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public List<String> update(@RequestBody @Valid UserDTO userDTO,BindingResult bindingResult) {
        List<String> result =new ArrayList<>();
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error->result.add(error.getDefaultMessage()));
        }
        else {

            if(userService.update(userDTO)!=null)
                result.add("Updating successful!");
            else
                result.add("Updating not successful!");
        }
        return result;
    }


}
