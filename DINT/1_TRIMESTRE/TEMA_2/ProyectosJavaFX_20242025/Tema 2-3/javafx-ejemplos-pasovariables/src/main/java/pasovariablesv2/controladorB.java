package pasovariablesv2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Molina
 */
public class controladorB implements Initializable {

    //Controladores-----------------------------
    private controladorA cA;

    @FXML
    private TextField cajaTexto;

    @FXML
    private Label miEtiqueta;

    @FXML
    private void botonPulsado() {
        cA.enviaDeBaA(cajaTexto.getText());
    }

    void setControladorEnlace(controladorA c) {
        System.out.println("Controlador enlace");
        cA = c; //Enlace con controlador externo: ENLACE A<-B. Podemos llamar a cualquier variable/método PÚBLICO
    }

    //Función que llamamos desde A
    void enviaDeAaB(String texto) {
        miEtiqueta.setText("Dato recibido:" + texto);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Desde aquí NO se gestiona el evento de ventana porque en la inicialización
        //aún no puedo tener acceso a ningún componente, por tanto no se puede acceder al
        //Stage ni al Scene (se han creado en A)

    }

  

}
