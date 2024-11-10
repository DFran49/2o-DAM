/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author DFran49
 */
public class controllerSignIn implements Initializable {
    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnRegistro;

    @FXML
    private PasswordField pwContraseña;

    @FXML
    private TextField txtNombre;

    @FXML
    void registrar() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/core_v1.fxml"));
            Scene scene=new Scene(root);
            Stage miStage = (Stage) this.txtNombre.getScene().getWindow();
            miStage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(controllerSignIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void inicio() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/signIn.fxml"));
            Scene scene=new Scene(root);
            Stage miStage = (Stage) this.txtNombre.getScene().getWindow();
            miStage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(controllerSignIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    void subirImagen() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
}
