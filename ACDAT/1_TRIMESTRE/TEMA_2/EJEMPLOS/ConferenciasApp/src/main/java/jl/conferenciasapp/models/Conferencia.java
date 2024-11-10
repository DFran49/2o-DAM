package jl.conferenciasapp.models;

import java.time.LocalDate;

public class Conferencia {
    private String referencia;
    private String tema;
    private Double precio;
    private LocalDate fecha;
    private String turno;
    private String sala;

    public Conferencia() { }

    public String getReferencia() {
        return referencia;
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTema() {
        return tema;
    }
    public void setTema(String tema) {
        this.tema = tema;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getSala() {
        return sala;
    }
    public void setSala(String sala) {
        this.sala = sala;
    }
}
