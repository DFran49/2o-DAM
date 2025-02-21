/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cvs;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;

/**
 *
 * @author DFran49
 */
public class MiListView extends ListView<String> {
    StringProperty selectedText = new SimpleStringProperty();
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
        
        getSelectionModel().selectFirst();
    }
    
    public void asignarTexto(MiText texto) {
        miTexto = texto;
        
        
        getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                selectedText.set(newValue);
            }
        });
        
        miTexto.textProperty().bindBidirectional(selectedText);
        
        selectedText.addListener((obs, oldValue, newValue) -> {
            int selectedIndex = getSelectionModel().getSelectedIndex();
            getItems().set(selectedIndex, newValue);
            getSelectionModel().select(selectedIndex);
        });

        miTexto.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                getItems().add("");
                getSelectionModel().selectNext();
                miTexto.clear();
            }
        });
    }
}
