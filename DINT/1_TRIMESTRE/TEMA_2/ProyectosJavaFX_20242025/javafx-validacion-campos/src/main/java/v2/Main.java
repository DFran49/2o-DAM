package v2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Molina
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primeraEscena) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ejemplo.fxml"));

        Scene scene = new Scene(root,310,330);
        primeraEscena.setScene(scene);
        primeraEscena.setTitle("Validaciones");
        primeraEscena.show();      
    }
    
    
}
