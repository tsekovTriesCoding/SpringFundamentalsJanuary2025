package bg.softuni.musicdb.controller;

import bg.softuni.musicdb.model.dto.AlbumShortInfoDTO;
import bg.softuni.musicdb.service.AlbumService;
import bg.softuni.musicdb.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final AlbumService albumService;

    public HomeController(LoggedUser loggedUser,
                          AlbumService albumService) {
        this.loggedUser = loggedUser;
        this.albumService = albumService;
    }

    @GetMapping
    String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("home")
    String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        Set<AlbumShortInfoDTO> allAlbums = this.albumService.getAll();
        model.addAttribute("allAlbums", allAlbums);

        int albumsCount = allAlbums.stream().mapToInt(AlbumShortInfoDTO::getCopies).sum();
        model.addAttribute("albumsCount", albumsCount);

        return "home";
    }
}
