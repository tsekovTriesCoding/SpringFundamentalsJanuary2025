package bg.softuni.springfundamentalsretakeexam15december2021.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    String index() {
        return "index";
    }

    @GetMapping("home")
    String home(Model model) {
        return "home";
    }
}
