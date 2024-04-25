package ua.edu.znu.musicalbums.repository;

import org.springframework.data.repository.CrudRepository;
import ua.edu.znu.musicalbums.model.Album;

public interface AlbumRepository extends CrudRepository<Album, Long> {

}
