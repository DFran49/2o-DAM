/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cvs;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
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
    private IntegerProperty tamaño;

    public MiText() {
        tamaño = new SimpleIntegerProperty(this, "maxLength", -1); // -1 significa sin límite
        hint = new SimpleStringProperty(this, "hint", "");
        
        this.textProperty().bind(Bindings.createStringBinding(() -> {
            String text = super.getText();
            return (tamaño.get() > 0 && text != null && text.length() > tamaño.get()) ? text.substring(0, tamaño.get()) : text;
        },
            textProperty(),
            tamaño
        ));
        
        promptTextProperty().bind(hint);
    }

    public String getHint() {
        return hint.get();
    }

    public Integer getTamaño() {
        return tamaño.get();
    }
    
    public void setHint(String hint) {
        this.hint.set(hint);
    }

    public void setTamaño(int tamaño) {
        this.tamaño.set(tamaño);
    }
}
