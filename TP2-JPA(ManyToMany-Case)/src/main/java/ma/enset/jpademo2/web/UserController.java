package ma.enset.jpademo2.web;

import ma.enset.jpademo2.entities.User;
import ma.enset.jpademo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users/{username}")
    User user(@PathVariable String username){
        return userService.findUserByUserName(username);
    }

}
