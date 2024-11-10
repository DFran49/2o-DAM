package pasovariablesv3;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Molina
 */
public class controladorA implements Initializable {

    //Definimos una instancia del otro Stage (el que vamos a abrir desde aquí)
    //Al nodo Stage es al que vamos a asociar los datos
    Stage stageB;

    @FXML
    private TextField cajaTexto1;
    
    @FXML
    private Label miEtiqueta;

    @FXML
    private void botonPulsado() {
        //Cambio V3: Ahora NO pasamos los datos vía una función pública
        //Se hace creación de dato y enlace a nodo
        Calendar calendario = Calendar.getInstance();
        Datos datos1 = new Datos(this.cajaTexto1.getText(), calendario.get(Calendar.YEAR));
        System.out.println(datos1);
        stageB.setUserData(datos1);//Pasamos datos más complejos que un simple String o Integer
        
        Stage primaryStage = (Stage) this.cajaTexto1.getScene().getWindow();
        stageB.setX(primaryStage.getX()); // Establecer la posición y
        stageB.setY(primaryStage.getY() + 240); // Establecer la posición y
        
        this.stageB.show();//Al mostrar se llama al evento SHOWN

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfazB.fxml"));
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(controladorA.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Cambio V3: Ahora NO enlazamos controladores

        Scene sceneB = new Scene(root, 400, 200);
        stageB = new Stage();
        stageB.initModality(Modality.APPLICATION_MODAL); //MODAL!!
        stageB.setResizable(false);
        stageB.setScene(sceneB);
        stageB.setTitle("Ventana Emergente");

        stageB.setOnCloseRequest(e -> {
            e.consume();
            stageB.hide();
        });

        //CAMBIO V3: añadimos evento para cuando se muestre la ventana y para cuando se cierra
        stageB.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> {
            Datos datos1 = (Datos) stageB.getUserData();
            System.out.println("Recogiendo datos en B: "+datos1);
            Scene scene2 = (Scene) stageB.getScene();
            TextField t2 = (TextField) scene2.lookup("#cajaTexto2");//Accedemos a Nodo de otro Stage por ID
            t2.setText(datos1.getDato() + " en año " + datos1.getAño().toString());
        });

        stageB.addEventHandler(WindowEvent.WINDOW_HIDDEN, e -> {
            Datos datos = (Datos) stageB.getUserData();
            System.out.println("Recuperando datos:" + datos);
            miEtiqueta.setText(datos.getDato() + " en año " + datos.getAño().toString());//Accedemos a Nodo de forma normal
        });

      
    }

}
