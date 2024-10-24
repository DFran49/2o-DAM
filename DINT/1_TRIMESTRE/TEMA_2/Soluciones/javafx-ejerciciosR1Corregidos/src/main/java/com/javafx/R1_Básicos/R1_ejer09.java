package com.javafx.R1_Básicos;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class R1_ejer09 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Crear una lista observable de objetos Person
        ObservableList<Año> lista=FXCollections.observableArrayList();

        //Crear una tabla (TableView) para mostrar los elementos de la lista
        TableView<Año> tableView=new TableView<>(lista);

        //Definir las columnas de la tabla
        TableColumn<Año, Integer> c1=new TableColumn<>("AÑO");
        c1.setCellValueFactory(new PropertyValueFactory<>("año"));//propiedad en Año
        TableColumn<Año, String> c2=new TableColumn<>("BISIESTO");
        c2.setCellValueFactory(new PropertyValueFactory<>("bisiesto"));//propiedad en Año

        tableView.getColumns().addAll(c1, c2);

        //Crear campos de texto para el nombre y la edad, y un botón para agregar personas a la lista
        TextField campoTexto=new TextField();
        Button boton=new Button("Comprobar AÑO");
        boton.setOnAction(e -> {
            String añoTexto=campoTexto.getText();
            if (!añoTexto.isEmpty()) {
                try {
                    Integer añoInteger=Integer.valueOf(añoTexto);
                    String res="";
                    if (esBisiesto(añoInteger)) {
                        res=" BISIESTO";
                    } else {
                        res=" NO BISIESTO";
                    }
                    //Aquí es donde creo el nuevo objeto siguiendo el modelo Año y lo añado al OL    
                    Año nuevoAño=new Año(añoInteger, res);
                    lista.add(nuevoAño);//Controla TV vía un OL
                    campoTexto.clear();
                } catch (NumberFormatException ex) {
                    System.out.println("ERROR en el tipo de dato");
                }
            }
            
            //No es necesario pero vemos una forma de recorrer cualquier array
            System.out.println("Recorremos la lista..");
            for (Año a : lista) {
                System.out.println(a.getAño());
            }
        });

        //Crear un diseño de caja vertical (VBox) para organizar los controles
        VBox vbox=new VBox(tableView, new HBox(campoTexto, boton, tableView));
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        //Crear la escena y mostrar la ventana
        Scene scene=new Scene(vbox, 400, 300);
        primaryStage.setTitle("Ejemplo de ObservableList en JavaFX - TableView - AÑO BISIESTO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean esBisiesto(int a) {
        return (a % 4 == 0 && a % 100 != 0) || (a % 400 == 0);
    }
}
