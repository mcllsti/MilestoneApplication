package wpd2.teamR.models;

import org.junit.Test;


import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class MilestoneTest {

    @Test
    public void getDateDueDay() {
        Date d = new Date();
        Timestamp s = new Timestamp(d.getTime());
        Milestone m = new Milestone(1,"Test","This is a test",s,s,s,s);

        assertEquals("27",m.getDueDay());
    }

    @Test
    public void getDateDueMonth() {
        Date d = new Date();
        Timestamp s = new Timestamp(d.getTime());
        Milestone m = new Milestone(1,"Test","This is a test",s,s,s,s);

        assertEquals(4,m.getDueMonth());
    }

    @Test
    public void getDateDueYear() {
        Date d = new Date();
        Timestamp s = new Timestamp(d.getTime());
        Milestone m = new Milestone(1,"Test","This is a test",s,s,s,s);

        assertEquals("2018",m.getDueYear());
    }
}