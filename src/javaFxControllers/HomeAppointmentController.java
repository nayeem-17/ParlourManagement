package javaFxControllers;

import controllers.AptController;
import dbOperations.DbServices;
import entities.Appointment;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Service;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeAppointmentController {

    DbServices dbServices = DbServices.getInstance(); //Get the database service class

    @FXML
    private BorderPane homeBorderPane;

    @FXML
    private TextField appointmentMakerName;

    @FXML
    private TextField appointmentMakerEmail;

    @FXML
    private ChoiceBox<String> selectedService;

    @FXML
    private Text selectServices;

    @FXML
    private DatePicker appointmentDate;

    @FXML
    private TextField appointmantMakerPhone;

    @FXML
    private ChoiceBox<String> appointmentTimeChoiceBox;

    @FXML
    private Text selectAppointmentTimeText;

    @FXML
    public void initialize() {
        //Firing new thread to keep UI safe from hanging 
        new Thread(new Runnable() {

            @Override
            public void run() {

                final List<Service> services = dbServices.getAllServicesRecords();

                if (!services.isEmpty()) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            List<String> servicesNamesList = new ArrayList<>();
                            for (Service s : services) servicesNamesList.add(s.getServiceName());
                            selectedService.getItems().addAll(servicesNamesList);
                            selectedService.setValue(servicesNamesList.get(0)); //Add the first item as a default value
                            selectServices.setVisible(false);
                        }
                    });
                } else {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            showAlert(Alert.AlertType.ERROR, "Can not get services", "Can not get services,please try again");
                        }
                    });
                }
            }
        }
        ).start();

        //Fetching time slots on a new thread.
        new Thread(new Runnable() {
            @Override
            public void run() {

                final List<String> timeslots = dbServices.getTimeSlots();

                System.out.println(timeslots);

                if (!timeslots.isEmpty()) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            appointmentTimeChoiceBox.getItems().addAll(timeslots);
                            appointmentTimeChoiceBox.setValue(timeslots.get(0));
                            selectAppointmentTimeText.setVisible(false);
                        }

                    });
                } else {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            showAlert(Alert.AlertType.ERROR, "Can not get time slots", "Can not get time slots,please try again");
                        }
                    });
                }
            }
        }).start();
    }

    @FXML
    void aboutUs(ActionEvent event
    ) {

    }

    @FXML
    void admin(ActionEvent event) throws InterruptedException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxmls/AdminSigninPage.fxml"));


        Stage stage = (Stage) homeBorderPane.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene((Parent) loader.load(), 1180, 627);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
    }

    @FXML
    void contactUs(ActionEvent event
    ) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxmls/AdminTimeSlots.fxml"));

        Stage stage = (Stage) selectServices.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene((Parent) loader.load(), 1114, 627);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
    }

    @FXML
    void homeScreen(ActionEvent event
    ) {

    }

    @FXML
    void services(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/Services.fxml"));

        Stage primaryStage = (Stage) homeBorderPane.getScene().getWindow();

        primaryStage.getScene().setRoot(root);
    }

    @FXML
    void makeAnAppointment(ActionEvent event) {
        validateAppointmentInput();
    }

    private void validateAppointmentInput() {
        if (appointmentMakerName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Appointment maker name can not be empty", "Please enter your name");
        } else if (appointmentDate.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Appointment can not be empty", "Plese enter appoinment date");
        } else if (appointmantMakerPhone.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Appoint maker phone number can not be empty", "Please enter your mobile number");
        } else if (appointmentDate.getValue().isBefore(LocalDate.now())) {
            showAlert(Alert.AlertType.ERROR, "Date selection error", "Appointment selection date can not be before current date");
        } else if (appointmantMakerPhone.getText().length() < 11) {
            showAlert(Alert.AlertType.ERROR, "Phone Number invalid", "Please enter a valid phone number");
        } else {
            //Firing new thread for smooth operations
            new Thread(new Runnable() {
                @Override
                public void run() {
                    saveToDatabase();
                }
            }).start();
        }
    }

    private void saveToDatabase() {
        Appointment appointment = new Appointment();

        appointment.setAppointmentNumber(UUID.randomUUID().toString());
        appointment.setName(appointmentMakerName.getText());
        appointment.setEmail(appointmentMakerEmail.getText());
        appointment.setPhoneNumber(appointmantMakerPhone.getText());
        appointment.setAppointMakingDate(LocalDate.now().toString());
        appointment.setAppointmentTime(appointmentTimeChoiceBox.getSelectionModel().getSelectedItem());
        appointment.setSelectedService(selectedService.getSelectionModel().getSelectedItem());
        appointment.setAppointmentDate(appointmentDate.getValue().toString());

        final boolean response = AptController.addAppointment(appointment);

        if (response == false) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    showAlert(Alert.AlertType.ERROR, "Error making an appointment", "error occured");
                }
            });
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    showAlert(Alert.AlertType.CONFIRMATION, "Successfull", "Successfully added");
                }
            });
        }
    }

    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
//javaFxControllers.HomeAppointmentController.showAlert