/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cvs;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;

/**
 *
 * @author DFran49
 */
public class MiListView extends ListView<String> {
    private MiText miTexto;

    public MiListView() {
        super();
        getItems().add("");
        
        setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item);
            }
        });
    }
    
    public void asignarTexto(MiText texto) {
        this.miTexto = texto;

        // Vincular el texto del TextField a la entrada seleccionada
        miTexto.textProperty().bind(
            Bindings.createStringBinding(() -> {
                int selectedIndex = getSelectionModel().getSelectedIndex();
                return selectedIndex >= 0 ? getItems().get(selectedIndex) : "";
            }, 
                    getSelectionModel().selectedIndexProperty())
        );

        // Crear una nueva entrada al presionar Enter
        miTexto.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                getItems().add(""); // Nueva entrada vacía
                getSelectionModel().select(getItems().size() - 1); // Seleccionar la nueva entrada
                miTexto.clear();
            }
        });
    }
}
