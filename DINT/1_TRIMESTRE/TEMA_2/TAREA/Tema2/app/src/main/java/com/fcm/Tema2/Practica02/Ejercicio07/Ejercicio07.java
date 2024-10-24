/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.Tema2.Practica02.Ejercicio07;

import static java.lang.Integer.parseInt;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Plantilla JAVAFX
 * Autor:
 * Curso y año:
 * Objetivo de esta clase:
 */
public class Ejercicio07 extends Application {
    GridPane gridPane;
    ObservableList<Cerveza> listCerveza;
    Image imagenCargada;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        gridPane=new GridPane();
        listCerveza = FXCollections.observableArrayList();
        for (int i=0; i<12; i++) {
            listCerveza.add(new Cerveza(new Image("file:src/main/resources/alhambra-roja.png"),"Alhambra "+i,"Española",70));
        }
        
        gridPane.setHgap(10); 
        gridPane.setVgap(10); 
        gridPane.setPadding(new Insets(30)); 
        
        rellenarGrid();
        
        BorderPane padre = new BorderPane();
        MenuBar menu = new MenuBar();
        padre.setTop(menu);
        
        Menu archivo = new Menu("Archivo");
        MenuItem cargar = new MenuItem("Cargar Imagen");
        archivo.getItems().add(cargar);
        cargar.setOnAction(event -> añadir());
        menu.getMenus().add(archivo);
        
        padre.setCenter(gridPane);
        

        Scene scene=new Scene(padre, 500, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Pane panel(Cerveza cerveza) {
        Pane panel = new Pane();
        Label label = new Label(cerveza.getNombre());
        ImageView imgCerveza = new ImageView(cerveza.getImagen());
        Button btnMostrar = new Button("DATOS");
        btnMostrar.setOnAction((event) -> {
            alerta(cerveza.mostrarInfo()).showAndWait();
        });
        imgCerveza.setFitHeight(50);
        imgCerveza.setFitWidth(50);
        
        Button btnBorrar = new Button("BORRAR");
        btnBorrar.setOnAction((event) -> {
            int indice = listCerveza.indexOf(cerveza);
            listCerveza.remove(cerveza);
            rellenarGrid();
        });
        
        VBox vbox=new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(label,imgCerveza,btnMostrar, btnBorrar);
        
        panel.getChildren().add(vbox);
        return panel;
    }
    
    private void rellenarGrid() {
        gridPane.getChildren().clear();
        int i = 0;
        int x = (listCerveza.size()/4)+1;
        for (int fila=0; fila < x; fila++) {
            for (int columna=0; columna <4; columna++) {
                if (i < listCerveza.size()) {
                    Pane panel = panel(listCerveza.get(i));
                    gridPane.add(panel, columna, fila);
                    i++;
                }
            }
        }
    }
    
    private void añadir() {
        Stage crear = new Stage();
        crear.setTitle("Añadir");
        
        Label lblNombre = new Label("Nombre: ");
        TextField txtNombre = new TextField();
        Label lblURL = new Label("URL: ");
        TextField txtURL = new TextField();
        txtURL.disableProperty().set(true);
        Label lblNacionalidad = new Label("Nacionalidad: ");
        TextField txtNacionalidad = new TextField();
        Label lblGraduacion = new Label("Graduación: ");
        TextField txtGraduacion = new TextField();
        Button btnInsertar = new Button("Insertar");
        Button btnCargar = new Button("Cargar imagen");
        
        VBox vertical = new VBox();
        vertical.getChildren().addAll(
                new HBox(10, lblNombre, txtNombre),
                new HBox(10, lblURL, txtURL, btnCargar),
                new HBox(10, lblNacionalidad, txtNacionalidad),
                new HBox(10, lblGraduacion, txtGraduacion),
                btnInsertar
        );
        
        btnCargar.setOnAction(event -> {
            cargarIMG();
        });
        
        btnInsertar.setOnAction(event -> {
            listCerveza.add(new Cerveza(imagenCargada,txtNombre.getText(),txtNacionalidad.getText(),parseInt(txtGraduacion.getText())));
            rellenarGrid();
            crear.close();
        });
        
        crear.setScene(new Scene(vertical,500,800));
        crear.show();
    }
    
    private void cargarIMG() {
        FileChooser escoger = new FileChooser();
        escoger.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de imagen", "*.jpg", "*.png", "*.gif", "*.bmp"));
        java.io.File archivo = escoger.showOpenDialog(null);
        
        if (archivo != null) {
            Image img = new Image(archivo.toURI().toString());
            imagenCargada = img;
        }
    }
    
    private Alert alerta(String texto) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Info de la cerveza");
        alerta.setContentText(texto);
        return alerta;
    }
}
