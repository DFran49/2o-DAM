package com.javafx.R1_Básicos; //Modificar al package correcto

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class R1_ejer05b extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label precioLabel = new Label("Precio:");
        Label dineroLabel = new Label("Dinero:");
        TextField precio = new TextField();
        TextField dinero = new TextField();
        Button botonCalcular = new Button("Calcular");
        TextArea resultadoTextArea = new TextArea();

        //Configurar el manejo de eventos del botón
        botonCalcular.setOnAction(event -> {
            //calcularCambio(precio, dineroTextField, resultadoTextArea);
            double prec = Double.parseDouble(precio.getText());
            double din = Double.parseDouble(dinero.getText());

            double cambio = din - prec;
            String txt = "Cambio: " + cambio + " €\n";

            int[] billete = new int[7];
            int[] valores = {500, 100, 50, 20, 10, 2, 1};

            for (int i = 0; i < valores.length; i++) {
                billete[i] = (int) (cambio / valores[i]);
                cambio = cambio % valores[i];
            }

            txt += "Billetes de 500: " + billete[0] + "\n";
            txt += "Billetes de 100: " + billete[1] + "\n";
            txt += "Billetes de 50: " + billete[2] + "\n";
            txt += "Billetes de 20: " + billete[3] + "\n";
            txt += "Billetes de 10: " + billete[4] + "\n";
            txt += "Monedas de 2: " + billete[5] + "\n";
            txt += "Monedas de 1: " + billete[6];
            resultadoTextArea.setText(txt);
        }
        );
        //En este caso indicamos borde de 10px y justo después todos los nodes
        VBox vbox = new VBox(10, new HBox(precioLabel, precio), new HBox(dineroLabel, dinero), botonCalcular, resultadoTextArea);
        vbox.setPadding(new Insets(20));
        Scene scene = new Scene(vbox, 230, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
