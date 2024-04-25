SET
SQL_SAFE_UPDATES = 0;

LOCK
TABLES `album_artist_group` WRITE;
DELETE
FROM `album_artist_group`;
UNLOCK TABLES;

LOCK
TABLES `albums` WRITE;
DELETE
FROM `albums`;
UNLOCK
TABLES;

LOCK
TABLES `songs` WRITE;
DELETE
FROM `songs`;
UNLOCK
TABLES;

LOCK
TABLES `genres` WRITE;
DELETE
FROM `genres`;
UNLOCK
TABLES;

LOCK
TABLES `groups` WRITE;
DELETE
FROM `groups`;
UNLOCK
TABLES;

LOCK
TABLES `artists` WRITE;
DELETE
FROM `artists`;
UNLOCK
TABLES;

LOCK
TABLES `users` WRITE;
DELETE
FROM `users`;
UNLOCK
TABLES;

LOCK
TABLES `albums` WRITE;
INSERT INTO albums (album_id, album_name, release_year)
VALUES (1, 'Al Green - Greatest Hits', 1975),
       (2, 'A Hard Day`s Night', 1964),
       (3, 'The Bends', 1995),
       (4, 'Synchronicity', 1983),
       (5, 'Nevermind', 1991),
       (6, 'Kid A', 2000);
UNLOCK
TABLES;

LOCK
TABLES `genres` WRITE;
INSERT INTO genres (genre_id, genre_name)
VALUES (1, 'Rock'),
       (2, 'Alternative'),
       (3, 'R&B/Soul'),
       (4, 'Reggae'),
       (6, 'Jazz'),
       (7, 'Opera music');
UNLOCK
TABLES;

LOCK
TABLES `groups` WRITE;
INSERT INTO `groups` (group_id, group_name)
VALUES (1, 'The Beatles'),
       (2, 'Radiohead'),
       (3, 'Portishead'),
       (4, 'Ramones'),
       (5, 'The Rolling Stones'),
       (6, 'Talking Heads');
UNLOCK
TABLES;

LOCK
TABLES `artists` WRITE;
INSERT INTO artists (artist_id, first_name, last_name)
VALUES (1, 'Elvis', 'Presley'),
       (2, 'David', 'Bowie'),
       (3, 'Lauryn', 'Hill'),
       (4, 'Curtis', 'Mayfield'),
       (5, 'Bob', 'Dylan'),
       (6, 'Neil', 'Young');
UNLOCK
TABLES;

LOCK
TABLES `album_artist_group` WRITE;
INSERT INTO album_artist_group (album_id, artist_id, group_id)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3);
UNLOCK
TABLES;

LOCK
TABLES `songs` WRITE;
INSERT INTO songs (song_id, song_name, duration_minutes, duration_seconds, genre_id)
VALUES (1, 'Elvis Presley', 3, 30, 1),
       (2, 'Low', 4, 15, 1),
       (3, 'The Miseducation', 2, 45, 3),
       (4, 'James Brown', 1, 34, 2);
UNLOCK
TABLES;

LOCK
TABLES `album_songs` WRITE;
INSERT INTO album_songs (album_id, song_id)
VALUES (1, 1),
       (2, 2),
       (2, 3),
       (3, 4);
UNLOCK
TABLES;

LOCK
TABLES `users` WRITE;
INSERT INTO `users` (id, `username`, password)
VALUES (1, 'foo', 'bar');
UNLOCK
TABLES;

SET
SQL_SAFE_UPDATES = 1;