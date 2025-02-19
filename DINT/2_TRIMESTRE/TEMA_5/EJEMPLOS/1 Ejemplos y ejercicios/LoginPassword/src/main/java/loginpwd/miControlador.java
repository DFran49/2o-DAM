package loginpwd;

import cvs.MiBoton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
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

            System.out.println("Texto Debug: " + miBoton.getTextoDebug()); //Accedemos a Property
            intentos--;
            System.out.println("Intentos: " + String.valueOf(intentos));
            //Aquí habría que hacer la comprobación de user/password correcto
        } else {
            System.out.println("ERROR! Número de intentos superado");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        intentos = (int) miBoton.getValorNum(); //Accedemos a Property

        //Ejemplos de BINDINGS
        //Binding entre propiedades de un mismo nodo
        usuarioTextField.prefWidthProperty().bind(Bindings.createDoubleBinding( //Valor que es devuelto: double
                () -> {
                    int tama = usuarioTextField.getText().length();
                    if ((tama * 2 + 90) < 150) {
                        return (tama * 2.0 + 90);
                    } else {
                        return 150.0;
                    }}, //Valor que cambia (variable+mínimo)
                usuarioTextField.textProperty() //En qué momento realiza el bind (cada vez que cambia la propiedad textProperty)
                    ));

        //Binding entre propiedades de varios nodos (boton y cajas de texto). 
        //La idea es activar "solo si hay datos en ambas cajas"
        //Pero ocurre que solo podemos afectar a un property a la vez, por tanto solo se tiene en cuenta el último bind
        miBoton.disableProperty().bind(usuarioTextField.textProperty().isEmpty());
                    miBoton.disableProperty().bind(passwordTextField.textProperty().isEmpty());

                }
    }
