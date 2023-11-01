//package library.demo.jwt;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class JwtAuthenticationController {
//
//    private final JwtTokenService tokenService;
//
//    private final AuthenticationManager authenticationManager;
//
//    private JwtAuthenticationController(JwtTokenService tokenService, AuthenticationManager authenticationManager){
//        this.tokenService =tokenService;
//        this.authenticationManager=authenticationManager;
//    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<JwtTokenResponse> generateToken(
//            @RequestBody JwkTokenRequest jwkTokenRequest){
//        var authenticationToken =
//                new UsernamePasswordAuthenticationToken(
//                        jwkTokenRequest.username(),
//                        jwkTokenRequest.password());
//
//        var authentication =
//                authenticationManager.authenticate(authenticationToken);
//
//        var token = tokenService.generateToken(authentication);
//
//        return ResponseEntity.ok(new JwtTokenResponse(token));
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
