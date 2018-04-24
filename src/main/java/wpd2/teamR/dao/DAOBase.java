package wpd2.teamR.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOBase implements AutoCloseable{

    private Connection connection;

    DAOBase(Connection connection) {
        this.connection = connection;
    }

    @Override
    public synchronized void close()throws SQLException {

        if (connection != null) {
            connection.close();
            connection = null;
        }

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
