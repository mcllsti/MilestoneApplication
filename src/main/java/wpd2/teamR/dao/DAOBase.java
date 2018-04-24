package wpd2.teamR.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOBase {

    private Connection connection;

    DAOBase(Connection connection) {
        this.connection = connection;
    }

    public synchronized void close()throws SQLException {

        if (connection != null) {
            connection.close();
            connection = null;
        }

    }

    protected Connection getConnection() {

        return connection;

    }

    public Connection provide() {
        try {
            // the driver class must be loaded
            // so that DriverManager can find the loaded class
            Class.forName("com.mysql.jdbc.Driver");

            // CONNECTION DETAILS
            String servername = "localhost";
            int port = 3306;
            String user = "root";
            String pass = "";
            String db = "milestones";
            String connectionString = "jdbc:mysql://" + servername + ":" + port + "/" + db;

            return DriverManager.getConnection(connectionString,user, pass);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void execute(Connection connection, String cmd) {
        try {
            Statement statement = null;
            try {
                statement = connection.createStatement();
                statement.execute(cmd);
            } finally {
                if (statement != null) {
                    statement.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    protected void errIfClosed() {
        if (getConnection() == null) {
            throw new NullPointerException("MySQL Connection connection is closed");
        }
    }

}
