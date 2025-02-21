/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cvs;

import java.awt.Image;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

/**
 *
 * @author Francisco
 */
public class MiProgressIndicator extends ProgressIndicator {
    private DoubleProperty progreso = new SimpleDoubleProperty(0.0);
    private BooleanProperty indeterminado = new SimpleBooleanProperty(true);
    private MiText miTexto;

    public MiProgressIndicator() {
    }
    
    public void asignarTexto(MiText texto) {
        miTexto = texto;
        
        progressProperty().bind(
            Bindings.createDoubleBinding(() -> {
                if (miTexto.getText() == null) {
                    return 0.0;
                }
                double length = miTexto.getText().length();
                double maxLength = miTexto.getTamaño();
                double prog = 0;
                if (maxLength > 0 && length <= maxLength) {
                    prog = length / maxLength;
                }
                if (length > maxLength) {
                    String text = miTexto.getText();
                    miTexto.setText(text.substring(0, (int) miTexto.tamaño.get()));
                }
                if (miTexto.focusedProperty().get() == true) {
                    return ProgressIndicator.INDETERMINATE_PROGRESS;
                }
                return prog;
            }, 
                    miTexto.textProperty(),miTexto.focusedProperty()
            ));

        
    }

    public Double getProgreso() {
        return progreso.get();
    }

    public void setProgreso(Double progreso) {
        this.progreso.set(progreso);
    }

    public Boolean getIndeterminado() {
        return indeterminado.get();
    }

    public void setIndeterminado(Boolean indeterminad) {
        this.indeterminado.set(indeterminad);
    }
    
    
}
