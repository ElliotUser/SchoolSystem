package loginapp;

import dbUtil.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    Connection connection;

    public LoginModel(){
        try {
            this.connection = dbConnection.getConnection ();
        }catch (SQLException ex){
            ex.printStackTrace ();
        }

        if (this.connection ==null){
            System.exit (1);
        }
    }

    public boolean isDataBaseConnected(){
        return this.connection !=null;
    }

    public boolean isLogin(String user, String password, String options) throws Exception{
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM login where username - ? and password - ? and division - ?";

        try {
            pr = this.connection.prepareStatement (sql);
            pr.setString (1,user);
            pr.setString (2,password);
            pr.setString (3,options);

            rs = pr.executeQuery ();

            boolean bolli;

            if (rs.next ()){
                return true;
            }
            return false;
        }
        catch (SQLException ex){
            return  false;
        }

        finally {
            {
                pr.close ();
                rs.close ();
            }

        }
    }
}
