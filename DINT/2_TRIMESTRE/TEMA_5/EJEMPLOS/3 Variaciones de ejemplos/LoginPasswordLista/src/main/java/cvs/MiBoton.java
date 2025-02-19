/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cvs;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    //Propiedad lista
    private final ListProperty<String> listaTextos = new SimpleListProperty<>(FXCollections.observableArrayList());

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

    public ObservableList<String> getListaTextos() {
        return (ObservableList<String>) this.listaTextos.get();
    }
    
     public void addListaTextos(String t) { //Para añadir más textos vía programa
        this.listaTextos.add(t);
    }

    private void inicializar() {
        // Aquí se podrían inicializar variables
        listaTextos.addAll("ERROR!", "Has superado el número de intentso", "Pista 1: Empieza por Z...");
    }

}
