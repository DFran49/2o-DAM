/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams;

/**
 *
 * @author DFran49
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;

public class controllerPokedex implements Initializable {

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
    private Label txtDenominacion;

    @FXML
    private Label txtDescripcion;

    @FXML
    private TextField txtEspecie;

    @FXML
    private Label txtHp;

    @FXML
    private Label txtPeso;

    @FXML
    private Label txtSpA;

    @FXML
    private Label txtSpD;

    @FXML
    private Label txtSpe;

    @FXML
    private Label txtTamaño;

    @FXML
    private Label txtTipo1;

    @FXML
    private Label txtTipo2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}

