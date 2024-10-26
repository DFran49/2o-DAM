package _examen; 

import com.javafx.tableview.Robot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Nombre alumn@: Francisco Crespo Martín
 */
public class Examen extends Application {
    
    TextField txtCerveza1, txtCerveza2;
    ComboBox cbGrados1 = new ComboBox();
    ComboBox cbGrados2 = new ComboBox();
    ComboBox cbApellidos = new ComboBox();
    ToggleGroup tgCombinaciones;
    RadioButton rbIni1 = new RadioButton("Inic. B1 + Final B2");
    RadioButton rbIni2 = new RadioButton("Final. B1 + Inicio B2");
    
    ObservableList olCervezas;
    TableView tvCervezas;
    int indexCerveza = -1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ArrayList<Integer> grados = new ArrayList();
        for (int i = 3; i <= 10; i++) {
            grados.add(i);
        }
        
        HBox hbCerveza1 = new HBox();
        Label lblCerveza1 = new Label("Nombre cerveza 1");
        txtCerveza1 = new TextField();
        Label lblGrados1 = new Label("Gradosº");
        for (int nums : grados) {
            cbGrados1.getItems().add(nums);
        }
        cbGrados1.setValue(grados.get(2));
        hbCerveza1.getChildren().addAll(lblCerveza1,txtCerveza1,lblGrados1,cbGrados1);
        
        HBox hbCerveza2 = new HBox();
        Label lblCerveza2 = new Label("Nombre cerveza 1");
        txtCerveza2 = new TextField();
        Label lblGrados2 = new Label("Gradosº");
        for (int nums : grados) {
            cbGrados2.getItems().add(nums);
        }
        cbGrados2.setValue(grados.get(2));
        hbCerveza2.getChildren().addAll(lblCerveza2,txtCerveza2,lblGrados2,cbGrados2);
        
        HBox hbApellidos = new HBox();
        ArrayList<String> apellidos = new ArrayList();
        apellidos.add("Ahumada");
        apellidos.add("Delarisa");
        apellidos.add("Loca");
        apellidos.add("Crealagunas");
        apellidos.add("Quitapenas");
        apellidos.add("Del infierno");
        for (String text : apellidos) {
            cbApellidos.getItems().add(text);
        }
        tgCombinaciones = new ToggleGroup();
        rbIni1.setToggleGroup(tgCombinaciones);
        rbIni1.setSelected(true);
        rbIni2.setToggleGroup(tgCombinaciones);
        hbApellidos.getChildren().addAll(cbApellidos,rbIni1,rbIni2);
        
        VBox vboxCervezas = new VBox(10);
        olCervezas = FXCollections.observableArrayList(); 
        olCervezas.add(new Cerveza("Alhambra",7));
        olCervezas.add(new Cerveza("Alhambra",8));
        Button btnMezcla = new Button("Mezcla!");
        btnMezcla.setOnAction((event) -> mezclar());
        Button btnBorra = new Button("Borra!");
        btnBorra.setOnAction((event) -> borrar());
        tvCervezas = new TableView(olCervezas);
        TableColumn<Cerveza, String> tcNombre = new TableColumn<>("Nombre");
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Cerveza, Double> tcGrados = new TableColumn<>("Graduación");
        tcGrados.setCellValueFactory(new PropertyValueFactory<>("graduacion"));
        tvCervezas.getColumns().addAll(tcNombre,tcGrados);
        vboxCervezas.getChildren().addAll(btnMezcla,btnBorra,tvCervezas);
        
        tvCervezas.getSelectionModel().selectedItemProperty().addListener((observable, viejoValor, nuevoValor) -> {
            if (nuevoValor != null) {
                indexCerveza = olCervezas.indexOf(nuevoValor);
            }
        });
        
        VBox vboxFavoritos = new VBox(10);
        Button btnFavorito = new Button("Volcar Favorito");
        btnFavorito.setOnAction((event) -> marcarFavorito());
        ObservableList olFavoritos = FXCollections.observableArrayList(); 
        ListView lvFavoritos = new ListView();
        vboxFavoritos.getChildren().addAll(btnFavorito,lvFavoritos);
        
        VBox vbox=new VBox(10);
        vbox.getChildren().addAll(hbCerveza1,hbCerveza2,hbApellidos,vboxCervezas,vboxFavoritos);
        
        vbox.setPadding(new Insets(20));
        Scene scene=new Scene(vbox, 500,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FRANCISCO CRESPO MARTÍN");
        primaryStage.show();
    }

    public void mezclar() {
        int grados1 = (int) cbGrados1.getItems().get(cbGrados1.getSelectionModel().getSelectedIndex());
        int grados2 = (int) cbGrados2.getItems().get(cbGrados2.getSelectionModel().getSelectedIndex());
        double mediaGrados = (grados1+grados2)/2;
        String nombre1 = txtCerveza1.getText();
        String nombre2 = txtCerveza2.getText();
        String comNombre = "";
        if (txtCerveza1.getText().isEmpty() || txtCerveza2.getText().isEmpty()) {
            Alert alertError=new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Esto es una alerta de error");
            alertError.setContentText("No ha introducido algún nombre o un apellido");
            alertError.showAndWait();
        } else {
            if (tgCombinaciones.getSelectedToggle().equals(rbIni1)) {
            comNombre = comNombre + nombre1.substring(0,(nombre1.length()/2)) + 
                    nombre2.substring((nombre2.length()/2)) + " " + 
                    cbApellidos.getItems().get(cbApellidos.getSelectionModel().getSelectedIndex());            
        } else {
            comNombre = comNombre + nombre1.substring((nombre1.length()/2)) + 
                    nombre2.substring(0,(nombre2.length()/2)) + " " + 
                    cbApellidos.getItems().get(cbApellidos.getSelectionModel().getSelectedIndex());  
        }
        olCervezas.add(new Cerveza(comNombre,mediaGrados));
        }
    }
    
    public void borrar() {
        if (indexCerveza != -1 && !olCervezas.isEmpty()) {
            olCervezas.remove(indexCerveza);
            
        } 
        if (indexCerveza == -1){
            Alert alertError=new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Esto es una alerta de error");
            alertError.setContentText("No ha seleccionado una cerveza o no quedan");
            alertError.showAndWait();
        }
    }
    
    public void marcarFavorito() {
        
    }
}
