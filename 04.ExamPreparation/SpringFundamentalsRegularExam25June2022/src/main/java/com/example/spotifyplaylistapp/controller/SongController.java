package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.AddSongDTO;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/songs")
public interface SongController {

    @GetMapping("/add-song")
    String addSong();

    @PostMapping("/add-song")
    String addSong(@Valid AddSongDTO addSongDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/add-song-to-playlist/{id}")
    String addSongToPlaylist(@PathVariable Long id);

    @GetMapping("/remove-songs-from-playlist")
    String removeAllSongsFromPlaylist();
}
