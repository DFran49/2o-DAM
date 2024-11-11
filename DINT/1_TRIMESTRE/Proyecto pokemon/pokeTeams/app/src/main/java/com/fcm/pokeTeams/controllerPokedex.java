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
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class controllerPokedex implements Initializable {
    private controllerTarjetaPokemon ctp;
    Utilidades util = new Utilidades();

    @FXML
    private ProgressBar BarSpD;

    @FXML
    private ProgressBar barAtk;

    @FXML
    private ProgressBar barDef;

    @FXML
    private ProgressBar barHp;

    @FXML
    private ProgressBar barSpA;

    @FXML
    private ProgressBar barSpe;

    @FXML
    private ImageView imgPokemon;

    @FXML
    private TitledPane tpaneHabilidad1;

    @FXML
    private TitledPane tpaneHabilidad2;

    @FXML
    private TitledPane tpaneHabilidad3;

    @FXML
    private Label txtAtk;

    @FXML
    private Label txtDef;

    @FXML
    private TextArea txtDenominacion;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtEspecie;

    @FXML
    private Label txtHp;

    @FXML
    private TextArea txtPeso;

    @FXML
    private Label txtSpA;

    @FXML
    private Label txtSpD;

    @FXML
    private Label txtSpe;

    @FXML
    private TextArea txtTamaño;

    @FXML
    private TextArea txtTipo1;

    @FXML
    private TextArea txtTipo2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    void enviaPokemon(Pokemon p) {
        txtEspecie.setText(p.getEspecie());
        txtDenominacion.setText(p.getDenominacion());
        txtDescripcion.setText(p.getDescripcion());
        txtTamaño.setText(p.getTamaño() + " metros");
        txtPeso.setText(p.getPeso() + " kilogramos");
        txtTipo1.setText(p.getTipo1());
        txtTipo2.setText(p.getTipo2());
        util.recuperarImagenBBDD(p.getSprite(), imgPokemon);
        leerStats(p);
        leerHabilidades(p);
    }
    
    void leerStats(Pokemon p) {
        Gson gson = new Gson();
        /*try {
            Persona persona = gson.fromJson(jsonString, Persona.class);
            System.out.println("Nombre: " + persona.getNombre());
            System.out.println("Edad: " + persona.getEdad());
        } catch (JsonSyntaxException e) {
            System.out.println("Error: " + e.getMessage());
        }*/
    }
    
    void leerHabilidades(Pokemon p) {}
    
    void setControladorEnlace(controllerTarjetaPokemon c) {
        System.out.println("Controlador enlace");
        ctp = c; //Enlace con controlador externo: ENLACE A<-B. Podemos llamar a cualquier variable/método PÚBLICO
    }
}

