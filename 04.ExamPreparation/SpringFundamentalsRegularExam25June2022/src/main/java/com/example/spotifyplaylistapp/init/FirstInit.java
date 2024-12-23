package com.example.spotifyplaylistapp.init;

import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.StyleService;
import com.example.spotifyplaylistapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {
    private final StyleService styleService;
    private final UserService userService;
    private final SongService songService;

    @Autowired
    public FirstInit(StyleService styleService,
                     UserService userService,
                     SongService songService) {
        this.styleService = styleService;
        this.userService = userService;
        this.songService = songService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.styleService.initStyles();
        this.userService.initAdmin();
        this.userService.initUser();
        this.songService.initSongs();
    }
}
