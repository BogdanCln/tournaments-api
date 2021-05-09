package com.unibuc.tournaments.model.tournament;

import com.unibuc.tournaments.model.team.Team;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "tournament_match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bracket_id", nullable = false)
    @NotNull
    private Bracket bracket;

    @ManyToOne
    @JoinColumn(name = "red_team_id", nullable = false)
    @NotNull(message = "Required")
    private Team redTeam;

    @ManyToOne
    @JoinColumn(name = "blue_team_id", nullable = false)
    @NotNull(message = "Required")
    private Team blueTeam;

    //    TODO: A match can have mutiple match games, based on the 'best of' value (eg: 2 or 3 games for a BO3 match)
    //    private Integer bestOf;

    @Min(0)
    @NotNull
    private Integer redTeamScore;

    @Min(0)
    @NotNull
    private Integer blueTeamScore;

    @Enumerated(value = EnumType.STRING)
    private MatchStatus status;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date scheduledDate;

    private Boolean defaultWin = false;
}
