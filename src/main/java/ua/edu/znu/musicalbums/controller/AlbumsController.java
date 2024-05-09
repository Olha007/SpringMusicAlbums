package ua.edu.znu.musicalbums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.znu.musicalbums.model.Album;
import ua.edu.znu.musicalbums.repository.*;


@Controller
public class AlbumsController {
    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping("/albums")
    public String albums(@RequestParam(required = false) String search, Model model) {
        Iterable<Album> albums;
        if (search != null && !search.isEmpty()) {
            albums = albumRepository.findByAlbumNameContainingIgnoreCaseOrReleaseYear(search, parseReleaseYear(search));
        } else {
            albums = albumRepository.findAll();
        }
        model.addAttribute("albums", albums);
        return "albums";
    }

    private Integer parseReleaseYear(String search) {
        try {
            return Integer.parseInt(search);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @PostMapping("/albumEditForm")
    public String albumEditForm(@RequestParam Long albumId, Model model) {
        Album album = albumRepository.findById(albumId).orElse(new Album());
        model.addAttribute("album", album);
        return "albumedit";
    }

    @PostMapping("/albumEdit")
    public String albumEdit(@RequestParam Long albumId, @RequestParam String albumName,
                            @RequestParam Integer albumReleaseYear, Model model) {
        Album album = albumRepository.findById(albumId).orElse(new Album());
        album.setAlbumName(albumName);
        album.setReleaseYear(albumReleaseYear);
        albumRepository.save(album);
        return "redirect:/albums";
    }


    @PostMapping("/albumRemove")
    public String albumRemove(@RequestParam Long albumId) {
        albumRepository.findById(albumId).ifPresent(albumRepository::delete);
        return "redirect:/albums";
    }

    @GetMapping("/albumAddForm")
    public String albumAddForm() {
        return "albumadd";
    }

    @PostMapping("/albumAdd")
    public String albumAdd(@RequestParam String albumName, @RequestParam Integer releaseYear) {
        Album album = new Album();
        album.setAlbumName(albumName);
        album.setReleaseYear(releaseYear);
        albumRepository.save(album);
        return "redirect:/albums";
    }
}

