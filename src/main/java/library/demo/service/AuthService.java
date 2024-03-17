package library.demo.service;

import library.demo.model.ReqRes;
import library.demo.model.UserEntity;
import library.demo.security.JWTUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {
    @Autowired
    public CreateUserRepository createUserRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;


    public ReqRes signUP(ReqRes registrationRequest){
        ReqRes response = new ReqRes();

        try {
            UserEntity user = new UserEntity();
            ObjectId objectId = new ObjectId();
            String stringId = objectId.toHexString();
            user.setId(stringId);
            user.setEmail(registrationRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setRoles(registrationRequest.getRole());
            UserEntity userResult = createUserRepository.save(user);
            if(userResult != null){
                response.setUserEntity(userResult);
                response.setMessage("User created successfully");
                response.setStatusCode(200);
            }
        }
        catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public ReqRes signIn(ReqRes signIn){
        ReqRes response = new ReqRes();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword()));
            var user = createUserRepository.findByEmail(signIn.getEmail()).orElseThrow();
            System.out.println("USER IS: " + user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(),user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Zalogowano pomyslnie");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }

        return response;
    }

    public ReqRes refreshToken(ReqRes refresh){
        ReqRes response = new ReqRes();
        String ourEmail = jwtUtils.extractUsername(refresh.getToken());
        UserEntity user = createUserRepository.findByEmail(ourEmail).orElseThrow();
        if( jwtUtils.isTokenValid(refresh.getToken(), user)){
            var jwt = jwtUtils.generateToken(user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refresh.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Odświezono pomyslnie");
        }
        response.setStatusCode(500);
        return response;
    }
}
