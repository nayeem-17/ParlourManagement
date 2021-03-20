package javaFxControllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import controllers.AptController;
import dbOperations.DbServices;
import entities.Appointment;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AppointmentController {

    private final DbServices dbServices = DbServices.getInstance();

    ObservableList<Appointment> allAppointments;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Appointment> serviceTable;

    @FXML
    private TableColumn<Appointment, String> appointmentId;

    @FXML
    private TableColumn<Appointment, String> name;

    @FXML
    private TableColumn<Appointment, String> mobilenumber;

    @FXML
    private TableColumn<Appointment, String> appDate;

    @FXML
    private TableColumn<Appointment, String> appTime;

    @FXML
    private TableColumn<Appointment, String> serviceId;

    @FXML
    private TableColumn<Appointment, String> status;

    @FXML
    private Button acceptButton;

    @FXML
    private Button deleteButton;

    @FXML

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

        appointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentNumber"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        mobilenumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        appDate.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        appTime.setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));
        serviceId.setCellValueFactory(new PropertyValueFactory<>("selectedService"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        serviceTable.setItems(allAppointments);

        deleteButton.setOnAction(e -> {
            int index = serviceTable.getSelectionModel().getSelectedIndex();
            System.out.println("deleting at in index " + index);
            System.out.println(allAppointments.get(index).getName());

            new Thread(() -> {
                try {
                    dbServices.deleteAppointment(String.valueOf(allAppointments.get(index).getAppointmentNumber()));

                    Platform.runLater(() -> {
                        allAppointments.remove(index);
                    });

                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }).start();
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
