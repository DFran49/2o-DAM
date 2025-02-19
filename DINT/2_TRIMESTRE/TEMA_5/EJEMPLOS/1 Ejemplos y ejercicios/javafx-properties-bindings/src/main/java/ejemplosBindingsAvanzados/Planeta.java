/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplosBindingsAvanzados;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Molina
 */
public class Planeta {

    private SimpleStringProperty nombre;
    private SimpleStringProperty sistemaSolar;
    private SimpleIntegerProperty radio;

    public Planeta(String nombre, String sistemaSolar, int radio) {
        this.nombre = new SimpleStringProperty(nombre);
        this.sistemaSolar = new SimpleStringProperty(sistemaSolar);
        this.radio = new SimpleIntegerProperty(radio);
    }

    //Getters
    public String getNombre() {
        return nombre.get();
    }

    public String getSistemaSolar() {
        return sistemaSolar.get();
    }

    public int getRadio() {
        return radio.get();
    }
    
    //Setters no son necesarios en este ejemplo

    //Getters de property. Pueden ser necesario si queremos acceder a la propiedad en sí
    public StringProperty nombreProperty() {
        return nombre;
    }

    public SimpleStringProperty sistemaSolarProperty() {
        return sistemaSolar;
    }

    public SimpleIntegerProperty radioProperty() {
        return radio;
    }

    //Para vere el nombre en el LV, porque si no, saca por defecto una referencia al objeto
    @Override
    public String toString() {
        return getNombre();
    }
}
