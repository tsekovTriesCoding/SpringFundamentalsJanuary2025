package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.util.LoggedUser;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final WordService wordService;
    private final UserService userService;

    public HomeController(LoggedUser loggedUser, WordService wordService, UserService userService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String notLogged() {
        if (this.loggedUser.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String logged(Model model) {
        if (!this.loggedUser.isLoggedIn()) {
            return "redirect:/";
        }

        Set<Word> allGermanWords = this.wordService.getAllGermanWords();
        Set<Word> allFrenchWords = this.wordService.getAllFrenchWords();
        Set<Word> allSpanishWords = this.wordService.getAllSpanishWords();
        Set<Word> allItalianWords = this.wordService.getAllItalianWords();

        model.addAttribute("allGermanWords", allGermanWords);
        model.addAttribute("allFrenchWords", allFrenchWords);
        model.addAttribute("allSpanishWord", allSpanishWords);
        model.addAttribute("allItalianWords", allItalianWords);

        long allCount = this.wordService.getAllCount();
        model.addAttribute("allCount", allCount);

        return "home";
    }

    @GetMapping("/home/remove-all")
    public String removeAll() {
        this.userService.removeAllWords();
        return "redirect:/home";
    }
}
