package com.philately.model.entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Paper paper;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    private User owner;

    public Stamp() {
    }

    public String getDescription() {
        return description;
    }

    public Stamp setDescription(String description) {
        this.description = description;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public Stamp setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Stamp setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public Stamp setName(String name) {
        this.name = name;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Stamp setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public Paper getPaper() {
        return paper;
    }

    public Stamp setPaper(Paper paper) {
        this.paper = paper;
        return this;
    }
}
