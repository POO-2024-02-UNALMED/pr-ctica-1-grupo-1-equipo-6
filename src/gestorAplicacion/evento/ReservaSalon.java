package gestorAplicacion.evento;

public class ReservaSalon extends ReservaBase {
    private int numeroParticipantes;

    // Constructor que recibe los detalles de la reserva
    public ReservaSalon(String fecha, String hora, int numeroParticipantes) {
        super(fecha, hora); 
        this.numeroParticipantes = numeroParticipantes;
    }

    public boolean verificarDisponibilidad() {
        return numeroParticipantes <= 50; 
    }

    public boolean verificarDisponibilidad(boolean conServiciosAdicionales) {
        if (conServiciosAdicionales) {
            return numeroParticipantes <= 50 && serviciosAdicionalesDisponibles();
        }
        return verificarDisponibilidad(); 
    }

    private boolean serviciosAdicionalesDisponibles() {
        return true; // Suponiendo que siempre están disponibles
    }
}
