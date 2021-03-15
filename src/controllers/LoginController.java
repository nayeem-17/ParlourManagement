package controllers;

import dbOperations.DbServices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginController {

    public static boolean isValid(String usrname, String password) {

        try {
            Connection connection = DbServices.getInstance().getConnection();
            Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM admin where username= '" + usrname.trim() + "'";
            ResultSet rs = statement.executeQuery(query);
            return rs.getString("password").equals(password.trim());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}