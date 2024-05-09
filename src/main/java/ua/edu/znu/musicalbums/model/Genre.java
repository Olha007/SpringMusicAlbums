package ua.edu.znu.musicalbums.model;

import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;
import lombok.*;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", nullable = false)
    private Long id;
    @ToString.Exclude

    @Column(name = "genre_name", nullable = false)
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "genre", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Song> songs = new LinkedHashSet<>();
}
