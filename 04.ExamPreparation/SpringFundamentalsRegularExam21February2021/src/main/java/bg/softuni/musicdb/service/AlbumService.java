package bg.softuni.musicdb.service;

import bg.softuni.musicdb.model.dto.AddAlbumDTO;

public interface AlbumService {
    void add(AddAlbumDTO addAlbumDTO, Long userId);
}
