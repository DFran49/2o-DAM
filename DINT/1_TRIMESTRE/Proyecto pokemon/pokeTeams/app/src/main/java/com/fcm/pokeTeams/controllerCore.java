/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author DFran49
 */
public class controllerCore implements Initializable {
    @FXML
    private GridPane gridPokemon;
    
    @FXML
    private GridPane gridEquipos;

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
            for (int i = 0; i < 10; i++) {
                FXMLLoader cargarPokemon = new FXMLLoader(getClass().getResource("fxml/tarjeta_pokemon_v1.fxml"));
                SplitPane tarjetaPokemon = cargarPokemon.load();

                int row = i / 3; 
                int col = i % 3;
                gridPokemon.add(tarjetaPokemon, col, row);
            }
            
            for (int i = 0; i < 4; i++) {
                FXMLLoader cargarEquipo = new FXMLLoader(getClass().getResource("fxml/tarjeta_equipo_v1.fxml"));
                SplitPane tarjetaEquipo = cargarEquipo.load();

                gridEquipos.add(tarjetaEquipo, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
