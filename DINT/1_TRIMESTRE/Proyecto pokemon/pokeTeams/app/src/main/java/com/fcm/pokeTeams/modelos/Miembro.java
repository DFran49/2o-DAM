/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcm.pokeTeams.modelos;

/**
 *
 * @author DFran49
 */
public class Miembro {
    private String mote;
    private int nPokedex;
    private char genero;
    private int nivel;
    private String habilidad;
    private String naturaleza;
    private String objeto;
    private String movimientos;
    private String evs;
    private String ivs;

    public Miembro() {
    }

    public String getMote() {
        return mote;
    }

    public void setMote(String mote) {
        this.mote = mote;
    }

    public int getnPokedex() {
        return nPokedex;
    }

    public void setnPokedex(int nPokedex) {
        this.nPokedex = nPokedex;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(String movimientos) {
        this.movimientos = movimientos;
    }

    public String getEvs() {
        return evs;
    }

    public void setEvs(String evs) {
        this.evs = evs;
    }

    public String getIvs() {
        return ivs;
    }

    public void setIvs(String ivs) {
        this.ivs = ivs;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    
}
