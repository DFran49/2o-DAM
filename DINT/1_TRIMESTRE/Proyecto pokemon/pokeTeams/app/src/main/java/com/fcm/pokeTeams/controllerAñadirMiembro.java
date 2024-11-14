/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams;

/**
 *
 * @author DFran49
 */
import com.fcm.pokeTeams.modelos.EVsEnvoltorio;
import com.fcm.pokeTeams.modelos.EstadisticasEnvoltorio;
import com.fcm.pokeTeams.modelos.Miembro;
import com.fcm.pokeTeams.modelos.Pokemon;
import com.fcm.pokeTeams.util.Utilidades;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class controllerAñadirMiembro implements Initializable {
    private controllerTarjetaMiembro ctm;
    Utilidades util = new Utilidades();
    private Miembro miembro;

    @FXML
    private ComboBox<String> cbEspecie;

    @FXML
    private ComboBox<Character> cbGenero;

    @FXML
    private ComboBox<String> cbHabilidad;

    @FXML
    private ComboBox<String> cbTipo1;

    @FXML
    private ComboBox<String> cbTipo2;

    @FXML
    private ImageView imgPokemon;

    @FXML
    private Slider sdEVsAtk;

    @FXML
    private Slider sdEVsDef;

    @FXML
    private Slider sdEVsHp;

    @FXML
    private Slider sdEVsSpA;

    @FXML
    private Slider sdEVsSpD;

    @FXML
    private Slider sdEVsSpe;

    @FXML
    private Slider sdIVsAtk;

    @FXML
    private Slider sdIVsDef;

    @FXML
    private Slider sdIVsHp;

    @FXML
    private Slider sdIVsSpA;

    @FXML
    private Slider sdIVsSpD;

    @FXML
    private Slider sdIVsSpe;

    @FXML
    private Label txtEVsAtk;

    @FXML
    private Label txtEVsDef;

    @FXML
    private Label txtEVsHp;

    @FXML
    private Label txtEVsSpA;

    @FXML
    private Label txtEVsSpD;

    @FXML
    private Label txtEVsSpe;

    @FXML
    private TextField txtEspecie;

    @FXML
    private Label txtIVsAtk;

    @FXML
    private Label txtIVsDef;

    @FXML
    private Label txtIVsHp;

    @FXML
    private Label txtIVsSpA;

    @FXML
    private Label txtIVsSpD;

    @FXML
    private Label txtIVsSpe;

    @FXML
    private TextField txtMote;

    @FXML
    private TextField txtMovimiento1;

    @FXML
    private TextField txtMovimiento2;

    @FXML
    private TextField txtMovimiento3;

    @FXML
    private TextField txtMovimiento4;

    @FXML
    private TextField txtObjeto;

    @FXML
    void actualizarEVs(MouseEvent event) {

    }

    @FXML
    void actualizarIVs(MouseEvent event) {

    }

    @FXML
    void subirImagen(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagen jpg", "*.jpg"),
                new FileChooser.ExtensionFilter("Imagen png", "*.png")
        );
        File archivoSeleccionado = fileChooser.showOpenDialog(null);
            if (archivoSeleccionado != null) {
                String rutaArchivo = archivoSeleccionado.toURI().toString();
                Image imagen = new Image(rutaArchivo);
                imgPokemon.setImage(imagen);
            }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    void setControladorEnlace(controllerTarjetaMiembro c) {
        ctm = c;
    }
    
    void enviaMiembro(Miembro m) {
        miembro = m;
        txtEspecie.setText(m.getEspecie());
        cbEspecie.getItems().clear();
        cbEspecie.getItems().add(m.getEspecie());
        cbEspecie.getSelectionModel().select(0);
        txtMote.setText(m.getMote());
        cbHabilidad.getItems().clear();
        cbHabilidad.getItems().add(m.getHabilidad());
        cbHabilidad.getSelectionModel().select(0);
        txtObjeto.setText(m.getObjeto());
        cbGenero.getItems().clear();
        cbGenero.getItems().add(m.getGenero());
        cbGenero.getSelectionModel().select(0);
        cbTipo1.getItems().clear();
        cbTipo1.getItems().add(m.getTipo1());
        cbTipo1.getSelectionModel().select(0);
        cbTipo2.getItems().clear();
        cbTipo2.getItems().add(m.getTipo2());
        cbTipo2.getSelectionModel().select(0);
        
        //movimientos
        //evis, ivs
        util.recuperarImagenBBDD(m.getSprite(), imgPokemon);
        //leerHabilidades(p);
        //leerStats(p);
    }
    
    void leerStats(Miembro m) {
        Gson gson = new Gson();
        EVsEnvoltorio listEVs = gson.fromJson(m.getEvs(), EVsEnvoltorio.class);
        List<Slider> listBarras = new ArrayList<>();
        listBarras.add(sdEVsHp);
        listBarras.add(sdEVsAtk);
        listBarras.add(sdEVsDef);
        listBarras.add(sdEVsSpA);
        listBarras.add(sdEVsSpD);
        listBarras.add(sdEVsSpe);
        List<Label> listEtiquetas = new ArrayList<>();
        listEtiquetas.add(txtEVsHp);
        listEtiquetas.add(txtEVsAtk);
        listEtiquetas.add(txtEVsDef);
        listEtiquetas.add(txtEVsSpA);
        listEtiquetas.add(txtEVsSpD);
        listEtiquetas.add(txtEVsSpe);
        
        try {
            for (int i = 0; i < 6; i++) {
                listBarras.get(i).setValue(listEVs.getEV(i).getValor());
                listEtiquetas.get(i).setText(listEVs.getEV(i).getValor()+"/31");
            }
        } catch (JsonSyntaxException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

