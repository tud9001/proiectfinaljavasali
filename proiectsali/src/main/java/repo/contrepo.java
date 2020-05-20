package repo;

import conn.DbConnection;
import obiecte.cont;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class contrepo {
    public static void save(cont c) throws SQLException {
        //c.setIdcont(c.getIdcont()+1);
        String sql = "INSERT INTO cont(username, password) VALUES(?, ?)";
        PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
        //preparedStatement.setInt(1, c.getIdcont());
        preparedStatement.setString(1, c.getUsername());
        preparedStatement.setString(2, c.getPassword());
        preparedStatement.execute();

        //return contrepo.findByUsername(angajat.getUsername());
    }
    public static String login(String user,String input)throws SQLException{
        Statement statement = DbConnection.getConnection().createStatement();
        String sql = "SELECT password FROM cont WHERE username like '" + user + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        //System.out.println(resultSet.getString("password"));
        //return resultSet.getString("password");
        if(!input.equals(resultSet.getString("password")))return "no";
        else return "Bun venit!";
    }
    public static int id(String user)throws SQLException{
        Statement statement = DbConnection.getConnection().createStatement();
        String sql = "SELECT idcont FROM cont WHERE username like '" + user + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt("idcont");
    }
}
