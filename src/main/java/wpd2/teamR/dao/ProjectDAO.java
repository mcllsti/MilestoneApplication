package wpd2.teamR.dao;

import lombok.NonNull;
import net.sf.resultsetmapper.ReflectionResultSetMapper;
import net.sf.resultsetmapper.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.teamR.models.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO extends DAOBase {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectDAO.class);


    public ProjectDAO(){
        // CALL THE DAO BASE TO INITIALISE THE DB CONNCTION
        super();

    }

    public Project getProjectById(int id) throws SQLException
    {

        final String GET_PROJECT = "SELECT * FROM projects WHERE id=?";

        try (PreparedStatement ps = connection.prepareStatement(GET_PROJECT)) {

            // PASS THROUGH THE id INTO THE PREPARED STATEMENT
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            // LOOP THROUGH RESULTS - SHOULD ONLY BE ONE
            Project project = null;
            while (rs.next()) {
                project = new Project(rs.getInt("id"),rs.getString("name")
                        ,rs.getString("description"),rs.getTimestamp("dateCreated"),rs.getTimestamp("dateModified"));
            }

            return project;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } }


    public List<Project> getAllProjects() throws SQLException
    {

        final String GET_PROJECTS = "SELECT * FROM projects";

        try (PreparedStatement ps = connection.prepareStatement(GET_PROJECTS)) {

            ResultSet rs = ps.executeQuery();

            // LOOP THROUGH RESULTS
            List<Project> allProjects = new ArrayList<Project>();
            while (rs.next()) {
                allProjects.add(new Project(rs.getInt("id"),rs.getString("name")
                        ,rs.getString("description"),rs.getTimestamp("dateCreated"),rs.getTimestamp("dateModified")));
            }

            return allProjects;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } }


    public List<Project> getProjectsbyUserId(String email) throws SQLException
    {

        final String GET_USERS_PROJECTS = "SELECT * FROM projects WHERE userID = (SELECT id FROM users WHERE email = ?)";

        try (PreparedStatement ps = connection.prepareStatement(GET_USERS_PROJECTS)) {

            // PASS THROUGH THE EMAIL INTO THE PREPARED STATEMENT
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            // LOOP THROUGH RESULTS
            List<Project> usersProjects = new ArrayList<Project>();
            while (rs.next()) {
                usersProjects.add(new Project(rs.getInt("id"),rs.getString("name"),
                        rs.getString("description"),rs.getTimestamp("dateCreated"),rs.getTimestamp("dateModified")));
            }

            return usersProjects;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } }
}


//    public Project findByUserId(String email){
//        String query ="SELECT * FROM projects WHERE userID = (SELECT id FROM users WHERE email = \"?\");"
//    }
//    INSERT INTO projects (name, description, dateCreated, dateModified, userID) VALUES('DUMMY FROM CLI','DUMMY FROM CLI',NOW(),NOW(),(SELECT id FROM users WHERE email = 'chris@chrisconnor.co.uk'));



/*
    public ProjectDAO(ConnectionSupplier connectionSupplier) {

//        super(connectionSupplier.provide());
//        super(connectionSupplier.provide());
        try {
            initTable(getConnection());
        } catch (Exception e) {
            LOG.error("Can't find database driver: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void initTable(Connection conn) throws SQLException {
        execute(conn, "CREATE TABLE IF NOT EXISTS users (name VARCHAR(255) PRIMARY KEY, hash VARCHAR(255))");
    }*/


//    public synchronized boolean login(@NonNull final String userName, @NonNull final String password) {
//        errIfClosed();
//        try {
//            return loginSQL(userName, password);
//        } catch (SQLException e) {
////            LOG.error("Can't login " + userName + ": " + e.getMessage());
//            return false;
//        }
//    }

//    public synchronized boolean register(@NonNull final String userName, @NonNull final String password) {
//        errIfClosed();
//        try {
//            return registerSQL(userName, password);
//        } catch (SQLException e) {
//            LOG.error("Can't register " + userName + ": " + e.getMessage());
//            return false;
//        }
//    }

//    public boolean isRegistered(String userName) {
//        try (PreparedStatement ps = getConnection().prepareStatement("SELECT 1 FROM users WHERE name = ?")) {
//            ps.setString(1, userName);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return true;
//            }
//            return false;
//        } catch (SQLException e) {
//            return false;
//        }
//    }
//
//    private boolean loginSQL(String userName, String password) throws SQLException {
//        try (PreparedStatement ps = getConnection().prepareStatement("SELECT hash FROM users WHERE name = ?")) {
//            ps.setString(1, userName);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                String hash = rs.getString("hash");
//                return hash != null && validate(password, hash);
//            }
//        }
//        return false;
//    }
//
//    private boolean registerSQL(String userName, String password) throws SQLException {
//        String hash = hash(password);
//        if (hash == null) {
//            return false;
//        }
//        if (hasUserSQL(userName)) {
//            return false;
//        }
//        String query = "INSERT into users (name, hash) VALUES(?,?)";
//        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
//            ps.setString(1, userName);
//            ps.setString(2, hash);
//            int count = ps.executeUpdate();
//            LOG.debug("insert count = " + count);
//            return count == 1;
//        }
//    }
//
//
//    private boolean hasUserSQL(String userName) throws SQLException {
//        try (PreparedStatement ps = getConnection().prepareStatement("SELECT name FROM users WHERE name = ?")) {
//            ps.setString(1, userName);
//            ResultSet rs = ps.executeQuery();
//            return rs.next();
//        }
//    }
//
//    private boolean validate(String password, String hash) {
//        try {
//            return Password.validatePassword(password, hash);
//        } catch (Password.PasswordException e) {
//            LOG.error("Can't validate password: " + e.getMessage());
//            return false;
//        }
//    }
//
//    private String hash(String password) {
//        try {
//            return Password.createHash(password);
//        } catch (Password.PasswordException e) {
//            LOG.error("Can't hash password <" + password + ">: " + e.getMessage());
//            return null;
//        }
//    }

