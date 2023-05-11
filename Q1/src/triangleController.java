import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class triangleController {

    @FXML
    private TextField heightField;

    @FXML
    private TextArea resultsTextArea;

    @FXML
    private TextField widthField;

    private int height;
    private int width;

    @FXML
    void calculatePerimeterPressed(ActionEvent event) {
        width = Integer.parseInt(widthField.getText());
        height = Integer.parseInt(heightField.getText());
        //print result
        double hypotenuse = Math.sqrt(Math.pow(width/2, 2) + Math.pow(height, 2));
        double perimeter = width + (2 * hypotenuse);
        resultsTextArea.setText("The triangular building's perimeter is: " + perimeter);

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

    @FXML
    void printResultsPressed(ActionEvent event) {
        height = Integer.parseInt(heightField.getText());
        width = Integer.parseInt(widthField.getText());

        if((width % 2) == 0 || (height * 2) < width)
            resultsTextArea.setText("Printing the triangular building isn't possible with the given parameters. Try again");
        else
            printTriangle(height, width);
    }

    private void printTriangle(int height, int width) {
        String star = "";

        for (int i = 0; i < height; i++) {
            int starsThisLine = (int) Math.round(width * ((i + 1) / (double) height));
            int spacesBeforeStars = (int) Math.round((width - starsThisLine) / 2.0);

            for (int j = 0; j < width; j++) {
                if (j < spacesBeforeStars)
                    star += " ";
                else if (j < spacesBeforeStars + starsThisLine)
                    star += "*";
                else
                    star += " ";
            }
            star += "\n";

            resultsTextArea.setText(star);
        }
    }
}

