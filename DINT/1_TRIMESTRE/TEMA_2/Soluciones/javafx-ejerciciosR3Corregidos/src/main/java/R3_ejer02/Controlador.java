package R3_ejer02;

/**
 *
 * @author Molina
 */
import static java.lang.System.exit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Controlador implements Initializable {

    private final String PWD_OK = "nirmata";
    private int intentos = 5;

    @FXML
    private TextField nombreUsuario;

    @FXML
    private TextField pwdUsuario;

    @FXML
    void login() {
        String usuario = nombreUsuario.getText();
        String pwd = pwdUsuario.getText();

        if (pwd.equals(PWD_OK)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("OLE AHÍ " + usuario);
            alert.setHeaderText("Estas dentro");
            alert.setContentText("Lo hiciste en: " + intentos);
            alert.showAndWait();
        } else {
            intentos -= 1;
            if (intentos > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de inicio de sesión");
                alert.setHeaderText("Usuario o contraseña incorrectos.");
                alert.setContentText("Intentos restantes: " + intentos);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de inicio de sesión");
                alert.setHeaderText("Has agotado tus intentos.");
                alert.setContentText("La aplicación se cerrará.");
                alert.showAndWait();
                exit(0);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
