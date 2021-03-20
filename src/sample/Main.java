package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxmls/Home.fxml"));
        primaryStage.setTitle("Welcome to parlour management software");
        primaryStage.getIcons().add(new Image("HomeImages/parlourLogo.png"));
        primaryStage.setScene(new Scene(root, 1300, 850));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

