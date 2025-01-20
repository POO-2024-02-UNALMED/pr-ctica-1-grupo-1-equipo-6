
import java.util.ArrayList;
import java.util.Scanner;

public class ruta {
    int precio = 0;
    private int presupuesto;
    private int duracion;
    private int tipo;
    private String lugar;

    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }



    ArrayList<Lugar> lugaresDisponibles = new ArrayList<>();

    void RutaTuristica() {
        lugaresDisponibles.add(new Lugar("Museo del Oro", "Un museo con piezas precolombinas.", 50000));
        lugaresDisponibles.add(new Lugar("Monserrate", "Un mirador icónico de Bogotá.", 80000));
        lugaresDisponibles.add(new Lugar("Cristo Rey", "Una estatua monumental en Cali.", 30000));
        lugaresDisponibles.add(new Lugar("Castillo San Felipe", "Una fortaleza histórica en Cartagena.", 120000));
    }

    void itinerario(int puntaje) {
        Scanner scanner = new Scanner(System.in);
        boolean finalizar = false;

        while (!finalizar) {
            System.out.println("Lugares disponibles:");
            for (int i = 0; i < lugaresDisponibles.size(); i++) {
                Lugar lugar = lugaresDisponibles.get(i);
                if (lugar.getPrecio() <= puntaje) {
                    System.out.println((i + 1) + ". " + lugar.getNombre() + " - Precio: " + lugar.getPrecio());
                }
            }
            System.out.println("Seleccione un lugar por su número o escriba '0' para terminar:");
            int opcion = scanner.nextInt();

            if (opcion == 0) {
                finalizar = true;
            } else if (opcion > 0 && opcion <= lugaresDisponibles.size()) {
                Lugar lugarSeleccionado = lugaresDisponibles.get(opcion - 1);
                lugarSeleccionado.show();
                System.out.println("¿Desea agregar este lugar a la ruta turística? (si/no):");
                String respuesta = scanner.next();
                if (respuesta.equalsIgnoreCase("si")) {
                    precio += lugarSeleccionado.getPrecio();
                }
            } else {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}