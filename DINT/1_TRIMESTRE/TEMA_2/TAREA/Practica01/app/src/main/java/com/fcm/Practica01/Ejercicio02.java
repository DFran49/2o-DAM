/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.Practica01;

/**
 *
 * @author DFran49
 */
import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Plantilla JAVAFX
 * Autor: Francisco Crespo martín
 * Curso y año: 2 DAM B
 * Objetivo de esta clase: Login
 */
public class Ejercicio02 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label lblIntro = new Label("Login Username and Password");
        ImageView imgFoto = new ImageView("file:src/main/resources/mono.jpg");
        imgFoto.setFitHeight(40);
        imgFoto.setFitWidth(30);
        HBox cajaHorizontal = new HBox(10);
        cajaHorizontal.getChildren().addAll(lblIntro,imgFoto);
        
        Label lblUsername = new Label("Username");
        TextField txtUsername = new TextField();
        
        Label lblPasswd = new Label("Password");
        PasswordField pwdContraseña = new PasswordField();
        
        Button btnOk = new Button("Ok");

        VBox vbox=new VBox(10);
        vbox.getChildren().add(cajaHorizontal);
        vbox.getChildren().add(lblUsername);
        vbox.getChildren().add(txtUsername);
        vbox.getChildren().add(lblPasswd);
        vbox.getChildren().add(pwdContraseña);
        HBox cajaHorizontalBTN = new HBox(btnOk);
        cajaHorizontalBTN.setAlignment(Pos.CENTER);
        vbox.getChildren().add(cajaHorizontalBTN);
        
        AtomicInteger contador = new AtomicInteger(0);
        
        btnOk.setOnAction((event) -> {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            if (contador.get() < 5) {
                if (!txtUsername.getText().equals("DFran49") || !pwdContraseña.getText().equals("duro")) {
                    contador.incrementAndGet();
                } else if(txtUsername.getText().equals("DFran49") || pwdContraseña.getText().equals("duro")) {
                    alerta.setTitle("Welcome");
                    alerta.setHeaderText("Bienvenido usuario " + txtUsername.getText());
                    alerta.setContentText("Su contraseña es:" + pwdContraseña.getText());
                    alerta.showAndWait();
                }
            } else {
                alerta.setTitle("ERROR");
                alerta.setHeaderText("Demasiados intentos");
                alerta.setContentText("Reinicie la app");
                alerta.showAndWait();
            }
        });
        
        vbox.setPadding(new Insets(20));
        Scene scene=new Scene(vbox, 300,250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
}
