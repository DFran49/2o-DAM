/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicios.ejercicio4;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class ControladorVentanaCalendario implements Initializable {

    @FXML
    private Menu MenuCitas;

    @FXML
    private Menu MenuCumple;

    @FXML
    private Menu MenuUrgentes;

    @FXML
    private MenuBar barraMenu;

    @FXML
    private MenuItem borrarCita;

    @FXML
    private MenuItem borrarCumple;

    @FXML
    private MenuItem borrarUrgente;

    @FXML
    private ToggleButton botonNotificaciones;

    @FXML
    private TextField campoEdad;

    @FXML
    private MenuItem crearCita;

    @FXML
    private MenuItem crearCumple;

    @FXML
    private MenuItem crearUrgente;

    @FXML
    private Etiqueta etiqueta;

    @FXML
    private AnchorPane panelPrincipal;

    @FXML
    private DatePicker selectorFechas;

    /**
     *
     * Hay 3 bindings:
     * Bidireccional etiquetaVisible<-->Botón Toggle
     * Bidireccional EdadTexto<-->Property Edad
     * Unidireccional fecha-->Property Fecha: al cambiar el DatePicker guarda la fecha
     * @author Molina
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selectorFechas.setValue(LocalDate.now());
        this.etiqueta.visibleProperty().bindBidirectional(this.botonNotificaciones.selectedProperty());
        this.etiqueta.edadProperty().bindBidirectional(this.campoEdad.textProperty());
        this.etiqueta.fechaProperty().bind(this.selectorFechas.valueProperty().asString());
        
        //Esto activaría el toggle (al enlazarse Bidireccionalmente). Descomentar y probar
        //this.etiqueta.setVisible(true);
    }

    @FXML
    void crearCita(ActionEvent event) {
        this.etiqueta.setModo("cita");
        this.etiqueta.setMensaje();
    }

    @FXML
    void crearCumple(ActionEvent event) {
        this.etiqueta.setModo("cumple");
        this.etiqueta.setMensaje();
    }

    @FXML
    void crearUrgente(ActionEvent event) {
        this.etiqueta.setModo("urgente");
        this.etiqueta.setMensaje();
    }

}
