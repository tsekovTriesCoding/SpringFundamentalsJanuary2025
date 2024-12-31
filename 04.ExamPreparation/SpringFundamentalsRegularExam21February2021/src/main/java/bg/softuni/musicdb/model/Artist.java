package bg.softuni.musicdb.model;

import bg.softuni.musicdb.model.enums.ArtistNameEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "artists")
public class Artist extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ArtistNameEnum name;

    @Column(name = "career_information")
    private String careerInformation;

    public Artist() {
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public Artist setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }

    public ArtistNameEnum getName() {
        return name;
    }

    public Artist setName(ArtistNameEnum name) {
        this.name = name;
        return this;
    }
}
