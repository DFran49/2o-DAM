package jl.conferenciasapp.utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class ValidadorCampos {
    // Validar campo de texto para no estar vacío y no superar un tamaño dado
    public static boolean validarTextField(TextField campo, String mensaje, int maxSize) {
        String texto = (campo.getText() != null) ? campo.getText().trim() : null;
        if (texto == null || texto.isEmpty()) {
            FuncionesVarias.mostrarError(mensaje,"Campo vacío");
            campo.requestFocus();
            return false;
        } else if (texto.length() > maxSize) {
            FuncionesVarias.mostrarError("El campo no puede exceder " + maxSize + " caracteres.","Tamaño excedido");
            campo.requestFocus();
            return false;
        }
        return true;
    }

    // Validar campo numérico con o sin decimales, con un total de "n" dígitos en total y "d" dígitos decimales
    public static boolean validarTextFieldNumerico(TextField campo, String mensaje, int totalDigitos, int digitosDecimales) {
        String texto = (campo.getText() != null) ? campo.getText().trim() : null;
        if (texto == null || texto.isEmpty()) {
            FuncionesVarias.mostrarError(mensaje, "Campo vacío");
            campo.requestFocus();
            return false;
        } else if (!texto.matches("^\\d{1," + (totalDigitos - digitosDecimales) + "}(\\.\\d{1," + digitosDecimales + "})?$")) {
            FuncionesVarias.mostrarError("El campo debe ser un número con un máximo de " + totalDigitos + " dígitos\ny " + digitosDecimales + " decimales (" + (totalDigitos - digitosDecimales) + " dígitos para la parte entera).", "Formato inválido");
            campo.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean validarTextFieldNumerico(TextField campo, String mensaje, int totalDigitos) {
        String texto = (campo.getText() != null) ? campo.getText().trim() : null;
        if (texto == null || texto.isEmpty()) {
            FuncionesVarias.mostrarError(mensaje, "Campo vacío");
            campo.requestFocus();
            return false;
        } else if (!texto.matches("^\\d{1," + totalDigitos + "}?$")) {
            FuncionesVarias.mostrarError("El campo debe ser un número entero con un máximo\nde " + totalDigitos + " dígitos.", "Formato inválido");
            campo.requestFocus();
            return false;
        }
        return true;
    }

    // Validar DatePicker para asegurarse de que se haya seleccionado una fecha válida
    public static boolean validarDatePicker(DatePicker datePicker) {
        String fechaString = datePicker.getEditor().getText(); // Obtener la cadena de fecha del editor
        if (!validarCadenaFecha(fechaString)) {
            FuncionesVarias.mostrarError("La fecha proporcionada no es válida.", "Fecha incorrecta");
            datePicker.requestFocus();
            return false;
        }
        return true;
    }

    // Validar ComboBox para que se seleccione un valor distinto al pasado como argumento
    public static <T> boolean validarComboBox(ComboBox<T> campo, String mensaje, T valorExcluido) {
        T valorSeleccionado = campo.getValue();
        if (valorSeleccionado == null || valorSeleccionado.equals(valorExcluido)) {
            FuncionesVarias.mostrarError(mensaje, "Selección inválida");
            campo.requestFocus();
            return false;
        }
        return true;
    }

    // Método para validar si una cadena de fecha está en el formato "dd-MM-yyyy"
    public static boolean validarCadenaFecha(String fechaString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy").withResolverStyle(ResolverStyle.STRICT);

        try {
            formatter.parse(fechaString);
            return true; // La cadena de fecha es válida
        } catch (DateTimeParseException e) {
            return false; // La cadena de fecha no es válida
        }
    }
}

