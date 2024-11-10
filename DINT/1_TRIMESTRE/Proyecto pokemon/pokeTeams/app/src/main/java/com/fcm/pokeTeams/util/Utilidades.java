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
    
    public void recuperarImagenBBDD(String b64, ImageView imagen) {
        byte[] bytesAnormal = Base64.getDecoder().decode(b64);
        ByteArrayInputStream is = new ByteArrayInputStream(bytesAnormal);
        Image nueva = new Image(is);
        imagen.setImage(nueva);
    }
}
