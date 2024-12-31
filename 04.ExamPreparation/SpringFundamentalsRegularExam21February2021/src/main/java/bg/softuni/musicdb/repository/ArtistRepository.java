package bg.softuni.musicdb.repository;

import bg.softuni.musicdb.model.entity.Artist;
import bg.softuni.musicdb.model.enums.ArtistNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByName(ArtistNameEnum name);
}
