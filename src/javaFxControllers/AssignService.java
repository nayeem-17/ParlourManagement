/**
 * Sample Skeleton for 'AssignService.fxml' Controller Class
 */

package javaFxControllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import controllers.InvoiceController;
import dbOperations.DbServices;
import entities.AssignS;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AssignService {
    private Customer customer;
    InvoiceData invoiceData;
    @FXML // fx:id="pane"
    private AnchorPane pane; // Value injected by FXMLLoader

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="assignTable"
    private TableView<AssignS> assignTable; // Value injected by FXMLLoader

    @FXML // fx:id="serviceName"
    private TableColumn<AssignS, String> serviceName; // Value injected by FXMLLoader

    @FXML // fx:id="servicePrice"
    private TableColumn<AssignS, String> servicePrice; // Value injected by FXMLLoader

    @FXML // fx:id="status"
    private TableColumn<AssignS, String> status; // Value injected by FXMLLoader

    @FXML // fx:id="addButton"
    private Button addButton; // Value injected by FXMLLoader

    @FXML // fx:id="removeButton"
    private Button removeButton; // Value injected by FXMLLoader

    @FXML // fx:id="doneButton"
    private Button doneButton; // Value injected by FXMLLoader

    ObservableList<AssignS> observableList;

    public void AssignService() {
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.invoiceData = new InvoiceData(customer.getName());
        System.out.println(customer);
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert assignTable != null : "fx:id=\"assignTable\" was not injected: check your FXML file 'AssignService.fxml'.";
        assert serviceName != null : "fx:id=\"serviceName\" was not injected: check your FXML file 'AssignService.fxml'.";
        assert servicePrice != null : "fx:id=\"servicePrice\" was not injected: check your FXML file 'AssignService.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'AssignService.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'AssignService.fxml'.";
        assert removeButton != null : "fx:id=\"removeButton\" was not injected: check your FXML file 'AssignService.fxml'.";
        assert doneButton != null : "fx:id=\"doneButton\" was not injected: check your FXML file 'AssignService.fxml'.";
        getItems();

        serviceName.setCellValueFactory(new PropertyValueFactory<AssignS, String>("serviceName"));
        servicePrice.setCellValueFactory(new PropertyValueFactory<AssignS, String>("servicePrice"));
        status.setCellValueFactory(new PropertyValueFactory<AssignS, String>("status"));

        assignTable.setItems(observableList);

        addButton.setOnAction(e -> {
            int index = assignTable.getSelectionModel().getSelectedIndex();
            observableList.get(index).setStatus("added");
            if (!invoiceData.getAssignSList().contains(assignTable.getSelectionModel().getSelectedItem()))
                this.invoiceData.getAssignSList().add(assignTable.getSelectionModel().getSelectedItem());
            assignTable.refresh();
        });
        removeButton.setOnAction(e -> {
            int index = assignTable.getSelectionModel().getSelectedIndex();
            observableList.get(index).setStatus("not added");
            this.invoiceData.getAssignSList().remove(assignTable.getSelectionModel().getSelectedItem());
            assignTable.refresh();
        });
        doneButton.setOnAction(e -> {
//            inserting invoice data
            System.out.println(invoiceData);
            InvoiceController.insertInvoice(this.invoiceData);

//            Return to prev scene
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

    private void getItems() {
        observableList = FXCollections.observableArrayList();
        List<Service> serviceList = DbServices.getInstance().getAllServicesRecords();
        for (Service s : serviceList) {
            AssignS temp = new AssignS(s.getServiceName(), String.valueOf(s.getServicePrice()));
            observableList.add(temp);
        }
    }
}
