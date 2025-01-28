package uiMain;

import java.time.LocalDate;
import java.util.Scanner;
import gestorAplicacion.evento.*;
public class uiEvento {

    public static void procesar() {
        Scanner scanner = new Scanner(System.in);

        // Paso 1: Crear el evento específico
        System.out.println("¡Bienvenido a la agencia de viajes!");
        String nombreEvento = obtenerNombreEvento(scanner);
        
        LocalDate fechaEvento = obtenerFechaEvento(scanner);
        
        double precioEvento = obtenerPrecioEvento(scanner);
        
        String tipoEvento = obtenerTipoEvento(scanner);

        EventoEspecifico eventoEspecifico = new EventoEspecifico(nombreEvento, fechaEvento, precioEvento, tipoEvento);

        if (!eventoEspecifico.esPrecioValido()) {
            System.out.println("El precio del evento no es válido.");
            return;
        }

        if (!eventoEspecifico.esTipoEventoValido()) {
            System.out.println("El tipo de evento no es válido.");
            return;
        }

        System.out.println("\nPaso 1: Creación del evento específico - " + eventoEspecifico.detalles());

        // Paso 2: Confirmación de los detalles del evento
        System.out.println("¿Es correcto el evento? (Sí/No): ");
        String confirmacionEvento = scanner.nextLine(); 

        int numPersonas = obtenerNumeroPersonas(scanner);

        System.out.println("Calculando el costo total...");
        boolean reservaExitosa = Reserva.realizarReserva(eventoEspecifico, numPersonas);

        if (!reservaExitosa) {
            System.out.println("No se pudo completar la reserva.");
            return;
        }

        confirmarReserva(scanner);

        scanner.close();
    }

    private static String obtenerNombreEvento(Scanner scanner) {
        System.out.print("Ingresa el nombre del evento: ");
        return scanner.nextLine();
    }

    private static LocalDate obtenerFechaEvento(Scanner scanner) {
        System.out.print("Ingresa la fecha del evento (formato: yyyy-mm-dd): ");
        String fechaEvento = scanner.nextLine();
        return LocalDate.parse(fechaEvento);
    }

    private static double obtenerPrecioEvento(Scanner scanner) {
        double precio;
        while (true) {
            System.out.print("Ingresa el precio del evento: ");
            precio = scanner.nextDouble();
            scanner.nextLine(); 
            if (precio > 0) break;
            System.out.println("El precio debe ser mayor a cero.");
        }
        return precio;
    }

    private static String obtenerTipoEvento(Scanner scanner) {
        System.out.print("Ingresa el tipo de evento (por ejemplo, Concierto, Conferencia, etc.): ");
        return scanner.nextLine();
    }

    private static int obtenerNumeroPersonas(Scanner scanner) {
        int numPersonas;
        while (true) {
            System.out.print("¿Cuántas personas desean asistir al evento? ");
            numPersonas = scanner.nextInt();
            scanner.nextLine(); 
            if (numPersonas > 0) break;
            System.out.println("El número de personas debe ser mayor a cero.");
        }
        return numPersonas;
    }

    private static void confirmarReserva(Scanner scanner) {
        System.out.println("¿Deseas confirmar la reserva? (Sí/No): ");
        String confirmacionReserva = scanner.nextLine(); 
        if (confirmacionReserva.equalsIgnoreCase("Sí")) {
            System.out.println("\nPaso 4: Finalizando reserva...");
            System.out.println("¡Reserva confirmada con éxito!");
        } else {
            System.out.println("Reserva cancelada.");
        }
    }
}
