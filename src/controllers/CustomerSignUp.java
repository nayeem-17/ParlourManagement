package controllers;

import dbOperations.DbServices;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerSignUp {
    public static boolean signup(String username, String email, String mobileNumber) {
        try {
            Connection connection = DbServices.getInstance().getConnection();
            Statement statement = connection.createStatement();
            Date date = new Date(System.currentTimeMillis());
            String query = "INSERT INTO Customer VALUES ( '" + username + "','" + email + "','" + mobileNumber + "','" + date.toString() + "');";
            statement.executeUpdate(query);
            System.out.println(query);
            System.out.println("Inserted successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error in Customer SignUp is " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) signup("nayeem" + i, "email", "1911910p");
    }
}
