package ua.edu.znu.musicalbums.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.znu.musicalbums.model.*;
import ua.edu.znu.musicalbums.model.DTO.AlbumAssignment;
import ua.edu.znu.musicalbums.repository.AlbumRepository;
import ua.edu.znu.musicalbums.repository.UserRepository;

import java.util.*;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping("/")
    public String start() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String authenticate(@RequestParam String username,
                               @RequestParam String password,
                               Model model, HttpSession session) {
        String sessionID = session.getId();
        System.out.println("LoginController: sessionID: " + sessionID);
        String message;
        User user = userRepository.findByUsername(username);
        if (user == null) {
            message = "No such username!";
            model.addAttribute("message", message);
            return "login";
        } else if (user.getUsername().equals(username)
                && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return "forward:home";
        } else {
            message = "Authentication failed!";
            model.addAttribute("message", message);
            return "login";
        }
    }

    @RequestMapping("/home")
    public String home(Model model) {
        Iterable<Album> albums = albumRepository.findAll();
        List<AlbumAssignment> albumAssignments = new ArrayList<>();
        for (Album album : albums) {
            AlbumAssignment albumAssignment  = new AlbumAssignment();
            albumAssignment .setId(album.getId());
            albumAssignment .setAlbumName(album.getAlbumName());
            Set<Song> songs = album.getSongs();
//            StringBuilder songsNames = new StringBuilder();
            StringJoiner songsNames = new StringJoiner(", ");
            for (Song song : songs) {
                if (song.getSongName() != null)
                    songsNames.add(song.getSongName());
            }
            albumAssignment.setSongs(songsNames.toString());
            StringJoiner genres = new StringJoiner(", ");
//            StringBuilder genres = new StringBuilder();
            for (Song song : songs) {
                if (song.getGenre() != null)
                    genres.add(song.getGenre().getName());
            }

            albumAssignment.setGenres(genres.toString());
            albumAssignment.setReleaseYear(album.getReleaseYear());
            Collection<AlbumArtistGroup> albumArtistGroups = album.getAlbumArtistGroups();
//            StringBuilder artists = new StringBuilder();
            StringJoiner artists = new StringJoiner(", ");
            for (AlbumArtistGroup albumArtistGroup : albumArtistGroups) {
                Artist artist = albumArtistGroup.getArtist();
                if (artist != null) {
                    artists.add(artist.getFirstName() + " " + artist.getLastName());
                }
            }

            albumAssignment.setArtistName(artists.toString());
            StringJoiner groups = new StringJoiner(", ");
//            StringBuilder groups = new StringBuilder();
            for (AlbumArtistGroup albumArtistGroup : albumArtistGroups) {
                if (albumArtistGroup.getGroup() != null) {
                    groups.add(albumArtistGroup.getGroup().getGroupName());
                }
            }
            albumAssignment.setGroupName(groups.toString());
            albumAssignments.add(albumAssignment);
        }

        model.addAttribute("albumAssignments", albumAssignments);
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
