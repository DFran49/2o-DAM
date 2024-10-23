/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.Tema2.Practica02.Ejercicio04;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        for (int fila=0; fila < 4; fila++) {
            for (int columna=0; columna < 4; columna++) {
                ImageView imageView=insertaFoto("https://images.virgula.me/2017/04/robocop1.jpg");
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                gridPane.add(imageView, columna, fila);

            }
        }


        VBox vbox=new VBox(10); //Layout padre (contenedor de nodos). 10 px separación
        vbox.setPadding(new Insets(20));//Borde interno 20px
        Scene scene=new Scene(vbox, 500,400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Pane panel(Image imagen, String nombre, String nacionalidad, int grados) {
        Pane panel = new Pane();
        Cerveza cerveza = new Cerveza(imagen,nombre,nacionalidad,grados);
        Button btnMostrar = new Button("DATOS");
        
        panel.getChildren().addAll();
        return panel;
    }
}