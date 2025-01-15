public class RutaTuristica {
    int precio = 0;
    ArrayList<Lugar> lugaresDisponibles = new ArrayList<>();

    RutaTuristica() {
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
                if (lugar.precio <= puntaje) {
                    System.out.println((i + 1) + ". " + lugar.nombre + " - Precio: " + lugar.precio);
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
                    precio += lugarSeleccionado.precio;
                }
            } else {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}