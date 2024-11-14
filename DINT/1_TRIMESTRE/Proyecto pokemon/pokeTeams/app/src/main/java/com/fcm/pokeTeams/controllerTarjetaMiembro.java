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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class controllerTarjetaMiembro implements Initializable{
    controllerEquipo ce;
    private controllerConfirmar cc;
    controllerAñadirMiembro cam;
    Stage emergente;
    Miembro miembro;
    Utilidades util = new Utilidades();

    @FXML
    private ImageView imgPokemonMiembro;

    @FXML
    private Label txtNombreMiembro;
    
    @FXML
    void editar(ActionEvent event) {
        this.cam.enviaMiembro(miembro);
        this.emergente.setTitle(miembro.getMote());
        emergente.setOnCloseRequest(evento -> {
            evento.consume();
            Parent raiz = null;
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("fxml/popUp_confirmar_cambios.fxml"));
            try {
                raiz = cargador.load();
            } catch (IOException ex) {
                Logger.getLogger(controllerTarjetaPokemon.class.getName()).log(Level.SEVERE, null, ex);
            }
            cc = cargador.getController();

            Stage confirmar = new Stage();
            Scene scene = new Scene(raiz);
            confirmar.setScene(scene);
            confirmar.setTitle("Confirmar");
            cc.enviaStage(emergente);
            confirmar.showAndWait();
        });
        emergente.getIcons().add(util.getImage(miembro.getSprite()));
        this.emergente.show();
    }

    @FXML
    void editarMiembro(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            this.cam.enviaMiembro(miembro);
            this.emergente.setTitle(miembro.getMote());
            emergente.setOnCloseRequest(evento -> {
                evento.consume();
                Parent raiz = null;
                FXMLLoader cargador = new FXMLLoader(getClass().getResource("fxml/popUp_confirmar_cambios.fxml"));
                try {
                    raiz = cargador.load();
                } catch (IOException ex) {
                    Logger.getLogger(controllerTarjetaPokemon.class.getName()).log(Level.SEVERE, null, ex);
                }
                cc = cargador.getController();

                Stage confirmar = new Stage();
                Scene scene = new Scene(raiz);
                confirmar.setScene(scene);
                confirmar.setTitle("Confirmar");
                cc.enviaStage(emergente);
                confirmar.showAndWait();
            });
            emergente.getIcons().add(util.getImage(miembro.getSprite()));
            emergente.show();
        }
    }
    
    @FXML
    void eliminar(ActionEvent event) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/popUp_eliminar.fxml"));
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(controllerTarjetaPokemon.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage miStage = new Stage();
        Scene inicio = new Scene(root);
        miStage.setScene(inicio);
        miStage.setTitle("Eliminar " + miembro.getMote());
        miStage.getIcons().add(new Image("Trubbish.png"));
        miStage.showAndWait();
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
        emergente.setTitle("Añadir/Editar miembro");
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
