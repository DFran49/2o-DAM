package jl.conferenciasapp.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import jl.conferenciasapp.daos.PonenteDAO;
import jl.conferenciasapp.models.Ponente;
import jl.conferenciasapp.utils.FuncionesVarias;
import jl.conferenciasapp.utils.ValidadorCampos;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

public class PonentesController implements Initializable {
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
    private TableColumn<Ponente, String> colCodigo;

    @FXML
    private TableColumn<Ponente, String> colEspecialidad;

    @FXML
    private TableColumn<Ponente, String> colNombre;

    @FXML
    private TableColumn<Ponente, String> colApellido1;

    @FXML
    private TableColumn<Ponente, String> colApellido2;

    @FXML
    private TableView<Ponente> tblPonentes;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido1;

    @FXML
    private TextField txtApellido2;

    @FXML
    private TextField txtEspecialidad;

    @FXML
    private TextField txtFiltrar;

    private final PonenteDAO ponenteDAO = new PonenteDAO();
    ObservableList<Ponente> listaPonentes = FXCollections.observableArrayList();
    private final ObjectProperty<Ponente> objPonente = new SimpleObjectProperty<>();

    private static final int NINGUNA = 0, INSERT = 1, UPDATE = 2;

    private int operacion = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FuncionesVarias.imagenesBotones(btnCancelar, btnEliminar, btnGuardar, btnInsertar, btnModificar);

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        listarPonentes();
        tblPonentes.setItems(listaPonentes);
        objPonente.bind(tblPonentes.getSelectionModel().selectedItemProperty());
    }

    @FXML
    void listarPonentes() {
        try {
            listaPonentes.setAll(ponenteDAO.getAll(txtFiltrar.getText()));
        } catch (SQLException | IOException e) {
            FuncionesVarias.mostrarError("Error al cargar la lista de ponentes.", "Listar ponentes");
        }
    }

    void controlesInicio() {
        FuncionesVarias.botonesInicio(btnCancelar, btnEliminar, btnGuardar, btnInsertar, btnModificar);
        FuncionesVarias.clearAndDisableControls(Arrays.asList(txtCodigo, txtNombre, txtApellido1, txtApellido2, txtEspecialidad));
    }

    void controlesInsertarModificar(int oper) {
        FuncionesVarias.botonesInsertarModificar(btnCancelar, btnEliminar, btnGuardar, btnInsertar, btnModificar);
        if (oper == INSERT)
            txtCodigo.setDisable(false);
        FuncionesVarias.enableControls(Arrays.asList(txtNombre, txtApellido1, txtApellido2, txtEspecialidad));
    }

    @FXML
    void cancelar() {
        tblPonentes.getSelectionModel().clearSelection();
        operacion = NINGUNA;
        controlesInicio();
    }

    @FXML
    void guardar() {
        boolean esValido = ValidadorCampos.validarTextField(txtCodigo, "El campo 'Código' no puede estar vacío.", 6)
                && ValidadorCampos.validarTextField(txtNombre, "El campo 'Nombre' no puede estar vacío.", 50)
                && ValidadorCampos.validarTextField(txtApellido1, "El campo 'Apellido 1' no puede estar vacío.", 50)
                && ValidadorCampos.validarTextField(txtApellido2, "El campo 'Apellido 2' no puede estar vacío.", 50)
                && ValidadorCampos.validarTextField(txtEspecialidad, "El campo 'Especialidad' no puede estar vacío.", 50);
        if (esValido) {
            Ponente p = new Ponente();
            p.setCodigo(txtCodigo.getText());
            p.setNombre(txtNombre.getText());
            p.setApellido1(txtApellido1.getText());
            p.setApellido2(txtApellido2.getText());
            p.setEspecialidad(txtEspecialidad.getText());
            boolean errores = false;
            try {
                if (operacion == INSERT) {
                    if (!ponenteDAO.existeRegistro(txtCodigo.getText(), "ponente","codigo")) {
                        ponenteDAO.insert(p);
                    } else {
                        errores = true;
                        FuncionesVarias.mostrarError("El ponente ya existe.\nDebe cambiar el código del ponente.", "Insertar ponente");
                    }
                } else if (operacion == UPDATE) {
                    ponenteDAO.update(p);
                }
            } catch (SQLException | IOException e) {
                errores = true;
                if (operacion == INSERT)
                    FuncionesVarias.mostrarError("El ponente ya existe.\nDebe cambiar el código del ponente.", "Insertar ponente");
                else if (operacion == UPDATE)
                    FuncionesVarias.mostrarError("Error al modificar el ponente.\nCompruebe que los datos son correctos.", "Modificar ponente");
            }
            if (!errores) {
                listarPonentes();
                tblPonentes.getSelectionModel().clearSelection();
                operacion = NINGUNA;
                controlesInicio();
            }
        }
    }

    @FXML
    void eliminar() {
        if (objPonente.get() == null) {
            FuncionesVarias.mostrarError("Debe seleccionar un ponente en la tabla antes de poder eliminarlo.", "Eliminar ponente");
            return;
        }
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea eliminar el ponente seleccionado?", ButtonType.YES, ButtonType.NO);
        a.setHeaderText("Eliminar ponente");
        Optional<ButtonType> result = a.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                if (ponenteDAO.existeRegistro(objPonente.get().getCodigo(), "participar", "codPonente")) {
                    FuncionesVarias.mostrarError("El ponente tiene asignadas ponencias en conferencias.\nDebe eliminarlas antes de poder eliminar el ponente.", "Eliminar ponente");
                } else {
                    ponenteDAO.delete(objPonente.get().getCodigo());
                    listarPonentes();
                }
            } catch (SQLException | IOException e) {
                FuncionesVarias.mostrarError("El ponente tiene asignadas ponencias en conferencias.\nDebe eliminarlas antes de poder eliminar el ponente.", "Eliminar ponente");
            }
        }
        tblPonentes.getSelectionModel().clearSelection();
    }

    @FXML
    void insertar() {
        operacion = INSERT;
        controlesInsertarModificar(operacion);
    }

    @FXML
    void modificar() {
        if (objPonente.get() == null) {
            FuncionesVarias.mostrarError("Debe seleccionar un ponente en la tabla antes de poder modificarlo.", "Modificar ponente");
            return;
        }
        Ponente a;
        try {
            a = ponenteDAO.findByCodigo(objPonente.get().getCodigo());
            txtCodigo.setText(a.getCodigo());
            txtNombre.setText(a.getNombre());
            txtApellido1.setText(a.getApellido1());
            txtApellido2.setText(a.getApellido2());
            txtEspecialidad.setText(a.getEspecialidad());

            operacion = UPDATE;
            controlesInsertarModificar(operacion);
        } catch (SQLException | IOException e) {
            FuncionesVarias.mostrarError("Ponente no encontrado.", "Modificar ponente");
        }
    }
}