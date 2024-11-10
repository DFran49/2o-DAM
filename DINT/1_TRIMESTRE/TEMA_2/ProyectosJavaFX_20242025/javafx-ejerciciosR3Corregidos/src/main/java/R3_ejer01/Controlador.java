package R3_ejer01;

/**
 *
 * @author Molina
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Controlador implements Initializable {

    @FXML
    private TextField txtDescuento;

    @FXML
    private TextField txtPrecio;

    @FXML
    void calcular() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Info");

        //Hay que hacer conversiones
        Float precioFloat = Float.valueOf(txtPrecio.getText());
        Float descuentoFloat = Float.valueOf(txtDescuento.getText());
        Float desc = precioFloat * (descuentoFloat / 100);
        Float res = precioFloat - desc;

        alerta.setHeaderText("Descuento aplicado. Precio inicial " + txtPrecio.getText());
        alerta.setContentText("Descuento : " + desc.toString() + " Precio final: " + res.toString());
        alerta.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
