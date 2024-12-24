package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.enums.StyleEnum;
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
    private Integer duration;

    @PastOrPresent(message = "The date cannot be in the future ")
    private LocalDate releaseDate;

    @NotNull(message = "You must select a style!")
    private StyleEnum style;

    public AddSongDTO() {
    }

    public Integer getDuration() {
        return duration;
    }

    public AddSongDTO setDuration(Integer duration) {
        this.duration = duration;
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

    public StyleEnum getStyle() {
        return style;
    }

    public AddSongDTO setStyle(StyleEnum style) {
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
