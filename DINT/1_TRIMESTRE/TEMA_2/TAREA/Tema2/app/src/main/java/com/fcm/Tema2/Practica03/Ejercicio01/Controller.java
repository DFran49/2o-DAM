/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.Tema2.Practica03.Ejercicio01;

/**
 *
 * @author Francisco
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller implements Initializable {

    @FXML
    private Button btnCalcular;

    @FXML
    private TextField txtDescuento;

    @FXML
    private TextField txtPrecio;

    @FXML
    void calcularDescuento() {
        double precio = Integer.parseInt(txtPrecio.getText());
        double descuento = Integer.parseInt(txtDescuento.getText());
        double descontado = (precio*descuento)/100;
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Info");
        alerta.setHeaderText("Descuento aplicado. Precio inicial " + precio);
        alerta.setContentText("Descuento: " + descontado + " Precio final: " + (precio - descontado));
        alerta.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
