/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams;

/**
 *
 * @author DFran49
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class controllerEliminar implements Initializable {

    @FXML
    private TextField txtEliminar;

    @FXML
    void eliminar(ActionEvent event) {
        Stage ventana = (Stage) txtEliminar.getScene().getWindow();
        ventana.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
