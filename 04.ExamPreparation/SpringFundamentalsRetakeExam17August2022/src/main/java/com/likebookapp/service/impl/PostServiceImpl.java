package com.likebookapp.service.impl;

import com.likebookapp.model.dto.AddPostDTO;
import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.model.enums.MoodEnum;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.service.MoodService;
import com.likebookapp.service.PostService;
import com.likebookapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final MoodService moodService;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           UserService userService,
                           MoodService moodService,
                           UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.moodService = moodService;
        this.userRepository = userRepository;
    }

    @Override
    public void addPosts() {
        if (this.postRepository.count() > 0) {
            return;
        }

        User admin = this.userService.findUserById(Long.parseLong("1")).orElse(null);
        User user = this.userService.findUserById(Long.parseLong("2")).orElse(null);

        Post adminPost1 = new Post()
                .setContent("Admin content")
                .setMood(this.moodService.findMood(MoodEnum.SAD))
                .setCreator(admin)
                .setUserLikes(Set.of(user));

        admin.getPosts().add(adminPost1);

        this.postRepository.save(adminPost1);

        Post adminPost2 = new Post()
                .setContent("I am very happy!")
                .setMood(this.moodService.findMood(MoodEnum.HAPPY))
                .setCreator(admin);

        admin.getPosts().add(adminPost2);

        this.postRepository.save(adminPost2);

        Post userPost1 = new Post()
                .setContent("User content")
                .setMood(this.moodService.findMood(MoodEnum.INSPIRED))
                .setCreator(user)
                .setUserLikes(Set.of(admin));

        user.getPosts().add(userPost1);

        this.postRepository.save(userPost1);

        Post userPost2 = new Post()
                .setContent("I am powewfulll!")
                .setMood(this.moodService.findMood(MoodEnum.HAPPY))
                .setCreator(user);

        user.getPosts().add(userPost2);

        this.postRepository.save(userPost2);

        this.userRepository.save(admin);
        this.userRepository.save(user);
    }

    @Override
    public void addPost(AddPostDTO addPostDTO, Long userId) {
        Post post = new Post();
        Mood mood = this.moodService.findMood(addPostDTO.getMood());
        User user = this.userService.findUserById(userId).orElse(null);

        post.setContent(addPostDTO.getContent())
                .setMood(mood).setCreator(user);

        user.getPosts().add(post);

        this.postRepository.save(post);
        this.userRepository.save(user);
    }
}
