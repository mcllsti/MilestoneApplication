package wpd2.teamR.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.teamR.models.User;
import wpd2.teamR.util.Password;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO extends DAOBase {

    private Connection connection;
    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);


    public UserDAO(){
        ConnectionSupplier cs = new ConnectionSupplier();
        this.connection = cs.provide();
    }

    // TODO: create user
    public boolean registerUser(User user){

        String query = "INSERT INTO users (fname, lname, email, password,dateCreated) VALUES(?, ?, ?, ?, NOW())";

        try (PreparedStatement ps = getConnection().prepareStatement(query)) {

            ps.setString(1, user.getFname());
            ps.setString(2, user.getLname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            int count = ps.executeUpdate();
            LOG.debug("insert count = " + count);

            if(count==1){

                return true;

            } else {

                return false;

            }

        } catch(SQLException error){
            LOG.debug(error.toString());
            return false;
        }

//        return true;
//

    }

    // TODO: check user

    //

}
