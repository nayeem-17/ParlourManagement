package javaFxControllers;

import entities.Service;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServicesController implements Initializable {

    @FXML
    private TableView<Service> servicesTable;

    @FXML
    private TableColumn<Service, Integer> serviceId;

    @FXML
    private TableColumn<Service, String> serviceName;

    @FXML
    private TableColumn<Service, Double> serviceCharge;

    @FXML
    void goToAboutUs(ActionEvent event) {

    }

    @FXML
    void goToAdmin(ActionEvent event) {

    }

    @FXML
    void goToContactUs(ActionEvent event) {

    }

    @FXML
    void goToHome(ActionEvent event) {

    }

    @FXML
    void goToService(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeColumns();
        populateServiceTable();
    }

    private void populateServiceTable() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                final List<Service> servicesList = dbOperations.DbServices.getInstance()
                        .getAllServicesRecords();

                if (servicesList.isEmpty()) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            showAlert(Alert.AlertType.ERROR, "Failed to fetch services", "Failed to get servies, please try again");
                        }
                    });
                } else {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            servicesTable.setItems(FXCollections.observableList(servicesList));
                            servicesTable.refresh();
                        }
                    });
                }
            }
        }).start();
    }

    private void initializeColumns() {
        serviceId.setCellValueFactory(new PropertyValueFactory<Service, Integer>("id"));
        serviceName.setCellValueFactory(new PropertyValueFactory<Service, String>("serviceName"));
        serviceCharge.setCellValueFactory(new PropertyValueFactory<Service, Double>("servicePrice"));
    }

    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
