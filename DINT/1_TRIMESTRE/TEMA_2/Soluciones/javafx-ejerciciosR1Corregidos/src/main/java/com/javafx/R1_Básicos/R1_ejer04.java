package com.javafx.R1_Básicos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class R1_ejer04 extends Application {

    String selec;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Conversor de Moneda");

        //Componentes
        Label etiqueta = new Label("Conversión:");
        TextField cantidad = new TextField();
        ChoiceBox<String> moneda = new ChoiceBox<>();
        Button convertir = new Button("Convertir");
        Label res = new Label();

        //RadioBotones
        //Grupo para los botones de radio dependientes
        ToggleGroup toggleGroup = new ToggleGroup();
        //3 botones de radio dependientes
        RadioButton radioButton1 = new RadioButton("Dólares");
        radioButton1.setSelected(true);
        selec = "Dólares";
        radioButton1.setToggleGroup(toggleGroup);
        radioButton1.setSelected(true);
        RadioButton radioButton2 = new RadioButton("Libras");
        radioButton2.setToggleGroup(toggleGroup);
        RadioButton radioButton3 = new RadioButton("Yenes");
        radioButton3.setToggleGroup(toggleGroup);
        
        
        //Se usa el cambio de propiedad para no tener que estar preguntando con el .isSelected a cada radio
        toggleGroup.selectedToggleProperty().addListener((observable, valorAnt, valorAct) -> {
            if (valorAct != null) {
                selec = ((RadioButton) valorAct).getText();
            }
        });

        convertir.setOnAction(e -> {
            String cantidadStr = cantidad.getText();
            if (!cantidadStr.isEmpty()) {
                double cantidadReal = Double.parseDouble(cantidadStr);
                // String selec=moneda.getValue();
                System.out.println("Seleccionado: " + selec);
                double resultado = cambia(cantidadReal, selec);
                res.setText(String.format("%.2f %s", resultado, selec));
            } else {
                res.setText("Cantidad incorrecta");
            }
        });

        //Layout
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(etiqueta, cantidad, new HBox(radioButton1, radioButton2, radioButton3), convertir, res);

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
