package bg.softuni.musicdb.service;

import bg.softuni.musicdb.model.dto.AddAlbumDTO;
import bg.softuni.musicdb.model.dto.AlbumShortInfoDTO;

import java.util.Set;

public interface AlbumService {
    void add(AddAlbumDTO addAlbumDTO, Long userId);

    Set<AlbumShortInfoDTO> getAll();

    void initAlbums();
}
