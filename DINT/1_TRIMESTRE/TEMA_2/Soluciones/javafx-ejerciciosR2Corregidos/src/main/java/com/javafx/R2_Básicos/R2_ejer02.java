package com.javafx.R2_Básicos; //Modificar al package correcto

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class R2_ejer02 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Crear un StackPane con 3 paneles
        StackPane stackPane = new StackPane();
        Pane panel1 = new Pane();
        Pane panel2 = new Pane();
        Pane panel3 = new Pane();

        panel1.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(0), null)));
        panel2.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(0), null)));
        panel3.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, new CornerRadii(0), null)));

        // Agregar un TabPane 
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Pestaña 1");
        Pane pane = new Pane();
        BackgroundFill fondoColor = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, null);
        Background background = new Background(fondoColor);
        pane.setPrefSize(200, 200);
        pane.setBackground(background);
        tab1.setContent(pane);
        tab1.setClosable(false);

        Tab tab2 = new Tab("Pestaña 2");
        tab2.setClosable(false);

        Tab tab3 = new Tab("Pestaña 3");
        tab3.setClosable(false);
        tabPane.getTabs().addAll(tab1, tab2, tab3);
        panel1.getChildren().add(tabPane);

        stackPane.getChildren().addAll(panel1, panel2, panel3);
        root.setCenter(stackPane);

        // Crear ToolBar con 3 botones
        ToolBar toolBar = new ToolBar();
        Button btnPanel1 = new Button("Panel 1");
        Button btnPanel2 = new Button("Panel 2");
        Button btnPanel3 = new Button("Panel 3");

        btnPanel1.setOnAction(e -> {
            panel1.toFront();
        });
        btnPanel2.setOnAction(e -> {
            panel2.toFront();
        });
        btnPanel3.setOnAction(e -> {
            panel3.toFront();
        });

        toolBar.getItems().addAll(btnPanel1, btnPanel2, btnPanel3);
        root.setTop(toolBar);

        toolBar.setPadding(new Insets(10));
        tabPane.setPadding(new Insets(20));
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle("Interfaz con ToolBar y StackPane");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
