package pasovariablesv3;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Molina
 */
public class controladorB implements Initializable {

    @FXML
    private TextField cajaTexto2;

    @FXML
    void botonPulsadoEnviaDatos() {
        Calendar calendario = Calendar.getInstance();
        Datos datos1 = new Datos(this.cajaTexto2.getText(), calendario.get(Calendar.YEAR));
        Stage stageB = (Stage) this.cajaTexto2.getScene().getWindow();
        System.out.println("Guardando: " + datos1);
        stageB.setUserData(datos1);//Guardo datos
        stageB.hide();//Al ocultar llama al método HIDDEN
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
