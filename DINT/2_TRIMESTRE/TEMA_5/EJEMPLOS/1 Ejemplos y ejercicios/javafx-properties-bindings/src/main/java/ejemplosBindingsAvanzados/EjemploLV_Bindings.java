/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplosBindingsAvanzados;

/**
 *
 * @author Molina
 */
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Se puede usar las propiedades como si fueran atributos normales de una clase
 *  Versión con Bindings
 * @author Molina
 */

public class EjemploLV_Bindings extends Application {

    @Override
    public void start(Stage primaryStage) {
        ObservableList<Planeta> planetas = FXCollections.observableArrayList();

         //S.Solar
        planetas.add(new Planeta("Tierra", "Sistema Solar", 6371));
        planetas.add(new Planeta("Marte", "Sistema Solar", 3389));
        planetas.add(new Planeta("Venus", "Sistema Solar", 6052));
        //Cinturón de Asteroides 
        planetas.add(new Planeta("Ceres", "Cinturón de Asteroides", 473));
        planetas.add(new Planeta("Eros", "Cinturón de Asteroides", 168));
        //Lunas
        planetas.add(new Planeta("Ganímedes", "Sistema Solar (Júpiter)", 2634));
        planetas.add(new Planeta("Io", "Sistema Solar (Júpiter)", 1821));
        planetas.add(new Planeta("Europa", "Sistema Solar (Júpiter)", 1560));
        planetas.add(new Planeta("Titán", "Sistema Solar (Saturno)", 2575));
        planetas.add(new Planeta("Miranda", "Sistema Solar (Urano)", 235));
        //Más allá del Sistema Solar
        planetas.add(new Planeta("La Puerta", "Más allá del Sistema Solar", 100));
        planetas.add(new Planeta("Ilus", "Más allá de la Puerta", 3000));
        //Otros 
        planetas.add(new Planeta("El Cinturón de Asteroides", "Sistema Solar", 200));
        planetas.add(new Planeta("Nube de Oort", "Sistema Solar", 100000));

        ListView<Planeta> listView = new ListView<>(planetas);

        Label LabelNombrePlaneta = new Label("Selecciona un planeta para ver su información.");

        //Usando Bindings.createStringBinding para el texto del Label
        StringBinding planetaSeleccionadoBinding = Bindings.createStringBinding(() -> {
            Planeta p = listView.getSelectionModel().getSelectedItem();
            if (p != null) {
                return "Planeta: " + p.getNombre()+" S.Solar: " + p.getSistemaSolar() +" Radio: " + p.getRadio();
            } else {
                return "Selecciona un planeta para ver sus datos";
            }
        }, listView.getSelectionModel().selectedItemProperty());//

        LabelNombrePlaneta.textProperty().bind(planetaSeleccionadoBinding);


        VBox root = new VBox(listView, LabelNombrePlaneta);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setSpacing(10);

        Scene scene = new Scene(root, 380, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ejemplo ListView<Clase con Properties> (Bindings)");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}