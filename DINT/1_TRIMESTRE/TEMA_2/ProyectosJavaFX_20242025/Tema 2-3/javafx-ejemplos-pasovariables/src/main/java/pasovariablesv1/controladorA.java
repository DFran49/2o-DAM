package pasovariablesv1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Molina
 */
public class controladorA implements Initializable {

    //Controladores-----------------------------
    private controladorB cB;//ControladorB

    @FXML
    private TextField cajaTexto;

    @FXML
    private Label miEtiqueta;

    @FXML
    private void botonPulsado() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfazB.fxml"));
        Parent root = loader.load();

        //ENLACE DE CONTROLADORES
        cB = loader.getController();//me devuelve un objeto de la clase Controlador asociado al fxml (Controlador B)
        //Desde aquí ya podemos llamar a cualquier variable/método PÚBLICO de B
        this.cB.enviaDeAaB(this.cajaTexto.getText());
        cB.setControladorEnlace(this);//le pasamos el controlador (clase actual - Controlador A) al nuevo Controlador B
        //Desde B podemos llamar a cualquier método PÚBLICO de A

        //Esta variante tiene un problema: 
        // cada vez que pulsamos botón se abre B de forma modal.
        // si no queremos que sea modal vemos que abre tantas ventanas como veces le demos al botón
        Scene scene = new Scene(root, 400, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); //MODAL!!
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Ventana Emergente");
        stage.show();

        //Accedemos al primaryStage para mostrar la ventana con respecto a la principal
        //Usamos cualquier nodo
        Stage primaryStage = (Stage) this.cajaTexto.getScene().getWindow();
        stage.setX(primaryStage.getX()); // Establecer la posición y
        stage.setY(primaryStage.getY() + 240); // Establecer la posición y

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //Función que llamamos desde B
    void enviaDeBaA(String texto) {
        this.miEtiqueta.setText("Dato recibido:" + texto);
    }

}
