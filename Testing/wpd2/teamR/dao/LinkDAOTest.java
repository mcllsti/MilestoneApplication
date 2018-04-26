package wpd2.teamR.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import wpd2.teamR.models.Link;

import static org.junit.Assert.*;

public class LinkDAOTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findById() {

//        LinkDAO testingDAO = new LinkDAO();
//        Link link = testingDAO.findById(5);
//        assertEquals(link, new Link("chris@hotmail.com",6));

    }

    @Test
    public void findByProjectId() {
    }

    @Test
    public void findByUserId() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() {

        LinkDAO testingDAO = new LinkDAO();
        Link link = new Link("chris@hotmail.com");

        assertTrue(testingDAO.save(link,6));
    }

    @Test
    public void delete() {

//        LinkDAO testingDAO = new LinkDAO();
//        Link link = new Link("chris@hotmail.com",5);
//        assertTrue(testingDAO.delete(link));
    }

    @Test
    public void deleteByEmail() {
    }
}