package jl.conferenciasapp.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import jl.conferenciasapp.utils.DbConnection;
import jl.conferenciasapp.utils.FuncionesVarias;
import jl.conferenciasapp.utils.ValidadorCampos;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController extends BaseController {
    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtClave;

    @FXML
    void cancelar() {
        Platform.exit();
    }

    @FXML
    void aceptar() {
        boolean esValido = ValidadorCampos.validarTextField(txtUsuario, "El campo 'Usuario' no puede estar vacío.", 50)
                && ValidadorCampos.validarTextField(txtClave, "El campo 'Contraseña' no puede estar vacío.", 50);
        if (esValido) {
            DbConnection.setCredentials(txtUsuario.getText(), txtClave.getText());
            boolean errorConexion = false;
            try {
                DbConnection.getConnection();
            } catch (IOException | SQLException e) {
                FuncionesVarias.mostrarError("Se ha producido un error al conectar con el SGBD.\nUsuario/clave incorrectos.\nRevise el archivo de configuración 'config.prop'.", "Abrir conexión con el SGBD");
                errorConexion = true;
            }

            if (!errorConexion) {
                try {
                    // Acceder a la ventana de login y cerrarla
                    Stage ventanaPadre = (Stage) txtUsuario.getScene().getWindow();
                    ventanaPadre.close(); // Cerrar la ventana padre
                    // Mostrar la ventana del panel de administración
                    mostrarVentana("/jl/conferenciasapp/panel-view.fxml", "Panel de administración - Gestión de conferencias");
                } catch (IOException e) {
                    FuncionesVarias.mostrarError("Se ha producido un error al conectar con el SGBD.\nSe cerrará la aplicación.\nRevise el archivo de configuración 'config.prop'.", "Abrir conexión con el SGBD");
                }
            }
        }
    }
}