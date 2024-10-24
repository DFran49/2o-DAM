
package com.javafx.R1_Básicos;

/**
 *
 * @author Molina
 */
public class Año {
    
    Integer año;
    String bisiesto;

    public Año(Integer año, String bisiesto) {
        this.año = año;
        this.bisiesto = bisiesto;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public String getBisiesto() {
        return bisiesto;
    }

    public void setBisiesto(String bisiesto) {
        this.bisiesto = bisiesto;
    }
    
    
}
