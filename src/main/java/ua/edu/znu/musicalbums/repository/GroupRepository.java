package ua.edu.znu.musicalbums.repository;

import org.springframework.data.repository.CrudRepository;
import ua.edu.znu.musicalbums.model.Group;

public interface GroupRepository extends CrudRepository<Group, Long> {
}
