package letscode;

import com.sun.org.apache.xml.internal.utils.res.StringArrayWrapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/MapPage/*")
        public class MapPage  extends HttpServlet {
    double x=-4.866943359375;
    double y=40.622154769976056;
    double z=2.1972656250000004;
    double w=42.99548785094031;

    double mx=41.82444787003703;

    double my=-1.3348388671875;

    static boolean isauthenticated = false;
    final int Transmission=5;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!isauthenticated)
        {
            resp.sendRedirect("/my-app/Authentication/failed");
        }

        String up=new String("<form method=\"get\" action=\"/my-app/MapPage/up\">\n" +
                "    <button type=\"submit\">up</button>\n" +
                "</form>");
        String down=new String("<form method=\"get\" action=\"/my-app/MapPage/down\">\n" +
                "    <button type=\"submit\">down</button>\n" +
                "</form>");
        String left=new String("<form method=\"get\" action=\"/my-app/MapPage/left\">\n" +
                "    <button type=\"submit\">left</button>\n" +
                "</form>");
        String right=new String("<form method=\"get\" action=\"/my-app/MapPage/right\">\n" +
                "    <button type=\"submit\">right</button>\n" +
                "</form>");
        String save=new String("<form method=\"get\" action=\"/my-app/MapPage/save\">\n" +
                "    <label>input name of object:\n<input name=\"name\">\n" +
                "    <label>input description of object:\n<input name=\"description\">\n" +
                "    <button type=\"submit\">save</button>\n" +
                "</form>");

        String uri = req.getRequestURI();
        System.out.println(uri);
        int fragup =uri.compareTo("/my-app/MapPage/up");
        int fragdown=uri.compareTo("/my-app/MapPage/down");
        int fragleft=uri.compareTo("/my-app/MapPage/left");
        int fragright=uri.compareTo("/my-app/MapPage/right");
        int fragsave =uri.compareTo("/my-app/MapPage/save");
        System.out.println("fragup=\n"+fragup);
        if (fragup == 0) {
            //x=x+1;
            y=y+Transmission;
            //z=z+1;
            w=w+Transmission;

            mx=mx+Transmission;
            System.out.println("Successful up, x= "+ x +"y= "+ y+"mx= "+mx+"my= "+my+"\n");
        }
        if (fragdown == 0) {
            //x=x+1;
            y=y-Transmission;
            //z=z+1;
            w=w-Transmission;

            mx=mx-Transmission;
            System.out.println("Successful down, x= "+ x +"y= "+ y+"mx= "+mx+"my= "+my+"\n");
        }

        if (fragleft == 0) {
            x=x-Transmission;
            //y=y+Transmission;
            z=z-Transmission;
            //w=w+Transmission;

            //mx=mx+Transmission;
            my=my-Transmission;
            System.out.println("Successful, x= "+ x +"y= "+ y+"mx= "+mx+"my= "+my+"\n");
        }
        if (fragright == 0) {
            x=x+Transmission;
            //y=y+Transmission;
            z=z+Transmission;
            //w=w+Transmission;

            //mx=mx+Transmission;
            my=my+Transmission;
            System.out.println("Successful, x= "+ x +"y= "+ y+" mx= "+mx+" my= "+my+"\n");
        }
        if (fragsave==0)
        {
            Map<String,String[]> params = req.getParameterMap();
            String [] nameorig = (params.get("name"));
            resp.getWriter().write("you've entered this name:" + nameorig[0] + "\n");
            String[] description = (params.get("description"));
            resp.getWriter().write("you've entered this description:" + description[0]);
        }

        double minusxmy= x-my;
        double minusmyz=my-z;
        double minusymx= y-mx;
        double minuswmx= mx-w;
        System.out.println("x-my= "+minusxmy+" my-z = "+minusmyz + "\n");
        System.out.println("y-mx= "+minusymx+" mx-w = "+minuswmx);
        String sx= new String(String.valueOf(x));
        String sy= new String(String.valueOf(y));
        String sz=new String(String.valueOf(z));
        String sw=new String(String.valueOf(w));
        String smx=new String(String.valueOf(mx));
        String smy=new String(String.valueOf(my));
        String map =new String("<iframe width=\"425\" height=\"350\" frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" src=\"https://www.openstreetmap.org/export/embed.html?bbox="+sx+"%2C"+sy+"%2C"+sz+"%2C"+sw+"&amp;layer=mapnik&amp;marker="+smx+"%2C"+smy+"\" style=\"border: 1px solid black\"></iframe><br/>\n" +
                "<small><a href=\"https://www.openstreetmap.org/?mlat=51.824&amp;mlon=-1.335#map=8/51.824/-1.335\">Посмотреть более крупную карту</a></small>\n");
        // Параметр
        String parameter = req.getParameter("parameter");

        // Старт HTTP сессии
        HttpSession session = req.getSession(true);
        session.setAttribute("parameter", parameter);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try{
            out.println(up);
            out.println(down);
            out.println(left);
            out.println(right);
            out.println(save);
            out.println(map);
        }
        finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Map<String,String[]> params = req.getParameterMap();
        resp.getWriter().write("Method doPost:\n");
        resp.getWriter().write("URI = " + uri + "\nparams = " + params + "\n");
        String[] correctlogin={"123"};
        String[] correctpassword={"78"};
        System.out.println("login is "+ Arrays.equals(params.get("login"),correctlogin ));
        System.out.println("password is " + Arrays.equals(params.get("password"),correctpassword ));
        if (Arrays.equals(params.get("login"),correctlogin ) & Arrays.equals(params.get("password"),correctpassword ))
        {
            isauthenticated=true;
            resp.sendRedirect("/my-app/MapPage");
        }
        else {
            resp.sendRedirect("/my-app/Authentication/failed");
        }

    }
}
