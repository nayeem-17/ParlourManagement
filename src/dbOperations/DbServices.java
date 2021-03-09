package dbOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import entities.Appointment;
import entities.Service;
import sun.reflect.generics.tree.Tree;

public class DbServices {

    /*
    private static DbServices dbServices = null;

    static
    {
        String url = "jdbc:sqlserver://localhost:1433/project";
        String user = "sa";
        String pass = "Rahi-8000";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

     */

//    private final EntityManager entityManager;

    private DbServices() throws SQLException {

        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

        String dbURL = "jdbc:sqlserver://localhost:1433/project;user=sa;password=Rahi-8000";

        Connection conn = DriverManager.getConnection(dbURL);

        if (conn != null) {
            System.out.println("Connected");
        }

    }

    public static DbServices getInstance() //Making this service class singleton
    {
        /*
        if (dbServices == null) {
            try {
                dbServices = new DbServices();
            } catch (SQLException throwables) {
                System.out.println("Can not connect to database, Error is: "+throwables.getMessage());
            }
        }


         */
        return null;
    }

    //Appointment operations
    public synchronized String addAnAppoinment(Appointment appointment) {
        return null;
//        try {
//            entityManager.getTransaction().begin();
//            entityManager.persist(appointment);
//            entityManager.getTransaction().commit();
//            return "Successfully made an appoinment. Appointment number is: " + String.valueOf(appointment.getAppointmentNumber());
//        } catch (Exception e) {
//            return "Can not make an appointment, please try again";
//        }
    }

    //Adding new service
    public synchronized String addNewService(Service service) {
        return null;
//        try {
//            entityManager.getTransaction().begin();
//            entityManager.persist(service);
//            entityManager.getTransaction().commit();
//            return "Successfully added new service";
//        } catch (Exception e) {
//            return "Can not add new service, please try again";
//        }
    }

    public synchronized List<String> getServicesList() {
        return null;
//        try {
//            entityManager.getTransaction().begin();
//            List<String> servicesList = entityManager.createQuery("SELECT t.serviceName FROM Service t").getResultList();
//            entityManager.getTransaction().commit();
//            return servicesList;
//        } catch (Exception e) {
//            return null;
//        }
    }

    public synchronized List<Service> getAllServicesRecords() {
        return null;
//        try {
//            entityManager.getTransaction().begin();
//            List<Service> servicesList = entityManager.createQuery("SELECT t FROM Service t").getResultList();
//            entityManager.getTransaction().commit();
//            return servicesList;
//        } catch (Exception e) {
//            return null;
//        }
    }

    public synchronized List<String> getTimeSlots() {
        return null;
//        try {
//            entityManager.getTransaction().begin();
//            List<String> timeSlotLists = entityManager.createQuery("SELECT t.timeSlots FROM TimeSlots t").getResultList();
//            entityManager.getTransaction().commit();
//            return timeSlotLists;
//        } catch (Exception e) {
//            return null;
//        }
    }

    public synchronized String saveTime(String text) {
        return "Hello World";
        /*
        TimeSlots timeSlots = new TimeSlots();

        timeSlots.setTimeSlots(text);

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(timeSlots);
            entityManager.getTransaction().commit();
            return "Successfully added time slot to database";
        } catch (Exception e) {
            return "Can not add time slot to database, please try again";
        }

         */
    }

}
