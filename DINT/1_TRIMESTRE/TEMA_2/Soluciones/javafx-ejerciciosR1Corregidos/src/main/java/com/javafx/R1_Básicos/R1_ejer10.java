package com.javafx.R1_Básicos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class R1_ejer10 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menú");

        //Crear el menú Fichero
        Menu menuFichero=new Menu("Fichero");
        MenuItem nuevoItem=new MenuItem("Nuevo");
        MenuItem abrirItem=new MenuItem("Abrir");
        MenuItem guardarItem=new MenuItem("Guardar");
        MenuItem separador1=new SeparatorMenuItem();
        MenuItem salirItem=new MenuItem("Salir");
        menuFichero.getItems().addAll(nuevoItem, abrirItem, guardarItem, separador1, salirItem);

        //Crear el menú Clientes
        Menu menuClientes=new Menu("Clientes");
        MenuItem listarClientesItem=new MenuItem("Listar");
        MenuItem separador2=new SeparatorMenuItem();
        MenuItem informeClientesItem=new MenuItem("Informe Clientes");
        menuClientes.getItems().addAll(listarClientesItem, separador2, informeClientesItem);

        //Crear el menú Productos
        Menu menuProductos=new Menu("Productos");
        MenuItem listarProductosItem=new MenuItem("Listar");
        MenuItem separador3=new SeparatorMenuItem();
        MenuItem informeProductosItem=new MenuItem("Informe Productos");
        menuProductos.getItems().addAll(listarProductosItem, separador3, informeProductosItem);

        //Crear la barra de menú
        MenuBar menuBar=new MenuBar();
        menuBar.getMenus().addAll(menuFichero, menuClientes, menuProductos);

        //Configurar eventos de menú
        salirItem.setOnAction(e -> System.exit(0));

        //Crear el diseño de la escena
        BorderPane borderPane=new BorderPane();
        borderPane.setTop(menuBar);

        Scene scene=new Scene(borderPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

