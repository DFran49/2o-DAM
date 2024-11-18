/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams.util;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author DFran49
 */
public class Utilidades {

    public Utilidades() {
    }
    
    public Image getImage(String b64) {
        byte[] bytesAnormal = Base64.getDecoder().decode(b64);
        ByteArrayInputStream is = new ByteArrayInputStream(bytesAnormal);
        return new Image(is);
    }
    
    public void recuperarImagenBBDD(String b64, ImageView imagen) {
        imagen.setImage(getImage(b64));
    }
    
    public void crearTooltip(String msg, Node n) {
        Tooltip tooltip = new Tooltip(msg);
        tooltip.setStyle("-fx-font-size: 24px;");
        tooltip.setShowDelay(Duration.millis(300));
        tooltip.autoFixProperty().set(true);
        tooltip.consumeAutoHidingEventsProperty().set(true);
        tooltip.hideOnEscapeProperty().set(true);
        Tooltip.install(n, tooltip);
    }
}
