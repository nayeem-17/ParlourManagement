package dbOperations;

import entities.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerOperations {

    DbServices dbServices = DbServices.getInstance();
    Connection connection = dbServices.getConnection();

    public synchronized List<Customer> getCustomer() {

        List<Customer> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM Customer";

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Customer user = new Customer();
                user.setCreationDate(rs.getString("creationDate"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setMobileNumber(rs.getString("mobileNumber"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
