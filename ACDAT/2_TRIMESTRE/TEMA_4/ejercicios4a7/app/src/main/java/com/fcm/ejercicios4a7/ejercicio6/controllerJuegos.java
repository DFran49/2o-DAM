/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.ejercicios4a7.ejercicio6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.text.ParseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Francisco
 */
public class ControllerJuegos {
    
    @FXML 
    private TableView<Juego> twJuegos;
    @FXML 
    private TableColumn<Juego, String> colTitulo;
    @FXML 
    private TableColumn<Juego, String> colGenero;
    @FXML 
    private TableColumn<Juego, Double> colPrecio;
    @FXML 
    private TableColumn<Juego, String> colLanzamiento;
    @FXML 
    private ComboBox<String> cbGenero;

    private MongoCollection<Document> collectionJuegos;
    private ObservableList<Juego> juegosList = FXCollections.observableArrayList();
    
    @FXML
    private void actualizarTabla() {
        juegosList.clear();
        for (Document doc : collectionJuegos.find()) {
            juegosList.add(new Juego(
                    doc.getString("titulo"),
                    doc.getString("genero"),
                    doc.getDouble("precio"),
                    doc.getDate("fecha_lanzamiento") != null ? new SimpleDateFormat("yyyy-MM-dd").format(doc.getDate("fecha_lanzamiento")) : ""
            ));
        }
        twJuegos.setItems(juegosList);
    }

    @FXML
    private void añadirJuego() {
        TextField tituloField = new TextField();
        TextField generoField = new TextField();
        TextField precioField = new TextField();
        TextField fechaField = new TextField();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Título:"), 0, 0);
        grid.add(tituloField, 1, 0);
        grid.add(new Label("Género:"), 0, 1);
        grid.add(generoField, 1, 1);
        grid.add(new Label("Precio:"), 0, 2);
        grid.add(precioField, 1, 2);
        grid.add(new Label("Fecha de Lanzamiento (YYYY-MM-DD):"), 0, 3);
        grid.add(fechaField, 1, 3);

        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Añadir Juego");
        dialog.setHeaderText(null);
        dialog.getDialogPane().setContent(grid);
        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String titulo = tituloField.getText();
                if (titulo == null || titulo.trim().isEmpty()) {
                    mostrarError("El título es obligatorio.");
                    return;
                }

                if (collectionJuegos.find(Filters.eq("titulo", titulo)).first() != null) {
                    mostrarError("Ya existe un juego con ese título.");
                    return;
                }

                String genero = generoField.getText();
                double precio = precioField.getText().isEmpty() ? 0.0 : Double.parseDouble(precioField.getText());
                String fechaStr = fechaField.getText();

                Document nuevoJuego = new Document("titulo", titulo)
                        .append("genero", genero)
                        .append("precio", precio);
                if (!fechaStr.isEmpty()) {
                    try {
                        nuevoJuego.append("fecha_lanzamiento", new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr));
                    } catch (Exception e) {
                        mostrarError("Formato de fecha inválido.");
                        return;
                    }
                }

                collectionJuegos.insertOne(nuevoJuego);
                actualizarTabla();
                cargarGeneros();
            }
        });
    }

    @FXML
    private void borrarPorGenero() {
        String genero = cbGenero.getSelectionModel().getSelectedItem();
        if (genero != null && !genero.isEmpty()) {
            collectionJuegos.deleteMany(Filters.eq("genero", genero));
            actualizarTabla();
            cargarGeneros();
        }
    }

    @FXML
    private void actualizarJuego() {
        Juego selectedJuego = twJuegos.getSelectionModel().getSelectedItem();
        if (selectedJuego == null) {
            mostrarError("Selecciona un juego para modificar.");
            return;
        }

        TextField tituloField = new TextField(selectedJuego.getTitulo());
        TextField generoField = new TextField(selectedJuego.getGenero());
        TextField precioField = new TextField(String.valueOf(selectedJuego.getPrecio()));
        TextField fechaField = new TextField(selectedJuego.getFechaLanzamiento());

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Título:"), 0, 0);
        grid.add(tituloField, 1, 0);
        grid.add(new Label("Género:"), 0, 1);
        grid.add(generoField, 1, 1);
        grid.add(new Label("Precio:"), 0, 2);
        grid.add(precioField, 1, 2);
        grid.add(new Label("Fecha de Lanzamiento (YYYY-MM-DD):"), 0, 3);
        grid.add(fechaField, 1, 3);

        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Modificar Juego");
        dialog.setHeaderText(null);
        dialog.getDialogPane().setContent(grid);
        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String nuevoTitulo = tituloField.getText();
                String nuevoGenero = generoField.getText();
                double nuevoPrecio = precioField.getText().isEmpty() ? 0.0 : Double.parseDouble(precioField.getText());
                String nuevaFechaStr = fechaField.getText();

                try {
                    collectionJuegos.updateOne(Filters.eq("titulo", selectedJuego.getTitulo()), Updates.combine(
                            Updates.set("titulo", nuevoTitulo),
                            Updates.set("genero", nuevoGenero),
                            Updates.set("precio", nuevoPrecio),
                            Updates.set("fecha_lanzamiento", nuevaFechaStr.isEmpty() ? null : new SimpleDateFormat("yyyy-MM-dd").parse(nuevaFechaStr))
                    ));
                } catch (ParseException ex) {
                    Logger.getLogger(ControllerJuegos.class.getName()).log(Level.SEVERE, null, ex);
                }

                actualizarTabla();
                cargarGeneros();
            }
        });
    }
    
    @FXML
    public void initialize() {
        MongoClient mongoClient = MongoClients.create("mongodb://root:root@localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("Francisco");
        collectionJuegos = database.getCollection("juegos");

        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colLanzamiento.setCellValueFactory(new PropertyValueFactory<>("fechaLanzamiento"));

        actualizarTabla();
        cargarGeneros();
    }
    
    private void cargarGeneros() {
        List<String> generos = collectionJuegos.distinct("genero", String.class).into(new ArrayList<>());
        cbGenero.setItems(FXCollections.observableArrayList(generos));
    }

    private void mostrarError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
