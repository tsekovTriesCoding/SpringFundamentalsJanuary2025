package bg.softuni.musicdb.model.entity;

import bg.softuni.musicdb.model.enums.GenreEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "albums")
public class Album extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer copies;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column
    private String producer;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    @ManyToOne(optional = false)
    private Artist artist;

    @ManyToOne(optional = false)
    @JoinColumn(name = "added_from_id")
    private User addedFrom;

    public Album() {
    }

    public User getAddedFrom() {
        return addedFrom;
    }

    public Album setAddedFrom(User addedFrom) {
        this.addedFrom = addedFrom;
        return this;
    }

    public Artist getArtist() {
        return artist;
    }

    public Album setArtist(Artist artist) {
        this.artist = artist;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public Album setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Album setDescription(String description) {
        this.description = description;
        return this;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public Album setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Album setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public Album setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Album setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public Album setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Album setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }
}
