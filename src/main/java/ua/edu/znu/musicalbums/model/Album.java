package ua.edu.znu.musicalbums.model;

import lombok.*;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id", nullable = false)
    private Long id;
    @Column(name = "album_name", nullable = false)
    private String albumName;
    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    @ManyToMany
    @JoinTable(name = "album_songs",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private Set<Song> songs = new LinkedHashSet<>();


    @OneToMany(mappedBy = "album", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<AlbumArtistGroup> albumArtistGroups;

}

