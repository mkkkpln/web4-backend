package backend.user;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void findAllByID(int id) {

    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
