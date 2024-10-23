/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.Tema2.Practica02.Ejercicio04;

import java.util.concurrent.atomic.AtomicInteger;
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
public class Ejercicio04 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane=new GridPane();
        ObservableList<Cerveza> listCerveza = FXCollections.observableArrayList();
        for (int i=0; i<16; i++) {
            listCerveza.add(new Cerveza(new Image("file:src/main/resources/mono.jpg"),"Alhambra "+i,"Española",70));
        }
        AtomicInteger cont = new AtomicInteger(0);
        for (int fila=0; fila < 4; fila++) {
            for (int columna=0; columna < 4; columna++) {
                Pane panel = new Pane();
                ImageView imageView= new ImageView(listCerveza.get(cont.intValue()).getImagen());
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                Label nombre = new Label(listCerveza.get(cont.intValue()).getNombre());
                panel.getChildren().add(nombre);
                panel.getChildren().add(imageView);
                Button boton = new Button("DATOS");
                boton.setOnAction((event) -> {
                            alerta(listCerveza.get(cont.intValue()).mostrarInfo()).showAndWait();
                        });
                panel.getChildren().add(boton);
                gridPane.add(panel, columna, fila);
                cont.getAndIncrement();
            }
        }

        Scene scene=new Scene(gridPane, 500,400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Pane panel(Image imagen, String nombre, String nacionalidad, int grados) {
        Pane panel = new Pane();
        ImageView imgCerveza = new ImageView(imagen);
        Cerveza cerveza = new Cerveza(imagen,nombre,nacionalidad,grados);
        Button btnMostrar = new Button("DATOS");
        
        panel.getChildren().addAll();
        return panel;
    }
    
    private Alert alerta(String texto) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Info de la cerveza");
        alerta.setContentText(texto);
        return alerta;
    }
}