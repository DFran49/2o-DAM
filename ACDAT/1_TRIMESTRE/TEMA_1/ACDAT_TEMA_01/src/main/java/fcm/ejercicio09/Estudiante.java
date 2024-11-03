package fcm.ejercicio09;

import java.util.Arrays;

public class Estudiante {
    private String nombre;
    private int edad;
    private double[] calificaciones;

    public Estudiante(String nombre, int edad, double[] calificaciones) {
        this.nombre = nombre;
        this.edad = edad;
        this.calificaciones = calificaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double[] getCalificaciones() {
        return calificaciones;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre +
                ", edad=" + edad +
                ", calificaciones=" + Arrays.toString(calificaciones) +
                '}';
    }
}
