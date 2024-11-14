/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams.util;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
}
