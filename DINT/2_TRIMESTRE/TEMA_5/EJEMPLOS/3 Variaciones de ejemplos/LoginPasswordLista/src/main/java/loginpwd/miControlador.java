package loginpwd;

import cvs.MiBoton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author Molina
 */
public class miControlador implements Initializable {

    @FXML
    private TextField usuarioTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private MiBoton miBoton;

    int intentos = 0;

    @FXML
    public void enviarButton() {
        if (intentos > 1) {
            String usuario = usuarioTextField.getText();
            String password = passwordTextField.getText();

            System.out.println("Usuario: " + usuario);
            System.out.println("Password: " + password);

            System.out.println("Texto Debug: " + miBoton.getTextoDebug());
            intentos--;
            System.out.println("Intentos: " + String.valueOf(intentos));
            //Aquí habría que hacer la comprobación de user/password correcto
        } else {
            // System.out.println("ERROR! Número de intentos superado");
            // Alerta de advertencia
            Alert alertWarning = new Alert(Alert.AlertType.WARNING);
            ObservableList<String> textosAlert = miBoton.getListaTextos();
            alertWarning.setTitle(textosAlert.get(0));
            alertWarning.setHeaderText(textosAlert.get(1));
            String pistas = new String(); //Sacaría más hipotéticas pistas (incluídas desde SB o por código)
            for (int i = 2; i < textosAlert.size(); i++) {
                pistas = pistas + textosAlert.get(i);
            }
            alertWarning.setContentText(pistas);
            alertWarning.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        intentos = (int) miBoton.getValorNum();

        //Podemos añadir datos a la lista desde el código (además de los datos que se pasan por SB)
        //miBoton.getListaTextos().add("Pista 2: acaba en un & ");
        
        //Ejemplos de BINDINGS
        //Binding entre propiedades de un mismo nodo
         usuarioTextField.prefWidthProperty().bind(Bindings.createDoubleBinding( //Valor que es devuelto: double
                () -> {
                    int tama = usuarioTextField.getText().length();
                    if ((tama * 2 + 90) < 150) {
                        return (tama * 2.0 + 90);
                    } else {
                        return 150.0;
                    }
                }, 
                usuarioTextField.textProperty() //En qué momento realiza el bind (cada vez que cambia la propiedad textProperty)
        ));

        //Binding entre propiedades de varios nodos (boton y cajas de texto). 
        //La idea es activar "solo si hay datos en ambas cajas"
        //Pero ocurre que solo podemos afectar a un property a la vez, por tanto solo se tiene en cuenta el último bind
        miBoton.disableProperty().bind(usuarioTextField.textProperty().isEmpty());
        miBoton.disableProperty().bind(passwordTextField.textProperty().isEmpty());

        //Desactiva si uno de los dos está vacío (usamos OR)
        miBoton.disableProperty().bind(Bindings.or(usuarioTextField.textProperty().isEmpty(), passwordTextField.textProperty().isEmpty()));
    }
}
