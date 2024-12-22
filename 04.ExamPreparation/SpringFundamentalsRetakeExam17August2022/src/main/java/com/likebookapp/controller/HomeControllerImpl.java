package com.likebookapp.controller;

import com.likebookapp.model.dto.UserWithPostsDTO;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.service.PostService;
import com.likebookapp.service.UserService;
import com.likebookapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Set;

@Controller
public class HomeControllerImpl implements HomeController {
    private final LoggedUser loggedUser;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public HomeControllerImpl(LoggedUser loggedUser, UserService userService, PostService postService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @Override
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        User user = this.userService.findUserById(loggedUser.getId()).orElse(null);
        model.addAttribute("currentUserInfo", user);

        Set<Post> userPosts = this.userService.getUserPosts(this.loggedUser.getId());
        model.addAttribute("currentUserPosts", userPosts);

        Set<UserWithPostsDTO> otherUsersPosts = this.userService.getOtherUsersPosts(this.loggedUser.getId());
        model.addAttribute("otherUsersPosts", otherUsersPosts);

        return "home";
    }
}
