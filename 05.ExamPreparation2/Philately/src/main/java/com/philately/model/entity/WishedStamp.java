package com.philately.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class WishedStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    private User owner;

    public WishedStamp() {
    }

    public String getDescription() {
        return description;
    }

    public WishedStamp setDescription(String description) {
        this.description = description;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public WishedStamp setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public WishedStamp setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public WishedStamp setName(String name) {
        this.name = name;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public WishedStamp setOwner(User owner) {
        this.owner = owner;
        return this;
    }
}
