/**
 * Sample Skeleton for 'ServiceTable.fxml' Controller Class
 */

package javaFxControllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dbOperations.DbServices;
import entities.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServiceTable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="servicesTable"
    private TableView<Service> servicesTable; // Value injected by FXMLLoader

    @FXML // fx:id="serviceId"
    private TableColumn<Service, String> serviceId; // Value injected by FXMLLoader

    @FXML // fx:id="serviceName"
    private TableColumn<Service, String> serviceName; // Value injected by FXMLLoader

    @FXML // fx:id="serviceCharge"
    private TableColumn<Service, String> serviceCharge; // Value injected by FXMLLoader

    ObservableList<Service> serviceObservableList;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert servicesTable != null : "fx:id=\"servicesTable\" was not injected: check your FXML file 'ServiceTable.fxml'.";
        assert serviceId != null : "fx:id=\"serviceId\" was not injected: check your FXML file 'ServiceTable.fxml'.";
        assert serviceName != null : "fx:id=\"serviceName\" was not injected: check your FXML file 'ServiceTable.fxml'.";
        assert serviceCharge != null : "fx:id=\"serviceCharge\" was not injected: check your FXML file 'ServiceTable.fxml'.";
        getItems();

        serviceId.setCellValueFactory(new PropertyValueFactory<Service, String>("id"));
        serviceName.setCellValueFactory(new PropertyValueFactory<Service, String>("serviceName"));
        serviceCharge.setCellValueFactory(new PropertyValueFactory<Service, String>("servicePrice"));

        servicesTable.setItems(serviceObservableList);
    }

    private ObservableList<Service> getItems() {
        this.serviceObservableList = FXCollections.observableArrayList();
        List<Service> allList = DbServices.getInstance().getAllServicesRecords();
        for (Service s : allList) serviceObservableList.add(s);
        return serviceObservableList;
    }
}
