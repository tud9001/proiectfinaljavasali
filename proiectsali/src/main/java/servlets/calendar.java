package servlets;

import conn.DbConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/calendar")
public class calendar extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Statement statement = DbConnection.getConnection().createStatement();
            String sql = "select sali.nume,datainceput,datafinal from eveniment join sali on idsala=idsala " ;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                resp.getWriter().println(resultSet.getString(1)+ " " + resultSet.getString(2) + " " + resultSet.getString(3));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
