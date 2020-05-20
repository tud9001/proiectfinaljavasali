package repo;

import conn.DbConnection;
import obiecte.cont;
import obiecte.sali;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class salarepo {
    public static void save(sali s) throws SQLException {
        String sql = "INSERT INTO sali(nume) VALUES(?)";
        PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, s.getNume());
        preparedStatement.execute();
    }
    public static void edit(String n,String nou)throws SQLException {

        String sql = "UPDATE sali SET nume='" + nou + "'WHERE nume='" + n +"'";
        PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
        preparedStatement.execute();

    }
    public static void del(String n)throws SQLException{
        String sql = "DELETE FROM sali WHERE nume='" + n + "'";
        PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
        preparedStatement.execute();
    }

    public static int id(String n)throws SQLException{
        Statement statement = DbConnection.getConnection().createStatement();
        String sql = "SELECT idsali FROM sali WHERE nume like '" + n + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt("idsali");
    }

}
