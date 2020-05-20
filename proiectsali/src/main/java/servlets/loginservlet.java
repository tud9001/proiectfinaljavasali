package servlets;

import obiecte.cont;
import repo.contrepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

@WebServlet("/login")
public class loginservlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String u=req.getParameter("username");
        String p=req.getParameter("password");
        try {
            resp.getWriter().println(contrepo.login(u,p));
        } catch (SQLException e) {
            resp.getWriter().println("Nu exista user-ul " + u);
        }*/
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String line = request.getReader().readLine();
        line = URLDecoder.decode(line);
        String[] tokens = line.split("&");
        System.out.println(line);

            String u=tokens[0].split("=")[1];
            String p=tokens[1].split("=")[1];

            System.out.println(u);
            System.out.println(p);
            try {
                //response.getWriter().println(contrepo.login(u,p));
                System.out.println(contrepo.login(u,p));
                if (contrepo.login(u,p).equals("no")){
                    System.out.println("a");
                    request.setAttribute("loginError", " Username gresit");}
                else {
                    System.out.println("b");
                    request.setAttribute("username", u);
                    request.setAttribute("loginError", " Username corect");
                }
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } catch (SQLException | ServletException e) {
                response.getWriter().println("Nu exista user-ul " + u);
            }



            //request.getRequestDispatcher("login.jsp").forward(request, response);


    }
}
