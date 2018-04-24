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

    /**
     * Check if the user is a valid user.
     * @param email Address of user to check
     * @param password Plain text password of user to check against Hash
     * @return Email address if a valid user and valid password
     * @throws SQLException
     */
    public String checkIsValidUser(String email, String password) throws SQLException
    {

        final String CHECK_USER = "SELECT email, password FROM users WHERE email=?";
        String result = "";

        try (PreparedStatement ps = connection.prepareStatement(CHECK_USER)) {

            // PASS THROUGH THE EMAIL INTO THE PREPARED STATEMENT
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            // LOOP THROUGH RESULTS - SHOULD ONLY BE ONE
            while (rs.next()) {
                result = rs.getString(2);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // CHECK IF HASHES MATCHED - IF SO RETURN EMAIL
        if(Password.validatePassword(password,result)){
            return email;
        } else {

            // OTHERWISE RETURN BLANK
            return "";
        }

    }

//    public Project findByUserId(String email){
//        String query ="SELECT * FROM projects WHERE userID = (SELECT id FROM users WHERE email = \"?\");"
//    }
//    INSERT INTO projects (name, description, dateCreated, dateModified, userID) VALUES('DUMMY FROM CLI','DUMMY FROM CLI',NOW(),NOW(),(SELECT id FROM users WHERE email = 'chris@chrisconnor.co.uk'));

    /**
     * Save user into DB
     * @param user User to be saved into the Database
     * @return true if registered successfully
     */
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

    }





}
