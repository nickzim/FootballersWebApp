package Services;

import DB.DataBaseConnection;
import Model.Club;
import org.apache.log4j.Logger;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClubsServiceImpl implements ClubsService {

    private static final Logger log = Logger.getLogger(ClubsServiceImpl.class);

    @Override
    public ArrayList<Club> getAllClubs() {

        ArrayList<Club> list = new ArrayList<Club>();
        DataBaseConnection dbConnection = DataBaseConnection.getInstance();

        try {

            String query = "SELECT * FROM clubs";

            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()){
                list.add(new Club(resultSet.getString("name"),resultSet.getInt("id")));
            }

        } catch (SQLException e) {
            log.error("Error request for get all clubs list " + e.getMessage());
            return list;
        } finally {
            dbConnection.closeConnection();
        }

        return list;
    }

    @Override
    public boolean addClub(String name) {
        DataBaseConnection dbConnection = DataBaseConnection.getInstance();

        try {

            String query = "INSERT INTO clubs (name) VALUES (?)";

            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
            statement.setString(1,name);
            statement.executeUpdate();

        } catch (SQLException e){
            log.error("Error adding club " + e.getMessage());
            return false;
        } finally {
            dbConnection.closeConnection();
        }

        log.info("Request to add club success");
        return true;
    }

    @Override
    public boolean deleteClub(String name) {
        DataBaseConnection dbConnection = DataBaseConnection.getInstance();

        try {
            String query = "DELETE FROM clubs WHERE name = (?)";

            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
            statement.setString(1,name);
            statement.executeUpdate();

        } catch (SQLException e){
            log.error("Error deleting club " + e.getMessage());
            return false;
        } finally {
            dbConnection.closeConnection();
        }

        log.info("Request to delete club success");
        return true;
    }
}
