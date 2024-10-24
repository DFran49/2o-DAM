package com.javafx.R1_Básicos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class R1_ejer03 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Conversor de Moneda");

        //Componentes
        Label etiqueta = new Label("Conversión €:");
        TextField cantidad = new TextField();
        ChoiceBox<String> moneda = new ChoiceBox<>();
        moneda.getItems().addAll("Dólares", "Libras", "Yenes"); //Agrega más monedas si es necesario
        Button convertir = new Button("Convertir");
        Label res = new Label();

        convertir.setOnAction(e -> {
            String cantidadStr = cantidad.getText();
            if (!cantidadStr.isEmpty()) {
                double cantidadReal = Double.parseDouble(cantidadStr);
                String selec = moneda.getValue();
                double resultado = cambia(cantidadReal, selec);
                res.setText(String.format("%.2f %s", resultado, selec));
            } else {
                res.setText("Cantidad incorrecta");
            }
        });

        //Layout
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(etiqueta, cantidad, moneda, convertir, res);

        //Scene y Stage
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Función para convertir la moneda
    private double cambia(double cantidad, String moneda) {
        switch (moneda) {
            case "Dólares" -> {
                cantidad *= 1.07;
            } 
            case "Libras" -> {
                cantidad *= 2.07;
            } 
            case "Yenes" -> {
                cantidad *= 1.47;
            } 
        }
        return cantidad;
    }
}
