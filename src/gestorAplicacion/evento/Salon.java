package gestorAplicacion.evento;

public class Salon {
    private int capacidadMaxima;
    private boolean disponible;
    private boolean reservado;
    private String serviciosConfirmados; // Servicios confirmados para el evento

    public Salon(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.disponible = true;  // Suponemos que el salón está disponible inicialmente
        this.reservado = false;  // No está reservado
        this.serviciosConfirmados = "";
    }

    public boolean verificarDisponibilidad(String fecha, String hora) {
        // Lógica para verificar la disponibilidad en una base de datos o sistema
        return disponible;
    }

    public boolean verificarCapacidad(int numeroParticipantes) {
        return numeroParticipantes <= capacidadMaxima;
    }

    public void confirmarServicios(String servicios) {
        this.serviciosConfirmados = servicios;
        System.out.println("Servicios confirmados: " + servicios);
    }

    public void organizarLogistica(int tiempoMontaje, int tiempoDesmontaje) {
        System.out.println("Organizando la logística para el evento...");
        System.out.println("Tiempo de montaje: " + tiempoMontaje + " horas.");
        System.out.println("Tiempo de desmontaje: " + tiempoDesmontaje + " horas.");
    }

    public void reservar() {
        this.reservado = true;
        this.disponible = false;
        System.out.println("El salón ha sido reservado.");
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public boolean isReservado() {
        return reservado;
    }

    public String getServiciosConfirmados() {
        return serviciosConfirmados;
    }
}
