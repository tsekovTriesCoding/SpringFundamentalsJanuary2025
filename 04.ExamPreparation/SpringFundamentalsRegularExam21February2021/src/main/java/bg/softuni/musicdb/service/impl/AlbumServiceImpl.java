package bg.softuni.musicdb.service.impl;

import bg.softuni.musicdb.model.dto.AddAlbumDTO;
import bg.softuni.musicdb.model.dto.AlbumShortInfoDTO;
import bg.softuni.musicdb.model.entity.Album;
import bg.softuni.musicdb.model.entity.Artist;
import bg.softuni.musicdb.model.entity.User;
import bg.softuni.musicdb.model.enums.ArtistNameEnum;
import bg.softuni.musicdb.model.enums.GenreEnum;
import bg.softuni.musicdb.repository.AlbumRepository;
import bg.softuni.musicdb.repository.UserRepository;
import bg.softuni.musicdb.service.AlbumService;
import bg.softuni.musicdb.service.ArtistService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public LinkedHashSet<AlbumShortInfoDTO> getAll() {
        return this.albumRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Album::getCopies).reversed())
                .map(a -> {
                    AlbumShortInfoDTO albumShortInfoDTO = new AlbumShortInfoDTO();
                    albumShortInfoDTO.setName(a.getName());
                    albumShortInfoDTO.setArtist(a.getArtist().getName().getValue());
                    albumShortInfoDTO.setGenre(a.getGenre().getValue());
                    albumShortInfoDTO.setReleaseDate(a.getReleaseDate());
                    albumShortInfoDTO.setCopies(a.getCopies());
                    albumShortInfoDTO.setImageUrl(a.getImageUrl());
                    albumShortInfoDTO.setPrice(a.getPrice());
                    return albumShortInfoDTO;
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public void initAlbums() {
        if (this.albumRepository.count() > 0) {
            return;
        }

        User user = this.userRepository.findById(1L).orElse(null);

        Artist artistMetallica = this.artistService.getByName(ArtistNameEnum.METALLICA);
        Artist artistQueen = this.artistService.getByName(ArtistNameEnum.QUEEN);
        Artist artistMadonna = this.artistService.getByName(ArtistNameEnum.MADONNA);

        Album album = new Album();
        album.setName("Ride The Lightning");
        album.setArtist(artistMetallica);
        album.setGenre(GenreEnum.METAL);
        album.setPrice(BigDecimal.valueOf(39));
        album.setReleaseDate(LocalDate.of(1984, 7, 27));
        album.setCopies(2500000);
        album.setAddedFrom(user);
        album.setDescription("Ride The Lightning");
        album.setImageUrl("/img/metallica.jpg");

        this.albumRepository.save(album);

        Album album2 = new Album();
        album2.setName("The Works");
        album2.setArtist(artistQueen);
        album2.setGenre(GenreEnum.POP);
        album2.setPrice(BigDecimal.valueOf(29));
        album2.setReleaseDate(LocalDate.of(1984, 2, 27));
        album2.setCopies(2000000);
        album2.setAddedFrom(user);
        album2.setDescription("Ride The Lightning");
        album2.setImageUrl("/img/queen.jpg");

        this.albumRepository.save(album2);

        Album album3 = new Album();
        album3.setName("Madonna Antology");
        album3.setArtist(artistMadonna);
        album3.setGenre(GenreEnum.POP);
        album3.setPrice(BigDecimal.valueOf(19));
        album3.setReleaseDate(LocalDate.of(2019, 7, 18));
        album3.setCopies(2000000);
        album3.setAddedFrom(user);
        album3.setDescription("Ride The Lightning");
        album3.setImageUrl("/img/madonna.jpg");

        this.albumRepository.save(album3);

        Album album4 = new Album();
        album4.setName("S&M2");
        album4.setArtist(artistMetallica);
        album4.setGenre(GenreEnum.METAL);
        album4.setPrice(BigDecimal.valueOf(19));
        album4.setReleaseDate(LocalDate.of(2020, 9, 28));
        album4.setCopies(2000000);
        album4.setAddedFrom(user);
        album4.setDescription("Ride The Lightning");
        album4.setImageUrl("/img/metallica.jpg");

        this.albumRepository.save(album4);
    }
}
