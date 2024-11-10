package jl.conferenciasapp.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import jl.conferenciasapp.daos.SalaDAO;
import jl.conferenciasapp.models.Sala;
import jl.conferenciasapp.utils.FuncionesVarias;
import jl.conferenciasapp.utils.ValidadorCampos;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

public class SalasController implements Initializable {
    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnInsertar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<Sala, String> colNombre;

    @FXML
    private TableColumn<Sala, Integer> colCapacidad;

    @FXML
    private TableView<Sala> tblSalas;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCapacidad;

    @FXML
    private TextField txtFiltrar;

    private final SalaDAO salaDAO = new SalaDAO();
    ObservableList<Sala> listaSalas = FXCollections.observableArrayList();
    private final ObjectProperty<Sala> objSala = new SimpleObjectProperty<>();

    private static final int NINGUNA = 0, INSERT = 1, UPDATE = 2;

    private int operacion = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FuncionesVarias.imagenesBotones(btnCancelar, btnEliminar, btnGuardar, btnInsertar, btnModificar);
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        listarSalas();
        tblSalas.setItems(listaSalas);
        objSala.bind(tblSalas.getSelectionModel().selectedItemProperty());
    }

    @FXML
    void listarSalas() {
        try {
            listaSalas.setAll(salaDAO.getAll(txtFiltrar.getText()));
        } catch (SQLException | IOException e) {
            FuncionesVarias.mostrarError("Error al cargar la lista de salas.", "Listar salas");
        }
    }

    void controlesInicio() {
        FuncionesVarias.botonesInicio(btnCancelar, btnEliminar, btnGuardar, btnInsertar, btnModificar);
        FuncionesVarias.clearAndDisableControls(Arrays.asList(txtNombre, txtCapacidad));
    }

    void controlesInsertarModificar(int oper) {
        FuncionesVarias.botonesInsertarModificar(btnCancelar, btnEliminar, btnGuardar, btnInsertar, btnModificar);
        if (oper == INSERT)
            txtNombre.setDisable(false);
        txtCapacidad.setDisable(false);
    }

    @FXML
    void cancelar() {
        tblSalas.getSelectionModel().clearSelection();
        operacion = NINGUNA;
        controlesInicio();
    }

    @FXML
    void guardar() {
        boolean esValido = ValidadorCampos.validarTextField(txtNombre, "El campo 'Nombre' no puede estar vacío.", 50)
                && ValidadorCampos.validarTextFieldNumerico(txtCapacidad, "El campo 'Capacidad' no puede estar vacío.", 5);
        if (esValido) {
            Sala s = new Sala();
            s.setNombre(txtNombre.getText());
            s.setCapacidad(Integer.parseInt(txtCapacidad.getText()));
            boolean errores = false;
            try {
                if (operacion == INSERT) {
                    if (!salaDAO.existeRegistro(txtNombre.getText(), "sala", "nombre")) {
                        salaDAO.insert(s);
                    } else {
                        errores = true;
                        FuncionesVarias.mostrarError("La sala ya existe.\nDebe cambiar el nombre de la sala.", "Insertar sala");
                    }
                } else if (operacion == UPDATE) {
                    salaDAO.update(s);
                }
            } catch (SQLException | IOException e) {
                errores = true;
                if (operacion == INSERT)
                    FuncionesVarias.mostrarError("La sala ya existe.\nDebe cambiar el nombre de la sala.", "Insertar sala");
                else if (operacion == UPDATE)
                    FuncionesVarias.mostrarError("Error al modificar la sala.\nCompruebe que los datos son correctos.", "Modificar sala");
            }
            if (!errores) {
                listarSalas();
                tblSalas.getSelectionModel().clearSelection();
                operacion = NINGUNA;
                controlesInicio();
            }
        }
    }

    @FXML
    void eliminar() {
        if (objSala.get() == null) {
            FuncionesVarias.mostrarError("Debe seleccione una sala en la tabla antes de poder eliminarla.", "Eliminar sala");
            return;
        }
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea eliminar la sala seleccionada?", ButtonType.YES, ButtonType.NO);
        a.setHeaderText("Eliminar sala");
        Optional<ButtonType> result = a.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                if (salaDAO.existeRegistro(objSala.get().getNombre(), "conferencia", "sala")) {
                    FuncionesVarias.mostrarError("La sala tiene asignadas conferencias.\nDebe eliminarlas antes de poder eliminar la sala.", "Eliminar sala");
                } else {
                    salaDAO.delete(objSala.get().getNombre());
                    listarSalas();
                }
            } catch (SQLException | IOException e) {
                FuncionesVarias.mostrarError("La sala tiene asignadas conferencias.\nDebe eliminarlas antes de poder eliminar la sala.", "Eliminar sala");
            }
        }
        tblSalas.getSelectionModel().clearSelection();
    }

    @FXML
    void insertar() {
        operacion = INSERT;
        controlesInsertarModificar(operacion);
    }

    @FXML
    void modificar() {
        if (objSala.get() == null) {
            FuncionesVarias.mostrarError("Debe seleccione una sala en la tabla antes de poder modificarla.", "Modificar sala");
            return;
        }
        Sala s;
        try {
            s = salaDAO.findByCodigo(objSala.get().getNombre());
            txtNombre.setText(s.getNombre());
            txtCapacidad.setText(String.valueOf(s.getCapacidad()));

            operacion = UPDATE;
            controlesInsertarModificar(operacion);
        } catch (SQLException | IOException e) {
            FuncionesVarias.mostrarError("Sala no encontrada.", "Modificar sala");
        }
    }
}