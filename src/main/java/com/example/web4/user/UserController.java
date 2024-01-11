package com.example.web4.user;

import com.example.web4.BasicConfiguration;
import com.example.web4.jwt.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserEntity user) {
        String login = user.getUsername();
        String password = passwordEncoder.encode(user.getPassword());
        if (userRepository.existsByUsername(user.getUsername()).isEmpty()) {
            userRepository.registerUser(login, password);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User exists", HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity user) {
        UserDetails user1 = userDetailsService.loadUserByUsername(user.getUsername());
        if (passwordEncoder.matches(user.getPassword(), user1.getPassword())) {
            ResponseEntity.BodyBuilder response = ResponseEntity.ok();
            return response.body(jwtTokenUtils.generateToken(userDetailsService.loadUserByUsername(user.getUsername())));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
