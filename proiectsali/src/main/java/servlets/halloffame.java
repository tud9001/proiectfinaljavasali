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

@WebServlet("/halloffame")
public class halloffame extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Statement statement = null;
        try {
            statement = DbConnection.getConnection().createStatement();

        String sql = "select count(idsala),sali.nume from eveniment join sali on idsala=idsala group by(idsala) order by count(idsala) DESC";

        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        resp.getWriter().println("Cea mai populara sala: " + resultSet.getInt(1)+ " " + resultSet.getString(2));
        sql = "select max(datediff(datafinal,datainceput)),nume from eveniment";
        resultSet = statement.executeQuery(sql);
        resultSet.next();
        resp.getWriter().println("Cel mai lung eveniment: " + resultSet.getInt(1)+ " zile " + resultSet.getString(2));
        } catch (SQLException e) {
        e.printStackTrace();
    }

    }
}
