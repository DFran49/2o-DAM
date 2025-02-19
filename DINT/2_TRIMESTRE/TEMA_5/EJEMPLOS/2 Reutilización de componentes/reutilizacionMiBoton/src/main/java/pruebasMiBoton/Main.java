package pruebasMiBoton;

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
        Parent root = FXMLLoader.load(getClass().getResource("/vista/loginpwd.fxml"));

        Scene scene = new Scene(root);
        primeraEscena.setScene(scene);
        primeraEscena.setWidth(320);
        primeraEscena.setHeight(260);
        primeraEscena.setTitle("Ejemplo C. Visual");
        primeraEscena.show();      
    }
}
