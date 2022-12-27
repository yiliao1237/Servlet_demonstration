package letscode;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/my-cool-servlet/*")
public class MainServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log("Method init =)");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().write("Method service enter\n");
        super.service(req, resp);
        resp.getWriter().write("Hello world!\n");
        //resp.getWriter().write("Method service exit\n");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //resp.getWriter().write("Method doGet\n");
        String URI = req.getRequestURI();
        Map<String,String[]> params = req.getParameterMap();
        System.out.println("URI = " + URI + " params = " + params);
        resp.getWriter().write("URI = " + URI + "\nparams = " + params + "\n");
        //log("Log is here");
        String map =new String("<iframe width=\"425\" height=\"350\" frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" src=\"https://www.openstreetmap.org/export/embed.html?bbox=-4.866943359375%2C50.622154769976056%2C2.1972656250000004%2C52.99548785094031&amp;layer=mapnik&amp;marker=51.82444787003703%2C-1.3348388671875\" style=\"border: 1px solid black\"></iframe><br/>\n" +
                "<small><a href=\"https://www.openstreetmap.org/?mlat=51.824&amp;mlon=-1.335#map=8/51.824/-1.335\">Посмотреть более крупную карту</a></small>\n");
        // Параметр
        String parameter = req.getParameter("parameter");

        // Старт HTTP сессии
        HttpSession session = req.getSession(true);
        session.setAttribute("parameter", parameter);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try{
            out.println(map);
        }
        finally {
            out.close();
        }
    }

    @Override
    public void destroy() {
        log("Method destroy =)");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Map<String,String[]> params = req.getParameterMap();
        resp.getWriter().write("Method doPost:\n");
        resp.getWriter().write("URI = " + uri + "\nparams = " + params + "\n");
    }

}

