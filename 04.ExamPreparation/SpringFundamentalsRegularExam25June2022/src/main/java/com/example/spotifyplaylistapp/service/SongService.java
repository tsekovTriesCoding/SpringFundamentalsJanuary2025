package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.AddSongDTO;
import com.example.spotifyplaylistapp.model.dto.SongDTO;

import java.util.Set;

public interface SongService {
    void initSongs();

    Set<SongDTO> getAllSongs();

    Set<SongDTO> getAllPopSongs();

    Set<SongDTO> getAllRockSongs();

    Set<SongDTO> getAllJazzSongs();

    void addSong(AddSongDTO addSongDTO, Long userId);

    void addSongToUserPlaylist(Long songId, Long userId);

    void removeAllSongFromUserPlaylist(Long id);
}
