package v1;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;

/** VALIDACIÓN DE CAMPOS - MÉTODOS NO GRÁFICOS
 *
 * @author Molina
 */
public class Controlador implements Initializable {

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField edadTextField;

    @FXML
    private RadioButton damRadioButton;

    @FXML
    private RadioButton dawRadioButton;

    @FXML
    private CheckBox fctCheckBox;

    @FXML
    private ComboBox<String> provinciaComboBox;

    @FXML
    public void comprobarDatos() {
        //Obtenemos datos de los controles
        String nombre = nombreTextField.getText();
        String email = emailTextField.getText();
        String edad = edadTextField.getText();
        boolean dam = damRadioButton.isSelected();
        boolean daw = dawRadioButton.isSelected();
        boolean leido = fctCheckBox.isSelected();
        String provincia = provinciaComboBox.getValue();//También podríamos haberlo enfocado con las variantes de .getSelectionModel().

        //Validaciones básicas. Objetivo: que no haya nada vacío (no comprueba tipos concretos)
        if (nombre.equals("") || nombre.isEmpty()
                || email.isEmpty() || edad.isEmpty()
                || provincia == null || (!dam && !daw) || !leido) {
            System.out.println("ERROR VALIDACION BASICA: algun campo vacio !!!");
        } else {
            System.out.println("TODO OK VALIDACION BASICA!!!");
        }

        //Validaciones intermedias: email formado correctamente, edad ha de tener entre 18 y 100 y no tener más de 3 dígitos
        try {
            Integer edadNum = Integer.valueOf(edad);
            if (!validarEmail(email) || edad.length() > 3 || edadNum < 18 || edadNum > 100) {
                System.out.println("ERROR VALIDACION INTERMEDIA: email incorrecto, o longitud de edad incorrecta, o rango de edad incorrecto !!!");
            } else {
                System.out.println("TODO OK VALIDACION INTERMEDIA!!!");
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR VALIDACION INTERMEDIA: edad ha de ser numerico!!");
        }
    }

    private boolean validarEmail(String email) {
        String regex= "^(.+)@(.+)\\.(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup ciclo = new ToggleGroup();
        damRadioButton.setToggleGroup(ciclo);
        dawRadioButton.setToggleGroup(ciclo);

        ObservableList<String> provinciasAndalucia = FXCollections.observableArrayList("Almería", "Cádiz", "Córdoba", "Granada", "Huelva", "Jaén", "Málaga", "Sevilla");
        provinciaComboBox.getItems().addAll(provinciasAndalucia);

        nombreTextField.setTooltip(new Tooltip("Nombre de usuario"));
        emailTextField.setTooltip(new Tooltip("Formato <texto>@<texto>.<texto>"));
        edadTextField.setTooltip(new Tooltip("Número con Máximo 3 digitos. Restricción entre 18 y 100"));

        //El ejemplo está hecho para que al pulsar en COMPROBAR DATOS se compruebe todo
        
        //ALTERNATIVA 1: Hacer que vaya comprobando campo por campo, poniendo el foco
        //donde nos interese según si hay o no error
        nombreTextField.setOnAction(event -> {
            //Se podría haber hecho una función de comprobación solo sobre el nombre
            //si se produce algún fallo devolver false y no continuar...
            //if (!comprobarNombre()){
                emailTextField.requestFocus();
            //}
        });
        //ALTERNATIVA 2: Hacer que vaya comprobando campo por campo mediante el FOCO
        nombreTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {//Se ha obtenido el foco //FocusGained
            } else {//Se ha perdido el foco (FocusLost)
                System.out.println("Nombre pierde foco");
                //comprobarNombre()
            }
        });
        emailTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {//Se ha obtenido el foco //FocusGained
                System.out.println("eMail gana foco");
                //comprobarNombre()
            } else {//Se ha perdido el foco (FocusLost)
            }
        });
    }

}
