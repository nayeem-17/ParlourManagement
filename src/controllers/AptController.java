package controllers;

import dbOperations.DbServices;
import entities.Appointment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AptController {
    public static boolean addAppointment(Appointment appointment) {

        try {
            Connection connection = DbServices.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Appointment VALUES ( '" + appointment.getAppointmentNumber().toString()
                    + "','" + appointment.getName() + "','" + appointment.getEmail() + "','" + appointment.getPhoneNumber()
                    + "','" + appointment.getSelectedService() + "','" + appointment.getAppointmentDate() + "','" + appointment.getAppointmentTime()
                    + "','" + appointment.getAppointMakingDate() + "','pending');";
            statement.executeUpdate(query);
            System.out.println(query);
            System.out.println("Inserted successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error in Adding Appointment is " + e.getMessage());
            return false;
        }
    }

    public synchronized static boolean deleteAppointment(String appointmentNumber) {
        String query = "DELETE FROM Appointment WHERE " + "'appointment id' = " + "'" + appointmentNumber + "';";
        System.out.println(query);
        try {
            Connection connection = DbServices.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            System.out.println("Deleted successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error in databasecontroller is " + e.getMessage());
            return false;
        }
    }

    public synchronized static boolean updateStatus(String appointmentNumber) {
        String query = "UPDATE Appointment SET status='accepted' WHERE " + "'appointment id' = " + "'" + appointmentNumber + "';";
        System.out.println(query);
        try {
            Connection connection = DbServices.getInstance().getConnection();
            Statement statement = connection.createStatement();
            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Updated successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error in databasecontroller is " + e.getMessage());
            return false;
        }
    }
//    public synchronized static List<Appointment> getAppointments() {
//        List<Appointment> appointments = new ArrayList<>();
//        try {
//            Connection connection = DbServices.getInstance().getConnection();
//            Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//            String query = "SELECT * FROM Appointment";
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                Appointment appointment = new Appointment();
//                appointment.setAppointmentNumber(rs.getString("appointment id"));
//                appointment.setName(rs.getString("name"));
//                appointment.setEmail(rs.getString("email"));
//                appointment.setAppointMakingDate(rs.getString("create at"));
//                appointment.setPhoneNumber(rs.getString("mobile"));
//                appointment.setSelectedService(rs.getString("service"));
//                appointment.setAppointmentDate(rs.getString("date"));
//                appointment.setAppointmentTime(rs.getString("time"));
//                appointment.setStatus(rs.getString("status"));
//
//                appointments.add(appointment);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return appointments;
//    }

    public static void main(String[] args) {
        String id="d769e074-9e54-4a30-8941-3c5f6b8ee0e6";
        updateStatus(id);
    }
}