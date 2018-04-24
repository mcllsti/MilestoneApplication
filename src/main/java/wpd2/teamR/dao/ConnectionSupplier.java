package wpd2.teamR.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSupplier {
    String myDriver = "org.gjt.mm.mysql.Driver";
    public final String dbURL = "jdbc:mysql://localhost:3306/milestones";
    String username = "root";
    String password = "";


    public Connection provide() {
        try {
            // the driver class must be loaded
            // so that DriverManager can find the loaded class
            Class.forName(myDriver);
            return DriverManager.getConnection(dbURL, username, password);
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

