package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.Style;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddSongDTO {
    @NotNull
    @Size(min = 3, max = 20, message = "Performer length must be between 3 and 20 characters!")
    private String performer;

    @NotNull
    @Size(min = 2, max = 20, message = "Title length must be between 2 and 20 characters!")
    private String title;

    @NotNull
    @Positive(message = "Duration must be positive!")
    private Integer durationInSeconds;

    @PastOrPresent(message = "The date cannot be in the future ")
    private LocalDate releaseDate;

    @NotNull(message = "You myst select a style!")
    private Style style;

    public AddSongDTO() {
    }

    public Integer getDurationInSeconds() {
        return durationInSeconds;
    }

    public AddSongDTO setDurationInSeconds(Integer durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public AddSongDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AddSongDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public AddSongDTO setStyle(Style style) {
        this.style = style;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AddSongDTO setTitle(String title) {
        this.title = title;
        return this;
    }
}
