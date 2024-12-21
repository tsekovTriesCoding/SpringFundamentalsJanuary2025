package com.likebookapp.init;

import com.likebookapp.service.MoodService;
import com.likebookapp.service.impl.MoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {
    private final MoodService moodService;

    @Autowired
    public FirstInit(MoodService moodService) {
        this.moodService = moodService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.moodService.initMoods();
    }
}
