package repo;

import conn.DbConnection;
import obiecte.cont;
import obiecte.eveniment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Formatter;

public class evenimentrepo {
    public static boolean dataok1(String di, String df,int idsala) throws SQLException {
        Statement statement = DbConnection.getConnection().createStatement();
        System.out.println(idsala);
        String sql = "select count(idsala) from eveniment where ((date(datainceput)<=date('"+di+"') and date(datafinal)>=date('"+di+"')) or (date(datainceput)<=date('"+df+"') and date(datafinal)>=date('"+df+"'))) and idsala="+ idsala;
        ResultSet resultSet = statement.executeQuery(sql);
        try {
            System.out.println(resultSet.getInt(1));
        }catch (Exception e){
            System.out.println("nu am ce citi");
        }
            resultSet.next();

        System.out.println(di + " " +df);
        System.out.println(resultSet.getString(1));
        if(resultSet.getInt(1)>0)return false;
        return true;
    }
    public static boolean dataok2(String di, String df, int idev) throws SQLException {
        Statement statement = DbConnection.getConnection().createStatement();
        String sql = "select ideveniment from eveniment where (date(datainceput)<=date('"+di+"') and date(datafinal)>=date('"+di+"')) or (date(datainceput)<=date('"+df+"') and date(datafinal)>=date('"+df+"'))";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(di + " " + df);
            if (idev==resultSet.getInt(1)) return true;
        }
        return false;
    }
    public static boolean save(eveniment e) throws SQLException {
        //c.setIdcont(c.getIdcont()+1);
        Date d=e.getDatainceput();
        Date dd=e.getDatafinal();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String s=dateFormat.format(d);
        String ss=dateFormat.format(dd);
        if(ss.compareTo(s)<0){
            String aux=ss;
            ss=s;
            s=aux;
        }
        System.out.println(e.getIdsali());
        if(dataok1(s,ss,e.getIdsali())!=false){
        String sql = "INSERT INTO eveniment(nume, datainceput, datafinal, idsala, idcont) VALUES(?,?, ?, ?, ?)";
        PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
        //preparedStatement.setInt(1, c.getIdcont());

        preparedStatement.setString(1, e.getNume());
        preparedStatement.setString(2, s);
        preparedStatement.setString(3, ss);
        preparedStatement.setInt(4, e.getIdsali());
        preparedStatement.setInt(5, e.getIdcont());
        preparedStatement.execute();
        return true;}
        else return false;

        //return contrepo.findByUsername(angajat.getUsername());
    }
    public static String select(String tip,int idev)throws SQLException{
        Statement statement = DbConnection.getConnection().createStatement();
        String sql = "SELECT " + tip + " FROM eveniment WHERE ideveniment=" + idev ;
        System.out.println(sql);
        ResultSet resultSet=statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getString(1);
    }
    public static void edit(int idev,String nn,Date di, Date df, String ns) throws SQLException{
        //String sql1 = "SELECT ideveniment from eveniment where nn";
        System.out.println(di+ " "+df);
        String s;
        String ss;
        Date d=di;
        Date dd=df;
        int idsala;
        //int idev = Integer.parseInt(select("ideveniment",));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        if(di==null)s=select("datainceput",idev);
        else s=dateFormat.format(d);
        if(df==null)ss=select("datafinal",idev);
        else ss=dateFormat.format(dd);
        if(ns==null)idsala=Integer.parseInt(select("idsala",idev));
        else idsala=salarepo.id(ns);
        if(nn==null)nn=select("nume",idev);
        if(ss.compareTo(s)<0){
            return;
        }
        if(dataok1(s,ss,idsala)==true || dataok2(s,ss,idev)==true) {
            String sql = "UPDATE eveniment SET idsala="+idsala+" ,nume='" + nn + "' ,datainceput='" + s +"' , datafinal='"+ ss +"'WHERE ideveniment=" + idev ;
            PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
            preparedStatement.execute();
        }
    }
    public static List<String> salilibere(Date d) throws SQLException {
        Statement statement = DbConnection.getConnection().createStatement();
        List<String> s =new ArrayList<>();
        String sql="select idsali,sali.nume from sali left join eveniment on idsali=idsala where ideveniment is null;";

        ResultSet resultSet=statement.executeQuery(sql);

        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
            s.add(resultSet.getString(2));
        }
        System.out.println(d);
        if(d!=null) {
            String sql2 = "select idsala from evenimente where date(datainceput)>'" + d + "' or date(datafinal)<'" + d + "'";
            ResultSet resultSet2=statement.executeQuery(sql2);
        while (resultSet2.next()){
            s.add(resultSet2.getString(1));
        }}
        System.out.println(s);
        return s;
    }
    public static void editn(String n,String nn)throws SQLException{
        String sql = "UPDATE eveniment SET nume='" + nn+ "'WHERE nume='" + n+ "'";
        PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
        preparedStatement.execute();
    }
    public static void del(String n)throws SQLException{
        String sql = "DELETE FROM eveniment WHERE nume='" + n + "'";
        PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
        preparedStatement.execute();
    }
    public static void deli(int idev)throws SQLException{
        String sql = "DELETE FROM eveniment WHERE ideveniment='" + idev + "'";
        PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
        preparedStatement.execute();
    }

}
