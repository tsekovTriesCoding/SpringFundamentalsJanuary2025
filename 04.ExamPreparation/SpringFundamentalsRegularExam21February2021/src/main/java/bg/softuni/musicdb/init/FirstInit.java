package bg.softuni.musicdb.init;

import bg.softuni.musicdb.service.AlbumService;
import bg.softuni.musicdb.service.ArtistService;
import bg.softuni.musicdb.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {
    private final ArtistService artistService;
    private final UserService userService;
    private final AlbumService albumService;

    public FirstInit(ArtistService artistService,
                     UserService userService,
                     AlbumService albumService) {
        this.artistService = artistService;
        this.userService = userService;
        this.albumService = albumService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.artistService.initArtists();
        this.userService.initAdmin();
        this.albumService.initAlbums();
    }
}
