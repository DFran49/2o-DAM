package ejercicios.ejercicio2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class ControladorEjercicio2 implements Initializable {

    @FXML
    private ChoiceBox<Integer> choiceBox;

    @FXML
    private Label etiqueta;

    @FXML
    private Label etiqueta1;

    @FXML
    private AnchorPane panelPrincipal;

    @FXML
    private RadioButton radioButton1;

    @FXML
    private RadioButton radioButton2;

    @FXML
    private AreaTexto areaTexto;

    //Para agrupar RadioButtons
    ToggleGroup group = new ToggleGroup();

    @FXML
    private CheckBox checkBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //RadioButtons
        this.radioButton1.setText("Blanca");
        this.radioButton2.setText("Negro");
        this.radioButton1.setToggleGroup(group);
        this.radioButton2.setToggleGroup(group);
        if (this.areaTexto.getLetraNegra()) {
            this.radioButton2.setSelected(true);
        } else {
            this.radioButton1.setSelected(true);
        }

        //Rellena ChoiceBox
        for (int i = 1; i <= 3; i++) {
            this.choiceBox.getItems().add(i);
        }
        this.choiceBox.getSelectionModel().select(1);

        //APARTADO BINDINGS
        //CheckBox que cambia la propiedad editable del área de texto
        this.checkBox.setSelected(true);
        this.checkBox.selectedProperty().bindBidirectional(this.areaTexto.editableProperty());

        //Cuando el texto esté vacío, el tamaño de letra no podrá escogerse
        this.choiceBox.disableProperty().bind(Bindings.createBooleanBinding(()
                -> this.areaTexto.getText().isBlank(), //first argument computes the value of the binding
                this.areaTexto.textProperty())); //observables that trigger recomputation
        //Para establecer los binding de los radiobutton de manera individual:
        this.radioButton1.disableProperty().bind(Bindings.createBooleanBinding(()
                -> this.areaTexto.getText().isBlank(), //first argument computes the value of the binding
                this.areaTexto.textProperty())); //observables that trigger recomputation
        this.radioButton2.disableProperty().bind(Bindings.createBooleanBinding(()
                -> this.areaTexto.getText().isBlank(), //first argument computes the value of the binding
                this.areaTexto.textProperty())); //observables that trigger recomputation

        this.choiceBox.setOnAction((event) -> {
            this.areaTexto.setTalla(this.choiceBox.getValue());
        });

        this.radioButton1.setOnAction((event) -> {
            this.areaTexto.setLetraNegra(false);
        });

        this.radioButton2.setOnAction((event) -> {
            this.areaTexto.setLetraNegra(true);
        });
    }
}
