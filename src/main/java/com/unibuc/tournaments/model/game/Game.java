package com.unibuc.tournaments.model.game;

import com.unibuc.tournaments.model.team.Team;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Setter
@Getter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required field")
    private String name;

    @NotBlank(message = "required field")
    private String publisherName;

    @Enumerated(value = EnumType.STRING)
    private GameGenre genre;

    @OneToMany(mappedBy = "game")
    private List<Team> teams;
}
