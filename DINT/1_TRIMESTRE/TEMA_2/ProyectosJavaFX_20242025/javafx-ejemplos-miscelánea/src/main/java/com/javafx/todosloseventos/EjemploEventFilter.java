package com.javafx.todosloseventos;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * EVENTFILTER Y REQUESTFOCUS
 * @author JMMolina
 */
public class EjemploEventFilter extends Application {

    //Muestra un mensaje sobre el primer parámetro que es un textArea
    private void logEvento(TextArea textArea, String message) {
        textArea.appendText(message + "\n");
    }

    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();
        textArea.setPrefHeight(500);
        textArea.setPadding(new Insets(10));
        ObservableList<String> informáticosFamosos = FXCollections.observableArrayList(
                "Ada Lovelace", //Primera programadora
                "Alan Turing", //Padre de la computación moderna
                "Grace Hopper", //Pionera en programación
                "John von Neumann", //Pionero en arquitectura de computadoras
                "Margaret Hamilton", //Desarrolladora del software para las misiones Apolo
                "Donald Knuth", //Padre del análisis de algoritmos
                "Tim Berners-Lee", //Inventor de la World Wide Web
                "Katherine Johnson", //Matemática de la NASA
                "Dennis Ritchie", //Creador del lenguaje C
                "Barbara Liskov", //Pionera en la teoría de tipos de datos
                "Linus Torvalds", //Creador de Linux
                "Jean Bartik", //Una de las primeras programadoras de ENIAC
                "Vinton Cerf", //Padre de Internet
                "Radia Perlman", //Inventora del protocolo STP
                "Steve Wozniak", //Cofundador de Apple
                "Frances Allen", //Pionera en optimización de compiladores
                "James Gosling", //Creador del lenguaje Java
                "Marissa Mayer", //Ejecutiva de tecnología e ingeniera
                "Guido van Rossum", //Creador de Python
                "Sheryl Sandberg" //Directiva de tecnología y autora
        );
        ListView<String> listView = new ListView<>(informáticosFamosos);
        listView.setPrefHeight(600);
        TextField informático = new TextField("");
        textArea.setPromptText("Prueba a darle al ESPACIO");
        informático.setPromptText("Prueba a darle al TAB");
        listView.setTooltip(new Tooltip("Prueba a darle al SUPR"));

        //Requerimos que tome el foco
        //El resto de eventos de Foco, como laa pérdida o ganancia se verán en la parte 2-4 Validación de Campos
        listView.requestFocus();

        //EVENTFILTER: nos permiten centralizar (simplificando) el manejo de eventos (no es necesario enlazar eventos desde SB)
        //Para usar un EVENTFILTER primero creamos un MANEJADOR de eventos y programamos
        //qué queremos que haga según el origen del EVENTO: textArea, TextField o ListView
        //Hay que indicar CATEGORÍA de evento
        EventHandler<KeyEvent> keyEventHandler = event -> {
            Object source = event.getSource();
            if (source == textArea) {
                //Acciones específicas para TextArea. Por ejemplo,contar palabras(espacios). También pasa foco al siguiente Node (ENTER)
                System.out.println("Tecla presionada en el TextArea: " + event.getCode());
                if (event.getCode() == KeyCode.SPACE) {
                    String text = textArea.getText();
                    String[] words = text.split("\\s+");
                    System.out.println("Num. de palabras: " + words.length);
                } else if (event.getCode() == KeyCode.ENTER) {
                    informático.requestFocus();
                }
            } else if (source == informático) {
                //Acciones específicas para caja "informático". Por ejemplo,convertir a mayúsculas si le damos al TABULADOR
                System.out.println("Tecla presionada en el TextField: " + event.getCode());
                if (event.getCode() == KeyCode.TAB) {
                    informático.setText(informático.getText().toUpperCase());
                } else if (event.getCode() == KeyCode.ENTER) {
                    listView.requestFocus();
                }
            } else if (source == listView) {
                //Acciones específicas nuestro ListView. Por ejemplo,borrar fila al darle a SUPRIMIR. También pasa foco al siguiente Node (ENTER)
                System.out.println("Tecla presionada en el ListView: " + event.getCode());
                if (event.getCode() == KeyCode.DELETE) {
                    listView.getItems().remove(listView.getSelectionModel().getSelectedIndex());
                } else if (event.getCode() == KeyCode.ENTER) {
                    textArea.requestFocus();
                }
            }
        };
        //Asociamos Filtro a Node: indicando CATEGORÍA.SUBEVENTO y MANEJADOR de eventos. También pasa foco al siguiente Node (ENTER)
        textArea.addEventFilter(KeyEvent.KEY_PRESSED, keyEventHandler);
        informático.addEventFilter(KeyEvent.KEY_PRESSED, keyEventHandler);
        listView.addEventFilter(KeyEvent.KEY_PRESSED, keyEventHandler);

        VBox root = new VBox(10, new Label("Eventos de Escena"), textArea, informático, listView);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Ejemplos de EventFilter");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
