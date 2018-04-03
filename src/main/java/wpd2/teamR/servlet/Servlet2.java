package wpd2.teamR.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Servlet2 extends BaseServlet {

    @SuppressWarnings("unused")
    static final Logger LOG = LoggerFactory.getLogger(Servlet2.class);
    private static final long serialVersionUID = -7461821901454655091L;

    public Servlet2() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String n = (String) session.getAttribute("uname");
        String out = "Hello " + n;   ;
        issue(HTML_UTF_8, HttpServletResponse.SC_OK, out.getBytes(CHARSET_UTF8), response);
    }
}
