package com.likebookapp.controller;

import com.likebookapp.model.dto.AddPostDTO;
import com.likebookapp.service.PostService;
import com.likebookapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostControllerImpl implements PostController {
    private final LoggedUser loggedUser;
    private final PostService postService;

    @Autowired
    public PostControllerImpl(LoggedUser loggedUser, PostService postService) {
        this.loggedUser = loggedUser;
        this.postService = postService;
    }

    @ModelAttribute
    AddPostDTO addPostDTO() {
        return new AddPostDTO();
    }

    @Override
    public String addPost() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        return "post-add";
    }

    @Override
    public String addPost(AddPostDTO addPostDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addPostDTO", addPostDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addPostDTO", result);

            return "redirect:/posts/add-post";
        }

        this.postService.addPost(addPostDTO, loggedUser.getId());

        return "redirect:/home";
    }
}
