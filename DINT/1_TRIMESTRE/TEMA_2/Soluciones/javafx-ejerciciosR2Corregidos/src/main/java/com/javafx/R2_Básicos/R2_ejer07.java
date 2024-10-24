package com.javafx.R2_Básicos; //Modificar al package correcto

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class R2_ejer07 extends Application {

    ObservableList<CervezaImagen> listaBirras;
    GridPane gridPaneBirras;

    ImageView imagen;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        gridPaneBirras = new GridPane();

        listaBirras = FXCollections.observableArrayList(
                new CervezaImagen("Alcázar Leyenda 01", null, "España", 5.6)
        );

        this.rellenaGridPane();

        BorderPane root = new BorderPane();
        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);

        // Crear menú "Archivo" y submenú "Cargar Imagen"
        Menu archivoMenu = new Menu("Archivo");
        MenuItem cargarImagenItem = new MenuItem("Cargar Imagen");
        archivoMenu.getItems().add(cargarImagenItem);

        // Agregar el evento para mostrar la ventana de carga de imagen
        cargarImagenItem.setOnAction(event -> mostrarVentanaCarga());

        menuBar.getMenus().add(archivoMenu);

        root.setCenter(gridPaneBirras);

        // Crear una escena y mostrarla en la ventana
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lista de Cervezas");
        primaryStage.show();
    }

    // Método para mostrar un Alert con los detalles de la cerveza
    private void mostrarDetallesCerveza(CervezaImagen cerveza) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Detalles de la Cerveza");
        alert.setHeaderText(cerveza.getNombre());
        alert.setContentText(".....\n"
                + "País: " + cerveza.getPais() + "\n"
                + "Graduación: " + cerveza.getGraduacion() + " %");
        alert.showAndWait();
    }

    private StackPane insertaPanel(CervezaImagen c) {
        StackPane p = new StackPane();
        ImageView i = new ImageView(c.getImagen());
        i.setPreserveRatio(true);
        i.setFitHeight(50);
        p.setPrefSize(200, 200);
        p.setStyle("-fx-background-color: lightyellow");
        Button b1 = new Button("DATOS");
        Button b2 = new Button("BORRAR");//NUEVO
        Label l = new Label(c.getNombre());
        HBox hBox = new HBox(b1, b2);
        hBox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(10, l, i, hBox);
        vbox.setAlignment(Pos.CENTER);
        p.setOpacity(0.3);
        p.getChildren().add(vbox);
        //Agregar un evento de clic a la imagen
        b1.setOnMouseClicked(event -> {
            System.out.println("Click en " + c.getNombre());
            mostrarDetallesCerveza(c);
        });

         //NUEVO
        b2.setOnMouseClicked(event -> {
            //Se elimina y se recarga, no se elimina la posición y se queda vacío,
            //para eso habría que guardar fila y columna y entonces "limpiar" ese panel
            System.out.println("Click en " + c.getNombre());
            int indice = listaBirras.indexOf(c);
            System.out.println(listaBirras.toString());
            listaBirras.remove(indice);
            gridPaneBirras.getChildren().clear();
            rellenaGridPane();
            //para "limpiar" un panel concreto, se podría hacerse de varias formas
            
            //A: Si queremos quitar uno determinado se puede hacer por posición, por ejemplo el 2,2: se podría hacer así
            //gridPaneBirras.getChildren().removeIf(node -> 
              //      GridPane.getColumnIndex(node) == 2 && GridPane.getRowIndex(node) == 2);
              
            //B: Si se sabe indice (que en este caso se ha averiguado:
            //gridPaneBirras.getChildren().remove(indice);
          });


        //Agregar un evento de mouse hover y exit (ratón sobre o fuera de la Foto)
        p.setOnMouseEntered(event -> {
            p.setOpacity(1);
        });

        p.setOnMouseExited(event -> {
            p.setOpacity(0.3);
        });

        return p;
    }

    private void rellenaGridPane() {
        int i = 0;
        int numFC = 4;

        for (int fila = 0; fila < numFC; fila++) {
            for (int columna = 0; columna < numFC; columna++) {
                i = fila * numFC + columna;
                if (i < listaBirras.size()) {//Para no colarnos de los datos que hay
                    System.out.println(i);
                    listaBirras.get(i);
                    System.out.println("Cogiendo " + i);
                    StackPane p = insertaPanel(listaBirras.get(i));
                    gridPaneBirras.add(p, columna, fila);
                }
            }
        }
    }

    // Método para mostrar la ventana de carga de imagen
    private void mostrarVentanaCarga() {
        Stage stage = new Stage();
        stage.setTitle("Cargar Imagen");

        // Crear cajas de texto y etiquetas
        TextField nombreTextField = new TextField();
        TextField urlTextField = new TextField("https://cdn0.iconfinder.com/data/icons/small-n-flat/24/678063-beer-64.png");
        urlTextField.setDisable(true);
        TextField nacionalidadTextField = new TextField();
        TextField graduacionTextField = new TextField();
        Label nombreLabel = new Label("Nombre:");
        Label urlLabel = new Label("URL:");
        Label nacionalidadLabel = new Label("Nacionalidad:");
        Label graduacionLabel = new Label("Graduación:");

        // Crear el botón para cargar la imagen y cerrar la ventana
        Button insertarBoton = new Button("Insertar");

        Button cargarImagenBoton = new Button("Cargar Imagen");
        Image imgBirra = null;
        cargarImagenBoton.setOnAction(e -> {
            cargarImagen();//no podemos asignar valores dentro de una expresión lambda, por eso no devolvemos image
        });

        VBox vBoxControles = new VBox(10);
        imagen = new ImageView();
        vBoxControles.setPadding(new Insets(10));
        vBoxControles.setAlignment(Pos.CENTER);
        vBoxControles.getChildren().addAll(new HBox(10,nombreLabel, nombreTextField),
                new HBox(10,urlLabel, urlTextField, cargarImagenBoton),
                new HBox(10,nacionalidadLabel, nacionalidadTextField),
                new HBox(10,graduacionLabel, graduacionTextField), insertarBoton, imagen);

        insertarBoton.setOnAction(event -> {
            //en este punto ya se tiene en imagen la imagen cargada, si no se carga será null
            listaBirras.add(new CervezaImagen(nombreTextField.getText(), imagen.getImage(), nacionalidadTextField.getText(), Double.parseDouble(graduacionTextField.getText())));
            System.out.println(listaBirras.size());
            rellenaGridPane();
            stage.close();
        });

        VBox vbox = new VBox(vBoxControles, insertarBoton);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));

        stage.setScene(new Scene(vbox, 350, 400));
        stage.show();
    }

    private void cargarImagen() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de imagen", "*.jpg", "*.png", "*.gif", "*.bmp"));
        java.io.File archivo = fileChooser.showOpenDialog(null);

        if (archivo != null) {
            Image img = new Image(archivo.toURI().toString());
            imagen.setFitWidth(100); // Ajustar el ancho del ImageView
            imagen.setFitHeight(100); // Ajustar la altura del ImageView
            imagen.setImage(img);

        }

    }

}
