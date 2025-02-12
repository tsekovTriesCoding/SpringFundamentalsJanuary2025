package com.philately.model.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Stamp> stamps;

    @OneToMany(mappedBy = "owner")
    private List<WishedStamp> wishedStamps;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<Stamp> getStamps() {
        return stamps;
    }

    public User setStamps(List<Stamp> stamps) {
        this.stamps = stamps;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<WishedStamp> getWishedStamps() {
        return wishedStamps;
    }

    public User setWishedStamps(List<WishedStamp> wishedStamps) {
        this.wishedStamps = wishedStamps;
        return this;
    }
}
