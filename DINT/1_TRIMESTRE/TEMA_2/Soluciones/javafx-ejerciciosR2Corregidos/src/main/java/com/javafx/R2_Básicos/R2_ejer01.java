package com.javafx.R2_Básicos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Plantilla JAVAFX
 */
public class R2_ejer01 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        Label etiqPrecio=new Label("Precio (€)");
        TextField precio=new TextField();
        Label etiqDesc=new Label("Descuento (%)");
        TextField descuento=new TextField();
        Button b =new Button("Calcular");
        
        
        b.setOnAction(evento -> {
            Alert alerta=new Alert(AlertType.INFORMATION);
            alerta.setTitle("Info");
            Float precioFloat=Float.valueOf(precio.getText());
            Float descuentoFloat=Float.valueOf(descuento.getText());
            Float desc=precioFloat * (descuentoFloat/100);
            Float res=precioFloat-desc;
            alerta.setHeaderText("Descuento aplicado. Precio inicial "+precio.getText());
            alerta.setContentText("Descuento : "+desc.toString()+" Precio final: "+res.toString());
            alerta.show();
        });
        
   
        //VBox cont=new VBox();
        //HBox cont=new HBox();
        //Pane cont=new Pane();
        StackPane cont=new StackPane();
        cont.getChildren().addAll(etiqPrecio, precio, etiqDesc,descuento, b);
        
        
        Scene scene=new Scene(cont);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ejer1");
        primaryStage.show();
    }

}
