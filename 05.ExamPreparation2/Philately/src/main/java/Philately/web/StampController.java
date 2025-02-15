package Philately.web;

import Philately.stamp.service.StampService;
import Philately.user.model.User;
import Philately.user.service.UserService;
import Philately.web.dto.CreateNewStamp;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/stamps")
public class StampController {

    private final UserService userService;
    private final StampService stampService;

    @Autowired
    public StampController(UserService userService, StampService stampService) {
        this.userService = userService;
        this.stampService = stampService;
    }

    @GetMapping("/new")
    public ModelAndView getNewStampPage(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-stamp");
        modelAndView.addObject("user", user);
        modelAndView.addObject("createNewStamp", new CreateNewStamp());

        return modelAndView;
    }

    @PostMapping
    public ModelAndView createNewStamp(@Valid CreateNewStamp createNewStamp, BindingResult bindingResult, HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        if (bindingResult.hasErrors()) {

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("add-stamp");
            modelAndView.addObject("user", user);

            return modelAndView;
        }

        stampService.create(createNewStamp, user);

        return new ModelAndView("redirect:/home");
    }

    // /stamps/{id}/whishlist
    @PostMapping("/{id}/whishlist")
    public String createNewWishlistItemForStamp(@PathVariable UUID id, HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        stampService.createWished(id, user);

        return "redirect:/home";
    }

    // /stamps/{id}
    @DeleteMapping("/{id}")
    public String deleteWishedStamp(@PathVariable UUID id) {

        stampService.deleteWishedStampById(id);

        return "redirect:/home";
    }
}
