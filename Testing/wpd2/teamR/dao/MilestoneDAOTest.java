package wpd2.teamR.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import wpd2.teamR.models.Milestone;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class MilestoneDAOTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createMilestone() {


        MilestoneDAO milestones = new MilestoneDAO();
        Milestone insetM = new Milestone("Test","Test",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),6);
        assertTrue(milestones.createMilestone(insetM,6));
    }

    @Test
    public void deleteMilestoneById() {
    }

    @Test
    public void updateMilestone() {
    }
}