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



//=======================================================================================
//TODO: NONE OF THEASE METHODS RETURN A PROJECT THAT HAS THE LIST OF MILESTONES POPULATED.
//TODO: METHODS IMPLEMENTED SO FAR ARE NOT EXAUSTIVE. MORE MAY BE REQUIRED OR LESS
//=======================================================================================
public class ProjectDAO extends DAOBase {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectDAO.class);



    public ProjectDAO(){
        // CALL THE DAO BASE TO INITIALISE THE DB CONNCTION
        super();

    }

    /**
     * Gets a single project with the specified id
     * @param id of project to get
     * @return Project object of the recieved project
     * @throws SQLException
     */
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
                //ADD NEW PROJECT WITH CURRENT RESULTSET DETAILS
                project = new Project(rs.getInt("id"),rs.getString("name")
                        ,rs.getString("description"),rs.getTimestamp("dateCreated"),rs.getTimestamp("dateModified"));
            }

            return project;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } }


    /**
     * Gets a list of all projects from the database
     * @return List of projects that contains all database projects
     * @throws SQLException
     */
    public List<Project> getAllProjects() throws SQLException
    {

        final String GET_PROJECTS = "SELECT * FROM projects";

        try (PreparedStatement ps = connection.prepareStatement(GET_PROJECTS)) {

            ResultSet rs = ps.executeQuery();

            // LOOP THROUGH RESULTS
            List<Project> allProjects = new ArrayList<Project>();
            while (rs.next()) {
                //ADD NEW PROJECT WITH CURRENT RESULTSET DETAILS
                allProjects.add(new Project(rs.getInt("id"),rs.getString("name")
                        ,rs.getString("description"),rs.getTimestamp("dateCreated"),rs.getTimestamp("dateModified")));
            }

            return allProjects;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } }


    /**
     * Gets all projects for a single user
     * @param email address of user to recieve projects for
     * @return List of projects that contains all of a single users projects
     * @throws SQLException
     */
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
                //ADD NEW PROJECT WITH CURRENT RESULTSET DETAILS
                usersProjects.add(new Project(rs.getInt("id"),rs.getString("name"),
                        rs.getString("description"),rs.getTimestamp("dateCreated"),rs.getTimestamp("dateModified")));
            }

            return usersProjects;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } }


    /**
     * Creates a project and stores in the database
     * @param project object containing name and description details to be written
     * @param Email address of user to asign the project to
     * @return boolean of successfull or not
     */
    public boolean createProject(Project project, String Email){

        String query = "INSERT INTO projects (name, description, dateCreated, dateModified, userID) VALUES(?,?,NOW(),NOW(),(SELECT id FROM users WHERE email = ?))";

        try (PreparedStatement ps = getConnection().prepareStatement(query)) {

            //PASS VARIABLES TO PREPARED STATEMENT
            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.setString(3, Email);
            int count = ps.executeUpdate();
            LOG.debug("insert count = " + count);

            //RETURBNS TRUE OR FALSE DEPENDING ON COUNT RESULT
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

