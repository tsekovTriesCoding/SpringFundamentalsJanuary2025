package com.likebookapp.model.dto;

import java.util.Set;

public class UserWithPostsDTO {
    private String username;
    private Long id;
    private Set<PostDTO> posts;

    public UserWithPostsDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserWithPostsDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserWithPostsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<PostDTO> getPosts() {
        return posts;
    }

    public UserWithPostsDTO setPosts(Set<PostDTO> posts) {
        this.posts = posts;
        return this;
    }
}
