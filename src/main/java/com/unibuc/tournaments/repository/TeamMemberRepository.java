package com.unibuc.tournaments.repository;

import com.unibuc.tournaments.exception.GenericNotCreatedException;
import com.unibuc.tournaments.exception.GenericNotFoundException;
import com.unibuc.tournaments.model.team.Team;
import com.unibuc.tournaments.model.team.TeamMember;
import com.unibuc.tournaments.model.team.TeamMemberCategory;
import com.unibuc.tournaments.model.team.TeamMemberType;
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
                    resultSet.getString("name")));

    public TeamMemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<TeamMember> createTeamMember(TeamMember teamMember) {
        Boolean teamExists = jdbcTemplate.queryForObject("select exists(select id from team where id = ?)", Boolean.class, teamMember.getTeamId());
        if (teamExists != null && !teamExists) {
            throw new GenericNotFoundException(Team.class.getSimpleName());
        }

        String query = "INSERT INTO team_member VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, teamMember.getType().toString());
            preparedStatement.setInt(3, teamMember.getTeamId());
            preparedStatement.setString(4, teamMember.getFirstName());
            preparedStatement.setString(5, teamMember.getLastName());
            preparedStatement.setString(6, teamMember.getNickName());
            preparedStatement.setDate(7, (Date) teamMember.getDateOfBirth());
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

    public List<TeamMember> getTeamMembersFiltered(Integer teamId, String type) {
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

        for (TeamMember member : teamMembers) {
            member.setCategories(getTeamMemberCategories(member.getId()));
        }

        return teamMembers;
    }

    public Optional<TeamMember> createTeamMemberCategory(TeamMemberCategory category) {
        Boolean memberExists = jdbcTemplate.queryForObject("select exists(select id from team_member where id = ?)", Boolean.class, category.getMemberId());
        if (memberExists != null && !memberExists) {
            throw new GenericNotFoundException(TeamMember.class.getSimpleName());
        }

        Boolean categoryExists = jdbcTemplate.queryForObject("select exists(select id from team_member_category where member_id = ? AND name = ?)",
                Boolean.class,
                category.getMemberId(),
                category.getName());
        if (categoryExists != null && categoryExists) {
            throw new GenericNotCreatedException(TeamMemberCategory.class.getSimpleName());
        }

        String query = "INSERT INTO team_member_category VALUES(?, ?, ?)";
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setInt(2, category.getMemberId());
            preparedStatement.setString(3, category.getName());
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        return getTeamMember(category.getMemberId());
    }

    private List<TeamMemberCategory> getTeamMemberCategories(int teamMemberId) {
        String query = "SELECT * FROM team_member_category WHERE member_id = ?";
        return jdbcTemplate.query(query, teamMemberCategoryMapper, teamMemberId);
    }
}
