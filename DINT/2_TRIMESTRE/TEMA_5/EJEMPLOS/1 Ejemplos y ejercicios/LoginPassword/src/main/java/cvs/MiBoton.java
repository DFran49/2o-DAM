/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cvs;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author Molina
 */
public class MiBoton extends Button {

    //Propiedad para el textoDebug
    private final StringProperty textoDebug = new SimpleStringProperty();

    //Propiedad para el valorNum
    private final DoubleProperty valorNum = new SimpleDoubleProperty();
    

    // Constructor
    public MiBoton() {
        super(); // Llama al constructor de la clase base (Button)
        inicializar();
    }

    // Constructor con texto
    public MiBoton(String text) {
        super(text);
        inicializar();
    }
   
    // Métodos getter y setter para textoDebug
    public String getTextoDebug() {
        return textoDebug.get();
    }
    public void setTextoDebug(String textoDebug) {
        this.textoDebug.set(textoDebug);
    }

    // Métodos getter y setter para valorNum
    public double getValorNum() {
        return valorNum.get();
    }

    public void setValorNum(double valorNum) {
        this.valorNum.set(valorNum);
    }
    
     private void inicializar() {
        // Aquí se podrían inicializar variables
    }

}
