package javaFxControllers;

import java.io.IOException;
import java.util.List;
import dbOperations.CustomerOperations;
import entities.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerList {

    private final CustomerOperations customerOperations = new CustomerOperations();

    @FXML
    private TableView<Customer> tableView;

    @FXML
    private TableColumn<Customer, String> name;

    @FXML
    private TableColumn<Customer, String> email;

    @FXML
    private TableColumn<Customer, String> mobile;

    @FXML
    private TableColumn<Customer, String> creationDate;

    @FXML
    private Button assignService;

    @FXML
    private AnchorPane pane;

    ObservableList<Customer> allCustomer;

    @FXML
    void initialize() {
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'CustomerList.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'CustomerList.fxml'.";
        assert mobile != null : "fx:id=\"mobile\" was not injected: check your FXML file 'CustomerList.fxml'.";
        assert creationDate != null : "fx:id=\"creationDate\" was not injected: check your FXML file 'CustomerList.fxml'.";
        assert assignService != null : "fx:id=\"assignService\" was not injected: check your FXML file 'CustomerList.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'CustomerList.fxml'.";

        getItems();

        name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        mobile.setCellValueFactory(new PropertyValueFactory<Customer, String>("mobileNumber"));
        creationDate.setCellValueFactory(new PropertyValueFactory<Customer, String>("creationDate"));

        tableView.setItems(allCustomer);

        assignService.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxmls/AssignService.fxml"));
            Stage stage = (Stage) pane.getScene().getWindow();
            Scene scene = null;
            try {
                scene = new Scene(loader.load(), 889, 773);

                int index = tableView.getSelectionModel().getSelectedIndex();
                AssignService assignService = loader.getController();
                assignService.setCustomer(tableView.getSelectionModel().getSelectedItem());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            stage.setScene(scene);
        });
    }

    private void getItems() {

        this.allCustomer = FXCollections.observableArrayList();

        List<Customer> cars = customerOperations.getCustomer();

        allCustomer.addAll(cars);

    }
}
