import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Q1 extends Application {

    @Override
    public void start(Stage stage) throws IOException{

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

            Scene scene = new Scene(root); // attach scene graph to scene
            stage.setTitle("Main Menu"); // displayed in window's title bar
            stage.setScene(scene); // attach scene to stage
            stage.show(); // display the stage
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println(ex);
        }
    }

    public static void main(String[] args) {

        launch(args);
    }

}
