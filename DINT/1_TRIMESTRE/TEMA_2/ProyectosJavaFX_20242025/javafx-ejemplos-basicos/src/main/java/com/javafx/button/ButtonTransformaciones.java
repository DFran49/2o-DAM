package com.javafx.button;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;


public class ButtonTransformaciones extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Button button=new Button();

        button.setText("Haz Click!");

        button.setOnAction((event) -> {
            System.out.println("Botón pulsado!");
        });

        Scale scaleTransformation=new Scale();
        scaleTransformation.setX(3.0);//3 veces ancho
        scaleTransformation.setY(2.0);//2 veces alto
        scaleTransformation.setPivotX(0);// scaleTransformation.setPivotX(50);
        scaleTransformation.setPivotY(0);

        button.getTransforms().add(scaleTransformation);

        VBox  vbox =new VBox(button);
        Scene scene=new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setWidth(512);
        primaryStage.setHeight(256);
        primaryStage.show();
    }

}
