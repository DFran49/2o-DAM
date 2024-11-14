/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams;

/**
 *
 * @author DFran49
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class controllerPopUpCambioContraseña {

    @FXML
    private TextField txtConfContraseña;

    @FXML
    private TextField txtContraseña;

    @FXML
    void cambiarContraseña(ActionEvent event) {
        Stage ventana = (Stage) txtConfContraseña.getScene().getWindow();
        ventana.close();
    }

}
