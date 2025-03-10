/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.ejercicio6;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.net.URL;
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
 * @author allae
 */

public class JuegosController {
    
    @FXML 
    private TableView<Juego> juegosTable;
    @FXML 
    private TableColumn<Juego, String> tituloCol;
    @FXML 
    private TableColumn<Juego, String> generoCol;
    @FXML 
    private TableColumn<Juego, Double> precioCol;
    @FXML 
    private TableColumn<Juego, String> fechaLanzamientoCol;
    @FXML 
    private ComboBox<String> generoCombo;

    private MongoCollection<Document> juegosCollection;
    private ObservableList<Juego> juegosList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Conectar a MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://root:root@localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("Allae");
        juegosCollection = database.getCollection("juegos");

        tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        generoCol.setCellValueFactory(new PropertyValueFactory<>("genero"));
        precioCol.setCellValueFactory(new PropertyValueFactory<>("precio"));
        fechaLanzamientoCol.setCellValueFactory(new PropertyValueFactory<>("fechaLanzamiento"));

        refreshTable();
        loadGeneros();
    }

    @FXML
    private void refreshTable() {
        juegosList.clear();
        for (Document doc : juegosCollection.find()) {
            juegosList.add(new Juego(
                    doc.getString("titulo"),
                    doc.getString("genero"),
                    doc.getDouble("precio"),
                    doc.getDate("fecha_lanzamiento") != null ? new SimpleDateFormat("yyyy-MM-dd").format(doc.getDate("fecha_lanzamiento")) : ""
            ));
        }
        juegosTable.setItems(juegosList);
    }

    private void loadGeneros() {
        
        List<String> generos = juegosCollection.distinct("genero", String.class).into(new ArrayList<>());

        generoCombo.setItems(FXCollections.observableArrayList(generos));
    }

    @FXML
    private void addJuego() {
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
                    showError("El título es obligatorio.");
                    return;
                }

                if (juegosCollection.find(Filters.eq("titulo", titulo)).first() != null) {
                    showError("Ya existe un juego con ese título.");
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
                        showError("Formato de fecha inválido.");
                        return;
                    }
                }

                juegosCollection.insertOne(nuevoJuego);
                refreshTable();
                loadGeneros();
            }
        });
    }

    @FXML
    private void deleteByGenero() {
        String genero = generoCombo.getSelectionModel().getSelectedItem();
        if (genero != null && !genero.isEmpty()) {
            juegosCollection.deleteMany(Filters.eq("genero", genero));
            refreshTable();
            loadGeneros();
        }
    }

    @FXML
    private void updateJuego() {
        Juego selectedJuego = juegosTable.getSelectionModel().getSelectedItem();
        if (selectedJuego == null) {
            showError("Selecciona un juego para modificar.");
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
                    juegosCollection.updateOne(Filters.eq("titulo", selectedJuego.getTitulo()), Updates.combine(
                            Updates.set("titulo", nuevoTitulo),
                            Updates.set("genero", nuevoGenero),
                            Updates.set("precio", nuevoPrecio),
                            Updates.set("fecha_lanzamiento", nuevaFechaStr.isEmpty() ? null : new SimpleDateFormat("yyyy-MM-dd").parse(nuevaFechaStr))
                    ));
                } catch (ParseException ex) {
                    Logger.getLogger(JuegosController.class.getName()).log(Level.SEVERE, null, ex);
                }

                refreshTable();
                loadGeneros();
            }
        });
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
