package ejercicios.ejercicio3;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

/**
 *
 * Clase para crear un TextField personalizado. Tiene una propiedad, moneda de
 * tipo String que guarda tipo de moneda (€,$,sin moneda)
 *
 * @author Molina
 */
public class CampoTexto extends TextField {

    //Inicializa la propiedad si se carga en SB. 
    //Pero si se personaliza desde SB ya no tendrá efecto (de hecho, tomará un $ de inicio)
    private final SimpleStringProperty moneda = new SimpleStringProperty("");

    public CampoTexto() {
        super();
    }

    public String getMoneda() {
        return this.moneda.get();
    }

    public void setMoneda(String texto) {
        System.out.println("Iniciando Property con " + texto);
        switch (texto) {
            case "euro" -> {
                this.moneda.set("€");
                this.setVisible(true); //Este cambio no se ve al llamarse desde, la GUI aún no está inicializada
            }
            case "dolar" -> {
                this.moneda.set("$");
                this.setVisible(true); //Este cambio no se ve al llamarse desde, la GUI aún no está inicializada
            }
            default -> {
                this.moneda.set("");
                this.setVisible(false); //Este cambio no se ve al llamarse desde, la GUI aún no está inicializada
            }
        }
    }

}
