package wpd2.teamR;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.teamR.servlet.*;

public class Runner {
    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(Runner.class);

    private static final int PORT = 9001;


    private Runner() {
    }

    /**
     * Start server
     * @throws Exception
     */
    private void start() throws Exception {

        // SETUP THE SERVER AND PASS THE PORT NUMBER
        Server server = new Server(PORT);

//        WebAppContext webapp = new WebAppContext();
//        webapp.setContextPath("/");
//        webapp.getWebInf("/WEB-INF/web.xml");

        // SETUP SESSIONS, BASE PATH AND RESOURCE PATH
        ServletContextHandler handler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        handler.setInitParameter("org.eclipse.jetty.servlet.Default." + "resourceBase", "src/main/resources/webapp");

        // SETUP DEFAULT SERVLET AND ADD DEFAULT SERVLET HOLDER
        DefaultServlet ds = new DefaultServlet();
        handler.addServlet(new ServletHolder(ds), "/");

        // SETUP AND POINT A URL FOR SERVLET 1
//        Servlet1 servlet1 = new Servlet1();
//        handler.addServlet(new ServletHolder(servlet1), "/servlet1");

        // SETUP AND POINT A URL FOR SERVLET 2
//        Servlet2 servlet2 = new Servlet2();
//        handler.addServlet(new ServletHolder(servlet2), "/servlet2");

        // SETUP AND POINT A URL FOR THE PRIVATE AND PUBLIC SERVLETS AS WELL AS LOGIN
        //     //register

        handler.addServlet(new ServletHolder(new PublicPageServlet()), "/project");
        handler.addServlet(new ServletHolder(new Servlet1()), "/project/*");

        //handler.addServlet(new ServletHolder(new Servlet2()), "/project/*/milestones");

//        handler.addServlet(new ServletHolder(new PublicPageServlet()), "/public/*/");
//        handler.addServlet(new ServletHolder(new PublicPageServlet()), "/public/*/*/");
        handler.addServlet(new ServletHolder(new PrivatePageServlet()), "/private");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login");
        handler.addServlet(new ServletHolder(new LogoutServlet()), "/logout");


//        DAOBase daoBase = new DAOBase();
//        daoBase.getAllUsers();



        // START THE SERVER
        server.start();
        LOG.info("Server started, will run until terminated");
        server.join();

    }

    public static void main(String[] args) {
        try {

            // START THE SERVER ABOVE
            LOG.info("starting...");
            new Runner().start();

        } catch (Exception e) {

            // SOMETHING WENT WRONG
            LOG.error("Unexpected error running: " + e.getMessage());

        }
    }
}
