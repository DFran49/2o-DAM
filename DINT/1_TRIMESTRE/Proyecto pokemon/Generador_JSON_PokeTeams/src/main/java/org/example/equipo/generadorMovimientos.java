package org.example.equipo;

public class generadorMovimientos {
}

/*
public class generadorHabilidades {
    private List<Habilidad> habilidades = new ArrayList<>();
    public List<Habilidad> getHabilidades() { return habilidades; }
    public static void generarHabilidades(String pokemon, int nHabilidades) {

        String nombre = "";
        String descripcion = "";

        for (int i = 1; i <= nHabilidades; i++) {
            System.out.println("Introduzca el nombre de la habilidad " + i);
            nombre = new Scanner(System.in).nextLine();
            System.out.println("Introduzca la descripción de la habilidad " + nombre);
            descripcion = new Scanner(System.in).nextLine();
            Habilidad temp = new Habilidad(nombre, descripcion);
            listaHabilidades.getHabilidades().add(temp);
        }

        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("habilidades" + pokemon + ".json")) {
            gson.toJson(listaHabilidades, writer);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try (FileReader reader = new FileReader("habilidades" + pokemon + ".json")) {
            generadorHabilidades listaPersonasLeido = gson.fromJson(reader, generadorHabilidades.class);
            listaPersonasLeido.getHabilidades().forEach(p -> System.out.println(p.getNombre() +
                    " - " + p.getDescripcion()));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
*/