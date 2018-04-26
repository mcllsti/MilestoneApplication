package wpd2.teamR.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.teamR.dao.MilestoneDAO;
import wpd2.teamR.models.Milestone;
import wpd2.teamR.util.FlashMessage;
import wpd2.teamR.util.SessionFunctions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

        //int id = Integer.parseInt(request.getParameter("projectId")); TODO: Get this to retrive porjectId of session.


       setCurrentProject(request,10); //Only put here for testing, need to make it get the porject id from the session.

        // TODO Make this all work. Don't think I'm approaching it correctly

        List<Milestone> milestoneList = new ArrayList<Milestone>();
        try {
            milestoneList = milestones.getAllMilestonesByProjectId(getCurrentProject(request));
        }
        catch (SQLException error){}

        HashMap<String, Object> viewBag = new HashMap<String, Object>();

        FlashMessage message = SessionFunctions.getFlashMessage(request);
//        viewBag.put("username",userName);
        viewBag.put("message", message);
        viewBag.put("total", milestoneList.size());
        viewBag.put("milestones", milestoneList);

        showView(response, "milestone/milestone-list.mustache", viewBag);

    }





}
