package uiMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import gestorAplicacion.evento.*;

public class uiEvento {

    public static void ejecutarEvento() {
        Scanner scanner = new Scanner(System.in);

        String[] serviciosDisponibles = {
            "Catering", "Proyector", "Personal de apoyo", "Sonido e iluminación", 
            "Fotografía y video", "Decoración", "Wi-Fi", "Aire acondicionado/Calefacción"
        };

        System.out.println("Servicios adicionales disponibles:");
        for (int i = 0; i < serviciosDisponibles.length; i++) {
            System.out.println((i + 1) + ". " + serviciosDisponibles[i]);
        }

        System.out.println("Ingrese la capacidad máxima del salón: ");
        int capacidadMaxima = scanner.nextInt();
        scanner.nextLine();  

        Salon salon = new Salon(capacidadMaxima, new String[0]);

        System.out.println("Ingrese la fecha de la reserva (formato: yyyy-MM-dd): ");
        String fechaReserva = scanner.nextLine();
        System.out.println("Ingrese la hora de la reserva (formato: HH:mm): ");
        String horaReserva = scanner.nextLine();
        System.out.println("Ingrese el número de participantes: ");
        int numeroParticipantes = scanner.nextInt();
        scanner.nextLine(); 

        ReservaSalon reserva = new ReservaSalon(fechaReserva, horaReserva, numeroParticipantes);

        List<String> serviciosSeleccionados = new ArrayList<>();
        System.out.println("Seleccione los servicios adicionales (Ingrese el número de la opción, separados por comas): ");
        String serviciosInput = scanner.nextLine();
        String[] serviciosElegidos = serviciosInput.split(",\\s*");

        for (String servicio : serviciosElegidos) {
            int opcion = Integer.parseInt(servicio.trim()) - 1;
            if (opcion >= 0 && opcion < serviciosDisponibles.length) {
                serviciosSeleccionados.add(serviciosDisponibles[opcion]);
            }
        }

        String[] serviciosArray = serviciosSeleccionados.toArray(new String[0]);
        System.out.println("Ingrese el tipo de evento: ");
        String tipoEvento = scanner.nextLine();

        Evento evento = new Evento(tipoEvento, numeroParticipantes, serviciosArray);

        if (reserva.verificarDisponibilidad()) {
            System.out.println("El salón está disponible solo por capacidad.");
            evento.ajustarRecursos();
            evento.confirmarEvento();
        } else {
            System.out.println("El salón no está disponible por capacidad.");
        }

        if (reserva.verificarDisponibilidad(true)) {
            System.out.println("El salón está disponible con capacidad y servicios adicionales.");
            evento.ajustarRecursos();
            evento.confirmarEvento();
        } else {
            System.out.println("El salón no está disponible con los servicios adicionales.");
        }

        if (salon.verificarCapacidad(evento.obtenerRecursosSolicitados().length)) {
            salon.actualizarEstado("reservado");
            System.out.println("El salón ha sido actualizado a 'reservado'.");
        }

        System.out.println("El salón ha sido confirmado para la fecha y hora solicitada.");
        System.out.println("Detalles del evento: Capacidad del salón: " + salon.obtenerEstado() +
                ", Servicios incluidos: " + String.join(", ", salon.obtenerServiciosAdicionales()));

        evento.ajustarRecursos();
        evento.confirmarEvento();

        System.out.println("\nReserva confirmada: El evento '" + tipoEvento + "' ha sido confirmado con éxito.");


    }
}
