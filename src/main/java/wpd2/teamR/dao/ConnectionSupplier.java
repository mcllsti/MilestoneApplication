package wpd2.teamR.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSupplier {
    public static final String MEMORY = "jdbc:h2:mem:test";
    public static final String FILE = "jdbc:h2:~/test";

    private final String db;

    public ConnectionSupplier(String db) {
        this.db = db;
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
            throw new ConnectionSupplierException(e);
        }
    }

    public class ConnectionSupplierException extends RuntimeException {
        ConnectionSupplierException(Exception e) {
            super(e);
        }
    }
}

