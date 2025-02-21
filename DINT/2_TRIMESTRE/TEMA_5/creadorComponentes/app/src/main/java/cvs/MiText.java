/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cvs;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

/**
 *
 * @author Francisco
 */
public class MiText extends TextField {
    private StringProperty hint;
    protected DoubleProperty tamaño;

    public MiText() {
        tamaño = new SimpleDoubleProperty(25); 
        hint = new SimpleStringProperty(this, "hint", "");
        
        promptTextProperty().bind(hint);
    }

    public String getHint() {
        return hint.get();
    }

    public Double getTamaño() {
        return tamaño.get();
    }
    
    public void setHint(String hint) {
        this.hint.set(hint);
    }

    public void setTamaño(int tamaño) {
        this.tamaño.set(tamaño);
    }
    
    public DoubleProperty tamañoProperty() {
        return tamaño;
    }
}
