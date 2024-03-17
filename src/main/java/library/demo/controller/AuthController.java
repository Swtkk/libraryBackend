package library.demo.controller;

import jakarta.validation.Valid;
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
    //kontroler logowania
    @PostMapping("/login")
    public ResponseEntity<ReqRes> login(@Valid @RequestBody ReqRes login) {
        return ResponseEntity.ok(authService.signIn(login));
    }
    //kontroler odswiezania tokena w projekcie nieuzywany endpoit ale zaaplikowany w kodzie
    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refresh(@RequestBody ReqRes refreshToken){
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }
    //kontroler odpowiedzialny za pobranie wszystkich uzytkownikow, wprowadzony na poczet sprawdzania w postmanie
    @GetMapping("/register")
    public ResponseEntity<List<UserEntity>> getUsers() {
        return new ResponseEntity<List<UserEntity>>(userService.getAllUsers(), HttpStatus.OK);
    }
    //kontroler pobierania uzytkownika po nazwie
    @GetMapping("/public/{email}")
    public Optional<UserEntity> getBookById(@PathVariable String email) {

        return createUserRepository.findByEmail(email);
    }
    //kontroler pobierania uzywkotnika po id
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    //kontroler odpowiedizalny za rejestracje
    @PostMapping("/register")
    public ResponseEntity<ReqRes> createUser(@Valid @RequestBody ReqRes user) {
        return ResponseEntity.ok(authService.signUP(user));
    }

}
