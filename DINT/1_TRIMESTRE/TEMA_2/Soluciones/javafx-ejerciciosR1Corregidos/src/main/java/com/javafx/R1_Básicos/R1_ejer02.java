package com.javafx.R1_Básicos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class R1_ejer02 extends Application {

    private final String PWD_OK = "nirmata";
    private int intentos = 5;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ejercicio 2");

        //Parent
        VBox vbox = new VBox(10);
        
        //Primera fila userHBox
        Label usuarioEtiqueta = new Label("Usuario:");
        TextField nombreUsuario = new TextField();
        HBox userHBox = new HBox(25);
        userHBox.getChildren().addAll(usuarioEtiqueta, nombreUsuario);
        
        //Segunda fila passwordHBox
        Label pwdEtiqueta = new Label("Contraseña:");
        PasswordField pwdUsuario = new PasswordField();
        HBox passwordHBox = new HBox(25);
        passwordHBox.getChildren().addAll(pwdEtiqueta, pwdUsuario);

        Image image = new Image("https://cdn0.iconfinder.com/data/icons/essentials-solid-glyphs-vol-1/100/User-Account-64.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
 
        //Botón
        Button loginButton = new Button("Iniciar Sesión");
        loginButton.setOnAction(e -> {
            String usuario = nombreUsuario.getText();
            String pwd = pwdUsuario.getText();

            if (pwd.equals(PWD_OK)) {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("OLE AHÍ "+usuario);
                    alert.setHeaderText("Estas dentro");
                    alert.setContentText("Lo hiciste en: " + intentos);
                    alert.showAndWait();
                    primaryStage.close();
            } else {
                intentos-=1;
                if (intentos>0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error de inicio de sesión");
                    alert.setHeaderText("Usuario o contraseña incorrectos.");
                    alert.setContentText("Intentos restantes: " + intentos);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error de inicio de sesión");
                    alert.setHeaderText("Has agotado tus intentos.");
                    alert.setContentText("La aplicación se cerrará.");
                    alert.showAndWait();
                    primaryStage.close();
                }
            }
        });
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(new HBox(userHBox,imageView), passwordHBox, loginButton );
        Scene scene = new Scene(vbox, 260, 110);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
       
        //Mejoras?
        //Se podría hacer que al darle al ENTER en cada caja pasara a la siguiente, ¿cómo?
        //Cuando se vea validación de campos usaremos isFocused y requestFocus
