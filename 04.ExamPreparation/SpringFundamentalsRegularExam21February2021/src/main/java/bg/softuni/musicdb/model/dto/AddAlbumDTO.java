package bg.softuni.musicdb.model.dto;

import bg.softuni.musicdb.model.enums.ArtistNameEnum;
import bg.softuni.musicdb.model.enums.GenreEnum;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddAlbumDTO {
    @NotNull
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;

    @NotNull
    @Size(min = 5, message = "Image url must be minimum 5 characters")
    private String imageUrl;

    @NotNull
    @Size(min = 5, message = "Description length must be minimum 5 characters")
    private String description;

    @NotNull
    @Min(value = 10, message = "Copies must be more than 10")
    private Integer copies;

    @NotNull
    @Positive(message = "Price must be positive number")
    private BigDecimal price;

    @NotNull
    @PastOrPresent(message = "Release date cannot be in the future")
    private LocalDate releaseDate;

    private String producer;

    @NotNull(message = "You must select genre")
    private GenreEnum genre;

    @NotNull(message = "You must select artist")
    private ArtistNameEnum artist;

    public AddAlbumDTO() {
    }

    public ArtistNameEnum getArtist() {
        return artist;
    }

    public AddAlbumDTO setArtist(ArtistNameEnum artist) {
        this.artist = artist;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AddAlbumDTO setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddAlbumDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public AddAlbumDTO setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddAlbumDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddAlbumDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddAlbumDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public AddAlbumDTO setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AddAlbumDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }
}
