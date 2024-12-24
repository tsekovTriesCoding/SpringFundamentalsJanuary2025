package bg.softuni.linkedout.web;

import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    public HomeController() {
    }

    @GetMapping({"/"})
    public String homeView(Model model) {
        LocalDate date = LocalDate.now();
        String text = "Last update of Database: " + date;
        model.addAttribute("text", text);
        return "index";
    }
}