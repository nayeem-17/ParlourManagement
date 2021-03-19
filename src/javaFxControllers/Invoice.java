/**
 * Sample Skeleton for 'Invoice.fxml' Controller Class
 */

package javaFxControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controllers.InvoiceController;
import entities.AssignS;
import entities.InvoiceData;
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

public class Invoice {

    @FXML // fx:id="pane"
    private AnchorPane pane; // Value injected by FXMLLoader

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="invoiceTable"
    private TableView<InvoiceData> invoiceTable; // Value injected by FXMLLoader

    @FXML // fx:id="invoideId"
    private TableColumn<InvoiceData, String> invoideId; // Value injected by FXMLLoader

    @FXML // fx:id="customerName"
    private TableColumn<InvoiceData, String> customerName; // Value injected by FXMLLoader

    @FXML // fx:id="date"
    private TableColumn<InvoiceData, String> date; // Value injected by FXMLLoader

    @FXML // fx:id="viewButton"
    private Button viewButton; // Value injected by FXMLLoader

    ObservableList<InvoiceData> observableList;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert invoiceTable != null : "fx:id=\"invoiceTable\" was not injected: check your FXML file 'Invoice.fxml'.";
        assert invoideId != null : "fx:id=\"invoideId\" was not injected: check your FXML file 'Invoice.fxml'.";
        assert customerName != null : "fx:id=\"customerName\" was not injected: check your FXML file 'Invoice.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'Invoice.fxml'.";
        assert viewButton != null : "fx:id=\"viewButton\" was not injected: check your FXML file 'Invoice.fxml'.";

        getItems();

        invoideId.setCellValueFactory(new PropertyValueFactory<InvoiceData, String>("invoiceId"));
        date.setCellValueFactory(new PropertyValueFactory<InvoiceData, String>("date"));
        customerName.setCellValueFactory(new PropertyValueFactory<InvoiceData, String>("cutomerName"));


        invoiceTable.setItems(observableList);

        viewButton.setOnAction((e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxmls/InvoiceDetail.fxml"));
            Stage stage = (Stage) pane.getScene().getWindow();
            Scene scene = null;
            try {
                scene = new Scene((Parent) loader.load(), 1180, 627);

                InvoiceDetail invoiceDetail = loader.getController();
                invoiceDetail.setInvoice(invoiceTable.getSelectionModel().getSelectedItem());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            stage.setScene(scene);
        }));
    }

    public ObservableList<InvoiceData> getItems() {
        observableList = FXCollections.observableArrayList();
        List<InvoiceData> temp = InvoiceController.getInvoiceList();
        List<String> invId = new ArrayList<>();
        for (InvoiceData i : temp) {
            String id = i.getInvoiceId();
            if (!invId.contains(id)) {
                invId.add(id);
                InvoiceData invoiceData = new InvoiceData(i.getCutomerName());
                invoiceData.setInvoiceId(id);
                invoiceData.setDate(i.getDate());
                System.out.println(invoiceData);
                observableList.add(invoiceData);
            }

        }
        return observableList;
    }

    public static void main(String[] args) {
        new Invoice().getItems();
    }
}
