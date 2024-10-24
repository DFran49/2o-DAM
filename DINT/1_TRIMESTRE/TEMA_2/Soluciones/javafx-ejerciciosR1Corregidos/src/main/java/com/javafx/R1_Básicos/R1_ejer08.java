package com.javafx.R1_Básicos;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class R1_ejer08 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Crear una lista observable de cadenas
        ObservableList<String> items=FXCollections.observableArrayList();

        //Crear un ListView para mostrar los elementos de la lista
        ListView<String> listView=new ListView<>(items);
     
        //Crear un campo de texto y un botón para agregar elementos a la lista
        TextField textField=new TextField();
        Button addButton=new Button("Comprobar AÑO");
        addButton.setOnAction(e -> {
            String anyo=textField.getText();
            String res=new String();
            try {
                int year=Integer.parseInt(anyo);
                if (esBisiesto(year)) {
                    res=year + " BISIESTO";
                } else {
                    res=year + " NO BISIESTO";
                }
            } catch (NumberFormatException ex) {
                System.out.println("ERROR en el tipo de dato");
            }
            if (!anyo.isEmpty()) {
                items.add(res);
                textField.clear();
            }
            
            //Aunque no es necesario, para saber si un ListView o TableView está seleecionado se usa
            System.out.println(!listView.getSelectionModel().isEmpty());
        });

        //Crear un diseño de caja vertical (VBox) para organizar los controles
        VBox vbox=new VBox(listView, new HBox(textField, addButton));
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        //Crear la escena y mostrar la ventana
        Scene scene=new Scene(vbox, 300, 200);
        primaryStage.setTitle("Ejemplo de ObservableList en JavaFX - AÑO BISIESTO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
     private boolean esBisiesto(int a) {
        return (a % 4 == 0 && a % 100 != 0) || (a % 400 == 0);
    }
}

