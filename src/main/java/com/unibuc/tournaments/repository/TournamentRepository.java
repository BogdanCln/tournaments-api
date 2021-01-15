package com.unibuc.tournaments.repository;

import com.unibuc.tournaments.model.tournament.Tournament;
import com.unibuc.tournaments.model.tournament.TournamentStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TournamentRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<Tournament> tournamentMapper = (resultSet, i) ->
            new Tournament(
                    resultSet.getInt("id"),
                    resultSet.getInt("game_id"),
                    resultSet.getString("name"),
                    TournamentStatus.valueOf(resultSet.getString("status")),
                    resultSet.getDate("start_date"),
                    resultSet.getDate("end_date"),
                    resultSet.getString("location"),
                    resultSet.getString("teams"));

    private static final RowMapper<Tournament> tournamentMapperNoTeams = (resultSet, i) ->
            new Tournament(
                    resultSet.getInt("id"),
                    resultSet.getInt("game_id"),
                    resultSet.getString("name"),
                    TournamentStatus.valueOf(resultSet.getString("status")),
                    resultSet.getDate("start_date"),
                    resultSet.getDate("end_date"),
                    resultSet.getString("location"));

    public TournamentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Tournament> createTournament(Tournament tournament) {
        String query = "INSERT INTO tournament VALUES(?,?,?,?,?,?,?)";
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setInt(2, tournament.getGameId());
            preparedStatement.setString(3, tournament.getName());
            preparedStatement.setString(4, tournament.getStatus().toString());
            preparedStatement.setDate(5, (Date) tournament.getStartDate());
            preparedStatement.setDate(6, (Date) tournament.getEndDate());
            preparedStatement.setString(7, tournament.getLocation());
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);

        return getTournament(Objects.requireNonNull(generatedKeyHolder.getKey()).intValue());
    }

    public void deleteTournament(int tournamentId) {
        String query = "DELETE FROM tournament WHERE id = ?";
        jdbcTemplate.update(query, tournamentId);
    }

    public Optional<Tournament> getTournament(int id) {
        String query = "SELECT t.id,\n" +
                "       t.game_id,\n" +
                "       t.name,\n" +
                "       t.status,\n" +
                "       t.start_date,\n" +
                "       t.end_date,\n" +
                "       t.location,\n" +
                "       ttt.teams\n" +
                "FROM tournament t\n" +
                "         join (select t.id                     as tid,\n" +
                "                      GROUP_CONCAT(tt.team_id) as teams\n" +
                "               from tournament t\n" +
                "                        left join tournament_team tt on t.id = tt.tournament_id\n" +
                "               where t.id = ?\n" +
                "               group by t.id) ttt on t.id = ttt.tid\n" +
                "group by t.id;";
        List<Tournament> tournaments = jdbcTemplate.query(query, tournamentMapper, id);

        if (!tournaments.isEmpty()) {
            return Optional.of(tournaments.get(0));
        } else {
            return Optional.empty();
        }
    }

    public void createTournamentTeam(int id, int teamId) {
        String query = "INSERT INTO tournament_team VALUES(?, ?, ?)";
        jdbcTemplate.update(query, null, id, teamId);
    }

    public List<Tournament> getTournamentFiltered(String name, Integer gameId) {
        String query;
        List<Tournament> dbTournaments;
        if (name != null && gameId != null) {
            query = "SELECT * FROM tournament WHERE name = ? AND game_id = ?";
            dbTournaments = jdbcTemplate.query(query, tournamentMapperNoTeams, name, gameId);
        } else if (name != null) {
            query = "SELECT * FROM tournament WHERE name = ?";
            dbTournaments = jdbcTemplate.query(query, tournamentMapperNoTeams, name);
        } else if (gameId != null) {
            query = "SELECT * FROM tournament WHERE game_id = ?";
            dbTournaments = jdbcTemplate.query(query, tournamentMapperNoTeams, gameId);
        } else {
            query = "SELECT * FROM tournament";
            dbTournaments = jdbcTemplate.query(query, tournamentMapperNoTeams);
        }

        List<Tournament> tournaments = new ArrayList<>();
        for (Tournament dbTournament : dbTournaments) {
            tournaments.add(getTournament(dbTournament.getId()).get());
        }

        return tournaments;
    }

    public Optional<Tournament> updateTournament(Tournament tournament) {
        String query = "UPDATE tournament SET " +
                "game_id = ?, " +
                "name = ?, " +
                "status = ?," +
                "start_date = ?," +
                "end_date = ?," +
                "location = ? WHERE id = ?";
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, tournament.getGameId());
            preparedStatement.setString(2, tournament.getName());
            preparedStatement.setString(3, tournament.getStatus().toString());
            preparedStatement.setDate(4, (Date) tournament.getStartDate());
            preparedStatement.setDate(5, (Date) tournament.getEndDate());
            preparedStatement.setString(6, tournament.getLocation());
            preparedStatement.setInt(7, tournament.getId());
            return preparedStatement;
        };

        jdbcTemplate.update(preparedStatementCreator);

        return getTournament(tournament.getId());
    }
}
