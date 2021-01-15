package com.unibuc.tournaments.repository;

import com.unibuc.tournaments.exception.GenericNotFoundException;
import com.unibuc.tournaments.model.tournament.Bracket;
import com.unibuc.tournaments.model.tournament.Match;
import com.unibuc.tournaments.model.tournament.Tournament;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BracketRepository {
    private JdbcTemplate jdbcTemplate;

    public static RowMapper<Bracket> bracketMapper = ((resultSet, i) ->
            new Bracket(
                    resultSet.getInt("id"),
                    resultSet.getInt("tournament_id")
            ));

    public BracketRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Bracket> createBracket(Bracket bracket) {
        String query = "INSERT INTO bracket VALUES(?, ?)";
        PreparedStatementCreator preparedStatementCreator = (connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setInt(2, bracket.getTournamentId());
            return preparedStatement;
        });

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        return getBracket(Objects.requireNonNull(generatedKeyHolder.getKey()).intValue());
    }

    public Optional<Bracket> getBracket(int id) {
        String query = "SELECT * FROM bracket WHERE id = ?";
        List<Bracket> dbBrackets = jdbcTemplate.query(query, bracketMapper, id);

        if (!dbBrackets.isEmpty()) {
            Bracket bracket = dbBrackets.get(0);
            bracket.setPhases(getBracketPhases(bracket.getId()));
            return Optional.of(bracket);
        } else {
            return Optional.empty();
        }
    }

    public Bracket getBracketByTournamentId(int tournamentId) {
        String query = "SELECT * FROM bracket WHERE tournament_id = ?";
        List<Bracket> dbBrackets = jdbcTemplate.query(query, bracketMapper, tournamentId);

        if (!dbBrackets.isEmpty()) {
            Bracket bracket = dbBrackets.get(0);
            bracket.setPhases(getBracketPhases(bracket.getId()));
            return bracket;
        } else {
            throw new GenericNotFoundException(Bracket.class.getSimpleName());
        }
    }

    public Map<String, List<Match>> getBracketPhases(int bracketId) {
        String query = "SELECT * FROM tournament_match WHERE bracket_id = ?";
        List<Match> dbMatches = jdbcTemplate.query(query, MatchRepository.matchMapper, bracketId);
        Map<String, List<Match>> phases = new HashMap<>();

        if (!dbMatches.isEmpty()) {
            phases = dbMatches
                    .stream()
                    .collect(Collectors.groupingBy(Match::getBracketPhase));
        }

        return phases;
    }
}
