package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.service.LoggedUser;
import com.dictionaryapp.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final WordService wordService;

    @Autowired
    public HomeController(LoggedUser loggedUser, WordService wordService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
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

        Set<Word> allGermanWords = wordService.getAllGermanWords();
        Set<Word> allFrenchWords = wordService.getAllFrenchWords();
        Set<Word> allSpanishWords = wordService.getAllSpanishWords();
        Set<Word> allItalianWords = wordService.getAllItalianWords();

        model.addAttribute("allGermanWords", allGermanWords);
        model.addAttribute("allFrenchWords", allFrenchWords);
        model.addAttribute("allSpanishWord", allSpanishWords);
        model.addAttribute("allItalianWords", allItalianWords);

        return "home";
    }
}
