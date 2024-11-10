package com.javafx.todosloseventos;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * EVENTOS Y CLIPBOARD
 *
 * Eventos se pueden aplicar a un contenedor, a la escena o a un control individual
 *
 * @author JMMolina
 */
public class EventosCompleto extends Application {
    
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
        informático.setPromptText("Arrastra desde el LV o Escribe un nombre y arrastra al LV");

        VBox root = new VBox(10, new Label("Eventos de Escena"), textArea, informático, listView);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 800, 600);
        
        
        //EVENTOS
        /*Hay que tener en cuenta que:
        -existe prioridad entre eventos (posibles conflictos)
        -un evento se propaga hacia sus hijos y cada Nodo podría interpretar el evento de distinta forma, 
        y es posible que ciertas acciones ya estén personalizadas (ej: ContextMenu en TextArea):
        por ejemplo, sobre el textarea pinchar con el  botón izquierdo no genera evento, sitúa el cursor...
        por ejemplo, sobre el textarea pinchar con el  botón derecho no genera evento ContextMenú ya que tiene el suyo propio
        por ejemplo, sobre el ListView pinchar con el botón izquierdo no genera evento, selecciona...
        por ejemplo, sobre el textarea pulsar una tecla no genera evento, escribe...
        */
        
        // EVENTOS DE ESCENA
        //-----------------------------------------------------------------------------------------
        
        //Mouse Events: 
        scene.setOnContextMenuRequested(e -> logEvento(textArea, "On Context Menu Requested"));
        scene.setOnMouseClicked(e -> logEvento(textArea, "On Mouse Clicked"));
        scene.setOnMouseDragged(e -> logEvento(textArea, "On Mouse Dragged"));
        scene.setOnMouseEntered(e -> logEvento(textArea, "On Mouse Entered"));
        scene.setOnMouseExited(e -> logEvento(textArea, "On Mouse Exited"));
        scene.setOnMouseMoved(e -> logEvento(textArea, "On Mouse Moved"));
        scene.setOnMousePressed(e -> logEvento(textArea, "On Mouse Pressed"));
        scene.setOnMouseReleased(e -> logEvento(textArea, "On Mouse Released"));
        scene.setOnScroll(e -> logEvento(textArea, "On Scroll"));

        //Keyboard Events
        scene.setOnKeyPressed(e -> logEvento(textArea, "On Key Pressed"));
        scene.setOnKeyReleased(e -> logEvento(textArea, "On Key Released"));
        scene.setOnKeyTyped(e -> logEvento(textArea, "On Key Typed"));

        // EVENTOS TÁCTILES (GESTOS)
        //-----------------------------------------------------------------------------------------
        //Scroll Events: necesita pantalla táctil
        scene.setOnScrollStarted(e -> logEvento(textArea, "On Scroll Started"));
        scene.setOnScrollFinished(e -> logEvento(textArea, "On Scroll Finished"));

        //Rotation Events: necesita pantalla táctil
        scene.setOnRotate(e -> {
            logEvento(textArea, "On Rotate");
            Rotate rotate = new Rotate(e.getAngle(), listView.getWidth() / 2, listView.getHeight() / 2);
            listView.getTransforms().add(rotate);
        });
        scene.setOnRotationStarted(e -> logEvento(textArea, "On Rotation Started"));
        scene.setOnRotationFinished(e -> logEvento(textArea, "On Rotation Finished"));

        //Swipe Events: los deslizamientos necesitan pantalla táctil
        scene.setOnSwipeLeft(e -> logEvento(textArea, "On Swipe Left"));
        scene.setOnSwipeRight(e -> logEvento(textArea, "On Swipe Right"));
        scene.setOnSwipeUp(e -> logEvento(textArea, "On Swipe Up"));
        scene.setOnSwipeDown(e -> logEvento(textArea, "On Swipe Down"));

        //Touch Events: necesita pantalla táctil
        scene.setOnTouchMoved(e -> logEvento(textArea, "On Touch Moved"));
        scene.setOnTouchPressed(e -> logEvento(textArea, "On Touch Pressed"));
        scene.setOnTouchReleased(e -> logEvento(textArea, "On Touch Released"));
        scene.setOnTouchStationary(e -> logEvento(textArea, "On Touch Stationary"));

        //Zoom Events: necesita pantalla táctil
        scene.setOnZoom(e -> {
            logEvento(textArea, "On Zoom");
            listView.setScaleX(listView.getScaleX() * e.getZoomFactor());
            listView.setScaleY(listView.getScaleY() * e.getZoomFactor());
        });
        scene.setOnZoomStarted(e -> logEvento(textArea, "On Zoom Started"));
        scene.setOnZoomFinished(e -> logEvento(textArea, "On Zoom Finished"));

        //EVENTOS PARA TEXTAREA, TEXTFIELD Y LISTVIEW
        //-----------------------------------------------------------------------------------------
        //A) EventosCompleto Drag and Drop para el Textfield (informátic@), usa el portapapeles
        informático.setOnDragDetected(e -> {
            //Dragboard es el objeto que representa al Clipboard mientras que arrastramos
            Dragboard db = informático.startDragAndDrop(TransferMode.MOVE); //Se podría también usar .COPY
            ClipboardContent content = new ClipboardContent();//Creamos un contenido nuevo
            content.putString(informático.getSelectedText());//Lo inicializamos
            db.setContent(content);//Y asignamos
            e.consume();//Finalizamos evento para que no se propague a posibles nodos hijos
        });
        //A) Evento Drag and Drop para el ListView, usa el portapapeles
        listView.setOnDragDetected(e -> {
            Dragboard db = listView.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(listView.getSelectionModel().getSelectedItem());
            db.setContent(content);
            e.consume();
        });

        //B) Evento Drag Dropped (arrastre exitoso)
        listView.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasString()) {//Podemos consultar el TIPO .hasFiles, .hasImage....etc..
                informáticosFamosos.add(db.getString());//Añado al LV al final, se podría investigar para poner en una posición.
            }
            e.consume();
        });
        //B) Evento Drag Dropped (arrastre exitoso)
        informático.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasString()) {
                informático.setText(db.getString());//Pongo texto en TextField
                informáticosFamosos.remove(listView.getSelectionModel().getSelectedIndex());
            }
            e.consume();
        });

        //C) Evento importante porque PERMITE (DEJA PASO SEGÚN REGLAS) que se arrastre algo desde un origen concreto (getGestureSource() 
        //y el tipo de dato que contiene .hasxxxxxx
        listView.setOnDragOver(e -> {
            if (e.getGestureSource() != listView && e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });
        informático.setOnDragOver(e -> {
            if (e.getGestureSource() != informático && e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });
        //Resto de eventos Drag: probar a arrastrar y soltar algo dentro del propio LV
        listView.setOnDragDone(e -> logEvento(textArea, "On ListView Drag Done"));
        listView.setOnDragEntered(e -> logEvento(textArea, "On ListView Drag Entered"));
        listView.setOnDragExited(e -> logEvento(textArea, "On ListView Drag Exited"));

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Ejemplos de todos los eventos posibles");
        primaryStage.show();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
}
