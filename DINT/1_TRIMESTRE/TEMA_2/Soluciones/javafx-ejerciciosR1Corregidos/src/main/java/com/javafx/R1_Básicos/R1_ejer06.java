package com.javafx.R1_Básicos; //Modificar al package correcto

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class R1_ejer06 extends Application {

    char letraOK;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label dniEtiq=new Label("DNI:");
        TextField dni=new TextField();
        Button validarButton=new Button("Validar");
        CheckBox checkbox=new CheckBox("Comprobar \nLetra correcta");

        validarButton.setOnAction(e -> {
            String d=dni.getText();
            boolean autoCalcular=checkbox.isSelected();
            System.out.println(validarDNI(d, autoCalcular));
            if (validarDNI(d, autoCalcular)) {
                mostrarAlerta("El formato es válido");
            } else {
                mostrarAlerta("DNI inválido! \nFormato correcto: DDDDDDDDL  \n(D=Dígito, L=Letra [A-HJ-NP-TV-Z]) ");
            }
        });

        HBox hbox=new HBox(10, dniEtiq, dni, checkbox, validarButton);
        hbox.setPadding(new Insets(20));
        Scene scene=new Scene(hbox, 430, 80);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean validarDNI(String dni, boolean autoCalcular) {
        //Validación básica de formato
        if (dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
            if (autoCalcular) {
                int numero=Integer.parseInt(dni.substring(0, 8));
                String caractValidos="TRWAGMYFPDXBNJZSQVHLCKE";
                letraOK=caractValidos.charAt(numero % 23);
                if (dni.charAt(8) == letraOK) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Letra calculada es " + letraOK);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
