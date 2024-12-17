package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.enums.LanguageEnum;
import com.dictionaryapp.service.LoggedUser;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WordController {
    private final WordService wordService;
    private final LoggedUser loggedUser;

    @Autowired
    public WordController(WordService wordService, LoggedUser loggedUser) {
        this.wordService = wordService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute(name = "addWordData")
    public AddWordDTO addWordData() {
        return new AddWordDTO();
    }

    @ModelAttribute(name = "languages")
    public List<LanguageEnum> languages() {
        return List.of(LanguageEnum.values());
    }

    @GetMapping("/add-word")
    public String viewAddWord() {
        if (!this.loggedUser.isLoggedIn()) {
            return "redirect:/login";
        }

        return "/word-add";
    }

    @PostMapping("/add-word")
    public String doAddWord(@Valid AddWordDTO data,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addWordData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addWordData", bindingResult);

            return "redirect:/add-word";
        }

        this.wordService.addWord(data, this.loggedUser.getId());

        return "redirect:/home";
    }

    @GetMapping("/remove-word/{id}")
    public String removeWord(@PathVariable Long id) {
        wordService.removeWordById(id);

        return "redirect:/home";
    }
}
