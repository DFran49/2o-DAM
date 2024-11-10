package jl.conferenciasapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;

public class ConferenciasApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");

        // Cargar el icono de la ventana desde un archivo
        InputStream inputStream = getClass().getResourceAsStream("/images/icon.png");
        if (inputStream != null) {
            Image icono = new Image(inputStream);
            stage.getIcons().add(icono);
        }
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}