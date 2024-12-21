package com.likebookapp.model.entity;

import com.likebookapp.model.enums.MoodEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "moods")
public class Mood extends BaseEntity{
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private MoodEnum moodName;

    @Column
    private String description;

    public Mood() {
    }

    public Mood(MoodEnum name) {
        this.moodName = name;
    }

    public MoodEnum getMoodName() {
        return moodName;
    }

    public Mood setMoodName(MoodEnum moodName) {
        this.moodName = moodName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Mood setDescription(String description) {
        this.description = description;
        return this;
    }
}
