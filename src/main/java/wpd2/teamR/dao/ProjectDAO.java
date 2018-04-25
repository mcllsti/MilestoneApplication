package wpd2.teamR.dao;

import lombok.NonNull;
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
    public List<Project> getProjectsbyUser(String email) throws SQLException
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

    /**
     * Deletes a project from the database with the matching id
     * @param id of project to be deleted
     * @return boolean of sucessfull or not
     */
    public boolean deleteProjectById(int id){

        String query = "DELETE FROM projects WHERE id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(query)) {

            //PASS ID TO PREPARED STATEMENT
            ps.setInt(1, id);

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


