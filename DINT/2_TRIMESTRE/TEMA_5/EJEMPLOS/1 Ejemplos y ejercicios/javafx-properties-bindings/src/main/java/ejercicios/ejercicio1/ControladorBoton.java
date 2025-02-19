package ejercicios.ejercicio1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ControladorBoton implements Initializable {

    @FXML
    private Boton botonCustom;
        
    @FXML
    public void pulsarBoton() {
           this.botonCustom.cambiarEstado();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Valores leídos desde Scene Builder :");
        System.out.println("Texto sin  Clic: " + this.botonCustom.getTextoSinClic());
        System.out.println("Texto con  Clic:: " + this.botonCustom.getTextoConClic());
        System.out.println("Boolean de Clicado: " + this.botonCustom.getClicado());
        System.out.println("--------------------------------" );
        //BINDING: Desactivaremos el botón cuando el valor sea Cancelado, ignorando si es mayúscula o minúscula
//        this.botonCustom.disableProperty().bind(Bindings.createBooleanBinding(
//                   () -> (this.botonCustom.textProperty().get().equalsIgnoreCase("Cancelado")), 
//                       this.botonCustom.textProperty()));
//    
    }
}

   
