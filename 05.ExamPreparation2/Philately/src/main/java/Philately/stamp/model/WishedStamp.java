package Philately.stamp.model;

import Philately.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class WishedStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private User owner;
}
