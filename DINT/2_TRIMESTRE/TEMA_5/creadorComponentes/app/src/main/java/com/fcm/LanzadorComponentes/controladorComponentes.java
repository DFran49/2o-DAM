/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.LanzadorComponentes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import cvs.MiListView;
import cvs.MiProgressIndicator;
import cvs.MiText;
import javafx.fxml.FXML;

/**
 *
 * @author DFran49
 */
public class controladorComponentes implements Initializable {
    @FXML
    private MiListView lstTextos;

    @FXML
    private MiProgressIndicator piProgreso;

    @FXML
    private MiText txtPrincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstTextos.asignarTexto(txtPrincipal);
        piProgreso.asignarTexto(txtPrincipal);
    }
    
}
