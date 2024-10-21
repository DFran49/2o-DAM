/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.Practica01;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Plantilla JAVAFX
 * Autor: Francisco Crespo martín
 * Curso y año: 2 DAM B
 * Objetivo de esta clase: Aplicar descuentos
 */
public class Ejercicio05 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        Label lblPrecio = new Label("Precio (€)");
        TextField txtPrecio = new TextField();
        
        Label lblDinero = new Label("Dinero (€)");
        TextField txtDinero = new TextField();
        
        Button btnCalcular = new Button("Calcular Cambio");

        VBox vbox=new VBox(10);
        vbox.getChildren().add(lblPrecio);
        vbox.getChildren().add(txtPrecio);
        vbox.getChildren().add(lblDinero);
        vbox.getChildren().add(txtDinero);
        vbox.getChildren().add(btnCalcular);
        
        btnCalcular.setOnAction((event) -> {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            double precio = Integer.parseInt(txtPrecio.getText());
            double dinero = Integer.parseInt(txtDinero.getText());
            int de500 = 0;
            int de100 = 0;
            int de50 = 0;
            int de20 = 0;
            int de10 = 0;
            int de2 = 0;
            int de1 = 0;
            if (precio > dinero) {
                alerta.setTitle("Insuficiente");
                alerta.setHeaderText("El dinero entregado no es suficiente.");
                alerta.setContentText("Necesita " + (precio - dinero) + "€ extra.");
            } else if(precio == dinero) {
                alerta.setTitle("Pago correcto");
                alerta.setHeaderText("El dinero entregado es suficiente.");
                alerta.setContentText("No se precisa cambio.");
            } else {
                dinero = dinero - precio;
                while(dinero > 0) {
                    if (dinero >= 500) {
                        de500++;
                        dinero -= 500;
                    } else if (dinero >= 100) {
                        de100++;
                        dinero -= 100;
                    } else if (dinero >= 50) {
                        de50++;
                        dinero -= 50;
                    } else if (dinero >= 20) {
                        de20++;
                        dinero -= 20;
                    } else if (dinero >= 10) {
                        de10++;
                        dinero -= 10;
                    } else if (dinero >= 2) {
                        de2++;
                        dinero -= 2;
                    } else if (dinero >= 1) {
                        de1++;
                        dinero -= 1;
                    }
                    
                    alerta.setTitle("Pago correcto");
                    alerta.setHeaderText("El dinero entregado es suficiente.");
                    alerta.setContentText("El cambio consiste de: " + de500 + " billetes de 500€\n" + de100 + " billetes de 100€\n"
                                             + de50 + " billetes de 50€\n" + de20 + " billetes de 20€\n" + de10 + " billetes de 10€\n"
                                             + de2 + " monedas de 2€\n" + de1 + " monedas de 1€.");
                }
            }
            alerta.showAndWait();
        });
        
        vbox.setPadding(new Insets(20));
        Scene scene=new Scene(vbox, 300,200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculadora de Descuentos");
        primaryStage.show();
    }

}