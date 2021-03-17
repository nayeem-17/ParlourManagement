package dbOperations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entities.Appointment;
import entities.Customer;
import entities.Service;
import entities.TimeSlots;
import org.sqlite.JDBC;

public class DbServices {

    private static String sqlitedbUrl = "jdbc:sqlite:./main.db";

    public Connection getConnection() {
        return connection;
    }

    private static DbServices dbServices = null;
    private Connection connection = null;

    private DbServices() throws SQLException {

//        for sql server
     /*   DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

        String dbURL = "jdbc:sqlserver://localhost:1433;user=sa;password=<YourStrong@Passw0rd>";
    */
        DriverManager.registerDriver(new JDBC());
        Connection conn = DriverManager.getConnection(sqlitedbUrl);

        try {
            connection = DriverManager.getConnection(sqlitedbUrl);
            System.out.println("Connection Successful!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (conn != null) {
            System.out.println("Connected");
        }

    }

    public static void main(String[] args) {
//        DbServices.getInstance();
        List<Customer> q = DbServices.getInstance().getCustomer();
        for (Customer a : q) {
            System.out.println(a);
        }
    }

    public static DbServices getInstance() //Making this service class singleton
    {
//        /*
        if (dbServices == null) {
            try {
                dbServices = new DbServices();
            } catch (SQLException throwables) {
                System.out.println("Can not connect to database, Error is: " + throwables.getMessage());
            }
        }
        return dbServices;
    }

    //Appointment operations
    public synchronized String addAnAppoinment(Appointment appointment) {
//        return null;
        try {
//            entityManager.getTransaction().begin();
//            entityManager.persist(appointment);
//            entityManager.getTransaction().commit();
            return "Successfully made an appoinment. Appointment number is: " + String.valueOf(appointment.getAppointmentNumber());
        } catch (Exception e) {
            return "Can not make an appointment, please try again";
        }
    }

    //Adding new service
    public synchronized String addNewService(Service service) {
//        return null;
        try {
//            entityManager.getTransaction().begin();
//            entityManager.persist(service);
//            entityManager.getTransaction().commit();
            return "Successfully added new service";
        } catch (Exception e) {
            return "Can not add new service, please try again";
        }
    }

//    public synchronized List<String> getServicesList() {
////        return null;
//        try {
////            entityManager.getTransaction().begin();
////            List<String> servicesList = entityManager.createQuery("SELECT t.serviceName FROM Service t").getResultList();
////            entityManager.getTransaction().commit();
////            return servicesList;
//        } catch (Exception e) {
//            return null;
//        }
//        return null;
//    }

    public synchronized List<Service> getAllServicesRecords() {
        List<Service> services = new ArrayList<>();
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM Service";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getString("id"));
                service.setServiceName(rs.getString("name"));
                service.setServicePrice(rs.getString("price"));
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    public synchronized List<String> getTimeSlots() {
        List<String> times = new ArrayList<>();
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM Timeslot";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                times.add(rs.getString("time"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return times;
    }

    public synchronized List<Customer> getCustomer() {
        List<Customer> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM Customer";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Customer user = new Customer();
                user.setCreationDate(rs.getString("creation date"));
                user.setName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setMobileNumber(rs.getString("mobile"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public synchronized String saveTime(String text) {
        TimeSlots timeSlots = new TimeSlots();
        timeSlots.setTimeSlots(text);

        try {
//            entityManager.getTransaction().begin();
//            entityManager.persist(timeSlots);
//            entityManager.getTransaction().commit();
            return "Successfully added time slot to database";
        } catch (Exception e) {
            return "Can not add time slot to database, please try again";
        }
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        try {
            Connection connection = DbServices.getInstance().getConnection();
            Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM Appointment";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentNumber(rs.getString("appointment id"));
                appointment.setName(rs.getString("name"));
                appointment.setEmail(rs.getString("email"));
                appointment.setAppointMakingDate(rs.getString("create at"));
                appointment.setPhoneNumber(rs.getString("mobile"));
                appointment.setSelectedService(rs.getString("service"));
                appointment.setAppointmentDate(rs.getString("date"));
                appointment.setAppointmentTime(rs.getString("time"));
                appointment.setStatus(rs.getString("status"));

                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
}
