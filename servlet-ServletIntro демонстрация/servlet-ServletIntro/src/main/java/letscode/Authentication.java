package letscode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

@WebServlet(urlPatterns = "/Authentication/*")
public class Authentication extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String form=new String("<form method=\"post\" action=\"/my-app/MapPage\">\n" +
                "    <label>Login:<input name=\"login\">\n" +
                "    <label>Password:<input name=\"password\">\n" +
                "    <button type=\"submit\">Send</button>\n" +
                "</form>");

        String uri = req.getRequestURI();
        int fragfail=uri.compareTo("/my-app/Authentication/failed");
        if (fragfail==0)
            resp.getWriter().println("Oh, seems that you entered wrong password or login,\n please try again\n");
        resp.getWriter().write("Please insert login and password");
        // Параметр
        String parameter = req.getParameter("parameter");

        // Старт HTTP сессии
        HttpSession session = req.getSession(true);
        session.setAttribute("parameter", parameter);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try{
            out.println(form);
        }
        finally {
            out.close();
        }
    }
}
