package library.demo.controller;

import library.demo.model.User;
import library.demo.service.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/create")
public class UserController {
    @Autowired
    private CreateUserService userService;

    @PostMapping()
    public User createUser(@RequestBody User user) {
        return userService.createUser(user.getName(), user.getPassword());

    }
}
