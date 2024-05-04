package ua.edu.znu.musicalbums.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", nullable = false)
    private Long id;

    @Column(name = "genre_name", nullable = false)
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "genre", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Song> songs = new LinkedHashSet<>();
}
