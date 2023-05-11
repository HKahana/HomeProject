import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainMenuController {

    @FXML
    void exitPressed(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void rectanglePressed(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("rectangle.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root); // attach scene graph to scene
            stage.setTitle("Rectangle"); // displayed in window's title bar
            stage.setScene(scene); // attach scene to stage
            stage.show(); // display the stage

            // Hide the main menu window
            ((Node)(event.getSource())).getScene().getWindow().hide();

        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println(ex);
        }

    }


    @FXML
    void trianglePressed(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("triangle.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root); // attach scene graph to scene
            stage.setTitle("Triangle"); // displayed in window's title bar
            stage.setScene(scene); // attach scene to stage
            stage.show(); // display the stage

            // Hide the main menu window
            ((Node)(event.getSource())).getScene().getWindow().hide();

        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println(ex);
        }

    }


}




