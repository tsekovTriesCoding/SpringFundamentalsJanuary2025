package bg.softuni.musicdb.service;

import bg.softuni.musicdb.model.entity.Artist;
import bg.softuni.musicdb.model.enums.ArtistNameEnum;

public interface ArtistService {
    void initArtists();

    Artist getByName(ArtistNameEnum artist);
}
