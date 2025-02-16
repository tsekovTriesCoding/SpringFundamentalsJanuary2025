package app.web;

import app.user.service.UserService;
import app.web.dto.EditProfileRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/users")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/profile")
    public ModelAndView getEditProfilePage(@PathVariable UUID userId) {
        EditProfileRequest editProfileRequest = userService.getUserEditProfileInfo(userId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit-profile");
        modelAndView.addObject("editProfileRequest", editProfileRequest);
        modelAndView.addObject("userId", userId);

        return modelAndView;
    }

    @PutMapping("/{userId}/profile")
    public ModelAndView editProfile(@Valid EditProfileRequest editProfileRequest,
                                    BindingResult bindingResult,
                                    @PathVariable UUID userId) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("edit-profile");
            modelAndView.addObject("userId", userId);

            return modelAndView;
        }

        userService.editProfile(editProfileRequest, userId);

        return new ModelAndView("redirect:/home");
    }
}
