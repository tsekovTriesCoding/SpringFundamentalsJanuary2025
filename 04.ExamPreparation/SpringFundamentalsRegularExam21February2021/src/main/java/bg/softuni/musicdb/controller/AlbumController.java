package bg.softuni.musicdb.controller;

import bg.softuni.musicdb.model.dto.AddAlbumDTO;
import bg.softuni.musicdb.model.enums.ArtistNameEnum;
import bg.softuni.musicdb.model.enums.GenreEnum;
import bg.softuni.musicdb.service.AlbumService;
import bg.softuni.musicdb.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/albums")
public class AlbumController {
    private final LoggedUser loggedUser;
    private final AlbumService albumService;

    public AlbumController(LoggedUser loggedUser,
                           AlbumService albumService) {
        this.loggedUser = loggedUser;
        this.albumService = albumService;
    }

    @ModelAttribute
    public AddAlbumDTO addAlbumDTO() {
        return new AddAlbumDTO();
    }

    @ModelAttribute
    public ArtistNameEnum[] artistNameEnum() {
        return ArtistNameEnum.values();
    }

    @ModelAttribute
    public GenreEnum[] genreEnum() {
        return GenreEnum.values();
    }

    @GetMapping("/add")
    public String add() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        return "add-album";
    }

    @PostMapping("/add")
    public String add(@Valid AddAlbumDTO addAlbumDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addAlbumDTO", addAlbumDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addAlbumDTO", result);

            return "redirect:/albums/add";
        }

        this.albumService.add(addAlbumDTO, this.loggedUser.getId());
        return "redirect:/home";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        this.albumService.remove(id);

        return "redirect:/home";
    }
}
