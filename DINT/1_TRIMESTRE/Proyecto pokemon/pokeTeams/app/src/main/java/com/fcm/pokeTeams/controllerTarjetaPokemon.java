/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams;

/**
 *
 * @author DFran49
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class controllerTarjetaPokemon implements Initializable {

    @FXML
    private ImageView imgPokemon;

    @FXML
    private Label txtEspecie;

    @FXML
    private Label txtId;

    @FXML
    void abrirPokemon() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/emergente_pokemon_v1.fxml"));
            Scene scene=new Scene(root);
            Stage escenaPrincipal = new Stage();
            escenaPrincipal.setScene(scene);
            escenaPrincipal.setTitle("Log In");
            escenaPrincipal.show();
        } catch (IOException ex) {
            Logger.getLogger(controllerTarjetaPokemon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
