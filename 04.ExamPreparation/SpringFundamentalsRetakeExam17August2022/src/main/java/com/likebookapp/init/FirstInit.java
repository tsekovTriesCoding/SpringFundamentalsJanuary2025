package com.likebookapp.init;

import com.likebookapp.service.MoodService;
import com.likebookapp.service.PostService;
import com.likebookapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {
    private final MoodService moodService;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public FirstInit(MoodService moodService, UserService userService, PostService postService) {
        this.moodService = moodService;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.moodService.initMoods();
        this.userService.initAdmin();
        this.userService.initTest();
        this.postService.addPosts();
    }
}
