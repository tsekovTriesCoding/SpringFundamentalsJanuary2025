package bg.softuni.musicdb.service.impl;

import bg.softuni.musicdb.model.dto.AddAlbumDTO;
import bg.softuni.musicdb.model.entity.Album;
import bg.softuni.musicdb.model.entity.Artist;
import bg.softuni.musicdb.model.entity.User;
import bg.softuni.musicdb.repository.AlbumRepository;
import bg.softuni.musicdb.repository.UserRepository;
import bg.softuni.musicdb.service.AlbumService;
import bg.softuni.musicdb.service.ArtistService;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final UserRepository userRepository;
    private final ArtistService artistService;
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(UserRepository userRepository,
                            ArtistService artistService,
                            AlbumRepository albumRepository) {
        this.userRepository = userRepository;
        this.artistService = artistService;
        this.albumRepository = albumRepository;
    }

    @Override
    public void add(AddAlbumDTO addAlbumDTO, Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);

        Album album = new Album();
        album.setName(addAlbumDTO.getName());
        album.setDescription(addAlbumDTO.getDescription());
        album.setCopies(addAlbumDTO.getCopies());
        album.setGenre(addAlbumDTO.getGenre());
        album.setReleaseDate(addAlbumDTO.getReleaseDate());
        album.setImageUrl(addAlbumDTO.getImageUrl());

        Artist artist = this.artistService.getByName(addAlbumDTO.getArtist());
        album.setArtist(artist);
        album.setPrice(addAlbumDTO.getPrice());
        album.setProducer(addAlbumDTO.getProducer());
        album.setAddedFrom(user);

        this.albumRepository.save(album);
    }
}
