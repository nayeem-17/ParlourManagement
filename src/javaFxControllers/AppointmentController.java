/**
 * Sample Skeleton for 'Appointment.fxml' Controller Class
 */

package javaFxControllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import controllers.AptController;
import dbOperations.DbServices;
import entities.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AppointmentController {

    ObservableList<Appointment> allAppointments;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="serviceTable"
    private TableView<Appointment> serviceTable; // Value injected by FXMLLoader

    @FXML // fx:id="appointmentId"
    private TableColumn<Appointment, String> appointmentId; // Value injected by FXMLLoader

    @FXML // fx:id="name"
    private TableColumn<Appointment, String> name; // Value injected by FXMLLoader

    @FXML // fx:id="mobilenumber"
    private TableColumn<Appointment, String> mobilenumber; // Value injected by FXMLLoader

    @FXML // fx:id="appDate"
    private TableColumn<Appointment, String> appDate; // Value injected by FXMLLoader

    @FXML // fx:id="appTime"
    private TableColumn<Appointment, String> appTime; // Value injected by FXMLLoader

    @FXML // fx:id="serviceId"
    private TableColumn<Appointment, String> serviceId; // Value injected by FXMLLoader

    @FXML // fx:id="status"
    private TableColumn<Appointment, String> status; // Value injected by FXMLLoader

    @FXML // fx:id="acceptButton"
    private Button acceptButton; // Value injected by FXMLLoader

    @FXML // fx:id="deleteButton"
    private Button deleteButton; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert serviceTable != null : "fx:id=\"serviceTable\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert appointmentId != null : "fx:id=\"appointmentId\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert mobilenumber != null : "fx:id=\"mobilenumber\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert appDate != null : "fx:id=\"appDate\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert appTime != null : "fx:id=\"appTime\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert status != null : "fx:id=\"rejected\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert serviceId != null : "fx:id=\"accepted\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert acceptButton != null : "fx:id=\"acceptButton\" was not injected: check your FXML file 'Appointment.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'Appointment.fxml'.";

        getItems();

        appointmentId.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentNumber"));
        name.setCellValueFactory(new PropertyValueFactory<Appointment, String>("name"));
        mobilenumber.setCellValueFactory(new PropertyValueFactory<Appointment, String>("phoneNumber"));
        appDate.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentDate"));
        appTime.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentTime"));
        serviceId.setCellValueFactory(new PropertyValueFactory<Appointment, String>("selectedService"));
        status.setCellValueFactory(new PropertyValueFactory<Appointment, String>("status"));

        serviceTable.setItems(allAppointments);

        deleteButton.setOnAction(e -> {

            int index = serviceTable.getSelectionModel().getSelectedIndex();
            System.out.println("deleting at in index " + index);
            System.out.println(allAppointments.get(index).getName());
            AptController.deleteAppointment(allAppointments.get(index).getAppointmentNumber());
            allAppointments.remove(index);
        });

        acceptButton.setOnAction(e -> {
            int index = serviceTable.getSelectionModel().getSelectedIndex();
            allAppointments.get(index).setStatus("accepted");
            AptController.updateStatus(allAppointments.get(index).getAppointmentNumber());
            serviceTable.refresh();
        });
    }

    private ObservableList<Appointment> getItems() {
        this.allAppointments = FXCollections.observableArrayList();
        List<Appointment> temp = DbServices.getInstance().getAllAppointments();
        for (Appointment apt : temp) {
            System.out.println(apt);
            allAppointments.add(apt);
        }
        return allAppointments;
    }
}
