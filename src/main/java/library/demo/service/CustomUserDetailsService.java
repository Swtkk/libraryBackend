package library.demo.service;

import library.demo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CreateUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return new User(user.getEmail(), user.getPassword(), mapRolesToauthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToauthorities(String roles) {
        return Arrays.stream(roles.split(","))
                .map(String::trim) // Usuwa białe znaki z każdej roli
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }
}
