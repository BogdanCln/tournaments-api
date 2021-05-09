package com.unibuc.tournaments.model.tournament;

import com.unibuc.tournaments.model.team.Team;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "tournament_match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "bracket_id")
    private Bracket bracket;

    @ManyToOne
    @JoinColumn(name = "red_team_id")
    private Team redTeam;

    @ManyToOne
    @JoinColumn(name = "blue_team_id")
    private Team blueTeam;

    //    TODO: A match can have mutiple match games, based on the 'best of' value (eg: 2 or 3 games for a BO3 match)
    //    private Integer bestOf;

    private Integer redTeamScore;
    private Integer blueTeamScore;

    @Enumerated(value = EnumType.STRING)
    private MatchStatus status;

    private Date scheduledDate;
    private Boolean defaultWin;
}
