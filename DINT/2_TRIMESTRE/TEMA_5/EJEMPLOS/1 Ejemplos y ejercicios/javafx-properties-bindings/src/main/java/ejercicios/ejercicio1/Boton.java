/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicios.ejercicio1;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 *
 * Clase para crear un botón personalizado simple que sólo tiene dos propiedades:
 * un texto que indica si fue pulsado o no. Permite que se le cambie de estado 
 * manualmente haciendo uso del método cambiarEstado().
 * Estos textos serán configurables al importar la librería en cualquier proyecto.
 * @author Molina
 */
public class Boton extends Button {

    //Se ponen a final (comportan como constantes) porque una vez inicializadas no cambian.
    private final SimpleStringProperty textoSinClic = new SimpleStringProperty("Sin enviar");
    private final SimpleStringProperty textoConClic = new SimpleStringProperty("Enviado");
    private final SimpleBooleanProperty clicado = new SimpleBooleanProperty(false);

   
    public Boton() {
        super(); // Llama al constructor de la clase base (Button)
        this.setText(this.textoSinClic.get());//Coge el valor de la propiedad desde Scene Builder           
        this.setStyle("-fx-background-color:yellow");
    }
    
    public Boton(String text) {
        super(text);
        this.setText(this.textoSinClic.get());//Coge el valor de la propiedad desde Scene Builder    
        this.setStyle("-fx-background-color:yellow");
    }

    //Getters y Setters
    public String getTextoSinClic() {
        return textoSinClic.get(); //Devuelve el contenido del property
    }
    public String getTextoConClic() {
        return textoConClic.get();
    }
    public Boolean getClicado() {
        return clicado.get();
    }

    public void setTextoSinClic(String texto) {
        this.textoSinClic.set(texto);
    }

    public void setTextoConClic(String texto) {
        this.textoConClic.set(texto);
    }

    public void setClicado(boolean clicado) {
        this.clicado.set(clicado);
    }
    
    //Vamos cambiando de estado
    public void cambiarEstado(){
        if (this.getClicado() == true){
            this.setClicado(false);
            this.setText(this.getTextoSinClic());     
            this.setStyle("-fx-background-color:red");
        }
        else{
            this.setClicado(true);
            this.setText(this.getTextoConClic());
            this.setStyle("-fx-background-color:green");

        }
    }

}
