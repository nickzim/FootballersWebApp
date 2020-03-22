package Services;

import DB.DataBaseConnection;
import Model.Player;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayersServiceImpl implements PlayersService {

    private static final Logger log = Logger.getLogger(PlayersServiceImpl.class);

    public ArrayList<Player> getClubPlayersList(String club) {
        DataBaseConnection dbConnection = DataBaseConnection.getInstance();
        ArrayList<Player> list = new ArrayList<Player>();

        try {
            String query = "SELECT footballers.*, positions.name AS posname FROM footballers LEFT JOIN positions ON footballers.position_id = positions.id " +
                    "                                                     LEFT JOIN clubs ON footballers.club_id = clubs.id WHERE clubs.name = (?);";
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);

            statement.setString(1,club);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Player(resultSet.getString("playername"),
                                    resultSet.getInt("age"),
                                    resultSet.getString("country"),
                                    resultSet.getString("posname"),
                                    resultSet.getInt("club_id")));
            }
        } catch (SQLException e) {
            log.error("Error request for get players list " + e.getMessage());
        } finally {
            dbConnection.closeConnection();
        }

        return list;
    }

    @Override
    public boolean addPlayer(String name, int age, String country, String position, String club) {
        DataBaseConnection dbConnection = DataBaseConnection.getInstance();

        try {
            int position_id = 0, club_id = 0;
            ResultSet resultSet;
            PreparedStatement statement;

            String query = "SELECT clubs.id AS club_id, positions.id AS position_id FROM clubs, positions WHERE clubs.name = (?) AND positions.name = (?);";
            statement = dbConnection.getConnection().prepareStatement(query);
            statement.setString(1,club);
            statement.setString(2,position);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                position_id = resultSet.getInt("position_id");
                club_id = resultSet.getInt("club_id");
            }

            if (position_id == 0 || club_id == 0){
                log.error("Error getting id club or id position");
                return false;
            }

            query = "INSERT INTO footballers VALUES ( (?), (?), (?), (?), (?))";
            statement = dbConnection.getConnection().prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2,age);
            statement.setString(3,country);
            statement.setInt(4,position_id);
            statement.setInt(5,club_id);

            statement.executeUpdate();

        } catch (SQLException e) {
            log.error("Error adding player " + e.getMessage());
            return false;
        } finally {
            dbConnection.closeConnection();
        }

        log.info("Request to add player success");
        return true;
    }

    @Override
    public boolean deletePlayer(String name) {
        DataBaseConnection dbConnection = DataBaseConnection.getInstance();

        try {
            String query = "DELETE FROM footballers WHERE playername = (?)";

            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
            statement.setString(1,name);
            statement.executeUpdate();

        } catch (SQLException e){
            log.error("Error deleting player " + e.getMessage());
            return false;
        } finally {
            dbConnection.closeConnection();
        }

        log.info("Request to delete player success");
        return true;
    }

}
