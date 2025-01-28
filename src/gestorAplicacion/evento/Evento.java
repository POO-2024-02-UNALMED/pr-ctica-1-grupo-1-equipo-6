package gestorAplicacion.evento;

public class Evento {
    private String tipoEvento;
    private int numeroParticipantes;
    private String[] recursosSolicitados;

    public Evento(String tipoEvento, int numeroParticipantes, String[] recursosSolicitados) {
        this.tipoEvento = tipoEvento;
        this.numeroParticipantes = numeroParticipantes;
        this.recursosSolicitados = recursosSolicitados;
    }

    public void ajustarRecursos() {
        // Lógica para ajustar los recursos solicitados según la disponibilidad
        System.out.println("Ajustando los recursos para el evento...");
    }

    public void confirmarEvento() {
        System.out.println("El evento ha sido confirmado: " + tipoEvento);
    }

    public String[] obtenerRecursosSolicitados() {
        return recursosSolicitados;
    }
}
