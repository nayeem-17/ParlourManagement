/**
 * Sample Skeleton for 'AddCustomer.fxml' Controller Class
 */

package javaFxControllers;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.CustomerSignUp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddCustomer {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="nameField"
    private TextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="emailField"
    private TextField emailField; // Value injected by FXMLLoader

    @FXML // fx:id="mobileField"
    private TextField mobileField; // Value injected by FXMLLoader

    @FXML // fx:id="addButton"
    private Button addButton; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert mobileField != null : "fx:id=\"mobileField\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'AddCustomer.fxml'.";
        addButton.setOnAction(e -> {
            if (CustomerSignUp.signup(nameField.getText(), emailField.getText(), mobileField.getText())) {
                HomeAppointmentController.showAlert(Alert.AlertType.CONFIRMATION, "Successful", "Successfully SignedUp");
            } else {
                HomeAppointmentController.showAlert(Alert.AlertType.ERROR, "Error", "Error occured");
            }
        });
    }
}
