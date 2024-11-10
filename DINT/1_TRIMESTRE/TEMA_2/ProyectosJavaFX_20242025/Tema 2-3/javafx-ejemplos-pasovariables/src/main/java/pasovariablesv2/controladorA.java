package pasovariablesv2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private controladorB cB;//ControladorEmergente

    //Definimos una instancia del otro Stage (el que vamos a abrir desde aquí)
    Stage stageB;

    @FXML
    private TextField cajaTexto;

    @FXML
    private Label miEtiqueta;

    @FXML
    private void botonPulsado() {
        //CAMBIO V2: la función botonPulsado solo muestra y envía
        //El FXML se gestiona desde initialize
        this.cB.enviaDeAaB(this.cajaTexto.getText());
        
        //Truco de acceso al Stage desde el FXML a partir de cualquier NODO
        Stage primaryStage = (Stage) this.cajaTexto.getScene().getWindow();
        stageB.setX(primaryStage.getX()); // Establecer la posición y
        stageB.setY(primaryStage.getY() + 240); // Establecer la posición y
        this.stageB.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfazB.fxml"));
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(controladorA.class.getName()).log(Level.SEVERE, null, ex);
        }

        //ENLACE DE CONTROLADORES
        cB = loader.getController();//me devuelve un objeto de la clase Controlador asociado al fxml (Controlador B)
        //Desde aquí ya podemos llamar a cualquier variable/método PÚBLICO de B
        //this.cB.enviaDeAaB(this.cajaTexto.getText());
        cB.setControladorEnlace(this);//le pasamos el controlador (clase actual - Controlador A) al nuevo Controlador B
        //Desde B podemos llamar a cualquier método PÚBLICO de A

        Scene sceneB = new Scene(root, 400, 200);
        stageB = new Stage();
        stageB.initModality(Modality.APPLICATION_MODAL); //MODAL!!
        stageB.setResizable(false);
        stageB.setScene(sceneB);
        stageB.setTitle("Ventana Emergente");
        // stageB.show(); //NO MOSTRAMOS AQUÍ

        //CAMBIO V2: ahora ocultamos, no cerramos
        stageB.setOnCloseRequest(e -> {
            e.consume();//Detiene el evento de cierre
            stageB.hide();
        });
        //CAMBIO V2: ahora ocultamos, no cerramos

        //En la función initialize NO PODEMOS obtener la posición de la ventana porque 
        //en la inicialización aún no están mostrados los componentes!!
        //Por tanto aquí no podemos hacer esto: 
        //Stage primaryStage = (Stage) this.cajaTexto.getScene().getWindow();
        //primaryStage.setY(primaryStage.getY() + 170); // Establecer la posición y
    }

    //Función que llamamos desde B
    void enviaDeBaA(String texto) {
        this.miEtiqueta.setText("Dato recibido:" + texto);
    }

}
