package jl.conferenciasapp.models;

public class Sala {
    private String nombre;
    private Integer capacidad;

    public Sala() { }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}
