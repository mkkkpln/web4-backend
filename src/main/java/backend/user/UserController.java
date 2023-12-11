package backend.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class UserController {

    private UserService userService;
    private UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        if (!userRepository.existsByUsername(user.getUsername())) {
            List<User> users = userService.findAllByID();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }
}
