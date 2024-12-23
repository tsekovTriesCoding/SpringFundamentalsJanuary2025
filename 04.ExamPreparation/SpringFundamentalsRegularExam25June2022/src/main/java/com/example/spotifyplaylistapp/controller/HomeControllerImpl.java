package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.SongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Set;

@Controller
public class HomeControllerImpl implements HomeController {
    private final LoggedUser loggedUser;
    private final UserService userService;
    private final SongService songService;

    @Autowired
    public HomeControllerImpl(LoggedUser loggedUser,
                              UserService userService,
                              SongService songService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.songService = songService;
    }

    @Override
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @Override
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        User user = this.userService.findUserById(loggedUser.getId()).orElse(null);

        Set<SongDTO> userSongs = this.userService.getUserSongs(this.loggedUser.getId());
        model.addAttribute("userSongs", userSongs);

        int totalSongsDuration = userSongs
                .stream()
                .mapToInt(SongDTO::getDuration)
                .sum();
        model.addAttribute("totalSongsDuration", totalSongsDuration);

        Set<SongDTO> allPopSongs = this.songService.getAllPopSongs();
        model.addAttribute("allPopSongs", allPopSongs);

        Set<SongDTO> allRockSongs = this.songService.getAllRockSongs();
        model.addAttribute("allRockSongs", allRockSongs);

        Set<SongDTO> allJazzSongs = this.songService.getAllJazzSongs();
        model.addAttribute("allJazzSongs", allJazzSongs);

        return "home";
    }
}
