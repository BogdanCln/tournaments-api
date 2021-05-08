package com.unibuc.tournaments.model.tournament;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Dictionary;

@Data
@Entity
@Table(name = "tournament_match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "bracket_id")
    private Bracket bracket;

    private String bracketPhase;
    private Integer redTeamId;
    private Integer blueTeamId;
    private Integer bestOf;
    private Integer redTeamScore;
    private Integer blueTeamScore;

    @Enumerated(value = EnumType.STRING)
    private MatchStatus status;

    private Date scheduledDate;
    private Boolean defaultWin;
}
