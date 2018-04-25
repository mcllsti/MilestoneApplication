package wpd2.teamR.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.teamR.models.Link;
import wpd2.teamR.models.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LinkDAO extends DAOBase {

    private static final Logger LOG = LoggerFactory.getLogger(LinkDAO.class);

    public LinkDAO(){
        // CALL THE DAO BASE TO INITIALISE THE DB CONNCTION
        super();

    }

    public Link findById(int id) throws SQLException
    {

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

    public List<Link> findByProjectId(int id) throws SQLException
    {

        final String query = "SELECT * FROM links WHERE projectId=?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            // PASS THROUGH THE id INTO THE PREPARED STATEMENT
            ps.setInt(1, id);

            // RETURN THE LINKS
            return this.retrieveLinks(ps);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Link> findByUserId(int userId) throws SQLException
    {

        final String query = "SELECT links.id, links.email, links.dateCreated, links.dateLastAccessed, links.projectID FROM links, projects, users WHERE links.projectID = projects.id AND projects.userID = users.id AND users.id = ?;";

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

    public List<Link> findAll() throws SQLException
    {

        final String query = "SELECT * FROM links";

        try {

            // CREATE PREPARED STATEMENT
            PreparedStatement ps = connection.prepareStatement(query);

            // RETURN THE LINKS
            return this.retrieveLinks(ps);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } }

    public boolean save(Link link, int projectId){

        String query = "INSERT INTO links (email, dateCreated, projectID) VALUES(?,NOW(),?)";

        try (PreparedStatement ps = getConnection().prepareStatement(query)) {

            ps.setString(1, link.getEmail());
            ps.setInt(2, link.getProjectID());
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

    public boolean delete(Link link){

        String query = "DELETE FROM links WHERE id = ? LIMIT 1";

        try (PreparedStatement ps = getConnection().prepareStatement(query)) {

            //PASS ID TO PREPARED STATEMENT
            ps.setInt(1, link.getId());

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

    public boolean deleteByEmail(String email){

        String query = "DELETE FROM links WHERE email = ? LIMIT 1";

        try (PreparedStatement ps = getConnection().prepareStatement(query)) {

            //PASS ID TO PREPARED STATEMENT
            ps.setString(1, email);

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

//    PRIVATE FUNCTIONS ///////////////////////////

    private List<Link> retrieveLinks(PreparedStatement ps) throws SQLException {

        ResultSet rs = ps.executeQuery();

        // LOOP THROUGH RESULTS - SHOULD ONLY BE ONE
        List<Link> links = new ArrayList<Link>();
        while (rs.next()) {
            //ADD NEW PROJECT WITH CURRENT RESULTSET DETAILS
            links.add(new Link(rs.getInt("id"),rs.getString("email")
                    ,rs.getTimestamp("dateCreated"),rs.getTimestamp("dateLastAccessed"),rs.getInt("projectID")));
        }

        return links;

    }

    private Link retrieveLink(PreparedStatement ps) throws SQLException{

        ResultSet rs = ps.executeQuery();

        // LOOP THROUGH RESULTS - SHOULD ONLY BE ONE
        Link link = null;
        while (rs.next()) {
            //ADD NEW PROJECT WITH CURRENT RESULTSET DETAILS
            link = new Link(rs.getInt("id"),rs.getString("email")
                    ,rs.getTimestamp("dateCreated"),rs.getTimestamp("dateLastAccessed"),rs.getInt("projectID"));
        }

        return link;

    }


}


