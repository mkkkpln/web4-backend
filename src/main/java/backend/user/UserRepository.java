package backend.user;

import org.springframework.data.repository.CrudRepository;


interface UserRepository extends CrudRepository<User, Integer> {

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
