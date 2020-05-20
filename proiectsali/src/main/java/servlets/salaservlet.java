package servlets;

import obiecte.cont;
import obiecte.sali;
import repo.contrepo;
import repo.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/sala")
public class salaservlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tip=req.getParameter("tip");
        String n=req.getParameter("nume");
        String nnou=req.getParameter("nou");
        if(tip.equals("new")){
        try {
            salarepo.save(new sali(n));
        } catch (SQLException e) {
            e.printStackTrace();
        }}
        if(tip.equals("edit")){
        try {
             salarepo.edit(n,nnou);
        }catch (SQLException e) {
            e.printStackTrace();
        }}
        if(tip.equals("del")){
            try{
                salarepo.del(n);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
