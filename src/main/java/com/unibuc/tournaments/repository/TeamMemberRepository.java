package com.unibuc.tournaments.repository;

import com.unibuc.tournaments.model.team.TeamMember;
import com.unibuc.tournaments.model.team.TeamMemberCategory;
import com.unibuc.tournaments.model.team.TeamMemberType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TeamMemberRepository {
    private JdbcTemplate jdbcTemplate;

    private RowMapper<TeamMember> teamMemberMapper = ((resultSet, i) ->
            new TeamMember(resultSet.getInt("id"),
                    resultSet.getInt("team_id"),
                    TeamMemberType.valueOf(resultSet.getString("type")),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("nick_name"),
                    resultSet.getDate("dob")));

    private RowMapper<TeamMemberCategory> teamMemberCategoryMapper = ((resultSet, i) ->
            new TeamMemberCategory(resultSet.getInt("id"),
                    resultSet.getInt("member_id"),
                    resultSet.getString("category_name")));

    public TeamMemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<TeamMember> createTeamMember(TeamMember teamMember) {
        String query = "INSERT INTO team_member VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setObject(2, teamMember.getTeamId());
            preparedStatement.setObject(3, teamMember.getType());
            preparedStatement.setObject(4, teamMember.getFirstName());
            preparedStatement.setObject(5, teamMember.getLastName());
            preparedStatement.setObject(6, teamMember.getNickName());
            preparedStatement.setObject(7, teamMember.getDateOfBirth());
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        return getTeamMember(Objects.requireNonNull(generatedKeyHolder.getKey()).intValue());
    }

    public Optional<TeamMember> getTeamMember(int id) {
        String query = "SELECT * FROM team_member WHERE id = ?";
        List<TeamMember> dbTeamMembers = jdbcTemplate.query(query, teamMemberMapper, id);

        if (!dbTeamMembers.isEmpty()) {
            TeamMember dbTeamMember = dbTeamMembers.get(0);
            dbTeamMember.setCategories(getTeamMemberCategories(dbTeamMember.getId()));
            return Optional.of(dbTeamMember);
        } else {
            return Optional.empty();
        }
    }

    private List<TeamMemberCategory> getTeamMemberCategories(int teamMemberId) {
        String query = "SELECT * FROM team_member_category WHERE member_id = ?";
        return jdbcTemplate.query(query, teamMemberCategoryMapper, teamMemberId);
    }

    public List<TeamMember> getTeamMembersBy(Integer teamId, String type) {
        String query;
        List<TeamMember> teamMembers;
        if (teamId != null && type != null) {
            query = "SELECT * FROM team_member WHERE team_id = ? AND type = ?";
            teamMembers = jdbcTemplate.query(query, teamMemberMapper, teamId, type);
        } else if (teamId != null) {
            query = "SELECT * FROM team_member WHERE team_id = ?";
            teamMembers = jdbcTemplate.query(query, teamMemberMapper, teamId);
        } else if (type != null) {
            query = "SELECT * FROM team_member WHERE type = ?";
            teamMembers = jdbcTemplate.query(query, teamMemberMapper, type);
        } else {
            query = "SELECT * FROM team_member";
            teamMembers = jdbcTemplate.query(query, teamMemberMapper);
        }

        return teamMembers;
    }
}
