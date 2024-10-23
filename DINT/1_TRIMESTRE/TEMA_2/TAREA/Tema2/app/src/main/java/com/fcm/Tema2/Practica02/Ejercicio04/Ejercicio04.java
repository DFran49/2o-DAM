/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.Tema2.Practica02;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
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

        VBox vbox=new VBox(10); //Layout padre (contenedor de nodos). 10 px separación
        vbox.setPadding(new Insets(20));//Borde interno 20px
        Scene scene=new Scene(vbox, 500,400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}