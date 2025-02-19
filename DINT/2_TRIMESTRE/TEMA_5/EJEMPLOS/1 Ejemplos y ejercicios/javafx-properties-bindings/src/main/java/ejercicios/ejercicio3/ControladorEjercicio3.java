package ejercicios.ejercicio3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class ControladorEjercicio3 implements Initializable {

    @FXML
    private CampoTexto campoTexto;

    @FXML
    private ComboBox<String> desplegable;

    @FXML
    private ProgressIndicator progreso;

    @FXML
    private TextField texto1;

    @FXML
    private TextField texto2;

    private IntegerProperty cantidadProperty;

    private StringProperty textoMonedaProperty;

    /**
     * Hay 3 enlaces: texto1-->progressIndicator cantidadProperty-->texto2
     * textoMoneda-->campoTexto
     *
     * @author Molina
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.desplegable.getItems().addAll(new String[]{"euro", "dolar", "sin moneda"});
        this.progreso.setProgress(0.0);

        //Enlace 1: Enlazamos progreso (Integer) con texto1 (String)
        this.progreso.progressProperty().bind(Bindings.createIntegerBinding(() -> {
            return this.texto1.getText().length();
        }, this.texto1.textProperty()));

        //Enlace 2: Necesitamos crear propiedad para sumar (100+Cantidad) 
        //luego cambiaremos su valor con un Listener para escribir en texto2 (TextField de la derecha)
        Integer cantidad = 100;
        this.cantidadProperty = new SimpleIntegerProperty(cantidad);
        this.texto2.textProperty().bind(this.cantidadProperty.asString());

        //Enlace 3: Necesitamos crear una propiedad (texto con tipo de moneda)
        //a la que luego se le dará valor (Cantidad €) y se enlaza a la caja de texto abajo
        String textoTipoMoneda = "";
        this.textoMonedaProperty = new SimpleStringProperty(textoTipoMoneda);
        this.campoTexto.textProperty().bind(this.textoMonedaProperty);

        //Listener del combo: cambia property textoMonedaProperty
        this.desplegable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (desplegable.getSelectionModel().getSelectedItem() != null) {
                campoTexto.setMoneda(desplegable.getSelectionModel().getSelectedItem());
                String texto = campoTexto.getMoneda();//Lee PROPERTY moneda
                if (!texto1.getText().isBlank()) {
                    String cantidad1 = texto1.getText();
                    texto = cantidad1 + " " + texto;//Lee PROPERTY moneda
                }
                textoMonedaProperty.setValue(texto);
            } else {
                campoTexto.setMoneda(""); //Si no hay moneda seleccionada en combo
            }
        });

        //Listener de la caja de texto: cambia properties cantidadProperty y textoMonedaProperty
        this.texto1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            Integer cantidad1 = 100;
            String texto = "";
            if (!texto1.getText().isBlank()) { //Inicializa
                cantidad1 = 100 + Integer.parseInt(texto1.getText());
                if (!campoTexto.getMoneda().isBlank()) {
                    String cantidadTexto = texto1.getText();
                    texto = cantidadTexto + " " + campoTexto.getMoneda();
                }
            }
            cantidadProperty.setValue(cantidad1);
            textoMonedaProperty.setValue(texto);
        });

    }

    @FXML
    void clicBoton(ActionEvent event) {
        this.texto1.setText("");
        this.desplegable.valueProperty().set(null);
    }
}
