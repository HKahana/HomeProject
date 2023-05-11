import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class rectangleController {

    @FXML
    private TextField heightField;

    @FXML
    private TextField widthField;

    @FXML
    private TextField resultsTextField;

    private int height;
    private int width;

    @FXML
    void applyPressed(ActionEvent event) {
        height = Integer.parseInt(heightField.getText());
        width = Integer.parseInt(widthField.getText());

        if(height == width || Math.abs(height - width) > 5)
            resultsTextField.setText("The rectangular building's area is: " + (height * width));
        else
            resultsTextField.setText(("The rectangular building's perimeter is: " + (height*2 + width*2)));
    }

    @FXML
    void donePressed(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root); // attach scene graph to scene
            stage.setTitle("Main Menu"); // displayed in window's title bar
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

