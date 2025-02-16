package app.web;

import app.story.model.Story;
import app.story.service.StoryService;
import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.CreateNewStory;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/stories")
public class StoryController {

    private final StoryService storyService;
    private final UserService userService;

    public StoryController(StoryService storyService, UserService userService) {
        this.storyService = storyService;
        this.userService = userService;
    }

    @GetMapping("/new")
    public ModelAndView getNewStoriesPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("createNewStory", new CreateNewStory());
        modelAndView.setViewName("add-story");

        return modelAndView;
    }

    @PostMapping("/new")
    public ModelAndView addNewStory(@Valid CreateNewStory createNewStory, BindingResult bindingResult, HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("add-story");

            return modelAndView;
        }

        storyService.create(createNewStory, user);

        return new ModelAndView("redirect:/home");
    }

    @PutMapping("/{storyId}/visibility")
    public ModelAndView changeStoryVisibility(@PathVariable UUID storyId) {
        storyService.updateVisibility(storyId);

        return new ModelAndView("redirect:/home");
    }

    @DeleteMapping("/{storyId}")
    public String deleteStory(@PathVariable UUID storyId) {
        storyService.delete(storyId);

        return "redirect:/home";
    }

    @GetMapping("/{storyId}")
    public ModelAndView getStoryDetailsPage(@PathVariable UUID storyId) {
        Story story = storyService.getDetails(storyId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("story", story);
        modelAndView.setViewName("story");

        return modelAndView;
    }
}