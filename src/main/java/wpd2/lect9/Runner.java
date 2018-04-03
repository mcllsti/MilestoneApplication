package wpd2.lect9;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wpd2.lect9.servlet.*;

public class Runner {
    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(Runner.class);

    private static final int PORT = 9001;


    private Runner() {
    }

    private void start() throws Exception {
        Server server = new Server(PORT);

        ServletContextHandler handler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        handler.setInitParameter("org.eclipse.jetty.servlet.Default." + "resourceBase", "src/main/resources/webapp");

        DefaultServlet ds = new DefaultServlet();
        handler.addServlet(new ServletHolder(ds), "/");

        Servlet1 servlet1 = new Servlet1();
        handler.addServlet(new ServletHolder(servlet1), "/servlet1");

        Servlet2 servlet2 = new Servlet2();
        handler.addServlet(new ServletHolder(servlet2), "/servlet2");

        handler.addServlet(new ServletHolder(new PublicPageServlet()), "/public");
        handler.addServlet(new ServletHolder(new PrivatePageServlet()), "/private");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login");
        handler.addServlet(new ServletHolder(new LogoutServlet()), "/logout");
        server.start();
        LOG.info("Server started, will run until terminated");
        server.join();

    }

    public static void main(String[] args) {
        try {
            LOG.info("starting...");
            new Runner().start();
        } catch (Exception e) {
            LOG.error("Unexpected error running: " + e.getMessage());
        }
    }
}
