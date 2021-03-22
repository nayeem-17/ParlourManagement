package javaFxControllers;

import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Dashboard {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button dashboard;

    @FXML
    private Button service;

    @FXML
    private Button appointment;

    @FXML
    private Button addCustomer;

    @FXML
    private Button customerList;

    @FXML
    private Button addService;

    @FXML
    private Button invoice;


    @FXML
    void initialize() {

        try {
            Pane view = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/AdminDashboard.fxml")));
            borderPane.setCenter(view);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        dashboard.setOnAction(e -> {
            try {
                Pane view = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/AdminDashboard.fxml")));
                borderPane.setCenter(view);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        addService.setOnAction(e -> {
            try {
                Pane view = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/AddServices.fxml")));
                borderPane.setCenter(view);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        service.setOnAction(e -> {

            try {
                Pane view = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/ServiceTable.fxml")));
                borderPane.setCenter(view);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        appointment.setOnAction(e -> {
            try {
                Pane view = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/Appointment.fxml")));
                borderPane.setCenter(view);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        addCustomer.setOnAction(e -> {
            try {
                Pane view = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/AddCustomer.fxml")));
                borderPane.setCenter(view);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        customerList.setOnAction(e -> {
            try {
                Pane view = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/CustomerList.fxml")));
                borderPane.setCenter(view);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        invoice.setOnAction(e -> {
            try {
                Pane view = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/Invoice.fxml")));
                borderPane.setCenter(view);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    }

}
