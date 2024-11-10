package jl.conferenciasapp.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import jl.conferenciasapp.daos.AsistenteDAO;
import jl.conferenciasapp.models.Asistente;
import jl.conferenciasapp.utils.FuncionesVarias;
import jl.conferenciasapp.utils.ValidadorCampos;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

public class AsistentesController implements Initializable {
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
    private TableColumn<Asistente, String> colCodigo;

    @FXML
    private TableColumn<Asistente, LocalDate> colFechaNac;

    @FXML
    private TableColumn<Asistente, String> colEmpresa;

    @FXML
    private TableColumn<Asistente, String> colNombre;

    @FXML
    private TableColumn<Asistente, String> colApellido1;

    @FXML
    private TableColumn<Asistente, String> colApellido2;

    @FXML
    private TableColumn<Asistente, String> colSexo;

    @FXML
    private TableView<Asistente> tblAsistentes;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido1;

    @FXML
    private TextField txtApellido2;

    @FXML
    private ComboBox<String> cbSexo;

    @FXML
    private DatePicker dpFechaNac;

    @FXML
    private TextField txtEmpresa;

    @FXML
    private TextField txtFiltrar;

    private final AsistenteDAO asistenteDAO = new AsistenteDAO();
    ObservableList<Asistente> listaAsistentes = FXCollections.observableArrayList();
    private final ObjectProperty<Asistente> objAsistente = new SimpleObjectProperty<>();

    private static final int NINGUNA = 0, INSERT = 1, UPDATE = 2;

    private int operacion = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FuncionesVarias.imagenesBotones(btnCancelar, btnEliminar, btnGuardar, btnInsertar, btnModificar);

