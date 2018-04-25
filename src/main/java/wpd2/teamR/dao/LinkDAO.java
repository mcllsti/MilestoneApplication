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

        final String GET_PROJECT = "SELECT * FROM links WHERE id=?";

        try (PreparedStatement ps = connection.prepareStatement(GET_PROJECT)) {

            // PASS THROUGH THE id INTO THE PREPARED STATEMENT
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            // LOOP THROUGH RESULTS - SHOULD ONLY BE ONE
            Link link = null;
            while (rs.next()) {
                //ADD NEW PROJECT WITH CURRENT RESULTSET DETAILS
                link = new Link(rs.getInt("id"),rs.getString("email")
                        ,rs.getTimestamp("dateCreated"),rs.getTimestamp("dateLastAccessed"),rs.getInt("projectID"));
            }

            return link;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Link findByProjectId(int id) throws SQLException
    {

        final String GET_PROJECT = "SELECT * FROM links WHERE projectId=?";

        try (PreparedStatement ps = connection.prepareStatement(GET_PROJECT)) {

            // PASS THROUGH THE id INTO THE PREPARED STATEMENT
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            // LOOP THROUGH RESULTS - SHOULD ONLY BE ONE
            Link link = null;
            while (rs.next()) {
                //ADD NEW PROJECT WITH CURRENT RESULTSET DETAILS
                link = new Link(rs.getInt("id"),rs.getString("email")
                        ,rs.getTimestamp("dateCreated"),rs.getTimestamp("dateLastAccessed"),rs.getInt("projectID"));
            }

            return link;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Link findByUserId(int userId) throws SQLException
    {

        final String GET_PROJECT = "SELECT links.id, links.email, links.dateCreated, links.dateLastAccessed, links.projectID FROM links, projects, users WHERE links.projectID = projects.id AND projects.userID = users.id AND users.id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(GET_PROJECT)) {

            // PASS THROUGH THE id INTO THE PREPARED STATEMENT
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            // LOOP THROUGH RESULTS - SHOULD ONLY BE ONE
            Link link = null;
            while (rs.next()) {
                //ADD NEW PROJECT WITH CURRENT RESULTSET DETAILS
                link = new Link(rs.getInt("id"),rs.getString("email")
                        ,rs.getTimestamp("dateCreated"),rs.getTimestamp("dateLastAccessed"),rs.getInt("projectID"));
            }

            return link;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Link> findAll() throws SQLException
    {

        final String GET_PROJECTS = "SELECT * FROM links";

        try (PreparedStatement ps = connection.prepareStatement(GET_PROJECTS)) {

            ResultSet rs = ps.executeQuery();

            // LOOP THROUGH RESULTS
            List<Link> links = new ArrayList<Link>();
            while (rs.next()) {
                //ADD NEW PROJECT WITH CURRENT RESULTSET DETAILS
                links.add(new Link(rs.getInt("id"),rs.getString("email")
                        ,rs.getTimestamp("dateCreated"),rs.getTimestamp("dateLastAccessed"),rs.getInt("projectID")));
            }

            return links;

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


}


