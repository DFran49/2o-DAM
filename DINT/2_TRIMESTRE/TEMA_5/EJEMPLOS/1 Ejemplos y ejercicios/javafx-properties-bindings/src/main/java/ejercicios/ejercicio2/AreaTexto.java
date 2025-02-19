/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicios.ejercicio2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextArea;

/**
 *
 * Clase para crear un TextArea personalizado. Tiene dos propiedades, una
 * booleana para saber si la letra es Negra, y otra llamada talla (tamaño de
 * texto)
 *
 * @author Molina
 */
public class AreaTexto extends TextArea {

    private final SimpleBooleanProperty letraNegra = new SimpleBooleanProperty();
    private final IntegerProperty talla = new SimpleIntegerProperty();

    public AreaTexto() {
        super();
        getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());
        this.setLetraNegra(true);
        this.setTalla(2);
        this.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim "
                + "ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip "
                + "ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate "
                + "velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat "
                + "cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        this.setWrapText(true);
    }

    public AreaTexto(String text) {
        super();
        getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());
        this.setLetraNegra(true);
        this.setTalla(2);
        this.wrapTextProperty();
        this.setText(text);
    }

    //Getters y Setters
    public Boolean getLetraNegra() {
        return letraNegra.get();
    }

    public Integer getTalla() {
        return talla.get();
    }

    public final void setLetraNegra(boolean negra) {
        //La función .set establece el valor del property (BooleanProperty a partir de un boolean)
        this.letraNegra.set(negra);
        if (negra) {
            this.setStyle("-fx-text-fill: black");
        } else {
            this.setStyle("-fx-text-fill: white");
        }
    }

    public final void setTalla(Integer talla) {
        //La función .set establece el valor del property (IntegerProperty a partir de un Integer)
        this.talla.set(talla);
        this.getStyleClass().clear();

        switch (talla) {
            case 1 ->
                this.getStyleClass().add("letraPeque");
            case 2 ->
                this.getStyleClass().add("letraMedia");
            case 3 ->
                this.getStyleClass().add("letraGrande");
        }
    }

}
