package bg.softuni.musicdb.init;

import bg.softuni.musicdb.service.ArtistService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {
    private final ArtistService artistService;

    public FirstInit(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.artistService.initArtists();
    }
}
