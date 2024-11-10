/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author DFran49
 */
public class controllerEquipo implements Initializable {

    @FXML
    private GridPane gridMiembros;

    @FXML
    private TextField txtEspecie;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            for (int i = 0; i < 5; i++) {
                FXMLLoader cargar = new FXMLLoader(getClass().getResource("fxml/tarjeta_miembro_equipo_v1.fxml"));
                SplitPane tarjeta = cargar.load();

                int col = i%2;
                int row = i/2;              
                gridMiembros.add(tarjeta, col, row);
            }
        } catch (IOException ex) {
            Logger.getLogger(controllerEquipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
