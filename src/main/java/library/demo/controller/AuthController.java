package library.demo.controller;

import library.demo.model.Login;
import library.demo.model.UserEntity;
import library.demo.service.CreateUserRepository;
import library.demo.service.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class AuthController {
    private AuthenticationManager authenticationManager;
    private CreateUserRepository createUserRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CreateUserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          CreateUserRepository createUserRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.createUserRepository = createUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(login.getEmail(),
                                login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed success", HttpStatus.OK);
    }


    @GetMapping("/register")
    public ResponseEntity<List<UserEntity>> getUsers() {
        return new ResponseEntity<List<UserEntity>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        if (userService.getUser(user.getEmail()).equals(user.getEmail())) {
            return null;
        }


        userService.createUser(user.getEmail(), passwordEncoder.encode(user.getPassword()), "USER");
        return new ResponseEntity("User created successfully", HttpStatus.OK);
    }
}
