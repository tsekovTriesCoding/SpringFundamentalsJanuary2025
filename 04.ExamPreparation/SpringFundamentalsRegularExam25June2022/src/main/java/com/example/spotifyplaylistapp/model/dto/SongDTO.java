package com.example.spotifyplaylistapp.model.dto;

public class SongDTO {
    private Long id;
    private String title;
    private String performer;
    private Double duration;

    public SongDTO() {
    }

    public Long getId() {
        return id;
    }

    public SongDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getDuration() {
        return duration;
    }

    public SongDTO setDuration(Double duration) {
        this.duration = duration;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public SongDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SongDTO setTitle(String title) {
        this.title = title;
        return this;
    }
}
