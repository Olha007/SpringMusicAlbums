package ua.edu.znu.musicalbums.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "`groups`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Long id;

    @Column(name = "group_name", nullable = false)
    private String groupName;
}