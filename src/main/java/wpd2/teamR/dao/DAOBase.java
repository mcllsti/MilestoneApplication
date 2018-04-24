package wpd2.teamR.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOBase implements AutoCloseable {

    private Connection connection;


    public DAOBase() {
        ConnectionSupplier cs = new ConnectionSupplier();
        this.connection = cs.provide();
    }

    public synchronized void close()throws SQLException {

        if (connection != null) {
            connection.close();
            connection = null;
        }

    }

    public List<Object> getAllUsers() throws SQLException {

        final String LIST_PERSONS = "SELECT email FROM users";
        List<Object> results = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(LIST_PERSONS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return results;

    }

    protected Connection getConnection() {

        return connection;

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
