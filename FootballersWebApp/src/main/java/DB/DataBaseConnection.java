package DB;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static DataBaseConnection ourInstance;

    private Connection connection;

    private final Logger log = Logger.getLogger(DataBaseConnection.class);

    private final String URL = "jdbc:postgresql://localhost:5432/footballersDB";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "root";

    public static DataBaseConnection getInstance() {
        if (ourInstance == null) {
            ourInstance = new DataBaseConnection();
        }

        return ourInstance;
    }

    private DataBaseConnection() {

    }

    public Connection getConnection(){

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            log.error("Error opening connection DB " + e.getMessage());
        }

        return connection;
    }

    public void closeConnection(){

        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Error closing connection DB " + e.getMessage());
        }
    }
}
