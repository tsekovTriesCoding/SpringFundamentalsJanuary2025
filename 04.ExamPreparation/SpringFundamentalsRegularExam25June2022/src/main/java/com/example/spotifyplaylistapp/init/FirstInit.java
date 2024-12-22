package com.example.spotifyplaylistapp.init;

import com.example.spotifyplaylistapp.service.StyleService;
import com.example.spotifyplaylistapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {
    private final StyleService styleService;
    private final UserService userService;

    @Autowired
    public FirstInit(StyleService styleService, UserService userService) {
        this.styleService = styleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.styleService.initStyles();
        this.userService.initAdmin();
    }
}
