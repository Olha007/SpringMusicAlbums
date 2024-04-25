package ua.edu.znu.musicalbums.model.DTO;

import lombok.Data;

@Data
public class AlbumAssignment {
    private Long id;
    private String albumName;
    private String genres;
    private Integer releaseYear;
    private String artistName;
    private String groupName;
}
