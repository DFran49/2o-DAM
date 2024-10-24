package com.javafx.R1_Básicos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Plantilla JAVAFX
 */
public class R1_ejer01 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        //Se compone la interfaz
        Label etiqPrecio=new Label("Precio (€)");
        TextField precio=new TextField();
        Label etiqDesc=new Label("Descuento (%)");
        TextField descuento=new TextField();
        Button b =new Button("Calcular");
        VBox vbox=new VBox();
        vbox.getChildren().addAll(etiqPrecio, precio, etiqDesc,descuento, b);
        
        //No se validan campos!!
        b.setOnAction(evento -> {
            Alert alerta=new Alert(AlertType.INFORMATION);
            alerta.setTitle("Info");
            
            //Hay que hacer conversiones
            Float precioFloat=Float.valueOf(precio.getText());
            Float descuentoFloat=Float.valueOf(descuento.getText());
            Float desc=precioFloat * (descuentoFloat/100);
            Float res=precioFloat-desc;
            
            alerta.setHeaderText("Descuento aplicado. Precio inicial "+precio.getText());
            alerta.setContentText("Descuento : "+desc.toString()+" Precio final: "+res.toString());
            alerta.show();
        });
        
        Scene scene=new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ejer1");
        primaryStage.show();
    }

}
