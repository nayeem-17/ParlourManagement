/**
 * Sample Skeleton for 'AdminSigninPage.fxml' Controller Class
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminSignIn {

    @FXML // fx:id="anchorpane"
    private AnchorPane anchorpane; // Value injected by FXMLLoade

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="usernameFIeld"
    private TextField usernameFIeld; // Value injected by FXMLLoader

    @FXML // fx:id="paswordField"
    private PasswordField paswordField; // Value injected by FXMLLoader

    @FXML // fx:id="signInButton"
    private Button signInButton; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert usernameFIeld != null : "fx:id=\"usernameFIeld\" was not injected: check your FXML file 'AdminSigninPage.fxml'.";
        assert paswordField != null : "fx:id=\"paswordField\" was not injected: check your FXML file 'AdminSigninPage.fxml'.";
        assert signInButton != null : "fx:id=\"signInButton\" was not injected: check your FXML file 'AdminSigninPage.fxml'.";
        signInButton.setOnAction(e -> {
            if (usernameFIeld.getText().equals("admin") && paswordField.getText().equals("admin")) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxmls/Dashboard.fxml"));

                Stage stage = (Stage) anchorpane.getScene().getWindow();
                Scene scene = null;
                try {
                    scene = new Scene((Parent) loader.load(), 1180, 627);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                stage.setScene(scene);
            }
        });
    }


}
