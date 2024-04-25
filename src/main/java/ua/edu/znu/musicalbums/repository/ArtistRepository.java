package ua.edu.znu.musicalbums.repository;

import org.springframework.data.repository.CrudRepository;
import ua.edu.znu.musicalbums.model.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
}
