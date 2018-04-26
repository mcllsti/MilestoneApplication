package wpd2.teamR.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import wpd2.teamR.models.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ProjectDAOTest {

    @Before
    public void setUp() throws Exception {

    }

    @AfterClass
    public static void tearDown() throws Exception {

        ConnectionSupplier cs = new ConnectionSupplier();
        Connection connection = cs.provide();

        String query = "DELETE FROM projects WHERE name = ?";

        PreparedStatement ps = connection.prepareStatement(query);

        //PASS ID TO PREPARED STATEMENT
        ps.setString(1, "Test");

        ps.executeUpdate();

    }

    @Test
    public void createProject() {
        ProjectDAO testingDAO = new ProjectDAO();
        Project project = new Project("Test","Auto Test");

        assertTrue(testingDAO.createProject(project,"chris@chrisconnor.co.uk"));
    }

    @Test
    public void getProjectById() throws SQLException {

        ProjectDAO testingDAO = new ProjectDAO();
        assertTrue(testingDAO.getProjectById(2).getName().equals("Web Platform Development"));

    }

    @Test
    public void deleteProjectById() {
        ProjectDAO testingDAO = new ProjectDAO();

        assertFalse(testingDAO.deleteProjectById(2000));
    }



    @Test
    public void getAllProjects() throws SQLException {
        ProjectDAO testingDAO = new ProjectDAO();
        assertNotNull(testingDAO.getAllProjects());
    }

    @Test
    public void getProjectsbyUser() throws SQLException {
        ProjectDAO testingDAO = new ProjectDAO();
        assertFalse(testingDAO.getProjectsbyUser("g.macleod@domain.com").isEmpty());
    }




}