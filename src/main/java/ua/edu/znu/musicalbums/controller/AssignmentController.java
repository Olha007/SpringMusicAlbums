package ua.edu.znu.musicalbums.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.znu.musicalbums.model.*;
import ua.edu.znu.musicalbums.repository.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
public class AssignmentController {
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SongRepository songRepository;
    @Autowired
    AlbumArtistGroupRepository albumArtistGroupRepository;

    @PostMapping("/albumassign")
    public String assign(@RequestParam String action, @RequestParam long albumId,
                         HttpServletRequest request, Model model) {
        Album album = albumRepository.findById(albumId).isPresent() ? albumRepository.findById(albumId).get() : new Album();
        switch (action) {
            case "albumSelect" -> {
            }
            case "assignArtist" -> {
                Long artistId = Long.valueOf(request.getParameter("selectedArtist"));
                Artist artist = artistRepository.findById(artistId).isPresent() ? artistRepository.findById(artistId).get() : new Artist();
                AlbumArtistGroup aag = new AlbumArtistGroup();
                aag.setAlbum(album);
                aag.setArtist(artist);
                albumArtistGroupRepository.save(aag);
            }
            case "removeArtist" -> {
                Long artistId = Long.valueOf(request.getParameter("selectedArtist"));
                Artist artist = artistRepository.findById(artistId).isPresent() ? artistRepository.findById(artistId).get() : new Artist();
                Collection<AlbumArtistGroup> aags = album.getAlbumArtistGroups();
                for (AlbumArtistGroup aag : aags) {
                    if (aag.getArtist() != null && aag.getArtist().equals(artist)) {
                        aag.setArtist(null);
                        albumArtistGroupRepository.save(aag);
                    }
                }
            }

            case "assignGroup" -> {
                Long groupId = Long.valueOf(request.getParameter("selectedGroup"));
                Group group = groupRepository.findById(groupId).isPresent() ? groupRepository.findById(groupId).get() : new Group();
                AlbumArtistGroup aag = new AlbumArtistGroup();
                aag.setAlbum(album);
                aag.setGroup(group);
                albumArtistGroupRepository.save(aag);
            }
            case "removeGroup" -> {
                Long groupId = Long.valueOf(request.getParameter("selectedGroup"));
                Group group = groupRepository.findById(groupId).isPresent() ? groupRepository.findById(groupId).get() : new Group();
                Collection<AlbumArtistGroup> aags = album.getAlbumArtistGroups();
                for (AlbumArtistGroup aag : aags) {
                    if (aag.getGroup() != null && aag.getGroup().equals(group)) {
                        aag.setGroup(null);
                        albumArtistGroupRepository.save(aag);
                    }
                }
            }
            case "assignSong" -> {
                Long songId = Long.valueOf(request.getParameter("selectedSong"));
                Song song = songRepository.findById(songId).isPresent() ? songRepository.findById(songId).get() : new Song();
                album.getSongs().add(song);
                albumRepository.save(album);
            }
            case "removeSong" -> {
                Long songId = Long.valueOf(request.getParameter("selectedSong"));
                Song song = songRepository.findById(songId).isPresent() ? songRepository.findById(songId).get() : new Song();
                album.getSongs().remove(song);
                albumRepository.save(album);
            }
        }
        List<Artist> availableArtists = (List<Artist>) artistRepository.findAll();
        Collection<AlbumArtistGroup> albumArtistGroup = album.getAlbumArtistGroups();
        List<Artist> albumArtists = albumArtistGroup.stream()
                .map(AlbumArtistGroup::getArtist)
                .filter(Objects::nonNull)
                .toList();
        availableArtists.removeAll(albumArtists);

        List<Group> availableGroups = (List<Group>) groupRepository.findAll();
        List<Group> albumGroups = album.getAlbumArtistGroups().stream()
                .map(AlbumArtistGroup::getGroup)
                .filter(Objects::nonNull)
                .toList();
        availableGroups.removeAll(albumGroups);

        List<Song> availableSongs = (List<Song>) songRepository.findAll();
        Set<Song> albumSongs = album.getSongs();
        availableSongs.removeAll(albumSongs);
        model.addAttribute("album", album);
        model.addAttribute("albumArtists", albumArtists);
        model.addAttribute("otherArtists", availableArtists);
        model.addAttribute("albumGroups", albumGroups);
        model.addAttribute("otherGroups", availableGroups);
        model.addAttribute("albumSongs", albumSongs);
        model.addAttribute("otherSongs", availableSongs);

        return "albumassignment";
    }
}



