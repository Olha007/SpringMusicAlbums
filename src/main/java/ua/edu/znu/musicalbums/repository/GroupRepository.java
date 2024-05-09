package ua.edu.znu.musicalbums.repository;

import org.springframework.data.repository.CrudRepository;
import ua.edu.znu.musicalbums.model.Group;
import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Long> {
    List<Group> findByGroupNameContainingIgnoreCase(String search);
}
