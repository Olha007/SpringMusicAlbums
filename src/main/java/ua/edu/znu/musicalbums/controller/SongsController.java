package ua.edu.znu.musicalbums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.znu.musicalbums.model.Genre;
import ua.edu.znu.musicalbums.model.Song;
import ua.edu.znu.musicalbums.repository.GenreRepository;
import ua.edu.znu.musicalbums.repository.SongRepository;

import java.util.List;

@Controller
public class SongsController {
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private GenreRepository genreRepository;

//    @GetMapping("/songs")
//    public String songs(Model model) {
//        Iterable<Song> songs = songRepository.findAll();
//        model.addAttribute("songs", songs);
//        return "songs";
//    }

    @GetMapping("/songs")
    public String songs(@RequestParam(required = false) String search, Model model) {
        Iterable<Song> songs;
        if (search != null && !search.isEmpty()) {
            songs = songRepository.findBySongNameContainingIgnoreCaseOrGenreNameContainingIgnoreCase(search, search);
        } else {
            songs = songRepository.findAll();
        }
        model.addAttribute("songs", songs);
        return "songs";
    }

    @PostMapping("/songEditForm")
    public String songEditForm(@RequestParam Long songId, Model model) {
        return songRepository.findById(songId)
                .map(song -> {
                    model.addAttribute("song", song);
                    List<Genre> genres = (List<Genre>) genreRepository.findAll();
                    model.addAttribute("genres", genres);
                    return "songedit";
                })
                .orElse("redirect:/songs");
    }

    @PostMapping("/songEdit")
    public String songEdit(@RequestParam Long songId, @RequestParam String songName,
                           @RequestParam Integer durationMinutes, @RequestParam Integer durationSeconds,
                           @RequestParam Long genreId) {
        Song song = songRepository.findById(songId).orElse(new Song());
        song.setSongName(songName);
        song.setDurationMinutes(durationMinutes);
        song.setDurationSeconds(durationSeconds);

        Genre genre = genreRepository.findById(genreId).orElse(null);
        song.setGenre(genre);
        songRepository.save(song);
        return "redirect:/songs";
    }

    @PostMapping("/songRemove")
    public String songRemove(@RequestParam Long songId) {
        songRepository.findById(songId).ifPresent(songRepository::delete);
        return "redirect:/songs";
    }

    @GetMapping("/songAddForm")
    public String songAddForm(Model model) {
        List<Genre> genres = (List<Genre>) genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "songadd";
    }

    @PostMapping("/songAdd")
    public String songAdd(@RequestParam String songName,
                          @RequestParam Integer durationMinutes, @RequestParam Integer durationSeconds,
                          @RequestParam Long genreId) {
        Song song = new Song();
        song.setSongName(songName);
        song.setDurationMinutes(durationMinutes);
        song.setDurationSeconds(durationSeconds);
        Genre genre = genreRepository.findById(genreId).orElse(null);
        song.setGenre(genre);

        songRepository.save(song);
        return "redirect:/songs";
    }
}

