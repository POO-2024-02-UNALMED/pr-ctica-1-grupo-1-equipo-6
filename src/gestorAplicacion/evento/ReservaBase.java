package gestorAplicacion.evento;

public abstract class ReservaBase {
    protected String fecha;
    protected String hora;

    public ReservaBase(String fecha, String hora) {
        this.fecha = fecha;
        this.hora = hora;
    }

    public abstract boolean verificarDisponibilidad();
}
