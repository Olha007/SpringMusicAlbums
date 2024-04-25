CREATE TABLE Albums
(
    album_id     BIGINT       NOT NULL AUTO_INCREMENT,
    album_name   VARCHAR(255) NOT NULL,
    release_year INT          NOT NULL,
    PRIMARY KEY (album_id)
) engine = InnoDB;

CREATE TABLE Songs
(
    song_id          BIGINT       NOT NULL AUTO_INCREMENT,
    song_name        VARCHAR(255) NOT NULL,
    duration_minutes INT          NOT NULL,
    duration_seconds INT          NOT NULL,
    genre_id         BIGINT,
    PRIMARY KEY (song_id),
    FOREIGN KEY (genre_id) REFERENCES Genres (genre_id)
) engine = InnoDB;

CREATE TABLE Genres
(
    genre_id   BIGINT       NOT NULL AUTO_INCREMENT,
    genre_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (genre_id)
) engine = InnoDB;

CREATE TABLE Groups
(
    group_id   BIGINT       NOT NULL AUTO_INCREMENT,
    group_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (group_id)
) engine = InnoDB;

CREATE TABLE Artists
(
    artist_id  BIGINT       NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    PRIMARY KEY (artist_id)
) engine = InnoDB;

CREATE TABLE AlbumArtistGroup
(
    artist_id BIGINT,
    album_id  BIGINT,
    group_id  BIGINT,
    PRIMARY KEY (artist_id, album_id, group_id),
    FOREIGN KEY (artist_id) REFERENCES Artists (artist_id),
    FOREIGN KEY (album_id) REFERENCES Albums (album_id),
    FOREIGN KEY (group_id) REFERENCES "Groups" (group_id)
) engine = InnoDB;

CREATE TABLE Users
(
    id       bigint      not null auto_increment,
    password varchar(10) not null,
    username varchar(10) not null,
    primary key (id)
) engine = InnoDB;