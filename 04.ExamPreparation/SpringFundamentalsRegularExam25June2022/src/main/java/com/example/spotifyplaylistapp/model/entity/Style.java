package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.model.enums.StyleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity {
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private StyleEnum styleName;

    @Column
    private String description;

    public Style() {
    }

    public Style(StyleEnum styleName) {
        this.styleName = styleName;
    }

    public String getDescription() {
        return description;
    }

    public Style setDescription(String description) {
        this.description = description;
        return this;
    }

    public StyleEnum getStyleName() {
        return styleName;
    }

    public Style setStyleName(StyleEnum styleName) {
        this.styleName = styleName;
        return this;
    }
}
