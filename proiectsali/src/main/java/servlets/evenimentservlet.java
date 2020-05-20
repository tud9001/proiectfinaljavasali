package servlets;

import obiecte.eveniment;
import repo.evenimentrepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import repo.*;
@WebServlet("/eveniment")
public class evenimentservlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tip=req.getParameter("tip");
        String n=req.getParameter("nume");
        String nn=req.getParameter("nou");
        Date di= null;
        Date df= null;
        try {
            di = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("dinc"));
            df = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("dfin"));
        } catch (Exception e) {
            //
        }
        String ie=req.getParameter("id");
        String u=req.getParameter("user");
        String s=req.getParameter("sala");

        //http://localhost:8090/eveniment?tip=new&nume=comicon4&dinc=2020-05-15&dfin=2020-06-21&user=tud&sala=partyrock
        if(tip.equals("new")){
            try {
                int uint=contrepo.id(u);
                int sint=salarepo.id(s);
                evenimentrepo.save(new eveniment(n,di,df,sint,uint));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println(ie);
        int idev;//=Integer.parseInt(ie);
        try{
            idev=Integer.parseInt(ie);
            System.out.println(idev);
        }catch (Exception e){
            idev=-1;
        }
        if(tip.equals("edit")){
            try{
                //int idev=Integer.parseInt(ie);
                evenimentrepo.edit(idev,nn,di,df,s);
            }catch (SQLException e) {
                e.printStackTrace();
            }catch (Exception e){

            }
        }
        if(tip.equals("editn")){
            try{
                evenimentrepo.editn(n,nn);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(tip.equals("deln")){
            try{
                evenimentrepo.del(n);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(tip.equals("del")){
            try{
                evenimentrepo.deli(idev);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
