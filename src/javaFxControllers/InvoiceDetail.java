/**
 * Sample Skeleton for 'InvoiceDetail.fxml' Controller Class
 */

package javaFxControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controllers.InvoiceController;
import dbOperations.DbServices;
import entities.Customer;
import entities.InvoiceData;
import entities.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InvoiceDetail {

    private InvoiceData invoice;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="name"
    private Label name; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private Label email; // Value injected by FXMLLoader

    @FXML // fx:id="number"
    private Label number; // Value injected by FXMLLoader

    @FXML // fx:id="date"
    private Label date; // Value injected by FXMLLoader

    @FXML // fx:id="serviceTable"
    private TableView<Service> serviceTable; // Value injected by FXMLLoader

    @FXML // fx:id="serviceName"
    private TableColumn<Service, String> serviceName; // Value injected by FXMLLoader

    @FXML // fx:id="serviceCost"
    private TableColumn<Service, String> serviceCost; // Value injected by FXMLLoader

    @FXML // fx:id="totalCost"
    private Label totalCost; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="pane"
    private AnchorPane pane; // Value injected by FXMLLoader

    ObservableList<Service> observableList;

    public InvoiceDetail() {
        observableList = FXCollections.observableArrayList();
    }

    public void setInvoice(InvoiceData invoice) {
        this.invoice = invoice;
        System.out.println(this.invoice);
        getItems();
        serviceTable.setItems(observableList);
        List<Customer> customers = DbServices.getInstance().getCustomer();
        System.out.println(this.invoice);
        for (Customer c : customers) {
            if (c.getName().equals(this.invoice.getCutomerName())) {
                name.setText(this.invoice.getCutomerName());
                email.setText(c.getEmail());
                number.setText(c.getMobileNumber());
                date.setText(this.invoice.getDate());
                break;
            }
        }

    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'InvoiceDetail.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'InvoiceDetail.fxml'.";
        assert number != null : "fx:id=\"number\" was not injected: check your FXML file 'InvoiceDetail.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'InvoiceDetail.fxml'.";
        assert serviceTable != null : "fx:id=\"serviceTable\" was not injected: check your FXML file 'InvoiceDetail.fxml'.";
        assert serviceName != null : "fx:id=\"serviceName\" was not injected: check your FXML file 'InvoiceDetail.fxml'.";
        assert serviceCost != null : "fx:id=\"serviceCost\" was not injected: check your FXML file 'InvoiceDetail.fxml'.";
        assert totalCost != null : "fx:id=\"totalCost\" was not injected: check your FXML file 'InvoiceDetail.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'InvoiceDetail.fxml'.";

//        getItems();

        serviceName.setCellValueFactory(new PropertyValueFactory<Service, String>("serviceName"));
        serviceCost.setCellValueFactory(new PropertyValueFactory<Service, String>("servicePrice"));

        serviceTable.setItems(observableList);

        backButton.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxmls/Dashboard.fxml"));

            Stage stage = (Stage) pane.getScene().getWindow();
            Scene scene = null;
            try {
                scene = new Scene((Parent) loader.load(), 1180, 800);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            stage.setScene(scene);
        });
    }

    public ObservableList<Service> getItems() {
        observableList = FXCollections.observableArrayList();
        List<Service> services = InvoiceController.getServices(this.invoice.getInvoiceId());
        int tCost = 0;
        for (Service i : services) {
            tCost += Integer.valueOf(i.getServicePrice());
            observableList.add(i);
        }
        totalCost.setText(tCost + "");
        return observableList;
    }
}