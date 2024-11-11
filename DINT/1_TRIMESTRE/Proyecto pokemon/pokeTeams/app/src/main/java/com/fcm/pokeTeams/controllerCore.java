/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams;

import com.fcm.pokeTeams.modelos.Pokemon;
import com.fcm.pokeTeams.util.Conexion;
import com.fcm.pokeTeams.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author DFran49
 */
public class controllerCore implements Initializable {
    private controllerLogIn cLi;
    Conexion conexion = null;
    int col = 0;
    int row = 0;
    Utilidades utils = new Utilidades();
    ObservableList<Pokemon> listaPokemon = FXCollections.observableArrayList();
    @FXML
    private GridPane gridPokemon;
    
    @FXML
    private GridPane gridEquipos;
    
    @FXML
    private ImageView imgEntrenador;

    @FXML
    private TextField txtBusquedaEquipos;

    @FXML
    private TextField txtBusquedaEquipos1;

    @FXML
    private TextField txtGeneroEntrenador;

    @FXML
    private TextField txtNombreEntrenador;

    @FXML
    void añadirEquipo(MouseEvent event) {

    }

    @FXML
    void añadirPokemon(MouseEvent event) {

    }

    @FXML
    void buscarEquipo(MouseEvent event) {

    }

    @FXML
    void buscarPokemon(MouseEvent event) {

    }

    @FXML
    void busquedaDinamicaEquipos(KeyEvent event) {

    }

    @FXML
    void busquedaDinamicaPokemon(KeyEvent event) {

    }

    @FXML
    void cambiarContraseña(ActionEvent event) {

    }

    @FXML
    void cambiarGenero(ActionEvent event) {

    }

    @FXML
    void cambiarNombre(ActionEvent event) {
        try {
            String query = "SELECT * FROM entrenador";
            
            Statement statement = conexion.getConexion().createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {                
                System.out.println(result.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la BD: " + e.getMessage());
        }
    }
    
    @FXML
    void carga(MouseEvent event) {
        /*if (conexion == null) {
            conexion = (Conexion) this.txtBusquedaEquipos.getScene().getWindow().getUserData();
            user = this.txtBusquedaEquipos.getScene().getUserData().toString();
            txtNombreEntrenador.setText(user);
            
            try {
                String query = "SELECT * FROM entrenador WHERE Nombre = '" + user +"'";

                Statement statement = conexion.getConexion().createStatement();
                ResultSet result = statement.executeQuery(query);
                result.next();
                switch (result.getString("Genero")) {
                    case "F" -> txtGeneroEntrenador.setText("Mujer");
                    case "M" -> txtGeneroEntrenador.setText("Hombre");
                    case "0" -> txtGeneroEntrenador.setText("Otro");
                }
                
                query = "SELECT * FROM entrenador WHERE Nombre = '" + user +"'";
                statement = conexion.getConexion().createStatement();
                result = statement.executeQuery(query);
                result.next();
                utils.recuperarImagenBBDD(result.getString("Sprite"), imgEntrenador);
                
                query = "SELECT * FROM pokemon";
                statement = conexion.getConexion().createStatement();
                result = statement.executeQuery(query);
                while (result.next()) {                    
                    Pokemon temp = new Pokemon();
                    temp.setnPokedex(result.getString("N_Pokedex"));
                    temp.setEspecie(result.getString("Especie"));
                    temp.setDenominacion(result.getString("Denominacion"));
                    temp.setDescripcion(result.getString("Descripcion"));
                    temp.setSprite(result.getString("Sprite"));
                    temp.setTipo1(result.getString("Tipo_1"));
                    temp.setTipo2(result.getString("Tipo_2"));
                    temp.setTamaño(result.getDouble("Tamaño"));
                    temp.setPeso(result.getDouble("Peso"));
                    temp.setHabilidades(result.getString("Habilidades"));
                    temp.setEstadisticas(result.getString("Estadisticas"));
                    cargarPokemon(temp);
                }
                
            } catch (SQLException e) {
                System.out.println("Error al conectar con la BD: " + e.getMessage());
            }
        }*/
    }

    @FXML
    void eliminarCuenta(ActionEvent event) {

    }

    @FXML
    void filtrarEquipo(MouseEvent event) {

    }

    @FXML
    void filtrarPokemon(MouseEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTarjetas();
    }

    private void cargarTarjetas() {
        try {
            
            
            for (int i = 0; i < 4; i++) {
                FXMLLoader cargarEquipo = new FXMLLoader(getClass().getResource("fxml/tarjeta_equipo_v1.fxml"));
                SplitPane tarjetaEquipo = cargarEquipo.load();

                gridEquipos.add(tarjetaEquipo, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void cargarPokemon(Pokemon pokemon) {
        try {
            FXMLLoader cargarPokemon = new FXMLLoader(getClass().getResource("fxml/tarjeta_pokemon_v1.fxml"));
            SplitPane tarjetaPokemon = cargarPokemon.load();
            controllerTarjetaPokemon controlador = cargarPokemon.getController();

            controlador.asignarPokemon(pokemon);
            tarjetaPokemon.setUserData(pokemon);
            gridPokemon.add(tarjetaPokemon, col, row);
            if(col == 2) {
                col = 0;
                row++;
            } else {
                col++;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
    void setControladorEnlace(controllerLogIn c) {
        cLi = c;
    }
    
    void enviaLogIn(Conexion c, String user) {
        conexion = c;
        try {
            String query = "SELECT * FROM entrenador WHERE Nombre = '" + user +"'";

            Statement statement = conexion.getConexion().createStatement();
            ResultSet result = statement.executeQuery(query);
            result.next();
            switch (result.getString("Genero")) {
                case "F" -> txtGeneroEntrenador.setText("Mujer");
                case "M" -> txtGeneroEntrenador.setText("Hombre");
                case "0" -> txtGeneroEntrenador.setText("Otro");
            }

            query = "SELECT * FROM entrenador WHERE Nombre = '" + user +"'";
            statement = conexion.getConexion().createStatement();
            result = statement.executeQuery(query);
            result.next();
            utils.recuperarImagenBBDD(result.getString("Sprite"), imgEntrenador);

            query = "SELECT * FROM pokemon";
            statement = conexion.getConexion().createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {                    
                Pokemon temp = new Pokemon();
                temp.setnPokedex(result.getString("N_Pokedex"));
                temp.setEspecie(result.getString("Especie"));
                temp.setDenominacion(result.getString("Denominacion"));
                temp.setDescripcion(result.getString("Descripcion"));
                temp.setSprite(result.getString("Sprite"));
                temp.setTipo1(result.getString("Tipo_1"));
                temp.setTipo2(result.getString("Tipo_2"));
                temp.setTamaño(result.getDouble("Tamaño"));
                temp.setPeso(result.getDouble("Peso"));
                temp.setHabilidades(result.getString("Habilidades"));
                temp.setEstadisticas(result.getString("Estadisticas"));
                cargarPokemon(temp);
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar con la BD: " + e.getMessage());
        }
    }
}
