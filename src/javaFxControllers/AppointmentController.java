/**
 * Sample Skeleton for 'Appointment.fxml' Controller Class
 */

package javaFxControllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Service;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AppointmentController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="serviceTable"
    private TableView<Service> serviceTable; // Value injected by FXMLLoader

    @FXML // fx:id="appointmentId"
    private TableColumn<?, ?> appointmentId; // Value injected by FXMLLoader

    @FXML // fx:id="name"
    private TableColumn<?, ?> name; // Value injected by FXMLLoader

    @FXML // fx:id="mobilenumber"
    private TableColumn<?, ?> mobilenumber; // Value injected by FXMLLoader

    @FXML // fx:id="appDate"
    private TableColumn<?, ?> appDate; // Value injected by FXMLLoader

    @FXML // fx:id="appTime"
    private TableColumn<?, ?> appTime; // Value injected by FXMLLoader

    @FXML // fx:id="rejected"
    private TableColumn<?, ?> rejected; // Value injected by FXMLLoader

    @FXML // fx:id="accepted"
    private TableColumn<?, ?> accepted; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert serviceTable != null : "fx:id=\"serviceTable\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert appointmentId != null : "fx:id=\"appointmentId\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert mobilenumber != null : "fx:id=\"mobilenumber\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert appDate != null : "fx:id=\"appDate\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert appTime != null : "fx:id=\"appTime\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert rejected != null : "fx:id=\"rejected\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert accepted != null : "fx:id=\"accepted\" was not injected: check your FXML file 'Appointment.fxml'.";

    }
}
