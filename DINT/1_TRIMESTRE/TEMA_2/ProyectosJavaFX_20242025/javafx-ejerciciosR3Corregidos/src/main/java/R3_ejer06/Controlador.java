package R3_ejer06;

/**
 *
 * @author Molina
 */
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
import javafx.scene.control.cell.PropertyValueFactory;

public class Controlador implements Initializable {

    @FXML
    private TableColumn<Pais, String> cPais;

    @FXML
    private TableColumn<Pais, Double> cProb;

    @FXML
    private Label labelEstado;

    @FXML
    private TableView<Pais> tablaPaises;
    
    //Nuevo
    private ObservableList<Pais> listaPaises;

    @FXML
    void Aceptar(ActionEvent event) {
      //Insertar en TableView. Ejemplo
      listaPaises.add(new Pais("España", 97.50));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaPaises = FXCollections.observableArrayList();
        
        System.out.println(listaPaises.size());

        //No hay que hacer ni new de TableView ni asignar las columnas, solo asociar al modelo
        cPais.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cProb.setCellValueFactory(new PropertyValueFactory<>("prob"));

        tablaPaises.setItems(listaPaises);
    }

}
