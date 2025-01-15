public class LugTuristico {
    int preferencias;
    int lugares;
    int presupuesto;
    int puntaje;

    int buscar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione un lugar: \n1. Bogotá \n2. Cali \n3. Medellín \n4. Cartagena");
        int opcionLugar = scanner.nextInt();
        switch (opcionLugar) {
            case 1 -> lugares = 2;
            case 2 -> lugares = 1;
            case 3 -> lugares = 3;
            case 4 -> lugares = 5;
            default -> {
                System.out.println("Opción no válida. Asignando Bogotá por defecto.");
                lugares = 2;
            }
        }

        System.out.println("Seleccione un rango de presupuesto: \n1. 500,000 - 1,000,000 \n2. 1,000,001 - 1,500,000 \n3. 1,500,001 - 2,000,000 \n4. 2,000,001+");
        int opcionPresupuesto = scanner.nextInt();
        switch (opcionPresupuesto) {
            case 1 -> presupuesto = 3;
            case 2 -> presupuesto = 5;
            case 3 -> presupuesto = 7;
            case 4 -> presupuesto = 9;
            default -> {
                System.out.println("Opción no válida. Asignando el presupuesto más bajo por defecto.");
                presupuesto = 3;
            }
        }

        System.out.println("Seleccione una preferencia: \n1. Senderismo \n2. Urbanismo \n3. Mix");
        int opcionPreferencia = scanner.nextInt();
        switch (opcionPreferencia) {
            case 1 -> preferencias = 3;
            case 2 -> preferencias = 5;
            case 3 -> preferencias = 8;
            default -> {
                System.out.println("Opción no válida. Asignando Senderismo por defecto.");
                preferencias = 3;
            }
        }

        puntaje = (lugares + presupuesto + preferencias) * 10;
        System.out.println("Puntaje calculado: " + puntaje);
        return puntaje;
    }
}
