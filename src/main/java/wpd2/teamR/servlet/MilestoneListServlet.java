package wpd2.teamR.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.teamR.dao.MilestoneDAO;
import wpd2.teamR.models.Milestone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MilestoneListServlet extends BaseServlet {

    static final Logger LOG = LoggerFactory.getLogger(ProjectListServlet.class);

    private MilestoneDAO milestones;

    public MilestoneListServlet(){ milestones = new MilestoneDAO();}


    protected void  doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // CHECK IF USER IS LOGGED IN - IF NOT BOUNCE TO LOGIN
        if (!authOK(request, response)) {
            return;
        }
        // TODO Make this all work. Don't think I'm approaching it correctly

        List<Milestone> milestoneList = new ArrayList<Milestone>();
        try {
            milestoneList = milestones.getAllMilestones(getCurrentProject(request), getCurrentUser(request));
        }
        catch (SQLException error){}



    }



}
