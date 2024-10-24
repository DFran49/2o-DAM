package com.javafx.R1_Básicos; //Modificar al package correcto

import java.util.Collections;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class R1_ejer07 extends Application {

    private TextField notaTextField;
    private ListView<Double> misnotasLV;
    ObservableList<Double> notas;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        notas=FXCollections.observableArrayList();

        notaTextField=new TextField();
        Button agregarButton=new Button("Añadir");
        Button calcularButton=new Button("Calcular");
        Button borrarButton=new Button("Borrar OL");
        misnotasLV=new ListView<>(notas);

        agregarButton.setOnAction(e -> agregarNota());
        calcularButton.setOnAction(e -> calcularNotas());
        borrarButton.setOnAction(e -> notas.clear());

        VBox vbox=new VBox();
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(notaTextField, new HBox(agregarButton, calcularButton, borrarButton), misnotasLV);
        Scene scene=new Scene(vbox, 300, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void agregarNota() {
        try {
            double nota=Double.parseDouble(notaTextField.getText());
            notas.add(nota);//No hace falta añadir al  ListView porque ya está asociado al OL
            notaTextField.clear();
        } catch (NumberFormatException e) {
            alerta(Alert.AlertType.ERROR, "Error", "Número incorrecto", null);

        }
    }

    private void calcularNotas() {
        if (!notas.isEmpty()) {
            double max=Collections.max(notas);
            double min=Collections.min(notas);
            double sum=0;
            //Se recorre el LV
            for (Double nota : notas) {
                sum += nota;
            }
            double media=sum / notas.size();
            alerta(Alert.AlertType.INFORMATION, "Resultados",
                    "Resultados de las notas", notas.size()+" Nota(s) introducida(s) \nMayor nota: "
                    + max + "\nMenor nota: " + min + "\nMedia: " + media);
        }

    }

    private void alerta(AlertType tipo, String tit, String header, String contenido) {
        Alert alert=new Alert(tipo);
        alert.setTitle(tit);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
