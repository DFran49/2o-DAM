package com.javafx.R1_Básicos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class R1_ejer05 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora de Cambio");

        //Crear elementos de la GUI
        Label labelPrecio=new Label("Precio:");
        TextField precioTextField=new TextField();
        Label labelDinero=new Label("Dinero recibido:");
        TextField dineroTextField=new TextField();
        Button calcularButton=new Button("Calcular Cambio");

        //Manejar el evento de clic en el botón
        calcularButton.setOnAction(e -> {
            try {
                double precio=Double.parseDouble(precioTextField.getText());
                double dinero=Double.parseDouble(dineroTextField.getText());
                double cambio=dinero - precio;

                if (cambio >= 0) {
                    mostrarCambio(cambio);
                } else {
                    mostrarFaltaDinero();
                }
            } catch (NumberFormatException ex) {
                mostrarError("Ingrese valores numéricos válidos.");
            }
        });

        //Crear diseño de la GUI
        VBox vbox=new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(labelPrecio, precioTextField, labelDinero, dineroTextField, calcularButton);

        //Crear escena y mostrar ventana
        Scene scene=new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Función para mostrar el cambio con el menor número de billetes y monedas
    private void mostrarCambio(double cambio) {
        int[] billetesMonedas={ 500, 100, 50, 20, 10, 2, 1 };
        String[] nombres={ "billetes de 500€", "billetes de 100€", "billetes de 50€", "billetes de 20€", "billetes de 10€", "monedas de 2€", "monedas de 1€" };
        String mensaje=new String("Cambio:\n");

        for (int i=0; i < billetesMonedas.length; i++) {
            int cantidad=(int) (cambio / billetesMonedas[i]);
            if (cantidad > 0) {
                mensaje=mensaje+cantidad+" "+nombres[i]+"\n";
                cambio %= billetesMonedas[i];
            }
        }

        mostrarAlerta("Cambio calculado", mensaje.toString());
    }

    //Función para mostrar una alerta de falta de dinero
    private void mostrarFaltaDinero() {
        mostrarAlerta("Falta de dinero", "El dinero recibido es insuficiente.");
    }

    //Función para mostrar una alerta de error
    private void mostrarError(String mensaje) {
        mostrarAlerta("Error", mensaje);
    }

    //Función para mostrar una alerta con un mensaje personalizado
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
