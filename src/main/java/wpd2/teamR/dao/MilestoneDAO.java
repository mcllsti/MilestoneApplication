package wpd2.teamR.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.teamR.models.Milestone;
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
    public Milestone getProjectById(int id) throws SQLException {

        final String GET_PROJECT = "SELECT * FROM milestones WHERE id=?";

        try (PreparedStatement ps = connection.prepareStatement(GET_PROJECT)) {

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
                        rs.getString("desc"),
                        rs.getTimestamp("dateCreated"),
                        rs.getTimestamp("dateMod"),
                        rs.getTimestamp("dateDue"),
                        rs.getTimestamp("dueComplete")
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
     * @param userId    ID of user
     * @return List of all milestones in a project
     * @throws SQLException
     */
    public List<Milestone> getAllMilestones(int projectId, int userId) throws SQLException {

        final String GET_MILESTONES = "SELECT milestones.* FROM milestones JOIN projects ON milestones.projectID = projects.id WHERE projects.userID =? AND milestones.projectID =?";

        try (PreparedStatement ps = connection.prepareStatement(GET_MILESTONES)) {

            //Pass ID's into statement
            ps.setInt(userId, projectId);

            ResultSet result = ps.executeQuery();

            List<Milestone> allMilestones = new ArrayList<Milestone>();
            while (result.next()) {
                allMilestones.add(
                        new Milestone(
                                result.getInt("id"),
                                result.getString("name"),
                                result.getString("desc"),
                                result.getTimestamp("dateCreated"),
                                result.getTimestamp("dateMod"),
                                result.getTimestamp("dateDue"),
                                result.getTimestamp("dueComplete")
                        )
                );
            }
            return allMilestones;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}



