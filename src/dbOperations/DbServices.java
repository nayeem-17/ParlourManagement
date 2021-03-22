package dbOperations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entities.Service;
import entities.TimeSlots;

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

    public Connection getConnection() {
        return connection;
    }

    private void createTablesIfNotExists() throws SQLException
    {
        if(connection!=null)
        {
            Statement statement = connection.createStatement();

            //create appointment table if not exists
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

            //create time slots table if not exists
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

            //create service table if not exists
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

            //create admin table if not exists
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

            //create customer table if not exists
            {
                String createCustomer =
                        "CREATE TABLE Customer(username VARCHAR(255) NOT NULL, " +
                        "email varchar(255) NOT NULL, mobile varchar(20), creationDate varchar(255) NOT NULL)";

                ResultSet customerTable = connection.getMetaData().getTables(null, null,
                        "Customer", null);

                if (customerTable.next()) {
                    System.out.println("Customer table already exists");
                } else {
                    statement.executeUpdate(createCustomer);
                    System.out.println("Successfully created customer table");
                }
            }

            //create invoice table if not exists
            {
                String createInvoice =
                        "CREATE TABLE Invoice(" +
                                "customername varchar(255) NOT NULL, servicename varchar(255), " +
                                "serviceprice varchar(255), date varchar(255), id varchar(255))";

                ResultSet createInvoiceTable = connection.getMetaData().getTables(null, null,
                        "Invoice", null);

                if (createInvoiceTable.next()) {
                    System.out.println("Invoice table already exists");
                } else {
                    statement.executeUpdate(createInvoice);
                    System.out.println("Successfully created invoice table");
                }
            }

        }
        else
        {
            System.out.println("Can not connect to database");
        }

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


}
