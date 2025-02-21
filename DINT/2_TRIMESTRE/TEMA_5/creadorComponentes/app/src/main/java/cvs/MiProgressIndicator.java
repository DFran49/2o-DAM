/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cvs;

import java.awt.Image;
import javafx.beans.binding.Bindings;
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
    private final DoubleProperty progreso = new SimpleDoubleProperty(this, "porcentageProgreso", 0.0);
    private final BooleanProperty indeterminado = new SimpleBooleanProperty(this, "esIndeterminado", false);
    private MiText miTexto;

    public MiProgressIndicator(MiText texto) {
        progressProperty().bind(
            Bindings.when(indeterminado)
                .then(ProgressIndicator.INDETERMINATE_PROGRESS)
                .otherwise(progreso)
        );
    }
    
    public void asignarTexto(MiText texto) {
        miTexto = texto;
        
        progreso.bind(
            Bindings.createDoubleBinding(() -> {
                double length = miTexto.getText().length();
                double maxLength = miTexto.getTamaño();
                return maxLength > 0 && length <= maxLength ? length / maxLength : 0.0;
            }, 
                    miTexto.textProperty())
        );

        indeterminado.bind(miTexto.focusedProperty());
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
