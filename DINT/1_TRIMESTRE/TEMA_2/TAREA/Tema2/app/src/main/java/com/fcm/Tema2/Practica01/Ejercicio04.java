/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.Practica01;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Plantilla JAVAFX
 * Autor: Francisco Crespo martín
 * Curso y año: 2 DAM B
 * Objetivo de esta clase: Convertir divisas
 */
public class Ejercicio04 extends Application {
    String opcion = "";
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label lblConversion = new Label("Conversión");
        TextField txtCantidad = new TextField();
        
        ToggleGroup tgDivisas = new ToggleGroup();
        RadioButton rbtnDolares = new RadioButton("Dolares");
        rbtnDolares.setToggleGroup(tgDivisas);
        RadioButton rbtnLibras = new RadioButton("Libras");
        rbtnLibras.setToggleGroup(tgDivisas);
        RadioButton rbtnYens = new RadioButton("Yens");
        rbtnYens.setToggleGroup(tgDivisas);
        
        Label lblResultado = new Label();
        
        Button btnConvertir = new Button("Convertir");

        VBox vbox=new VBox(10);
        vbox.getChildren().add(lblConversion);
        vbox.getChildren().add(txtCantidad);
        vbox.getChildren().addAll(rbtnDolares,rbtnLibras,rbtnYens);
        vbox.getChildren().add(btnConvertir);
        vbox.getChildren().add(lblResultado);
                
        tgDivisas.selectedToggleProperty().addListener((observable, valorAnt, valorAct) -> {
            opcion = ((RadioButton) valorAct).getText();  
        });
        
        btnConvertir.setOnAction((event) -> {
            double cantidad = 0;
            switch (opcion.toString()) {
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
