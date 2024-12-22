package com.likebookapp.model.dto;

import com.likebookapp.model.enums.MoodEnum;

public class PostDTO {
    private Long Id;
    private String content;
    private MoodEnum mood;
    private Integer likes;

    public PostDTO() {
    }

    public String getContent() {
        return content;
    }

    public PostDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public Long getId() {
        return Id;
    }

    public PostDTO setId(Long id) {
        Id = id;
        return this;
    }

    public MoodEnum getMood() {
        return mood;
    }

    public PostDTO setMood(MoodEnum mood) {
        this.mood = mood;
        return this;
    }

    public Integer getLikes() {
        return likes;
    }

    public PostDTO setLikes(Integer likes) {
        this.likes = likes;
        return this;
    }
}
