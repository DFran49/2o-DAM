/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.Tema2.Practica01;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Plantilla JAVAFX
 * Autor: Francisco Crespo martín
 * Curso y año: 2 DAM B
 * Objetivo de esta clase: Convertir divisas
 */
public class Ejercicio03 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label lblConversion = new Label("Conversión");
        TextField txtCantidad = new TextField();
        
        ChoiceBox chbOpciones = new ChoiceBox();
        chbOpciones.getItems().addAll("Dolares","Libras","Yens");
        chbOpciones.setValue("Dolares");
        Label lblResultado = new Label();
        
        Button btnConvertir = new Button("Convertir");

        VBox vbox=new VBox(10);
        vbox.getChildren().add(lblConversion);
        vbox.getChildren().add(txtCantidad);
        vbox.getChildren().add(chbOpciones);
        vbox.getChildren().add(btnConvertir);
        vbox.getChildren().add(lblResultado);
                
        btnConvertir.setOnAction((event) -> {
            double cantidad = 0;
            switch (chbOpciones.getValue().toString()) {
                case "Dolares" -> {cantidad = Integer.parseInt(txtCantidad.getText())*1.1;}
                case "Libras" -> {cantidad = Integer.parseInt(txtCantidad.getText())*0.83;}
                case "Yens" -> {cantidad = Integer.parseInt(txtCantidad.getText())*162.87;}
                default -> throw new AssertionError();
            }
            lblResultado.setText("La cantidad en la divisa seleccionada es: " + cantidad);
        });
        
        vbox.setPadding(new Insets(20));
        Scene scene=new Scene(vbox, 300,250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculadora de Divisas");
        primaryStage.show();
    }
}
