package ua.edu.znu.musicalbums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.znu.musicalbums.model.Artist;
import ua.edu.znu.musicalbums.repository.ArtistRepository;

@Controller
public class ArtistsController {
    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/artists")
    public String artists(Model model) {
        Iterable<Artist> artists = artistRepository.findAll();
        model.addAttribute("artists", artists);
        return "artists";
    }

    @PostMapping("/artistEditForm")
    public String artistEditForm(@RequestParam Long artistId, Model model) {
        Artist artist = artistRepository.findById(artistId).orElse(new Artist());
        model.addAttribute("artist", artist);
        return "artistedit";
    }

    @PostMapping("/artistEdit")
    public String artistEdit(@RequestParam Long artistId, @RequestParam String artistFirstName,
                            @RequestParam String artistLastName, Model model) {
        Artist artist = artistRepository.findById(artistId).orElse(new Artist());
        artist.setFirstName(artistFirstName);
        artist.setLastName(artistLastName);
        artistRepository.save(artist);
        return "redirect:/artists";
    }

    @PostMapping("/artistRemove")
    public String artistRemove(@RequestParam Long artistId) {
        artistRepository.findById(artistId).ifPresent(artistRepository::delete);
        return "redirect:/artists";
    }

    @GetMapping("/artistAddForm")
    public String artistAddForm() {
        return "artistadd";
    }

    @PostMapping("/artistAdd")
    public String artistAdd(@RequestParam String artistFirstName,
                            @RequestParam String artistLastName) {
        Artist artist = new Artist();
        artist.setFirstName(artistFirstName);
        artist.setLastName(artistLastName);
        artistRepository.save(artist);
        return "redirect:/artists";
    }
}
