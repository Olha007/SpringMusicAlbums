package ua.edu.znu.musicalbums.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "username", nullable = false, unique = true, length = 10)
    private String username;
    @Column(name = "password", nullable = false, length = 10)
    private String password;
}
