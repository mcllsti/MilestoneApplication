package wpd2.teamR.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.teamR.models.Milestone;
import wpd2.teamR.models.Project;
import wpd2.teamR.util.Password;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class MilestoneDAO extends DAOBase {

    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);

    public MilestoneDAO() {
        // CALL THE DAO BASE TO INITIALISE THE DB CONNECTION
        super();
    }

    /**
     * Gets a single milestone with the specified id
     *
     * @param id of milestone to get
     * @return Milestone object of the received project
     * @throws SQLException
     */

    public Milestone getMilestonesById(int id) throws SQLException {

        final String GET_MILESTONE = "SELECT * FROM milestones WHERE id=?";

        try (PreparedStatement ps = connection.prepareStatement(GET_MILESTONE)) {

            // PASS THROUGH THE id INTO THE PREPARED STATEMENT
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            // LOOP THROUGH RESULTS - SHOULD ONLY BE ONE
            Milestone milestone = null;
            while (rs.next()) {
                //ADD NEW PROJECT WITH CURRENT RESULTSET DETAILS

                milestone = new Milestone(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("dateCreated"),
                        rs.getTimestamp("dateModified"),
                        rs.getTimestamp("dueDate"),
                        rs.getTimestamp("dateCompleted"),
                        rs.getInt("projectID")
                );
            }

            return milestone;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Milestone getMilestoneByIdandUser(int id, String email) {
        final String GET_MILESTONE = "SELECT milestones.* FROM milestones JOIN projects ON milestones.projectID = projects.id WHERE milestones.id = ? AND projects.userID = (SELECT id FROM users WHERE email = ? LIMIT 1 )";

        try (PreparedStatement ps = connection.prepareStatement(GET_MILESTONE)) {

            // PASS THROUGH THE id INTO THE PREPARED STATEMENT
            ps.setInt(1, id);
            ps.setString(2,email);

            ResultSet rs = ps.executeQuery();

            // LOOP THROUGH RESULTS - SHOULD ONLY BE ONE
            Milestone milestone = null;
            while (rs.next()) {
                //ADD NEW PROJECT WITH CURRENT RESULTSET DETAILS

                milestone = new Milestone(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("dateCreated"),
                        rs.getTimestamp("dateModified"),
                        rs.getTimestamp("dueDate"),
                        rs.getTimestamp("dateCompleted"),
                        rs.getInt("projectID")
                );
            }

            return milestone;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * Get all milestones in a project
     *
     * @param projectId ID of project containing milestones
     * @param email    ID of user
     * @return List of all milestones in a project
     * @throws SQLException
     */

    public List<Milestone> getAllMilestonesByProjectAndUser(int projectId, String email) throws SQLException {

        final String GET_MILESTONES = "SELECT milestones.* FROM milestones JOIN projects ON milestones.projectID = projects.id WHERE projects.userID =? AND milestones.projectID =?";

        try (PreparedStatement ps = connection.prepareStatement(GET_MILESTONES)) {

            //Pass ID's into statement
            ps.setString(1, email);
            ps.setInt(2, projectId);

            ResultSet result = ps.executeQuery();

            List<Milestone> allMilestones = new ArrayList<Milestone>();
            while (result.next()) {
                allMilestones.add(
                        new Milestone(
                                result.getInt("id"),
                                result.getString("name"),
                                result.getString("description"),
                                result.getTimestamp("dateCreated"),
                                result.getTimestamp("dateModified"),
                                result.getTimestamp("dueDate"),
                                result.getTimestamp("dateCompleted"),
                                result.getInt("projectID")

                        )
                );
            }
            return allMilestones;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Milestone> getGuestMilestones(String email, String urlHash) throws SQLException {

        final String GET_MILESTONES = "SELECT milestones.* from milestones JOIN projects ON projects.id = milestones.projectID WHERE milestones.projectID = (SELECT projectID FROM links WHERE urlHash = ? AND email = ? LIMIT 1);";

        try (PreparedStatement ps = connection.prepareStatement(GET_MILESTONES)) {

            //Pass ID's into statement
            ps.setString(1, urlHash);
            ps.setString(2, email);

            ResultSet result = ps.executeQuery();

            List<Milestone> allMilestones = new ArrayList<Milestone>();
            while (result.next()) {
                allMilestones.add(
                        new Milestone(
                                result.getInt("id"),
                                result.getString("name"),
                                result.getString("description"),
                                result.getTimestamp("dateCreated"),
                                result.getTimestamp("dateModified"),
                                result.getTimestamp("dueDate"),
                                result.getTimestamp("dateCompleted"),
                                result.getInt("projectID")

                        )
                );
            }
            return allMilestones;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Method to try and get milestones of a project.
    //This is one I wrote myself, not Gavin's, think the mySQL query isnt correct

    public List<Milestone> getAllMilestonesByProjectId(int id) throws SQLException{

        final String GET_MILESTONES = "SELECT * FROM milestones WHERE projectID = ? ORDER BY dueDate ASC";

        try (PreparedStatement ps = connection.prepareStatement(GET_MILESTONES)) {

            //Pass ID's into statement
            ps.setInt(1, id);

            ResultSet result = ps.executeQuery();

            List<Milestone> allMilestones = new ArrayList<Milestone>();
            while (result.next()) {
                allMilestones.add(
                        new Milestone(
                                result.getInt("id"),
                                result.getString("name"),
                                result.getString("description"),
                                result.getTimestamp("dateCreated"),
                                result.getTimestamp("dateModified"),
                                result.getTimestamp("dueDate"),
                                result.getTimestamp("dateCompleted"),
                                result.getInt("projectID")
                        )
                );
            }

            return allMilestones;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * @param milestone object with details to be written
     * @param projectId     the project to add the milestone to
     * @return boolean determining success or failure
     * @throws SQLException
     */
    public boolean createMilestone(Milestone milestone, int projectId) {

        String CREATE_MILESTONE = "INSERT INTO milestones (name, description, dateCreated, dateModified, dueDate, dateCompleted, projectID) VALUES (?,?,NOW(),NOW(),?,?,?)";

        try (PreparedStatement ps = getConnection().prepareStatement(CREATE_MILESTONE)) {

            ps.setString(1, milestone.getName());
            ps.setString(2, milestone.getDescription());
            ps.setTimestamp(3, milestone.getDueDate());
            ps.setTimestamp(4, milestone.getDateCompleted());
            ps.setInt(5, projectId);
            int count = ps.executeUpdate();
            LOG.debug("insert count = " + count);

            //Return true or false
            return determineTrueFalse(count);


        } catch (SQLException e) {
            LOG.debug(e.toString());
            return false;
        }

    }


    /**
     *
     * @param id of milestone to delete
     * @return boolean determining success or failure
     */
    public boolean deleteMilestoneById(int id) {

        String DELETE_MILESTONE = "DELETE FROM milestones WHERE id = ?";

        try(PreparedStatement ps = getConnection().prepareStatement(DELETE_MILESTONE)) {

            //Pass ID into prepared statement
            ps.setInt(1, id);

            int count = ps.executeUpdate();
            LOG.debug ("insert count=" + count);

            //Return true or false
            return determineTrueFalse(count);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    /**
     * Updates a project that is stored in the database
     *
     * @param milestone object containing the updated name and description details to be written
     * @return boolean of successfull or not
     */
    public boolean updateMilestone(Milestone milestone) {

        String UPDATE_MILESTONE = "UPDATE milestones SET name=?, description=?, dateModified=NOW(),dueDate=?,dateCompleted=? WHERE id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(UPDATE_MILESTONE)) {

            //PASS VARIABLES TO PREPARED STATEMENT
            ps.setString(1, milestone.getName());
            ps.setString(2, milestone.getDescription());
            ps.setTimestamp(3, milestone.getDueDate());
            ps.setTimestamp(4, milestone.getDateCompleted());
            ps.setInt(5, milestone.getId());
            int count = ps.executeUpdate();
            LOG.debug("insert count = " + count);

            return determineTrueFalse(count);

        } catch (SQLException error) {
            LOG.debug(error.toString());
            return false;
        }

    }


    /**
     * Mark the milestone as complete
     * @param milestoneId The milestone to mark
     * @param projectId The project id of the milestone
     * @return true if successful
     */
    public boolean markAsComplete(int milestoneId, int projectId) {

        String query = "UPDATE milestones SET dateCompleted = NOW() WHERE id = ? AND projectID = ?; ";

        return this.toggleUpdate(query, milestoneId,projectId);


    }

    /**
     * Mark the milestone as incomplete
     * @param milestoneId The milestone id
     * @param projectId The project id
     * @return true if successful
     */
    public boolean markAsIncomplete(int milestoneId, int projectId) {

        String query = "UPDATE milestones SET dateCompleted = NULL WHERE id = ? AND projectID = ?; ";

        return this.toggleUpdate(query, milestoneId,projectId);

    }

    /**
     * Execute the update query depending on the query
     * @param statement The query to execute
     * @param milestoneId Milestone ID
     * @param projectId The project ID
     * @return True if success
     */
    private boolean toggleUpdate(String statement, int milestoneId, int projectId){
        try (PreparedStatement ps = getConnection().prepareStatement(statement)) {

            //PASS VARIABLES TO PREPARED STATEMENT
            ps.setInt(1, milestoneId);
            ps.setInt(2, projectId);
            int count = ps.executeUpdate();
            LOG.debug("insert count = " + count);

            return determineTrueFalse(count);

        } catch (SQLException error) {
            LOG.debug(error.toString());
            return false;
        }
    }



    /**
     * Private method that returns true of false depending on int value
     * Used to cut down repetitive code
     *
     * @param count Integer variable to be used to determine true or false
     * @return Boolean depending on count value
     */
    private boolean determineTrueFalse(int count) {
        //RETURBNS TRUE OR FALSE DEPENDING ON COUNT RESULT
        if (count == 1) {

            return true;

        } else {

            return false;

        }
    }



}