        // Crear un DateTimeFormatter para personalizar el formato de visualización
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Establecer el StringConverter en el DatePicker para formatear la fecha seleccionada
        FuncionesVarias.configureDatePickerFormat(dpFechaNac, formatter);

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));

        colFechaNac.setCellValueFactory(new PropertyValueFactory<>("fechaNac"));
        colFechaNac.setCellFactory(FuncionesVarias.getDateCellFactory());

        colEmpresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        listarAsistentes();
        tblAsistentes.setItems(listaAsistentes);
        objAsistente.bind(tblAsistentes.getSelectionModel().selectedItemProperty());
    }

    @FXML
    void listarAsistentes() {
        try {
            listaAsistentes.setAll(asistenteDAO.getAll(txtFiltrar.getText()));
        } catch (SQLException | IOException e) {
            FuncionesVarias.mostrarError("Error al cargar la lista de asistentes.", "Listar asistentes");
        }
    }

    void controlesInicio() {
        FuncionesVarias.botonesInicio(btnCancelar, btnEliminar, btnGuardar, btnInsertar, btnModificar);
        FuncionesVarias.clearAndDisableControls(Arrays.asList(txtCodigo, txtNombre, txtApellido1, txtApellido2, cbSexo, dpFechaNac, txtEmpresa));
    }

    void controlesInsertarModificar(int oper) {
        FuncionesVarias.botonesInsertarModificar(btnCancelar, btnEliminar, btnGuardar, btnInsertar, btnModificar);
        if (oper == INSERT)
            txtCodigo.setDisable(false);
        FuncionesVarias.enableControls(Arrays.asList(txtNombre, txtApellido1, txtApellido2, cbSexo, dpFechaNac, txtEmpresa));
    }

    @FXML
    void cancelar() {
        tblAsistentes.getSelectionModel().clearSelection();
        operacion = NINGUNA;
        controlesInicio();
    }

    @FXML
    void guardar() {
        boolean esValido = ValidadorCampos.validarTextField(txtCodigo, "El campo 'Código' no puede estar vacío.", 6)
                && ValidadorCampos.validarTextField(txtNombre, "El campo 'Nombre' no puede estar vacío.", 50)
                && ValidadorCampos.validarTextField(txtApellido1, "El campo 'Apellido 1' no puede estar vacío.", 50)
                && ValidadorCampos.validarTextField(txtApellido2, "El campo 'Apellido 2' no puede estar vacío.", 50)
                && ValidadorCampos.validarComboBox(cbSexo, "Se debe seleccionar un sexo válido.", "sexo")
                && ValidadorCampos.validarDatePicker(dpFechaNac)
                && ValidadorCampos.validarTextField(txtEmpresa, "El campo 'Empresa' no puede estar vacío.", 50);
        if (esValido) {
            Asistente a = new Asistente();
            a.setCodigo(txtCodigo.getText());
            a.setNombre(txtNombre.getText());
            a.setApellido1(txtApellido1.getText());
            a.setApellido2(txtApellido2.getText());
            a.setSexo(cbSexo.getValue());
            a.setFechaNac(dpFechaNac.getValue());
            a.setEmpresa(txtEmpresa.getText());
            boolean errores = false;
            try {
                if (operacion == INSERT) {
                    if (!asistenteDAO.existeRegistro(txtCodigo.getText(), "asistente","codigo")) {
                        asistenteDAO.insert(a);
                    } else {
                        errores = true;
                        FuncionesVarias.mostrarError("El asistente ya existe.\nDebe cambiar el código del asistente.", "Insertar asistente");
                    }
                } else if (operacion == UPDATE) {
                    asistenteDAO.update(a);
                }
            } catch (SQLException | IOException e) {
                errores = true;
                if (operacion == INSERT)
                    FuncionesVarias.mostrarError("El asistente ya existe.\nDebe cambiar el código del asistente.", "Insertar asistente");
                else if (operacion == UPDATE)
                    FuncionesVarias.mostrarError("Error al modificar el asistente.\nCompruebe que los datos son correctos.", "Modificar asistente");
            }
            if (!errores) {
                listarAsistentes();
                tblAsistentes.getSelectionModel().clearSelection();
                operacion = NINGUNA;
                controlesInicio();
            }
        }
    }

    @FXML
    void eliminar() {
        if (objAsistente.get() == null) {
            FuncionesVarias.mostrarError("Debe seleccionar un asistente en la tabla antes de poder eliminarlo.", "Eliminar asistente");
            return;
        }
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea eliminar el asistente seleccionado?", ButtonType.YES, ButtonType.NO);
        a.setHeaderText("Eliminar asistente");
        Optional<ButtonType> result = a.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                if (asistenteDAO.existeRegistro(objAsistente.get().getCodigo(), "asistir", "codAsistente")) {
                    FuncionesVarias.mostrarError("El asistente tiene asignadas asistencias a conferencias.\nDebe eliminarlas antes de poder eliminar el asistente.", "Eliminar asistente");
                } else {
                    asistenteDAO.delete(objAsistente.get().getCodigo());
                    listarAsistentes();
                }
            } catch (SQLException | IOException e) {
                FuncionesVarias.mostrarError("El asistente tiene asignadas asistencias a conferencias.\nDebe eliminarlas antes de poder eliminar el asistente.", "Eliminar asistente");
            }
        }
        tblAsistentes.getSelectionModel().clearSelection();
    }

    void cargarSexos(String valDefecto) {
        if (cbSexo.getItems().isEmpty())
            cbSexo.getItems().addAll(Arrays.asList("sexo", "H", "M"));
        cbSexo.setValue(valDefecto);
    }

    @FXML
    void insertar() {
        cargarSexos("sexo");
        operacion = INSERT;
        controlesInsertarModificar(operacion);
    }

    @FXML
    void modificar() {
        if (objAsistente.get() == null) {
            FuncionesVarias.mostrarError("Debe seleccionar un asistente en la tabla antes de poder modificarlo.", "Modificar asistente");
            return;
        }
        Asistente a;
        try {
            a = asistenteDAO.findByCodigo(objAsistente.get().getCodigo());
            txtCodigo.setText(a.getCodigo());
            txtNombre.setText(a.getNombre());
            txtApellido1.setText(a.getApellido1());
            txtApellido2.setText(a.getApellido2());
            cargarSexos(a.getSexo());
            dpFechaNac.setValue(a.getFechaNac());
            txtEmpresa.setText(a.getEmpresa());

            operacion = UPDATE;
            controlesInsertarModificar(operacion);
        } catch (SQLException | IOException e) {
            FuncionesVarias.mostrarError("Asistente no encontrado.", "Modificar asistente");
        }
    }
}