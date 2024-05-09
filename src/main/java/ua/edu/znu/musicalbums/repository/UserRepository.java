package ua.edu.znu.musicalbums.repository;

import org.springframework.data.repository.CrudRepository;
import ua.edu.znu.musicalbums.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(final String username);
}
