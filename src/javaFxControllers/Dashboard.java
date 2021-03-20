/**
 * Sample Skeleton for 'Dashboard.fxml' Controller Class
 */

package javaFxControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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

    @FXML // fx:id="anchPane"
    private AnchorPane anchPane; // Value injected by FXMLLoader

    @FXML // fx:id="addService"
    private Button addService; // Value injected by FXMLLoader

    @FXML // fx:id="invoice"
    private Button invoice; // Value injected by FXMLLoader


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert borderPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert dashboard != null : "fx:id=\"dashboard\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert addService != null : "fx:id=\"addService\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert service != null : "fx:id=\"service\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert appointment != null : "fx:id=\"appointment\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert addCustomer != null : "fx:id=\"addCustomer\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert customerList != null : "fx:id=\"customerList\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert anchPane != null : "fx:id=\"anchPane\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert invoice != null : "fx:id=\"invoice\" was not injected: check your FXML file 'Dashboard.fxml'.";

        try {
            Pane view = new FXMLLoader().load(getClass().getClassLoader().getResource("fxmls/AdminDashboard.fxml"));
            borderPane.setCenter(view);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        dashboard.setOnAction(e -> {

            try {
                Pane view = new FXMLLoader().load(getClass().getClassLoader().getResource("fxmls/AdminDashboard.fxml"));
                borderPane.setCenter(view);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

//
//            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxmls/AdminDashboard.fxml"));
//            Stage stage = (Stage) borderPane.getScene().getWindow();
//            Scene scene = null;
//            try {
//                scene = new Scene((Parent) loader.load(), 1180, 627);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//            stage.setScene(scene);

        });
        addService.setOnAction(e -> {
            try {
                Pane view = new FXMLLoader().load(getClass().getClassLoader().getResource("fxmls/AddServices.fxml"));
                borderPane.setCenter(view);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
//
//            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxmls/AddServices.fxml"));
//            Stage stage = (Stage) borderPane.getScene().getWindow();
//            Scene scene = null;
//            try {
//                scene = new Scene((Parent) loader.load(), 1180, 627);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//            stage.setScene(scene);
        });
        service.setOnAction(e -> {

            try {
                Pane view = new FXMLLoader().load(getClass().getClassLoader().getResource("fxmls/ServiceTable.fxml"));
                borderPane.setCenter(view);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

//            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxmls/ServiceTable.fxml"));
//            Stage stage = (Stage) borderPane.getScene().getWindow();
//            Scene scene = null;
//            try {
//                scene = new Scene((Parent) loader.load(), 1180, 627);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//            stage.setScene(scene);
        });
        appointment.setOnAction(e -> {

            try {
                Pane view = new FXMLLoader().load(getClass().getClassLoader().getResource("fxmls/Appointment.fxml"));
                borderPane.setCenter(view);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


//            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxmls/Appointment.fxml"));
//            Stage stage = (Stage) borderPane.getScene().getWindow();
//            Scene scene = null;
//            try {
//                scene = new Scene((Parent) loader.load(), 1180, 627);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//            stage.setScene(scene);
        });
        addCustomer.setOnAction(e -> {


            try {
                Pane view = new FXMLLoader().load(getClass().getClassLoader().getResource("fxmls/AddCustomer.fxml"));
                borderPane.setCenter(view);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
//
//            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxmls/AddCustomer.fxml"));
//            Stage stage = (Stage) borderPane.getScene().getWindow();
//            Scene scene = null;
//            try {
//                scene = new Scene((Parent) loader.load(), 1180, 627);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//            stage.setScene(scene);
        });
        customerList.setOnAction(e -> {
            try {
                Pane view = new FXMLLoader().load(getClass().getClassLoader().getResource("fxmls/CustomerList.fxml"));
                borderPane.setCenter(view);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

//            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxmls/CustomerList.fxml"));
//            Stage stage = (Stage) borderPane.getScene().getWindow();
//            Scene scene = null;
//            try {
//                scene = new Scene((Parent) loader.load(), 1180, 627);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//            stage.setScene(scene);
        });

        invoice.setOnAction(e -> {
            try {
                Pane view = new FXMLLoader().load(getClass().getClassLoader().getResource("fxmls/Invoice.fxml"));
                borderPane.setCenter(view);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

//            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxmls/Invoice.fxml"));
//            Stage stage = (Stage) borderPane.getScene().getWindow();
//            Scene scene = null;
//            try {
//                scene = new Scene((Parent) loader.load(), 1180, 627);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//            stage.setScene(scene);
        });
    }

}
