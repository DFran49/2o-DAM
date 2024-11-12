/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams;

/**
 *
 * @author DFran49
 */
import com.fcm.pokeTeams.modelos.Equipo;
import com.fcm.pokeTeams.modelos.Miembro;
import com.fcm.pokeTeams.util.Conexion;
import com.fcm.pokeTeams.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class controllerEquipos implements Initializable {
    Conexion conexion = null;
    Utilidades util = new Utilidades();
    List<Miembro> participantes = new ArrayList<>();
    Equipo equipo;

    @FXML
    private ImageView imgPokemon1;

    @FXML
    private ImageView imgPokemon2;

    @FXML
    private ImageView imgPokemon3;

    @FXML
    private ImageView imgPokemon4;

    @FXML
    private ImageView imgPokemon5;

    @FXML
    private ImageView imgPokemon6;

    @FXML
    private Label txtFormatoEquipo;

    @FXML
    private Label txtNombreEquipo;

    @FXML
    void abrirEquipo(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/emergente_añadir_equipo_v1.fxml"));
            Scene scene=new Scene(root);
            Stage escenaPrincipal = new Stage();
            escenaPrincipal.setScene(scene);
            escenaPrincipal.setTitle("Log In");
            escenaPrincipal.show();
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    @FXML
    void abrirMenu(ContextMenuEvent event) {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void asignarEquipo(Equipo e, Conexion c) {
        txtNombreEquipo.setText(e.getNombre());
        txtFormatoEquipo.setText(e.getFormato());
        


        try {
            String query = "SELECT * FROM equipo WHERE ID_Equipo = " + e.getIdEquipo();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Miembro tempMiembro = new Miembro();
                tempMiembro.setMote(result.getString("Mote"));
                tempMiembro.setnPokedex(result.getInt("N_Pokedex"));
                tempMiembro.setGenero(result.getString("Genero").charAt(0));
                tempMiembro.setNivel(result.getInt("Nivel"));
                tempMiembro.setHabilidad(result.getString("Habilidad"));
                tempMiembro.setNaturaleza(result.getString("Naturaleza"));
                tempMiembro.setObjeto(result.getString("Objeto"));
                tempMiembro.setMovimientos(result.getString("Movimientos"));
                tempMiembro.setEvs(result.getString("EVs"));
                tempMiembro.setIvs(result.getString("IVs"));
                participantes.add(tempMiembro);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(controllerEquipos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //util.recuperarImagenBBDD(p.getSprite(), imgPokemon);
        equipo = e;
        conexion = c;
    }
}
