/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.creadorComponentes;

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
    private final StringProperty hint = new SimpleStringProperty();
    private final IntegerProperty tamaño = new SimpleIntegerProperty();
    private final TextField este = this;

    public MiText() {
        inicializar("");
    }

    public MiText(String string) {
        super();
        inicializar(string);
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
    
    private void inicializar(String s) {
        this.promptTextProperty().bind(Bindings.createStringBinding(() -> {
            return hint.get();
        },
            hint
        ));
        
        this.prefWidthProperty().bind(Bindings.createDoubleBinding(() -> {
            return (double)tamaño.get()*2;
        },
            tamaño
        ));
    }
}
