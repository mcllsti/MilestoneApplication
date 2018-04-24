package wpd2.teamR.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAOBase {

    public void createUser()
    {


    }

    public String checkIsValidUser(String Email) throws SQLException
    {

        final String CHECK_USER = "SELECT email FROM users WHERE email=?";
        String result = "";

        try (PreparedStatement ps = connection.prepareStatement(CHECK_USER)) {

            ps.setString(1, Email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result = rs.getString(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }}




