package fcm;


public class Coche implements Runnable {
    private String nombre;
    private int distanciaRecorrida;
    private static final int DISTANCIA_META = 100;
    public Coche(String nombre) {
        this.nombre = nombre;
        this.distanciaRecorrida = 0;
    }
    @Override
    public void run() {
// Implementa la lógica de la carrera aquí
    }
// Otros métodos necesarios
}
