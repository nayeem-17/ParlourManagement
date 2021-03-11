/**
 * Sample Skeleton for 'Dashboard.fxml' Controller Class
 */

package javaFxControllers;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Dashboard {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="borderPane"
    private BorderPane borderPane; // Value injected by FXMLLoader

    @FXML // fx:id="dashboard"
    private Button dashboard; // Value injected by FXMLLoader

    @FXML // fx:id="service"
    private Button service; // Value injected by FXMLLoader

    @FXML // fx:id="appointment"
    private Button appointment; // Value injected by FXMLLoader

    @FXML // fx:id="addCustomer"
    private Button addCustomer; // Value injected by FXMLLoader

    @FXML // fx:id="customerList"
    private Button customerList; // Value injected by FXMLLoader

    @FXML // fx:id="report"
    private Button report; // Value injected by FXMLLoader

    @FXML // fx:id="anchPane"
    private AnchorPane anchPane; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert borderPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert dashboard != null : "fx:id=\"dashboard\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert service != null : "fx:id=\"service\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert appointment != null : "fx:id=\"appointment\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert addCustomer != null : "fx:id=\"addCustomer\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert customerList != null : "fx:id=\"customerList\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert report != null : "fx:id=\"report\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert anchPane != null : "fx:id=\"anchPane\" was not injected: check your FXML file 'Dashboard.fxml'.";

        dashboard.setOnAction(e -> {
            new Thread(() -> {
                try {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("fxmls/adminDashboard.fxml"));
                    anchPane.getChildren().setAll(pane);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }).start();
        });
        service.setOnAction(e -> {
        });
        appointment.setOnAction(e -> {
        });
        addCustomer.setOnAction(e -> {
        });
        customerList.setOnAction(e -> {
        });
        report.setOnAction(e -> {
        });
    }
}
