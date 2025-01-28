package gestorAplicacion.evento;

public class Salon {
    private int capacidadMaxima;
    private String[] serviciosAdicionales;
    private String estadoSalon; 

    public Salon(int capacidadMaxima, String[] serviciosAdicionales) {
        this.capacidadMaxima = capacidadMaxima;
        this.serviciosAdicionales = serviciosAdicionales;
        this.estadoSalon = "disponible"; // Inicialmente disponible
    }

    public boolean verificarCapacidad(int numeroParticipantes) {
        return numeroParticipantes <= capacidadMaxima;
    }

    public void actualizarEstado(String estado) {
        this.estadoSalon = estado;
    }

    public String[] obtenerServiciosAdicionales() {
        return serviciosAdicionales;
    }

    // Obtener el estado actual del salón
    public String obtenerEstado() {
        return estadoSalon;
    }
}
