package com.unibuc.tournaments.repository;

import com.unibuc.tournaments.exception.team.TeamNotFoundException;
import com.unibuc.tournaments.model.team.TeamMember;
import com.unibuc.tournaments.model.team.TeamMemberType;
import com.unibuc.tournaments.model.tournament.Match;
import com.unibuc.tournaments.model.tournament.MatchStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class MatchRepository {
    private JdbcTemplate jdbcTemplate;

    public static RowMapper<Match> matchMapper = ((resultSet, i) ->
            new Match(resultSet.getInt("id"),
                    resultSet.getInt("bracket_id"),
                    resultSet.getString("bracket_phase"),
                    resultSet.getInt("red_team_id"),
                    resultSet.getInt("blue_team_id"),
                    resultSet.getInt("best_of"),
                    resultSet.getInt("red_team_score"),
                    resultSet.getInt("blue_team_score"),
                    MatchStatus.valueOf(resultSet.getString("status")),
                    resultSet.getDate("scheduled_date"),
                    resultSet.getBoolean("default_win")));

    public MatchRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Match> createMatch(Match match) {
        String query = "INSERT INTO tournament_match VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setInt(2, match.getBracketId());
            preparedStatement.setString(3, match.getBracketPhase());
            preparedStatement.setInt(4, match.getRedTeamId());
            preparedStatement.setInt(5, match.getBlueTeamId());
            preparedStatement.setInt(6, match.getBestOf());
            preparedStatement.setInt(7, match.getRedTeamScore());
            preparedStatement.setInt(8, match.getBlueTeamScore());
            preparedStatement.setString(9, match.getStatus().toString());
            preparedStatement.setDate(10, (Date) match.getScheduledDate());
            preparedStatement.setBoolean(11, match.getDefaultWin());
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        return getMatch(Objects.requireNonNull(generatedKeyHolder.getKey()).intValue());
    }

    public Optional<Match> getMatch(int id) {
        String query = "SELECT * FROM tournament_match WHERE id = ?";
        List<Match> matches = jdbcTemplate.query(query, matchMapper, id);

        if (!matches.isEmpty()) {
            Match match = matches.get(0);
//            TODO
//            match.setCustomFields(getMatchCustomFields(match.getId()));
            return Optional.of(match);
        } else {
            return Optional.empty();
        }
    }
}
