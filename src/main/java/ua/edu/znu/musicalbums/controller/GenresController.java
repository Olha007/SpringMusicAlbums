package ua.edu.znu.musicalbums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.znu.musicalbums.model.Genre;
import ua.edu.znu.musicalbums.repository.GenreRepository;

@Controller
public class GenresController {
    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/genres")
    public String genres(Model model) {
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "genres";
    }

    @PostMapping("/genreEditForm")
    public String artistEditForm(@RequestParam Long genreId, Model model) {
        Genre genre = genreRepository.findById(genreId).orElse(new Genre());
        model.addAttribute("genre", genre);
        return "genreedit";
    }

    @PostMapping("/genreEdit")
    public String genreEdit(@RequestParam Long genreId, @RequestParam String genreName, Model model) {
        Genre genre = genreRepository.findById(genreId).orElse(new Genre());
        genre.setName(genreName);
        genreRepository.save(genre);
        return "redirect:/genres";
    }

    @PostMapping("/genreRemove")
    public String genreRemove(@RequestParam Long genreId) {
        genreRepository.findById(genreId).ifPresent(genreRepository::delete);
        return "redirect:/genres";
    }

    @GetMapping("/genreAddForm")
    public String genreAddForm() {
        return "genreadd";
    }

    @PostMapping("/genreAdd")
    public String genreAdd(@RequestParam String genreName) {
        Genre genre = new Genre();
        genre.setName(genreName);
        genreRepository.save(genre);
        return "redirect:/genres";
    }
}
