package com.likebookapp.service;

import com.likebookapp.model.dto.AddPostDTO;

public interface PostService {
    void addPosts();

    void addPost(AddPostDTO addPostDTO, Long id);
}
