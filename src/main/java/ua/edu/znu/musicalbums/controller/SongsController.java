package ua.edu.znu.musicalbums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.znu.musicalbums.model.Song;
import ua.edu.znu.musicalbums.repository.SongRepository;

@Controller
public class SongsController {
    @Autowired
    private SongRepository songRepository;

    @GetMapping("/songs")
    public String songs(Model model) {
        Iterable<Song> songs = songRepository.findAll();
        model.addAttribute("songs", songs);
        return "songs";
    }

    @PostMapping("/songEditForm")
    public String songEditForm(@RequestParam Long songId, Model model) {
        Song song = songRepository.findById(songId).orElse(new Song());
        model.addAttribute("song", song);
        return "songedit";
    }

    @PostMapping("/songEdit")
    public String songEdit(@RequestParam Long songId, @RequestParam String songName,
                             @RequestParam Integer durationMinutes, @RequestParam Integer durationSeconds, Model model) {
       Song song = songRepository.findById(songId).orElse(new Song());
        song.setSongName(songName);
        song.setDurationMinutes(durationMinutes);
        song.setDurationSeconds(durationSeconds);
        songRepository.save(song);
        return "redirect:/songs";
    }

    @PostMapping("/songRemove")
    public String songRemove(@RequestParam Long songId) {
        songRepository.findById(songId).ifPresent(songRepository::delete);
        return "redirect:/songs";
    }

    @GetMapping("/songAddForm")
    public String songAddForm() {
        return "songadd";
    }

    @PostMapping("/songAdd")
    public String songAdd(@RequestParam String songName,
                          @RequestParam Integer durationMinutes, @RequestParam Integer durationSeconds) {
        Song song = new Song();
        song.setSongName(songName);
        song.setDurationMinutes(durationMinutes);
        song.setDurationSeconds(durationSeconds);
        songRepository.save(song);
        return "redirect:/songs";
    }
}

