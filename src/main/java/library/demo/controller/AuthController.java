package library.demo.controller;

import library.demo.model.ReqRes;
import library.demo.model.UserEntity;
import library.demo.service.AuthService;
import library.demo.service.CreateUserRepository;
import library.demo.service.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class AuthController {

    @Autowired
    private CreateUserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private CreateUserRepository createUserRepository;

    @PostMapping("/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes login) {
        return ResponseEntity.ok(authService.signIn(login));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refresh(@RequestBody ReqRes refreshToken){
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }
    @GetMapping("/register")
    public ResponseEntity<List<UserEntity>> getUsers() {
        return new ResponseEntity<List<UserEntity>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/public/{email}")
    public Optional<UserEntity> getBookById(@PathVariable String email) {

        return createUserRepository.findByEmail(email);
    }

    @PostMapping("/register")
    public ResponseEntity<ReqRes> createUser(@RequestBody ReqRes user) {
        return ResponseEntity.ok(authService.signUP(user));
    }

}
