/**
 * Sample Skeleton for 'AdminDashboard.fxml' Controller Class
 */

package javaFxControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class admindashboard {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="customerNumber"
    private Label customerNumber; // Value injected by FXMLLoader

    @FXML // fx:id="appointmentNumber"
    private Label appointmentNumber; // Value injected by FXMLLoader

    @FXML // fx:id="acceptedApt"
    private Label acceptedApt; // Value injected by FXMLLoader

    @FXML // fx:id="rejectedApts"
    private Label rejectedApts; // Value injected by FXMLLoader

    @FXML // fx:id="totalServices"
    private Label totalServices; // Value injected by FXMLLoader

    @FXML // fx:id="todaySales"
    private Label todaySales; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert customerNumber != null : "fx:id=\"customerNumber\" was not injected: check your FXML file 'AdminDashboard.fxml'.";
        assert appointmentNumber != null : "fx:id=\"appointmentNumber\" was not injected: check your FXML file 'AdminDashboard.fxml'.";
        assert acceptedApt != null : "fx:id=\"acceptedApt\" was not injected: check your FXML file 'AdminDashboard.fxml'.";
        assert rejectedApts != null : "fx:id=\"rejectedApts\" was not injected: check your FXML file 'AdminDashboard.fxml'.";
        assert totalServices != null : "fx:id=\"totalServices\" was not injected: check your FXML file 'AdminDashboard.fxml'.";
        assert todaySales != null : "fx:id=\"todaySales\" was not injected: check your FXML file 'AdminDashboard.fxml'.";

    }
}
