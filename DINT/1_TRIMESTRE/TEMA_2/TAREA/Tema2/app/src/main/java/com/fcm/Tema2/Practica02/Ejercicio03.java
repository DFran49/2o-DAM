/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.Tema2.Practica02;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Francisco
 */
public class Ejercicio03 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button btn1 = new Button("Rojo");
        Button btn2 = new Button("Verde");
        Button btn3 = new Button("Azul");
        
        StackPane stpContenedor = new StackPane();
        
        Pane pnRed = new Pane();
        BackgroundFill fondoColor = new BackgroundFill(Color.RED, new CornerRadii(0), null);
        pnRed.setBackground(new Background(fondoColor));
        pnRed.setPrefSize(500, 400);
        Pane pnGreen = new Pane();
        fondoColor = new BackgroundFill(Color.GREEN, new CornerRadii(0), null);
        pnGreen.setBackground(new Background(fondoColor));
        pnGreen.setPrefSize(500, 400);
        Pane pnBlue = new Pane();
        fondoColor = new BackgroundFill(Color.BLUE, new CornerRadii(0), null);
        pnBlue.setBackground(new Background(fondoColor));
        pnBlue.setPrefSize(500, 400);
        
        stpContenedor.getChildren().addAll(pnRed, pnGreen, pnBlue);
        
        btn1.setOnAction( e -> {
            pnRed.setVisible(true);
            pnGreen.setVisible(false);
            pnBlue.setVisible(false);
        });
        
        btn2.setOnAction( e -> {
            pnRed.setVisible(false);
            pnGreen.setVisible(true);
            pnBlue.setVisible(false);
        });
        
        btn3.setOnAction( e -> {
            pnRed.setVisible(false);
            pnGreen.setVisible(false);
            pnBlue.setVisible(true);
        });
        
        

        VBox vbox=new VBox(10); //Layout padre (contenedor de nodos). 10 px separación
        vbox.getChildren().addAll(btn1, btn2, btn3);
        
        BorderPane padre = new BorderPane();
        padre.setLeft(vbox);
        padre.setCenter(stpContenedor);
        
        vbox.setPadding(new Insets(20));//Borde interno 20px
        Scene scene=new Scene(padre, 500,400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
