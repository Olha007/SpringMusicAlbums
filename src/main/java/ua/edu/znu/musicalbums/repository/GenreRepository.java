package ua.edu.znu.musicalbums.repository;

import org.springframework.data.repository.CrudRepository;
import ua.edu.znu.musicalbums.model.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
