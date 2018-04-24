package wpd2.teamR.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.teamR.dao.DAOBase;
import wpd2.teamR.dao.ProjectDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


public class PublicPageServlet extends BaseServlet {
    @SuppressWarnings("unused")
    static final Logger LOG = LoggerFactory.getLogger(PublicPageServlet.class);

    private static final String PUBLIC_PAGE_TEMPLATE = "public.mustache";

    public PublicPageServlet() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {

//        if (!authOK(request, response)) {
//            return;
//        }

        String userName = UserFuncs.getCurrentUser(request);
        showView(response, PUBLIC_PAGE_TEMPLATE, userName);
    }
}
