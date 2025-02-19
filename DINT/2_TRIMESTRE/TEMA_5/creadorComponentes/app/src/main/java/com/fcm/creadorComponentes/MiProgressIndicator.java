/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.creadorComponentes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.ProgressIndicator;

/**
 *
 * @author Francisco
 */
public class MiProgressIndicator extends ProgressIndicator {
    private IntegerProperty max = new SimpleIntegerProperty();

    public MiProgressIndicator() {
    }

    public int getMax() {
        return max.get();
    }

    public void setMax(int m) {
        this.max.set(m);
    }

    
}
