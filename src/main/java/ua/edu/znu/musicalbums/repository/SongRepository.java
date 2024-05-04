package ua.edu.znu.musicalbums.repository;

import org.springframework.data.repository.CrudRepository;
import ua.edu.znu.musicalbums.model.Song;

public interface SongRepository extends CrudRepository<Song, Long> {

}
