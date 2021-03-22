package javaFxControllers;

import java.io.IOException;
import java.util.List;
import controllers.InvoiceController;
import dbOperations.CustomerOperations;
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
    private final CustomerOperations customerOperations = new CustomerOperations();

    @FXML
    private Label name;

    @FXML
    private Label email;

    @FXML
    private Label number;

    @FXML
    private Label date;

    @FXML
    private TableView<Service> serviceTable;

    @FXML
    private TableColumn<Service, String> serviceName;

    @FXML
    private TableColumn<Service, String> serviceCost;

    @FXML
    private Label totalCost;

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane pane;

    ObservableList<Service> observableList;

    public InvoiceDetail() {
        observableList = FXCollections.observableArrayList();
    }

    public void setInvoice(InvoiceData invoice) {

        this.invoice = invoice;
        System.out.println(this.invoice);

        getItems();


        serviceTable.setItems(observableList);

        List<Customer> customers = customerOperations.getCustomer();

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

    public void getItems() {
        observableList = FXCollections.observableArrayList();
        List<Service> services = InvoiceController.getServices(this.invoice.getInvoiceId());
        double tCost = 0;
        for (Service i : services) {
            tCost += i.getServicePrice();
            observableList.add(i);
        }
        totalCost.setText(tCost + "");
    }
}