package ua.edu.znu.musicalbums.repository;

import org.springframework.data.repository.CrudRepository;
import ua.edu.znu.musicalbums.model.Genre;
import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findByNameContainingIgnoreCase(String search);

}
