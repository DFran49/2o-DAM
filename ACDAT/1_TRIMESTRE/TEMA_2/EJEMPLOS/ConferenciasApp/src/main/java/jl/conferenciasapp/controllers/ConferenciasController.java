package jl.conferenciasapp.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import jl.conferenciasapp.daos.ConferenciaDAO;
import jl.conferenciasapp.daos.SalaDAO;
import jl.conferenciasapp.models.Conferencia;
import jl.conferenciasapp.utils.FuncionesVarias;
import jl.conferenciasapp.utils.ValidadorCampos;

public class ConferenciasController implements Initializable {
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
    private TableColumn<Conferencia, String> colCodigo;

    @FXML
    private TableColumn<Conferencia, LocalDate> colFecha;

    @FXML
    private TableColumn<Conferencia, Double> colPrecio;

    @FXML
    private TableColumn<Conferencia, String> colSala;

    @FXML
    private TableColumn<Conferencia, String> colTema;

    @FXML
    private TableColumn<Conferencia, String> colTurno;

    @FXML
    private TableView<Conferencia> tblConferencias;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtTema;

    @FXML
    private TextField txtPrecio;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private ComboBox<String> cbSala;

    @FXML
    private ComboBox<String> cbTurno;

    @FXML
    private TextField txtFiltrar;

    private final ConferenciaDAO conferenciaDAO = new ConferenciaDAO();
    private final SalaDAO salaDAO = new SalaDAO();
    ObservableList<Conferencia> listaConferencias = FXCollections.observableArrayList();
    private final ObjectProperty<Conferencia> objConferencia = new SimpleObjectProperty<>();

    private static final int NINGUNA = 0, INSERT = 1, UPDATE = 2;

    private int operacion = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FuncionesVarias.imagenesBotones(btnCancelar, btnEliminar, btnGuardar, btnInsertar, btnModificar);

