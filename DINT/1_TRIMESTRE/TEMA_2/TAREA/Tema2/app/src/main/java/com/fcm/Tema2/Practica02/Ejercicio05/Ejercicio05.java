/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.Tema2.Practica02.Ejercicio05;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Plantilla JAVAFX
 * Autor:
 * Curso y año:
 * Objetivo de esta clase:
 */
public class Ejercicio05 extends Application {
    GridPane gridPane;
    ObservableList<Cerveza> listCerveza;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        gridPane=new GridPane();
        listCerveza = FXCollections.observableArrayList();
        for (int i=0; i<14; i++) {
            listCerveza.add(new Cerveza(new Image("file:src/main/resources/alhambra-roja.png"),"Alhambra "+i,"Española",70,i));
        }
        
        gridPane.setHgap(10); 
        gridPane.setVgap(10); 
        gridPane.setPadding(new Insets(30)); 
        
        rellenarGrid();

        Scene scene=new Scene(gridPane, 500, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Pane panel(Cerveza cerveza) {
        Pane panel = new Pane();
        Label label = new Label(cerveza.getNombre());
        ImageView imgCerveza = new ImageView(cerveza.getImagen());
        Button btnMostrar = new Button("DATOS");
        btnMostrar.setOnAction((event) -> {
            alerta(cerveza.mostrarInfo()).showAndWait();
        });
        imgCerveza.setFitHeight(50);
        imgCerveza.setFitWidth(50);
        
        Button btnBorrar = new Button("BORRAR");
        btnBorrar.setOnAction((event) -> {
            int indice = listCerveza.indexOf(cerveza);
            listCerveza.remove(cerveza);
            rellenarGrid();
        });
        
        VBox vbox=new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(label,imgCerveza,btnMostrar, btnBorrar);
        
        panel.getChildren().add(vbox);
        return panel;
    }
    
    private void rellenarGrid() {
        gridPane.getChildren().clear();
        int i = 0;
        for (int fila=0; fila < 4; fila++) {
            for (int columna=0; columna < 4; columna++) {
                if (i < listCerveza.size()) {
                    Pane panel = panel(listCerveza.get(i));
                    gridPane.add(panel, columna, fila);
                    i++;
                }
            }
        }
    }
    
    private Alert alerta(String texto) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Info de la cerveza");
        alerta.setContentText(texto);
        return alerta;
    }
}