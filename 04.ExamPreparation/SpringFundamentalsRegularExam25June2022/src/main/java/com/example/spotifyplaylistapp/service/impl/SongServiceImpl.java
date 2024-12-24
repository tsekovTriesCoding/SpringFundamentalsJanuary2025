package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.dto.AddSongDTO;
import com.example.spotifyplaylistapp.model.dto.SongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.model.enums.StyleEnum;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.util.RoundDouble;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final StyleRepository styleRepository;

    @Autowired
    public SongServiceImpl(SongRepository songRepository, UserRepository userRepository, StyleRepository styleRepository) {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.styleRepository = styleRepository;
    }

    @Transactional
    @Override
    public void initSongs() {
        if (this.songRepository.count() > 0) {
            return;
        }

        User admin = this.userRepository.findById(Long.parseLong("1")).orElse(null);
        User user = this.userRepository.findById(Long.parseLong("2")).orElse(null);

        Song adminSong1 = new Song()
                .setDuration(300)
                .setPerformer("Ed Sheeran")
                .setTitle("Shape of you")
                .setStyle(this.styleRepository.findByStyleName(StyleEnum.POP));

        admin.getPlaylist().add(adminSong1);
        this.songRepository.save(adminSong1);

        Song adminSong2 = new Song()
                .setDuration(500)
                .setPerformer("Керана и космонавтите")
                .setTitle("Комедиантката")
                .setStyle(this.styleRepository.findByStyleName(StyleEnum.ROCK));

        admin.getPlaylist().add(adminSong2);
        this.songRepository.save(adminSong2);

        Song adminSong3 = new Song()
                .setDuration(500)
                .setPerformer("Miles Davis")
                .setTitle("Blue In Green")
                .setStyle(this.styleRepository.findByStyleName(StyleEnum.JAZZ));

        admin.getPlaylist().add(adminSong3);
        this.songRepository.save(adminSong3);

        Song userSong1 = new Song()
                .setDuration(320)
                .setPerformer("Britney Spears")
                .setTitle("Toxic")
                .setStyle(this.styleRepository.findByStyleName(StyleEnum.POP));

        user.getPlaylist().add(userSong1);
        this.songRepository.save(userSong1);

        Song userSong2 = new Song()
                .setDuration(320)
                .setPerformer("Графа")
                .setTitle("Невидим")
                .setStyle(this.styleRepository.findByStyleName(StyleEnum.POP));

        user.getPlaylist().add(userSong2);
        this.songRepository.save(userSong2);

        Song userSong3 = new Song()
                .setDuration(410)
                .setPerformer("Queen")
                .setTitle("Bohemian Rhapsody")
                .setStyle(this.styleRepository.findByStyleName(StyleEnum.ROCK));

        user.getPlaylist().add(userSong3);
        this.songRepository.save(userSong3);

        this.userRepository.save(admin);
        this.userRepository.save(user);
    }

    @Override
    public Set<SongDTO> getAllSongs() {
        List<Song> allSongs = this.songRepository.findAll();

        return mapSongs(allSongs);
    }

    @Override
    public Set<SongDTO> getAllPopSongs() {
        Style style = this.styleRepository.findByStyleName(StyleEnum.POP);
        List<Song> allPopSongs = this.songRepository.getSongsByStyle(style);

        return mapSongs(allPopSongs);
    }

    @Override
    public Set<SongDTO> getAllRockSongs() {
        Style style = this.styleRepository.findByStyleName(StyleEnum.ROCK);
        List<Song> allRockSongs = this.songRepository.getSongsByStyle(style);

        return mapSongs(allRockSongs);
    }

    @Override
    public Set<SongDTO> getAllJazzSongs() {
        Style style = this.styleRepository.findByStyleName(StyleEnum.JAZZ);
        List<Song> allJazzSongs = this.songRepository.getSongsByStyle(style);

        return mapSongs(allJazzSongs);
    }

    @Override
    public void addSong(AddSongDTO addSongDTO, Long userId) {
        Song song = new Song();
        Style style = this.styleRepository.findByStyleName(addSongDTO.getStyle());

        song.setTitle(addSongDTO.getTitle());
        song.setStyle(style);
        song.setPerformer(addSongDTO.getPerformer());
        song.setDuration(addSongDTO.getDuration());

        this.songRepository.save(song);
    }

    @Transactional
    @Override
    public void addSongToUserPlaylist(Long songId, Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);
        Song song = this.songRepository.findById(songId).orElse(null);

        user.getPlaylist().add(song);
        this.songRepository.save(song);
        this.userRepository.save(user);
    }

    @Override
    public void removeAllSongFromUserPlaylist(Long id) {
        User user = this.userRepository.findById(id).orElse(null);

        user.setPlaylist(null);
        this.userRepository.save(user);
    }

    private Set<SongDTO> mapSongs(List<Song> songs) {
        return songs.stream().map(song -> {
            SongDTO songDTO = new SongDTO();

            double duration = (double)song.getDuration() / 60;
            duration = RoundDouble.round(duration, 2);

            songDTO.setId(song.getId());
            songDTO.setTitle(song.getTitle());
            songDTO.setDuration(duration);
            songDTO.setPerformer(song.getPerformer());
            return songDTO;
        }).collect(Collectors.toSet());
    }
}
