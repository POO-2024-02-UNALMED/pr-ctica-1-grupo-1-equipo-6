public class Viaje {
    int numTotal;
    int documento;
    int refrigerio;
    int transporte;
    int extender;

    void calcTotal(int precioRuta) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el número total de viajeros:");
        numTotal = scanner.nextInt();

        System.out.println("Seleccione el tipo de refrigerio: \n1. 4500 \n2. 6000 \n3. 7000");
        int opcionRefrigerio = scanner.nextInt();
        refrigerio = switch (opcionRefrigerio) {
            case 1 -> 4500;
            case 2 -> 6000;
            case 3 -> 7000;
            default -> 4500;
        };

        System.out.println("Seleccione el tipo de transporte: \n1. Categoría 1 \n2. Categoría 2 \n3. Categoría 3");
        int opcionTransporte = scanner.nextInt();
        transporte = switch (opcionTransporte) {
            case 1 -> 10000;
            case 2 -> 20000;
            case 3 -> 30000;
            default -> 10000;
        };

        System.out.println("¿Desea extender la ruta? (1. Sí / 2. No):");
        extender = (scanner.nextInt() == 1) ? 50000 : 0;

        int total = (precioRuta + refrigerio + transporte + extender) * numTotal;
        System.out.println("El costo total del viaje es: " + total);

        System.out.println("¿Desea confirmar el viaje? (si/no):");
        String confirmar = scanner.next();
        if (confirmar.equalsIgnoreCase("si")) {
            System.out.println("Viaje confirmado. ¡Disfrute su experiencia!");
        } else {
            System.out.println("Viaje cancelado.");
        }
    }
}
