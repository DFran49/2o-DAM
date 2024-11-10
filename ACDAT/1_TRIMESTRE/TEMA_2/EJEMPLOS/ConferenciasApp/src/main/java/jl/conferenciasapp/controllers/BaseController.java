package jl.conferenciasapp.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jl.conferenciasapp.utils.DbConnection;
import jl.conferenciasapp.utils.FuncionesVarias;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BaseController implements Initializable {
    protected void mostrarVentana(String view, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BaseController.class.getResource(view));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle(title);

        // Configurar ventana modal
        stage.initModality(Modality.APPLICATION_MODAL);

        // Cargar el icono de la ventana desde un archivo
        InputStream inputStream = getClass().getResourceAsStream("/images/icon.png");
        if (inputStream != null) {
            Image icono = new Image(inputStream);
            stage.getIcons().add(icono);
        }
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        try {
            DbConnection.closeConnection();
        } catch (SQLException e) {
            FuncionesVarias.mostrarError("Se ha producido un error al cerrar la conexión con el SGBD.", "Cerrar conexión con el SGBD");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
