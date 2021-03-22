package javaFxControllers;

import java.util.List;
import dbOperations.AppointmentOperations;
import dbOperations.CustomerOperations;
import dbOperations.DbServices;
import entities.Appointment;
import entities.Customer;
import entities.Service;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class admindashboard {

    private final AppointmentOperations appointmentOperations = new AppointmentOperations();
    private final CustomerOperations customerOperations = new CustomerOperations();

    @FXML
    public AnchorPane adminPane;

    @FXML
    private javafx.scene.text.Text customerNumber;

    @FXML
    private javafx.scene.text.Text appointmentNumber;

    @FXML
    private javafx.scene.text.Text acceptedApt;

    @FXML
    private javafx.scene.text.Text rejectedApts;

    @FXML
    private javafx.scene.text.Text totalServices;


    @FXML
    void initialize() {

        new Thread(() -> {
            List<Customer> customers = customerOperations.getCustomer();
            List<Appointment> appointments = appointmentOperations.getAllAppointments();

            Platform.runLater(() -> {
                customerNumber.setText(customers.size() + "");
                appointmentNumber.setText(appointments.size() + "");

                int accepted = 0;

                for (Appointment a : appointments) {

                    if (a.getStatus().equals("accepted"))
                        accepted++;

                }

                acceptedApt.setText(accepted + "");
                rejectedApts.setText((appointments.size() - accepted) + "");
                List<Service> services = DbServices.getInstance().getAllServicesRecords();
                totalServices.setText(services.size() + "");
            });

        }).start();
    }
}
