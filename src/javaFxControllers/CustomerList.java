/**
 * Sample Skeleton for 'CustomerList.fxml' Controller Class
 */

package javaFxControllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dbOperations.DbServices;
import entities.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerList {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tableView"
    private TableView<Customer> tableView; // Value injected by FXMLLoader

    @FXML // fx:id="name"
    private TableColumn<Customer, String> name; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private TableColumn<Customer, String> email; // Value injected by FXMLLoader

    @FXML // fx:id="mobile"
    private TableColumn<Customer, String> mobile; // Value injected by FXMLLoader

    @FXML // fx:id="creationDate"
    private TableColumn<Customer, String> creationDate; // Value injected by FXMLLoader

    ObservableList<Customer> allCustomer;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'CustomerList.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'CustomerList.fxml'.";
        assert mobile != null : "fx:id=\"mobile\" was not injected: check your FXML file 'CustomerList.fxml'.";
        assert creationDate != null : "fx:id=\"creationDate\" was not injected: check your FXML file 'CustomerList.fxml'.";

        getItems();

        name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        mobile.setCellValueFactory(new PropertyValueFactory<Customer, String>("mobileNumber"));
        creationDate.setCellValueFactory(new PropertyValueFactory<Customer, String>("creationDate"));

        tableView.setItems(allCustomer);

        
    }

    private ObservableList<Customer> getItems() {
        this.allCustomer = FXCollections.observableArrayList();
        List<Customer> cars = DbServices.getInstance().getCustomer();
        for (int i = 0; i < cars.size(); i++) {
            allCustomer.add(cars.get(i));
        }

        return allCustomer;
    }
}
