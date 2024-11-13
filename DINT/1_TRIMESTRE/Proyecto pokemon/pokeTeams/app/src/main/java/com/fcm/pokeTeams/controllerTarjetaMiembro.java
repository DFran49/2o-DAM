/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams;

/**
 *
 * @author DFran49
 */
import com.fcm.pokeTeams.modelos.Miembro;
import com.fcm.pokeTeams.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

public class controllerTarjetaMiembro implements Initializable{
    controllerEquipo ce;
    controllerAñadirMiembro cam;
    Stage emergente;
    Miembro miembro;
    Utilidades util = new Utilidades();

    @FXML
    private ImageView imgPokemonMiembro;

    @FXML
    private Label txtNombreMiembro;

    @FXML
    void editarMiembro(MouseEvent event) {
        this.cam.enviaMiembro(miembro);
        this.emergente.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/emergente_añadir_pokemon_equipo_v1.fxml"));
        try {
            root = loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        cam = loader.getController();
        cam.setControladorEnlace(this);

        Scene sceneB = new Scene(root);
        emergente = new Stage();
        emergente.setResizable(false);
        emergente.setScene(sceneB);
        emergente.setTitle("Ventana Emergente");
    }
    
    public void asignarMiembro(Miembro m) {
        txtNombreMiembro.setText(m.getMote());
        util.recuperarImagenBBDD(m.getSprite(), imgPokemonMiembro);
        miembro = m;
    }
    
    void setControladorEnlace(controllerEquipo c) {
        ce = c;
    }
}
