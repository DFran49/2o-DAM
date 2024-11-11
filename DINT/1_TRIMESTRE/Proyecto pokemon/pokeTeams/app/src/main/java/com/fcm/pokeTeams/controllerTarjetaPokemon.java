/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams;

/**
 *
 * @author DFran49
 */
import com.fcm.pokeTeams.modelos.Pokemon;
import com.fcm.pokeTeams.util.Utilidades;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class controllerTarjetaPokemon implements Initializable {
    private controllerPokedex cp;
    Stage emergente;
    Pokemon pokemon;
    Utilidades util = new Utilidades();
    
    @FXML
    private ImageView imgPokemon;

    @FXML
    private Label txtEspecie;

    @FXML
    private Label txtId;

    @FXML
    void abrirPokemon() {
        this.cp.enviaPokemon(pokemon);
        Stage primaryStage = (Stage) this.txtEspecie.getScene().getWindow();
        this.emergente.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/emergente_pokemon_v1.fxml"));
        try {
            root = loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        cp = loader.getController();
        cp.setControladorEnlace(this);

        Scene sceneB = new Scene(root);
        emergente = new Stage();
        emergente.setResizable(false);
        emergente.setScene(sceneB);
        emergente.setTitle("Ventana Emergente");
    }

    public void asignarPokemon(Pokemon p) {
        txtEspecie.setText(p.getEspecie());
        txtId.setText(p.getnPokedex());
        util.recuperarImagenBBDD(p.getSprite(), imgPokemon);
        pokemon = p;
    }
}
