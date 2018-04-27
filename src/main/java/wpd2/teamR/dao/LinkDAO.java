package wpd2.teamR.dao;

import com.mysql.cj.api.jdbc.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.teamR.models.Link;
import wpd2.teamR.models.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

/**
 * LinkDAO Class - Retrieves all link data from DB
 */
public class LinkDAO extends DAOBase {

    private static final Logger LOG = LoggerFactory.getLogger(LinkDAO.class);

    public LinkDAO() {
        // CALL THE DAO BASE TO INITIALISE THE DB CONNCTION
        super();

    }

    /**
     * Find Link from DB using ID
     *
     * @param id Link ID to retrieve
     * @return Link object
     * @throws SQLException
     */
    public Link findById(int id) throws SQLException {

        final String query = "SELECT * FROM links WHERE id=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            // PASS THROUGH THE id INTO THE PREPARED STATEMENT
            ps.setInt(1, id);

            // RETURN THE LINK - PRIVATE METHOD IN THIS CLASS
            return this.retrieveLink(ps);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieve links using project id
     *
     * @param projectID Project ID of links to retrieve
     * @return List of links
     * @throws SQLException
     */
    public List<Link> findByProjectId(int projectID) throws SQLException {

        final String query = "SELECT * FROM links WHERE projectId=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            // PASS THROUGH THE id INTO THE PREPARED STATEMENT
            ps.setInt(1, projectID);

            // RETURN THE LINKS
            return this.retrieveLinks(ps);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieve all links based on UserID
     *
     * @param userId ID of user from which to retrieve
     * @return List of links
     * @throws SQLException
     */
    public List<Link> findByUserId(int userId) throws SQLException {

        final String query = "SELECT links.* FROM links JOIN projects ON links.projectID = projects.id WHERE projects.userID = ?;";

        try {

            PreparedStatement ps = connection.prepareStatement(query);

            // PASS THROUGH THE id INTO THE PREPARED STATEMENT
            ps.setInt(1, userId);

            // RETRIEVE THE LINKS FROM THE DB
            return this.retrieveLinks(ps);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieve all links from data base. TODO: Might not be required
     *
     * @return List of all links in DB
     * @throws SQLException
     */
    public List<Link> findAll() throws SQLException {

        final String query = "SELECT * FROM links";

        try {

            // CREATE PREPARED STATEMENT
            PreparedStatement ps = connection.prepareStatement(query);

            // RETURN THE LINKS
            return this.retrieveLinks(ps);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Save a Link into the database
     *
     * @param link      Link object to save
     * @param projectId Id of project under which to save
     * @return true if successful
     */
    public boolean save(Link link, int projectId) throws SQLException {

        String query = "INSERT INTO links (email, dateCreated, projectID) VALUES(?,NOW(),?)";

        try (PreparedStatement ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, link.getEmail());
            ps.setInt(2, projectId);

            int count = ps.executeUpdate();
            LOG.debug("insert count = " + count);


            //RETURBNS TRUE OR FALSE DEPENDING ON COUNT RESULT
            if (count == 1) {

                // IF WE NEED IT - THIS WILL GET THE ID OF THE NEW OBJECT
//                ResultSet keys = ps.getGeneratedKeys();
//                keys.next();
//                int id = keys.getInt(1);

                return true;

            } else {

                return false;

            }

        } catch (SQLException error) {
            LOG.debug(error.toString());
            return false;
        }

    }

    /**
     * Delete a link from the data based.
     *
     * @param link Link which should be deleted (Will use the ID)
     * @return
     */
    public boolean delete(Link link) {

        String query = "DELETE FROM links WHERE id = ? LIMIT 1";

        try (PreparedStatement ps = getConnection().prepareStatement(query)) {

            //PASS ID TO PREPARED STATEMENT
            ps.setInt(1, link.getId());

            int count = ps.executeUpdate();
            LOG.debug("insert count = " + count);

            //RETURBNS TRUE OR FALSE DEPENDING ON COUNT RESULT
            if (count == 1) {

                return true;

            } else {

                return false;

            }

        } catch (SQLException error) {
            LOG.debug(error.toString());
            return false;
        }

    }

    /**
     * Delete Link from DB based on email
     *
     * @param email email address related to link to delete
     * @return True if successful
     */
    public boolean deleteByEmail(String email) {

        String query = "DELETE FROM links WHERE email = ? LIMIT 1";

        try (PreparedStatement ps = getConnection().prepareStatement(query)) {

            //PASS ID TO PREPARED STATEMENT
            ps.setString(1, email);

            int count = ps.executeUpdate();
            LOG.debug("insert count = " + count);

            //RETURBNS TRUE OR FALSE DEPENDING ON COUNT RESULT
            if (count == 1) {

                return true;

            } else {

                return false;

            }

        } catch (SQLException error) {
            LOG.debug(error.toString());
            return false;
        }

    }

//    PRIVATE FUNCTIONS ///////////////////////////

    /**
     * Execute the prepared statement in the DB and return the retreived links
     *
     * @param ps Prepared statement to execute
     * @return List of links
     * @throws SQLException
     */
    private List<Link> retrieveLinks(PreparedStatement ps) throws SQLException {

        ResultSet rs = ps.executeQuery();

        // LOOP THROUGH RESULTS - SHOULD ONLY BE ONE
        List<Link> links = new ArrayList<Link>();
        while (rs.next()) {
            //ADD NEW PROJECT WITH CURRENT RESULTSET DETAILS
            links.add(new Link(rs.getInt("id"), rs.getString("email")
                    , rs.getTimestamp("dateCreated"), rs.getTimestamp("dateLastAccessed"), rs.getInt("projectID")));
        }

        return links;

    }

    /**
     * Retrieve a link based on the executred prepared statement. Should only ever return one.
     *
     * @param ps Prepared statement to execute.
     * @return Link object
     * @throws SQLException
     */
    private Link retrieveLink(PreparedStatement ps) throws SQLException {

        ResultSet rs = ps.executeQuery();

        // LOOP THROUGH RESULTS - SHOULD ONLY BE ONE
        Link link = null;
        while (rs.next()) {
            //ADD NEW PROJECT WITH CURRENT RESULTSET DETAILS
            link = new Link(rs.getInt("id"), rs.getString("email")
                    , rs.getTimestamp("dateCreated"), rs.getTimestamp("dateLastAccessed"), rs.getInt("projectID"));
        }

        return link;

    }

    private String generateUriHash(String hashString){
        String string = "Adler32 Checksum For Byte Array";
        // Convert string to bytes
        byte bytes[] = string.getBytes();
        Checksum checksum = new Adler32();
        checksum.update(bytes, 0, bytes.length);
        long lngChecksum = checksum.getValue();
        System.out.println("Adler32 checksum for byte array :" + lngChecksum);
        return null;
    }


}