        // Crear un DateTimeFormatter para personalizar el formato de visualización
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Establecer el StringConverter en el DatePicker para formatear la fecha seleccionada
        FuncionesVarias.configureDatePickerFormat(dpFecha, formatter);

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("referencia"));
        colTema.setCellValueFactory(new PropertyValueFactory<>("tema"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colPrecio.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item));
                }
            }
        });
        colTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));
        colSala.setCellValueFactory(new PropertyValueFactory<>("sala"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colFecha.setCellFactory(FuncionesVarias.getDateCellFactory());
        listarConferencias();
        tblConferencias.setItems(listaConferencias);
        objConferencia.bind(tblConferencias.getSelectionModel().selectedItemProperty());
    }

    @FXML
    void listarConferencias() {
        try {
            listaConferencias.setAll(conferenciaDAO.getAll(txtFiltrar.getText()));
        } catch (SQLException | IOException e) {
            FuncionesVarias.mostrarError("Error al cargar la lista de conferencias.", "Listar conferencias");
        }
    }

    void controlesInicio() {
        FuncionesVarias.botonesInicio(btnCancelar, btnEliminar, btnGuardar, btnInsertar, btnModificar);
        FuncionesVarias.clearAndDisableControls(Arrays.asList(txtCodigo, txtTema, txtPrecio, dpFecha, cbSala, cbTurno));
    }

    void controlesInsertarModificar(int oper) {
        FuncionesVarias.botonesInsertarModificar(btnCancelar, btnEliminar, btnGuardar, btnInsertar, btnModificar);
        if (oper == INSERT)
            txtCodigo.setDisable(false);
        FuncionesVarias.enableControls(Arrays.asList(txtTema, txtPrecio, dpFecha, cbSala, cbTurno));
    }

    @FXML
    void cancelar() {
        tblConferencias.getSelectionModel().clearSelection();
        operacion = NINGUNA;
        controlesInicio();
    }

    @FXML
    void guardar() {
        boolean esValido = ValidadorCampos.validarTextField(txtCodigo, "El campo 'Código' no puede estar vacío.", 7)
                && ValidadorCampos.validarTextField(txtTema, "El campo 'Tema' no puede estar vacío.", 60)
                && ValidadorCampos.validarTextFieldNumerico(txtPrecio, "El campo 'precio' no puede estar vacío.", 7, 2)
                && ValidadorCampos.validarDatePicker(dpFecha)
                && ValidadorCampos.validarComboBox(cbTurno, "Se debe seleccionar un turno válido.", "turno")
                && ValidadorCampos.validarComboBox(cbSala, "Se debe seleccionar una sala válida.", "sala");
        if (esValido) {
            Conferencia c = new Conferencia();
            c.setReferencia(txtCodigo.getText());
            c.setTema(txtTema.getText());
            c.setPrecio(Double.parseDouble(txtPrecio.getText()));
            c.setFecha(dpFecha.getValue());
            c.setTurno(cbTurno.getValue());
            c.setSala(cbSala.getValue());
            boolean errores = false;
            try {
                if (operacion == INSERT) {
                    if (!conferenciaDAO.existeRegistro(txtCodigo.getText(), "conferencia", "referencia")) {
                        conferenciaDAO.insert(c);
                    } else {
                        errores = true;
                        FuncionesVarias.mostrarError("La conferencia ya existe.\nDebe cambiar el código de la conferencia.", "Insertar conferencia");
                    }
                } else if (operacion == UPDATE) {
                    conferenciaDAO.update(c);
                }
            } catch (SQLException | IOException e) {
                errores = true;
                if (operacion == INSERT)
                    FuncionesVarias.mostrarError("La conferencia ya existe.\nDebe cambiar el código de la conferencia.", "Insertar conferencia");
                else if (operacion == UPDATE)
                    FuncionesVarias.mostrarError("Error al modificar la conferencia.\nCompruebe que los datos son correctos.", "Modificar conferencia");
            }
            if (!errores) {
                listarConferencias();
                tblConferencias.getSelectionModel().clearSelection();
                operacion = NINGUNA;
                controlesInicio();
            }
        }
    }

    @FXML
    void eliminar() {
        if (objConferencia.get() == null) {
            FuncionesVarias.mostrarError("Debe seleccione una conferencia en la tabla antes de poder eliminarla.", "Eliminar conferencia");
            return;
        }
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea eliminar la conferencia seleccionada?", ButtonType.YES, ButtonType.NO);
        a.setHeaderText("Eliminar conferencia");
        Optional<ButtonType> result = a.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                if (conferenciaDAO.existeRegistro(objConferencia.get().getReferencia(), "asistir","refConferencia") && conferenciaDAO.existeRegistro(objConferencia.get().getReferencia(), "participar","refConferencia")) {
                    FuncionesVarias.mostrarError("La conferencia tiene asignados asistentes o participantes.\nDebe eliminarlos antes de poder eliminar la conferencia.", "Eliminar conferencia");
                } else {
                    conferenciaDAO.delete(objConferencia.get().getReferencia());
                    listarConferencias();
                }
            } catch (SQLException | IOException e) {
                FuncionesVarias.mostrarError("La conferencia tiene asignados asistentes y/o participantes.\nDebe eliminarlos antes de poder eliminar la conferencia.", "Eliminar conferencia");
            }
        }
        tblConferencias.getSelectionModel().clearSelection();
    }

    void cargarTurnos(String valDefecto) {
        if (cbTurno.getItems().isEmpty())
            cbTurno.getItems().addAll(Arrays.asList("turno", "M", "T"));
        cbTurno.setValue(valDefecto);
    }

    void cargarSalas(String valDefecto) throws SQLException, IOException {
        cbSala.getItems().clear();
        cbSala.getItems().addAll(salaDAO.getSalas());
        cbSala.setValue(valDefecto);
    }

    @FXML
    void insertar() {
        try {
            cargarTurnos("turno");
            cargarSalas("sala");

            operacion = INSERT;
            controlesInsertarModificar(operacion);
        } catch (SQLException | IOException e) {
            FuncionesVarias.mostrarError("Error al recuperar datos de las salas.", "Insertar conferencia");
        }
    }

    @FXML
    void modificar() {
        if (objConferencia.get() == null) {
            FuncionesVarias.mostrarError("Debe seleccione una conferencia en la tabla antes de poder modificarla.", "Modificar conferencia");
            return;
        }
        Conferencia c;
        try {
            c = conferenciaDAO.findByCodigo(objConferencia.get().getReferencia());
            txtCodigo.setText(c.getReferencia());
            txtTema.setText(c.getTema());
            txtPrecio.setText(String.valueOf(c.getPrecio()));
            dpFecha.setValue(c.getFecha());
            cargarTurnos(c.getTurno());
            cargarSalas(c.getSala());

            operacion = UPDATE;
            controlesInsertarModificar(operacion);
        } catch (SQLException | IOException e) {
            FuncionesVarias.mostrarError("Conferencia no encontrada.", "Modificar conferencia");
        }
    }
}