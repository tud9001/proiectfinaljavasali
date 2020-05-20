package servlets;

import obiecte.cont;
import repo.contrepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static repo.contrepo.save;

@WebServlet("/cont")
public class contservlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String u=req.getParameter("username");
        String p=req.getParameter("password");
        try {
            contrepo.save(new cont(u,p));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String s;
        List<cont> c=new ArrayList<>();
        int i=0;
        while ((s=req.getReader().readLine())!=null)
        {
            c.add(new cont(s.split("=")[0],s.split("=")[1]));
            try {
                contrepo.save(c.get(i));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ++i;
        }

    }
}
