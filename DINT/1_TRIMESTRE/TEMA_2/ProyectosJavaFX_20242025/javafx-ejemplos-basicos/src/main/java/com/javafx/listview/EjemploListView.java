package com.javafx.listview; //Modificar al package correcto

import com.javafx.tableview.RobotFoto;
import java.util.Comparator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Plantilla JAVAFX
 */
public class EjemploListView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ObservableList<String> seriesLista = FXCollections.observableArrayList(
                "Breaking Bad",
                "The Expanse",
                "Stranger Things",
                "Last Of Us",
                "The Mandalorian",
                "Friends",
                "The IT"
        );

        //Se crea el ListView y se le pasa el OL (Fuente)
        ListView<String> lvSeries = new ListView<>(seriesLista);
        VBox vbox = new VBox(lvSeries);

        //La OL se modifica y no es necesario decirle nada al ListView
        seriesLista.add("The Continental");
        seriesLista.remove(5);
        //seriesList.clear(); //Limpia el OL y por consiguiente el LV
        System.out.println("Hay " + seriesLista.size() + " elementos en la lista");
        lvSeries.getItems().sort(Comparator.naturalOrder()); //Orden natural es alfabético ascendente

        //Ocurre lo mismo que con los togglegroup: no sabes el número de elementos, no puedes poner u
        //setOnAction para cada uno, por lo tanto hay que "escuchar" un cambio de alguna propiedad
        lvSeries.getSelectionModel().selectedItemProperty().addListener((observable, viejoValor, nuevoValor) -> {
            // Manejar el evento de selección aquí
            if (nuevoValor != null) {
                System.out.println("Elemento seleccionado: " + nuevoValor);
                //Otro método cuando el evento no lo dispara el LV, sino un botón por ejemplo
                System.out.println("Otra forma: " + lvSeries.getSelectionModel().getSelectedItem());//Otra forma
            }
        });
        
        lvSeries.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) {//1 Click
                String serie=lvSeries.getSelectionModel().getSelectedItem();
                if (serie != null) {
                    System.out.println("Serie Seleccionado: " + serie);
                }
            }
        });

        // Permite PERSONALIZAR como se pinta (renderiza) los elementos: puede cambiar el texto o el aspecto
        lvSeries.setCellFactory(e -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item);
                    //Inicio parte personalizable
                    if (item.equals("The Expanse")) {//Personalizamos
                        setText("Favorita: " + item);
                        setStyle("-fx-background-color: lightblue; -fx-font-weight: bold;"); //CSS
                    }
                    //Fin parte personalizable
                } else {
                    setText(null);
                }
            }
        });

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setTitle("Lista de Series");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
