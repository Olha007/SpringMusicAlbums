package ua.edu.znu.musicalbums.repository;

import org.springframework.data.repository.CrudRepository;
import ua.edu.znu.musicalbums.model.Song;

import java.util.List;

public interface SongRepository extends CrudRepository<Song, Long> {
    List<Song> findBySongNameContainingIgnoreCaseOrGenreNameContainingIgnoreCase(String songName, String genreName);
}
