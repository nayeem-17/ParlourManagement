package dbOperations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entities.Appointment;
import entities.Service;

public class DbServices {

    private static DbServices dbServices = null;
    private Connection connection = null;

    private DbServices() throws SQLException {
        try {

            String connectionUrl = "jdbc:sqlserver://localhost:1433; databaseName=testDatabase;" +
                    "user=rahi;password=Rahi-8000";

            connection = DriverManager.getConnection(connectionUrl);

            new Thread(() -> {
                try {
                    createTablesIfNotExists();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }).start();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static DbServices getInstance()
    {
        if (dbServices == null) {
            try {
                dbServices = new DbServices();
            } catch (SQLException throwable) {
                System.out.println("Can not connect to database, Error is: " + throwable.getMessage());
            }
        }
        return dbServices;
    }

    private void createTablesIfNotExists() throws SQLException {
        if(connection!=null)
        {
            Statement statement = connection.createStatement();

            {
                String createAdminTable = "CREATE TABLE appointment(appointmentId Integer PRIMARY KEY IDENTITY(1, 1), appointmentNumber varchar(255), " +
                        "name varchar(255), email varchar(255),  phoneNumber varchar(255), appointMakingDate date, appointmentTime varchar(255), selectedService varchar(255)," +
                        "appointmentDate datetime, remark varchar(255), status varchar(255), remarkDate datetime)";

                ResultSet appointmentTable = connection.getMetaData().getTables(null, null,
                        "appointment", null);

                if (appointmentTable.next()) {
                    System.out.println("Appointment table already exists");
                } else {
                    statement.executeUpdate(createAdminTable);
                    System.out.println("Successfully created appointment table");
                }
            }

            {
                String createTimeSlotTable = "CREATE TABLE timeSlots(timeSlots varchar(255))";

                ResultSet timeSlots = connection.getMetaData().getTables(null, null,
                        "timeSlots", null);

                if (timeSlots.next()) {
                    System.out.println("Time slots table already exists");
                } else {
                    statement.executeUpdate(createTimeSlotTable);
                    System.out.println("Successfully created timeslots table");
                }
            }

            {
                String createServiceTable = "CREATE TABLE service(id INTEGER PRIMARY KEY Identity(1,1) , serviceName varchar(255)," +
                        "servicePrice DOUBLE PRECISION)";

                ResultSet serviceTable = connection.getMetaData().getTables(null, null,
                        "service", null);

                if (serviceTable.next()) {
                    System.out.println("Service table already exists");
                } else {
                    statement.executeUpdate(createServiceTable);
                    System.out.println("Successfully created service table");
                }
            }

            {
                String createAdminTable = "CREATE TABLE admin(adminId INTEGER PRIMARY KEY Identity(1,1) " +
                        ", name varchar(255), password varchar(255), email varchar(255), contactNumber varchar(255))" ;

                ResultSet adminTable = connection.getMetaData().getTables(null, null,
                        "admin", null);

                if (adminTable.next()) {
                    System.out.println("Admin table already exists");
                } else {
                    statement.executeUpdate(createAdminTable);
                    System.out.println("Successfully created admin table");
                }
            }
        }
        else
        {
            System.out.println("Can not connect to database");
        }

    }

    public synchronized String addAnAppointments(Appointment appointment) {

        if(connection!=null)
        {
            try {

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO appointment " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                preparedStatement.setString(1, String.valueOf(appointment.getAppointmentNumber()));
                preparedStatement.setString(2, appointment.getName());
                preparedStatement.setString(3, appointment.getEmail());
                preparedStatement.setString(4, appointment.getPhoneNumber());
                preparedStatement.setDate(5, appointment.getAppointMakingDate());
                preparedStatement.setString(6, appointment.getAppointmentTime());
                preparedStatement.setString(7, appointment.getSelectedService());
                preparedStatement.setDate(8, appointment.getAppointmentDate());
                preparedStatement.setString(9, appointment.getRemark());
                preparedStatement.setString(10, appointment.getStatus());
                preparedStatement.setDate(11, appointment.getRemarkDate());

                if(preparedStatement.executeUpdate()>0)
                {
                    return "Successfully made an appointment. Appointment number is: " + appointment.getAppointmentNumber();
                }
                else
                {
                    return "Can not make an appointment, please try again";
                }

            } catch (Exception e) {
                System.out.println("Error occurred. Error is:- "+e.getMessage());
                return "Can not make an appointment, please try again";
            }
        }
        else
        {
            System.out.println("Can not connect to database, please try again");
            return "Can not make an appointment, please try again";
        }
    }


    public synchronized void deleteAppointment(String appointmentNumber) throws SQLException {
        if(connection!=null)
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM appointment WHERE appointmentId = ?");
            preparedStatement.setString(1, appointmentNumber);

            System.out.println(preparedStatement);

            try {
                preparedStatement.executeUpdate();
                System.out.println("Appointment deleted successfully!");
            } catch (SQLException e) {
                System.out.println("Error while deleting appointment, Error is: " + e.getMessage());
            }
        }
        else
        {
            System.out.println("Can not connect to database, please try again");
        }
    }

    public List<Appointment> getAllAppointments() {

        List<Appointment> appointments = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM appointment";

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




    public synchronized String addNewService(Service service) throws SQLException {

        if(connection != null)
        {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO service VALUES (?, ?)");

            preparedStatement.setString(1, service.getServiceName());
            preparedStatement.setDouble(2, service.getServicePrice());

            preparedStatement.executeUpdate();
            return "Successfully added new service";
        }
        else
        {
            return "Can not connect to database";
        }
    }

    public synchronized List<String> getServicesName() {

        if(connection!=null)
        {
            try {

                List<String> serviceList = new ArrayList<>();

                Statement statement = connection.createStatement();

                String query = "SELECT * FROM service";

                ResultSet resultSet = statement.executeQuery(query);

                while(resultSet.next())
                {
                    serviceList.add(resultSet.getString("serviceName"));
                }

                return serviceList;

            } catch (Exception e) {
                return null;
            }
        }
        else
        {
            System.out.println("Can not connect to database, please try again");
            return null;
        }
    }

    public synchronized List<Service> getAllServicesRecords() {

        if(connection!=null)
        {
            try {

                List<Service> serviceList = new ArrayList<>();

                Statement statement = connection.createStatement();

                String query = "SELECT * FROM service";

                ResultSet resultSet = statement.executeQuery(query);

                while(resultSet.next())
                {
                    Service service = new Service();

                    service.setId(resultSet.getInt("id"));
                    service.setServiceName(resultSet.getString("serviceName"));
                    service.setServicePrice(resultSet.getDouble("servicePrice"));

                    serviceList.add(service);
                }

                return serviceList;

            } catch (Exception e) {
                return null;
            }
        }
        else
        {
            System.out.println("Can not connect to database, please try again");
            return null;
        }
    }

    public synchronized List<String> getTimeSlots() {

        if(connection!=null)
        {
            try {

                List<String> timeSlotList = new ArrayList<>();

                Statement statement = connection.createStatement();

                String query = "SELECT * FROM timeSlots";

                ResultSet resultSet = statement.executeQuery(query);

                while(resultSet.next())
                {
                    timeSlotList.add(resultSet.getString("timeSlots"));
                }

                return timeSlotList;

            } catch (Exception e) {
                return null;
            }
        }
        else
        {
            System.out.println("Can not connect to database, please try again");
            return null;
        }
    }


}
