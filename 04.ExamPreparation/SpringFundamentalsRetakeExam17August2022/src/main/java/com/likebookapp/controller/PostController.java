package com.likebookapp.controller;

import com.likebookapp.model.dto.AddPostDTO;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/posts")
public interface PostController {

    @GetMapping("/add-post")
    String addPost();

    @PostMapping("/add-post")
    String addPost(@Valid AddPostDTO addOfferDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/remove/{id}")
    String removePost(@PathVariable Long id);

    @GetMapping("/like-post/{id}")
    String likePost(@PathVariable Long id);
}
