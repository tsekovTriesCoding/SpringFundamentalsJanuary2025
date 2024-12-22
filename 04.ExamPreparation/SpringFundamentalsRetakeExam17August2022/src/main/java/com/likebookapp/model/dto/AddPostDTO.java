package com.likebookapp.model.dto;

import com.likebookapp.model.enums.MoodEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddPostDTO {
    @Size(min = 2, max = 50, message = "Content length must be between 2 and 50 characters!")
    @NotNull
    private String content;

    @NotNull(message = "You must select a mood!")
    private MoodEnum mood;

    public AddPostDTO() {
    }

    public String getContent() {
        return content;
    }

    public AddPostDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public MoodEnum getMood() {
        return mood;
    }

    public AddPostDTO setMood(MoodEnum mood) {
        this.mood = mood;
        return this;
    }
}
