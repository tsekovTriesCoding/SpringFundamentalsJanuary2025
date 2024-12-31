package bg.softuni.musicdb.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumShortInfoDTO {
    private String name;
    private String imageUrl;
    private String artist;
    private String genre;
    private LocalDate releaseDate;
    private int copies;
    private BigDecimal price;

    public AlbumShortInfoDTO() {
    }

    public String getArtist() {
        return artist;
    }

    public AlbumShortInfoDTO setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public int getCopies() {
        return copies;
    }

    public AlbumShortInfoDTO setCopies(int copies) {
        this.copies = copies;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public AlbumShortInfoDTO setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlbumShortInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AlbumShortInfoDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumShortInfoDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumShortInfoDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
