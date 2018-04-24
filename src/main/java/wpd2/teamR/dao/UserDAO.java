package wpd2.teamR.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.teamR.models.User;
import wpd2.teamR.util.Password;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DAOBase {

    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);

    public UserDAO(){
        // CALL THE DAO BASE TO INITIALISE THE DB CONNCTION
        super();

    }

    public String checkIsValidUser(String Email, String password) throws SQLException
    {

//        String hash = Password.createHash(password);
        final String CHECK_USER = "SELECT email, password FROM users WHERE email=?";
        String result = "";

        try (PreparedStatement ps = connection.prepareStatement(CHECK_USER)) {

            ps.setString(1, Email);
//            ps.setString(2, hash);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result = rs.getString(2);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // CHECK IF HASHES MATCHED
        if(Password.validatePassword(password,result)){
            return "MATCHED";
        } else {
            return "DOES NOT MATCH";
        }

//        return result;
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



}
