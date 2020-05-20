package servlets;

import repo.evenimentrepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/sugerare")
public class sugerare extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String d=req.getParameter("data");
        Date dd= null;
        try {
            dd = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("data"));

        } catch (Exception e) {
            //
        }
        try {
            System.out.println(evenimentrepo.salilibere(dd));
            resp.getWriter().println(evenimentrepo.salilibere(dd));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
