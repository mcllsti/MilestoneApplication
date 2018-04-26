package wpd2.teamR.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import wpd2.teamR.models.Link;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class LinkDAOTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        LinkDAO testingDAO = new LinkDAO();
        testingDAO.deleteByEmail("testclass@hotmail.com");
    }

    @Test
    public void save() throws SQLException {

        LinkDAO testingDAO = new LinkDAO();
        Link link = new Link("testclass@hotmail.com");
        assertTrue(testingDAO.save(link,6));
    }

    @Test
    public void findById() throws SQLException {

        LinkDAO testingDAO = new LinkDAO();
        Link link = testingDAO.findById(6);
        assertEquals(link.getId(), 6);

    }

    @Test
    public void findByProjectId() throws SQLException {
        LinkDAO testingDAO = new LinkDAO();
        List<Link> links =  testingDAO.findByProjectId(6);
        assertEquals(links.size(), 4);
    }

    @Test
    public void findByUserId() throws SQLException{
        LinkDAO testingDAO = new LinkDAO();
        List<Link> links =  testingDAO.findByUserId(4);
        assertEquals(links.size(), 2);
    }

    @Test
    public void findAll() throws SQLException{
        LinkDAO testingDAO = new LinkDAO();
        List<Link> links =  testingDAO.findAll();
        assertEquals(links.size(), 7);
    }


    @Test
    public void delete() {

        LinkDAO testingDAO = new LinkDAO();
        Link link = new Link("chris@hotmail.com",8);
        link.setId(8);
        assertTrue(testingDAO.delete(link));
    }

    @Test
    public void deleteByEmail() {
        LinkDAO testingDAO = new LinkDAO();
        assertTrue(testingDAO.deleteByEmail("chris@hotmail.com"));
    }
}