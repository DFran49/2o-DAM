package jl.conferenciasapp.utils;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class FuncionesVarias {
    public static void imagenesBotones(Button btnCancelar, Button btnEliminar, Button btnGuardar, Button btnInsertar, Button btnModificar) {
        Image imageGuardar = new Image(Objects.requireNonNull(FuncionesVarias.class.getResourceAsStream("/images/guardar.png")));
        ImageView imageViewGuardar = new ImageView(imageGuardar);
        btnGuardar.setGraphic(imageViewGuardar);

        Image imageCancelar = new Image(Objects.requireNonNull(FuncionesVarias.class.getResourceAsStream("/images/cancelar.png")));
        ImageView imageViewCancelar = new ImageView(imageCancelar);
        btnCancelar.setGraphic(imageViewCancelar);

        Image imageInsertar = new Image(Objects.requireNonNull(FuncionesVarias.class.getResourceAsStream("/images/insertar.png")));
        ImageView imageViewInsertar = new ImageView(imageInsertar);
        btnInsertar.setGraphic(imageViewInsertar);

        Image imageModificar = new Image(Objects.requireNonNull(FuncionesVarias.class.getResourceAsStream("/images/modificar.png")));
        ImageView imageViewModificar = new ImageView(imageModificar);
        btnModificar.setGraphic(imageViewModificar);

        Image imageEliminar = new Image(Objects.requireNonNull(FuncionesVarias.class.getResourceAsStream("/images/eliminar.png")));
        ImageView imageViewEliminar = new ImageView(imageEliminar);
        btnEliminar.setGraphic(imageViewEliminar);
    }

    public static void botonesInicio(Button btnCancelar, Button btnEliminar, Button btnGuardar, Button btnInsertar, Button btnModificar) {
        btnInsertar.setDisable(false);
        btnEliminar.setDisable(false);
        btnModificar.setDisable(false);
        btnGuardar.setDisable(true);
        btnCancelar.setDisable(true);
    }

    public static void botonesInsertarModificar(Button btnCancelar, Button btnEliminar, Button btnGuardar, Button btnInsertar, Button btnModificar) {
        btnInsertar.setDisable(true);
        btnEliminar.setDisable(true);
        btnModificar.setDisable(true);
        btnGuardar.setDisable(false);
        btnCancelar.setDisable(false);
        btnCancelar.requestFocus();
    }

    public static void mostrarError(String mensaje, String header) {
        Alert err = new Alert(Alert.AlertType.ERROR, mensaje, ButtonType.CLOSE);
        err.setHeaderText(header);
        err.showAndWait();
    }

    public static <S> Callback<TableColumn<S, LocalDate>, TableCell<S, LocalDate>> getDateCellFactory() {
        return col -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                }
            }
        };
    }

    public static void clearAndDisableControls(List<? extends Control> controls) {
        for (Control control : controls) {
            control.setDisable(true);
            switch (control) {
                case TextField textField -> textField.setText("");
                case DatePicker datePicker -> datePicker.setValue(null);
                case ComboBox<?> comboBox -> comboBox.getSelectionModel().clearSelection();
                default -> {
                }
            }
        }
    }

    public static void enableControls(List<? extends Control> controls) {
        for (Control control : controls) {
            control.setDisable(false);
        }
    }

    public static void configureDatePickerFormat(DatePicker datePicker, DateTimeFormatter formatter) {
        datePicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? formatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty() && ValidadorCampos.validarCadenaFecha(string))
                        ? LocalDate.parse(string, formatter)
                        : null;
            }
        });
    }
}
