package com.unibuc.tournaments.repository;

import com.unibuc.tournaments.model.game.Game;
import com.unibuc.tournaments.model.game.GameGenre;
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
public class GameRepository {
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Game> gameMapper = ((resultSet, i) ->
            new Game(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("publisher_name"),
                    GameGenre.valueOf(resultSet.getString("genre"))));

    public Optional<Game> createGame(Game game) {
        String query = "INSERT INTO game VALUES(?, ?, ?, ?)";
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setObject(2, game.getName());
            preparedStatement.setObject(3, game.getPublisherName());
            preparedStatement.setObject(4, game.getGenre().toString());
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);

        return getGame(Objects.requireNonNull(generatedKeyHolder.getKey()).intValue());
    }

    public Optional<Game> getGame(int id) {
        String query = "SELECT * FROM game WHERE id = ?";
        List<Game> games = jdbcTemplate.query(query, gameMapper, id);

        if (!games.isEmpty()) {
            return Optional.of(games.get(0));
        } else {
            return Optional.empty();
        }
    }

    public List<Game> getGamesBy(String genre, String publisherName) {
        String query;
        List<Game> games;
        if (genre != null && publisherName != null) {
            query = "SELECT * FROM game WHERE genre = ? AND publisher_name = ?";
            games = jdbcTemplate.query(query, gameMapper, genre, publisherName);
        } else if (genre != null) {
            query = "SELECT * FROM game WHERE genre = ?";
            games = jdbcTemplate.query(query, gameMapper, genre);
        } else if (publisherName != null) {
            query = "SELECT * FROM game WHERE publisher_name = ?";
            games = jdbcTemplate.query(query, gameMapper, publisherName);
        } else {
            query = "SELECT * FROM game";
            games = jdbcTemplate.query(query, gameMapper);
        }
        return games;
    }

    public GameRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
