package R3_ejer03;

/**
 *
 * @author Molina
 */
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Controlador implements Initializable {

    FileChooser fileChooserImp;
    FileChooser fileChooserExp;
    Stage primaryStage;

    @FXML
    private TableColumn<Empleado, Integer> columnaApellidos;

    @FXML
    private TableColumn<Empleado, String> columnaID;

    @FXML
    private TableColumn<Empleado, String> columnaNombre;

    @FXML
    private TableColumn<Empleado, Double> columnaSueldo;

    @FXML
    private TableColumn<Empleado, Double> columnaDepartamento;

    @FXML
    private Label info;

    @FXML
    private TableView<Empleado> tablaEmpleados;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtDepartamento;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtSueldo;

    @FXML
    void Actualizar(ActionEvent event) {

    }

    @FXML
    void Borrar(ActionEvent event) {

    }

    @FXML
    void Insertar(ActionEvent event) {
        this.info.setText(this.txtNombre.getText()); //Ejemplos para coger y poner texto

        //Insertar en OL. Ejemplo
        listaEmpleados.add(new Empleado(1, this.txtNombre.getText(), 
                this.txtApellidos.getText(),
                this.txtDepartamento.getText(), Double.parseDouble(this.txtSueldo.getText())));

    }

    @FXML
    void ExportarJSON(ActionEvent event) {

    }

    @FXML
    void ExportarXML(ActionEvent event) {

    }

    @FXML
    void ImportarJSON(ActionEvent event) {

    }

    @FXML
    void ImportarXML(ActionEvent event) {

    }

    //Nuevo
    private ObservableList<Empleado> listaEmpleados;

    @FXML
    void menuExportar(ActionEvent event) {

        primaryStage = (Stage) this.txtApellidos.getScene().getWindow();//Accedemos al primaryStage
        File selectedFile = fileChooserExp.showSaveDialog(primaryStage);

        if (selectedFile != null) {
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }

    @FXML
    void menuImportar(ActionEvent event) {

        primaryStage = (Stage) this.txtApellidos.getScene().getWindow();//Accedemos al primaryStage
        File selectedFile = fileChooserImp.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooserImp = new FileChooser();
        fileChooserExp = new FileChooser();

        // Configurar filtros para extensiones json y xml
        ExtensionFilter jsonFilter = new ExtensionFilter("Archivos JSON (*.json)", "*.json");
        ExtensionFilter xmlFilter = new ExtensionFilter("Archivos XML (*.xml)", "*.xml");
        fileChooserImp.getExtensionFilters().addAll(jsonFilter, xmlFilter);
        fileChooserExp.getExtensionFilters().addAll(jsonFilter, xmlFilter);

        listaEmpleados = FXCollections.observableArrayList();

        System.out.println(listaEmpleados.size());

        //No hay que hacer ni new de TableView ni asignar las columnas, solo asociar al modelo
        columnaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columnaDepartamento.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columnaSueldo.setCellValueFactory(new PropertyValueFactory<>("sueldo"));

        tablaEmpleados.setItems(listaEmpleados);
        
        
    }

}
