package ejemplosBindingsBasicos;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EjemploBindingsBasico extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label labelNombre = new Label();
        labelNombre.setDisable(true);
        TextField campoNombre = new TextField();
        Button botonEnviar = new Button("Enviar");

        //A) Podemos simular un binding unidireccional como ya sabemos
        //campoNombre.textProperty().addListener((observable, oldValue, newValue) -> {
        //    botonEnviar.setDisable(newValue.isEmpty());
        //});
        
        //B) 2 formas de simplificar el código mediante Bindings,
        //Ambas líneas hacen lo mismo de distintas formas y devuelven un valor BooleanBinding
        botonEnviar.disableProperty().bind(campoNombre.textProperty().isEmpty());
        botonEnviar.disableProperty().bind(Bindings.isEmpty(campoNombre.textProperty()));

        //C) Descomentar y comentar las dos líneas de arriba
        //Vinculamos la propiedad disabled del botón a una expresión condicional
        botonEnviar.disableProperty().bind(
                Bindings.when(campoNombre.textProperty().length().greaterThanOrEqualTo(5))
                        .then(false) //Si el texto tiene 5 o más caracteres, el botón está habilitado
                        .otherwise(true) //Si no, el botón está deshabilitado
        );
        
        //De los 3 bindings anteriores solamente vemos el resultado del último

        //D) Binding bidireccional: el texto del TextField se sincroniza con la propiedad nombre
        labelNombre.textProperty().bindBidirectional(campoNombre.textProperty());

        //Creamos el layout y la escena
        VBox root = new VBox(20);
        root.getChildren().addAll(labelNombre,campoNombre, botonEnviar);
        Scene scene = new Scene(root, 270, 200);

        primaryStage.setTitle("Ejemplo de Bindings");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
