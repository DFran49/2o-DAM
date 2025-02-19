/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicios.ejercicio4;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * Clase para crear un Text personalizado. Tiene 3 propiedades: modo, edad y
 * fecha
 *
 * @author Molina
 */
public class Etiqueta extends Text {

    private final SimpleStringProperty modo = new SimpleStringProperty();
    private final SimpleStringProperty edad = new SimpleStringProperty();
    private final SimpleStringProperty fecha = new SimpleStringProperty();

    public Etiqueta() {
        super();
        this.modo.set("");
        this.edad.set("");
        this.fecha.set("");
        this.setText("Sin información");
    }

    public String getModo() {
        return this.modo.get();
    }

    public void setModo(String modo) {
        this.modo.set(modo);

    }

    public String getEdad() {
        return this.edad.get();
    }

    public String getFecha() {
        return this.fecha.get();
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public void setEdad(String edad) {
        this.edad.set(edad);
    }

    //Definimos getters para Properties porque las voy a enlazar 
    public StringProperty edadProperty() {
        return this.edad;
    }

    public StringProperty fechaProperty() {
        return this.fecha;
    }

    //Funciones propias
    public void setMensaje() {
        switch (this.getModo()) {
            case "cumple" -> {
                this.setText("Feliz " + this.getEdad() + " cumpleaños!!");
                this.setStyle("-fx-font-size: 18px;");
                this.setFill(Color.BLUEVIOLET);
            }
            case "cita" -> {
                this.setText("Tiene una cita programada el " + this.getFecha());
                this.setStyle("-fx-font-size: 12px;");
                this.setFill(Color.BLACK);
            }
            case "urgente" -> {
                this.setText("EVENTO URGENTE EL " + this.getFecha());
                this.setFill(Color.RED);
                this.setStyle("-fx-font-size: 22px;");
            }
        }

    }
}
