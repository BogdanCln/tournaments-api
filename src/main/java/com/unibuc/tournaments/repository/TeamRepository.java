package com.unibuc.tournaments.repository;

import com.unibuc.tournaments.model.team.Team;
import com.unibuc.tournaments.model.team.TeamMember;
import com.unibuc.tournaments.model.team.TeamMemberType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.*;

@Repository
public class TeamRepository {
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Team> teamMapper = ((resultSet, i) ->
            new Team(resultSet.getInt("id"),
                    resultSet.getInt("game_id"),
                    resultSet.getString("name")));

    private RowMapper<TeamMember> teamMemberMapper = ((resultSet, i) ->
            new TeamMember(resultSet.getInt("id"),
                    resultSet.getInt("team_id"),
                    TeamMemberType.valueOf(resultSet.getString("type")),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("nick_name"),
                    resultSet.getDate("dob")));

    public TeamRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Team> createTeam(Team team) {
        String query = "INSERT INTO team VALUES(?, ?, ?)";
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setObject(2, team.getGameId());
            preparedStatement.setObject(3, team.getName());
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        return getTeam(Objects.requireNonNull(generatedKeyHolder.getKey()).intValue());
    }

    public Optional<Team> getTeam(int id) {
        String query = "SELECT * FROM team WHERE id = ?";
        List<Team> dbTeams = jdbcTemplate.query(query, teamMapper, id);

        if (!dbTeams.isEmpty()) {
            Team dbTeam = dbTeams.get(0);
            dbTeam.setMembers(getTeamMembers(dbTeam.getId()));
            return Optional.of(dbTeam);
        } else {
            return Optional.empty();
        }
    }

    private List<TeamMember> getTeamMembers(int teamId) {
        String query = "SELECT * FROM team_member WHERE team_id = ?";
        return jdbcTemplate.query(query, teamMemberMapper, teamId);
    }

    public List<Team> getTeamBy(Integer gameId, String name) {
        String query;
        List<Team> teams;
        if (gameId != null && name != null) {
            query = "SELECT * FROM team WHERE game_id = ? AND name = ?";
            teams = jdbcTemplate.query(query, teamMapper, gameId, name);
        } else if (gameId != null) {
            query = "SELECT * FROM team WHERE game_id = ?";
            teams = jdbcTemplate.query(query, teamMapper, gameId);
        } else if (name != null) {
            query = "SELECT * FROM team WHERE name = ?";
            teams = jdbcTemplate.query(query, teamMapper, name);
        } else {
            query = "SELECT * FROM team";
            teams = jdbcTemplate.query(query, teamMapper);
        }

        return teams;
    }
}
