package hu.elte.progtech.jdbc.mysql.examples.macilaci.persistence.dao;

import hu.elte.progtech.jdbc.mysql.examples.macilaci.model.score.ScoreBoard;
import hu.elte.progtech.jdbc.mysql.examples.macilaci.persistence.connection.DataSource;
import hu.elte.progtech.jdbc.mysql.examples.macilaci.persistence.entity.HighScore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HighScoreDao implements IEntity<HighScore> {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM macilaci";
    private static final String INSERT_QUERY = "INSERT INTO macilaci (name, point) VALUES (?, ?)";
    private static final String TOP_HIGH_SCORE_QUERY = "SELECT point FROM macilaci ORDER BY point DESC LIMIT 10";

    private static final String ATTR_NAME_ID = "id";
    private static final String ATTR_NAME_NAME = "name";
    private static final String ATTR_NAME_SCORE = "point";

    @Override
    public List<HighScore> getAll() throws SQLException {
        try(Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {

            final List<HighScore> entities = new ArrayList<>();
            while(resultSet.next()) {
                final HighScore entity = new HighScore();
                entity.setId(resultSet.getLong(ATTR_NAME_ID));
                entity.setName(resultSet.getString(ATTR_NAME_NAME));
                entity.setScore(resultSet.getInt(ATTR_NAME_SCORE));
                entities.add(entity);
            }
            return entities;
        }
    }

    @Override
    public void add(HighScore entity) throws SQLException {
        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getScore());

            preparedStatement.executeUpdate();
        }
    }

    /*
    * legtöbb pont
    * @return int a legjobb score visszaadása
    * */

    public int getTopScore() throws SQLException {
        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(TOP_HIGH_SCORE_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            if(resultSet.next()) {
                return resultSet.getInt(ATTR_NAME_SCORE);
            }
        }
        return 0;
    }

    /*
    * top 10 játékos visszaadása egy listában
    * @return top10Scores legjobb 10 játékos egy listában
    * */

    public List<String> getTop10Scores() {
        ScoreBoard scoreBoard = new ScoreBoard();
        final String TOP_10_QUERY = "SELECT name, point FROM macilaci ORDER BY point DESC LIMIT 10";

        List<String> top10Scores = new ArrayList<>();

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(TOP_10_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int score = resultSet.getInt("point");
                top10Scores.add(String.format("%s - %d", name, score));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            top10Scores.add("Error fetching top scores!");
        }

        return(top10Scores);
    }

}
