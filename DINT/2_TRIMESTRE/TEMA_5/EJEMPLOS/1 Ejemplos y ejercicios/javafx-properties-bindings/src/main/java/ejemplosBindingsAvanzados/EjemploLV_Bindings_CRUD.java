package ejemplosBindingsAvanzados;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;

/**
 * Ejemplo de CRUD mediante properties y bindings bidireccionales
 *
 * @author Molina
 */
public class EjemploLV_Bindings_CRUD extends Application {

    ObservableList<Planeta> planetas = FXCollections.observableArrayList();
    ListView<Planeta> listView = new ListView<>(planetas);
    TextField nombreTextField;
    TextField sistemaSolarTextField;
    TextField radioTextField;
    Planeta pseleccionado;

    @Override
    public void start(Stage primaryStage) {

        planetas.add(new Planeta("Tierra", "Sistema Solar", 6371));
        planetas.add(new Planeta("Marte", "Sistema Solar", 3389));
        planetas.add(new Planeta("Venus", "Sistema Solar", 6052));
        planetas.add(new Planeta("Ceres", "Cinturón de Asteroides", 473));
        planetas.add(new Planeta("Eros", "Cinturón de Asteroides", 168));
        planetas.add(new Planeta("Ganímedes", "Sistema Solar (Júpiter)", 2634));
        planetas.add(new Planeta("Io", "Sistema Solar (Júpiter)", 1821));
        planetas.add(new Planeta("Europa", "Sistema Solar (Júpiter)", 1560));
        planetas.add(new Planeta("Titán", "Sistema Solar (Saturno)", 2575));
        planetas.add(new Planeta("Miranda", "Sistema Solar (Urano)", 235));
        planetas.add(new Planeta("La Puerta", "Más allá del Sistema Solar", 100));
        planetas.add(new Planeta("Ilus", "Más allá de la Puerta", 3000));
        planetas.add(new Planeta("El Cinturón de Asteroides", "Sistema Solar", 200));
        planetas.add(new Planeta("Nube de Oort", "Sistema Solar", 100000));

        nombreTextField = new TextField();
        sistemaSolarTextField = new TextField();
        radioTextField = new TextField();

        //Botones
        Button insertarButton = new Button("Insertar");
        Button borrarButton = new Button("Borrar");
        Button actualizarButton = new Button("Actualizar");
        borrarButton.setDisable(true);
        actualizarButton.setDisable(true);

        //Este botón no es necesario incluirlo en la GUI, se incluye por didáctica
        //ya que se llama mediante .fire() al INSERTAR, BORRAR y ACTUALIZAR
        Button habilitarSeleccionButton = new Button("Habilitar Selección");
        habilitarSeleccionButton.setOnAction(e -> {
            habilitarSeleccionButton.setDisable(true); // Deshabilitar el botón mientras la selección está habilitada
            borrarButton.setDisable(false);
            actualizarButton.setDisable(false);
        });

        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                pseleccionado = newValue; //Se guarda el pseleccionado
                enlaza();
                desenlaza();
                listView.setDisable(true);
                habilitarSeleccionButton.setDisable(false);
                borrarButton.setDisable(false);
                actualizarButton.setDisable(false);
            }
        });

        //Aquí es donde realmente se hace la ACTUALIZACIÓN y LIMPIEZA de campos
        habilitarSeleccionButton.setOnAction(e -> {
            if (pseleccionado != null) { //Hacemos unbind
                nombreTextField.textProperty().unbindBidirectional(pseleccionado.nombreProperty());
                sistemaSolarTextField.textProperty().unbindBidirectional(pseleccionado.sistemaSolarProperty());
                radioTextField.textProperty().unbindBidirectional(pseleccionado.radioProperty());
            }
            //Limpiamos campos y selección de LVx
            limpiarCampos(nombreTextField, sistemaSolarTextField, radioTextField);
            listView.getSelectionModel().clearSelection();

            //Activo/Desactivo elementos
            listView.setDisable(false);
            habilitarSeleccionButton.setDisable(true);
            borrarButton.setDisable(true);
            actualizarButton.setDisable(true);
        });

        //INSERCIÓN
        insertarButton.setOnAction(e -> {
            try {
                int radio = Integer.parseInt(radioTextField.getText()); // Necesitamos convertir a entero
                Planeta nuevoPlaneta = new Planeta(nombreTextField.getText(), sistemaSolarTextField.getText(), radio);
                planetas.add(nuevoPlaneta);
                limpiarCampos(nombreTextField, sistemaSolarTextField, radioTextField);
                habilitarSeleccionButton.fire(); // Si quieres que se habilite la selección después de insertar

            } catch (NumberFormatException ex) {
                // Manejar el error si el radio no es un número válido
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error en el radio");
                alert.setContentText("Debe ser un número entero válido!");
                alert.showAndWait();
                radioTextField.clear(); //limpiamos campo
            }
        });

        //ACTUALIZACIÓN
        actualizarButton.setOnAction(e -> {
            //Se enlazan en orden inverso, para que se escriban en orden correcto
            pseleccionado.nombreProperty().bindBidirectional(nombreTextField.textProperty());
            pseleccionado.sistemaSolarProperty().bindBidirectional(sistemaSolarTextField.textProperty());
            //Usamos un property intermedio para la conversión
            SimpleIntegerProperty simpleIntegerProperty = new SimpleIntegerProperty(Integer.parseInt(radioTextField.getText()));
            pseleccionado.radioProperty().bindBidirectional(simpleIntegerProperty);
            habilitarSeleccionButton.fire();
        });

        //BORRADO
        borrarButton.setOnAction(e -> {
            Planeta seleccionado = listView.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                planetas.remove(seleccionado);
                habilitarSeleccionButton.fire();
            }
        });

        // Layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10));

        gridPane.add(new Label("Nombre:"), 0, 0);
        gridPane.add(nombreTextField, 1, 0);
        gridPane.add(new Label("Sistema Solar:"), 0, 1);
        gridPane.add(sistemaSolarTextField, 1, 1);
        gridPane.add(new Label("Radio:"), 0, 2);
        gridPane.add(radioTextField, 1, 2);

        VBox botonesVBox = new VBox(insertarButton, borrarButton, actualizarButton, habilitarSeleccionButton);
        botonesVBox.setSpacing(5);
        botonesVBox.setAlignment(Pos.CENTER);

        HBox contenedorPrincipal = new HBox(gridPane, botonesVBox);
        contenedorPrincipal.setSpacing(10);
        contenedorPrincipal.setPadding(new Insets(20));

        VBox root = new VBox(listView, contenedorPrincipal);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setSpacing(10);

        Scene scene = new Scene(root, 500, 300); // Aumentado el ancho
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ejemplo CRUD LV Bindings");
        primaryStage.show();
    }

    private void enlaza() {
        //Bindings bidireccionales (ambos son del mismo tipo, no hay problema)
        //Indica la dirección en la que se rellena inicialmente (el nombre del planeta le da valor a la caja de texto)
        nombreTextField.textProperty().bindBidirectional(pseleccionado.nombreProperty());
        sistemaSolarTextField.textProperty().bindBidirectional(pseleccionado.sistemaSolarProperty());
        //Usamos un property intermedio para la conversión
        SimpleStringProperty simpleStringProperty = new SimpleStringProperty( Integer.valueOf(pseleccionado.getRadio()).toString());
        radioTextField.textProperty().bindBidirectional(simpleStringProperty);
    }

    private void desenlaza() {
        Planeta planetaSeleccionado = listView.getSelectionModel().getSelectedItem();
        //Necesitamos un mecanismo que me permita desenlazar momentáneamente los campos
        //para en este caso limpiarlos
        if (planetaSeleccionado != null) { //Hacemos unbind
            nombreTextField.textProperty().unbindBidirectional(planetaSeleccionado.nombreProperty());
            sistemaSolarTextField.textProperty().unbindBidirectional(planetaSeleccionado.sistemaSolarProperty());
            radioTextField.textProperty().unbindBidirectional(planetaSeleccionado.radioProperty());
        }
    }

    private void limpiarCampos(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
