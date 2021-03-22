/**
 * Sample Skeleton for 'AddServices.fxml' Controller Class
 */

package javaFxControllers;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import controllers.AddServiceController;
import entities.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddService {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="serviceName"
    private TextField serviceName; // Value injected by FXMLLoader

    @FXML // fx:id="servicePrice"
    private TextField servicePrice; // Value injected by FXMLLoader

    @FXML // fx:id="addService"
    private Button addService; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert serviceName != null : "fx:id=\"serviceName\" was not injected: check your FXML file 'AddServices.fxml'.";
        assert servicePrice != null : "fx:id=\"servicePrice\" was not injected: check your FXML file 'AddServices.fxml'.";
        assert addService != null : "fx:id=\"addService\" was not injected: check your FXML file 'AddServices.fxml'.";

        addService.setOnAction(e -> {
            /*
            Service service = new Service();
            service.setServiceName(serviceName.getText());
            service.setServicePrice(servicePrice.getText());
            Random random = new Random();
            service.setId(Math.abs(random.nextInt()) + "");
            AddServiceController.addService(service);
            HomeAppointmentController.showAlert(Alert.AlertType.CONFIRMATION, "Successful", "Successfully created!");

             */
        });
    }
}
