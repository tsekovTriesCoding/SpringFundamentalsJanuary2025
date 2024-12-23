package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.AddSongDTO;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SongControllerImpl implements SongController {
    private final LoggedUser loggedUser;
    private final SongService songService;

    @Autowired
    public SongControllerImpl(LoggedUser loggedUser, SongService songService) {
        this.loggedUser = loggedUser;
        this.songService = songService;
    }

    @ModelAttribute
    public AddSongDTO addSongDTO() {
        return new AddSongDTO();
    }

    @Override
    public String addSong() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        return "song-add";
    }

    @Override
    public String addSong(AddSongDTO addSongDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addSongDTO", addSongDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addSongDTO", result);

            return "redirect:/songs/add-song";
        }

        this.songService.addSong(addSongDTO, loggedUser.getId());

        return "redirect:/home";
    }
}
